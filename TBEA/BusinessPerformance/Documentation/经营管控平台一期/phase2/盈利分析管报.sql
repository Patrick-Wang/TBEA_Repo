  /***************************************************************************** 产品名称
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_ylfxgb_cpmc')
DROP TABLE identifier_ylfxgb_cpmc
CREATE TABLE [dbo].[identifier_ylfxgb_cpmc](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET IDENTITY_INSERT [dbo].[identifier_ylfxgb_cpmc] ON

INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (1 ,N'其他')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (2 ,N'成套项目(国内工程)')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (3 ,N'        输变电-EPC模式')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (4 ,N'        输变电-BT模式')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (5 ,N'        输变电-其他模式')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (6 ,N'成套项目(国际工程)')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (7 ,N'        输变电-EPC模式')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (8 ,N'        输变电-BT模式')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (9 ,N'        输变电-其他模式')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (10 ,N'物流贸易')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (11 ,N'服务类')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (12 ,N'        会议费收入')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (13 ,N'        物业费收入')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (14 ,N'        劳务收入')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (15 ,N'        花苗收入')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (16 ,N'        住宿收入')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (17 ,N'        机票代理收入')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (18 ,N'        日用百货收入')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (19 ,N'        电费收入')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (20 ,N'        水汽暖收入')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (21 ,N'        餐饮收入')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (22 ,N'        其他收入')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (23 ,N'交流变压器')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (24 ,N'        35KV及以下')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (25 ,N'        66-110KV')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (26 ,N'        220KV')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (27 ,N'        330KV')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (28 ,N'        500KV')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (29 ,N'        750kV')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (30 ,N'        1000kV')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (31 ,N'直流变压器')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (32 ,N'        ±400kv及以下')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (33 ,N'        ±500kv')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (34 ,N'        ±600kv')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (35 ,N'        ±800kv')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (36 ,N'        平波电抗器')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (37 ,N'电抗器')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (38 ,N'        330kV及以下')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (39 ,N'        500kV电')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (40 ,N'        750kV电')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (41 ,N'        1000kV电')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (42 ,N'干式变压器')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (43 ,N'        F级干变')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (44 ,N'        H级干变')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (45 ,N'        箱式变电站')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (46 ,N'        干式电抗器')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (47 ,N'特种变压器')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (48 ,N'        隔爆变')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (49 ,N'        整流变')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (50 ,N'        牵引变')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (51 ,N'        油田变')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (52 ,N'        其它')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (53 ,N'延伸类')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (54 ,N'        配电自动化')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (55 ,N'        开关')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (56 ,N'        套管')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (57 ,N'        互感器')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (58 ,N'        维修备件')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (59 ,N'导线')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (60 ,N'布电线')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (61 ,N'架空线')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (62 ,N'控制电缆')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (63 ,N'交联电缆')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (64 ,N'电力电缆')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (65 ,N'电磁线')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (66 ,N'特种电缆')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (67 ,N'电缆附件')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (68 ,N'铜杆')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (69 ,N'铝杆')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (70 ,N'PVC料')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (71 ,N'工装轮')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (72 ,N'        非晶合金变')
INSERT [dbo].[identifier_ylfxgb_cpmc] ([id], [name]) VALUES (73 ,N'        卷铁芯')


SET IDENTITY_INSERT [dbo].[identifier_ylfxgb_cpmc] OFF


 /***************************************************************************** 材料名称
id name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_ylfxgb_clmc')
DROP TABLE identifier_ylfxgb_clmc
CREATE TABLE [dbo].[identifier_ylfxgb_clmc](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[identifier_ylfxgb_clmc] ON
INSERT [dbo].[identifier_ylfxgb_clmc] ([id], [name]) VALUES (1,N'铜')
INSERT [dbo].[identifier_ylfxgb_clmc] ([id], [name]) VALUES (2,N'铝')
INSERT [dbo].[identifier_ylfxgb_clmc] ([id], [name]) VALUES (3,N'交联料')
INSERT [dbo].[identifier_ylfxgb_clmc] ([id], [name]) VALUES (4,N'PVC料')
INSERT [dbo].[identifier_ylfxgb_clmc] ([id], [name]) VALUES (5,N'钢带')
INSERT [dbo].[identifier_ylfxgb_clmc] ([id], [name]) VALUES (6,N'钢丝')
INSERT [dbo].[identifier_ylfxgb_clmc] ([id], [name]) VALUES (7,N'橡胶')
INSERT [dbo].[identifier_ylfxgb_clmc] ([id], [name]) VALUES (8,N'硅钢片')
INSERT [dbo].[identifier_ylfxgb_clmc] ([id], [name]) VALUES (9,N'铜线')
INSERT [dbo].[identifier_ylfxgb_clmc] ([id], [name]) VALUES (10,N'纸板')
INSERT [dbo].[identifier_ylfxgb_clmc] ([id], [name]) VALUES (11,N'钢板')
INSERT [dbo].[identifier_ylfxgb_clmc] ([id], [name]) VALUES (12,N'变压器油')
SET IDENTITY_INSERT [dbo].[identifier_ylfxgb_clmc] OFF


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
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'ylfxgb_ylfx_wlyddmle_ncdr')
DROP TABLE ylfxgb_ylfx_wlyddmle_ncdr
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


 /***************************************************************************** 原材料报废情况
			产业	材料名称	当月（吨）		废料率	
					领用量	废料		
id	nf	yf	cy	clid	lyl	fl	fll	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'ylfxgb_yclbfqk')
DROP TABLE ylfxgb_yclbfqk
CREATE TABLE [dbo].[ylfxgb_yclbfqk](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[cy] [int] not NULL,
	[clid] [int] not NULL,
	[lyl] [numeric](18, 4) NULL,
	[fl] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

