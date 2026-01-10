# 项目数据库表结构文档

## 📋 数据库概述

- **数据库类型**: SQL Server
- **数据库名称**: hewendi
- **表总数**: 15个表
- **模块划分**: 4大模块

---

## 🏦 支付宝核心模块表

### 1. `user_account` - 用户账户表
**用途**: 存储支付宝用户账户信息

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 主键ID |
| username | VARCHAR(255) | NOT NULL, UNIQUE | 用户名 |
| balance | DECIMAL(10,2) | DEFAULT 0.00 | 账户余额 |
| created_at | DATETIME | | 创建时间 |
| updated_at | DATETIME | | 更新时间 |

### 2. `payment` - 支付记录表
**用途**: 存储支付交易记录

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 主键ID |
| from_user | VARCHAR(255) | NOT NULL | 付款方 |
| to_merchant | VARCHAR(255) | NOT NULL | 收款方 |
| amount | DECIMAL(10,2) | NOT NULL | 支付金额 |
| method | VARCHAR(50) | | 支付方式 |
| status | VARCHAR(50) | | 支付状态 |
| created_at | DATETIME | | 创建时间 |

### 3. `collection_qr_code` - 收款二维码表
**用途**: 存储收款二维码信息

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 主键ID |
| merchant_id | VARCHAR(255) | NOT NULL | 商户ID |
| qr_code_content | TEXT | | 二维码内容 |
| valid_seconds | INT | | 有效时间(秒) |
| created_at | DATETIME | | 创建时间 |
| expires_at | DATETIME | | 过期时间 |

### 4. `travel_pass` - 出行码表
**用途**: 存储用户出行码信息

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 主键ID |
| username | VARCHAR(255) | NOT NULL | 用户名 |
| city | VARCHAR(100) | | 城市 |
| line | VARCHAR(100) | | 线路 |
| payment | VARCHAR(50) | | 支付方式 |
| status | VARCHAR(50) | | 状态 |
| created_at | DATETIME | | 创建时间 |

### 5. `travel_record` - 出行记录表
**用途**: 存储用户出行记录

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 主键ID |
| username | VARCHAR(255) | NOT NULL | 用户名 |
| entry_station | VARCHAR(255) | | 进站站点 |
| exit_station | VARCHAR(255) | | 出站站点 |
| fare | DECIMAL(8,2) | | 费用 |
| status | VARCHAR(50) | | 状态 |
| entry_time | DATETIME | | 进站时间 |
| exit_time | DATETIME | | 出站时间 |

### 6. `assistant_message` - 智能助手对话表
**用途**: 存储智能助手对话记录

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 主键ID |
| username | VARCHAR(255) | NOT NULL | 用户名 |
| content | TEXT | | 对话内容 |
| role | VARCHAR(50) | | 角色(user/assistant) |
| created_at | DATETIME | | 创建时间 |

---

## 💰 金融科技模块表

### 7. `fintech_users` - 金融科技用户表
**用途**: 存储金融科技用户信息

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 主键ID |
| username | VARCHAR(255) | NOT NULL, UNIQUE | 用户名 |
| password | VARCHAR(255) | NOT NULL | 密码 |
| phone | VARCHAR(20) | | 手机号 |
| balance | DECIMAL(10,2) | DEFAULT 0.00 | 余额 |
| status | VARCHAR(50) | | 用户状态 |
| enabled | BIT | DEFAULT 1 | 是否启用 |
| created_at | DATETIME | | 创建时间 |
| updated_at | DATETIME | | 更新时间 |

### 8. `bank_card` - 银行卡表
**用途**: 存储用户绑定的银行卡信息

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 主键ID |
| user_id | BIGINT | NOT NULL | 用户ID |
| bank_name | VARCHAR(255) | NOT NULL | 银行名称 |
| card_number | VARCHAR(50) | NOT NULL | 卡号 |
| is_default | BIT | DEFAULT 0 | 是否默认卡 |
| created_at | DATETIME | | 创建时间 |

### 9. `bill` - 账单表
**用途**: 存储用户账单记录

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 主键ID |
| user_id | BIGINT | NOT NULL | 用户ID |
| amount | DECIMAL(10,2) | NOT NULL | 金额 |
| type | VARCHAR(50) | | 类型(收入/支出) |
| category | VARCHAR(100) | | 分类 |
| remark | VARCHAR(500) | | 备注 |
| created_at | DATETIME | | 创建时间 |

### 10. `user_audit` - 用户审核表
**用途**: 存储用户审核记录

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 主键ID |
| user_id | BIGINT | NOT NULL | 用户ID |
| audit_status | VARCHAR(50) | | 审核状态 |
| reject_reason | VARCHAR(500) | | 驳回原因 |
| created_at | DATETIME | | 创建时间 |
| updated_at | DATETIME | | 更新时间 |

---

## 🏢 后台管理模块表

### 11. `admin_users` - 管理员用户表
**用途**: 存储后台管理员账号信息

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 主键ID |
| username | VARCHAR(255) | NOT NULL, UNIQUE | 用户名 |
| password | VARCHAR(255) | NOT NULL | 密码 |
| role | VARCHAR(50) | | 角色(SUPER_ADMIN/SITE_MANAGER) |
| enabled | BIT | DEFAULT 1 | 是否启用 |

### 12. `discount_policies` - 折扣策略表
**用途**: 存储折扣策略配置

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 主键ID |
| policy_name | VARCHAR(255) | | 策略名称 |
| description | VARCHAR(500) | | 策略描述 |
| discount_rate | DECIMAL(5,2) | | 折扣率(0.8表示8折) |
| applicable_user_type | VARCHAR(100) | | 适用用户类型 |
| applicable_lines | VARCHAR(500) | | 适用线路 |
| start_time | DATETIME | | 生效时间 |
| end_time | DATETIME | | 失效时间 |
| enabled | BIT | DEFAULT 1 | 是否启用 |
| created_at | DATETIME | | 创建时间 |
| updated_at | DATETIME | | 更新时间 |

### 13. `admin_operation_logs` - 管理员操作日志表
**用途**: 记录管理员操作日志

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 主键ID |
| admin_id | BIGINT | NOT NULL | 管理员ID |
| admin_username | VARCHAR(255) | | 管理员用户名 |
| operation_type | VARCHAR(100) | | 操作类型 |
| target_type | VARCHAR(100) | | 目标类型 |
| target_id | BIGINT | | 目标ID |
| operation_details | VARCHAR(1000) | | 操作详情 |
| ip_address | VARCHAR(50) | | IP地址 |
| operation_time | DATETIME | | 操作时间 |
| success | BIT | DEFAULT 1 | 是否成功 |
| error_message | VARCHAR(500) | | 错误信息 |

---

## 🚉 站点管理模块表

### 14. `stations` - 站点信息表
**用途**: 存储地铁站点信息

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 主键ID |
| station_code | VARCHAR(50) | | 站点编码 |
| station_name | VARCHAR(255) | | 站点名称 |
| city | VARCHAR(100) | | 所在城市 |
| line | VARCHAR(100) | | 所属线路 |
| location | VARCHAR(500) | | 地理位置 |
| description | VARCHAR(500) | | 站点描述 |
| enabled | BIT | DEFAULT 1 | 是否启用 |
| created_at | DATETIME | | 创建时间 |
| updated_at | DATETIME | | 更新时间 |

### 15. `gates` - 闸机信息表
**用途**: 存储闸机设备信息

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 主键ID |
| station_id | BIGINT | NOT NULL | 所属站点ID |
| gate_code | VARCHAR(50) | | 闸机编码 |
| gate_name | VARCHAR(255) | | 闸机名称 |
| direction | VARCHAR(50) | | 方向(ENTRY/EXIT) |
| location | VARCHAR(500) | | 具体位置 |
| description | VARCHAR(500) | | 闸机描述 |
| enabled | BIT | DEFAULT 1 | 是否启用 |
| status | VARCHAR(50) | | 状态(ONLINE/OFFLINE/MAINTENANCE) |
| created_at | DATETIME | | 创建时间 |
| updated_at | DATETIME | | 更新时间 |

### 16. `gate_events` - 闸机事件记录表
**用途**: 记录闸机扫码事件

| 字段名 | 类型 | 约束 | 描述 |
|--------|------|------|------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | 主键ID |
| gate_id | BIGINT | | 闸机ID |
| gate_code | VARCHAR(50) | | 闸机编码 |
| user_id | BIGINT | | 用户ID |
| username | VARCHAR(255) | | 用户名 |
| event_type | VARCHAR(50) | | 事件类型(ENTRY/EXIT/ERROR) |
| qr_code | VARCHAR(500) | | 扫描的二维码内容 |
| fare | DECIMAL(8,2) | | 费用 |
| status | VARCHAR(50) | | 状态(SUCCESS/FAILED/PENDING) |
| error_code | VARCHAR(100) | | 错误代码 |
| error_message | VARCHAR(500) | | 错误信息 |
| event_time | DATETIME | | 事件时间 |
| processed_time | DATETIME | | 处理时间 |
| transaction_id | VARCHAR(100) | | 交易ID |
| remark | VARCHAR(500) | | 备注 |

---

## 🔗 表关系说明

### 主要关系
1. **用户关系**:
   - `fintech_users` ↔ `bank_card` (一对多)
   - `fintech_users` ↔ `bill` (一对多)
   - `fintech_users` ↔ `user_audit` (一对一)

2. **站点关系**:
   - `stations` ↔ `gates` (一对多)
   - `gates` ↔ `gate_events` (一对多)

3. **管理关系**:
   - `admin_users` ↔ `admin_operation_logs` (一对多)

### 业务逻辑关系
- 用户通过 `fintech_users` 表进行金融科技业务
- 管理员通过 `admin_users` 表进行后台管理
- 闸机扫码通过 `gate_events` 表记录事件
- 折扣策略通过 `discount_policies` 表配置

---

## 📊 索引建议

### 建议创建的索引
```sql
-- 用户相关索引
CREATE INDEX idx_fintech_users_username ON fintech_users(username);
CREATE INDEX idx_fintech_users_phone ON fintech_users(phone);
CREATE INDEX idx_bank_card_user_id ON bank_card(user_id);
CREATE INDEX idx_bill_user_id ON bill(user_id);

-- 管理员相关索引
CREATE INDEX idx_admin_users_username ON admin_users(username);
CREATE INDEX idx_admin_logs_admin_id ON admin_operation_logs(admin_id);
CREATE INDEX idx_admin_logs_operation_time ON admin_operation_logs(operation_time);

-- 站点相关索引
CREATE INDEX idx_stations_station_code ON stations(station_code);
CREATE INDEX idx_gates_station_id ON gates(station_id);
CREATE INDEX idx_gates_gate_code ON gates(gate_code);
CREATE INDEX idx_gate_events_gate_id ON gate_events(gate_id);
CREATE INDEX idx_gate_events_user_id ON gate_events(user_id);
CREATE INDEX idx_gate_events_event_time ON gate_events(event_time);
```

---

## 🎯 总结

### 数据库特点
- **模块化设计**: 4大模块，15个表，结构清晰
- **业务完整**: 覆盖支付、金融、管理、站点全流程
- **扩展性强**: 预留字段支持未来功能扩展
- **性能优化**: 合理的索引设计和关系映射

### 数据一致性
- 所有表都有主键和必要的约束
- 时间戳字段记录操作时间
- 状态字段支持业务状态管理
- 外键关系保证数据完整性

**文档版本**: v1.0  
**最后更新**: 2025/11/30
