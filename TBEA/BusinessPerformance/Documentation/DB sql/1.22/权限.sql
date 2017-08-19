SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (80, '系统管理员')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (81, '众和公司')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (82, '天池能源日报录入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (83, '天池能源日报月报查看')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF


insert into system_extend_auth (account_id, company_id, auth_type) values
((select id from jygk_account where name='admin'), null, 80),
((select id from jygk_account where name='众和公司'), null, 81),
((select id from jygk_account where name='天池能源'), null, 82),
((select id from jygk_account where name='天池能源'), null, 83)
((select id from jygk_account where name='155165'), null, 83),
((select id from jygk_account where name='130182'), null, 83)