--逾期应收账款明细
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'overdue_accounts_receivable_detail')
DROP TABLE overdue_accounts_receivable_detail
CREATE TABLE [dbo].[overdue_accounts_receivable_detail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	ledgerNum int NOT NULL, --台账num
	fundType varchar(100) NOT NULL, --款项性质
	businessType varchar(100) , --业务类型
	reason varchar(100), --到期未回原因
	reasonType varchar(100) , --原因分类
	measure varchar(100), --清收计划及措施
	precentMonthPlan varchar(100), --本月安排
	personLiable varchar(100), --责任人及联系方式
	supervisor varchar(100), --督导领导
	negotiationLetter varchar(100), --协商函
	paymentRemendLetter varchar(100), --催款函
	lowyerLetter varchar(100), --律师函
	notificationBeforeLawsuit varchar(100), --诉前通知书
	isPaymentPromised varchar(100), --是否承诺回款
	paymentStatus varchar(100), --回款情况
	measureInstruction varchar(100), --现阶段清收措施及成效说明
	hasRisk varchar(100), --判定是否存在风险
	responsibility varchar(100), --内部责任认定及考核情况
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
