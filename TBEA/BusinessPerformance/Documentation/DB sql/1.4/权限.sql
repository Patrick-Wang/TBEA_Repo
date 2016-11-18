insert into system_extend_auth (account_id, company_id, auth_type) values
(147, 1, 57),
(147, 2, 57),
(147, 3, 57),
(147, 301, 57),
(147, 4, 57),
(147, 5, 57),
(147, 6, 57),
(147, 1, 58),
(147, 2, 58),
(147, 3, 58),
(147, 301, 58),
(147, 4, 58),
(147, 5, 58),
(147, 6, 58)

SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (57, '市场部数据查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (58, '市场部数据录入')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF