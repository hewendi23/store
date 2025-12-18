package com.example.alipay.controller.admin;

import com.example.alipay.model.admin.AdminUser;
import com.example.alipay.model.admin.CreateAdminRequest;
import com.example.alipay.security.fintech.JwtUtil;
import com.example.alipay.service.admin.AdminAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/auth")
public class AdminAuthController {
    private final AdminAuthService adminAuthService;
    private final JwtUtil jwtUtil;

    public AdminAuthController(AdminAuthService adminAuthService, JwtUtil jwtUtil) {
        this.adminAuthService = adminAuthService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "用户名和密码不能为空"));
        }

        Optional<AdminUser> adminOpt = adminAuthService.authenticate(username, password);
        if (adminOpt.isPresent()) {
            AdminUser admin = adminOpt.get();
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(Map.of(
                "message", "管理员登录成功",
                "token", token,
                "adminId", admin.getId(),
                "username", admin.getUsername(),
                "role", admin.getRole()
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "用户名或密码错误"));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody CreateAdminRequest request) {
        try {
            String username = request.getUsername();
            String password = request.getPassword();
            String role = request.getRole();

            if (username == null || password == null || role == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "用户名、密码和角色不能为空"));
            }

            AdminUser admin = adminAuthService.createAdmin(username, password, role);
            return ResponseEntity.ok(Map.of(
                "message", "管理员创建成功",
                "adminId", admin.getId(),
                "username", admin.getUsername(),
                "role", admin.getRole()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/admins")
    public ResponseEntity<?> listAdmins() {
        List<AdminUser> admins = adminAuthService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @DeleteMapping("/admins/{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId) {
        boolean success = adminAuthService.deleteAdmin(adminId);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "管理员已删除", "adminId", adminId));
        }
        return ResponseEntity.badRequest().body(Map.of("error", "管理员不存在", "adminId", adminId));
    }
}
