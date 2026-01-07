package com.example.alipay.controller.fintech;

import com.example.alipay.model.fintech.BankCard;
import com.example.alipay.service.fintech.AssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fintech/assets")
public class AssetController {
    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<?> getBalance(@PathVariable Long userId) {
        BigDecimal balance = assetService.getBalance(userId);
        return ResponseEntity.ok(Map.of("balance", balance));
    }

    @PostMapping("/cards")
    public ResponseEntity<?> addBankCard(@RequestBody Map<String, Object> request) {
        try {
            Long userId = Long.valueOf(request.get("userId").toString());
            String bankName = (String) request.get("bankName");
            String cardNumber = (String) request.get("cardNumber");
            Boolean isDefault = (Boolean) request.get("isDefault");

            if (isDefault == null) {
                isDefault = false;
            }

            BankCard card = assetService.addBankCard(userId, bankName, cardNumber, isDefault);
            return ResponseEntity.ok(Map.of(
                "message", "银行卡添加成功",
                "cardId", card.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/cards/{userId}")
    public ResponseEntity<?> getUserCards(@PathVariable Long userId) {
        List<BankCard> cards = assetService.getUserCards(userId);
        return ResponseEntity.ok(cards);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody Map<String, Object> request) {
        try {
            Long fromUserId = Long.valueOf(request.get("fromUserId").toString());
            Long toUserId = Long.valueOf(request.get("toUserId").toString());
            BigDecimal amount = new BigDecimal(request.get("amount").toString());

            boolean success = assetService.transfer(fromUserId, toUserId, amount);
            if (success) {
                return ResponseEntity.ok(Map.of("message", "转账成功"));
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "转账失败，余额不足或用户不存在"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
