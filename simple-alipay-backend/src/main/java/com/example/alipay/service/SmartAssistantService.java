package com.example.alipay.service;

import com.example.alipay.model.AssistantMessage;
import com.example.alipay.model.Payment;
import com.example.alipay.repository.AssistantMessageRepository;
import com.example.alipay.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service
public class SmartAssistantService {

    @Autowired
    private AssistantMessageRepository messageRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    // 读取配置文件中的 AI 设置
    @Value("${ai.api.url}")
    private String apiUrl;

    @Value("${ai.api.key}")
    private String apiKey;

    @Value("${ai.api.model}")
    private String apiModel;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * 1. 欢迎语 (保持不变)
     */
    public String getWelcomeMessage() {
        return "您好！我是您的 AI 财务助手。我已经连接到您的账单数据库，您可以问我：“我这月花了多少钱？”、“有没有什么异常支出？” 或者 “帮我分析一下消费习惯”。";
    }

    /**
     * 2. 核心交互：获取数据 -> 组装 Prompt -> 调用 AI -> 保存记录
     */
    public AssistantMessage processChat(String username, String userContent) {
        // A. 准备上下文数据 (让 AI 知道用户现在的财务状况)
        Map<String, Object> monthlyStats = analyzeMonthly(username);
        List<Payment> largePayments = getLargePayments(username);

        // B. 构建提示词 (System Prompt)
        StringBuilder systemPrompt = new StringBuilder();
        systemPrompt.append("你是一个专业的财务助手。");
        systemPrompt.append("当前用户名为: ").append(username).append("。");
        systemPrompt.append("以下是该用户本月的实时财务数据，请根据这些数据回答用户的问题：\n");
        systemPrompt.append("1. 本月总支出: ").append(monthlyStats.get("total")).append("元\n");
        systemPrompt.append("2. 本月交易笔数: ").append(monthlyStats.get("count")).append("笔\n");
        systemPrompt.append("3. 财务健康建议: ").append(monthlyStats.get("advice")).append("\n");

        if (!largePayments.isEmpty()) {
            systemPrompt.append("4. 近期大额支出记录(超过500元): ");
            for (Payment p : largePayments) {
                systemPrompt.append("[").append(p.getCreatedAt().toLocalDate())
                        .append(" 消费 ").append(p.getAmount()).append("元], ");
            }
        } else {
            systemPrompt.append("4. 近期无大额异常支出。");
        }

        systemPrompt.append("\n请用自然、亲切的语气简短回答用户。如果用户问无关问题，礼貌回绝。");

        // C. 调用 AI 接口获取回复
        String botReply = callAiApi(systemPrompt.toString(), userContent);

        // D. 保存记录到数据库
        AssistantMessage message = new AssistantMessage();
        message.setUsername(username);
        message.setUserContent(userContent);
        message.setBotReply(botReply);

        return messageRepository.save(message);
    }

    /**
     * 私有方法：调用大模型 API
     */
    private String callAiApi(String systemContext, String userMessage) {
        try {
            // 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + apiKey);

            // 构建请求体 (OpenAI 兼容格式 JSON)
            // 结构: { "model": "...", "messages": [ {"role":"system",...}, {"role":"user",...} ] }
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", apiModel);
            requestBody.put("temperature", 0.7);

            List<Map<String, String>> messages = new ArrayList<>();
            messages.add(Map.of("role", "system", "content", systemContext));
            messages.add(Map.of("role", "user", "content", userMessage));
            requestBody.put("messages", messages);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            // 发送 POST 请求
            ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, entity, Map.class);

            // 解析响应 (提取 choices[0].message.content)
            if (response.getBody() != null) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
                if (choices != null && !choices.isEmpty()) {
                    Map<String, Object> messageObj = (Map<String, Object>) choices.get(0).get("message");
                    return (String) messageObj.get("content");
                }
            }
            return "AI 响应异常，请稍后再试。";

        } catch (Exception e) {
            e.printStackTrace();
            return "抱歉，AI 服务暂时不可用 (" + e.getMessage() + ")。但根据数据，您本月消费了 "
                    + analyzeMonthly("username").get("total") + " 元。"; // 降级处理
        }
    }

    // --- 以下保持原有的数据查询逻辑不变 ---

    public Map<String, Object> analyzeMonthly(String username) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end = now.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59);

        List<Payment> payments = paymentRepository.findByFromUserAndCreatedAtBetweenAndStatus(username, start, end, "SUCCESS");

        BigDecimal total = payments.stream().map(Payment::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        String advice = "消费保持在合理范围内。";
        if (total.compareTo(new BigDecimal("5000")) > 0) advice = "本月支出较高，建议关注娱乐类消费。";
        else if (total.compareTo(new BigDecimal("10000")) > 0) advice = "警告：本月支出已大幅超出平均水平！";

        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("advice", advice);
        result.put("count", payments.size());
        return result;
    }

    public List<Payment> getLargePayments(String username) {
        BigDecimal threshold = new BigDecimal("500.00");
        return paymentRepository.findByFromUserAndAmountGreaterThanEqualAndStatus(username, threshold, "SUCCESS");
    }
}