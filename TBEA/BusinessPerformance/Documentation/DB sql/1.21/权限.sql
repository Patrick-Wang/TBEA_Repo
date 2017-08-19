insert into jygk_account (name, password, role, deprecated) values
('西科质量部', '1234', 4, 0)

SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (78, '内外部质量导入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (79, '天池能源有限责任公司昌热电厂日报')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF


insert into system_extend_auth (account_id, company_id, auth_type) values
((select id from jygk_account where name='西科质量部'), 903, 20),
((select id from jygk_account where name='西科质量部'), 903, 21),
((select id from jygk_account where name='西科质量部'), 903, 43),
((select id from jygk_account where name='西科质量部'), 903, 42),
((select id from jygk_account where name='西科质量部'), 903, 40),
((select id from jygk_account where name='西科质量部'), 903, 78),
((select id from jygk_account where name='鲁缆质量部'), 4, 78),
((select id from jygk_account where name='新变质量部'), 3, 78)