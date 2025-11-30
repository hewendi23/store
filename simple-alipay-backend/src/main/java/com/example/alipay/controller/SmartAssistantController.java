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

    // 1. 获取欢迎语
    @GetMapping("/welcome")
    public String welcome() {
        return assistantService.getWelcomeMessage();
    }

    // 2. 发送消息（对话）
    @PostMapping("/chat")
    public AssistantMessage chat(@RequestParam String username, @RequestParam String content) {
        return assistantService.processChat(username, content);
    }

    // 3. 直接获取本月分析数据 (供前端图表使用)
    @GetMapping("/analysis/monthly")
    public Map<String, Object> getMonthlyAnalysis(@RequestParam String username) {
        return assistantService.analyzeMonthly(username);
    }

    // 4. 获取异常/大额支出列表
    @GetMapping("/analysis/alerts")
    public List<?> getAbnormalRecords(@RequestParam String username) {
        return assistantService.getLargePayments(username);
    }
}