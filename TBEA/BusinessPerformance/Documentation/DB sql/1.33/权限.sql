SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (104, '外汇收支查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (105, '外汇收支录入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (106, '招标采购外汇收支查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (107, '资金中心外汇收支查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (108, '招标采购外汇收支录入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (109, '资金中心外汇收支录入')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF


EXEC	addAuth	'130182',	104	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	106	,	''
EXEC	addAuth	'130182',	107	,	''
EXEC	addAuth	'沈变公司',	105	,	'1'
EXEC	addAuth	'沈变公司',	104	,	'1'
EXEC	addAuth	'新变厂',	105	,	'3'
EXEC	addAuth	'新变厂',	104	,	'3'
EXEC	addAuth	'鲁缆公司',	105	,	'4'
EXEC	addAuth	'鲁缆公司',	104	,	'4'