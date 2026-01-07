package com.example.alipay.model.site;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "gate_events")
@Data
public class GateEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long gateId; // 闸机ID
    private String gateCode; // 闸机编码
    private Long userId; // 用户ID
    private String username; // 用户名
    
    private String eventType; // 事件类型: ENTRY/EXIT/ERROR
    private String qrCode; // 扫描的二维码内容
    
    @Column(precision = 8, scale = 2)
    private BigDecimal fare; // 费用
    
    private String status; // 状态: SUCCESS/FAILED/PENDING
    private String errorCode; // 错误代码
    private String errorMessage; // 错误信息
    
    private LocalDateTime eventTime; // 事件时间
    private LocalDateTime processedTime; // 处理时间
    
    private String transactionId; // 交易ID
    private String remark; // 备注
}
