IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'stock_xjll')
DROP TABLE stock_xjll
CREATE TABLE [dbo].[stock_xjll](
	[id] [int] IDENTITY(1,1) NOT NULL,
	stockid	nvarchar(200)	,--	股票代码
	report_dt	date	,--	报告年度
	m01	numeric(18,2)	,--	销售商品、提供劳务收到的现金
	m02	numeric(18,2)	,--	收到的税费返还
	m03	numeric(18,2)	,--	收到其他与经营活动有关的现金
	m04	numeric(18,2)	,--	经营活动现金流入小计
	m05	numeric(18,2)	,--	购买商品、接受劳务支付的现金
	m06	numeric(18,2)	,--	支付给职工以及为职工支付的现金
	m07	numeric(18,2)	,--	支付的各项税费
	m08	numeric(18,2)	,--	支付其他与经营活动有关的现金
	m09	numeric(18,2)	,--	经营活动现金流出小计
	m10	numeric(18,2)	,--	经营活动产生的现金流量净额
	m11	numeric(18,2)	,--	收回投资收到的现金
	m12	numeric(18,2)	,--	取得投资收益收到的现金
	m13	numeric(18,2)	,--	处置固定资产、无形资产和其他长期资产收回的现金净额
	m14	numeric(18,2)	,--	处置子公司及其他营业单位收到的现金净额
	m15	numeric(18,2)	,--	收到其他与投资活动有关的现金
	m16	numeric(18,2)	,--	投资活动现金流入小计
	m17	numeric(18,2)	,--	购建固定资产、无形资产和其他长期资产支付的现金
	m18	numeric(18,2)	,--	投资支付的现金
	m19	numeric(18,2)	,--	取得子公司及其他营业单位支付的现金净额
	m20	numeric(18,2)	,--	支付其他与投资活动有关的现金
	m21	numeric(18,2)	,--	投资活动现金流出小计
	m22	numeric(18,2)	,--	投资活动产生的现金流量净额
	m23	numeric(18,2)	,--	吸收投资收到的现金
	m24	numeric(18,2)	,--	取得借款收到的现金
	m25	numeric(18,2)	,--	收到其他与筹资活动有关的现金
	m26	numeric(18,2)	,--	筹资活动现金流入小计
	m27	numeric(18,2)	,--	偿还债务支付的现金
	m28	numeric(18,2)	,--	分配股利、利润或偿付利息支付的现金
	m29	numeric(18,2)	,--	支付其他与筹资活动有关的现金
	m30	numeric(18,2)	,--	筹资活动现金流出小计
	m31	numeric(18,2)	,--	筹资活动产生的现金流量净额
	m32	numeric(18,2)	,--	期初现金及现金等价物余额
	m33	numeric(18,2)	,--	期末现金及现金等价物余额
	m34	numeric(18,2)	,--	净利润
	m35	numeric(18,2)	,--	加：资产减值准备
	m36	numeric(18,2)	,--	固定资产折旧、油气资产折耗、生产性生物资产折旧
	m37	numeric(18,2)	,--	无形资产摊销
	m38	numeric(18,2)	,--	长期待摊费用摊销
	m39	numeric(18,2)	,--	处置固定资产、无形资产和其他长期资产的损失
	m40	numeric(18,2)	,--	固定资产报废损失
	m41	numeric(18,2)	,--	公允价值变动损失
	m42	numeric(18,2)	,--	财务费用
	m43	numeric(18,2)	,--	投资损失
	m44	numeric(18,2)	,--	递延所得税资产减少
	m45	numeric(18,2)	,--	递延所得税负债增加
	m46	numeric(18,2)	,--	存货的减少
	m47	numeric(18,2)	,--	经营性应收项目的减少
	m48	numeric(18,2)	,--	经营性应付项目的增加
	m49	numeric(18,2)	,--	其他
	m50	numeric(18,2)	,--	经营活动产生的现金流量净额2
	m51	numeric(18,2)	,--	债务转为资本
	m52	numeric(18,2)	,--	一年内到期的可转换公司债券
	m53	numeric(18,2)	,--	融资租入固定资产
	m54	numeric(18,2)	,--	现金的期末余额
	m55	numeric(18,2)	,--	减：现金的期初余额
	m56	numeric(18,2)	,--	加：现金等价物的期末余额
	m57	numeric(18,2)	,--	减：现金等价物的期初余额
	m58	numeric(18,2)	,--	加：其他原因对现金的影响2
	m59	numeric(18,2)	,--	现金及现金等价物净增加额
	m60	nvarchar(1000),	--	备注
	source nvarchar(200),
	time datetime
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
        