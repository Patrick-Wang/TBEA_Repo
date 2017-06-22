--项目明细
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'domestic_market_xmmx')
DROP TABLE domestic_market_xmmx
CREATE TABLE [dbo].[domestic_market_xmmx](
	[id] 	[int] IDENTITY(1,1) NOT NULL	,
	comp_name	varchar(300)	,--	单位
	project_department	varchar(300)	,--	办事处名称
	pro_no	varchar(300)	,--	项目序号
	industry	varchar(300)	,--	所属行业
	system	varchar(300)	,--	所属系统
	pro_name	varchar(300)	,--	项目名称
	owner_comp	varchar(300)	,--	业主单位
	model	varchar(300)	,--	产品型号
	amount	numeric(18,4)	,--	数量
	predict_bid_amount	numeric(18,4)	,--	预计投标金额
	predict_bidding_date	date	,--	预计招标时间
	region	varchar(300)	,--	项目所在区域
	project_brief	varchar(1000)	,--	项目简介
	pro_condition	varchar(1000)	,--	目前推进跟踪情况及后期计划
	person_in_charge_info	varchar(300)	,--	本单位项目负责人及联系方式
	charge_leader	varchar(300)	,--	本单位负责该项目的主管领导
	other_comp	varchar(300)	,--	跟踪该项目的其它内部企业名称
	bid_condition	varchar(1000)	,--	投标情况
	jydw int,
	source varchar(100),
	time datetime
PRIMARY KEY CLUSTERED 						
(						
	[id] ASC					
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]						
) ON [PRIMARY]						

--投标细表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'domestic_market_tbmx')
DROP TABLE domestic_market_tbmx
CREATE TABLE [dbo].[domestic_market_tbmx](
	[id] 	[int] IDENTITY(1,1) NOT NULL	,	
	comp_name	varchar(300)	,--	单位
	bid_no	varchar(300)	,--	投标编号
	pro_no	varchar(300)	,--	项目信息编号
	auth_no	varchar(300)	,--	授权编号
	project_department	varchar(300)	,--	办事办或项目部
	bid_month	int	,--	投标月份
	bid_date	date	,--	投标日期
	industry	varchar(300)	,--	所属行业
	system	varchar(300)	,--	所属系统
	region	varchar(300)	,--	项目所在区域
	pro_name	varchar(300)	,--	项目名称
	owner_comp	varchar(300)	,--	业主单位
	model	varchar(300)	,--	型号
	voltage_level	varchar(300)	,--	电压等级
	bid_amount	numeric(18,4)	,--	投标数量（数字）
	bid_capacity	numeric(18,4)	,--	投标容量（数字）(kVA)
	bid_price	numeric(18,4)	,--	"投标价格（数字）"
	win_bid_compw	varchar(300)	,--	中标厂家
	win_bid_price	numeric(18,4)	,--	中标价格（数字）
	cause	varchar(1000)	,--	中标或未中标原因分析
	finally_date	varchar(100)	,--	定标月份
	status	varchar(300)	,--	状态
	is_need_summary	varchar(100)	,--	是否反馈投标总结
	specific_bid_comp	varchar(300)	,--	具体投标单位
	person_in_charge	varchar(100)	,--	负责人
	jydw int,
	source varchar(100),
	time datetime
PRIMARY KEY CLUSTERED 						
(						
	[id] ASC					
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]						
) ON [PRIMARY]						

--合同明细
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'domestic_market_htmx')
DROP TABLE domestic_market_htmx
CREATE TABLE [dbo].[domestic_market_htmx](
	[id]	[int] IDENTITY(1,1) NOT NULL	,	
	comp_name	varchar(300)	,--	单位
	contract_no	varchar(300)	,--	合同编号
	project_department	varchar(300)	,--	办事办或项目部
	sign_month	date	,--	签约月份
	industry	varchar(300)	,--	所属行业
	system	varchar(300)	,--	所属系统
	region	varchar(300)	,--	项目所在区域
	pro_name	varchar(300)	,--	项目名称
	owner_comp	varchar(300)	,--	业主单位
	model	varchar(300)	,--	产品型号
	voltage_level	varchar(300)	,--	电压等级
	product_amount	numeric(18,4)	,--	产品数量
	product_capacity	numeric(18,4)	,--	容量
	contract_amount	numeric(18,4)	,--	合同金额
	payment	varchar(300)	,--	付款方式
	person_in_charge	varchar(300)	,--	责任人
	specific_bid_comp	varchar(300)	,--	具体投标单位
	deliver_date	date	,--	 产品交货期
	jydw int,
	source varchar(100),
	time datetime
PRIMARY KEY CLUSTERED 						
(						
	[id] ASC					
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]						
) ON [PRIMARY]	