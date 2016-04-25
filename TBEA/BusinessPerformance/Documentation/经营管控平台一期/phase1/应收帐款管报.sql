/***************************************************************************** 表1 应收帐款账面表 
			公司	账面净额	坏账准备	原值
id	nf	yf	dwid	zmje	hzzb	yz
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'yszkgb_yszkzm')
DROP TABLE yszkgb_yszkzm
CREATE TABLE [dbo].[yszkgb_yszkzm](
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


/***************************************************************************** 表2 应收账款账龄变化 
			公司	5年以上	4-5年	3-4年	2-3年	1-2年	1年以内	合计
ID	nf	yf	dwid	zl5nys	zl4z5n	zl3z4n	zl2z3n	zl1z2n	zl1nyn	hj
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'yszkgb_yszkzl')
DROP TABLE yszkgb_yszkzl
CREATE TABLE [dbo].[yszkgb_yszkzl](
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
	[hj] [numeric](18, 4) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/***************************************************************************** 表3 应收账款款项性质情况 
			公司	逾期0-1个月	逾期1-3月	逾期3-6月	逾期6-12月	逾期1年以上	未到期(不含质保金)	未到期质保金	
ID	nf	yf	dwid	yq0z1y	yq1z3y	yq3z6y	yq6z12y	yq1nys	wdq	wdqzbj	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'yszkgb_yszkkxxzqk')
DROP TABLE yszkgb_yszkkxxzqk
CREATE TABLE [dbo].[yszkgb_yszkkxxzqk](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[yq0z1y] [numeric](18, 4) NULL,
	[yq1z3y] [numeric](18, 4) NULL,
	[yq3z6y] [numeric](18, 4) NULL,
	[yq6z12y] [numeric](18, 4) NULL,
	[yq1nys] [numeric](18, 4) NULL,
	[wdq] [numeric](18, 4) NULL,
	[wdqzbj] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/***************************************************************************** 表4 逾期应收账产生因素 
			公司	内部因素	客户资信	滚动付款	项目变化	合同因素	手续办理	诉讼	
ID	nf	yf	dwid	nbys	khzx	gdfk	xmbh	htys	sxbl	ss	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'yszkgb_yqyszcsys')
DROP TABLE yszkgb_yqyszcsys
CREATE TABLE [dbo].[yszkgb_yqyszcsys](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[nbys] [numeric](18, 4) NULL,
	[khzx] [numeric](18, 4) NULL,
	[gdfk] [numeric](18, 4) NULL,
	[xmbh] [numeric](18, 4) NULL,
	[htys] [numeric](18, 4) NULL,
	[sxbl] [numeric](18, 4) NULL,
	[ss] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/***************************************************************************** 应收账款账面与预警台账调节趋势表（5）
			公司	财务账面应收净收余额	保理余额（加项）	货发票未开金额（加项）	票开货未发金额（减项）	预收款冲减应收（加项）	信用证冲减应收（加项）	其他应收科目影响（加项）	预警台账应收账款余额 	
												
ID	nf	yf	dwid	cwzmysjsye	blye	hfpwkje	pkhwfje	yskcjys	xyzcjys	qtyskmyx	yjtzyszkye	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'yszkgb_yszkzmyyjtztjqs')
DROP TABLE yszkgb_yszkzmyyjtztjqs
CREATE TABLE [dbo].[yszkgb_yszkzmyyjtztjqs](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[cwzmysjsye] [numeric](18, 4) NULL,
	[blye] [numeric](18, 4) NULL,
	[hfpwkje] [numeric](18, 4) NULL,
	[pkhwfje] [numeric](18, 4) NULL,
	[yskcjys] [numeric](18, 4) NULL,
	[xyzcjys] [numeric](18, 4) NULL,
	[qtyskmyx] [numeric](18, 4) NULL,
	[yjtzyszkye] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

