IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'stock_lr')
DROP TABLE stock_lr
CREATE TABLE [dbo].[stock_lr](
	[id] [int] IDENTITY(1,1) NOT NULL,
	stockid	varchar(200),--		股票代码
	report_dt	date	,--	会计年度
	m01	numeric(18,2)	,--	一、营业收入
	m02	numeric(18,2)	,--	减：营业成本
	m03	numeric(18,2)	,--	营业税金及附加
	m04	numeric(18,2)	,--	销售费用
	m05	numeric(18,2)	,--	管理费用
	m06	numeric(18,2)	,--	勘探费用
	m07	numeric(18,2)	,--	财务费用
	m08	numeric(18,2)	,--	资产减值损失
	m09	numeric(18,2)	,--	加：公允价值变动净收益
	m10	numeric(18,2)	,--	投资收益
	m11	numeric(18,2)	,--	其中：对联营企业和合营企业的投资收益
	m12	numeric(18,2)	,--	影响营业利润的其他科目
	m13	numeric(18,2)	,--	二、营业利润
	m14	numeric(18,2)	,--	加：补贴收入
	m15	numeric(18,2)	,--	营业外收入
	m16	numeric(18,2)	,--	减：营业外支出
	m17	numeric(18,2)	,--	其中：非流动资产处置净损失
	m18	numeric(18,2)	,--	加：影响利润总额的其他科目
	m19	numeric(18,2)	,--	三、利润总额
	m20	numeric(18,2)	,--	减：所得税
	m21	numeric(18,2)	,--	加：影响净利润的其他科目
	m22	numeric(18,2)	,--	四、净利润
	m23	numeric(18,2)	,--	归属于母公司所有者的净利润
	m24	numeric(18,2)	,--	少数股东损益
	m25	numeric(18,2)	,--	五、每股收益
	m26	numeric(18,2)	,--	（一）基本每股收益
	m27	numeric(18,2)	,--	（二）稀释每股收益
	m28	nvarchar(1000)	,--	备注
	source nvarchar(200),
	time datetime
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
        