package com.example.alipay.controller.fintech;

import com.example.alipay.model.fintech.Bill;
import com.example.alipay.service.fintech.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fintech/bills")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserBills(@PathVariable Long userId) {
        List<Bill> bills = billService.getUserBills(userId);
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/{userId}/type/{type}")
    public ResponseEntity<?> getUserBillsByType(@PathVariable Long userId, @PathVariable String type) {
        List<Bill> bills = billService.getUserBillsByType(userId, type);
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/{userId}/overview")
    public ResponseEntity<?> getBillOverview(@PathVariable Long userId) {
        BillService.BillOverviewDto overview = billService.getBillOverview(userId);
        return ResponseEntity.ok(overview);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBill(@RequestBody Map<String, Object> request) {
        try {
            Long userId = Long.valueOf(request.get("userId").toString());
            BigDecimal amount = new BigDecimal(request.get("amount").toString());
            String type = (String) request.get("type");
            String category = (String) request.get("category");
            String remark = (String) request.get("remark");

            Bill bill = billService.createBill(userId, amount, type, category, remark);
            return ResponseEntity.ok(Map.of(
                "message", "账单创建成功",
                "billId", bill.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
