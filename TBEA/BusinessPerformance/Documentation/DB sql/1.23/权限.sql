SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (84, '融资业务查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (85, '融资业务录入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (86, '导出质量报告')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF


insert into system_extend_auth (account_id, company_id, auth_type) values
((select id from jygk_account where name='928006'), 1, 84),
((select id from jygk_account where name='928006'), 1, 85),
((select id from jygk_account where name='008382'), 2, 84),
((select id from jygk_account where name='008382'), 2, 85),
((select id from jygk_account where name='565839'), 2, 84),
((select id from jygk_account where name='565839'), 2, 85),
((select id from jygk_account where name='818099'), 2, 84),
((select id from jygk_account where name='818099'), 2, 85),
((select id from jygk_account where name='892603'), 3, 84),
((select id from jygk_account where name='892603'), 3, 85),
((select id from jygk_account where name='601858'), 4, 84),
((select id from jygk_account where name='601858'), 4, 85),
((select id from jygk_account where name='902991'), 5, 84),
((select id from jygk_account where name='902991'), 5, 85),
((select id from jygk_account where name='232321'), 6, 84),
((select id from jygk_account where name='232321'), 6, 85),

((select id from jygk_account where name='095961'), 7, 84),
((select id from jygk_account where name='095961'), 7, 85),

((select id from jygk_account where name='256118'), 8, 84),
((select id from jygk_account where name='256118'), 8, 85),
((select id from jygk_account where name='838582'), 9, 84),
((select id from jygk_account where name='838582'), 9, 85),
((select id from jygk_account where name='300022'), 9, 84),
((select id from jygk_account where name='300022'), 9, 85),

((select id from jygk_account where name='899628'), 10, 84),
((select id from jygk_account where name='899628'), 10, 85),
((select id from jygk_account where name='282208'), 11, 84),
((select id from jygk_account where name='282208'), 11, 85),
((select id from jygk_account where name='235021'), 301, 84),
((select id from jygk_account where name='235021'), 301, 85),

((select id from jygk_account where name='130182'), null, 86),
((select id from jygk_account where name='沈变质量部'), null, 86),
((select id from jygk_account where name='衡变质量部'), null, 86),
((select id from jygk_account where name='鲁缆质量部'), null, 86),
((select id from jygk_account where name='新缆质量部'), null, 86),
((select id from jygk_account where name='德缆质量部'), null, 86),
((select id from jygk_account where name='155165'), null, 86),
((select id from jygk_account where name='天变质量部'), null, 86),
((select id from jygk_account where name='新变质量部'), null, 86)


