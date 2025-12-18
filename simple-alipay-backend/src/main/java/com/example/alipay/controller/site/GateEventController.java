package com.example.alipay.controller.site;

import com.example.alipay.model.site.GateEvent;
import com.example.alipay.service.site.GateEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/site/gates")
public class GateEventController {
    private final GateEventService gateEventService;

    public GateEventController(GateEventService gateEventService) {
        this.gateEventService = gateEventService;
    }

    @PostMapping("/{gateCode}/entry")
    public ResponseEntity<?> processEntry(@PathVariable String gateCode, @RequestBody Map<String, String> request) {
        String qrCode = request.get("qrCode");
        if (qrCode == null || qrCode.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "二维码不能为空"));
        }

        GateEvent event = gateEventService.processEntry(gateCode, qrCode);
        
        if ("SUCCESS".equals(event.getStatus())) {
            return ResponseEntity.ok(Map.of(
                "message", "进站成功",
                "eventId", event.getId(),
                "transactionId", event.getTransactionId(),
                "userId", event.getUserId(),
                "username", event.getUsername()
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of(
                "error", event.getErrorMessage(),
                "errorCode", event.getErrorCode(),
                "eventId", event.getId()
            ));
        }
    }

    @PostMapping("/{gateCode}/exit")
    public ResponseEntity<?> processExit(@PathVariable String gateCode, @RequestBody Map<String, String> request) {
        String qrCode = request.get("qrCode");
        if (qrCode == null || qrCode.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "二维码不能为空"));
        }

        GateEvent event = gateEventService.processExit(gateCode, qrCode);
        
        if ("SUCCESS".equals(event.getStatus())) {
            return ResponseEntity.ok(Map.of(
                "message", "出站成功",
                "eventId", event.getId(),
                "transactionId", event.getTransactionId(),
                "userId", event.getUserId(),
                "username", event.getUsername(),
                "fare", event.getFare(),
                "remark", event.getRemark()
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of(
                "error", event.getErrorMessage(),
                "errorCode", event.getErrorCode(),
                "eventId", event.getId()
            ));
        }
    }

    @GetMapping("/events/gate/{gateId}")
    public ResponseEntity<?> getGateEvents(@PathVariable Long gateId) {
        List<GateEvent> events = gateEventService.getGateEvents(gateId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/events/user/{userId}")
    public ResponseEntity<?> getUserEvents(@PathVariable Long userId) {
        List<GateEvent> events = gateEventService.getUserEvents(userId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/events/errors")
    public ResponseEntity<?> getErrorEvents() {
        List<GateEvent> events = gateEventService.getErrorEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<?> getEventById(@PathVariable Long eventId) {
        // 这里需要添加获取单个事件的方法
        return ResponseEntity.ok(Map.of("message", "获取事件详情功能待实现"));
    }
}
