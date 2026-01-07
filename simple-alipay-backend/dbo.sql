/*
 Navicat Premium Dump SQL

 Source Server         : MSSQLSEVER
 Source Server Type    : SQL Server
 Source Server Version : 16001160 (16.00.1160)
 Source Host           : hewendi:1433
 Source Catalog        : hewendi
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 16001160 (16.00.1160)
 File Encoding         : 65001

 Date: 18/12/2025 15:55:16
*/


-- ----------------------------
-- Table structure for admin_operation_logs
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[admin_operation_logs]') AND type IN ('U'))
	DROP TABLE [dbo].[admin_operation_logs]
GO

CREATE TABLE [dbo].[admin_operation_logs] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [admin_id] bigint  NULL,
  [admin_username] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [error_message] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [ip_address] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [operation_details] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [operation_time] datetime2(6)  NULL,
  [operation_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [success] bit  NOT NULL,
  [target_id] bigint  NULL,
  [target_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[admin_operation_logs] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of admin_operation_logs
-- ----------------------------
SET IDENTITY_INSERT [dbo].[admin_operation_logs] ON
GO

INSERT INTO [dbo].[admin_operation_logs] ([id], [admin_id], [admin_username], [error_message], [ip_address], [operation_details], [operation_time], [operation_type], [success], [target_id], [target_type]) VALUES (N'9', N'10', N'admin', NULL, N'192.168.1.100', N'创建早鸟优惠策略', NULL, N'CREATE', N'1', N'1', N'DISCOUNT_POLICY')
GO

INSERT INTO [dbo].[admin_operation_logs] ([id], [admin_id], [admin_username], [error_message], [ip_address], [operation_details], [operation_time], [operation_type], [success], [target_id], [target_type]) VALUES (N'10', N'10', N'admin', NULL, N'192.168.1.100', N'更新用户赵六的信息', NULL, N'UPDATE', N'1', N'4', N'USER')
GO

INSERT INTO [dbo].[admin_operation_logs] ([id], [admin_id], [admin_username], [error_message], [ip_address], [operation_details], [operation_time], [operation_type], [success], [target_id], [target_type]) VALUES (N'11', N'11', N'manager1', NULL, N'192.168.1.101', N'创建学生优惠策略', NULL, N'CREATE', N'1', N'2', N'DISCOUNT_POLICY')
GO

INSERT INTO [dbo].[admin_operation_logs] ([id], [admin_id], [admin_username], [error_message], [ip_address], [operation_details], [operation_time], [operation_type], [success], [target_id], [target_type]) VALUES (N'12', N'12', N'manager2', N'站点正在使用中，无法删除', N'192.168.1.102', N'删除测试站点', NULL, N'DELETE', N'0', N'3', N'STATION')
GO

INSERT INTO [dbo].[admin_operation_logs] ([id], [admin_id], [admin_username], [error_message], [ip_address], [operation_details], [operation_time], [operation_type], [success], [target_id], [target_type]) VALUES (N'13', N'13', N'master', NULL, N'127.0.0.1', N'disable policy', N'2025-12-04 19:45:53.695885', N'POLICY_DISABLE', N'1', N'1', N'POLICY')
GO

INSERT INTO [dbo].[admin_operation_logs] ([id], [admin_id], [admin_username], [error_message], [ip_address], [operation_details], [operation_time], [operation_type], [success], [target_id], [target_type]) VALUES (N'14', N'13', N'master', NULL, N'127.0.0.1', N'enable policy', N'2025-12-04 19:45:54.741862', N'POLICY_ENABLE', N'1', N'1', N'POLICY')
GO

INSERT INTO [dbo].[admin_operation_logs] ([id], [admin_id], [admin_username], [error_message], [ip_address], [operation_details], [operation_time], [operation_type], [success], [target_id], [target_type]) VALUES (N'15', NULL, N'hewendi', NULL, N'127.0.0.1', N'set pay password', N'2025-12-04 20:35:24.721580', N'USER_SET_PAY_PASSWORD', N'1', N'8', N'USER')
GO

INSERT INTO [dbo].[admin_operation_logs] ([id], [admin_id], [admin_username], [error_message], [ip_address], [operation_details], [operation_time], [operation_type], [success], [target_id], [target_type]) VALUES (N'16', N'13', N'master', NULL, N'127.0.0.1', N'disable user', N'2025-12-04 20:51:59.664780', N'USER_DISABLE', N'1', N'10', N'USER')
GO

INSERT INTO [dbo].[admin_operation_logs] ([id], [admin_id], [admin_username], [error_message], [ip_address], [operation_details], [operation_time], [operation_type], [success], [target_id], [target_type]) VALUES (N'17', N'13', N'master', NULL, N'127.0.0.1', N'enable user', N'2025-12-04 20:52:09.044694', N'USER_ENABLE', N'1', N'10', N'USER')
GO

INSERT INTO [dbo].[admin_operation_logs] ([id], [admin_id], [admin_username], [error_message], [ip_address], [operation_details], [operation_time], [operation_type], [success], [target_id], [target_type]) VALUES (N'18', N'13', N'master', NULL, N'127.0.0.1', N'reject user audit: w', N'2025-12-04 20:52:41.985987', N'USER_AUDIT_REJECT', N'1', N'3', N'USER')
GO

INSERT INTO [dbo].[admin_operation_logs] ([id], [admin_id], [admin_username], [error_message], [ip_address], [operation_details], [operation_time], [operation_type], [success], [target_id], [target_type]) VALUES (N'19', N'13', N'master', NULL, N'127.0.0.1', N'approve user audit', N'2025-12-09 09:00:40.123832', N'USER_AUDIT_APPROVE', N'1', N'11', N'USER')
GO

SET IDENTITY_INSERT [dbo].[admin_operation_logs] OFF
GO


-- ----------------------------
-- Table structure for admin_users
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[admin_users]') AND type IN ('U'))
	DROP TABLE [dbo].[admin_users]
GO

CREATE TABLE [dbo].[admin_users] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [enabled] bit  NOT NULL,
  [password] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [role] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [username] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL
)
GO

ALTER TABLE [dbo].[admin_users] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of admin_users
-- ----------------------------
SET IDENTITY_INSERT [dbo].[admin_users] ON
GO

INSERT INTO [dbo].[admin_users] ([id], [enabled], [password], [role], [username]) VALUES (N'10', N'1', N'$2a$10$AdminPass', N'SUPER_ADMIN', N'admin')
GO

INSERT INTO [dbo].[admin_users] ([id], [enabled], [password], [role], [username]) VALUES (N'11', N'1', N'$2a$10$Manager1', N'SITE_MANAGER', N'manager1')
GO

INSERT INTO [dbo].[admin_users] ([id], [enabled], [password], [role], [username]) VALUES (N'12', N'1', N'$2a$10$Manager2', N'SITE_MANAGER', N'manager2')
GO

INSERT INTO [dbo].[admin_users] ([id], [enabled], [password], [role], [username]) VALUES (N'13', N'1', N'$2a$10$84xOFV3U2sPCPXo1kdD9aOR6GDS2UcLOR8sklwDjqGvzCm.V/3Y0u', N'SUPER_ADMIN', N'master')
GO

INSERT INTO [dbo].[admin_users] ([id], [enabled], [password], [role], [username]) VALUES (N'14', N'1', N'$2a$10$Qe2gk9WOOSOWz5EV2QPCwuokDr1lJYjgYRhuT02SbL2PpMwKcin7e', N'SUPER_ADMIN', N'hewendi')
GO

SET IDENTITY_INSERT [dbo].[admin_users] OFF
GO


-- ----------------------------
-- Table structure for assistant_message
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[assistant_message]') AND type IN ('U'))
	DROP TABLE [dbo].[assistant_message]
GO

CREATE TABLE [dbo].[assistant_message] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [username] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [content] nvarchar(max) COLLATE Chinese_PRC_CI_AS  NULL,
  [role] nvarchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [created_at] datetime2(7) DEFAULT getdate() NULL
)
GO

ALTER TABLE [dbo].[assistant_message] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of assistant_message
-- ----------------------------
SET IDENTITY_INSERT [dbo].[assistant_message] ON
GO

INSERT INTO [dbo].[assistant_message] ([id], [username], [content], [role], [created_at]) VALUES (N'1', N'alice', N'你好，这个月我的交通花费是多少？', N'user', N'2023-12-01 10:00:00.0000000')
GO

INSERT INTO [dbo].[assistant_message] ([id], [username], [content], [role], [created_at]) VALUES (N'2', N'alice', N'根据您的记录，您本月交通花费为80元。', N'assistant', N'2023-12-01 10:01:00.0000000')
GO

SET IDENTITY_INSERT [dbo].[assistant_message] OFF
GO


-- ----------------------------
-- Table structure for assistant_messages
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[assistant_messages]') AND type IN ('U'))
	DROP TABLE [dbo].[assistant_messages]
GO

CREATE TABLE [dbo].[assistant_messages] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [bot_reply] varchar(1000) COLLATE Chinese_PRC_CI_AS  NULL,
  [created_at] datetime2(6)  NULL,
  [user_content] varchar(1000) COLLATE Chinese_PRC_CI_AS  NULL,
  [username] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[assistant_messages] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of assistant_messages
-- ----------------------------
SET IDENTITY_INSERT [dbo].[assistant_messages] ON
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'1', N'根据您的本月数据，您目前总支出为0元，交易笔数也是0笔，暂时没有需要分析的消费记录哦。建议您继续保持合理消费习惯，如有具体交易数据后我可以帮您进一步分析~', N'2025-11-25 20:18:13.642535', N'我这月花得有点多，帮我分析下原因', N'alice')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'2', N'我专注于帮助您管理财务问题，目前无法提供关于其他工具使用情况的信息。如果您有本月支出、预算规划或其他财务相关的问题，我很乐意为您解答！??', N'2025-11-25 20:19:18.790026', N'现在是否在使用deepseek', N'alice')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'3', N'您好，我是您的财务助手，目前专注于为您提供本月支出、交易情况和财务健康建议。如果您有关于预算、消费记录或其他财务方面的问题，我很乐意帮您解答！??', N'2025-12-03 20:06:23.911399', N'1+1', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'4', N'根据您本月的财务数据，目前支出为0元，交易笔数为0笔，整体消费保持在合理范围内，近期也没有大额异常支出。财务情况非常健康，继续保持哦！', N'2025-12-03 20:06:47.106622', N'summarize the situation', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'5', N'我是您的财务助手，可以帮您查询本月支出、交易笔数，提供财务健康建议，并提醒异常支出。目前您的本月数据均为0，消费保持在合理范围内。有什么具体想了解的吗？', N'2025-12-03 20:07:02.518551', N'what can you do', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'6', N'您好，我是您的财务助手。根据本月数据，您目前没有支出记录，消费情况良好。如有财务问题，请随时告诉我。', N'2025-12-03 20:09:51.283969', N'huidaw', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'7', N'你好！我是你的财务助手，很高兴为你服务。有什么财务问题需要我帮忙吗？', N'2025-12-03 20:17:03.294638', N'你好啊', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'8', N'我是你的财务助手，可以帮你查看本月支出、交易笔数，并提供财务健康建议。目前你的本月总支出是0元，交易笔数为0笔，消费保持在合理范围内。有什么具体想了解的吗？??', N'2025-12-03 20:17:16.796277', N'你有什么功能', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'9', N'您好！我是您的财务助手，目前专注于为您提供本月的财务数据分析与建议。关于数学计算的问题，建议您使用计算工具或咨询相关专业人士哦。如果您有财务方面的疑问，我很乐意为您解答！ ??', N'2025-12-03 20:17:30.528441', N'请问1 1=多少', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'10', N'您好！我是您的财务助手，目前您的本月支出为0元，交易笔数为0笔，消费保持在合理范围内。如果您有关于财务的问题，我很乐意为您解答！ ??', N'2025-12-03 20:19:34.332893', N'1+1', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'11', N'你好！我是你的财务助手，随时为你提供本月支出和交易情况的查询帮助。有什么想了解的吗？', N'2025-12-03 20:19:45.859696', N'nihao', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'12', N'您好，我是您的财务助手，目前专注于为您提供财务数据分析和建议。如果您有关于本月支出、预算规划或其他财务相关的问题，我很乐意为您解答！ ??', N'2025-12-03 20:23:07.646105', N'1+1', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'13', N'你好！我是你的财务助手，目前看到你本月的支出为0元，交易笔数也是0笔。消费保持在合理范围内，近期没有大额异常支出。有什么财务问题需要我帮忙吗？', N'2025-12-03 20:23:26.154040', N'nihao', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'14', N'您好，我是您的财务助手，目前看到您本月还没有任何支出记录。如果您有关于预算、支出分类或财务规划方面的问题，我很乐意为您提供帮助哦！ ??', N'2025-12-03 20:26:16.383794', N'1+1', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'15', N'您好！本月您的总支出为0元，交易笔数为0笔，财务健康建议是消费保持在合理范围内，近期无大额异常支出。有什么我可以帮您进一步了解的吗？', N'2025-12-03 20:32:56.659536', N'1', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'16', N'您好！我是您的财务助手，目前您的本月支出为0元，交易笔数为0笔，消费保持在合理范围内。如果您有关于财务的问题，我很乐意为您解答哦！ ??', N'2025-12-03 20:40:49.572558', N'1+1', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'17', N'您好！根据您的财务数据，本月总支出为0元，交易笔数为0笔，消费保持在合理范围内，近期无大额异常支出。如果您有其他财务相关问题，我很乐意为您解答！', N'2025-12-03 20:45:32.292430', N'131+', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'18', N'我是您的财务助手，可以帮您查看本月支出、交易笔数，并提供财务健康建议。您本月暂无消费记录，目前财务状况良好哦！有其他财务问题可以随时问我～', N'2025-12-03 20:46:04.414345', N'what can you do', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'19', N'你好！我是你的财务助手，很高兴为你服务。本月你的总支出为2.00元，共2笔交易，消费保持在合理范围内，近期也没有大额异常支出。有什么我可以帮你的吗？', N'2025-12-03 21:23:00.737693', N'你好', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'20', N'根据您本月的财务数据，总支出为 **5.00元**，共 **5笔** 交易，消费保持在合理范围内，近期也没有大额异常支出。整体来看，您的财务状况非常健康，继续保持哦！ ??', N'2025-12-03 21:26:40.346049', N'总结', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'21', N'本月您共有5笔支出，总金额为5.00元，消费保持在合理范围内，近期没有大额异常支出。整体来看，您的财务状况很健康哦！', N'2025-12-03 21:26:58.689312', N'收支情况', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'22', N'本月您共支出 **5.00元**，交易笔数为 **5笔**，整体消费保持在合理范围内，没有大额异常支出，财务情况健康稳定 ??', N'2025-12-03 21:27:06.978190', N'收支情况', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'23', N'本月您共支出 **5.00元**，交易笔数为 **5笔**，消费保持在合理范围内，近期也没有大额异常支出。整体来看，您的财务状况很健康哦！ ??', N'2025-12-03 21:33:46.287699', N'收支情况', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'24', N'你好，hewendi！根据你本月的财务数据来看，你的总支出为28.00元，共28笔交易，整体消费保持在合理范围内，近期也没有大额异常支出。继续保持这样的消费习惯，财务状况很健康哦！', N'2025-12-04 21:51:50.165832', N'analysis my account', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'25', N'您本月的总支出是29.00元，共29笔交易。消费情况良好，保持在合理范围内，没有大额异常支出哦！', N'2025-12-08 17:08:13.631307', N'what the cost this month', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'26', N'你好！我是你的财务助手，很高兴为你服务。有什么关于本月财务的问题需要我帮忙解答吗？', N'2025-12-08 18:57:37.743004', N'nihao', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'27', N'本月你的总支出为30.00元，共进行了30笔交易，消费保持在合理范围内，近期也没有大额异常支出。继续保持哦！', N'2025-12-08 18:57:57.063811', N'zongjie', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'28', N'本月你的总支出为30.00元，共进行了30笔交易，消费情况保持在合理范围内，没有发现大额异常支出。继续保持哦！', N'2025-12-08 19:08:23.431730', N'zongjie', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'29', N'根据您本月的财务数据，本月总支出为0元，交易笔数为0笔。消费保持在合理范围内，近期无大额异常支出。继续保持良好的财务习惯哦！', N'2025-12-08 19:12:13.135926', N'zongjie', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'30', N'根据您本月的财务数据，本月总支出为0元，交易笔数为0笔。消费保持在合理范围内，近期无大额异常支出。继续保持良好的消费习惯哦！', N'2025-12-08 19:14:23.110566', N'zongjie', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'31', N'您好！根据您本月的财务数据，目前收入情况暂无记录。如果您需要了解收入详情或有其他财务问题，请随时告诉我，我会尽力为您提供帮助！ ??', N'2025-12-08 19:14:33.142973', N'shou ru zongjie', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'32', N'根据您本月的财务数据，您目前的总支出为0元，交易笔数为0笔，消费保持在合理范围内，近期也没有大额异常支出。继续保持良好的财务习惯哦！', N'2025-12-08 19:16:01.329376', N'zongjie', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'33', N'您好！根据您本月的财务数据，目前收入情况暂无记录。如果您想了解收入详情，可以随时告诉我，我会帮您整理分析哦！', N'2025-12-08 19:16:11.118816', N'shou ru', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'34', N'您好！目前您的财务数据如下：  
- 本月总支出：0元  
- 本月交易笔数：0笔  
- 财务健康建议：消费保持在合理范围内。  
- 近期无大额异常支出。  

如果您有其他财务相关问题，我很乐意帮您解答！??', N'2025-12-08 19:16:24.654858', N'suo you shi jian de', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'35', N'您好！本月您的支出为0元，交易笔数为0笔，消费保持在合理范围内，近期无大额异常支出。', N'2025-12-08 19:16:35.311592', N'zhen nian de', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'36', N'本月您的财务状况良好，总支出为0元，总收入为2.00元，交易笔数共2笔。消费保持在合理范围内，近期无大额异常支出。继续保持哦！', N'2025-12-08 19:22:10.001531', N'zongjie', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'37', N'本月您的总收入为 **2.00元**，目前支出为0元，财务情况良好。如有其他问题，欢迎随时问我哦！', N'2025-12-08 19:22:33.431371', N'shou ru', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'38', N'本月您的总支出为0元，消费保持在合理范围内，近期也没有大额异常支出。继续保持哦！', N'2025-12-08 19:22:40.043338', N'zhichu', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'39', N'您本月的总支出为0元，消费情况非常健康。继续保持哦！', N'2025-12-08 19:23:06.380755', N'zhichu', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'40', N'本月您的总收入为 **2.00元**，目前支出为0元，财务情况良好。', N'2025-12-08 19:23:17.570994', N'shou ru', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'41', N'本月总收入为 2.00 元，目前交易笔数为 2 笔。', N'2025-12-08 19:23:57.978247', N'suo you de shou ru', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'42', N'根据您的财务数据，本月总支出为 **0元**，目前没有记录到任何消费支出。', N'2025-12-08 19:24:06.845017', N'suo you de zhi chu', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'43', N'根据您的实时数据，本月总支出为 **0元**，总收入为 **2.00元**，交易笔数为 **2笔**。目前消费保持在合理范围内，没有大额异常支出。', N'2025-12-08 19:24:16.163608', N'all time cost', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'44', N'根据你的账单汇总，本月你共有2笔交易，总收入为2.00元，总支出为0元。目前你的消费保持在合理范围内，没有大额异常支出，财务状况良好。继续保持哦！', N'2025-12-08 19:27:05.003037', N'zongjie', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'45', N'您本月的总支出为0元，消费保持在合理范围内，近期也没有大额异常支出。继续保持良好的财务管理习惯吧！', N'2025-12-08 19:27:12.740067', N'zhichu', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'46', N'根据你的账单汇总，本月总支出为0元，总收入为2.00元，共发生2笔交易。目前消费保持在合理范围内，近期没有大额异常支出。整体财务状况良好，继续保持哦！', N'2025-12-08 19:31:24.987986', N'zongjie', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'47', N'根据您的账单汇总，目前总支出为0元，总收入为7.00元，本月共有3笔交易。整体来看，您的消费保持在合理范围内，近期没有大额异常支出，财务状况良好。', N'2025-12-08 19:33:11.617716', N'zongjie', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'48', N'根据你的账单汇总，本月你共有4笔交易，总收入为17.00元，总支出为0元。财务健康方面，消费保持在合理范围内，近期无大额异常支出。继续保持哦！', N'2025-12-08 19:39:58.661382', N'zongjie', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'49', N'根据你的账单数据，本月总收入为27.00元，总支出为0元，共发生5笔交易。目前消费保持在合理范围内，没有大额异常支出，财务状况良好。继续保持哦！', N'2025-12-08 19:41:51.821672', N'zongjie', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'50', N'根据您的账单汇总，目前总支出为0元，总收入为27.00元，本月共有5笔交易。整体消费保持在合理范围内，近期没有大额异常支出，财务状况良好。继续保持哦！', N'2025-12-08 19:51:31.354503', N'zongjie', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'51', N'根据你的账单数据，本月总收入为27.00元，总支出为0元，共发生5笔交易。目前你的消费保持在合理范围内，没有大额异常支出，财务状况良好。继续保持哦！', N'2025-12-08 20:00:36.587776', N'zongjie', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'52', N'你好，hewendi！根据你的账单汇总：

- 本月总收入 **27.00元**，总支出 **2.00元**，结余 **25.00元**。
- 本月共 **7笔** 交易，消费控制得很好，没有大额异常支出。
- 整体财务健康，继续保持合理消费习惯哦！ ??

有什么具体想了解的吗？', N'2025-12-08 20:01:18.253280', N'zongjie', N'hewendi')
GO

INSERT INTO [dbo].[assistant_messages] ([id], [bot_reply], [created_at], [user_content], [username]) VALUES (N'53', N'根据您的账单汇总，本月总支出为4.00元，总收入为27.00元，交易笔数共9笔。您的消费保持在合理范围内，近期无大额异常支出，财务状况健康。', N'2025-12-09 09:03:32.172464', N'zong jie', N'hewendi')
GO

SET IDENTITY_INSERT [dbo].[assistant_messages] OFF
GO


-- ----------------------------
-- Table structure for bank_card
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[bank_card]') AND type IN ('U'))
	DROP TABLE [dbo].[bank_card]
GO

CREATE TABLE [dbo].[bank_card] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [bank_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [card_number] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [is_default] bit  NULL,
  [user_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[bank_card] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of bank_card
-- ----------------------------
SET IDENTITY_INSERT [dbo].[bank_card] ON
GO

INSERT INTO [dbo].[bank_card] ([id], [bank_name], [card_number], [is_default], [user_id]) VALUES (N'1', N'招商银行', N'6225880123456789', N'1', N'3')
GO

INSERT INTO [dbo].[bank_card] ([id], [bank_name], [card_number], [is_default], [user_id]) VALUES (N'2', N'中国工商银行', N'6222020123456789', N'0', N'3')
GO

INSERT INTO [dbo].[bank_card] ([id], [bank_name], [card_number], [is_default], [user_id]) VALUES (N'3', N'中国建设银行', N'6217001234567890', N'1', N'4')
GO

INSERT INTO [dbo].[bank_card] ([id], [bank_name], [card_number], [is_default], [user_id]) VALUES (N'4', N'交通银行', N'6222600123456789', N'0', N'5')
GO

INSERT INTO [dbo].[bank_card] ([id], [bank_name], [card_number], [is_default], [user_id]) VALUES (N'5', N'何文迪银行', N'1234123412341234', N'1', N'8')
GO

SET IDENTITY_INSERT [dbo].[bank_card] OFF
GO


-- ----------------------------
-- Table structure for bill
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[bill]') AND type IN ('U'))
	DROP TABLE [dbo].[bill]
GO

CREATE TABLE [dbo].[bill] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [amount] numeric(19,2)  NULL,
  [category] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [remark] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [transaction_time] datetime2(6)  NULL,
  [type] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [user_id] bigint  NULL,
  [source_ref_id] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [source_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[bill] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of bill
-- ----------------------------
SET IDENTITY_INSERT [dbo].[bill] ON
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'1', N'50.00', N'餐饮', N'午餐', N'2023-12-01 12:30:00.000000', N'支出', N'3', NULL, NULL)
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'2', N'200.00', N'购物', N'衣服', N'2023-12-01 18:00:00.000000', N'支出', N'4', NULL, NULL)
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'3', N'1000.00', N'工资', N'11月工资', N'2023-12-02 09:00:00.000000', N'收入', N'3', NULL, NULL)
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'4', N'30.00', N'交通', N'地铁', N'2023-12-02 15:45:00.000000', N'支出', N'5', NULL, NULL)
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'5', N'1.00', N'支付', N'支付给:hewendi', N'2025-12-04 21:59:51.791852', N'EXPENDITURE', N'10', N'PAY:39', N'PAYMENT')
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'6', N'1.00', N'收款', N'来自:demo_payer', N'2025-12-04 21:59:51.805426', N'INCOME', N'8', N'PAY:39', N'PAYMENT')
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'7', N'4.50', N'交通', N'出行扣费', N'2025-12-08 18:08:03.945233', N'expense', N'8', NULL, NULL)
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'8', N'1.00', N'收款', N'收款码模拟收款(入账至余额)', N'2025-12-08 18:51:04.434876', N'income', N'8', NULL, NULL)
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'9', N'5.00', N'收款', N'收款码模拟收款(入账至余额)', N'2025-12-08 19:32:18.670397', N'income', N'8', NULL, NULL)
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'10', N'10.00', N'收款', N'收款码模拟收款(入账至余额)', N'2025-12-08 19:33:31.755126', N'income', N'8', NULL, NULL)
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'11', N'10.00', N'收款', N'收款码模拟收款(入账至余额)', N'2025-12-08 19:40:15.004317', N'income', N'8', NULL, NULL)
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'12', N'4.50', N'交通', N'出行扣费', N'2025-12-08 19:41:33.768851', N'expense', N'8', NULL, NULL)
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'13', N'1.00', N'支付', N'支付给:demo_merchant', N'2025-12-08 20:00:55.188755', N'EXPENDITURE', N'8', N'PAY:47', N'PAYMENT')
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'14', N'1.00', N'支付', N'支付给:demo_merchant', N'2025-12-08 20:00:55.188755', N'EXPENDITURE', N'8', N'PAY:48', N'PAYMENT')
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'15', N'1.00', N'支付', N'支付给:demo_merchant', N'2025-12-09 09:01:57.262095', N'EXPENDITURE', N'8', N'PAY:49', N'PAYMENT')
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'16', N'1.00', N'支付', N'支付给:demo_merchant', N'2025-12-09 09:02:35.477544', N'EXPENDITURE', N'8', N'PAY:50', N'PAYMENT')
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'17', N'4.50', N'交通', N'出行扣费', N'2025-12-09 09:05:08.435586', N'expense', N'8', NULL, NULL)
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'18', N'4.50', N'交通', N'出行扣费', N'2025-12-09 09:06:10.372910', N'expense', N'8', NULL, NULL)
GO

INSERT INTO [dbo].[bill] ([id], [amount], [category], [remark], [transaction_time], [type], [user_id], [source_ref_id], [source_type]) VALUES (N'19', N'10.00', N'收款', N'收款码模拟收款(入账至余额)', N'2025-12-09 09:06:34.187678', N'income', N'8', NULL, NULL)
GO

SET IDENTITY_INSERT [dbo].[bill] OFF
GO


-- ----------------------------
-- Table structure for collection_qr_code
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[collection_qr_code]') AND type IN ('U'))
	DROP TABLE [dbo].[collection_qr_code]
GO

CREATE TABLE [dbo].[collection_qr_code] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [merchant_id] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [qr_code_content] nvarchar(max) COLLATE Chinese_PRC_CI_AS  NULL,
  [valid_seconds] int  NULL,
  [created_at] datetime2(7) DEFAULT getdate() NULL,
  [expires_at] datetime2(7)  NULL
)
GO

ALTER TABLE [dbo].[collection_qr_code] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of collection_qr_code
-- ----------------------------
SET IDENTITY_INSERT [dbo].[collection_qr_code] ON
GO

INSERT INTO [dbo].[collection_qr_code] ([id], [merchant_id], [qr_code_content], [valid_seconds], [created_at], [expires_at]) VALUES (N'9', N'starbucks001', N'https://alipay.com/qr/starbucks001/20251110123456', N'3600', N'2025-12-03 15:14:49.9400000', NULL)
GO

INSERT INTO [dbo].[collection_qr_code] ([id], [merchant_id], [qr_code_content], [valid_seconds], [created_at], [expires_at]) VALUES (N'10', N'walmart002', N'https://alipay.com/qr/walmart002/20251111144530', N'7200', N'2025-12-03 15:14:49.9400000', NULL)
GO

INSERT INTO [dbo].[collection_qr_code] ([id], [merchant_id], [qr_code_content], [valid_seconds], [created_at], [expires_at]) VALUES (N'11', N'didi003', N'https://alipay.com/qr/didi003/20251112181500', N'1800', N'2025-12-03 15:14:49.9400000', NULL)
GO

INSERT INTO [dbo].[collection_qr_code] ([id], [merchant_id], [qr_code_content], [valid_seconds], [created_at], [expires_at]) VALUES (N'12', N'meituan004', N'https://alipay.com/qr/meituan004/20251113121000', N'5400', N'2025-12-03 15:14:49.9400000', NULL)
GO

SET IDENTITY_INSERT [dbo].[collection_qr_code] OFF
GO


-- ----------------------------
-- Table structure for collectionqrcode
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[collectionqrcode]') AND type IN ('U'))
	DROP TABLE [dbo].[collectionqrcode]
GO

CREATE TABLE [dbo].[collectionqrcode] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [active] bit  NOT NULL,
  [content] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [expire_at] datetime2(6)  NULL,
  [merchant_id] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[collectionqrcode] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of collectionqrcode
-- ----------------------------
SET IDENTITY_INSERT [dbo].[collectionqrcode] ON
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'1', N'1', N'collect://hewendi/a1c12ebe-884b-4516-a863-d25aa6b7d892', N'2025-11-30 15:52:38.503704', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'2', N'1', N'collect://hewendi/9580c5ae-9b1d-4579-87a2-05a5e0657d5f', N'2025-11-30 15:52:38.530991', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'3', N'1', N'collect://hewendi/5a2ea7d3-b476-4f8a-8397-a63851a8e9f7', N'2025-11-30 15:52:41.157678', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'4', N'1', N'collect://hewendi/bd9fe71d-4150-4200-b1f9-fbe262a0f3b3', N'2025-11-30 15:52:43.022126', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'5', N'1', N'collect://hewendi/772fd941-28e5-4d8f-8184-11b70203c4d4', N'2025-11-30 15:52:43.366920', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'6', N'1', N'collect://hewendi/bef6d72e-8e99-497f-9e92-1158f91f7e1c', N'2025-11-30 15:52:43.516759', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'7', N'1', N'collect://hewendi/44bb1087-12be-4315-a509-2cf1b57ecfb9', N'2025-11-30 15:52:48.765682', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'8', N'1', N'collect://hewendi/4afb7b2a-8221-4801-bb59-0d427e6c57fc', N'2025-11-30 15:52:48.793863', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'9', N'1', N'collect://hewendi/69cfcad7-b852-4349-b43a-a8794ea3c73b', N'2025-11-30 15:52:50.820577', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'10', N'1', N'collect://hewendi/774de4b8-54b5-4a14-b486-c163d5c7940c', N'2025-11-30 15:52:51.315796', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'11', N'1', N'collect://hewendi/77584749-38ef-4ff9-98bd-0fe9150df36d', N'2025-11-30 15:52:51.768603', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'12', N'1', N'collect://hewendi/07a2e9d9-2646-4a38-9ec6-be0005be3475', N'2025-11-30 15:52:58.430468', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'13', N'1', N'collect://hewendi/0b3c288e-65a1-40c8-b781-39f46bec49c2', N'2025-11-30 15:53:43.147006', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'14', N'1', N'collect://hewendi/2768787d-9043-4f77-bd1d-fcbbb66ce05f', N'2025-11-30 15:53:43.169731', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'15', N'1', N'collect://hewendi/1e55decb-b9a0-40cb-a6ed-ed282aed57e6', N'2025-11-30 15:53:45.973949', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'16', N'1', N'collect://hewendi/5ce6cf94-7386-48db-b365-4349a8a94df0', N'2025-11-30 15:54:15.338199', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'17', N'1', N'collect://hewendi/4d6a9336-80ae-4866-b694-4be68ab3211f', N'2025-11-30 15:54:16.060860', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'18', N'1', N'collect://hewendi/90bb7c9f-4526-42c1-adf1-4ecf7bb918c7', N'2025-11-30 15:54:19.987086', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'19', N'1', N'collect://hewendi/47c7b19b-ea25-4d80-b265-f42acb9f9994', N'2025-11-30 15:54:27.968755', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'20', N'1', N'collect://hewendi/8ea16490-494b-4908-bdb6-fc71e674347c', N'2025-11-30 15:54:27.993879', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'21', N'1', N'collect://hewendi/21dc4a82-bd8f-48e8-8dbb-58ab8057d2ae', N'2025-12-03 19:52:19.448895', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'22', N'1', N'collect://hewendi/0dd8e01b-f337-40c3-b674-d84d31fc81fa', N'2025-12-03 19:52:19.485496', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'23', N'1', N'collect://hewendi/db972a81-fde6-4f90-a24d-e2f555f2ac60', N'2025-12-03 19:52:21.788159', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'24', N'1', N'collect://hewendi/09eb83f8-6652-4090-bf1f-acaba362cbdc', N'2025-12-03 19:52:22.403792', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'25', N'1', N'collect://hewendi/6a875bdd-f4e3-4ba9-81f9-4287ae6283e4', N'2025-12-03 19:52:22.804600', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'26', N'1', N'collect://hewendi/7c5b0e8b-c232-41a2-bea7-e76c88d2cc25', N'2025-12-03 19:52:23.169303', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'27', N'1', N'collect://hewendi/3611905a-893f-40df-b91d-aaf103bab6f3', N'2025-12-03 20:03:44.751201', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'28', N'1', N'collect://hewendi/ce59d7da-63a9-42ce-9b92-2112034af9a8', N'2025-12-03 20:03:44.783214', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'29', N'1', N'collect://hewendi/bb0d74d5-12d2-4c54-8bbb-0eff8b93d8ee', N'2025-12-03 20:03:46.479831', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'30', N'1', N'collect://hewendi/7ff08678-cad9-45e7-82d0-f5157e84cbc4', N'2025-12-03 20:03:47.052287', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'31', N'1', N'collect://hewendi/056eb12c-26b8-4725-a799-103d6436da76', N'2025-12-03 20:03:47.283125', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'32', N'1', N'collect://hewendi/57e43b12-158c-4a77-899c-f6d25cb750cc', N'2025-12-03 20:03:47.469743', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'33', N'1', N'collect://hewendi/b9d52e54-b143-4483-b9ac-b3333edcc5b2', N'2025-12-03 20:35:52.510567', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'34', N'1', N'collect://hewendi/ac23e8f8-2044-4d4b-8c8a-e2f237e0dd68', N'2025-12-03 20:35:52.527351', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'35', N'1', N'collect://hewendi/54cb92ae-3e8e-43da-a741-4e8c051349df', N'2025-12-03 20:35:57.590971', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'36', N'1', N'collect://hewendi/b292bfc3-4c68-418c-9403-38c5737ed4c8', N'2025-12-03 20:35:58.178743', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'37', N'1', N'collect://hewendi/a212f61a-de91-48ee-912e-6ff425182e15', N'2025-12-03 20:35:58.921734', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'38', N'1', N'collect://hewendi/bd2bc702-9e1a-4c97-af30-bc9c16c7b439', N'2025-12-03 20:48:30.160339', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'39', N'1', N'collect://hewendi/cfc2eddd-d88d-4268-89a0-d7067886333b', N'2025-12-03 20:48:30.184750', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'40', N'1', N'collect://hewendi/5b336843-365a-4b20-811d-78385927af76', N'2025-12-03 20:48:31.703614', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'41', N'1', N'collect://hewendi/db71d5a1-99a5-47d8-b58d-f16a201a268b', N'2025-12-03 20:48:37.054523', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'42', N'1', N'collect://hewendi/2e28792a-fbc1-44cb-a3c5-b50408f27e64', N'2025-12-03 21:25:19.696874', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'43', N'1', N'collect://hewendi/db37827d-69fe-48c3-99d4-9965c550308c', N'2025-12-03 21:25:19.721039', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'44', N'1', N'collect://hewendi/7eec0bfb-687d-4147-95c4-41883f53aca1', N'2025-12-03 21:25:20.441756', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'45', N'1', N'collect://hewendi/b5b6d323-552e-4c0c-8445-f536f72a3726', N'2025-12-03 21:25:22.634814', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'46', N'1', N'collect://hewendi/ebaf90ef-0358-48fc-95aa-b82ddca0e54c', N'2025-12-03 21:25:22.851263', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'47', N'1', N'collect://hewendi/c7d25983-6c8e-4067-a9c0-8b214a67964c', N'2025-12-03 21:25:23.673634', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'48', N'1', N'collect://hewendi/78a74abd-9a3c-4b0a-bef8-adbd1368afac', N'2025-12-03 21:26:06.626742', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'49', N'1', N'collect://hewendi/b87e47d6-19bc-4250-a8c2-f156456045f6', N'2025-12-03 21:26:06.629323', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'50', N'1', N'collect://hewendi/2736d617-bf72-4658-b836-e1913558ab0a', N'2025-12-03 21:26:07.902408', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'51', N'1', N'collect://hewendi/974551d1-3c17-40dd-9389-39abf4ac8f8b', N'2025-12-03 21:27:21.450457', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'52', N'1', N'collect://hewendi/a17b46bb-95f3-4fda-b221-d85b8a332ef2', N'2025-12-03 21:27:21.450457', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'53', N'1', N'collect://hewendi/28fabd59-174a-48ab-8f1d-eb6260081d2d', N'2025-12-03 21:27:39.245207', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'54', N'1', N'collect://hewendi/0dd7734c-5fac-471c-aa31-6743b0359cf0', N'2025-12-03 21:27:39.835582', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'55', N'1', N'collect://hewendi/c97bce0d-476c-4ece-8810-7693793eda79', N'2025-12-03 21:35:59.637402', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'56', N'1', N'collect://hewendi/ec425545-43ed-49f0-bc61-9f92683232b2', N'2025-12-03 21:35:59.671821', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'57', N'1', N'collect://hewendi/0089bef4-b08e-402b-ba9d-c9226f52b9d9', N'2025-12-03 21:36:01.510657', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'58', N'1', N'collect://hewendi/aeff9dbd-0e56-4542-a06a-b00c28e0c027', N'2025-12-03 21:36:02.753227', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'59', N'1', N'collect://hewendi/dc81d1d3-61e5-4775-90be-c9de04b240fd', N'2025-12-03 21:40:40.616750', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'60', N'1', N'collect://hewendi/e13f413d-b3b4-4796-8abb-d82295470ce9', N'2025-12-03 21:40:40.661274', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'61', N'1', N'collect://hewendi/ef9a249e-378b-40eb-8cf6-fa0ac4a69075', N'2025-12-03 21:40:43.201199', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'62', N'1', N'collect://hewendi/95920769-bcdb-4af5-8b7f-0b21c73eaab6', N'2025-12-03 21:40:44.319669', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'63', N'1', N'collect://hewendi/433a8689-c82d-4c0d-ba03-ab0f61aa9be1', N'2025-12-03 21:40:46.433977', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'64', N'1', N'collect://hewendi/913213ee-f9cb-4d4f-9e7b-7c17e37422f7', N'2025-12-03 21:40:46.939307', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'65', N'1', N'collect://hewendi/50be6bb1-9b5e-46ba-bbd6-264c9d7817d3', N'2025-12-03 21:41:12.472515', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'66', N'1', N'collect://hewendi/6b1c3b36-47cf-4a8a-9dbf-eee1bb3f9907', N'2025-12-03 21:41:12.472515', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'67', N'1', N'collect://hewendi/899752d8-a363-4079-88fa-3f8e4e7b89fd', N'2025-12-03 21:56:26.863618', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'68', N'1', N'collect://hewendi/2335538b-2fa0-4d98-b1af-45f3f4eb29f8', N'2025-12-03 21:56:26.899312', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'69', N'1', N'collect://hewendi/0b81484f-07d3-4c97-8564-94fba504803b', N'2025-12-03 21:56:28.458129', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'70', N'1', N'collect://hewendi/99d93597-753e-4376-a566-c7da06fbbe47', N'2025-12-03 21:56:28.989112', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'71', N'1', N'collect://hewendi/7913b5c5-2093-498f-b2f0-af5f0944a597', N'2025-12-03 21:56:29.394700', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'72', N'1', N'collect://hewendi/523896a8-ee56-45a6-a992-bd839d9f2eb6', N'2025-12-03 21:56:29.519538', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'73', N'1', N'collect://hewendi/e0c07245-9ecf-413d-9960-76f7be3a6404', N'2025-12-03 21:56:30.310531', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'74', N'1', N'collect://hewendi/4d29c990-78e8-4fb5-bb58-c7f9811ea131', N'2025-12-03 21:56:30.823569', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'75', N'1', N'collect://hewendi/32026371-111a-455b-83ac-2fa5d6e835ae', N'2025-12-03 21:56:31.400100', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'76', N'1', N'collect://hewendi/4a8eba97-bd70-4ed8-b431-d3c4190fbbc0', N'2025-12-03 21:56:31.922935', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'77', N'1', N'collect://hewendi/40d63532-a027-45d4-b3cf-049274dc0299', N'2025-12-03 21:56:55.817180', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'78', N'1', N'collect://hewendi/d3c904f1-8c63-4e40-b4b6-19d7a780f702', N'2025-12-03 21:56:55.817180', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'79', N'1', N'collect://hewendi/05323946-e7d0-4657-8bae-a9731d35c919', N'2025-12-03 21:56:56.674654', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'80', N'1', N'collect://hewendi/62c342c8-09a7-4f24-a93d-f84bb74bccef', N'2025-12-03 22:01:39.659778', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'81', N'1', N'collect://hewendi/d799e850-ff71-4dbc-a101-25dd2e7a1e54', N'2025-12-03 22:01:39.660758', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'82', N'1', N'collect://hewendi/c3e6286b-3e8c-43e3-ba12-c2d22f08af1e', N'2025-12-03 22:01:40.594591', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'83', N'1', N'collect://hewendi/a8e1708a-0dec-4ad6-8caf-7d7d1dca1293', N'2025-12-03 22:01:41.321232', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'84', N'1', N'collect://hewendi/fb1ea2a8-3027-406d-9a4d-64cbd90decfd', N'2025-12-03 22:01:42.041357', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'85', N'1', N'collect://hewendi/0b71c26f-2d61-46f1-8c9a-747e330264bf', N'2025-12-03 22:01:42.403559', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'86', N'1', N'collect://hewendi/1ad043d3-0a43-4106-a7cb-a7318ea3ce23', N'2025-12-03 22:02:13.259783', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'87', N'1', N'collect://hewendi/5d716f0e-dbc2-4cfc-bce8-64ebe2255a7a', N'2025-12-03 22:02:13.954480', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'88', N'1', N'collect://bob/5bfade72-e4ff-426d-b676-da84baf90a21', N'2025-12-03 22:04:06.148172', N'bob')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'89', N'1', N'collect://hewendi/2f18e367-23ef-45aa-8663-75b78b4dea9f', N'2025-12-03 22:12:22.432214', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'90', N'1', N'collect://hewendi/f2cfb8ad-5aa7-41f1-b1e6-5935c1721ed4', N'2025-12-03 22:12:22.472604', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'91', N'1', N'collect://hewendi/7e4f1d92-1b04-45b6-89d9-271d22e69862', N'2025-12-03 22:12:24.078931', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'92', N'1', N'collect://hewendi/71467ea1-660a-4906-8104-a1f8031dd7f1', N'2025-12-04 20:12:17.959376', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'93', N'1', N'collect://hewendi/e507066b-a45d-418e-a568-335aeb6916ed', N'2025-12-04 20:12:18.015659', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'94', N'1', N'collect://hewendi/fb46a86e-a611-42e7-86e1-750afcd2deaa', N'2025-12-04 20:12:21.181246', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'95', N'1', N'collect://hewendi/92b7a078-faa9-4056-b876-ae025e7989e9', N'2025-12-04 20:12:23.369640', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'96', N'1', N'collect://hewendi/4a64e1d4-9a45-4b44-bf9b-c779f1e54c15', N'2025-12-04 20:13:44.831180', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'97', N'1', N'collect://hewendi/b0d61129-4ea7-4da9-9dd7-824303af30d8', N'2025-12-04 20:13:44.885531', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'98', N'1', N'collect://hewendi/0b901e48-4dd1-4b77-8baa-e8a59bdf4a11', N'2025-12-04 20:17:02.563684', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'99', N'1', N'collect://hewendi/5619c15d-a98a-477a-85ab-49194f3b0d8a', N'2025-12-04 20:17:02.598316', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'100', N'1', N'collect://hewendi/4a33a07f-1d3f-46f4-8479-1f469d82d996', N'2025-12-04 20:29:55.436883', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'101', N'1', N'collect://hewendi/23ec9a13-5c6b-4aa8-9609-19a21785c355', N'2025-12-04 20:29:55.468221', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'102', N'1', N'collect://hewendi/53b5458a-5785-408e-9f8b-d7e7bd499747', N'2025-12-04 20:37:58.004395', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'103', N'1', N'collect://hewendi/f1c667ad-9a9b-452c-b502-72be958b7151', N'2025-12-04 20:37:58.040079', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'104', N'1', N'collect://hewendi/9cda31de-c3c7-4629-90bc-a75d607cec54', N'2025-12-04 20:37:59.373007', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'105', N'1', N'collect://hewendi/c552f471-7a25-4494-aa7d-4bc1d9437444', N'2025-12-04 20:40:49.047481', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'106', N'1', N'collect://hewendi/9ff30e80-20b8-4c23-8b97-26d0098be33b', N'2025-12-04 20:40:49.087698', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'107', N'1', N'collect://hewendi/e3f724b0-534d-4f3e-857f-b44ec1a5ac54', N'2025-12-04 20:40:50.325317', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'108', N'1', N'collect://hewendi/213ff89f-2511-45ee-b0d4-efd406723cc9', N'2025-12-04 20:42:00.403646', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'109', N'1', N'collect://hewendi/445398e3-ce74-46de-ab8b-60f94148af57', N'2025-12-04 20:42:00.463186', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'110', N'1', N'collect://hewendi/7072b07c-9d4e-4d56-bbd4-c7abd44383ed', N'2025-12-04 20:49:41.944079', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'111', N'1', N'collect://hewendi/bea6bc67-012f-4ea3-8651-e95e4f74a3d1', N'2025-12-04 20:49:41.993200', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'112', N'1', N'collect://hewendi/21370303-fc17-4b94-a3a3-b3a0b37aef45', N'2025-12-04 20:50:01.216458', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'113', N'1', N'collect://hewendi/66e6afa8-3667-4957-9516-c551ccea720c', N'2025-12-04 20:50:01.259800', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'114', N'1', N'collect://hewendi/3a5f0868-0b9a-4639-8b5e-8c81fb289f9a', N'2025-12-04 20:50:04.532202', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'115', N'1', N'collect://hewendi/f1eb9160-d4a4-4a1c-91c2-1dd40f175958', N'2025-12-04 20:50:05.032925', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'116', N'1', N'collect://hewendi/d4dcc3f7-5adf-40fd-903a-162039c4a3a5', N'2025-12-04 20:50:05.237919', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'117', N'1', N'collect://hewendi/658aa1e7-5989-4485-906f-848963dbb98f', N'2025-12-04 20:50:05.428123', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'118', N'1', N'collect://hewendi/6fcc9622-70a6-429a-a77a-cc1f5d1d0a78', N'2025-12-04 20:50:05.619802', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'119', N'1', N'collect://hewendi/9ef45f78-be8f-4166-aa72-7b675c71713c', N'2025-12-04 20:50:14.660769', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'120', N'1', N'collect://hewendi/3cec6ed7-10fe-42d9-bf96-544e91f775d9', N'2025-12-04 20:53:08.493823', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'121', N'1', N'collect://hewendi/64a3899e-1547-45d0-8509-5ea1744b0195', N'2025-12-04 20:53:08.589002', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'122', N'1', N'collect://hewendi/850c4ca3-2a15-401a-9e00-f523a96e7ff8', N'2025-12-04 20:53:10.171187', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'123', N'1', N'collect://hewendi/bbbecf57-ff72-4508-a19c-a6d7310b715a', N'2025-12-04 21:09:18.333117', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'124', N'1', N'collect://hewendi/44d7074e-a0ef-4712-94f9-e871a865eddb', N'2025-12-04 21:09:18.371647', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'125', N'1', N'collect://hewendi/1a213812-5f45-47ee-be43-3ec5f226f446', N'2025-12-04 22:01:44.860869', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'126', N'1', N'collect://hewendi/0744a8a3-bb5c-42d7-9835-f738d0a2a684', N'2025-12-04 22:01:44.918738', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'127', N'1', N'collect://hewendi/ac3d009f-ccba-45f3-a5e6-c3892a48868f', N'2025-12-08 16:58:10.679282', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'128', N'1', N'collect://hewendi/5762a95b-d891-4606-a63a-b84accc7373e', N'2025-12-08 16:58:10.721036', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'129', N'1', N'collect://hewendi/70d0a7d0-c6a6-47ac-8f1b-6fff3ec2dc04', N'2025-12-08 16:58:28.263410', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'130', N'1', N'collect://hewendi/a6dc61ce-7617-4058-a0ba-799052ef5821', N'2025-12-08 16:58:28.315841', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'131', N'1', N'collect://hewendi/0d5345aa-b75a-46c9-b603-f3733725d7ca', N'2025-12-08 16:58:31.158127', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'132', N'1', N'collect://hewendi/87c701c3-b650-4764-be27-f03e261d17cd', N'2025-12-08 16:58:31.209693', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'133', N'1', N'collect://hewendi/ac431848-31f8-47f7-924a-b586675fed3b', N'2025-12-08 17:03:06.363269', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'134', N'1', N'collect://hewendi/0bdce4e3-5029-40e7-9769-5195b93aab28', N'2025-12-08 17:03:06.401566', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'135', N'1', N'collect://hewendi/1183371f-8ff4-45b1-b5f6-c90918e187fc', N'2025-12-08 17:05:36.230024', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'136', N'1', N'collect://hewendi/91f8b565-ad95-4eee-95af-75a74f407801', N'2025-12-08 17:05:36.289471', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'137', N'1', N'collect://hewendi/331cefae-2344-47a3-922a-c47839140a53', N'2025-12-08 17:08:22.534317', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'138', N'1', N'collect://hewendi/d92ee8d9-3030-4f82-81f7-b9febe0f697a', N'2025-12-08 17:08:30.450203', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'139', N'1', N'collect://hewendi/2e165d63-9030-4999-bc92-aa0154dd4ca6', N'2025-12-08 17:08:36.221575', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'140', N'1', N'collect://hewendi/9f424ff6-b0a3-4d56-af7d-d70c579e2559', N'2025-12-08 17:08:36.281403', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'141', N'1', N'collect://hewendi/32200efb-c5a3-405f-b025-16bb4b5338e4', N'2025-12-08 17:10:41.780765', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'142', N'1', N'collect://hewendi/0e51d63f-e52d-4e2e-9432-07c4715752a6', N'2025-12-08 17:10:41.840955', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'143', N'1', N'collect://hewendi/5b067623-b788-435a-928a-42198ff99377', N'2025-12-08 18:16:56.109848', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'144', N'1', N'collect://hewendi/83a3f63f-e996-407e-9ea4-8034405e7218', N'2025-12-08 18:16:56.158024', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'145', N'1', N'collect://hewendi/e55190e5-c6fc-43b3-b2d3-0e1b8c4da84c', N'2025-12-08 18:38:44.369158', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'146', N'1', N'collect://hewendi/6f23e8a4-ea99-41af-b241-a3917b1d78f9', N'2025-12-08 18:38:44.412993', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'147', N'1', N'collect://hewendi/4b837b0c-bf64-4421-afea-6810297ea74d', N'2025-12-08 18:39:09.396446', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'148', N'1', N'collect://hewendi/fb6b3af8-ef16-460f-b651-a4bfc32eb3f5', N'2025-12-08 18:39:09.453329', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'149', N'1', N'collect://hewendi/5a77d6ad-abb4-49eb-adf5-dbd6de120254', N'2025-12-08 18:39:12.360184', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'150', N'1', N'collect://hewendi/fcf78ae7-39b5-44df-b435-cb802476be50', N'2025-12-08 18:39:12.400599', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'151', N'1', N'collect://hewendi/937e45d7-acf3-489d-88f0-478b70ec2a8e', N'2025-12-08 18:42:29.512231', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'152', N'1', N'collect://hewendi/c2921a18-2cab-43b8-bb89-e0f9746736fc', N'2025-12-08 18:42:29.549398', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'153', N'1', N'collect://hewendi/057c93df-1786-4560-852b-1514b93f251f', N'2025-12-08 18:42:34.165090', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'154', N'1', N'collect://hewendi/9bc73063-a16d-4fc2-857d-107ab96a613a', N'2025-12-08 18:42:34.811798', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'155', N'1', N'collect://hewendi/1292bc49-84bf-441b-a254-016a0cef33cd', N'2025-12-08 18:42:42.898051', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'156', N'1', N'collect://hewendi/b3395abb-c59f-44d7-bda9-6e7aedbacd95', N'2025-12-08 18:42:42.964189', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'157', N'1', N'collect://hewendi/72d7badc-cd9a-4e83-8683-d1a36abbc751', N'2025-12-08 18:43:39.993214', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'158', N'1', N'collect://hewendi/075c4fd8-09b2-4387-a83b-9935aeea2740', N'2025-12-08 18:43:40.041130', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'159', N'1', N'collect://hewendi/e6119129-f8da-46d6-bc8e-e7e17d6e0282', N'2025-12-08 18:52:52.513212', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'160', N'1', N'collect://hewendi/a43a667b-f62a-42c9-b740-b601598f30da', N'2025-12-08 18:52:52.557970', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'161', N'1', N'collect://hewendi/3f9817cd-dd77-4b1e-a1fc-f78523d5d5c9', N'2025-12-08 19:16:49.257348', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'162', N'1', N'collect://hewendi/ba801192-231c-4bc2-b992-5fc387453022', N'2025-12-08 19:16:49.353226', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'163', N'1', N'collect://hewendi/10225609-9089-44a3-b540-c84923f1d7eb', N'2025-12-08 19:30:23.585254', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'164', N'1', N'collect://hewendi/5e8f746f-550a-4888-9332-4f2314824602', N'2025-12-08 19:30:23.585254', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'165', N'1', N'collect://hewendi/d6cd3c40-3387-485a-825e-e63019661d27', N'2025-12-08 19:33:15.012967', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'166', N'1', N'collect://hewendi/c77f37e4-fa74-46c5-aa4d-aab99eafab1f', N'2025-12-08 19:33:15.061513', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'167', N'1', N'collect://hewendi/c50832c0-8e65-45f6-a7e6-01607aa06789', N'2025-12-08 19:34:14.078245', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'168', N'1', N'collect://hewendi/1ad5779f-76c6-45a9-a12d-b2e61e074d66', N'2025-12-08 19:34:14.141018', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'169', N'1', N'collect://hewendi/e532958b-6b8a-47c5-a4af-be6d31a823cc', N'2025-12-08 19:34:29.869967', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'170', N'1', N'collect://hewendi/7b9c0162-f62b-4033-9f06-ad657e7841da', N'2025-12-08 19:34:29.916739', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'171', N'1', N'collect://hewendi/4648a991-83dd-4f81-bde5-fcd6b7ef8f09', N'2025-12-08 19:34:59.948491', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'172', N'1', N'collect://hewendi/245e1646-ee49-478e-b1cb-acd6553a1c8a', N'2025-12-08 19:35:25.688165', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'173', N'1', N'collect://hewendi/a3b78bae-6349-4fb3-905a-76bdcfaf1b66', N'2025-12-08 19:35:25.744339', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'174', N'1', N'collect://hewendi/578e7109-4060-4601-9c7e-631d8291cf51', N'2025-12-08 19:35:35.128303', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'175', N'1', N'collect://hewendi/3bc5a92e-5d60-4c41-a312-fd0096b5c214', N'2025-12-08 19:35:35.334211', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'176', N'1', N'collect://hewendi/3610ad0b-f4e4-49a3-b816-e4f2f262938d', N'2025-12-08 19:42:09.423593', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'177', N'1', N'collect://hewendi/6d669902-cb3b-4cc7-9004-712e0b0c1790', N'2025-12-08 19:42:09.450128', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'178', N'1', N'collect://hewendi/80572649-a625-4529-a775-9028b6477890', N'2025-12-09 09:04:26.544239', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'179', N'1', N'collect://hewendi/07dfeb2c-59fc-4dcc-a028-e7313aa6dc3d', N'2025-12-09 09:04:26.704961', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'180', N'1', N'collect://hewendi/56a14dc3-603f-49d4-985b-522f47a17aca', N'2025-12-09 09:08:26.631920', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'181', N'1', N'collect://hewendi/04d62735-0c59-41ed-8d4c-3ddfe99cc9ee', N'2025-12-09 09:08:26.721802', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'182', N'1', N'collect://hewendi/d6ad0ec4-e292-438f-9efb-95994a20544e', N'2025-12-18 09:16:13.434469', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'183', N'1', N'collect://hewendi/fbeedc44-0921-4fa1-8c91-8d703f17167f', N'2025-12-18 09:16:13.505287', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'184', N'1', N'collect://hewendi/94dd21b2-0e54-4a5d-8478-5ce58de9716f', N'2025-12-18 09:19:19.946083', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'185', N'1', N'collect://hewendi/6a773be7-eed2-4eed-a63a-5d52c7a42cbd', N'2025-12-18 09:19:19.990647', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'186', N'1', N'collect://hewendi/5a8f91a8-84c3-45a3-a24b-e57e4ac94a35', N'2025-12-18 09:19:22.101466', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'187', N'1', N'collect://hewendi/576b9c33-778d-467c-8638-22aaa31f910f', N'2025-12-18 09:19:22.134131', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'188', N'1', N'collect://hewendi/94e4fff1-4ad7-4f96-bea3-e40963b46f8f', N'2025-12-18 09:28:17.849244', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'189', N'1', N'collect://hewendi/2fcc088a-bac8-4ab8-b3eb-bde9c5edb265', N'2025-12-18 09:28:17.883330', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'190', N'1', N'collect://hewendi/bccc7467-0458-484a-a3fa-f6d1b688b387', N'2025-12-18 09:41:32.224385', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'191', N'1', N'collect://hewendi/5abe68ee-af86-40bc-b3e5-62b224b35495', N'2025-12-18 09:41:32.240463', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'192', N'1', N'collect://hewendi/1214a602-4982-44ee-9361-93f648672b2c', N'2025-12-18 09:41:34.839003', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'193', N'1', N'collect://hewendi/98822ccb-1c12-44f7-912e-9f545e601c0d', N'2025-12-18 09:41:34.879958', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'194', N'1', N'collect://hewendi/bd201bc8-906b-42fd-99b2-20eaccd372a2', N'2025-12-18 09:41:39.371212', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'195', N'1', N'collect://hewendi/f326c206-478b-442a-9687-c794820d460d', N'2025-12-18 09:41:39.409719', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'196', N'1', N'collect://hewendi/c48fa4ae-0bfe-4dba-967b-25874120eb8d', N'2025-12-18 09:41:41.383017', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'197', N'1', N'collect://hewendi/f4a81bc4-294b-4640-ad23-fb9bb92d1a75', N'2025-12-18 09:41:42.066243', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'198', N'1', N'collect://hewendi/d6b4ccf5-704f-4f25-a5bc-fc0b1131a404', N'2025-12-18 14:32:21.676195', N'hewendi')
GO

INSERT INTO [dbo].[collectionqrcode] ([id], [active], [content], [expire_at], [merchant_id]) VALUES (N'199', N'1', N'collect://hewendi/b5ac3176-50da-470f-9625-3f7e90331884', N'2025-12-18 14:32:21.632120', N'hewendi')
GO

SET IDENTITY_INSERT [dbo].[collectionqrcode] OFF
GO


-- ----------------------------
-- Table structure for discount_policies
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[discount_policies]') AND type IN ('U'))
	DROP TABLE [dbo].[discount_policies]
GO

CREATE TABLE [dbo].[discount_policies] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [applicable_lines] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [applicable_user_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [created_at] datetime2(6)  NULL,
  [description] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [discount_rate] numeric(5,2)  NULL,
  [enabled] bit  NOT NULL,
  [end_time] datetime2(6)  NULL,
  [policy_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [start_time] datetime2(6)  NULL,
  [updated_at] datetime2(6)  NULL
)
GO

ALTER TABLE [dbo].[discount_policies] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of discount_policies
-- ----------------------------
SET IDENTITY_INSERT [dbo].[discount_policies] ON
GO

INSERT INTO [dbo].[discount_policies] ([id], [applicable_lines], [applicable_user_type], [created_at], [description], [discount_rate], [enabled], [end_time], [policy_name], [start_time], [updated_at]) VALUES (N'1', N'1号线', N'REGULAR', N'2023-01-10 09:00:00.000000', N'工作日早高峰折扣', N'0.80', N'1', N'2025-12-31 23:59:59.000000', N'早鸟计划', N'2023-01-15 07:00:00.000000', N'2025-12-04 19:45:54.736582')
GO

INSERT INTO [dbo].[discount_policies] ([id], [applicable_lines], [applicable_user_type], [created_at], [description], [discount_rate], [enabled], [end_time], [policy_name], [start_time], [updated_at]) VALUES (N'2', N'所有线路', N'STUDENT', N'2023-02-01 10:00:00.000000', N'学生认证用户专享', N'0.50', N'1', N'2025-09-01 00:00:00.000000', N'学生优惠', N'2023-02-01 12:00:00.000000', N'2025-12-04 19:37:39.089077')
GO

INSERT INTO [dbo].[discount_policies] ([id], [applicable_lines], [applicable_user_type], [created_at], [description], [discount_rate], [enabled], [end_time], [policy_name], [start_time], [updated_at]) VALUES (N'8', N'Line1', N'ELDERLY', N'2025-12-03 19:16:00.937873', N'q', N'0.80', N'1', N'2025-12-30 19:15:00.000000', N'123', N'2025-12-27 19:15:00.000000', N'2025-12-04 19:37:40.131754')
GO

SET IDENTITY_INSERT [dbo].[discount_policies] OFF
GO


-- ----------------------------
-- Table structure for fintech_users
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[fintech_users]') AND type IN ('U'))
	DROP TABLE [dbo].[fintech_users]
GO

CREATE TABLE [dbo].[fintech_users] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [balance] numeric(19,2)  NULL,
  [password] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [phone] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [status] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [username] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [pay_password] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [pay_pwd_failed_attempts] int  NULL,
  [pay_pwd_locked_until] datetime2(6)  NULL
)
GO

ALTER TABLE [dbo].[fintech_users] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of fintech_users
-- ----------------------------
SET IDENTITY_INSERT [dbo].[fintech_users] ON
GO

INSERT INTO [dbo].[fintech_users] ([id], [balance], [password], [phone], [status], [username], [pay_password], [pay_pwd_failed_attempts], [pay_pwd_locked_until]) VALUES (N'3', N'1500.50', N'$2a$10$ABC123', N'13800138001', N'REJECTED', N'zhangsan', NULL, NULL, NULL)
GO

INSERT INTO [dbo].[fintech_users] ([id], [balance], [password], [phone], [status], [username], [pay_password], [pay_pwd_failed_attempts], [pay_pwd_locked_until]) VALUES (N'4', N'2800.00', N'$2a$10$DEF456', N'13800138002', N'ACTIVE', N'lisi', NULL, NULL, NULL)
GO

INSERT INTO [dbo].[fintech_users] ([id], [balance], [password], [phone], [status], [username], [pay_password], [pay_pwd_failed_attempts], [pay_pwd_locked_until]) VALUES (N'5', N'500.25', N'$2a$10$GHI789', N'13800138003', N'ACTIVE', N'wangwu', NULL, NULL, NULL)
GO

INSERT INTO [dbo].[fintech_users] ([id], [balance], [password], [phone], [status], [username], [pay_password], [pay_pwd_failed_attempts], [pay_pwd_locked_until]) VALUES (N'6', N'3200.75', N'$2a$10$JKL012', N'13800138004', N'ACTIVE', N'zhaoliu', NULL, NULL, NULL)
GO

INSERT INTO [dbo].[fintech_users] ([id], [balance], [password], [phone], [status], [username], [pay_password], [pay_pwd_failed_attempts], [pay_pwd_locked_until]) VALUES (N'7', N'1800.00', N'$2a$10$MNO345', N'13800138005', N'ACTIVE', N'sunqi', NULL, NULL, NULL)
GO

INSERT INTO [dbo].[fintech_users] ([id], [balance], [password], [phone], [status], [username], [pay_password], [pay_pwd_failed_attempts], [pay_pwd_locked_until]) VALUES (N'8', N'110.00', N'$2a$10$dvgIyHIIZLbzu/Va3.bbdOEI607lFC8kr5t58WWK4mkpkpFVGPEui', N'13341318124', N'ACTIVE', N'hewendi', N'$2a$10$5L6.IWSR8pfdPlfY2MFlQOZc0yUNDS7L0m4B9XjCKTKUR0ae6FhOG', N'0', NULL)
GO

INSERT INTO [dbo].[fintech_users] ([id], [balance], [password], [phone], [status], [username], [pay_password], [pay_pwd_failed_attempts], [pay_pwd_locked_until]) VALUES (N'9', N'0.00', N'$2a$10$4fyhK1ra8jdEaV6N6FR2T.jS6uv301Wd/dwiRD1UMBSZx3lsuAsPS', N'13341318124', N'INACTIVE', N'test', NULL, NULL, NULL)
GO

INSERT INTO [dbo].[fintech_users] ([id], [balance], [password], [phone], [status], [username], [pay_password], [pay_pwd_failed_attempts], [pay_pwd_locked_until]) VALUES (N'10', N'99999999.00', N'$2a$10$pqG/FdcqyWWihyrT.afcX.gtvpbu8ZV4Bf.RiQklRf6fGEzG1ygvO', N'13800000000', N'ACTIVE', N'demo_payer', N'$2a$10$qOd83U86JqtOlHJl4yfmh.VF3ETVjzeSp7aOr/rWG6yewIDd.w1WC', N'0', NULL)
GO

INSERT INTO [dbo].[fintech_users] ([id], [balance], [password], [phone], [status], [username], [pay_password], [pay_pwd_failed_attempts], [pay_pwd_locked_until]) VALUES (N'11', N'0.00', N'$2a$10$08BcDbZsGp4niog/G4EELePd3l4XiYHFeKEzFsGPfxJKf33Y67G.m', N'13341318124', N'ACTIVE', N'hwd', NULL, NULL, NULL)
GO

SET IDENTITY_INSERT [dbo].[fintech_users] OFF
GO


-- ----------------------------
-- Table structure for gate_events
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[gate_events]') AND type IN ('U'))
	DROP TABLE [dbo].[gate_events]
GO

CREATE TABLE [dbo].[gate_events] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [error_code] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [error_message] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [event_time] datetime2(6)  NULL,
  [event_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [fare] numeric(8,2)  NULL,
  [gate_code] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [gate_id] bigint  NULL,
  [processed_time] datetime2(6)  NULL,
  [qr_code] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [remark] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [status] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [transaction_id] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [user_id] bigint  NULL,
  [username] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[gate_events] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of gate_events
-- ----------------------------
SET IDENTITY_INSERT [dbo].[gate_events] ON
GO

INSERT INTO [dbo].[gate_events] ([id], [error_code], [error_message], [event_time], [event_type], [fare], [gate_code], [gate_id], [processed_time], [qr_code], [remark], [status], [transaction_id], [user_id], [username]) VALUES (N'1', NULL, NULL, N'2023-12-02 08:30:00.000000', N'ENTRY', NULL, N'G001', N'1', N'2023-12-02 08:30:05.000000', N'QR12345', N'正常进入', N'PROCESSED', N'T001', N'3', N'zhangsan')
GO

INSERT INTO [dbo].[gate_events] ([id], [error_code], [error_message], [event_time], [event_type], [fare], [gate_code], [gate_id], [processed_time], [qr_code], [remark], [status], [transaction_id], [user_id], [username]) VALUES (N'2', NULL, NULL, N'2023-12-02 09:30:00.000000', N'EXIT', N'4.00', N'G002', N'2', N'2023-12-02 09:30:05.000000', N'QR12345', N'正常出站', N'PROCESSED', N'T002', N'3', N'zhangsan')
GO

INSERT INTO [dbo].[gate_events] ([id], [error_code], [error_message], [event_time], [event_type], [fare], [gate_code], [gate_id], [processed_time], [qr_code], [remark], [status], [transaction_id], [user_id], [username]) VALUES (N'3', N'E001', N'余额不足', N'2023-12-03 10:00:00.000000', N'ENTRY', NULL, N'G003', N'3', N'2023-12-03 10:00:02.000000', N'QR67890', N'进站失败', N'FAILED', N'T003', N'4', N'lisi')
GO

SET IDENTITY_INSERT [dbo].[gate_events] OFF
GO


-- ----------------------------
-- Table structure for gates
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[gates]') AND type IN ('U'))
	DROP TABLE [dbo].[gates]
GO

CREATE TABLE [dbo].[gates] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [created_at] datetime2(6)  NULL,
  [description] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [direction] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [enabled] bit  NOT NULL,
  [gate_code] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [gate_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [location] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [station_id] bigint  NULL,
  [status] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [updated_at] datetime2(6)  NULL
)
GO

ALTER TABLE [dbo].[gates] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of gates
-- ----------------------------
SET IDENTITY_INSERT [dbo].[gates] ON
GO

INSERT INTO [dbo].[gates] ([id], [created_at], [description], [direction], [enabled], [gate_code], [gate_name], [location], [station_id], [status], [updated_at]) VALUES (N'1', N'2023-01-01 11:00:00.000000', N'A口', N'IN', N'1', N'G001', N'王府井-A口', N'北侧', N'1', N'OPERATIONAL', N'2023-01-01 11:00:00.000000')
GO

INSERT INTO [dbo].[gates] ([id], [created_at], [description], [direction], [enabled], [gate_code], [gate_name], [location], [station_id], [status], [updated_at]) VALUES (N'2', N'2023-01-01 11:01:00.000000', N'B口', N'OUT', N'1', N'G002', N'王府井-B口', N'南侧', N'1', N'OPERATIONAL', N'2023-01-01 11:01:00.000000')
GO

INSERT INTO [dbo].[gates] ([id], [created_at], [description], [direction], [enabled], [gate_code], [gate_name], [location], [station_id], [status], [updated_at]) VALUES (N'3', N'2023-03-01 10:00:00.000000', N'1号口', N'IN', N'1', N'G003', N'陆家嘴-1号口', N'东侧', N'3', N'OPERATIONAL', N'2023-03-01 10:00:00.000000')
GO

INSERT INTO [dbo].[gates] ([id], [created_at], [description], [direction], [enabled], [gate_code], [gate_name], [location], [station_id], [status], [updated_at]) VALUES (N'4', N'2023-03-01 10:01:00.000000', N'2号口', N'OUT', N'1', N'G004', N'陆家嘴-2号口', N'西侧', N'3', N'MAINTENANCE', N'2023-03-01 10:01:00.000000')
GO

SET IDENTITY_INSERT [dbo].[gates] OFF
GO


-- ----------------------------
-- Table structure for payment
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[payment]') AND type IN ('U'))
	DROP TABLE [dbo].[payment]
GO

CREATE TABLE [dbo].[payment] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [amount] numeric(38,2)  NULL,
  [created_at] datetime2(6)  NULL,
  [from_user] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [payment_method] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [status] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [to_merchant] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [error_code] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [error_message] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[payment] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of payment
-- ----------------------------
SET IDENTITY_INSERT [dbo].[payment] ON
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'1', N'35.50', NULL, N'张三', N'balance', N'成功', N'星巴克咖啡', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'2', N'128.00', NULL, N'李四', N'card', N'成功', N'沃尔玛超市', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'3', N'25.80', NULL, N'王五', N'balance', N'成功', N'滴滴出行', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'4', N'68.90', NULL, N'赵六', N'balance', N'成功', N'美团外卖', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'5', N'299.00', NULL, N'孙七', N'huabei', N'成功', N'天猫超市', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'6', N'200.00', NULL, N'张三', N'card', N'成功', N'中国石油', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'7', N'45.50', NULL, N'李四', N'balance', N'成功', N'肯德基', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'8', N'1.00', N'2025-12-03 20:46:17.413153', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'9', N'1.00', N'2025-12-03 20:46:20.987702', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'10', N'1.00', N'2025-12-03 21:23:14.540830', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'11', N'1.00', N'2025-12-03 21:23:17.321586', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'12', N'1.00', N'2025-12-03 21:23:34.454562', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'13', N'1.00', N'2025-12-04 19:55:41.670246', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'14', N'1.00', N'2025-12-04 19:55:42.848179', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'15', N'1.00', N'2025-12-04 19:55:44.697008', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'16', N'1.00', N'2025-12-04 19:55:51.363567', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'17', N'1.00', N'2025-12-04 19:56:04.229456', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'18', N'1.00', N'2025-12-04 19:56:04.999561', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'19', N'1.00', N'2025-12-04 19:56:09.221685', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'20', N'1.00', N'2025-12-04 19:56:09.898604', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'21', N'1.00', N'2025-12-04 19:57:38.714635', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'22', N'1.00', N'2025-12-04 19:57:54.922648', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'23', N'1.00', N'2025-12-04 20:02:36.025304', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'24', N'1.00', N'2025-12-04 20:02:38.408731', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'25', N'1.00', N'2025-12-04 20:02:38.871704', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'26', N'1.00', N'2025-12-04 20:02:39.099414', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'27', N'1.00', N'2025-12-04 20:02:39.267509', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'28', N'1.00', N'2025-12-04 20:02:39.766741', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'29', N'1.00', N'2025-12-04 20:03:25.088056', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'30', N'1.00', N'2025-12-04 20:03:26.216836', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'31', N'1.00', N'2025-12-04 20:03:36.933545', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'32', N'1.00', N'2025-12-04 20:05:20.173150', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'33', N'1.00', N'2025-12-04 20:10:00.346872', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'34', N'1.00', N'2025-12-04 20:15:04.447633', N'hewendi', N'qr', N'SUCCESS', N'hewendi', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'35', N'1.00', N'2025-12-04 20:35:38.553434', N'hewendi', N'qr', N'FAILED', N'demo_merchant', N'INSUFFICIENT_FUNDS', N'insufficient balance')
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'36', N'1.00', N'2025-12-04 20:35:45.486392', N'hewendi', N'qr', N'FAILED', N'demo_merchant', N'INSUFFICIENT_FUNDS', N'insufficient balance')
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'37', N'1.00', N'2025-12-04 20:47:56.997909', N'hewendi', N'qr', N'FAILED', N'demo_merchant', N'INSUFFICIENT_FUNDS', N'insufficient balance')
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'38', N'1.00', N'2025-12-04 21:41:34.115625', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'39', N'1.00', N'2025-12-04 21:59:51.752399', N'demo_payer', N'qr', N'SUCCESS', N'hewendi', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'40', N'1.00', N'2025-12-04 22:00:03.705769', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'41', N'1.00', N'2025-12-08 18:40:09.005324', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'42', N'1.00', N'2025-12-08 19:22:54.616019', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'43', N'1.00', N'2025-12-08 19:32:10.052557', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'44', N'1.00', N'2025-12-08 19:40:28.342411', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'45', N'1.00', N'2025-12-08 19:40:51.723161', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'46', N'1.00', N'2025-12-08 19:51:48.292431', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'47', N'1.00', N'2025-12-08 20:00:55.150875', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'48', N'1.00', N'2025-12-08 20:00:55.152878', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'49', N'1.00', N'2025-12-09 09:01:57.168567', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

INSERT INTO [dbo].[payment] ([id], [amount], [created_at], [from_user], [payment_method], [status], [to_merchant], [error_code], [error_message]) VALUES (N'50', N'1.00', N'2025-12-09 09:02:35.437755', N'hewendi', N'qr', N'SUCCESS', N'demo_merchant', NULL, NULL)
GO

SET IDENTITY_INSERT [dbo].[payment] OFF
GO


-- ----------------------------
-- Table structure for stations
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[stations]') AND type IN ('U'))
	DROP TABLE [dbo].[stations]
GO

CREATE TABLE [dbo].[stations] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [city] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [created_at] datetime2(6)  NULL,
  [description] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [enabled] bit  NOT NULL,
  [line] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [location] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [station_code] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [station_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [updated_at] datetime2(6)  NULL
)
GO

ALTER TABLE [dbo].[stations] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of stations
-- ----------------------------
SET IDENTITY_INSERT [dbo].[stations] ON
GO

INSERT INTO [dbo].[stations] ([id], [city], [created_at], [description], [enabled], [line], [location], [station_code], [station_name], [updated_at]) VALUES (N'1', N'北京', N'2023-01-01 10:00:00.000000', N'一号线起点站', N'1', N'1号线', N'东城区', N'STN001', N'王府井', N'2023-01-01 10:00:00.000000')
GO

INSERT INTO [dbo].[stations] ([id], [city], [created_at], [description], [enabled], [line], [location], [station_code], [station_name], [updated_at]) VALUES (N'2', N'北京', N'2023-01-01 10:05:00.000000', N'换乘站', N'1', N'1号线', N'西城区', N'STN002', N'西单', N'2023-01-01 10:05:00.000000')
GO

INSERT INTO [dbo].[stations] ([id], [city], [created_at], [description], [enabled], [line], [location], [station_code], [station_name], [updated_at]) VALUES (N'3', N'上海', N'2023-03-01 09:00:00.000000', N'二号线重要站点', N'1', N'2号线', N'浦东新区', N'STN003', N'陆家嘴', N'2023-03-01 09:00:00.000000')
GO

INSERT INTO [dbo].[stations] ([id], [city], [created_at], [description], [enabled], [line], [location], [station_code], [station_name], [updated_at]) VALUES (N'4', N'上海', N'2023-03-01 09:10:00.000000', N'商业区站点', N'1', N'10号线', N'黄浦区', N'STN004', N'南京东路', N'2023-03-01 09:10:00.000000')
GO

INSERT INTO [dbo].[stations] ([id], [city], [created_at], [description], [enabled], [line], [location], [station_code], [station_name], [updated_at]) VALUES (N'12', N'sad', N'2025-12-03 19:16:31.692055', N'ssd', N'1', N'Asd', N'SD', N'SOO!', N'WQ', N'2025-12-03 19:16:31.692055')
GO

SET IDENTITY_INSERT [dbo].[stations] OFF
GO


-- ----------------------------
-- Table structure for travel_pass
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[travel_pass]') AND type IN ('U'))
	DROP TABLE [dbo].[travel_pass]
GO

CREATE TABLE [dbo].[travel_pass] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [bound_payment_method] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [city] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [enabled] bit  NOT NULL,
  [line] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [updated_at] datetime2(6)  NULL,
  [username] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[travel_pass] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of travel_pass
-- ----------------------------
SET IDENTITY_INSERT [dbo].[travel_pass] ON
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'1', N'balance', N'北京', N'1', N'1号线', N'2023-11-30 10:00:00.000000', N'zhangsan')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'2', N'card', N'上海', N'1', N'2号线', N'2023-11-30 11:00:00.000000', N'lisi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'4', N'balance', N'北京', N'1', N'1号线', N'2025-12-09 09:03:52.792562', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'5', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:46.550441', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'6', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:46.939045', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'7', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:47.155078', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'8', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:47.604061', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'9', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:47.845256', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'10', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:51.506809', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'11', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:52.221872', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'12', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:52.877419', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'13', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:54.238010', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'14', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:57.585836', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'15', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:57.907581', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'16', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:58.085657', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'17', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:58.253026', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'18', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:58.429205', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'19', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:58.594748', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'20', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:58.771986', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'21', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:58.939910', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'22', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:59.136735', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'23', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 19:50:59.342921', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'24', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 20:46:44.089273', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'25', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 20:46:47.085520', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'26', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 20:46:47.957510', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'27', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 20:46:48.300178', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'28', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 20:46:48.652801', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'29', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 20:46:49.021800', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'30', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 20:46:49.702238', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'31', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 20:46:50.318328', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'32', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 20:46:50.880619', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'33', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 20:46:51.291371', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'34', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:23:26.991594', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'35', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:23:27.551307', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'36', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:23:27.906723', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'37', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:23:28.502720', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'38', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:24:10.643501', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'39', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:24:11.864318', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'40', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:34:07.253578', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'41', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:34:08.180446', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'42', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:34:09.002889', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'43', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:34:09.673236', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'44', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:34:10.343133', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'45', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:34:11.044646', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'46', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:34:11.708858', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'47', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:34:12.385659', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'48', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:34:13.047209', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'49', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:34:13.653265', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'50', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:34:14.297819', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'51', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:38:52.391860', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'52', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:38:53.025451', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'53', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:38:53.467545', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'54', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:38:54.483962', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'55', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:38:55.181243', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'56', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:38:55.877374', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'57', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:38:56.511225', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'58', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:38:57.109407', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'59', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:47:04.688215', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'60', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:47:06.252582', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'61', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:47:07.310178', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'62', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:47:08.223049', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'63', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:47:09.160665', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'64', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:47:09.597783', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'65', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:47:09.990200', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'66', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:47:10.407865', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'67', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:47:12.103233', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'68', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:47:13.326028', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'69', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:47:14.606758', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'70', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:47:15.714056', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'71', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:47:17.311243', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'72', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:47:18.628485', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'73', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:47:19.770140', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'74', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:48:59.469353', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'75', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:49:00.289363', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'76', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:49:00.972414', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'77', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:49:01.702794', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'78', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:49:02.382288', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'79', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:49:03.061364', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'80', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:49:18.223445', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'81', N'balance', N'北京', N'1', N'Line1', N'2025-12-03 21:50:26.115604', N'hewendi')
GO

INSERT INTO [dbo].[travel_pass] ([id], [bound_payment_method], [city], [enabled], [line], [updated_at], [username]) VALUES (N'82', N'balance', N'北京', N'1', N'Line1', N'2025-12-04 20:10:56.774079', N'hewendi')
GO

SET IDENTITY_INSERT [dbo].[travel_pass] OFF
GO


-- ----------------------------
-- Table structure for travel_record
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[travel_record]') AND type IN ('U'))
	DROP TABLE [dbo].[travel_record]
GO

CREATE TABLE [dbo].[travel_record] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [city] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [entry_time] datetime2(6)  NULL,
  [exit_time] datetime2(6)  NULL,
  [fare] numeric(38,2)  NULL,
  [line] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [status] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [username] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[travel_record] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of travel_record
-- ----------------------------
SET IDENTITY_INSERT [dbo].[travel_record] ON
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'1', N'北京', N'2023-12-02 08:30:00.000000', N'2023-12-02 09:30:00.000000', N'4.00', N'1号线', N'COMPLETED', N'zhangsan')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'2', N'上海', N'2023-12-03 10:00:00.000000', NULL, NULL, N'2号线', N'IN_PROGRESS', N'lisi')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'3', N'Beijing', N'2025-12-04 21:05:12.702544', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'4', N'Beijing', N'2025-12-04 21:05:15.168625', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'5', N'Beijing', N'2025-12-04 21:05:18.370803', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'6', N'Beijing', N'2025-12-04 21:05:19.050306', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'7', N'Beijing', N'2025-12-04 21:06:14.680049', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'8', N'Beijing', N'2025-12-04 21:06:15.516581', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'9', N'Beijing', N'2025-12-04 21:06:23.034738', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'10', N'Beijing', N'2025-12-04 21:15:40.952620', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'11', N'Beijing', N'2025-12-04 21:15:43.551905', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'12', N'Beijing', N'2025-12-04 21:15:49.023283', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'13', N'Beijing', N'2025-12-04 21:15:49.794883', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'14', N'Beijing', N'2025-12-04 21:15:58.047100', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'15', N'Beijing', N'2025-12-04 21:15:58.630869', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'16', N'Beijing', N'2025-12-04 21:16:02.083705', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'17', N'Beijing', N'2025-12-04 21:16:08.552408', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'18', N'Beijing', N'2025-12-04 21:16:11.310560', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'19', N'Beijing', N'2025-12-04 21:16:26.320708', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'20', N'Beijing', N'2025-12-04 21:16:26.868921', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'21', N'Beijing', N'2025-12-04 21:20:27.340062', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'22', N'Beijing', N'2025-12-04 21:20:32.597352', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'23', N'Beijing', N'2025-12-04 21:20:36.798849', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'24', N'Beijing', N'2025-12-04 21:24:05.619412', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'25', N'Beijing', N'2025-12-04 21:24:08.193168', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'26', N'Beijing', N'2025-12-04 21:24:12.426758', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'27', N'Beijing', N'2025-12-04 21:24:15.243712', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'28', N'Beijing', N'2025-12-04 21:25:12.268925', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'29', N'Beijing', N'2025-12-04 21:25:14.368368', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'30', N'Beijing', N'2025-12-04 21:25:14.551200', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'31', N'Beijing', N'2025-12-04 21:25:34.169409', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'32', N'Beijing', N'2025-12-04 21:25:34.570303', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'33', N'Beijing', N'2025-12-04 21:25:34.753087', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'34', N'Beijing', N'2025-12-04 21:28:03.755361', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'35', N'Beijing', N'2025-12-04 21:28:09.765809', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'36', N'Beijing', N'2025-12-04 21:28:18.725364', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'37', N'Beijing', N'2025-12-04 21:28:20.647783', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'38', N'Beijing', N'2025-12-04 21:28:22.752058', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'39', N'Beijing', N'2025-12-04 21:31:23.834418', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'40', N'Beijing', N'2025-12-04 21:31:31.480252', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'41', N'Beijing', N'2025-12-04 21:31:36.052280', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'42', N'Beijing', N'2025-12-04 21:31:46.295348', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'43', N'Beijing', N'2025-12-04 21:31:48.096480', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'44', N'Beijing', N'2025-12-04 21:31:48.835569', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'45', N'Beijing', N'2025-12-04 21:31:50.779001', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'46', N'Beijing', N'2025-12-04 21:31:51.907702', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'47', N'Beijing', N'2025-12-04 21:32:37.524216', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'48', N'Beijing', N'2025-12-04 21:32:38.312833', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'49', N'Beijing', N'2025-12-04 21:37:03.236995', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'50', N'Beijing', N'2025-12-04 21:37:08.135618', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'51', N'Beijing', N'2025-12-04 21:40:02.272215', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'52', N'Beijing', N'2025-12-04 21:40:09.172745', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'53', N'Beijing', N'2025-12-04 21:41:12.673640', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'54', N'Beijing', N'2025-12-04 21:43:04.297350', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'55', N'Beijing', N'2025-12-04 21:44:08.080441', NULL, NULL, N'Line1', N'IN_PROGRESS', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'56', N'Beijing', N'2025-12-04 21:51:10.972608', N'2025-12-04 21:51:12.636014', N'2.50', N'Line1', N'EXCEPTION', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'57', N'Beijing', N'2025-12-04 21:52:22.820478', N'2025-12-04 21:52:28.793772', N'2.50', N'Line1', N'EXCEPTION', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'58', N'Beijing', N'2025-12-04 22:00:13.126484', N'2025-12-04 22:00:14.437430', N'2.50', N'Line1', N'EXCEPTION', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'59', N'Beijing', N'2025-12-08 17:08:46.478736', N'2025-12-08 17:08:49.268222', N'2.50', N'Line1', N'EXCEPTION', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'60', N'Beijing', N'2025-12-08 17:59:17.058518', N'2025-12-08 17:59:18.834057', N'2.50', N'Line1', N'EXCEPTION', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'61', N'Beijing', N'2025-12-08 17:59:29.728022', N'2025-12-08 17:59:30.956931', N'2.50', N'Line1', N'EXCEPTION', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'62', N'Beijing', N'2025-12-08 18:00:21.296368', N'2025-12-08 18:00:26.540436', N'2.50', N'Line1', N'EXCEPTION', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'63', N'Beijing', N'2025-12-08 18:08:02.309009', N'2025-12-08 18:08:03.889144', N'2.50', N'Line1', N'EXCEPTION', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'64', N'Beijing', N'2025-12-08 19:41:31.798414', N'2025-12-08 19:41:33.639197', N'2.50', N'Line1', N'EXCEPTION', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'65', N'Beijing', N'2025-12-09 09:05:03.617507', N'2025-12-09 09:05:08.249660', N'2.50', N'Line1', N'EXCEPTION', N'alice')
GO

INSERT INTO [dbo].[travel_record] ([id], [city], [entry_time], [exit_time], [fare], [line], [status], [username]) VALUES (N'66', N'Beijing', N'2025-12-09 09:06:08.082863', N'2025-12-09 09:06:10.234371', N'2.50', N'Line1', N'EXCEPTION', N'alice')
GO

SET IDENTITY_INSERT [dbo].[travel_record] OFF
GO


-- ----------------------------
-- Table structure for user_account
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[user_account]') AND type IN ('U'))
	DROP TABLE [dbo].[user_account]
GO

CREATE TABLE [dbo].[user_account] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [balance] numeric(38,2)  NULL,
  [default_payment_method] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [display_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [username] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[user_account] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of user_account
-- ----------------------------
SET IDENTITY_INSERT [dbo].[user_account] ON
GO

INSERT INTO [dbo].[user_account] ([id], [balance], [default_payment_method], [display_name], [username]) VALUES (N'13', N'1500.50', N'balance', N'张三', N'张三')
GO

INSERT INTO [dbo].[user_account] ([id], [balance], [default_payment_method], [display_name], [username]) VALUES (N'14', N'2800.00', N'card', N'李四', N'李四')
GO

INSERT INTO [dbo].[user_account] ([id], [balance], [default_payment_method], [display_name], [username]) VALUES (N'15', N'500.25', N'balance', N'王五', N'王五')
GO

INSERT INTO [dbo].[user_account] ([id], [balance], [default_payment_method], [display_name], [username]) VALUES (N'16', N'3200.75', N'balance', N'赵六', N'赵六')
GO

INSERT INTO [dbo].[user_account] ([id], [balance], [default_payment_method], [display_name], [username]) VALUES (N'17', N'1800.00', N'huabei', N'孙七', N'孙七')
GO

INSERT INTO [dbo].[user_account] ([id], [balance], [default_payment_method], [display_name], [username]) VALUES (N'18', N'1000.00', N'balance', N'Alice', N'alice')
GO

INSERT INTO [dbo].[user_account] ([id], [balance], [default_payment_method], [display_name], [username]) VALUES (N'19', N'1000.00', N'balance', N'Bob Merchant', N'bob')
GO

SET IDENTITY_INSERT [dbo].[user_account] OFF
GO


-- ----------------------------
-- Table structure for user_audit
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[user_audit]') AND type IN ('U'))
	DROP TABLE [dbo].[user_audit]
GO

CREATE TABLE [dbo].[user_audit] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [id_card_url] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [real_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [reject_reason] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [submit_time] datetime2(6)  NULL,
  [user_id] bigint  NULL,
  [id_card_number] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[user_audit] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of user_audit
-- ----------------------------
SET IDENTITY_INSERT [dbo].[user_audit] ON
GO

INSERT INTO [dbo].[user_audit] ([id], [id_card_url], [real_name], [reject_reason], [submit_time], [user_id], [id_card_number]) VALUES (N'1', N'http://example.com/id/1.jpg', N'张三丰', N'w', N'2023-11-20 10:00:00.000000', N'3', NULL)
GO

INSERT INTO [dbo].[user_audit] ([id], [id_card_url], [real_name], [reject_reason], [submit_time], [user_id], [id_card_number]) VALUES (N'2', N'http://example.com/id/2.jpg', N'李四光', N'身份证照片不清晰', N'2023-11-21 11:30:00.000000', N'4', NULL)
GO

SET IDENTITY_INSERT [dbo].[user_audit] OFF
GO


-- ----------------------------
-- Auto increment value for admin_operation_logs
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[admin_operation_logs]', RESEED, 19)
GO


-- ----------------------------
-- Indexes structure for table admin_operation_logs
-- ----------------------------
CREATE NONCLUSTERED INDEX [idx_admin_logs_admin_id]
ON [dbo].[admin_operation_logs] (
  [admin_id] ASC
)
GO

CREATE NONCLUSTERED INDEX [idx_admin_logs_operation_time]
ON [dbo].[admin_operation_logs] (
  [operation_time] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table admin_operation_logs
-- ----------------------------
ALTER TABLE [dbo].[admin_operation_logs] ADD CONSTRAINT [PK__admin_op__3213E83F7A94F9D9] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for admin_users
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[admin_users]', RESEED, 14)
GO


-- ----------------------------
-- Indexes structure for table admin_users
-- ----------------------------
CREATE NONCLUSTERED INDEX [idx_admin_users_username]
ON [dbo].[admin_users] (
  [username] ASC
)
GO


-- ----------------------------
-- Uniques structure for table admin_users
-- ----------------------------
ALTER TABLE [dbo].[admin_users] ADD CONSTRAINT [UK3fgxk4ewgaxgtgvqwb1jjudj6] UNIQUE NONCLUSTERED ([username] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table admin_users
-- ----------------------------
ALTER TABLE [dbo].[admin_users] ADD CONSTRAINT [PK__admin_us__3213E83F0AA80125] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for assistant_message
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[assistant_message]', RESEED, 12)
GO


-- ----------------------------
-- Primary Key structure for table assistant_message
-- ----------------------------
ALTER TABLE [dbo].[assistant_message] ADD CONSTRAINT [PK__assistan__3213E83F3809CCDF] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for assistant_messages
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[assistant_messages]', RESEED, 53)
GO


-- ----------------------------
-- Primary Key structure for table assistant_messages
-- ----------------------------
ALTER TABLE [dbo].[assistant_messages] ADD CONSTRAINT [PK__assistan__3213E83F59CC8345] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for bank_card
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[bank_card]', RESEED, 5)
GO


-- ----------------------------
-- Indexes structure for table bank_card
-- ----------------------------
CREATE NONCLUSTERED INDEX [idx_bank_card_user_id]
ON [dbo].[bank_card] (
  [user_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table bank_card
-- ----------------------------
ALTER TABLE [dbo].[bank_card] ADD CONSTRAINT [PK__bank_car__3213E83F58B4FA5D] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for bill
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[bill]', RESEED, 19)
GO


-- ----------------------------
-- Indexes structure for table bill
-- ----------------------------
CREATE NONCLUSTERED INDEX [idx_bill_user_id]
ON [dbo].[bill] (
  [user_id] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table bill
-- ----------------------------
ALTER TABLE [dbo].[bill] ADD CONSTRAINT [PK__bill__3213E83FFCA46B38] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for collection_qr_code
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[collection_qr_code]', RESEED, 12)
GO


-- ----------------------------
-- Primary Key structure for table collection_qr_code
-- ----------------------------
ALTER TABLE [dbo].[collection_qr_code] ADD CONSTRAINT [PK__collecti__3213E83F3C874616] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for collectionqrcode
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[collectionqrcode]', RESEED, 199)
GO


-- ----------------------------
-- Primary Key structure for table collectionqrcode
-- ----------------------------
ALTER TABLE [dbo].[collectionqrcode] ADD CONSTRAINT [PK__collecti__3213E83F9BC27A2B] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for discount_policies
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[discount_policies]', RESEED, 8)
GO


-- ----------------------------
-- Primary Key structure for table discount_policies
-- ----------------------------
ALTER TABLE [dbo].[discount_policies] ADD CONSTRAINT [PK__discount__3213E83F0A31F302] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for fintech_users
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[fintech_users]', RESEED, 11)
GO


-- ----------------------------
-- Indexes structure for table fintech_users
-- ----------------------------
CREATE NONCLUSTERED INDEX [idx_fintech_users_username]
ON [dbo].[fintech_users] (
  [username] ASC
)
GO

CREATE NONCLUSTERED INDEX [idx_fintech_users_phone]
ON [dbo].[fintech_users] (
  [phone] ASC
)
GO


-- ----------------------------
-- Uniques structure for table fintech_users
-- ----------------------------
ALTER TABLE [dbo].[fintech_users] ADD CONSTRAINT [UKnnjim74l0l2athpgqx9gfc0v] UNIQUE NONCLUSTERED ([username] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Checks structure for table fintech_users
-- ----------------------------
ALTER TABLE [dbo].[fintech_users] ADD CONSTRAINT [CK__fintech_u__statu__73BA3083] CHECK ([status]='REJECTED' OR [status]='PENDING_AUDIT' OR [status]='INACTIVE' OR [status]='ACTIVE')
GO


-- ----------------------------
-- Primary Key structure for table fintech_users
-- ----------------------------
ALTER TABLE [dbo].[fintech_users] ADD CONSTRAINT [PK__fintech___3213E83FB65E687A] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for gate_events
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[gate_events]', RESEED, 10)
GO


-- ----------------------------
-- Indexes structure for table gate_events
-- ----------------------------
CREATE NONCLUSTERED INDEX [idx_gate_events_gate_id]
ON [dbo].[gate_events] (
  [gate_id] ASC
)
GO

CREATE NONCLUSTERED INDEX [idx_gate_events_user_id]
ON [dbo].[gate_events] (
  [user_id] ASC
)
GO

CREATE NONCLUSTERED INDEX [idx_gate_events_event_time]
ON [dbo].[gate_events] (
  [event_time] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table gate_events
-- ----------------------------
ALTER TABLE [dbo].[gate_events] ADD CONSTRAINT [PK__gate_eve__3213E83FE8137CE0] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for gates
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[gates]', RESEED, 10)
GO


-- ----------------------------
-- Indexes structure for table gates
-- ----------------------------
CREATE NONCLUSTERED INDEX [idx_gates_station_id]
ON [dbo].[gates] (
  [station_id] ASC
)
GO

CREATE NONCLUSTERED INDEX [idx_gates_gate_code]
ON [dbo].[gates] (
  [gate_code] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table gates
-- ----------------------------
ALTER TABLE [dbo].[gates] ADD CONSTRAINT [PK__gates__3213E83F60B7C4CE] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for payment
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[payment]', RESEED, 50)
GO


-- ----------------------------
-- Indexes structure for table payment
-- ----------------------------
CREATE NONCLUSTERED INDEX [idx_payment_created_at]
ON [dbo].[payment] (
  [created_at] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table payment
-- ----------------------------
ALTER TABLE [dbo].[payment] ADD CONSTRAINT [PK__payment__3213E83F70279CF0] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for stations
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[stations]', RESEED, 12)
GO


-- ----------------------------
-- Indexes structure for table stations
-- ----------------------------
CREATE NONCLUSTERED INDEX [idx_stations_station_code]
ON [dbo].[stations] (
  [station_code] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table stations
-- ----------------------------
ALTER TABLE [dbo].[stations] ADD CONSTRAINT [PK__stations__3213E83FAD3F3B08] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for travel_pass
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[travel_pass]', RESEED, 82)
GO


-- ----------------------------
-- Primary Key structure for table travel_pass
-- ----------------------------
ALTER TABLE [dbo].[travel_pass] ADD CONSTRAINT [PK__travel_p__3213E83F97C7866B] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for travel_record
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[travel_record]', RESEED, 66)
GO


-- ----------------------------
-- Primary Key structure for table travel_record
-- ----------------------------
ALTER TABLE [dbo].[travel_record] ADD CONSTRAINT [PK__travel_r__3213E83F0FD424D0] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for user_account
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[user_account]', RESEED, 19)
GO


-- ----------------------------
-- Indexes structure for table user_account
-- ----------------------------
CREATE NONCLUSTERED INDEX [idx_user_account_username]
ON [dbo].[user_account] (
  [username] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table user_account
-- ----------------------------
ALTER TABLE [dbo].[user_account] ADD CONSTRAINT [PK__user_acc__3213E83F27607115] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for user_audit
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[user_audit]', RESEED, 4)
GO


-- ----------------------------
-- Primary Key structure for table user_audit
-- ----------------------------
ALTER TABLE [dbo].[user_audit] ADD CONSTRAINT [PK__user_aud__3213E83FFB6CD55E] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Foreign Keys structure for table admin_operation_logs
-- ----------------------------
ALTER TABLE [dbo].[admin_operation_logs] ADD CONSTRAINT [fk_admin_logs_admin_id] FOREIGN KEY ([admin_id]) REFERENCES [dbo].[admin_users] ([id]) ON DELETE CASCADE ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table bank_card
-- ----------------------------
ALTER TABLE [dbo].[bank_card] ADD CONSTRAINT [fk_bank_card_user_id] FOREIGN KEY ([user_id]) REFERENCES [dbo].[fintech_users] ([id]) ON DELETE CASCADE ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table bill
-- ----------------------------
ALTER TABLE [dbo].[bill] ADD CONSTRAINT [fk_bill_user_id] FOREIGN KEY ([user_id]) REFERENCES [dbo].[fintech_users] ([id]) ON DELETE CASCADE ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table gates
-- ----------------------------
ALTER TABLE [dbo].[gates] ADD CONSTRAINT [fk_gates_station_id] FOREIGN KEY ([station_id]) REFERENCES [dbo].[stations] ([id]) ON DELETE CASCADE ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table user_audit
-- ----------------------------
ALTER TABLE [dbo].[user_audit] ADD CONSTRAINT [fk_user_audit_user_id] FOREIGN KEY ([user_id]) REFERENCES [dbo].[fintech_users] ([id]) ON DELETE CASCADE ON UPDATE NO ACTION
GO

