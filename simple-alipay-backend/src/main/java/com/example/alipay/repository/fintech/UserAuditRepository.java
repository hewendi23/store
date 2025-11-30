package com.example.alipay.repository.fintech;

import com.example.alipay.model.fintech.UserAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserAuditRepository extends JpaRepository<UserAudit, Long> {
    Optional<UserAudit> findByUserId(Long userId);
}
