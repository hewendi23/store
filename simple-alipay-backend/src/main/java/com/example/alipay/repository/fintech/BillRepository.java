package com.example.alipay.repository.fintech;

import com.example.alipay.model.fintech.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByUserIdOrderByTransactionTimeDesc(Long userId);
    List<Bill> findByUserIdAndTypeOrderByTransactionTimeDesc(Long userId, String type);
    boolean existsByUserIdAndSourceTypeAndSourceRefId(Long userId, String sourceType, String sourceRefId);
    List<Bill> findByUserIdAndTypeAndTransactionTimeBetween(Long userId, String type, LocalDateTime start, LocalDateTime end);
}
