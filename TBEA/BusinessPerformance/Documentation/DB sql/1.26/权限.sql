update auth_instruction set instruction = '人力信息查看' where id = 66
update auth_instruction set instruction = '新产品信息查看' where id = 67

SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (88, '用户权限管理')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF



SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (89, '变压器预警台账录入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (90, '变压器预警台账查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (91, '线缆预警台账录入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (92, '线缆预警台账查看')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF


