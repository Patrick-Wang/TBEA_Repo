--科目余额
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'CUX_ACCOUNTBALANCE_V')
DROP TABLE CUX_ACCOUNTBALANCE_V
CREATE TABLE [dbo].[CUX_ACCOUNTBALANCE_V](
	[id] [int] IDENTITY(1,1) NOT NULL,
	account_code varchar(100), --科目代码
	account_segments varchar(100), --科目组合
	year_begin_balance varchar(100), --年初余额
	begin_balance varchar(100), --期初余额
	period_net_dr varchar(100), --本期借方
	period_net_cr varchar(100), --本期贷方
	end_balance varchar(100), --期末余额
	year_acc_dr varchar(100), --本年累计借方
	year_acc_cr varchar(100), --本年累计贷方
	account_segments_desc varchar(100), --科目说明
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]