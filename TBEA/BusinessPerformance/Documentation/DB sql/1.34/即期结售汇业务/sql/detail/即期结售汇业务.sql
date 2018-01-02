--即期结售汇业务
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'exchange_settlement_immediate')
DROP TABLE exchange_settlement_immediate
CREATE TABLE [dbo].[exchange_settlement_immediate](
	[id] [int] IDENTITY(1,1) NOT NULL,
	dwmc varchar(100), --单位名称
	ywzl varchar(100), --业务种类
	jbr date, --经办日
	jqhl numeric(18, 4), --即期汇率
	mcbz varchar(100), --卖出币种
	mcje numeric(18, 4), --卖出金额 
	mrbz varchar(100), --买入币种
	mrje numeric(18, 4), --买入金额 
	yh numeric(18, 4), --银行
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]