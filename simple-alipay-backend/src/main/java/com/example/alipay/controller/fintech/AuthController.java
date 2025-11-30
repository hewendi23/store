package com.example.alipay.controller.fintech;

import com.example.alipay.model.fintech.User;
import com.example.alipay.security.fintech.JwtUtil;
import com.example.alipay.service.fintech.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/fintech/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        try {
            String username = request.get("username");
            String password = request.get("password");
            String phone = request.get("phone");

            if (username == null || password == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "用户名和密码不能为空"));
            }

            User user = authService.register(username, password, phone);
            return ResponseEntity.ok(Map.of(
                "message", "注册成功",
                "userId", user.getId(),
                "username", user.getUsername()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "用户名和密码不能为空"));
        }

        Optional<User> userOpt = authService.authenticate(username, password);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(Map.of(
                "message", "登录成功",
                "token", token,
                "userId", user.getId(),
                "username", user.getUsername(),
                "balance", user.getBalance()
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "用户名或密码错误"));
        }
    }
}
