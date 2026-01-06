package com.example.alipay.service.site;

import com.example.alipay.model.site.Station;
import com.example.alipay.model.site.Gate;
import com.example.alipay.repository.site.StationRepository;
import com.example.alipay.repository.site.GateRepository;
import com.example.alipay.repository.site.GateEventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StationManagementService {
    private final StationRepository stationRepository;
    private final GateRepository gateRepository;
    private final GateEventRepository gateEventRepository;

    public StationManagementService(StationRepository stationRepository, GateRepository gateRepository, GateEventRepository gateEventRepository) {
        this.stationRepository = stationRepository;
        this.gateRepository = gateRepository;
        this.gateEventRepository = gateEventRepository;
    }

    // 站点管理方法
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public List<Station> getActiveStations() {
        return stationRepository.findByEnabledTrue();
    }

    public Optional<Station> getStationById(Long stationId) {
        return stationRepository.findById(stationId);
    }

    public Optional<Station> getStationByCode(String stationCode) {
        return stationRepository.findByStationCode(stationCode);
    }

    public Station createStation(Station station) {
        station.setCreatedAt(LocalDateTime.now());
        station.setUpdatedAt(LocalDateTime.now());
        return stationRepository.save(station);
    }

    public Station updateStation(Long stationId, Station stationDetails) {
        Optional<Station> stationOpt = stationRepository.findById(stationId);
        if (stationOpt.isPresent()) {
            Station station = stationOpt.get();
            station.setStationName(stationDetails.getStationName());
            station.setCity(stationDetails.getCity());
            station.setLine(stationDetails.getLine());
            station.setLocation(stationDetails.getLocation());
            station.setDescription(stationDetails.getDescription());
            station.setEnabled(stationDetails.isEnabled());
            station.setUpdatedAt(LocalDateTime.now());
            return stationRepository.save(station);
        }
        return null;
    }

    public boolean enableStation(Long stationId) {
        Optional<Station> stationOpt = stationRepository.findById(stationId);
        if (stationOpt.isPresent()) {
            Station station = stationOpt.get();
            station.setEnabled(true);
            station.setUpdatedAt(LocalDateTime.now());
            stationRepository.save(station);
            return true;
        }
        return false;
    }

    public boolean disableStation(Long stationId) {
        Optional<Station> stationOpt = stationRepository.findById(stationId);
        if (stationOpt.isPresent()) {
            Station station = stationOpt.get();
            station.setEnabled(false);
            station.setUpdatedAt(LocalDateTime.now());
            stationRepository.save(station);
            return true;
        }
        return false;
    }

    // 闸机管理方法
    public List<Gate> getGatesByStation(Long stationId) {
        return gateRepository.findByStationId(stationId);
    }

    public List<Gate> getGatesByDirection(String direction) {
        return gateRepository.findByDirection(direction);
    }

    public Gate createGate(Gate gate) {
        gate.setCreatedAt(LocalDateTime.now());
        gate.setUpdatedAt(LocalDateTime.now());
        return gateRepository.save(gate);
    }

    public Gate updateGate(Long gateId, Gate gateDetails) {
        Optional<Gate> gateOpt = gateRepository.findById(gateId);
        if (gateOpt.isPresent()) {
            Gate gate = gateOpt.get();
            gate.setGateName(gateDetails.getGateName());
            gate.setDirection(gateDetails.getDirection());
            gate.setLocation(gateDetails.getLocation());
            gate.setDescription(gateDetails.getDescription());
            gate.setEnabled(gateDetails.isEnabled());
            gate.setStatus(gateDetails.getStatus());
            gate.setUpdatedAt(LocalDateTime.now());
            return gateRepository.save(gate);
        }
        return null;
    }

    public boolean enableGate(Long gateId) {
        Optional<Gate> gateOpt = gateRepository.findById(gateId);
        if (gateOpt.isPresent()) {
            Gate gate = gateOpt.get();
            gate.setEnabled(true);
            gate.setStatus("ONLINE");
            gate.setUpdatedAt(LocalDateTime.now());
            gateRepository.save(gate);
            return true;
        }
        return false;
    }

    public boolean disableGate(Long gateId) {
        Optional<Gate> gateOpt = gateRepository.findById(gateId);
        if (gateOpt.isPresent()) {
            Gate gate = gateOpt.get();
            gate.setEnabled(false);
            gate.setStatus("OFFLINE");
            gate.setUpdatedAt(LocalDateTime.now());
            gateRepository.save(gate);
            return true;
        }
        return false;
    }

    public boolean deleteGate(Long gateId) {
        Optional<Gate> gateOpt = gateRepository.findById(gateId);
        if (gateOpt.isEmpty()) {
            return false;
        }
        long related = gateEventRepository.countByGateId(gateId);
        if (related > 0) {
            throw new IllegalStateException("闸机存在关联事件，无法删除");
        }
        gateRepository.deleteById(gateId);
        return true;
    }

    public List<Station> getStationsByCity(String city) {
        return stationRepository.findByCity(city);
    }

    public List<Station> getStationsByLine(String line) {
        return stationRepository.findByLine(line);
    }
}
