package com.example.alipay.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TravelPass {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String city;
    private String line;
    private String boundPaymentMethod;
    private boolean enabled = true;
    private LocalDateTime updatedAt;

    // getters/setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getUsername(){return username;}
    public void setUsername(String u){this.username = u;}
    public String getCity(){return city;}
    public void setCity(String c){this.city = c;}
    public String getLine(){return line;}
    public void setLine(String l){this.line = l;}
    public String getBoundPaymentMethod(){return boundPaymentMethod;}
    public void setBoundPaymentMethod(String b){this.boundPaymentMethod = b;}
    public boolean isEnabled(){return enabled;}
    public void setEnabled(boolean e){this.enabled = e;}
    public java.time.LocalDateTime getUpdatedAt(){return updatedAt;}
    public void setUpdatedAt(java.time.LocalDateTime t){this.updatedAt = t;}
}
