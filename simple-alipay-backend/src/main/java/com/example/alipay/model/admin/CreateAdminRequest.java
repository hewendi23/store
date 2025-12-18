package com.example.alipay.model.admin;

import lombok.Data;

@Data
public class CreateAdminRequest {
    private String username;
    private String password;
    private String role;
}
