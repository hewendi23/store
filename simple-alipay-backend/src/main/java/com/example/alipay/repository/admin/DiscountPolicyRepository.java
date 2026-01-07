package com.example.alipay.repository.admin;

import com.example.alipay.model.admin.DiscountPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiscountPolicyRepository extends JpaRepository<DiscountPolicy, Long> {
    List<DiscountPolicy> findByEnabledTrue();
    List<DiscountPolicy> findByApplicableLinesContaining(String line);
}
