package com.example.alipay.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CollectionQRCode {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String merchantId;
    private String content; // encoded content
    private LocalDateTime expireAt;
    private boolean active = true;

    // getters/setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getMerchantId(){return merchantId;}
    public void setMerchantId(String m){this.merchantId = m;}
    public String getContent(){return content;}
    public void setContent(String c){this.content = c;}
    public LocalDateTime getExpireAt(){return expireAt;}
    public void setExpireAt(LocalDateTime t){this.expireAt = t;}
    public boolean isActive(){return active;}
    public void setActive(boolean a){this.active = a;}
}
