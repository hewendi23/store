# 金融科技模块集成说明

## 新增功能模块

### 1. 用户认证模块
- **注册**: POST `/api/fintech/auth/register`
- **登录**: POST `/api/fintech/auth/login`

### 2. 资产管理模块
- **查询余额**: GET `/api/fintech/assets/balance/{userId}`
- **添加银行卡**: POST `/api/fintech/assets/cards`
- **查询银行卡**: GET `/api/fintech/assets/cards/{userId}`
- **转账**: POST `/api/fintech/assets/transfer`

### 3. 账单管理模块
- **查询账单**: GET `/api/fintech/bills/{userId}`
- **按类型查询**: GET `/api/fintech/bills/{userId}/type/{type}`
- **账单概览**: GET `/api/fintech/bills/{userId}/overview`
- **创建账单**: POST `/api/fintech/bills/create`

## 安全特性
- JWT令牌认证
- 密码BCrypt加密
- 无状态会话管理

## 数据库表结构
- `fintech_users` - 用户表
- `bank_card` - 银行卡表
- `bill` - 账单表
- `user_audit` - 用户审核表

## 与原项目的关系
- 完全独立的新模块，不影响原有支付宝功能
- 使用相同的数据库连接
- 共享Spring Boot框架
- 新增API路径前缀 `/api/fintech/`

## Postman 测试指南

### 1. 用户注册
**请求:**
- **方法**: POST
- **URL**: `http://localhost:8080/api/fintech/auth/register`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "username": "testuser",
  "password": "123456",
  "phone": "13800138000"
}
```

**响应示例:**
```json
{
  "message": "注册成功",
  "userId": 1,
  "username": "testuser"
}
```

### 2. 用户登录
**请求:**
- **方法**: POST
- **URL**: `http://localhost:8080/api/fintech/auth/login`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "username": "testuser",
  "password": "123456"
}
```

**响应示例:**
```json
{
  "message": "登录成功",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "userId": 1,
  "username": "testuser",
  "balance": 0.00
}
```

### 3. 查询余额 (需要认证)
**请求:**
- **方法**: GET
- **URL**: `http://localhost:8080/api/fintech/assets/balance/1`
- **Headers**: 
  - `Content-Type: application/json`
  - `Authorization: Bearer <your-jwt-token>`

**响应示例:**
```json
{
  "balance": 0.00
}
```

### 4. 添加银行卡
**请求:**
- **方法**: POST
- **URL**: `http://localhost:8080/api/fintech/assets/cards`
- **Headers**: 
  - `Content-Type: application/json`
  - `Authorization: Bearer <your-jwt-token>`
- **Body**:
```json
{
  "userId": 1,
  "bankName": "中国银行",
  "cardNumber": "6222021234567890123",
  "isDefault": true
}
```

### 5. 查询账单
**请求:**
- **方法**: GET
- **URL**: `http://localhost:8080/api/fintech/bills/1`
- **Headers**: 
  - `Content-Type: application/json`
  - `Authorization: Bearer <your-jwt-token>`

### 6. 转账操作
**请求:**
- **方法**: POST
- **URL**: `http://localhost:8080/api/fintech/assets/transfer`
- **Headers**: 
  - `Content-Type: application/json`
  - `Authorization: Bearer <your-jwt-token>`
- **Body**:
```json
{
  "fromUserId": 1,
  "toUserId": 2,
  "amount": 100.00
}
```

## 注意事项
- 金融科技模块需要认证才能访问资产和账单相关API
- 认证API公开访问
- 与原支付宝模块完全解耦，可以独立使用
