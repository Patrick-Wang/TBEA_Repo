 /***************************************************************************** 盈利分析--完工产品盈利能力水平测算--NC导入+人工录入
			产业	统计方式	产品	毛利率
id	nf	yf	cy	tjfs	cp	mll
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'ylfxgb_ylfx_wgcpmll')
DROP TABLE ylfxgb_ylfx_wgcpmll
CREATE TABLE [dbo].[ylfxgb_ylfx_wgcpmll](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[tjfs] [int] not NULL,	-- 11->变压器综合   12->变压器按电压等级分类	13->变压器按产品分类   14->变压器按产品特殊1	15->线缆综合	16->线缆按产品分类
	[cpid] [int] not NULL,
	[sr] [numeric](18, 4) NULL,
	[cb] [numeric](18, 4) NULL,
	[zt] [int]
	
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


 /***************************************************************************** 盈利分析--未履约订单毛利水平测算--人工录入
			产业	统计方式	产品	毛利额
id	nf	yf	cy	tjfs	cp	mle
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'ylfxgb_ylfx_wlyddmle_rglr')
DROP TABLE ylfxgb_ylfx_wlyddmle_rglr
CREATE TABLE  [dbo].[ylfxgb_ylfx_wlyddmle_rglr](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[tjfs] [int] not NULL,	-- 11->变压器综合   12->变压器按电压等级分类	13->变压器按产品分类   14->线缆综合	15->线缆按产品分类
	[cpid] [int] not NULL,
	[cb] [numeric](18, 4) not NULL,
	[sr] [numeric](18, 4) not NULL,
	[zt] [int]
	
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]



 /***************************************************************************** 大宗材料控成本
			材料	期货盈亏（万元）	市场现货月均价（元/吨）	采购月均价（元/吨）（摊入当月期货盈亏）	三项费用保本价（元/吨）	目标利润倒算价（元/吨）	采购量（吨）	期现货合计盈亏		
										指导价格按照保本价（万元）	指导价格按照目标利润价（万元）	
id	nf	yf	clid	qhyk	scxhyjj	cgyjj	sxfybbj	mblrdsj	cgl	zdjazbbj	zdjazmulr	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'ylfxgb_dzclkcb')
DROP TABLE ylfxgb_dzclkcb
CREATE TABLE [dbo].[ylfxgb_dzclkcb](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[clid] [int] not NULL,
	[qhyk] [numeric](18, 4) NULL,
	[scxhyjj] [numeric](18, 4) NULL,
	[cgyjj] [numeric](18, 4) NULL,
	[sxfybbj] [numeric](18, 4) NULL,
	[mblrdsj] [numeric](18, 4) NULL,
	[cgl] [numeric](18, 4) NULL,
	[zdjazbbj] [numeric](18, 4) NULL,
	[zdjazmulr] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

 /***************************************************************************** 单位 材料
					单位	材料		
id dwid clid
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'ylfxgb_yclbfqk_dw_ref_cl')
DROP TABLE ylfxgb_yclbfqk_dw_ref_cl
CREATE TABLE [dbo].[ylfxgb_yclbfqk_dw_ref_cl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[dwid] [int] not NULL,
	[clid] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
SET IDENTITY_INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ON
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (1, 1, 8)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (2, 1, 9)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (3, 1, 10)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (4, 1, 11)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (5, 1, 12)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (6, 2, 8)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (7, 2, 9)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (8, 2, 10)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (9, 2, 11)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (10, 2, 12)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (11, 3, 8)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (12, 3, 9)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (13, 3, 10)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (14, 3, 11)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (15, 3, 12)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (16, 301, 8)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (17, 301, 9)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (18, 301, 10)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (19, 301, 11)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (20, 301, 12)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (21, 4, 1)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (22, 4, 2)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (23, 4, 3)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (24, 4, 4)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (25, 4, 5)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (26, 4, 6)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (27, 4, 7)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (28, 5, 1)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (29, 5, 2)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (30, 5, 3)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (31, 5, 4)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (32, 5, 5)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (33, 5, 6)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (34, 5, 7)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (35, 6, 1)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (36, 6, 2)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (37, 6, 3)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (38, 6, 4)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (39, 6, 5)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (40, 6, 6)
INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] ([id], [dwid], [clid]) VALUES (41, 6, 7)

SET IDENTITY_INSERT [dbo].[ylfxgb_yclbfqk_dw_ref_cl] OFF
 /***************************************************************************** 原材料报废情况
					领用量	废料		
id	nf	yf	cy	clid	lyl	fl	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'ylfxgb_yclbfqk')
DROP TABLE ylfxgb_yclbfqk
CREATE TABLE [dbo].[ylfxgb_yclbfqk](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[clid] [int] not NULL,
	[lyl] [numeric](18, 4) NULL,
	[fl] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

