package com.example.alipay.controller.site;

import com.example.alipay.model.site.Station;
import com.example.alipay.model.site.Gate;
import com.example.alipay.service.site.StationManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/site")
public class StationManagementController {
    private final StationManagementService stationManagementService;

    public StationManagementController(StationManagementService stationManagementService) {
        this.stationManagementService = stationManagementService;
    }

    // 站点管理API
    @GetMapping("/stations")
    public ResponseEntity<?> getAllStations() {
        List<Station> stations = stationManagementService.getAllStations();
        return ResponseEntity.ok(stations);
    }

    @GetMapping("/stations/active")
    public ResponseEntity<?> getActiveStations() {
        List<Station> stations = stationManagementService.getActiveStations();
        return ResponseEntity.ok(stations);
    }

    @GetMapping("/stations/{stationId}")
    public ResponseEntity<?> getStationById(@PathVariable Long stationId) {
        return stationManagementService.getStationById(stationId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/stations")
    public ResponseEntity<?> createStation(@RequestBody Station station) {
        try {
            Station createdStation = stationManagementService.createStation(station);
            return ResponseEntity.ok(Map.of(
                "message", "站点创建成功",
                "stationId", createdStation.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/stations/{stationId}")
    public ResponseEntity<?> updateStation(@PathVariable Long stationId, @RequestBody Station stationDetails) {
        Station updatedStation = stationManagementService.updateStation(stationId, stationDetails);
        if (updatedStation != null) {
            return ResponseEntity.ok(Map.of(
                "message", "站点更新成功",
                "stationId", updatedStation.getId()
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "站点不存在"));
        }
    }

    @PostMapping("/stations/{stationId}/enable")
    public ResponseEntity<?> enableStation(@PathVariable Long stationId) {
        boolean success = stationManagementService.enableStation(stationId);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "站点已启用"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "站点不存在"));
        }
    }

    @PostMapping("/stations/{stationId}/disable")
    public ResponseEntity<?> disableStation(@PathVariable Long stationId) {
        boolean success = stationManagementService.disableStation(stationId);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "站点已禁用"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "站点不存在"));
        }
    }

    // 闸机管理API
    @GetMapping("/stations/{stationId}/gates")
    public ResponseEntity<?> getGatesByStation(@PathVariable Long stationId) {
        List<Gate> gates = stationManagementService.getGatesByStation(stationId);
        return ResponseEntity.ok(gates);
    }

    @GetMapping("/gates/direction/{direction}")
    public ResponseEntity<?> getGatesByDirection(@PathVariable String direction) {
        List<Gate> gates = stationManagementService.getGatesByDirection(direction);
        return ResponseEntity.ok(gates);
    }

    @PostMapping("/gates")
    public ResponseEntity<?> createGate(@RequestBody Gate gate) {
        try {
            Gate createdGate = stationManagementService.createGate(gate);
            return ResponseEntity.ok(Map.of(
                "message", "闸机创建成功",
                "gateId", createdGate.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/gates/{gateId}")
    public ResponseEntity<?> updateGate(@PathVariable Long gateId, @RequestBody Gate gateDetails) {
        Gate updatedGate = stationManagementService.updateGate(gateId, gateDetails);
        if (updatedGate != null) {
            return ResponseEntity.ok(Map.of(
                "message", "闸机更新成功",
                "gateId", updatedGate.getId()
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "闸机不存在"));
        }
    }

    @PostMapping("/gates/{gateId}/enable")
    public ResponseEntity<?> enableGate(@PathVariable Long gateId) {
        boolean success = stationManagementService.enableGate(gateId);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "闸机已启用"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "闸机不存在"));
        }
    }

    @PostMapping("/gates/{gateId}/disable")
    public ResponseEntity<?> disableGate(@PathVariable Long gateId) {
        boolean success = stationManagementService.disableGate(gateId);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "闸机已禁用"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "闸机不存在"));
        }
    }

    @DeleteMapping("/gates/{gateId}")
    public ResponseEntity<?> deleteGate(@PathVariable Long gateId) {
        try {
            boolean success = stationManagementService.deleteGate(gateId);
            if (success) {
                return ResponseEntity.ok(Map.of("message", "闸机已删除", "gateId", gateId));
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "闸机不存在"));
            }
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(Map.of("error", e.getMessage()));
        }
    }

    // 查询API
    @GetMapping("/stations/city/{city}")
    public ResponseEntity<?> getStationsByCity(@PathVariable String city) {
        List<Station> stations = stationManagementService.getStationsByCity(city);
        return ResponseEntity.ok(stations);
    }

    @GetMapping("/stations/line/{line}")
    public ResponseEntity<?> getStationsByLine(@PathVariable String line) {
        List<Station> stations = stationManagementService.getStationsByLine(line);
        return ResponseEntity.ok(stations);
    }
}
