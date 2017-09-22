--变压器预警台账
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'ar_warning_book_t')
DROP TABLE ar_warning_book_t
CREATE TABLE [dbo].[ar_warning_book_t](
	[id] [int] IDENTITY(1,1) NOT NULL,
	num varchar(100) UNIQUE, --序号
	company varchar(100), --本部/项目公司
	department varchar(100), --部门/办事处
	businessType varchar(100), --业务类型
	contractNum varchar(100), --合同号
	productNum varchar(100), --生产号
	customerName varchar(100), --单位名称
	projectName varchar(100), --项目名称
	cusIndustry varchar(100), --客户所属行业
	productModel varchar(100), --产品型号
	productQuantity varchar(100), --数量
	payment varchar(100), --付款方式
	contractAmount numeric(18, 4), --合同金额
	deliverDate date, --发货时间/开工时间
	arriDate varchar(100), --到货时间
	deliverNum varchar(100), --发货台数
	deliverAmount numeric(18, 4), --发货金额
	deliverAging varchar(100), --发货账龄
	noninvoiceAmount numeric(18, 4), --无票金额
	invoicedAmount numeric(18, 4), --开票金额
	preInvoiceAmount numeric(18, 4), --预开票税金金额
	RPAmount numeric(18, 4), --已收回款
	ActualAR numeric(18, 4), --发货应收余额
	factoringAmount numeric(18, 4), --保理款
	bookBalanceAR numeric(18, 4), --账面应收余额
	preOfAR numeric(18, 4), --应收预付款
	expireOfArriDate date, --到货款到期时间
	arriOfAR numeric(18, 4), --应收到货款
	expireOfcheckaccept date, --验收款到期时间
	checkacceptOfAR numeric(18, 4), --应收验收款
	expireOfOperation date, --投运款到期时间
	operationOfAR numeric(18, 4), --应收投运款
	expireOfGuar date, --质保金到期时间
	GuarOfAR numeric(18, 4), --应收质保金
	NotDuePayment numeric(18, 4), --未到期款（不含质保金）
	NotDueGuar numeric(18, 4), --未到期质保金
	overdueAmount numeric(18, 4), --逾期款
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--线缆预警台账
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'ar_warning_book_c')
DROP TABLE ar_warning_book_c
CREATE TABLE [dbo].[ar_warning_book_c](
	[id] [int] IDENTITY(1,1) NOT NULL,
	num varchar(100) UNIQUE, --序号
	company varchar(100), --本部/项目公司
	department varchar(100), --部门/办事处
	operator varchar(100), --经办人
	customer varchar(100), --客户名称
	contractNum varchar(100), --合同编号
	cusIndustry varchar(100), --客户所属行业
	productType varchar(100), --产品大类
	contractDate date, --合同日期
	projectName varchar(100), --项目名称
	payment varchar(100), --交易条款/付款方式
	isDD varchar(100), --是否兜底（是/否）
	contractAmount numeric(18, 4), --合同总额
	deliverAmount numeric(18, 4), --发货总额
	lastDeliveryDate date, --末次发货时间
	invoicedAmount numeric(18, 4), --已开发票金额
	unbilledAmount numeric(18, 4), --未开票金额
	RPAmount numeric(18, 4), --回款总额
	RPRation numeric(18, 4), --回款比例
	lastRPDate date, --末次回款日期
	beginBookBalance numeric(18, 4), --本期账面余额
	ActualAR numeric(18, 4), --实际应收余额（发货口径）
	expireAmount numeric(18, 4), --到期款总额
	expireOfPrePAmount numeric(18, 4), --预收款到期款
	expireOfPrePDate varchar(100), --预收款到期日期
	expireOfPrePDays varchar(100), --预收款逾期天数
	expireOfDeliAmount numeric(18, 4), --发货款到期款
	expireOfDeliDate varchar(100), --发货款到期日期
	expireOfDeliDays varchar(100), --发货款逾期天数
	expireOfArriAmount numeric(18, 4), --到货款到期款
	expireOfArriDate varchar(100), --到货款到期日期
	expireOfArriDays varchar(100), --到货款逾期天数
	expireOfFixAmount numeric(18, 4), --安装款到期款
	expireOfFixDate varchar(100), --安装款到期日期
	expireOfFixDays varchar(100), --安装款逾期天数
	expireOfWorkAmount numeric(18, 4), --运行款到期款
	expireOfWorkDate varchar(100), --运行款到期日期
	expireOfWorkDays varchar(100), --运行款逾期天数
	expireOfGuarAmount numeric(18, 4), --质保金到期款
	expireOfGuarDate varchar(100), --质保金到期日期
	expireOfGuarDays varchar(100), --质保金逾期天数
	NotDuePayment numeric(18, 4), --未到期款总额
	NotDuePrePAmount numeric(18, 4), --未到期预收款
	NotDuePrePDate varchar(100), --预收款到期日期
	NotDueDeliAmount numeric(18, 4), --未到期发货款
	NotDueDeliDate varchar(100), --发货款到期日期
	NotDueArriAmount numeric(18, 4), --未到期到货款
	NotDueArriDate varchar(100), --到货款到期日期
	NotDueFixAmount numeric(18, 4), --未到期安装款
	NotDueFixDate varchar(100), --安装款到期日期
	NotDueWorkAmount numeric(18, 4), --未到期运行款
	NotDueWorkDate varchar(100), --运行款到期日期
	NotDueGuarAmount numeric(18, 4), --未到期质保金
	NotDueGuarDate varchar(100), --质保金到期日期
	NoPaymentFactor varchar(100), --未回因素
	preReceiptAmount numeric(18, 4), --预开发票金额
	liquidateProgress varchar(100), --当前清收进度
	liquidateMeasure varchar(100), --后期清收措施
	ensure numeric(18, 4), --确保
	strive numeric(18, 4), --争取
	summary numeric(18, 4), --小计
	leader varchar(100), --督导厂领导
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
