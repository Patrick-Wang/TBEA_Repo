﻿SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (69, '库存查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (70, '库存导入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (71, '国内市场项目签约明细查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (72, '国内市场项目签约明细导入')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF


insert into system_extend_auth (account_id, company_id, auth_type) values
((select id from jygk_account where name='130182'), 1, 69),
((select id from jygk_account where name='130182'), 2, 69),
((select id from jygk_account where name='130182'), 3, 69),
((select id from jygk_account where name='130182'), 4, 69),
((select id from jygk_account where name='130182'), 5, 69),
((select id from jygk_account where name='130182'), 6, 69),
((select id from jygk_account where name='沈变公司'), 1, 69),
((select id from jygk_account where name='衡变公司'), 2, 69),
((select id from jygk_account where name='新变厂'), 3, 69),
((select id from jygk_account where name='鲁缆公司'), 4, 69),
((select id from jygk_account where name='新缆厂'), 5, 69),
((select id from jygk_account where name='德缆公司'), 6, 69),
((select id from jygk_account where name='沈变公司'), 1, 70),
((select id from jygk_account where name='衡变公司'), 2, 70),
((select id from jygk_account where name='新变厂'), 3, 70),
((select id from jygk_account where name='鲁缆公司'), 4, 70),
((select id from jygk_account where name='新缆厂'), 5, 70),
((select id from jygk_account where name='德缆公司'), 6, 70),
((select id from jygk_account where name='130182'), 1, 71),
((select id from jygk_account where name='130182'), 2, 71),
((select id from jygk_account where name='130182'), 3, 71),
((select id from jygk_account where name='130182'), 4, 71),
((select id from jygk_account where name='130182'), 5, 71),
((select id from jygk_account where name='130182'), 6, 71),
((select id from jygk_account where name='沈变公司'), 1, 71),
((select id from jygk_account where name='衡变公司'), 2, 71),
((select id from jygk_account where name='新变厂'), 3, 71),
((select id from jygk_account where name='鲁缆公司'), 4, 71),
((select id from jygk_account where name='新缆厂'), 5, 71),
((select id from jygk_account where name='德缆公司'), 6, 71),
((select id from jygk_account where name='沈变公司'), 1, 72),
((select id from jygk_account where name='衡变公司'), 2, 72),
((select id from jygk_account where name='新变厂'), 3, 72),
((select id from jygk_account where name='鲁缆公司'), 4, 72),
((select id from jygk_account where name='新缆厂'), 5, 72),
((select id from jygk_account where name='德缆公司'), 6, 72)

