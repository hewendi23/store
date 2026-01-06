package com.example.alipay.config.fintech;

import com.example.alipay.security.fintech.CustomUserDetailsService;
import com.example.alipay.security.fintech.JwtAuthenticationFilter;
import com.example.alipay.security.fintech.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(userDetailsService, jwtUtil);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authz -> authz
                // 允许公开访问的API
                .requestMatchers("/api/fintech/auth/register", "/api/fintech/auth/login").permitAll()
                .requestMatchers("/api/fintech/auth/pay-password/**").authenticated()
                .requestMatchers("/api/admin/auth/**").permitAll()
                .requestMatchers("/api/pay/**").permitAll()
                .requestMatchers("/api/collect/**").permitAll()
                .requestMatchers("/api/travel/**").permitAll()
                .requestMatchers("/api/assistant/**").permitAll()
                .requestMatchers("/error").permitAll()
                .requestMatchers("/api/site/gates/*/entry").permitAll()
                .requestMatchers("/api/site/gates/*/exit").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/site/stations/**").permitAll()
                // Actuator 健康检查端点
                .requestMatchers("/actuator/health").permitAll()
                // 管理员API需要SUPER_ADMIN角色
                .requestMatchers("/api/admin/**").hasRole("SUPER_ADMIN")
                // 站点管理API需要SITE_MANAGER角色
                .requestMatchers("/api/site/**").hasAnyRole("SITE_MANAGER", "SUPER_ADMIN")
                // 金融科技API需要认证
                .requestMatchers("/api/fintech/assets/**").authenticated()
                .requestMatchers("/api/fintech/bills/**").authenticated()
                .anyRequest().authenticated()
            )
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(401);
                    response.setContentType("application/json;charset=UTF-8");
                    String json = "{\"status\":401,\"error\":\"unauthorized\",\"message\":\"Authentication required\",\"path\":\"" + request.getRequestURI() + "\"}";
                    response.getWriter().write(json);
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(403);
                    response.setContentType("application/json;charset=UTF-8");
                    String json = "{\"status\":403,\"error\":\"forbidden\",\"message\":\"Access denied\",\"path\":\"" + request.getRequestURI() + "\"}";
                    response.getWriter().write(json);
                })
            )
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
