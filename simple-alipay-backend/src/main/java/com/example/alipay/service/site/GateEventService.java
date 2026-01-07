package com.example.alipay.service.site;

import com.example.alipay.model.admin.DiscountPolicy;
import com.example.alipay.model.TravelRecord;
import com.example.alipay.model.site.Gate;
import com.example.alipay.model.site.GateEvent;
import com.example.alipay.model.site.Station;
import com.example.alipay.model.fintech.User;
import com.example.alipay.repository.admin.DiscountPolicyRepository;
import com.example.alipay.repository.TravelRecordRepository;
import com.example.alipay.repository.site.GateRepository;
import com.example.alipay.repository.site.GateEventRepository;
import com.example.alipay.repository.site.StationRepository;
import com.example.alipay.repository.fintech.UserRepository;
import com.example.alipay.model.fintech.Bill;
import com.example.alipay.repository.fintech.BillRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GateEventService {
    private final GateRepository gateRepository;
    private final GateEventRepository gateEventRepository;
    private final UserRepository userRepository;
    private final BillRepository billRepository;
    private final StationRepository stationRepository;
    private final DiscountPolicyRepository discountPolicyRepository;
    private final TravelRecordRepository travelRecordRepository;

    public GateEventService(
            GateRepository gateRepository,
            GateEventRepository gateEventRepository,
            UserRepository userRepository,
            BillRepository billRepository,
            StationRepository stationRepository,
            DiscountPolicyRepository discountPolicyRepository,
            TravelRecordRepository travelRecordRepository
    ) {
        this.gateRepository = gateRepository;
        this.gateEventRepository = gateEventRepository;
        this.userRepository = userRepository;
        this.billRepository = billRepository;
        this.stationRepository = stationRepository;
        this.discountPolicyRepository = discountPolicyRepository;
        this.travelRecordRepository = travelRecordRepository;
    }

    public GateEvent processEntry(String gateCode, String qrCode, Long stationId) {
        Optional<Gate> gateOpt = resolveGateByCode(gateCode, stationId);
        if (gateOpt.isEmpty()) {
            return createErrorEvent(gateCode, qrCode, "GATE_NOT_FOUND", "闸机不存在");
        }

        Gate gate = gateOpt.get();
        if (!gate.isEnabled() || !"ONLINE".equals(gate.getStatus())) {
            return createErrorEvent(gateCode, qrCode, "GATE_OFFLINE", "闸机离线");
        }

        if (!"ENTRY".equals(gate.getDirection())) {
            return createErrorEvent(gateCode, qrCode, "INVALID_DIRECTION", "此闸机不是进站口");
        }

        // 解析二维码获取用户信息
        Optional<User> userOpt = parseUserFromQRCode(qrCode);
        if (userOpt.isEmpty()) {
            return createErrorEvent(gateCode, qrCode, "INVALID_QRCODE", "无效的二维码");
        }

        User user = userOpt.get();
        
        // 检查用户状态
        if (!user.isEnabled()) {
            return createErrorEvent(gateCode, qrCode, "USER_DISABLED", "用户账户已禁用");
        }

        // 检查余额
        if (user.getBalance().compareTo(new BigDecimal("2.00")) < 0) {
            return createErrorEvent(gateCode, qrCode, "INSUFFICIENT_BALANCE", "余额不足");
        }

        // 创建进站事件
        GateEvent event = new GateEvent();
        event.setGateId(gate.getId());
        event.setGateCode(gateCode);
        event.setUserId(user.getId());
        event.setUsername(user.getUsername());
        event.setEventType("ENTRY");
        event.setQrCode(qrCode);
        event.setFare(BigDecimal.ZERO); // 进站不扣费
        event.setStatus("SUCCESS");
        event.setEventTime(LocalDateTime.now());
        event.setProcessedTime(LocalDateTime.now());
        event.setTransactionId(generateTransactionId());
        event.setRemark("进站成功");

        GateEvent saved = gateEventRepository.save(event);

        Station entryStation = resolveStation(gate.getStationId()).orElse(null);
        upsertEntryRecord(user.getUsername(), entryStation);

        return saved;
    }

    public GateEvent processExit(String gateCode, String qrCode, Long stationId) {
        Optional<Gate> gateOpt = resolveGateByCode(gateCode, stationId);
        if (gateOpt.isEmpty()) {
            return createErrorEvent(gateCode, qrCode, "GATE_NOT_FOUND", "闸机不存在");
        }

        Gate gate = gateOpt.get();
        if (!gate.isEnabled() || !"ONLINE".equals(gate.getStatus())) {
            return createErrorEvent(gateCode, qrCode, "GATE_OFFLINE", "闸机离线");
        }

        if (!"EXIT".equals(gate.getDirection())) {
            return createErrorEvent(gateCode, qrCode, "INVALID_DIRECTION", "此闸机不是出站口");
        }

        // 解析二维码获取用户信息
        Optional<User> userOpt = parseUserFromQRCode(qrCode);
        if (userOpt.isEmpty()) {
            return createErrorEvent(gateCode, qrCode, "INVALID_QRCODE", "无效的二维码");
        }

        User user = userOpt.get();
        
        // 检查用户状态
        if (!user.isEnabled()) {
            return createErrorEvent(gateCode, qrCode, "USER_DISABLED", "用户账户已禁用");
        }

        Optional<GateEvent> latestEntryOpt = gateEventRepository.findTopByUserIdAndEventTypeAndStatusOrderByEventTimeDesc(
                user.getId(),
                "ENTRY",
                "SUCCESS"
        );
        if (latestEntryOpt.isEmpty()) {
            return createErrorEvent(gateCode, qrCode, "NO_ENTRY_RECORD", "未找到进站记录");
        }

        Optional<GateEvent> latestExitOpt = gateEventRepository.findTopByUserIdAndEventTypeAndStatusOrderByEventTimeDesc(
                user.getId(),
                "EXIT",
                "SUCCESS"
        );
        if (latestExitOpt.isPresent() && latestExitOpt.get().getEventTime() != null && latestEntryOpt.get().getEventTime() != null) {
            if (latestExitOpt.get().getEventTime().isAfter(latestEntryOpt.get().getEventTime())) {
                return createErrorEvent(gateCode, qrCode, "NO_ENTRY_RECORD", "未找到未出站的进站记录");
            }
        }

        GateEvent latestEntry = latestEntryOpt.get();
        Optional<Gate> entryGateOpt = latestEntry.getGateId() == null ? Optional.empty() : gateRepository.findById(latestEntry.getGateId());
        if (entryGateOpt.isEmpty()) {
            return createErrorEvent(gateCode, qrCode, "ENTRY_GATE_NOT_FOUND", "进站闸机不存在");
        }

        Gate entryGate = entryGateOpt.get();
        Station entryStation = resolveStation(entryGate.getStationId()).orElse(null);
        if (entryStation == null) {
            return createErrorEvent(gateCode, qrCode, "ENTRY_STATION_NOT_FOUND", "进站站点不存在");
        }
        Station exitStation = resolveStation(gate.getStationId()).orElse(null);
        if (exitStation == null) {
            return createErrorEvent(gateCode, qrCode, "EXIT_STATION_NOT_FOUND", "出站站点不存在");
        }

        if (entryStation.getLine() == null || exitStation.getLine() == null || !entryStation.getLine().equals(exitStation.getLine())) {
            return createErrorEvent(gateCode, qrCode, "CROSS_LINE_NOT_SUPPORTED", "暂不支持跨线路计费");
        }

        Integer entrySeq = parseStationSequence(entryStation.getLocation());
        Integer exitSeq = parseStationSequence(exitStation.getLocation());
        if (entrySeq == null || exitSeq == null) {
            return createErrorEvent(gateCode, qrCode, "INVALID_STATION_SEQUENCE", "站点位置字段必须为数字顺序");
        }

        int stationCount = Math.abs(exitSeq - entrySeq);
        BigDecimal perStationFare = new BigDecimal("3.00");
        BigDecimal fare = perStationFare.multiply(new BigDecimal(stationCount));

        Optional<DiscountPolicy> bestPolicyOpt = findBestPolicy(entryStation.getLine(), LocalDateTime.now());
        if (bestPolicyOpt.isPresent()) {
            BigDecimal rate = bestPolicyOpt.get().getDiscountRate();
            if (rate != null) {
                fare = fare.multiply(rate).setScale(2, RoundingMode.HALF_UP);
            }
        } else {
            fare = fare.setScale(2, RoundingMode.HALF_UP);
        }
        
        // 检查余额
        if (user.getBalance().compareTo(fare) < 0) {
            return createErrorEvent(gateCode, qrCode, "INSUFFICIENT_BALANCE", "余额不足");
        }

        // 扣费
        user.setBalance(user.getBalance().subtract(fare));
        userRepository.save(user);

        GateEvent event = new GateEvent();
        event.setGateId(gate.getId());
        event.setGateCode(gateCode);
        event.setUserId(user.getId());
        event.setUsername(user.getUsername());
        event.setEventType("EXIT");
        event.setQrCode(qrCode);
        event.setFare(fare);
        event.setStatus("SUCCESS");
        event.setEventTime(LocalDateTime.now());
        event.setProcessedTime(LocalDateTime.now());
        event.setTransactionId(generateTransactionId());
        event.setRemark("出站成功，扣费" + fare + "元");
        event = gateEventRepository.save(event);

        String ref = event.getTransactionId();
        if (!billRepository.existsByUserIdAndSourceTypeAndSourceRefId(user.getId(), "GATE", ref)) {
            Bill b = new Bill();
            b.setUserId(user.getId());
            b.setAmount(fare);
            b.setType("EXPENDITURE");
            b.setCategory("交通");
            b.setTransactionTime(LocalDateTime.now());
            b.setRemark("出站扣费");
            b.setSourceType("GATE");
            b.setSourceRefId(ref);
            billRepository.save(b);
        }

        upsertExitRecord(user.getUsername(), entryStation, fare);

        return event;
    }

    private Optional<Gate> resolveGateByCode(String gateCode, Long stationId) {
        if (gateCode == null || gateCode.isBlank()) return Optional.empty();

        if (stationId != null) {
            Optional<Gate> stationEnabled = gateRepository.findTopByStationIdAndGateCodeAndEnabledTrueOrderByUpdatedAtDesc(stationId, gateCode);
            if (stationEnabled.isPresent()) return stationEnabled;
            Optional<Gate> stationAny = gateRepository.findTopByStationIdAndGateCodeOrderByUpdatedAtDesc(stationId, gateCode);
            if (stationAny.isPresent()) return stationAny;
        }

        Optional<Gate> enabled = gateRepository.findTopByGateCodeAndEnabledTrueOrderByUpdatedAtDesc(gateCode);
        if (enabled.isPresent()) return enabled;

        return gateRepository.findTopByGateCodeOrderByUpdatedAtDesc(gateCode);
    }

    private Optional<Station> resolveStation(Long stationId) {
        if (stationId == null) return Optional.empty();
        return stationRepository.findById(stationId);
    }

    private void upsertEntryRecord(String username, Station entryStation) {
        if (username == null || username.isBlank()) return;
        Optional<TravelRecord> existing = travelRecordRepository.findTopByUsernameAndStatusOrderByEntryTimeDesc(username, "IN_PROGRESS");
        if (existing.isPresent()) return;

        TravelRecord r = new TravelRecord();
        r.setUsername(username);
        if (entryStation != null) {
            r.setCity(entryStation.getCity());
            r.setLine(entryStation.getLine());
        }
        r.setEntryTime(LocalDateTime.now());
        r.setStatus("IN_PROGRESS");
        travelRecordRepository.save(r);
    }

    private void upsertExitRecord(String username, Station entryStation, BigDecimal fare) {
        if (username == null || username.isBlank()) return;
        Optional<TravelRecord> inProgress = travelRecordRepository.findTopByUsernameAndStatusOrderByEntryTimeDesc(username, "IN_PROGRESS");
        TravelRecord r = inProgress.orElseGet(TravelRecord::new);

        if (r.getUsername() == null) r.setUsername(username);
        if (r.getEntryTime() == null) r.setEntryTime(LocalDateTime.now());
        if (r.getCity() == null && entryStation != null) r.setCity(entryStation.getCity());
        if (r.getLine() == null && entryStation != null) r.setLine(entryStation.getLine());

        r.setExitTime(LocalDateTime.now());
        r.setFare(fare);
        r.setStatus("COMPLETED");
        travelRecordRepository.save(r);
    }

    private Integer parseStationSequence(String location) {
        if (location == null) return null;
        String s = location.trim();
        if (s.isEmpty()) return null;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Optional<DiscountPolicy> findBestPolicy(String line, LocalDateTime now) {
        List<DiscountPolicy> candidates = discountPolicyRepository.findByEnabledTrue();
        DiscountPolicy best = null;
        for (DiscountPolicy p : candidates) {
            if (p == null) continue;
            if (p.getDiscountRate() == null) continue;
            if (!isPolicyInTimeWindow(p, now)) continue;
            if (!isPolicyApplicableToLine(p, line)) continue;
            if (best == null || p.getDiscountRate().compareTo(best.getDiscountRate()) < 0) {
                best = p;
            }
        }
        return Optional.ofNullable(best);
    }

    private boolean isPolicyInTimeWindow(DiscountPolicy p, LocalDateTime now) {
        if (now == null) return true;
        if (p.getStartTime() != null && now.isBefore(p.getStartTime())) return false;
        if (p.getEndTime() != null && now.isAfter(p.getEndTime())) return false;
        return true;
    }

    private boolean isPolicyApplicableToLine(DiscountPolicy p, String line) {
        if (line == null || line.isBlank()) return true;
        String lines = p.getApplicableLines();
        if (lines == null || lines.isBlank()) return true;
        return lines.contains(line);
    }

    private GateEvent createErrorEvent(String gateCode, String qrCode, String errorCode, String errorMessage) {
        GateEvent event = new GateEvent();
        event.setGateCode(gateCode);
        event.setQrCode(qrCode);
        event.setEventType("ERROR");
        event.setStatus("FAILED");
        event.setErrorCode(errorCode);
        event.setErrorMessage(errorMessage);
        event.setEventTime(LocalDateTime.now());
        event.setProcessedTime(LocalDateTime.now());
        event.setTransactionId(generateTransactionId());
        event.setRemark("处理失败: " + errorMessage);
        
        return gateEventRepository.save(event);
    }

    private Optional<User> parseUserFromQRCode(String qrCode) {
        // 简化逻辑：假设二维码包含用户ID
        try {
            Long userId = Long.parseLong(qrCode);
            return userRepository.findById(userId);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private String generateTransactionId() {
        return "TXN_" + UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }

    public List<GateEvent> getGateEvents(Long gateId) {
        return gateEventRepository.findByGateIdOrderByEventTimeDesc(gateId);
    }

    public List<GateEvent> getUserEvents(Long userId) {
        return gateEventRepository.findByUserIdOrderByEventTimeDesc(userId);
    }

    public List<GateEvent> getErrorEvents() {
        return gateEventRepository.findByStatusOrderByEventTimeDesc("FAILED");
    }
}
