package com.example.alipay.config;

import com.example.alipay.model.UserAccount;
import com.example.alipay.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
