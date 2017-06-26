SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (73, '财务税费导入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (74, '财务税费查看')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF

insert into system_extend_auth (account_id, auth_type) values
((select id from jygk_account where name='130182'), 73),
((select id from jygk_account where name='130182'), 74)