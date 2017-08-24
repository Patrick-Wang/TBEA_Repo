SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (84, '融资业务查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (85, '融资业务录入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (86, '导出质量报告')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF


insert into system_extend_auth (account_id, company_id, auth_type) values
((select id from jygk_account where name='130182'), 1, 84),
((select id from jygk_account where name='130182'), 1, 85),
((select id from jygk_account where name='130182'), 2, 84),
((select id from jygk_account where name='130182'), 2, 85),
((select id from jygk_account where name='130182'), 3, 84),
((select id from jygk_account where name='130182'), 3, 85),
((select id from jygk_account where name='130182'), 4, 84),
((select id from jygk_account where name='130182'), 4, 85),
((select id from jygk_account where name='130182'), 5, 84),
((select id from jygk_account where name='130182'), 5, 85),
((select id from jygk_account where name='130182'), 6, 84),
((select id from jygk_account where name='130182'), 6, 85),
((select id from jygk_account where name='130182'), null, 86),
((select id from jygk_account where name='新变质量部'), 3, 84),
((select id from jygk_account where name='新变质量部'), 3, 85),
((select id from jygk_account where name='新变质量部'), null, 86)


