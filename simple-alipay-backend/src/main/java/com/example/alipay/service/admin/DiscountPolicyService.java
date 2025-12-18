package com.example.alipay.service.admin;

import com.example.alipay.model.admin.DiscountPolicy;
import com.example.alipay.repository.admin.DiscountPolicyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountPolicyService {
    private final DiscountPolicyRepository discountPolicyRepository;
    private final AdminOperationLogger adminOperationLogger;

    public DiscountPolicyService(DiscountPolicyRepository discountPolicyRepository, AdminOperationLogger adminOperationLogger) {
        this.discountPolicyRepository = discountPolicyRepository;
        this.adminOperationLogger = adminOperationLogger;
    }

    public List<DiscountPolicy> getAllPolicies() {
        return discountPolicyRepository.findAll();
    }

    public List<DiscountPolicy> getActivePolicies() {
        return discountPolicyRepository.findByEnabledTrue();
    }

    public Optional<DiscountPolicy> getPolicyById(Long policyId) {
        return discountPolicyRepository.findById(policyId);
    }

    public DiscountPolicy createPolicy(DiscountPolicy policy) {
        if (!isValidUserType(policy.getApplicableUserType())) {
            throw new IllegalArgumentException("invalid applicableUserType");
        }
        if (policy.getApplicableLines() != null) {
            policy.setApplicableLines(policy.getApplicableLines().trim());
        }
        policy.setCreatedAt(LocalDateTime.now());
        policy.setUpdatedAt(LocalDateTime.now());
        DiscountPolicy saved = discountPolicyRepository.save(policy);
        adminOperationLogger.log("POLICY_CREATE", "POLICY", saved.getId(), "create policy", true, null);
        return saved;
    }

    public DiscountPolicy updatePolicy(Long policyId, DiscountPolicy policyDetails) {
        Optional<DiscountPolicy> policyOpt = discountPolicyRepository.findById(policyId);
        if (policyOpt.isPresent()) {
            DiscountPolicy policy = policyOpt.get();
            policy.setPolicyName(policyDetails.getPolicyName());
            policy.setDescription(policyDetails.getDescription());
            policy.setDiscountRate(policyDetails.getDiscountRate());
            if (!isValidUserType(policyDetails.getApplicableUserType())) {
                throw new IllegalArgumentException("invalid applicableUserType");
            }
            policy.setApplicableUserType(policyDetails.getApplicableUserType());
            policy.setApplicableLines(policyDetails.getApplicableLines());
            policy.setStartTime(policyDetails.getStartTime());
            policy.setEndTime(policyDetails.getEndTime());
            policy.setEnabled(policyDetails.isEnabled());
            policy.setUpdatedAt(LocalDateTime.now());
            DiscountPolicy saved = discountPolicyRepository.save(policy);
            adminOperationLogger.log("POLICY_UPDATE", "POLICY", saved.getId(), "update policy", true, null);
            return saved;
        }
        adminOperationLogger.log("POLICY_UPDATE", "POLICY", policyId, "update policy", false, "policy not found");
        return null;
    }

    private boolean isValidUserType(String type) {
        if (type == null) return false;
        String t = type.trim();
        return t.equals("GENERAL") || t.equals("STUDENT") || t.equals("ELDERLY") || t.equals("DISABLED");
    }

    public boolean enablePolicy(Long policyId) {
        Optional<DiscountPolicy> policyOpt = discountPolicyRepository.findById(policyId);
        if (policyOpt.isPresent()) {
            DiscountPolicy policy = policyOpt.get();
            policy.setEnabled(true);
            policy.setUpdatedAt(LocalDateTime.now());
            discountPolicyRepository.save(policy);
            adminOperationLogger.log("POLICY_ENABLE", "POLICY", policyId, "enable policy", true, null);
            return true;
        }
        adminOperationLogger.log("POLICY_ENABLE", "POLICY", policyId, "enable policy", false, "policy not found");
        return false;
    }

    public boolean disablePolicy(Long policyId) {
        Optional<DiscountPolicy> policyOpt = discountPolicyRepository.findById(policyId);
        if (policyOpt.isPresent()) {
            DiscountPolicy policy = policyOpt.get();
            policy.setEnabled(false);
            policy.setUpdatedAt(LocalDateTime.now());
            discountPolicyRepository.save(policy);
            adminOperationLogger.log("POLICY_DISABLE", "POLICY", policyId, "disable policy", true, null);
            return true;
        }
        adminOperationLogger.log("POLICY_DISABLE", "POLICY", policyId, "disable policy", false, "policy not found");
        return false;
    }

    public boolean deletePolicy(Long policyId) {
        if (discountPolicyRepository.existsById(policyId)) {
            discountPolicyRepository.deleteById(policyId);
            adminOperationLogger.log("POLICY_DELETE", "POLICY", policyId, "delete policy", true, null);
            return true;
        }
        adminOperationLogger.log("POLICY_DELETE", "POLICY", policyId, "delete policy", false, "policy not found");
        return false;
    }

    public List<DiscountPolicy> getPoliciesByLine(String line) {
        return discountPolicyRepository.findByApplicableLinesContaining(line);
    }
}
