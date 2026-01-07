package com.example.alipay.security.fintech;

import com.example.alipay.model.admin.AdminUser;
import com.example.alipay.model.fintech.User;
import com.example.alipay.repository.admin.AdminUserRepository;
import com.example.alipay.repository.fintech.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AdminUserRepository adminUserRepository;

    public CustomUserDetailsService(UserRepository userRepository, AdminUserRepository adminUserRepository) {
        this.userRepository = userRepository;
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        Optional<AdminUser> adminOpt = adminUserRepository.findByUsername(username);
        if (adminOpt.isPresent()) {
            return adminOpt.get();
        }
        throw new UsernameNotFoundException("用户不存在: " + username);
    }
}
