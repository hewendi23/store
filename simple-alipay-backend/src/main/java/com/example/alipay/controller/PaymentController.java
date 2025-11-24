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
        String method = body.getOrDefault("method","balance");
        BigDecimal amount = new BigDecimal(amtS);
        Payment p = paymentService.createAndExecutePayment(from,to,amount,method);
        return ResponseEntity.ok(p);
    }
}
