--回款计划表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'ar_received_plan')
DROP TABLE ar_received_plan
CREATE TABLE [dbo].[ar_received_plan](
	[id] [int] IDENTITY(1,1) NOT NULL,
	planNum varchar(100) UNIQUE NOT NULL, --计划序号
	nf int, --年份
	yf int, --月份
	company varchar(100), --本部/项目公司
	department varchar(100), --办事处
	contractNum varchar(100), --合同号
	customerName varchar(100), --单位名称
	projectName varchar(100), --项目名称
	businessType varchar(100), --业务类型
	prePayment numeric(18, 4), --预付款
	proPayment numeric(18, 4), --进度款
	deliPayment numeric(18, 4), --发货款
	arriPayment numeric(18, 4), --到货款
	workPayment numeric(18, 4), --投运/安装款
	accePayment numeric(18, 4), --验收款
	guarPayment numeric(18, 4), --质保金
	planSum numeric(18, 4), --计划总额
	isReduceAR varchar(100), --款项回款是否可降应收
	ARStatus varchar(100), --款项状态
	ARProperty varchar(100), --款项性质
	receProperty varchar(100), --回款性质
	operator varchar(100), --经办人
	personIncharge varchar(100), --负责人
	leader varchar(100), --督导领导
	progress varchar(100), --目前款项的清收进度（填写款项目前已办理到何种程度）
	measure varchar(100), --清收措施（须细化到以时间节点为主线的各个手续办理环节）
	remark varchar(100), --困难款项说明
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
