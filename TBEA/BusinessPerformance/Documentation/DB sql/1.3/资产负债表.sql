IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'stock_zcfzb')
DROP TABLE stock_zcfzb
CREATE TABLE [dbo].[stock_zcfzb](
	[id] [int] IDENTITY(1,1) NOT NULL,
	stockid	varchar(200)	,--	股票代码
	report_dt	date	,--	会计年度
	m01	numeric(18,2)	,--	货币资金
	m02	numeric(18,2)	,--	交易性金融资产
	m03	numeric(18,2)	,--	应收票据
	m04	numeric(18,2)	,--	应收账款
	m05	numeric(18,2)	,--	预付款项
	m06	numeric(18,2)	,--	其他应收款
	m07	numeric(18,2)	,--	应收关联公司款
	m08	numeric(18,2)	,--	应收利息
	m09	numeric(18,2)	,--	应收股利
	m10	numeric(18,2)	,--	存货
	m11	numeric(18,2)	,--	其中：消耗性生物资产
	m12	numeric(18,2)	,--	一年内到期的非流动资产
	m13	numeric(18,2)	,--	其他流动资产
	m14	numeric(18,2)	,--	流动资产合计
	m15	numeric(18,2)	,--	可供出售金融资产
	m16	numeric(18,2)	,--	持有至到期投资
	m17	numeric(18,2)	,--	长期应收款
	m18	numeric(18,2)	,--	长期股权投资
	m19	numeric(18,2)	,--	投资性房地产
	m20	numeric(18,2)	,--	固定资产
	m21	numeric(18,2)	,--	在建工程
	m22	numeric(18,2)	,--	工程物资
	m23	numeric(18,2)	,--	固定资产清理
	m24	numeric(18,2)	,--	生产性生物资产
	m25	numeric(18,2)	,--	油气资产
	m26	numeric(18,2)	,--	无形资产
	m27	numeric(18,2)	,--	开发支出
	m28	numeric(18,2)	,--	商誉
	m29	numeric(18,2)	,--	长期待摊费用
	m30	numeric(18,2)	,--	递延所得税资产
	m31	numeric(18,2)	,--	其他非流动资产
	m32	numeric(18,2)	,--	非流动资产合计
	m33	numeric(18,2)	,--	资产总计
	m34	numeric(18,2)	,--	短期借款
	m35	numeric(18,2)	,--	交易性金融负债
	m36	numeric(18,2)	,--	应付票据
	m37	numeric(18,2)	,--	应付账款
	m38	numeric(18,2)	,--	预收款项
	m39	numeric(18,2)	,--	应付职工薪酬
	m40	numeric(18,2)	,--	应交税费
	m41	numeric(18,2)	,--	应付利息
	m42	numeric(18,2)	,--	应付股利
	m43	numeric(18,2)	,--	其他应付款
	m44	numeric(18,2)	,--	应付关联公司款
	m45	numeric(18,2)	,--	一年内到期的非流动负债
	m46	numeric(18,2)	,--	其他流动负债
	m47	numeric(18,2)	,--	流动负债合计
	m48	numeric(18,2)	,--	长期借款
	m49	numeric(18,2)	,--	应付债券
	m50	numeric(18,2)	,--	长期应付款
	m51	numeric(18,2)	,--	专项应付款
	m52	numeric(18,2)	,--	预计负债
	m53	numeric(18,2)	,--	递延所得税负债
	m54	numeric(18,2)	,--	其他非流动负债
	m55	numeric(18,2)	,--	非流动负债合计
	m56	numeric(18,2)	,--	负债合计
	m57	numeric(18,2)	,--	实收资本（或股本）
	m58	numeric(18,2)	,--	资本公积
	m59	numeric(18,2)	,--	盈余公积
	m60	numeric(18,2)	,--	减：库存股
	m61	numeric(18,2)	,--	未分配利润
	m62	numeric(18,2)	,--	少数股东权益
	m63	numeric(18,2)	,--	外币报表折算价差
	m64	numeric(18,2)	,--	非正常经营项目收益调整
	m65	numeric(18,2)	,--	归属母公司所有者权益（或股东权益）
	m66	numeric(18,2)	,--	所有者权益（或股东权益）合计
	m67	numeric(18,2)	,--	负债和所有者（或股东权益）合计
	m68	nvarchar(1000),	--	备注
		source nvarchar(200),
	time datetime
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
        