--各家银行授信情况统计表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financing_bank_currency_credit')
DROP TABLE financing_bank_currency_credit
CREATE TABLE [dbo].[financing_bank_currency_credit](
	[id] [int] IDENTITY(1,1) NOT NULL,
	bankName varchar(100), --银行名称
	currency varchar(10), --币种
	authCreditAmount numeric(20, 2), --授信额
	usedAmount numeric(20, 2), --已使用额度
	loanBalance numeric(20, 2), --贷款余额
	authCreditExpire date, --授信到期日
	dwid int, --公司名称
	nf int,
	yf int,
	solved varchar(100) DEFAULT 'N', --已处理
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--特变电工人民币贷款明细统计表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financing_RMB_loan')
DROP TABLE financing_RMB_loan
CREATE TABLE [dbo].[financing_RMB_loan](
	[id] [int] IDENTITY(1,1) NOT NULL,
	bankName text, --银行名称
	beginningDate date, --贷款起始日
	maturityDate date, --贷款到期日
	loanDmount numeric(20, 2), --金额
	loanRate numeric(18, 4), --利率
	floatingRatio numeric(18, 4), --浮动幅度
	loanTerm text, --贷款期限
	loanType text, --贷款品种
	guarantyStyle text, --担保方式
	dwid int, --公司名称
	solved varchar(100) DEFAULT 'N', --已处理
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--特变电工其他融资明细统计表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financing_other_detail')
DROP TABLE financing_other_detail
CREATE TABLE [dbo].[financing_other_detail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	bankName text, --银行名称
	beginningDate date, --贷款起始日
	maturityDate date, --贷款到期日
	loanAmount numeric(20, 2), --金额
	loanRate numeric(18, 4), --利率
	floatingRatio numeric(18, 4), --浮动幅度
	loanType text, --贷款品种
	dwid int, --公司名称
	solved varchar(100) DEFAULT 'N', --已处理
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--特变电工外币贷款明细统计表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financing_foreign_currency_loan')
DROP TABLE financing_foreign_currency_loan
CREATE TABLE [dbo].[financing_foreign_currency_loan](
	[id] [int] IDENTITY(1,1) NOT NULL,
	bankName text, --银行名称
	beginningDate date, --贷款起始日
	maturityDate date, --贷款到期日
	currency varchar(10), --币种
	loanAmount numeric(20, 2), --金额
	loanRate numeric(18, 4), --利率
	floatingRatio numeric(18, 4), --浮动幅度
	loanTerm text, --贷款期限
	loanType text, --贷款品种
	guarantyStyle text, --担保方式
	dwid int, --公司名称
	solved varchar(100) DEFAULT 'N', --已处理
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--特变电工承债式贷款明细统计表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financing_debt_bearing_loan')
DROP TABLE financing_debt_bearing_loan
CREATE TABLE [dbo].[financing_debt_bearing_loan](
	[id] [int] IDENTITY(1,1) NOT NULL,
	companySName varchar(100), --单位名称
	loanBank varchar(100), --贷款银行
	beginningDate date, --贷款起始日
	loanAmount numeric(18, 4), --金额
	repaymentDate date, --还款日
	repaymentAmount numeric(18, 4), --还款金额
	loanRate numeric(18, 4), --利率
	floatingRatio numeric(18, 4), --浮动幅度
	loanstyle varchar(100), --贷款种类
	loanType varchar(100), --贷款品种
	loadMode varchar(100), --贷款方式
	remark varchar(100), --备注
	companyFName varchar(100), --单位全名
	dwid int, --公司名称	
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--特变电工贸易业务明细统计表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financing_trade')
DROP TABLE financing_trade
CREATE TABLE [dbo].[financing_trade](
	[id] [int] IDENTITY(1,1) NOT NULL,
	bankName text, --银行名称
	beginningDate date, --业务起始日
	maturityDate date, --业务到期日
	amount numeric(20, 2), --金额
	rate numeric(18, 4), --利率
	fee numeric(18, 4), --手续费率
	type text, --品种
	guarantyStyle text, --担保方式
	dwid int, --公司名称
	solved varchar(100) DEFAULT 'N', --已处理
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--特变电工银票办理情况表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financing_bank_bill')
DROP TABLE financing_bank_bill
CREATE TABLE [dbo].[financing_bank_bill](
	[id] [int] IDENTITY(1,1) NOT NULL,
	bankName varchar(100), --银行名称
	beginningDate date, --出票日
	maturityDate date, --到期日
	amount numeric(18, 4), --票面金额
	marginRatio numeric(18, 4), --保证金比例
	remark varchar(100), --备注
	reason varchar(100), --未解付原因
	dwid int, --公司名称
	solved varchar(100) DEFAULT 'N', --已处理
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--特变电工商票办理情况表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financing_commercial_bill')
DROP TABLE financing_commercial_bill
CREATE TABLE [dbo].[financing_commercial_bill](
	[id] [int] IDENTITY(1,1) NOT NULL,
	bankName text, --银行名称
	beginningDate date, --出票日
	maturityDate date, --到期日
	amount numeric(20, 2), --票面金额
	reason text, --未解付原因
	dwid int, --公司名称
	solved varchar(100) DEFAULT 'N', --已处理
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--特变电工人民币保函办理情况统计表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financing_RMB_LG')
DROP TABLE financing_RMB_LG
CREATE TABLE [dbo].[financing_RMB_LG](
	[id] [int] IDENTITY(1,1) NOT NULL,
	bankName varchar(100), --银行名称
	beginningDate date, --起始日
	maturityDate date, --到期日
	amount numeric(18, 4), --保函金额
	marginRatio numeric(18, 4), --保证金比例
	type varchar(100), --保函种类
	guaranteeNumber text, --保函编号
	remark varchar(100), --备注
	reason varchar(100), --未注销原因
	dwid int, --公司名称
	solved varchar(100) DEFAULT 'N', --已处理
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--特变电工外币保函办理情况统计表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financing_foreign_currency_LG')
DROP TABLE financing_foreign_currency_LG
CREATE TABLE [dbo].[financing_foreign_currency_LG](
	[id] [int] IDENTITY(1,1) NOT NULL,
	bankName varchar(100), --银行名称
	beginningDate date, --起始日
	maturityDate date, --到期日
	currency varchar(50), --币种
	amount numeric(18, 4), --保函金额
	marginRatio numeric(18, 4), --保证金比例
	type varchar(100), --保函种类
	guaranteeNumber text, --保函编号
	remark varchar(100), --备注
	reason varchar(100), --未注销原因
	dwid int, --公司名称
	solved varchar(100) DEFAULT 'N', --已处理
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--特变电工双币种保函办理情况统计表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financing_double_currency_LG')
DROP TABLE financing_double_currency_LG
CREATE TABLE [dbo].[financing_double_currency_LG](
	[id] [int] IDENTITY(1,1) NOT NULL,
	bankName varchar(100), --银行名称
	beginningDate date, --起始日
	maturityDate date, --到期日
	currency varchar(50), --币种
	amount numeric(18, 4), --保函金额
	guaranteeAmount numeric(18, 4),--保证金金额
	type varchar(100), --保函种类
	guaranteeNumber varchar(100), --保函编号
	remark varchar(100), --备注
	reason varchar(100), --未注销原因
	dwid int, --公司名称
	solved varchar(100) DEFAULT 'N', --已处理
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--特变电工信用证
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financing_LC')
DROP TABLE financing_LC
CREATE TABLE [dbo].[financing_LC](
	[id] [int] IDENTITY(1,1) NOT NULL,
	bankName varchar(100), --业务银行
	currency varchar(100), --币种
	beginningDate date, --起始日
	maturityDate date, --到期日
	LCamount numeric(18, 4), --信用证金额
	cashAmount numeric(18, 4), --保证金金额 
	cashRate numeric(18, 4), --保证金比例
	remark varchar(100), --备注
	Lcbalance numeric(18, 4), --信用证余额
	dwid int, --单位ID
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--特变电工保理
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financing_factoring')
DROP TABLE financing_factoring
CREATE TABLE [dbo].[financing_factoring](
	[id] [int] IDENTITY(1,1) NOT NULL,
	beginningDate date, --起始日
	maturityDate date, --到期日
	bankName varchar(100), --业务银行
	customerName varchar(100), --客户名称
	contractType varchar(100), --合同类型
	amount numeric(18, 4), --保理金额
	beginningOfYearBalance numeric(18, 4), --年初余额
	loanReturned numeric(18, 4), --本年已归还金额
	balance numeric(18, 4), --保理余额
	rate numeric(18, 4), --年利率
	feeRate numeric(18, 4), --手续费率
	interest_expense numeric(18, 4), --利息支出
	fee numeric(18, 4), --手续费
	costUndertaker varchar(100), --利息承担方
	remark varchar(100), --备注
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
