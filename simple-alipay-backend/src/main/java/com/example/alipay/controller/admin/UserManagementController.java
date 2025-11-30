package com.example.alipay.controller.admin;

import com.example.alipay.model.fintech.User;
import com.example.alipay.model.fintech.UserAudit;
import com.example.alipay.service.admin.UserManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/users")
public class UserManagementController {
    private final UserManagementService userManagementService;

    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userManagementService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return userManagementService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}/disable")
    public ResponseEntity<?> disableUser(@PathVariable Long userId) {
        boolean success = userManagementService.disableUser(userId);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "用户已禁用"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "用户不存在"));
        }
    }

    @PostMapping("/{userId}/enable")
    public ResponseEntity<?> enableUser(@PathVariable Long userId) {
        boolean success = userManagementService.enableUser(userId);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "用户已启用"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "用户不存在"));
        }
    }

    @GetMapping("/audits/pending")
    public ResponseEntity<?> getPendingAudits() {
        List<UserAudit> audits = userManagementService.getPendingAudits();
        return ResponseEntity.ok(audits);
    }

    @PostMapping("/audits/{userId}/approve")
    public ResponseEntity<?> approveUserAudit(@PathVariable Long userId) {
        boolean success = userManagementService.approveUserAudit(userId);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "用户审核已通过"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "审核记录不存在"));
        }
    }

    @PostMapping("/audits/{userId}/reject")
    public ResponseEntity<?> rejectUserAudit(@PathVariable Long userId, @RequestBody Map<String, String> request) {
        String rejectReason = request.get("rejectReason");
        if (rejectReason == null || rejectReason.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "驳回原因不能为空"));
        }

        boolean success = userManagementService.rejectUserAudit(userId, rejectReason);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "用户审核已驳回"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "审核记录不存在"));
        }
    }
}
