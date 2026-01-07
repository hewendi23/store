package com.example.alipay.model.site;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "stations")
@Data
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stationCode; // 站点编码
    private String stationName; // 站点名称
    private String city; // 所在城市
    private String line; // 所属线路
    
    private String location; // 地理位置
    private String description; // 站点描述
    
    private boolean enabled = true; // 是否启用
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
