package com.example.alipay.controller;

import com.example.alipay.model.AssistantMessage;
import com.example.alipay.service.SmartAssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assistant")
public class SmartAssistantController {

    @Autowired
    private SmartAssistantService assistantService;

    @GetMapping("/welcome")
    public String welcome() {
        return assistantService.getWelcomeMessage();
    }

    @PostMapping("/chat")
    public AssistantMessage chat(@RequestParam String username, @RequestParam String content) {
        return assistantService.processChat(username, content);
    }

    @GetMapping("/analysis/monthly")
    public Map<String, Object> getMonthlyAnalysis(@RequestParam String username) {
        return assistantService.analyzeMonthly(username);
    }

    @GetMapping("/analysis/alerts")
    public List<?> getAbnormalRecords(@RequestParam String username) {
        return assistantService.getLargePayments(username);
    }
}
