package com.example.alipay.controller.fintech;

import com.example.alipay.model.fintech.User;
import com.example.alipay.security.fintech.JwtUtil;
import com.example.alipay.repository.fintech.UserAuditRepository;
import com.example.alipay.service.fintech.AuthService;
import com.example.alipay.service.admin.AdminOperationLogger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/fintech/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final UserAuditRepository userAuditRepository;
    private final AdminOperationLogger adminOperationLogger;

    public AuthController(AuthService authService, JwtUtil jwtUtil, UserAuditRepository userAuditRepository, AdminOperationLogger adminOperationLogger) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.userAuditRepository = userAuditRepository;
        this.adminOperationLogger = adminOperationLogger;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        try {
            String username = request.get("username");
            String password = request.get("password");
            String phone = request.get("phone");
            String realName = request.get("realName");
            String idCardNumber = request.get("idCardNumber");

            if (username == null || password == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "用户名和密码不能为空"));
            }

            User user = authService.register(username, password, phone, realName, idCardNumber);
            return ResponseEntity.ok(Map.of(
                "message", "注册成功",
                "userId", user.getId(),
                "username", user.getUsername()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> registerForm(@RequestParam Map<String, String> request) {
        return register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        return loginInternal(request.get("username"), request.get("password"));
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> loginForm(@RequestParam Map<String, String> request) {
        return loginInternal(request.get("username"), request.get("password"));
    }

    private ResponseEntity<?> loginInternal(String username, String password) {
        if (username == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "用户名和密码不能为空"));
        }

        Optional<User> userOpt = authService.authenticate(username, password);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "用户名或密码错误"));
        }

        User user = userOpt.get();
        switch (user.getStatus()) {
            case ACTIVE -> {
                String token = jwtUtil.generateToken(username);
                return ResponseEntity.ok(Map.of(
                    "message", "登录成功",
                    "token", token,
                    "userId", user.getId(),
                    "username", user.getUsername(),
                    "balance", user.getBalance()
                ));
            }
            case PENDING_AUDIT -> {
                return ResponseEntity.status(403).body(Map.of(
                    "error", "账户待审核，审核通过后方可登录"
                ));
            }
            case REJECTED -> {
                String reason = userAuditRepository.findByUserId(user.getId())
                        .map(a -> a.getRejectReason())
                        .orElse("审核已被驳回，请重新提交或联系管理员");
                return ResponseEntity.status(403).body(Map.of(
                    "error", reason
                ));
            }
            case INACTIVE -> {
                return ResponseEntity.status(403).body(Map.of(
                    "error", "账户已禁用，请联系管理员"
                ));
            }
            default -> {
                return ResponseEntity.status(403).body(Map.of("error", "账户状态异常"));
            }
        }
    }

    @PostMapping("/pay-password/set")
    public ResponseEntity<?> setPayPassword(@RequestHeader(value = "Authorization", required = false) String auth,
                                            @RequestBody Map<String, String> request) {
        if (auth == null || !auth.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("error", "unauthorized"));
        }
        String token = auth.substring(7);
        String requester = jwtUtil.extractUsername(token);

        String userIdS = request.get("userId");
        String newPassword = request.get("newPassword");
        String oldPassword = request.get("oldPassword");
        if (userIdS == null || newPassword == null || newPassword.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "invalid_request"));
        }
        Long userId = Long.valueOf(userIdS);
        boolean ok = authService.setPayPassword(userId, newPassword, oldPassword, requester);
        adminOperationLogger.log("USER_SET_PAY_PASSWORD", "USER", userId, "set pay password", ok, ok ? null : "forbidden");
        if (ok) {
            return ResponseEntity.ok(Map.of("success", true, "message", "更新成功"));
        } else {
            return ResponseEntity.status(403).body(Map.of("error", "forbidden"));
        }
    }
}
