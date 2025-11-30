package com.example.alipay.controller.admin;

import com.example.alipay.model.admin.DiscountPolicy;
import com.example.alipay.service.admin.DiscountPolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/policies")
public class DiscountPolicyController {
    private final DiscountPolicyService discountPolicyService;

    public DiscountPolicyController(DiscountPolicyService discountPolicyService) {
        this.discountPolicyService = discountPolicyService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPolicies() {
        List<DiscountPolicy> policies = discountPolicyService.getAllPolicies();
        return ResponseEntity.ok(policies);
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActivePolicies() {
        List<DiscountPolicy> policies = discountPolicyService.getActivePolicies();
        return ResponseEntity.ok(policies);
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<?> getPolicyById(@PathVariable Long policyId) {
        return discountPolicyService.getPolicyById(policyId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createPolicy(@RequestBody DiscountPolicy policy) {
        try {
            DiscountPolicy createdPolicy = discountPolicyService.createPolicy(policy);
            return ResponseEntity.ok(Map.of(
                "message", "折扣策略创建成功",
                "policyId", createdPolicy.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{policyId}")
    public ResponseEntity<?> updatePolicy(@PathVariable Long policyId, @RequestBody DiscountPolicy policyDetails) {
        DiscountPolicy updatedPolicy = discountPolicyService.updatePolicy(policyId, policyDetails);
        if (updatedPolicy != null) {
            return ResponseEntity.ok(Map.of(
                "message", "折扣策略更新成功",
                "policyId", updatedPolicy.getId()
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "策略不存在"));
        }
    }

    @PostMapping("/{policyId}/enable")
    public ResponseEntity<?> enablePolicy(@PathVariable Long policyId) {
        boolean success = discountPolicyService.enablePolicy(policyId);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "折扣策略已启用"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "策略不存在"));
        }
    }

    @PostMapping("/{policyId}/disable")
    public ResponseEntity<?> disablePolicy(@PathVariable Long policyId) {
        boolean success = discountPolicyService.disablePolicy(policyId);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "折扣策略已禁用"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "策略不存在"));
        }
    }

    @DeleteMapping("/{policyId}")
    public ResponseEntity<?> deletePolicy(@PathVariable Long policyId) {
        boolean success = discountPolicyService.deletePolicy(policyId);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "折扣策略已删除"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "策略不存在"));
        }
    }

    @GetMapping("/line/{line}")
    public ResponseEntity<?> getPoliciesByLine(@PathVariable String line) {
        List<DiscountPolicy> policies = discountPolicyService.getPoliciesByLine(line);
        return ResponseEntity.ok(policies);
    }
}
