package com.example.alipay.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String displayName;
    private String defaultPaymentMethod; // simple string like "balance" or card id
    private BigDecimal balance = new BigDecimal("1000.00"); // default balance for demo

    // getters/setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}
    public String getDisplayName(){return displayName;}
    public void setDisplayName(String displayName){this.displayName = displayName;}
    public String getDefaultPaymentMethod(){return defaultPaymentMethod;}
    public void setDefaultPaymentMethod(String m){this.defaultPaymentMethod = m;}
    public BigDecimal getBalance(){return balance;}
    public void setBalance(BigDecimal b){this.balance = b;}
}
