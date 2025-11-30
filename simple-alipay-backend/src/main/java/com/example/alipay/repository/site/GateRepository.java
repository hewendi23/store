package com.example.alipay.repository.site;

import com.example.alipay.model.site.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface GateRepository extends JpaRepository<Gate, Long> {
    Optional<Gate> findByGateCode(String gateCode);
    List<Gate> findByStationId(Long stationId);
    List<Gate> findByDirection(String direction);
    List<Gate> findByEnabledTrue();
    List<Gate> findByStationIdAndDirection(Long stationId, String direction);
}
