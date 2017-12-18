--进口外汇合同明细
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'foreign_exchange_import_contract_detail')
DROP TABLE foreign_exchange_import_contract_detail
CREATE TABLE [dbo].[foreign_exchange_import_contract_detail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	importContractId int, --进口外汇合同Id
	year int, --年份
	month int, --月份
	paidAmount numeric(18, 4), --已付款金额
	paymentAmountTheMonth numeric(18, 4), --本月付款金额
	moneyType varchar(100), --款项性质
	transactionType varchar(100), --付款方式
	amountLeft numeric(18, 4), --待付合同金额
	predictPaymentAmountN1 numeric(18, 4), --预计N1月付款金额
	predictPaymentAmountN2 numeric(18, 4), --预计N2月付款金额
	predictPaymentAmountN3 numeric(18, 4), --预计N3月付款金额
	predictTransactionType varchar(100), --预计付款方式
	operationAmount numeric(18, 4), --已操作金额
	transactionTypeLeft varchar(100), --剩余付款方式
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
