package com.example.alipay.service.admin;

import com.example.alipay.model.admin.AdminOperationLog;
import com.example.alipay.repository.admin.AdminOperationLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminOperationLogService {
    private final AdminOperationLogRepository logRepository;

    public AdminOperationLogService(AdminOperationLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<AdminOperationLog> getAllLogs() {
        return logRepository.findAll();
    }

    public List<AdminOperationLog> getLogsByAdmin(Long adminId) {
        return logRepository.findByAdminIdOrderByOperationTimeDesc(adminId);
    }

    public List<AdminOperationLog> getLogsByType(String operationType) {
        return logRepository.findByOperationTypeOrderByOperationTimeDesc(operationType);
    }
}
