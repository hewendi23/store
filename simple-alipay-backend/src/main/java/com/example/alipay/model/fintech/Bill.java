package com.example.alipay.model.fintech;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    @Column(precision = 19, scale = 2)
    private BigDecimal amount;

    private String type;

    private String category;
    private LocalDateTime transactionTime;
    private String remark;

    private String sourceType;
    private String sourceRefId;
}
