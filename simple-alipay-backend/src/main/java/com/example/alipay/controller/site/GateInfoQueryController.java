package com.example.alipay.controller.site;

import com.example.alipay.model.site.Gate;
import com.example.alipay.model.site.Station;
import com.example.alipay.repository.site.GateRepository;
import com.example.alipay.repository.site.StationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/site/stations")
public class GateInfoQueryController {
    private final StationRepository stationRepository;
    private final GateRepository gateRepository;

    public GateInfoQueryController(StationRepository stationRepository, GateRepository gateRepository) {
        this.stationRepository = stationRepository;
        this.gateRepository = gateRepository;
    }

    @GetMapping("/gate-info")
    public ResponseEntity<?> getGateInfo(
            @RequestParam String city,
            @RequestParam String line,
            @RequestParam String station
    ) {
        String cityV = city == null ? "" : city.trim();
        String lineV = line == null ? "" : line.trim();
        String stationV = station == null ? "" : station.trim();

        if (cityV.isEmpty() || lineV.isEmpty() || stationV.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "city/line/station 参数不能为空"));
        }

        List<Station> matchedStations = stationRepository.findByCity(cityV).stream()
                .filter(Station::isEnabled)
                .filter(s -> s.getLine() != null && s.getLine().trim().equalsIgnoreCase(lineV))
                .filter(s -> (s.getStationName() != null && s.getStationName().trim().equalsIgnoreCase(stationV))
                        || (s.getStationCode() != null && s.getStationCode().trim().equalsIgnoreCase(stationV)))
                .toList();

        if (matchedStations.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of(
                    "error", "未找到站点",
                    "city", cityV,
                    "line", lineV,
                    "station", stationV
            ));
        }

        List<Map<String, Object>> matches = matchedStations.stream()
                .sorted(Comparator.comparing(Station::getId))
                .map(s -> {
                    List<Map<String, Object>> gates = gateRepository.findByStationId(s.getId()).stream()
                            .filter(Gate::isEnabled)
                            .sorted(Comparator
                                    .comparing((Gate g) -> g.getGateCode() == null ? "" : g.getGateCode())
                                    .thenComparing(g -> g.getDirection() == null ? "" : g.getDirection())
                                    .thenComparing(g -> g.getId() == null ? 0L : g.getId()))
                            .map(g -> Map.<String, Object>of(
                                    "id", g.getId(),
                                    "gateCode", g.getGateCode(),
                                    "gateName", g.getGateName(),
                                    "direction", g.getDirection(),
                                    "status", g.getStatus(),
                                    "location", g.getLocation(),
                                    "description", g.getDescription()
                            ))
                            .toList();

                    return Map.<String, Object>of(
                            "stationId", s.getId(),
                            "stationCode", s.getStationCode(),
                            "stationName", s.getStationName(),
                            "city", s.getCity(),
                            "line", s.getLine(),
                            "gates", gates
                    );
                })
                .toList();

        return ResponseEntity.ok(Map.of(
                "city", cityV,
                "line", lineV,
                "station", stationV,
                "matches", matches
        ));
    }
}
