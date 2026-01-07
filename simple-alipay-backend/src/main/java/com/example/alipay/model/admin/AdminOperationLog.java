package com.example.alipay.model.admin;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "admin_operation_logs")
@Data
public class AdminOperationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long adminId; // 操作管理员ID
    private String adminUsername; // 操作管理员用户名
    
    private String operationType; // 操作类型
    private String targetType; // 目标类型 (USER, POLICY, STATION, etc.)
    private Long targetId; // 目标ID
    
    private String operationDetails; // 操作详情
    private String ipAddress; // 操作IP
    
    private LocalDateTime operationTime;
    
    private boolean success = true; // 操作是否成功
    private String errorMessage; // 错误信息
}
