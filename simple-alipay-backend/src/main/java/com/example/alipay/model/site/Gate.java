package com.example.alipay.model.site;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "gates")
@Data
public class Gate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long stationId; // 所属站点ID
    private String gateCode; // 闸机编码
    private String gateName; // 闸机名称
    private String direction; // 方向: ENTRY/EXIT
    
    private String location; // 具体位置
    private String description; // 闸机描述
    
    private boolean enabled = true; // 是否启用
    private String status; // 状态: ONLINE/OFFLINE/MAINTENANCE
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
