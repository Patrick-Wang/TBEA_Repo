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

