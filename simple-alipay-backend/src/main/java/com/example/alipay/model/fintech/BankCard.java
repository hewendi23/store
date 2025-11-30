package com.example.alipay.model.fintech;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String bankName;
    private String cardNumber;
    private Boolean isDefault;
}
