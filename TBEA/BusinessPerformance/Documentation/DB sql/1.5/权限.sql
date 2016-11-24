insert into system_extend_auth (account_id, company_id, auth_type) values
(	147	,	1	,	61	)	,
(	147	,	2	,	61	)	,
(	147	,	3	,	61	)

SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (61, '三单对比查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (62, '三单对比导入')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF
