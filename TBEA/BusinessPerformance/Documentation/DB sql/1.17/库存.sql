--实物成品台账数据
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'storage_swccptz')
DROP TABLE storage_swccptz
CREATE TABLE [dbo].[storage_swccptz](
	[id] 	[int] IDENTITY(1,1) NOT NULL	,
	status	varchar(100)	,	--	状态	
	company_name	varchar(300)	,	--	项目公司	
	contract_no	varchar(300)	,	--	合同编号	
	customer_name	varchar(300)	,	--	客户单位名称	
	deliver_date	date	,	--	合同约定交货期	
	contract_amount	numeric(18, 4)	,	--	合同金额	
	level	varchar(300)	,	--	规格型号及电压等级/产品大类	
	stock_amount	numeric(18, 4)	,	--	库存数量	
	stock_money	numeric(18, 4)	,	--	库存金额	
	complete_date	date	,	--	完工入库日期	
	stock_contract_date	numeric(18, 4)	,	--	库存部分合同金额	
	amount_of_paid	numeric(18, 4)	,	--	库存部分已付货款	金额
	ratio_of_paid	numeric(18, 4)	,	--		占库存合同金额比例
	plan_of_send_date	date	,	--	预计发货时间	
	reason_of_nondeliver	varchar(300)	,	--	未发货原因	
	risk	varchar(300)	,	--	风险评估	
	added_month	date	,	--	库存新增月份	
	is_overstock	varchar(10)	,	--	是否形成积压	
	person_liable	varchar(100),		--	责任人	
	source varchar(100),
	time datetime
PRIMARY KEY CLUSTERED 						
(						
	[id] ASC					
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]						
) ON [PRIMARY]						

--原材料明细表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'storage_yclmx')
DROP TABLE storage_yclmx
CREATE TABLE [dbo].[storage_yclmx](
	[id] 	[int] IDENTITY(1,1) NOT NULL	,	
	company_name	varchar(300)	,--	项目公司	
	material_name	varchar(300)	,--	材料名称	
	model	varchar(300)	,--	规格型号	
	provider	varchar(300)	,--	供应商	
	measurement_unit	varchar(300)	,--	计量单位	
	amount	numeric(18, 4)	,--	数量	
	unit_price	numeric(18, 4)	,--	单价	
	amount_of_money	numeric(18, 4)	,--	金额（万元）	
	storage_date	date	,--	"入库日期(年/月/日)"	
	stock_duration	numeric(18, 2)	,--	库存时长（月）	
	cause	varchar(300)	,--	形成原因	
	treatment	varchar(300)	,--	建议处置措施	
	risk_assessment	varchar(300)	,--	风险预警评估	
	added_month	date	,--	"新增月份(X年X月)"	
	is_overstock	varchar(10)	,--	是否形成积压	
	person_liable	varchar(100)	,--	责任人	
	comletion_date	date	,--	"完成时间(年/月/日)"	
	amount_of_this_month	numeric(18, 4)	,--	本月消化库存（含积压物资）	数量
	money_of_this_month	numeric(18, 4)	,--		金额（万元）
	action_of_this_month	varchar(300)	,--		处置措施
	source varchar(100),
	time datetime
PRIMARY KEY CLUSTERED 						
(						
	[id] ASC					
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]						
) ON [PRIMARY]						

--半成品统计表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'storage_bcpmx')
DROP TABLE storage_bcpmx
CREATE TABLE [dbo].[storage_bcpmx](
	[id]	[int] IDENTITY(1,1) NOT NULL	,	
	company_name	varchar(300)	,--	项目单位	
	type_name	varchar(300)	,--	大类名称	
	model	varchar(300)	,--	规格型号	
	amount	numeric(18, 4)	,--	数量	
	measurement_unit	varchar(300)	,--	计量单位	
	storage_date	date	,--	"形成日期（年/月/日）"	
	stock_duration	numeric(18, 2)	,--	库存时长（月）	
	cause	varchar(300)	,--	形成原因	
	treatment	varchar(300)	,--	处理方案	
	risk_assessment	varchar(300)	,--	风险评估	
	added_month	date	,--	"新增月份（X年X月）"	
	is_overstock	varchar(10)	,--	是否形成积压	
	person_liable	varchar(100)	,--	责任人	
	comletion_date	date	,--	"完成时间（年/月/日）"	
	amount_of_this_month	numeric(18, 4)	,--	本月消化存货（含积压物资）	数量
	money_of_this_month	numeric(18, 4)	,--		金额(万元)
	action_of_this_month	varchar(300)	,--		处置措施
	source varchar(100),
	time datetime
PRIMARY KEY CLUSTERED 						
(						
	[id] ASC					
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]						
) ON [PRIMARY]	