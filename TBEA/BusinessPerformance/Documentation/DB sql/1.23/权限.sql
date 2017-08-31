SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (84, '融资业务查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (85, '融资业务录入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (86, '导出质量报告')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF

insert into system_extend_auth (account_id, company_id, auth_type) values
((select id from jygk_account where name='130182'), null, 86),
((select id from jygk_account where name='沈变质量部'), null, 86),
((select id from jygk_account where name='衡变质量部'), null, 86),
((select id from jygk_account where name='鲁缆质量部'), null, 86),
((select id from jygk_account where name='新缆质量部'), null, 86),
((select id from jygk_account where name='德缆质量部'), null, 86),
((select id from jygk_account where name='155165'), null, 86),
((select id from jygk_account where name='天变质量部'), null, 86),
((select id from jygk_account where name='新变质量部'), null, 86);
