package com.example.alipay.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assistant_messages")
public class AssistantMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; // 对应 UserAccount.username 和 Payment.fromUser

    @Column(length = 1000)
    private String userContent; // 用户发送的内容

    @Column(length = 1000)
    private String botReply;    // 助手回复的内容

    private LocalDateTime createdAt;

    public AssistantMessage() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getUserContent() { return userContent; }
    public void setUserContent(String userContent) { this.userContent = userContent; }
    public String getBotReply() { return botReply; }
    public void setBotReply(String botReply) { this.botReply = botReply; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}