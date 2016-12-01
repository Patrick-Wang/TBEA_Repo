SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (63, '众合质量指标录入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (64, '众合质量指标查看')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF


insert into system_extend_auth (account_id, company_id, auth_type) values
(102, 1301,63),
(103, 1302,63),
(104, 1303,63),
(105, 1304,63),
(106, 1305,63),
(107, 1306,63),

((select id from jygk_account where name = '130182'), 1301,64),
((select id from jygk_account where name = '130182'), 1302,64),
((select id from jygk_account where name = '130182'), 1303,64),
((select id from jygk_account where name = '130182'), 1304,64),
((select id from jygk_account where name = '130182'), 1305,64),
((select id from jygk_account where name = '130182'), 1306,64),

((select id from jygk_account where name = '538890'), 1301,64),
((select id from jygk_account where name = '538890'), 1302,64),
((select id from jygk_account where name = '538890'), 1303,64),
((select id from jygk_account where name = '538890'), 1304,64),
((select id from jygk_account where name = '538890'), 1305,64),
((select id from jygk_account where name = '538890'), 1306,64),

((select id from jygk_account where name = '629061'), 1301,64),
((select id from jygk_account where name = '629061'), 1302,64),
((select id from jygk_account where name = '629061'), 1303,64),
((select id from jygk_account where name = '629061'), 1304,64),
((select id from jygk_account where name = '629061'), 1305,64),
((select id from jygk_account where name = '629061'), 1306,64),

((select id from jygk_account where name = '众和公司'), 1301,64),
((select id from jygk_account where name = '众和公司'), 1302,64),
((select id from jygk_account where name = '众和公司'), 1303,64),
((select id from jygk_account where name = '众和公司'), 1304,64),
((select id from jygk_account where name = '众和公司'), 1305,64),
((select id from jygk_account where name = '众和公司'), 1306,64)