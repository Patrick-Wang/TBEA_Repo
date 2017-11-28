SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (95, '导出工作报告')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (96, '进出口合同查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (97, '进出口合同导入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (98, '招标采购进出口合同查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (99, '资金中心进出口合同查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (100, '招标采购进出口合同录入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (101, '资金中心进出口合同录入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (102, '进出口合同录入')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF

EXEC	addAuth	'沈变公司',	95	,	'1'
EXEC	addAuth	'衡变公司',	95	,	'2'
EXEC	addAuth	'新变厂',	95	,	'3'
EXEC	addAuth	'天变公司',	95	,	'301'
EXEC	addAuth	'鲁缆公司',	95	,	'4'
EXEC	addAuth	'新缆厂',	95	,	'5'
EXEC	addAuth	'德缆公司',	95	,	'6'

EXEC	addAuth	'130182',	95	,	'1,2,3,301,4,5,6'
EXEC	addAuth	'155165',	95	,	'1,2,3,301,4,5,6'


EXEC	addAuth	'130182',	96	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	97	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	102	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	98	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	99	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	100	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	101	,	'1,2,3,301,4,5,6,9,10,11,12'