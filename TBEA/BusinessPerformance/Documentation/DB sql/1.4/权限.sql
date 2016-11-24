insert into system_extend_auth (account_id, company_id, auth_type) values
(226, null, 59),
(148, null, 59),
(150 , null, 59),
(119, null, 59),
(160 , null, 59)


SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (57, '市场部数据查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (58, '市场部数据录入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (59, '工程一张表查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (60, '工程一张表导入')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF