package com.example.alipay.service.admin;

import com.example.alipay.model.admin.AdminOperationLog;
import com.example.alipay.model.admin.AdminUser;
import com.example.alipay.repository.admin.AdminOperationLogRepository;
import com.example.alipay.repository.admin.AdminUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdminOperationLogger {
    private final AdminOperationLogRepository logRepository;
    private final AdminUserRepository adminUserRepository;

    public AdminOperationLogger(AdminOperationLogRepository logRepository, AdminUserRepository adminUserRepository) {
        this.logRepository = logRepository;
        this.adminUserRepository = adminUserRepository;
    }

    public void log(String operationType, String targetType, Long targetId, String operationDetails, boolean success, String errorMessage) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth != null ? auth.getName() : null;
            Long adminId = null;
            if (auth != null && auth.getPrincipal() instanceof AdminUser au) {
                adminId = au.getId();
                username = au.getUsername();
            } else if (username != null) {
                Optional<AdminUser> adminOpt = adminUserRepository.findByUsername(username);
                if (adminOpt.isPresent()) {
                    adminId = adminOpt.get().getId();
                }
            }

            String ip = null;
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest request = attrs.getRequest();
                ip = request.getHeader("X-Forwarded-For");
                if (ip == null || ip.isBlank()) {
                    ip = request.getRemoteAddr();
                }
            }

            AdminOperationLog log = new AdminOperationLog();
            log.setAdminId(adminId);
            log.setAdminUsername(username);
            log.setOperationType(operationType);
            log.setTargetType(targetType);
            log.setTargetId(targetId);
            log.setOperationDetails(operationDetails);
            log.setIpAddress(ip);
            log.setOperationTime(LocalDateTime.now());
            log.setSuccess(success);
            log.setErrorMessage(errorMessage);
            logRepository.save(log);
        } catch (Exception ignored) {
        }
    }
}

