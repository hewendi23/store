package com.example.alipay.controller.admin;

import com.example.alipay.model.admin.AdminOperationLog;
import com.example.alipay.service.admin.AdminOperationLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/logs")
public class AdminLogController {
    private final AdminOperationLogService logService;

    public AdminLogController(AdminOperationLogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<?> getAllLogs() {
        List<AdminOperationLog> logs = logService.getAllLogs();
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<?> getLogsByAdmin(@PathVariable Long adminId) {
        List<AdminOperationLog> logs = logService.getLogsByAdmin(adminId);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/type/{operationType}")
    public ResponseEntity<?> getLogsByType(@PathVariable String operationType) {
        List<AdminOperationLog> logs = logService.getLogsByType(operationType);
        return ResponseEntity.ok(logs);
    }
}
