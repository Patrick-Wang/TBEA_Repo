SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (66, '人力信息查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (67, '新产品信息查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (68, '人力信息导入')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF


insert into system_extend_auth (account_id, company_id, auth_type) values
(204, null, 66),
(204, null, 67),
(204, null, 68)
