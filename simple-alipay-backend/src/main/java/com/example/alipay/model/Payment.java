package com.example.alipay.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromUser;
    private String toMerchant;
    private BigDecimal amount;
    private String paymentMethod;
    private String status;
    private LocalDateTime createdAt;
    private String errorCode;
    private String errorMessage;

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getFromUser(){return fromUser;}
    public void setFromUser(String u){this.fromUser = u;}
    public String getToMerchant(){return toMerchant;}
    public void setToMerchant(String m){this.toMerchant = m;}
    public BigDecimal getAmount(){return amount;}
    public void setAmount(BigDecimal a){this.amount = a;}
    public String getPaymentMethod(){return paymentMethod;}
    public void setPaymentMethod(String pm){this.paymentMethod = pm;}
    public String getStatus(){return status;}
    public void setStatus(String s){this.status = s;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public void setCreatedAt(LocalDateTime t){this.createdAt = t;}
    public String getErrorCode(){return errorCode;}
    public void setErrorCode(String c){this.errorCode = c;}
    public String getErrorMessage(){return errorMessage;}
    public void setErrorMessage(String m){this.errorMessage = m;}
}
