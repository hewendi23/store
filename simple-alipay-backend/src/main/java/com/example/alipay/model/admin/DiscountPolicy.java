package com.example.alipay.model.admin;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "discount_policies")
@Data
public class DiscountPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyName; // 策略名称
    private String description; // 策略描述
    
    @Column(precision = 5, scale = 2)
    private BigDecimal discountRate; // 折扣率 0.8表示8折
    
    private String applicableUserType; // 适用用户类型
    private String applicableLines; // 适用线路
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;
    
    private boolean enabled = true; // 是否启用
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
