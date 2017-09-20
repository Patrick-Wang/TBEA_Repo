update auth_instruction set instruction = '人力信息查看' where id = 66
update auth_instruction set instruction = '新产品信息查看' where id = 67

SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (88, '用户权限管理')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF

