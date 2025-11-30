package com.example.alipay.repository;

import com.example.alipay.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // [新增] 用于月度分析：根据 用户名 + 时间范围 + 状态 查找
    List<Payment> findByFromUserAndCreatedAtBetweenAndStatus(String fromUser, LocalDateTime start, LocalDateTime end, String status);

    // [新增] 用于异常提醒：根据 用户名 + 最小金额 + 状态 查找
    List<Payment> findByFromUserAndAmountGreaterThanEqualAndStatus(String fromUser, BigDecimal minAmount, String status);
}