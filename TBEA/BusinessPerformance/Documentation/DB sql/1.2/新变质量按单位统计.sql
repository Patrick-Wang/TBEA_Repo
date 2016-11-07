update [dbo].[cpzlqk_adwtjjg_byq] set [formul] = N'#40+#46+#50+#53+#54' where id = 59
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cpzlqk_dwmc')
DROP TABLE cpzlqk_dwmc
CREATE TABLE [dbo].[cpzlqk_dwmc](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[dwid] [int] not NULL,
	[nameid] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[cpzlqk_dwmc] ON
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (0, 1, 50)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (1, 2, 51)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (2, 3, 52)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (3, 301, 53)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (4, 4, 54)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (5, 5, 55)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (6, 6, 56)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (7, 102, 69)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (8, 201, 70)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (9, 302, 71)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (10, 303, 72)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (11, 301, 73)
SET IDENTITY_INSERT [dbo].[cpzlqk_dwmc] OFF    