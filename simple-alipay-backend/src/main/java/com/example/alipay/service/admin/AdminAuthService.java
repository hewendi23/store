package com.example.alipay.service.admin;

import com.example.alipay.model.admin.AdminUser;
import com.example.alipay.repository.admin.AdminUserRepository;
import com.example.alipay.service.admin.AdminOperationLogger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminAuthService {
    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminOperationLogger adminOperationLogger;

    public AdminAuthService(AdminUserRepository adminUserRepository, PasswordEncoder passwordEncoder, AdminOperationLogger adminOperationLogger) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminOperationLogger = adminOperationLogger;
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

        AdminUser saved = adminUserRepository.save(admin);
        adminOperationLogger.log("ADMIN_CREATE", "ADMIN", saved.getId(), "create admin", true, null);
        return saved;
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

    public List<AdminUser> getAllAdmins() {
        return adminUserRepository.findAll();
    }

    public boolean deleteAdmin(Long adminId) {
        if (adminUserRepository.existsById(adminId)) {
            adminUserRepository.deleteById(adminId);
            adminOperationLogger.log("ADMIN_DELETE", "ADMIN", adminId, "delete admin", true, null);
            return true;
        }
        adminOperationLogger.log("ADMIN_DELETE", "ADMIN", adminId, "delete admin", false, "admin not found");
        return false;
    }
}
