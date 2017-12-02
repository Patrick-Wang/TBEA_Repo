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

EXEC	addAuth	'anfengling',	95	,	'1,2,3,301,4,5,6'


EXEC	addAuth	'anfengling',	96	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'anfengling',	97	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'anfengling',	102	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'anfengling',	98	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'anfengling',	99	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'anfengling',	100	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'anfengling',	101	,	'1,2,3,301,4,5,6,9,10,11,12'