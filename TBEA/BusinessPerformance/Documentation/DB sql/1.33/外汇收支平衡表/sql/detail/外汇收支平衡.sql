--外汇收支平衡表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'foreign_exchange_budget')
DROP TABLE foreign_exchange_budget
CREATE TABLE [dbo].[foreign_exchange_budget](
	[id] [int] IDENTITY(1,1) NOT NULL,
	dwmc varchar(100), --单位名称
	bz varchar(100), --币种
	yhzh numeric(18, 4), --银行账户
	sczj numeric(18, 4), --上存资金
	xj1 numeric(18, 4), --小计
	dq numeric(18, 4), --定期
	tz numeric(18, 4), --通知
	xj2 numeric(18, 4), --小计
	hj numeric(18, 4), --合计
	N numeric(18, 4), --N月
	N1 numeric(18, 4), --N1月
	N2 numeric(18, 4), --N2月
	shxj numeric(18, 4), --收汇小计
	T1zf numeric(18, 4), --TT支付
	ghbl numeric(18, 4), --归还保理
	hdk numeric(18, 4), --还贷款
	xyz numeric(18, 4), --信用证
	fhxj numeric(18, 4), --付汇小计
	whqkje numeric(18, 4), --外汇缺口金额
	yshje numeric(18, 4), --已锁汇金额
	nf int, --年份
	yf numeric(18, 4), --月份
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]