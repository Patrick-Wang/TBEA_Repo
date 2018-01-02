--远期结售汇业务
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'exchange_settlement_forward')
DROP TABLE exchange_settlement_forward
CREATE TABLE [dbo].[exchange_settlement_forward](
	[id] [int] IDENTITY(1,1) NOT NULL,
	dwmc varchar(100), --单位名称
	ywzl varchar(100), --业务种类
	jbr date, --经办日
	jqhl numeric(18, 4), --即期汇率
	mcbz varchar(100), --卖出币种
	mcje numeric(18, 4), --卖出金额 
	sdhl numeric(18, 4), --锁定汇率
	mrbz varchar(100), --买入币种
	mrje numeric(18, 4), --买入金额 
	jgrjqhl numeric(18, 4), --交割日即期汇率
	jgr date, --交割日
	jgqk varchar(100), --交割情况
	hdsy numeric(18, 4), --汇兑损益
	yh varchar(100), --银行
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
