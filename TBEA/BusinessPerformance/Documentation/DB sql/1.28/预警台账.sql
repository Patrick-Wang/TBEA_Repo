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
	expireOfPrePDate date, --预收款到期日期
	expireOfDeliAmount numeric(18, 4), --发货款到期款
	expireOfDeliDate date, --发货款到期日期
	expireOfArriAmount numeric(18, 4), --到货款到期款
	expireOfArriDate date, --到货款到期日期
	expireOfFixAmount numeric(18, 4), --安装款到期款
	expireOfFixDate date, --安装款到期日期
	expireOfWorkAmount numeric(18, 4), --运行款到期款
	expireOfWorkDate date, --运行款到期日期
	expireOfGuarAmount numeric(18, 4), --质保金到期款
	expireOfGuarDate date, --质保金到期日期
	NotDuePayment numeric(18, 4), --未到期款总额
	NotDuePrePAmount numeric(18, 4), --未到期预收款
	NotDuePrePDate date, --预收款到期日期
	NotDueDeliAmount numeric(18, 4), --未到期发货款
	NotDueDeliDate date, --发货款到期日期
	NotDueArriAmount numeric(18, 4), --未到期到货款
	NotDueArriDate date, --到货款到期日期
	NotDueFixAmount numeric(18, 4), --未到期安装款
	NotDueFixDate date, --安装款到期日期
	NotDueWorkAmount numeric(18, 4), --未到期运行款
	NotDueWorkDate date, --运行款到期日期
	NotDueGuarAmount numeric(18, 4), --未到期质保金
	NotDueGuarDate date, --质保金到期日期
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
