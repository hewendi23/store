package com.example.alipay.controller;

import com.example.alipay.model.Payment;
import com.example.alipay.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/pay")
public class PaymentController {
    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService){this.paymentService = paymentService;}

    @PostMapping("/execute")
    public ResponseEntity<?> executePayment(@RequestBody Map<String,String> body){
        String from = body.getOrDefault("fromUser","alice");
        String to = body.getOrDefault("toMerchant","merchant1");
        String amtS = body.getOrDefault("amount","0.00");
        String method = body.getOrDefault("method","qr");
        String paySource = body.get("paySource");
        String payCardIdS = body.get("payCardId");
        Long payCardId = (payCardIdS == null || payCardIdS.isBlank()) ? null : Long.valueOf(payCardIdS);
        String receiveSource = body.get("receiveSource");
        String receiveCardIdS = body.get("receiveCardId");
        Long receiveCardId = (receiveCardIdS == null || receiveCardIdS.isBlank()) ? null : Long.valueOf(receiveCardIdS);
        String payPassword = body.get("payPassword");
        BigDecimal amount = new BigDecimal(amtS);

        if (from.equals(to)) {
            return ResponseEntity.status(422).body(Map.of("error", "BUSINESS_RULE"));
        }
        if ("balance".equalsIgnoreCase(paySource) || "card".equalsIgnoreCase(paySource)) {
            String pre = paymentService.preCheck(from, payPassword);
            if (pre != null) {
                if ("USER_NOT_FOUND".equals(pre)) {
                    return ResponseEntity.status(404).body(Map.of("error", pre));
                }
                return ResponseEntity.status(403).body(Map.of("error", pre));
            }
        }

        Payment p = paymentService.createAndExecutePayment(from,to,amount,method,paySource,payCardId,receiveSource,receiveCardId);
        if ("FAILED".equals(p.getStatus())) {
            String code = p.getErrorCode();
            if ("INSUFFICIENT_FUNDS".equals(code)) {
                return ResponseEntity.status(402).body(p);
            } else if ("CARD_NOT_FOUND".equals(code) || "CARD_OWNERSHIP_ERROR".equals(code)) {
                return ResponseEntity.status(404).body(p);
            } else if ("INVALID_PAY_SOURCE".equals(code)) {
                return ResponseEntity.status(422).body(p);
            } else if ("RECEIVER_NOT_FOUND".equals(code)) {
                return ResponseEntity.status(404).body(p);
            }
        }
        return ResponseEntity.ok(p);
    }
}
