package com.example.alipay.service.site;

import com.example.alipay.model.site.Gate;
import com.example.alipay.model.site.GateEvent;
import com.example.alipay.model.fintech.User;
import com.example.alipay.repository.site.GateRepository;
import com.example.alipay.repository.site.GateEventRepository;
import com.example.alipay.repository.fintech.UserRepository;
import com.example.alipay.model.fintech.Bill;
import com.example.alipay.repository.fintech.BillRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public GateEventService(GateRepository gateRepository, GateEventRepository gateEventRepository, UserRepository userRepository, BillRepository billRepository) {
        this.gateRepository = gateRepository;
        this.gateEventRepository = gateEventRepository;
        this.userRepository = userRepository;
        this.billRepository = billRepository;
    }

    public GateEvent processEntry(String gateCode, String qrCode) {
        Optional<Gate> gateOpt = gateRepository.findByGateCode(gateCode);
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

        return gateEventRepository.save(event);
    }

    public GateEvent processExit(String gateCode, String qrCode) {
        Optional<Gate> gateOpt = gateRepository.findByGateCode(gateCode);
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

        // 计算费用 (简化逻辑，固定费用)
        BigDecimal fare = new BigDecimal("3.00");
        
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

        return event;
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
