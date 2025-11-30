package com.example.alipay.service.admin;

import com.example.alipay.model.admin.AdminUser;
import com.example.alipay.repository.admin.AdminUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminAuthService {
    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminAuthService(AdminUserRepository adminUserRepository, PasswordEncoder passwordEncoder) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AdminUser createAdmin(String username, String password, String role) {
        if (adminUserRepository.existsByUsername(username)) {
            throw new RuntimeException("管理员用户名已存在");
        }

        AdminUser admin = new AdminUser();
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setRole(role);
        admin.setEnabled(true);

        return adminUserRepository.save(admin);
    }

    public Optional<AdminUser> authenticate(String username, String password) {
        Optional<AdminUser> adminOpt = adminUserRepository.findByUsername(username);
        if (adminOpt.isPresent() && passwordEncoder.matches(password, adminOpt.get().getPassword())) {
            return adminOpt;
        }
        return Optional.empty();
    }

    public boolean hasPermission(AdminUser admin, String requiredRole) {
        return admin.getRole().equals(requiredRole) || admin.getRole().equals("SUPER_ADMIN");
    }
}
