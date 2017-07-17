SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (77, '主页数据地图查看')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF

insert into system_extend_auth (account_id, auth_type) values
((select id from jygk_account where name='130182'), 77),
((select id from jygk_account where name='155165'), 77),
((select id from jygk_account where name='538890'), 77),
((select id from jygk_account where name='255506'), 77),
((select id from jygk_account where name='655556'), 77),
((select id from jygk_account where name='612559'), 77)