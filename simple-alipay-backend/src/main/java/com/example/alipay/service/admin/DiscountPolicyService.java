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

    public DiscountPolicyService(DiscountPolicyRepository discountPolicyRepository) {
        this.discountPolicyRepository = discountPolicyRepository;
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
        policy.setCreatedAt(LocalDateTime.now());
        policy.setUpdatedAt(LocalDateTime.now());
        return discountPolicyRepository.save(policy);
    }

    public DiscountPolicy updatePolicy(Long policyId, DiscountPolicy policyDetails) {
        Optional<DiscountPolicy> policyOpt = discountPolicyRepository.findById(policyId);
        if (policyOpt.isPresent()) {
            DiscountPolicy policy = policyOpt.get();
            policy.setPolicyName(policyDetails.getPolicyName());
            policy.setDescription(policyDetails.getDescription());
            policy.setDiscountRate(policyDetails.getDiscountRate());
            policy.setApplicableUserType(policyDetails.getApplicableUserType());
            policy.setApplicableLines(policyDetails.getApplicableLines());
            policy.setStartTime(policyDetails.getStartTime());
            policy.setEndTime(policyDetails.getEndTime());
            policy.setEnabled(policyDetails.isEnabled());
            policy.setUpdatedAt(LocalDateTime.now());
            return discountPolicyRepository.save(policy);
        }
        return null;
    }

    public boolean enablePolicy(Long policyId) {
        Optional<DiscountPolicy> policyOpt = discountPolicyRepository.findById(policyId);
        if (policyOpt.isPresent()) {
            DiscountPolicy policy = policyOpt.get();
            policy.setEnabled(true);
            policy.setUpdatedAt(LocalDateTime.now());
            discountPolicyRepository.save(policy);
            return true;
        }
        return false;
    }

    public boolean disablePolicy(Long policyId) {
        Optional<DiscountPolicy> policyOpt = discountPolicyRepository.findById(policyId);
        if (policyOpt.isPresent()) {
            DiscountPolicy policy = policyOpt.get();
            policy.setEnabled(false);
            policy.setUpdatedAt(LocalDateTime.now());
            discountPolicyRepository.save(policy);
            return true;
        }
        return false;
    }

    public boolean deletePolicy(Long policyId) {
        if (discountPolicyRepository.existsById(policyId)) {
            discountPolicyRepository.deleteById(policyId);
            return true;
        }
        return false;
    }

    public List<DiscountPolicy> getPoliciesByLine(String line) {
        return discountPolicyRepository.findByApplicableLinesContaining(line);
    }
}
