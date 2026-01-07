package com.example.alipay.service.fintech;

import com.example.alipay.model.fintech.User;
import com.example.alipay.model.fintech.UserStatus;
import com.example.alipay.model.fintech.UserAudit;
import com.example.alipay.repository.fintech.UserRepository;
import com.example.alipay.repository.fintech.UserAuditRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final UserAuditRepository userAuditRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, UserAuditRepository userAuditRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userAuditRepository = userAuditRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String username, String password, String phone, String realName, String idCardNumber) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("用户名已存在");
        }

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .phone(phone)
                .balance(new BigDecimal("0.00"))
                .status(UserStatus.PENDING_AUDIT)
                .build();
        user = userRepository.save(user);

        UserAudit audit = new UserAudit();
        audit.setUserId(user.getId());
        audit.setRealName(realName);
        audit.setIdCardNumber(idCardNumber);
        audit.setSubmitTime(java.time.LocalDateTime.now());
        userAuditRepository.save(audit);

        return user;
    }

    public Optional<User> authenticate(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            return userOpt;
        }
        return Optional.empty();
    }

    public boolean setPayPassword(Long userId, String newPassword, String oldPassword, String requesterUsername) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) return false;
        User user = userOpt.get();
        if (!user.getUsername().equals(requesterUsername)) return false;
        if (oldPassword != null && user.getPayPassword() != null) {
            if (!passwordEncoder.matches(oldPassword, user.getPayPassword())) return false;
        }
        user.setPayPassword(passwordEncoder.encode(newPassword));
        user.setPayPwdFailedAttempts(0);
        user.setPayPwdLockedUntil(null);
        userRepository.save(user);
        return true;
    }
}
