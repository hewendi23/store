package com.example.alipay.repository.fintech;

import com.example.alipay.model.fintech.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BankCardRepository extends JpaRepository<BankCard, Long> {
    List<BankCard> findByUserId(Long userId);
    List<BankCard> findByUserIdAndIsDefaultTrue(Long userId);
}
