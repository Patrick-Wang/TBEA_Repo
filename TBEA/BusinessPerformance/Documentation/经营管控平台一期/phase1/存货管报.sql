/***************************************************************************** 积压库存项目
id name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_chgb_jykcxm')
DROP TABLE identifier_chgb_jykcxm
CREATE TABLE [dbo].[identifier_chgb_jykcxm](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[identifier_chgb_jykcxm] ON
INSERT [dbo].[identifier_chgb_jykcxm] ([id], [name])	VALUES (0,N'积压原材料')
INSERT [dbo].[identifier_chgb_jykcxm] ([id], [name])	VALUES (1,N'积压半成品')
INSERT [dbo].[identifier_chgb_jykcxm] ([id], [name])	VALUES (2,N'积压商品')
SET IDENTITY_INSERT [dbo].[identifier_chgb_jykcxm] OFF

/***************************************************************************** 表1 存货账面表
				账面净额	坏账准备	原值
id	nf	yf	dwid	zmje	hzzb	yz
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'chgb_chzm')
DROP TABLE chgb_chzm
CREATE  TABLE [dbo].[chgb_chzm](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[zmje] [numeric](18, 4) NULL,
	[hzzb] [numeric](18, 4) NULL,
	[yz] [numeric](18, 4) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/***************************************************************************** 表2 积压库存表
					上月余额	本月新增	本月处置	期末余额	
id	nf	yf	dwid	itemid	syye	byxz	bycz	qmye
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'chgb_jykc')
DROP TABLE chgb_jykc
CREATE TABLE [dbo].[chgb_jykc](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[jykcxmid] [int] not NULL,
	[syye] [numeric](18, 4) NULL,
	[byxz] [numeric](18, 4) NULL,
	[bycz] [numeric](18, 4) NULL,
	[qmye] [numeric](18, 4) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/***************************************************************************** 表3 存货账龄变化情况(滚动)
				5年以上	4-5年	3-4年	2-3年	1-2年	1年以内	
ID	nf	yf	dwid	zl5nys	zl4z5n	zl3z4n	zl2z3n	zl1z2n	zl1nyn	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'chgb_chzlbhqk')
DROP TABLE chgb_chzlbhqk
CREATE TABLE [dbo].[chgb_chzlbhqk](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[zl5nys] [numeric](18, 4) NULL,
	[zl4z5n] [numeric](18, 4) NULL,
	[zl3z4n] [numeric](18, 4) NULL,
	[zl2z3n] [numeric](18, 4) NULL,
	[zl1z2n] [numeric](18, 4) NULL,
	[zl1nyn] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/***************************************************************************** 表4 存货性质情况（滚动）
				原材料	半成品	实际库存商品	已发货未开票	期货浮动盈亏(盈+，亏-)	期货平仓盈亏(盈-，亏+)	未发货已开票	其他	
ID	nf	yf	dwid	ycl	bcp	sjkcsp	yfhwkp	qhfdyk	qhpcyk	wfhykp	qt	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'chgb_chxzqk')
DROP TABLE chgb_chxzqk
CREATE TABLE [dbo].[chgb_chxzqk](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[ycl] [numeric](18, 4) NULL,
	[bcp] [numeric](18, 4) NULL,
	[sjkcsp] [numeric](18, 4) NULL,
	[yfhwkp] [numeric](18, 4) NULL,
	[qhfdyk] [numeric](18, 4) NULL,
	[qhpcyk] [numeric](18, 4) NULL,
	[wfhykp] [numeric](18, 4) NULL,
	[qt] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/***************************************************************************** 表5 能源存货
				原材料	其中：油料	备品备件	库存商品	生产成本-待配比土方	发出商品	低耗
ID	nf	yf	dwid	ycl	yl	bpbj	kcsp	sccbDpbtf	fcsp	dh	hj
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'chgb_nych')
DROP TABLE chgb_nych
CREATE TABLE [dbo].[chgb_nych](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[ycl] [numeric](18, 4) NULL,
	[yl] [numeric](18, 4) NULL,
	[bpbj] [numeric](18, 4) NULL,
	[kcsp] [numeric](18, 4) NULL,
	[sccbDpbtf] [numeric](18, 4) NULL,
	[fcsp] [numeric](18, 4) NULL,
	[dh] [numeric](18, 4) NULL,
	[hj] [numeric](18, 4) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
