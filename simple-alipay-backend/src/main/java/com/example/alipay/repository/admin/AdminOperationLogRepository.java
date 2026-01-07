package com.example.alipay.repository.admin;

import com.example.alipay.model.admin.AdminOperationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdminOperationLogRepository extends JpaRepository<AdminOperationLog, Long> {
    List<AdminOperationLog> findByAdminIdOrderByOperationTimeDesc(Long adminId);
    List<AdminOperationLog> findByOperationTypeOrderByOperationTimeDesc(String operationType);
}
