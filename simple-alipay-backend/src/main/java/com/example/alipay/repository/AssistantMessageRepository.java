package com.example.alipay.repository;

import com.example.alipay.model.AssistantMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssistantMessageRepository extends JpaRepository<AssistantMessage, Long> {
    // 获取某用户的历史对话，按时间倒序
    List<AssistantMessage> findByUsernameOrderByCreatedAtDesc(String username);
}