package com.example.alipay.repository;

import com.example.alipay.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByFromUserAndCreatedAtBetweenAndStatus(String fromUser, LocalDateTime start, LocalDateTime end, String status);
    List<Payment> findByFromUserAndAmountGreaterThanEqualAndStatus(String fromUser, BigDecimal minAmount, String status);
}
