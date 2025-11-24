package com.example.alipay.service;

import com.example.alipay.model.Payment;
import com.example.alipay.model.UserAccount;
import com.example.alipay.repository.PaymentRepository;
import com.example.alipay.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserAccountRepository userRepo;

    public PaymentService(PaymentRepository paymentRepository, UserAccountRepository userRepo){
        this.paymentRepository = paymentRepository;
        this.userRepo = userRepo;
    }

    public Payment createAndExecutePayment(String fromUser, String toMerchant, BigDecimal amount, String paymentMethod){
        Payment p = new Payment();
        p.setFromUser(fromUser);
        p.setToMerchant(toMerchant);
        p.setAmount(amount);
        p.setPaymentMethod(paymentMethod);
        p.setStatus("INIT");
        p.setCreatedAt(LocalDateTime.now());
        paymentRepository.save(p);

        // Very simple "payment": deduct from fromUser balance if exists
        Optional<UserAccount> opt = userRepo.findByUsername(fromUser);
        if (opt.isPresent() && "balance".equals(paymentMethod)){
            UserAccount u = opt.get();
            if (u.getBalance().compareTo(amount) >= 0){
                u.setBalance(u.getBalance().subtract(amount));
                userRepo.save(u);
                p.setStatus("SUCCESS");
            } else {
                p.setStatus("FAILED");
            }
        } else {
            // if user not found or non-balance, mark success for demo
            p.setStatus("SUCCESS");
        }
        paymentRepository.save(p);
        return p;
    }
}
