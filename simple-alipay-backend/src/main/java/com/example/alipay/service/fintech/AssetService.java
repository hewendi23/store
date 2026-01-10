package com.example.alipay.service.fintech;

import com.example.alipay.model.fintech.BankCard;
import com.example.alipay.model.fintech.Bill;
import com.example.alipay.model.fintech.User;
import com.example.alipay.repository.fintech.BankCardRepository;
import com.example.alipay.repository.fintech.BillRepository;
import com.example.alipay.repository.fintech.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {
    private final UserRepository userRepository;
    private final BankCardRepository bankCardRepository;
    private final BillRepository billRepository;

    public AssetService(UserRepository userRepository, BankCardRepository bankCardRepository, BillRepository billRepository) {
        this.userRepository = userRepository;
        this.bankCardRepository = bankCardRepository;
        this.billRepository = billRepository;
    }

    public BigDecimal getBalance(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        return userOpt.map(User::getBalance).orElse(BigDecimal.ZERO);
    }

    public BankCard addBankCard(Long userId, String bankName, String cardNumber, Boolean isDefault) {
        BankCard card = new BankCard();
        card.setUserId(userId);
        card.setBankName(bankName);
        card.setCardNumber(cardNumber);
        card.setIsDefault(isDefault);

        if (isDefault) {
            List<BankCard> defaultCards = bankCardRepository.findByUserIdAndIsDefaultTrue(userId);
            for (BankCard defaultCard : defaultCards) {
                defaultCard.setIsDefault(false);
                bankCardRepository.save(defaultCard);
            }
        }

        return bankCardRepository.save(card);
    }

    public List<BankCard> getUserCards(Long userId) {
        return bankCardRepository.findByUserId(userId);
    }

    @Transactional
    public boolean transfer(Long fromUserId, Long toUserId, BigDecimal amount) {
        Optional<User> fromUserOpt = userRepository.findByIdForUpdate(fromUserId);
        Optional<User> toUserOpt = userRepository.findByIdForUpdate(toUserId);

        if (fromUserOpt.isEmpty() || toUserOpt.isEmpty()) {
            return false;
        }

        User fromUser = fromUserOpt.get();
        User toUser = toUserOpt.get();

        if (fromUser.getBalance().compareTo(amount) < 0) {
            return false;
        }

        fromUser.setBalance(fromUser.getBalance().subtract(amount));
        userRepository.save(fromUser);

        toUser.setBalance(toUser.getBalance().add(amount));
        userRepository.save(toUser);

        createBill(fromUserId, amount.negate(), "EXPENDITURE", "转账", "转账给用户" + toUser.getUsername());
        createBill(toUserId, amount, "INCOME", "转账", "收到用户" + fromUser.getUsername() + "的转账");

        return true;
    }

    private void createBill(Long userId, BigDecimal amount, String type, String category, String remark) {
        Bill bill = new Bill();
        bill.setUserId(userId);
        bill.setAmount(amount);
        bill.setType(type);
        bill.setCategory(category);
        bill.setTransactionTime(LocalDateTime.now());
        bill.setRemark(remark);
        billRepository.save(bill);
    }
}
