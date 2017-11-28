--进口外汇合同
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'foreign_exchange_import_contract')
DROP TABLE foreign_exchange_import_contract
CREATE TABLE [dbo].[foreign_exchange_import_contract](
	[id] [int] IDENTITY(1,1) NOT NULL,
	companyName varchar(100), --单位名称
	num varchar(100), --合同编号
	name varchar(100), --合同名称
	supplier varchar(100), --供货商名称
	country varchar(100), --所在国家
	currency varchar(100), --币种 
	payingBank varchar(100), --付款银行
	amount numeric(18, 4), --合同金额 
	startDate date, --签订合同起始日
	exchangeRate numeric(18, 4), --签订合同汇率
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--出口外汇合同
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'foreign_exchange_export_contract')
DROP TABLE foreign_exchange_export_contract
CREATE TABLE [dbo].[foreign_exchange_export_contract](
	[id] [int] IDENTITY(1,1) NOT NULL,
	companyName varchar(100), --单位名称
	num varchar(100), --合同编号
	name varchar(100), --合同名称
	projectName varchar(100), --项目名称
	clientCompanyName varchar(100), --对方单位名称
	country varchar(100), --所在国家
	currency varchar(100), --币种 
	dueBank varchar(100), --收款银行
	amount numeric(18, 4), --合同金额 
	startDate date, --签订合同起始日
	exchangeRate numeric(18, 4), --签订合同汇率
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
