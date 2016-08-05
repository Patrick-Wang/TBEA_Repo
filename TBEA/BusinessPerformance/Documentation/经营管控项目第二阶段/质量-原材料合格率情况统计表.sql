/***************************************************************************** zl_yclhglqk_cldl
	clmc
id start end xmgs cpzl cpxh bzscl bzfhl bzxsjg
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'zl_yclhglqk_cldl')
DROP TABLE zl_yclhglqk_cldl
CREATE TABLE [dbo].[zl_yclhglqk_cldl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50),
	[dw] [varchar](20)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[zl_yclhglqk_cldl] ON
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (0,N'层压木', N'T')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (1,N'绝缘纸板', N'T')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (2,N'绝缘成型件', N'个')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (3,N'硅钢片', N'T')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (4,N'电磁线', N'T')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (5,N'变压器油', N'T')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (6,N'套  管', N'支')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (7,N'开  关', N'个')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (8,N'散热器', N'个')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (9,N'冷却器', N'个')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (10,N'阀类', N'个')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (11,N'突发压力继电器', N'个')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (12,N'油位计', N'个')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (13,N'温度计', N'个')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (14,N'控制柜', N'个')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (15,N'风  机', N'个')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (16,N'波纹储油柜', N'个')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (17,N'压力释放阀', N'个')
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw])	VALUES (18,N'气体继电器', N'个')

SET IDENTITY_INSERT [dbo].[zl_yclhglqk_cldl] OFF

/***************************************************************************** zl_yclhglqk
	clmc
id start end xmgs cpzl cpxh bzscl bzfhl bzxsjg
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'zl_yclhglqk')
DROP TABLE zl_yclhglqk
CREATE TABLE [dbo].[zl_yclhglqk](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int],
	[yf] [int],
	[dwid] [int],
	[clid] [int],
	[hgs] [int],
	[zs] [int],
	[zt] [int]
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]