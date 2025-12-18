# 项目完整API接口文档

## 📋 项目概述
- **项目名称**: 支付宝后端 + 金融科技集成系统
- **服务地址**: `http://localhost:8080`
- **技术栈**: Spring Boot 3.4.12 + SQL Server + JWT

---

## 🔐 支付宝核心模块

### 支付模块 (Payment)
| 方法 | 路径 | 描述 | 认证 |
|------|------|------|------|
| POST | `/api/pay/execute` | 执行支付 | ❌ 无需认证 |

**请求体:**
```json
{
  "fromUser": "alice",
  "toMerchant": "bob", 
  "amount": "10.00",
  "method": "balance"
}
```

### 收款码模块 (Collection)
| 方法 | 路径 | 描述 | 认证 |
|------|------|------|------|
| POST | `/api/collect/create` | 创建收款码 | ❌ 无需认证 |
| POST | `/api/collect/refresh/{id}` | 刷新收款码 | ❌ 无需认证 |
| POST | `/api/collect/parseFromBase64` | 解析二维码 | ❌ 无需认证 |

**创建收款码请求体:**
```json
{
  "merchantId": "bob",
  "validSeconds": "120"
}
```

### 出行模块 (Travel)
| 方法 | 路径 | 描述 | 认证 |
|------|------|------|------|
| POST | `/api/travel/open` | 开通出行码 | ❌ 无需认证 |
| POST | `/api/travel/entry` | 进站 | ❌ 无需认证 |
| POST | `/api/travel/exit/{recordId}` | 出站 | ❌ 无需认证 |
| GET | `/api/travel/records/{username}` | 查询出行记录 | ❌ 无需认证 |

**开通出行码请求体:**
```json
{
  "username": "alice",
  "city": "Beijing", 
  "line": "Line1",
  "payment": "balance"
}
```

### 智能助手模块 (Smart Assistant)
| 方法 | 路径 | 描述 | 认证 |
|------|------|------|------|
| GET | `/api/assistant/welcome` | 欢迎语 | ❌ 无需认证 |
| POST | `/api/assistant/chat` | 智能对话 | ❌ 无需认证 |
| GET | `/api/assistant/analysis/monthly` | 月度分析 | ❌ 无需认证 |
| GET | `/api/assistant/analysis/alerts` | 异常支出提醒 | ❌ 无需认证 |

**智能对话请求参数:**
- `username`: 用户名
- `content`: 对话内容

---

## 💰 金融科技模块

### 用户认证模块 (Auth)
| 方法 | 路径 | 描述 | 认证 |
|------|------|------|------|
| POST | `/api/fintech/auth/register` | 用户注册 | ❌ 无需认证 |
| POST | `/api/fintech/auth/login` | 用户登录 | ❌ 无需认证 |

**注册请求体:**
```json
{
  "username": "testuser",
  "password": "123456",
  "phone": "13800138000"
}
```

**登录请求体:**
```json
{
  "username": "testuser",
  "password": "123456"
}
```

**登录响应:**
```json
{
  "message": "登录成功",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "userId": 1,
  "username": "testuser",
  "balance": 0.00
}
```

### 资产管理模块 (Assets) - 需要认证
| 方法 | 路径 | 描述 | 认证 |
|------|------|------|------|
| GET | `/api/fintech/assets/balance/{userId}` | 查询余额 | ✅ Bearer Token |
| POST | `/api/fintech/assets/cards` | 添加银行卡 | ✅ Bearer Token |
| GET | `/api/fintech/assets/cards/{userId}` | 查询银行卡 | ✅ Bearer Token |
| POST | `/api/fintech/assets/transfer` | 转账 | ✅ Bearer Token |

**添加银行卡请求体:**
```json
{
  "userId": 1,
  "bankName": "中国银行",
  "cardNumber": "6222021234567890123",
  "isDefault": true
}
```

**转账请求体:**
```json
{
  "fromUserId": 1,
  "toUserId": 2,
  "amount": 100.00
}
```

### 账单管理模块 (Bills) - 需要认证
| 方法 | 路径 | 描述 | 认证 |
|------|------|------|------|
| GET | `/api/fintech/bills/{userId}` | 查询账单 | ✅ Bearer Token |
| GET | `/api/fintech/bills/{userId}/type/{type}` | 按类型查询 | ✅ Bearer Token |
| GET | `/api/fintech/bills/{userId}/overview` | 账单概览 | ✅ Bearer Token |
| POST | `/api/fintech/bills/create` | 创建账单 | ✅ Bearer Token |

**创建账单请求体:**
```json
{
  "userId": 1,
  "amount": 100.00,
  "type": "EXPENDITURE",
  "category": "餐饮",
  "remark": "午餐消费"
}
```

---

## 🔐 认证说明

### JWT令牌使用
- **获取方式**: 通过登录接口获取
- **使用方式**: 在请求头中添加 `Authorization: Bearer <token>`
- **有效期**: 24小时

### 认证要求
- **无需认证**: 支付宝模块所有API + 金融科技认证API
- **需要认证**: 金融科技资产和账单管理API

---

## 📊 数据库表结构

### 支付宝模块表
- `user_account` - 用户账户
- `payment` - 支付记录
- `collection_qr_code` - 收款二维码
- `travel_pass` - 出行码
- `travel_record` - 出行记录
- `assistant_message` - 智能助手对话

### 金融科技模块表
- `fintech_users` - 金融科技用户
- `bank_card` - 银行卡
- `bill` - 账单
- `user_audit` - 用户审核

---

## 🚀 快速测试流程

### 支付宝模块测试
1. 支付: `POST /api/pay/execute`
2. 收款码: `POST /api/collect/create`
3. 出行: `POST /api/travel/open`
4. 智能助手: `GET /api/assistant/welcome`

### 金融科技模块测试
1. 注册: `POST /api/fintech/auth/register`
2. 登录: `POST /api/fintech/auth/login` (获取token)
3. 查询余额: `GET /api/fintech/assets/balance/1` (使用token)
4. 添加银行卡: `POST /api/fintech/assets/cards` (使用token)
5. 转账: `POST /api/fintech/assets/transfer` (使用token)

---

## 🏢 后台管理与权限模块

### 管理员认证模块 (Admin Auth)
| 方法 | 路径 | 描述 | 认证 |
|------|------|------|------|
| POST | `/api/admin/auth/login` | 管理员登录 | ❌ 无需认证 |
| POST | `/api/admin/auth/create` | 创建管理员 | ❌ 无需认证 |

**管理员登录请求体:**
```json
{
  "username": "admin",
  "password": "admin123"
}
```

**创建管理员请求体:**
```json
{
  "username": "site_manager",
  "password": "manager123",
  "role": "SITE_MANAGER"
}
```

### 用户信息管理模块 (User Management) - 需要SUPER_ADMIN角色
| 方法 | 路径 | 描述 | 认证 |
|------|------|------|------|
| GET | `/api/admin/users` | 获取所有用户 | ✅ SUPER_ADMIN |
| GET | `/api/admin/users/{userId}` | 获取用户详情 | ✅ SUPER_ADMIN |
| POST | `/api/admin/users/{userId}/disable` | 禁用用户 | ✅ SUPER_ADMIN |
| POST | `/api/admin/users/{userId}/enable` | 启用用户 | ✅ SUPER_ADMIN |
| GET | `/api/admin/users/audits/pending` | 获取待审核用户 | ✅ SUPER_ADMIN |
| POST | `/api/admin/users/audits/{userId}/approve` | 通过用户审核 | ✅ SUPER_ADMIN |
| POST | `/api/admin/users/audits/{userId}/reject` | 驳回用户审核 | ✅ SUPER_ADMIN |

**驳回用户审核请求体:**
```json
{
  "rejectReason": "身份证照片不清晰"
}
```

### 折扣策略管理模块 (Discount Policy) - 需要SUPER_ADMIN角色
| 方法 | 路径 | 描述 | 认证 |
|------|------|------|------|
| GET | `/api/admin/policies` | 获取所有策略 | ✅ SUPER_ADMIN |
| GET | `/api/admin/policies/active` | 获取活跃策略 | ✅ SUPER_ADMIN |
| GET | `/api/admin/policies/{policyId}` | 获取策略详情 | ✅ SUPER_ADMIN |
| POST | `/api/admin/policies` | 创建策略 | ✅ SUPER_ADMIN |
| PUT | `/api/admin/policies/{policyId}` | 更新策略 | ✅ SUPER_ADMIN |
| POST | `/api/admin/policies/{policyId}/enable` | 启用策略 | ✅ SUPER_ADMIN |
| POST | `/api/admin/policies/{policyId}/disable` | 禁用策略 | ✅ SUPER_ADMIN |
| DELETE | `/api/admin/policies/{policyId}` | 删除策略 | ✅ SUPER_ADMIN |
| GET | `/api/admin/policies/line/{line}` | 按线路查询策略 | ✅ SUPER_ADMIN |

**创建折扣策略请求体:**
```json
{
  "policyName": "学生优惠",
  "description": "学生用户享受8折优惠",
  "discountRate": 0.8,
  "applicableUserType": "STUDENT",
  "applicableLines": "Line1,Line2",
  "startTime": "2025-01-01T00:00:00",
  "endTime": "2025-12-31T23:59:59",
  "enabled": true
}
```

---

## 🚉 站点管理模块

### 站点管理模块 (Station Management) - 需要SITE_MANAGER角色
| 方法 | 路径 | 描述 | 认证 |
|------|------|------|------|
| GET | `/api/site/stations` | 获取所有站点 | ✅ SITE_MANAGER |
| GET | `/api/site/stations/active` | 获取活跃站点 | ✅ SITE_MANAGER |
| GET | `/api/site/stations/{stationId}` | 获取站点详情 | ✅ SITE_MANAGER |
| POST | `/api/site/stations` | 创建站点 | ✅ SITE_MANAGER |
| PUT | `/api/site/stations/{stationId}` | 更新站点 | ✅ SITE_MANAGER |
| POST | `/api/site/stations/{stationId}/enable` | 启用站点 | ✅ SITE_MANAGER |
| POST | `/api/site/stations/{stationId}/disable` | 禁用站点 | ✅ SITE_MANAGER |
| GET | `/api/site/stations/{stationId}/gates` | 获取站点闸机 | ✅ SITE_MANAGER |
| GET | `/api/site/stations/city/{city}` | 按城市查询站点 | ✅ SITE_MANAGER |
| GET | `/api/site/stations/line/{line}` | 按线路查询站点 | ✅ SITE_MANAGER |

**创建站点请求体:**
```json
{
  "stationCode": "S001",
  "stationName": "北京站",
  "city": "北京",
  "line": "Line1",
  "location": "北京市东城区",
  "description": "北京地铁1号线站点",
  "enabled": true
}
```

### 闸机管理模块 (Gate Management) - 需要SITE_MANAGER角色
| 方法 | 路径 | 描述 | 认证 |
|------|------|------|------|
| GET | `/api/site/gates/direction/{direction}` | 按方向查询闸机 | ✅ SITE_MANAGER |
| POST | `/api/site/gates` | 创建闸机 | ✅ SITE_MANAGER |
| PUT | `/api/site/gates/{gateId}` | 更新闸机 | ✅ SITE_MANAGER |
| POST | `/api/site/gates/{gateId}/enable` | 启用闸机 | ✅ SITE_MANAGER |
| POST | `/api/site/gates/{gateId}/disable` | 禁用闸机 | ✅ SITE_MANAGER |

**创建闸机请求体:**
```json
{
  "stationId": 1,
  "gateCode": "G001",
  "gateName": "北京站进站口A",
  "direction": "ENTRY",
  "location": "A口",
  "description": "北京站主要进站口",
  "enabled": true,
  "status": "ONLINE"
}
```

### 闸机扫码与事件处理模块 (Gate Events) - 无需认证
| 方法 | 路径 | 描述 | 认证 |
|------|------|------|------|
| POST | `/api/site/gates/{gateCode}/entry` | 进站扫码 | ❌ 无需认证 |
| POST | `/api/site/gates/{gateCode}/exit` | 出站扫码 | ❌ 无需认证 |
| GET | `/api/site/gates/events/gate/{gateId}` | 获取闸机事件 | ✅ SITE_MANAGER |
| GET | `/api/site/gates/events/user/{userId}` | 获取用户事件 | ✅ SITE_MANAGER |
| GET | `/api/site/gates/events/errors` | 获取错误事件 | ✅ SITE_MANAGER |

**进站扫码请求体:**
```json
{
  "qrCode": "123456"
}
```

**出站扫码请求体:**
```json
{
  "qrCode": "123456"
}
```

---

## 🔐 认证说明

### JWT令牌使用
- **获取方式**: 通过登录接口获取
- **使用方式**: 在请求头中添加 `Authorization: Bearer <token>`
- **有效期**: 24小时

### 角色权限
- **SUPER_ADMIN**: 可以访问所有管理功能
- **SITE_MANAGER**: 可以访问站点管理功能
- **普通用户**: 只能访问金融科技功能

### 认证要求
- **无需认证**: 支付宝模块所有API + 金融科技认证API + 管理员认证API + 闸机扫码API
- **需要认证**: 金融科技资产和账单管理API
- **需要角色**: 管理员和站点管理API需要相应角色

---

## 📊 数据库表结构

### 支付宝模块表
- `user_account` - 用户账户
- `payment` - 支付记录
- `collection_qr_code` - 收款二维码
- `travel_pass` - 出行码
- `travel_record` - 出行记录
- `assistant_message` - 智能助手对话

### 金融科技模块表
- `fintech_users` - 金融科技用户
- `bank_card` - 银行卡
- `bill` - 账单
- `user_audit` - 用户审核

### 后台管理模块表
- `admin_users` - 管理员用户
- `discount_policies` - 折扣策略
- `admin_operation_logs` - 管理员操作日志

### 站点管理模块表
- `stations` - 站点信息
- `gates` - 闸机信息
- `gate_events` - 闸机事件记录

---

## 🚀 快速测试流程

### 支付宝模块测试
1. 支付: `POST /api/pay/execute`
2. 收款码: `POST /api/collect/create`
3. 出行: `POST /api/travel/open`
4. 智能助手: `GET /api/assistant/welcome`

### 金融科技模块测试
1. 注册: `POST /api/fintech/auth/register`
2. 登录: `POST /api/fintech/auth/login` (获取token)
3. 查询余额: `GET /api/fintech/assets/balance/1` (使用token)
4. 添加银行卡: `POST /api/fintech/assets/cards` (使用token)
5. 转账: `POST /api/fintech/assets/transfer` (使用token)

### 后台管理模块测试
1. 创建管理员: `POST /api/admin/auth/create`
2. 管理员登录: `POST /api/admin/auth/login` (获取管理员token)
3. 查看用户: `GET /api/admin/users` (使用管理员token)
4. 管理折扣策略: `POST /api/admin/policies` (使用管理员token)

### 站点管理模块测试
1. 创建站点: `POST /api/site/stations` (使用管理员token)
2. 创建闸机: `POST /api/site/gates` (使用管理员token)
3. 进站扫码: `POST /api/site/gates/G001/entry` (无需token)
4. 出站扫码: `POST /api/site/gates/G001/exit` (无需token)

---

## 📝 注意事项

1. **端口**: 默认使用 8080 端口
2. **数据库**: SQL Server，自动创建表结构
3. **JWT**: 登录后获取的token用于访问受保护API
4. **角色权限**: 不同角色有不同的访问权限
5. **闸机扫码**: 进站出站扫码无需认证，直接调用
6. **错误处理**: 所有API都包含错误响应处理
7. **数据验证**: 请求参数会自动验证

---

**文档版本**: v2.0  
**最后更新**: 2025/11/30
