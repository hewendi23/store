package com.example.alipay.model.fintech;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class UserAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String realName;
    private String idCardNumber;
    private String rejectReason;
    private LocalDateTime submitTime;
}
