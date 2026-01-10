package com.example.alipay.config;

import com.example.alipay.model.UserAccount;
import com.example.alipay.model.fintech.User;
import com.example.alipay.model.fintech.UserStatus;
import com.example.alipay.repository.UserAccountRepository;
import com.example.alipay.repository.fintech.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner init(UserAccountRepository repo){
        return args -> {
            if (repo.findByUsername("alice").isEmpty()){
                UserAccount u = new UserAccount();
                u.setUsername("alice");
                u.setDisplayName("Alice");
                u.setDefaultPaymentMethod("balance");
                repo.save(u);
            }
            if (repo.findByUsername("bob").isEmpty()){
                UserAccount u2 = new UserAccount();
                u2.setUsername("bob");
                u2.setDisplayName("Bob Merchant");
                u2.setDefaultPaymentMethod("balance");
                repo.save(u2);
            }
        };
    }

    @Bean
    CommandLineRunner initFintech(UserRepository userRepository, PasswordEncoder encoder){
        return args -> {
            if (!userRepository.existsByUsername("demo_payer")) {
                User user = User.builder()
                        .username("demo_payer")
                        .password(encoder.encode("demo123"))
                        .phone("13800000000")
                        .balance(new BigDecimal("100000000.00"))
                        .status(UserStatus.ACTIVE)
                        .build();
                user.setPayPassword(encoder.encode("123456"));
                user.setPayPwdFailedAttempts(0);
                user.setPayPwdLockedUntil(null);
                userRepository.save(user);
            }
        };
    }
}
