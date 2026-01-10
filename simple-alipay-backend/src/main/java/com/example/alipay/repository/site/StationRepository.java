package com.example.alipay.repository.site;

import com.example.alipay.model.site.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long> {
    Optional<Station> findByStationCode(String stationCode);
    List<Station> findByCity(String city);
    List<Station> findByLine(String line);
    List<Station> findByEnabledTrue();
}
