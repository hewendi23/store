package com.example.alipay.repository.site;

import com.example.alipay.model.site.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface GateRepository extends JpaRepository<Gate, Long> {
    List<Gate> findByGateCode(String gateCode);
    Optional<Gate> findTopByGateCodeOrderByUpdatedAtDesc(String gateCode);
    Optional<Gate> findTopByGateCodeAndEnabledTrueOrderByUpdatedAtDesc(String gateCode);
    Optional<Gate> findTopByStationIdAndGateCodeOrderByUpdatedAtDesc(Long stationId, String gateCode);
    Optional<Gate> findTopByStationIdAndGateCodeAndEnabledTrueOrderByUpdatedAtDesc(Long stationId, String gateCode);
    List<Gate> findByGateCodeAndDirection(String gateCode, String direction);
    List<Gate> findByStationId(Long stationId);
    List<Gate> findByDirection(String direction);
    List<Gate> findByEnabledTrue();
    List<Gate> findByStationIdAndDirection(Long stationId, String direction);
}
