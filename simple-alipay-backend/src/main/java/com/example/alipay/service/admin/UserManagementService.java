package com.example.alipay.service.admin;

import com.example.alipay.model.fintech.User;
import com.example.alipay.model.fintech.UserAudit;
import com.example.alipay.model.fintech.UserStatus;
import com.example.alipay.repository.fintech.UserAuditRepository;
import com.example.alipay.repository.fintech.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserManagementService {
    private final UserRepository userRepository;
    private final UserAuditRepository userAuditRepository;
    private final AdminOperationLogger adminOperationLogger;

    public UserManagementService(UserRepository userRepository, UserAuditRepository userAuditRepository, AdminOperationLogger adminOperationLogger) {
        this.userRepository = userRepository;
        this.userAuditRepository = userAuditRepository;
        this.adminOperationLogger = adminOperationLogger;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public boolean disableUser(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setStatus(UserStatus.INACTIVE);
            userRepository.save(user);
            adminOperationLogger.log("USER_DISABLE", "USER", userId, "disable user", true, null);
            return true;
        }
        adminOperationLogger.log("USER_DISABLE", "USER", userId, "disable user", false, "user not found");
        return false;
    }

    public boolean enableUser(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setStatus(UserStatus.ACTIVE);
            userRepository.save(user);
            adminOperationLogger.log("USER_ENABLE", "USER", userId, "enable user", true, null);
            return true;
        }
        adminOperationLogger.log("USER_ENABLE", "USER", userId, "enable user", false, "user not found");
        return false;
    }

    public boolean approveUserAudit(Long userId) {
        Optional<UserAudit> auditOpt = userAuditRepository.findByUserId(userId);
        Optional<User> userOpt = userRepository.findById(userId);
        if (auditOpt.isPresent() && userOpt.isPresent()) {
            User user = userOpt.get();
            user.setStatus(UserStatus.ACTIVE);
            userRepository.save(user);
            userAuditRepository.delete(auditOpt.get());
            adminOperationLogger.log("USER_AUDIT_APPROVE", "USER", userId, "approve user audit", true, null);
            return true;
        }
        adminOperationLogger.log("USER_AUDIT_APPROVE", "USER", userId, "approve user audit", false, "audit or user not found");
        return false;
    }

    public boolean rejectUserAudit(Long userId, String rejectReason) {
        Optional<UserAudit> auditOpt = userAuditRepository.findByUserId(userId);
        Optional<User> userOpt = userRepository.findById(userId);
        if (auditOpt.isPresent() && userOpt.isPresent()) {
            User user = userOpt.get();
            user.setStatus(UserStatus.REJECTED);
            userRepository.save(user);

            UserAudit audit = auditOpt.get();
            audit.setRejectReason(rejectReason);
            userAuditRepository.save(audit);
            adminOperationLogger.log("USER_AUDIT_REJECT", "USER", userId, "reject user audit: " + rejectReason, true, null);
            return true;
        }
        adminOperationLogger.log("USER_AUDIT_REJECT", "USER", userId, "reject user audit", false, "audit or user not found");
        return false;
    }

    public List<UserAudit> getPendingAudits() {
        // 获取待审核的用户
        return userAuditRepository.findAll();
    }
}
