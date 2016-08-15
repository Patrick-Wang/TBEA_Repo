/***************************************************************************** zl_yclhglqk_cldl
	clmc
id start end xmgs cpzl cpxh bzscl bzfhl bzxsjg
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'zl_yclhglqk_cldl')
DROP TABLE zl_yclhglqk_cldl
CREATE TABLE [dbo].[zl_yclhglqk_cldl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50),
	[dw] [varchar](20),
	[cy] int   ---- 0 byq   1 xl
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[zl_yclhglqk_cldl] ON
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (0,N'层压木', N'T', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (1,N'绝缘纸板', N'T', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (2,N'绝缘成型件', N'个', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (3,N'硅钢片', N'T', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (4,N'电磁线', N'T', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (5,N'变压器油', N'T', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (6,N'套  管', N'支', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (7,N'开  关', N'个', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (8,N'散热器', N'个', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (9,N'冷却器', N'个', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (10,N'阀类', N'个', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (11,N'突发压力继电器', N'个', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (12,N'油位计', N'个', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (13,N'温度计', N'个', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (14,N'控制柜', N'个', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (15,N'风  机', N'个', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (16,N'波纹储油柜', N'个', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (17,N'压力释放阀', N'个', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (18,N'气体继电器', N'个', 0)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (19,N'铜板',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (20,N'铜杆',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (21,N'铜带',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (22,N'铝锭',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (23,N'铝杆',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (24,N'铝带',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (25,N'钢带',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (26,N'PVC树脂',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (27,N'PVC绝缘料',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (28,N'PVC护套料',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (29,N'10kV交联绝缘料',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (30,N'10kV内屏蔽料',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (31,N'10kV外屏蔽料',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (32,N'35kV交联绝缘料',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (33,N'35kV内屏蔽料',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (34,N'35kV外屏蔽料',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (35,N'硅烷绝缘料',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (36,N'镀锌钢绞线和钢丝',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (37,N'高压交联绝缘料',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (38,N'高压交联屏蔽料',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (39,N'PE护套料',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (40,N'硅橡胶',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (41,N'TPU料',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (42,N'非金属带材类',N'kg', 1)
INSERT [dbo].[zl_yclhglqk_cldl] ([id], [name], [dw], [cy])	VALUES (43,N'填充类',N'kg', 1)
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