package com.example.alipay.service;

import com.example.alipay.model.Payment;
import com.example.alipay.model.UserAccount;
import com.example.alipay.model.fintech.User;
import com.example.alipay.model.fintech.UserStatus;
import com.example.alipay.model.fintech.BankCard;
import com.example.alipay.model.fintech.Bill;
import com.example.alipay.repository.PaymentRepository;
import com.example.alipay.repository.UserAccountRepository;
import com.example.alipay.repository.fintech.UserRepository;
import com.example.alipay.repository.fintech.BankCardRepository;
import com.example.alipay.repository.fintech.BillRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;
    private final BankCardRepository bankCardRepository;
    private final PasswordEncoder passwordEncoder;
    private final BillRepository billRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            UserRepository userRepository,
            UserAccountRepository userAccountRepository,
            BankCardRepository bankCardRepository,
            PasswordEncoder passwordEncoder,
            BillRepository billRepository
    ){
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;
        this.bankCardRepository = bankCardRepository;
        this.passwordEncoder = passwordEncoder;
        this.billRepository = billRepository;
    }

    public String preCheck(String username, String payPassword) {
        Optional<User> opt = userRepository.findByUsername(username);
        if (opt.isEmpty()) return "USER_NOT_FOUND";
        User u = opt.get();
        if (u.getPayPwdLockedUntil() != null && u.getPayPwdLockedUntil().isAfter(java.time.LocalDateTime.now())) return "LOCKED";
        if (payPassword == null || u.getPayPassword() == null || !passwordEncoder.matches(payPassword, u.getPayPassword())) {
            int attempts = u.getPayPwdFailedAttempts() == null ? 0 : u.getPayPwdFailedAttempts();
            attempts++;
            u.setPayPwdFailedAttempts(attempts);
            if (attempts >= 5) {
                u.setPayPwdLockedUntil(java.time.LocalDateTime.now().plusMinutes(30));
                u.setPayPwdFailedAttempts(0);
            }
            userRepository.save(u);
            return "INVALID_PASSWORD";
        }
        u.setPayPwdFailedAttempts(0);
        u.setPayPwdLockedUntil(null);
        userRepository.save(u);
        if (u.getStatus() == UserStatus.PENDING_AUDIT) return "PENDING_AUDIT";
        if (u.getStatus() == UserStatus.REJECTED) return "REJECTED";
        if (u.getStatus() == UserStatus.INACTIVE) return "INACTIVE";
        return null;
    }

    @Transactional
    public Payment createAndExecutePayment(String fromUser, String toMerchant, BigDecimal amount, String paymentMethod, String paySource, Long payCardId, String receiveSource, Long receiveCardId){
        String normalizedFromUser = normalizeUserKey(fromUser);
        String normalizedToMerchant = normalizeUserKey(toMerchant);

        Payment p = new Payment();
        p.setFromUser(fromUser);
        p.setToMerchant(toMerchant);
        p.setAmount(amount);
        p.setPaymentMethod(paymentMethod);
        p.setStatus("INIT");
        p.setCreatedAt(LocalDateTime.now());
        paymentRepository.save(p);

        boolean receiveOnly = (paySource == null || paySource.isBlank());
        if (receiveOnly) {
            Optional<User> toFintechOpt = userRepository.findByUsernameForUpdate(normalizedToMerchant);
            Optional<UserAccount> toAccountOpt = toFintechOpt.isPresent()
                    ? Optional.empty()
                    : userAccountRepository.findByUsernameForUpdate(normalizedToMerchant);

            if (toFintechOpt.isPresent()) {
                User to = toFintechOpt.get();
                to.setBalance(to.getBalance() == null ? amount : to.getBalance().add(amount));
                userRepository.save(to);
                p.setStatus("SUCCESS");
                createBillIfNotExists(to.getId(), amount, "INCOME", "收款", "收款入账", "PAYMENT", "PAY:" + p.getId());
            } else if (toAccountOpt.isPresent()) {
                UserAccount to = toAccountOpt.get();
                BigDecimal toBalance = to.getBalance() == null ? BigDecimal.ZERO : to.getBalance();
                to.setBalance(toBalance.add(amount));
                userAccountRepository.save(to);
                p.setStatus("SUCCESS");
            } else {
                p.setStatus("FAILED");
                p.setErrorCode("RECEIVER_NOT_FOUND");
                p.setErrorMessage("receiver not found");
            }
            paymentRepository.save(p);
            return p;
        }

        Optional<User> fromOpt = userRepository.findByUsernameForUpdate(normalizedFromUser);
        if (fromOpt.isEmpty()) {
            p.setStatus("FAILED");
            paymentRepository.save(p);
            return p;
        }
        User from = fromOpt.get();

        boolean deducted = false;
        if ("balance".equalsIgnoreCase(paySource)) {
            if (from.getBalance() != null && from.getBalance().compareTo(amount) >= 0) {
                from.setBalance(from.getBalance().subtract(amount));
                userRepository.save(from);
                deducted = true;
            } else {
                p.setStatus("FAILED");
                p.setErrorCode("INSUFFICIENT_FUNDS");
                p.setErrorMessage("insufficient balance");
            }
        } else if ("card".equalsIgnoreCase(paySource)) {
            if (payCardId != null) {
                Optional<BankCard> cardOpt = bankCardRepository.findById(payCardId);
                if (cardOpt.isPresent() && cardOpt.get().getUserId().equals(from.getId())) {
                    deducted = true;
                } else {
                    p.setStatus("FAILED");
                    p.setErrorCode(cardOpt.isEmpty() ? "CARD_NOT_FOUND" : "CARD_OWNERSHIP_ERROR");
                    p.setErrorMessage("invalid card");
                }
            } else {
                p.setStatus("FAILED");
                p.setErrorCode("CARD_NOT_FOUND");
                p.setErrorMessage("card required");
            }
        } else {
            p.setStatus("FAILED");
            p.setErrorCode("INVALID_PAY_SOURCE");
            p.setErrorMessage("invalid pay source");
        }

        if (!"FAILED".equals(p.getStatus())) {
            Optional<User> toFintechOpt = userRepository.findByUsernameForUpdate(normalizedToMerchant);
            Optional<UserAccount> toAccountOpt = toFintechOpt.isPresent()
                    ? Optional.empty()
                    : userAccountRepository.findByUsernameForUpdate(normalizedToMerchant);

            if (deducted) {
                createBillIfNotExists(from.getId(), amount, "EXPENDITURE", "支付", "支付给:" + normalizedToMerchant, "PAYMENT", "PAY:" + p.getId());
            }

            if (toFintechOpt.isPresent()) {
                User to = toFintechOpt.get();
                to.setBalance(to.getBalance() == null ? amount : to.getBalance().add(amount));
                userRepository.save(to);
                if (deducted) {
                    createBillIfNotExists(to.getId(), amount, "INCOME", "收款", "来自:" + normalizedFromUser, "PAYMENT", "PAY:" + p.getId());
                }
            } else if (toAccountOpt.isPresent()) {
                UserAccount to = toAccountOpt.get();
                BigDecimal toBalance = to.getBalance() == null ? BigDecimal.ZERO : to.getBalance();
                to.setBalance(toBalance.add(amount));
                userAccountRepository.save(to);
            }

            p.setStatus(deducted ? "SUCCESS" : "FAILED");
        }

        paymentRepository.save(p);
        return p;
    }

    private String normalizeUserKey(String raw) {
        if (raw == null) return null;
        String v = raw.trim();
        if (v.isEmpty()) return v;
        if (v.startsWith("collect://")) {
            v = v.substring("collect://".length());
        }
        int slash = v.indexOf('/');
        if (slash > 0) {
            return v.substring(0, slash);
        }
        return v;
    }

    private void createBillIfNotExists(Long userId, BigDecimal amount, String type, String category, String remark, String sourceType, String sourceRefId) {
        if (userId == null) return;
        if (billRepository.existsByUserIdAndSourceTypeAndSourceRefId(userId, sourceType, sourceRefId)) return;
        Bill b = new Bill();
        b.setUserId(userId);
        b.setAmount(amount);
        b.setType(type);
        b.setCategory(category);
        b.setTransactionTime(java.time.LocalDateTime.now());
        b.setRemark(remark);
        b.setSourceType(sourceType);
        b.setSourceRefId(sourceRefId);
        billRepository.save(b);
    }
}
