package com.example.alipay.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
public class TravelRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String city;
    private String line;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private BigDecimal fare;
    private String status; // COMPLETED, IN_PROGRESS, EXCEPTION

    // getters/setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getUsername(){return username;}
    public void setUsername(String u){this.username = u;}
    public String getCity(){return city;}
    public void setCity(String c){this.city = c;}
    public String getLine(){return line;}
    public void setLine(String l){this.line = l;}
    public LocalDateTime getEntryTime(){return entryTime;}
    public void setEntryTime(LocalDateTime t){this.entryTime = t;}
    public LocalDateTime getExitTime(){return exitTime;}
    public void setExitTime(LocalDateTime t){this.exitTime = t;}
    public BigDecimal getFare(){return fare;}
    public void setFare(BigDecimal f){this.fare = f;}
    public String getStatus(){return status;}
    public void setStatus(String s){this.status = s;}
}
