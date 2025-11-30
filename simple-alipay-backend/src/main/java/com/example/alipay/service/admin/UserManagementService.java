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

    public UserManagementService(UserRepository userRepository, UserAuditRepository userAuditRepository) {
        this.userRepository = userRepository;
        this.userAuditRepository = userAuditRepository;
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
            return true;
        }
        return false;
    }

    public boolean enableUser(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setStatus(UserStatus.ACTIVE);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean approveUserAudit(Long userId) {
        Optional<UserAudit> auditOpt = userAuditRepository.findByUserId(userId);
        if (auditOpt.isPresent()) {
            UserAudit audit = auditOpt.get();
            // 这里可以添加审核通过后的逻辑
            userAuditRepository.delete(audit);
            return true;
        }
        return false;
    }

    public boolean rejectUserAudit(Long userId, String rejectReason) {
        Optional<UserAudit> auditOpt = userAuditRepository.findByUserId(userId);
        if (auditOpt.isPresent()) {
            UserAudit audit = auditOpt.get();
            audit.setRejectReason(rejectReason);
            userAuditRepository.save(audit);
            return true;
        }
        return false;
    }

    public List<UserAudit> getPendingAudits() {
        // 获取待审核的用户
        return userAuditRepository.findAll();
    }
}
