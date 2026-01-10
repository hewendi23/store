package com.example.alipay.service;

import com.example.alipay.model.AssistantMessage;
import com.example.alipay.model.Payment;
import com.example.alipay.model.fintech.Bill;
import com.example.alipay.model.fintech.User;
import com.example.alipay.repository.AssistantMessageRepository;
import com.example.alipay.repository.PaymentRepository;
import com.example.alipay.repository.fintech.BillRepository;
import com.example.alipay.repository.fintech.UserRepository;
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

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;

    // 读取配置文件中的 AI 设置
    @Value("${ai.api.url}")
    private String apiUrl;

    @Value("${ai.api.key}")
    private String apiKey;

    @Value("${ai.api.model}")
    private String apiModel;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getWelcomeMessage() {
        return "您好！我是您的 AI 财务助手。我已连接到您的账单数据库，您可以问我：‘我总共花了多少钱？’、‘有没有什么异常支出？’ 或者 ‘帮我分析一下消费习惯’。";
    }

    public AssistantMessage processChat(String username, String userContent) {
        Map<String, Object> monthlyStats = analyzeMonthly(username);
        List<Bill> largePayments = getLargePayments(username);

        StringBuilder systemPrompt = new StringBuilder();
        systemPrompt.append("你是一个专业的财务助手。");
        systemPrompt.append("当前用户名为: ").append(username).append("。");
        systemPrompt.append("以下是该用户的账单汇总数据，请根据这些数据回答用户的问题：\n");
        systemPrompt.append("1. 总支出: ").append(monthlyStats.get("totalExpenditure")).append("元, 总收入: ")
                .append(monthlyStats.get("totalIncome")).append("元\n");
        systemPrompt.append("2. 本月交易笔数: ").append(monthlyStats.get("count")).append("笔\n");
        systemPrompt.append("3. 财务健康建议: ").append(monthlyStats.get("advice")).append("\n");

        if (!largePayments.isEmpty()) {
            systemPrompt.append("4. 近期大额支出记录(超过500元): ");
            for (Bill p : largePayments) {
                java.math.BigDecimal a = p.getAmount() == null ? java.math.BigDecimal.ZERO : p.getAmount().abs();
                java.time.LocalDate d = p.getTransactionTime() != null ? p.getTransactionTime().toLocalDate() : java.time.LocalDate.now();
                systemPrompt.append("[").append(d)
                        .append(" 消费 ").append(a).append("元], ");
            }
        } else {
            systemPrompt.append("4. 近期无大额异常支出。");
        }

        systemPrompt.append("\n请用自然、亲切的语气简短回答用户。如果用户问无关问题，礼貌回绝。");

        String botReply = callAiApi(systemPrompt.toString(), userContent, username);

        AssistantMessage message = new AssistantMessage();
        message.setUsername(username);
        message.setUserContent(userContent);
        message.setBotReply(botReply);

        return messageRepository.save(message);
    }

    private String callAiApi(String systemContext, String userMessage, String username) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + apiKey);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", apiModel);
            requestBody.put("temperature", 0.7);

            List<Map<String, String>> messages = new ArrayList<>();
            messages.add(Map.of("role", "system", "content", systemContext));
            messages.add(Map.of("role", "user", "content", userMessage));
            requestBody.put("messages", messages);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, entity, Map.class);

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
                    + analyzeMonthly(username).get("total") + " 元。";
        }
    }

    public Map<String, Object> analyzeMonthly(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            Map<String, Object> empty = new HashMap<>();
            empty.put("total", BigDecimal.ZERO);
            empty.put("totalExpenditure", BigDecimal.ZERO);
            empty.put("totalIncome", BigDecimal.ZERO);
            empty.put("advice", "消费保持在合理范围内。");
            empty.put("count", 0);
            return empty;
        }
        Long userId = userOpt.get().getId();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end = now.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59);

        List<Bill> monthlyExp = billRepository.findByUserIdAndTypeAndTransactionTimeBetween(userId, "EXPENDITURE", start, end);
        List<Bill> monthlyInc = billRepository.findByUserIdAndTypeAndTransactionTimeBetween(userId, "INCOME", start, end);

        List<Bill> allExp = billRepository.findByUserIdAndTypeOrderByTransactionTimeDesc(userId, "EXPENDITURE");
        List<Bill> allInc = billRepository.findByUserIdAndTypeOrderByTransactionTimeDesc(userId, "INCOME");

        BigDecimal monthlyTotalExp = monthlyExp.stream()
                .map(b -> b.getAmount() == null ? BigDecimal.ZERO : b.getAmount().abs())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal allTotalExp = allExp.stream()
                .map(b -> b.getAmount() == null ? BigDecimal.ZERO : b.getAmount().abs())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal allTotalInc = allInc.stream()
                .map(b -> b.getAmount() == null ? BigDecimal.ZERO : b.getAmount().abs())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        String advice;
        if (monthlyTotalExp.compareTo(new BigDecimal("10000")) > 0) advice = "警告：本月支出已大幅超出平均水平！";
        else if (monthlyTotalExp.compareTo(new BigDecimal("5000")) > 0) advice = "本月支出较高，建议关注娱乐类消费。";
        else advice = "消费保持在合理范围内。";

        Map<String, Object> result = new HashMap<>();
        result.put("total", monthlyTotalExp);
        result.put("totalExpenditure", allTotalExp);
        result.put("totalIncome", allTotalInc);
        result.put("advice", advice);
        result.put("count", monthlyExp.size() + monthlyInc.size());
        return result;
    }

    public List<Bill> getLargePayments(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) return java.util.Collections.emptyList();
        Long userId = userOpt.get().getId();
        BigDecimal threshold = new BigDecimal("500.00");
        List<Bill> bills = billRepository.findByUserIdAndTypeOrderByTransactionTimeDesc(userId, "EXPENDITURE");
        return bills.stream().filter(b -> {
            BigDecimal a = b.getAmount() == null ? BigDecimal.ZERO : b.getAmount().abs();
            return a.compareTo(threshold) >= 0;
        }).toList();
    }
}
