--出口外汇合同明细
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'foreign_exchange_export_contract_detail')
DROP TABLE foreign_exchange_export_contract_detail
CREATE TABLE [dbo].[foreign_exchange_export_contract_detail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	exportContractId int, --出口合同id
	year int, --年份
	month int, --月份
	cashCollected numeric(18, 4), --已回款金额
	cashCollectionAmount numeric(18, 4), --实际回款金额
	cashCollectionDate date, --实际回款时间
	moneyType varchar(100), --款项性质
	cashCollectionType varchar(100), --回款方式
	CashCollectionLeftAmount numeric(18, 4), --未回款金额
	predictCashCollectionAmountN1 numeric(18, 4), --预计N1月回款金额
	predictCashCollectionAmountN2 numeric(18, 4), --预计N2月回款金额
	predictCashCollectionAmountN3 numeric(18, 4), --预计N3月回款金额
	predictCashCollectionType varchar(100), --预计回款方式
	operationAmount numeric(18, 4), --已操作金额
	transactionType varchar(100), --交易类型
	lockPrice numeric(18, 4), --锁汇价格
	tradeDate date, --交割日
	remark varchar(100), --备注
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
