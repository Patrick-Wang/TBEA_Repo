--内部户资金情况
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'account_fond_internal')
DROP TABLE account_fond_internal
CREATE TABLE [dbo].[account_fond_internal](
	[id] [int] IDENTITY(1,1) NOT NULL,
	nf int, --年份
	yf int, --月份
	dwmc varchar(100), --单位名称
	yh varchar(100), --银行
	nbkhhh varchar(100), --内部户客户号
	bz varchar(100), --币种
	khd varchar(100), --开户地
	qcye numeric(18, 4), --期初余额
	bqsr numeric(18, 4), --本期收入
	bqzc numeric(18, 4), --本期支出
	qmye numeric(18, 4), --期末余额
	tmzhzksr numeric(18, 4), --同名账户转款收入
	tmzhzkzc numeric(18, 4), --同名账户转款支出
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--银行账户资金
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'account_fond_bank')
DROP TABLE account_fond_bank
CREATE TABLE [dbo].[account_fond_bank](
	[id] [int] IDENTITY(1,1) NOT NULL,
	nf int, --年份
	yf int, --月份
	dwmc varchar(100), --单位名称
	yh varchar(100), --银行
	yhzh varchar(100), --银行账号
	bz varchar(100), --币种
	khd varchar(100), --开户地
	qcye numeric(18, 4), --期初余额
	bqsr numeric(18, 4), --本期收入
	bqzc numeric(18, 4), --本期支出
	qmye numeric(18, 4), --期末余额
	tmzhzksr numeric(18, 4), --同名账户转款收入
	tmzhzkzc numeric(18, 4), --同名账户转款支出
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--定期
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'account_fond_fixed')
DROP TABLE account_fond_fixed
CREATE TABLE [dbo].[account_fond_fixed](
	[id] [int] IDENTITY(1,1) NOT NULL,
	nf int, --年份
	yf int, --月份
	dwmc varchar(100), --单位名称
	yh varchar(100), --银行
	khd varchar(100), --开户地
	bz varchar(100), --币种
	ll numeric(18, 4), --利率%
	qxr date, --起息日
	dqr date, --到期日
	lx numeric(18, 4), --利息
	je numeric(18, 4), --金额
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--通知
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'account_fond_notification')
DROP TABLE account_fond_notification
CREATE TABLE [dbo].[account_fond_notification](
	[id] [int] IDENTITY(1,1) NOT NULL,
	nf int, --年份
	yf int, --月份
	dwmc varchar(100), --单位名称
	yh varchar(100), --银行
	khd varchar(100), --开户地
	bz varchar(100), --币种
	ll numeric(18, 4), --利率%
	qxr date, --起息日
	je numeric(18, 4), --金额
	lx numeric(18, 4), --利息
	ywye numeric(18, 4), --业务余额
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
