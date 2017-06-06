IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'storage_swccptz')
DROP TABLE storage_swccptz
CREATE TABLE [dbo].[storage_swccptz](
	[id] 	[int] IDENTITY(1,1) NOT NULL	,			
	statistics_year	int	,	--	统计年份	
	statistics_month	int	,	--	统计月份	
	status	varchar(100)	,	--	状态	
	company_name	varchar(300)	,	--	项目公司	
	contract_no	varchar(300)	,	--	合同编号	
	customer_name	varchar(300)	,	--	客户单位名称	
	deliver_date	varchar(300)	,	--	合同约定交货期	
	contract_amount	numeric(18, 4)	,	--	合同金额	
	level	varchar(300)	,	--	规格型号及电压等级/产品大类	
	stock_amount	numeric(18, 4)	,	--	库存数量	
	stock_money	numeric(18, 4)	,	--	库存金额	
	complete_date	date	,	--	完工入库日期	
	stock_contract_date	numeric(18, 4)	,	--	库存部分合同金额	
	amount_of_paid	numeric(18, 4)	,	--	库存部分已付货款	金额
	ratio_of_paid	numeric(18, 4)	,	--		占库存合同金额比例
	plan_of_send_date	varchar(300)	,	--	预计发货时间	
	reason_of_nondeliver	varchar(300)	,	--	未发货原因	
	risk	varchar(300)	,	--	风险评估	
	added_month	varchar(300)	,	--	库存新增月份	
	is_overstock	varchar(10)	,	--	是否形成积压	
	person_liable	varchar(100),		--	责任人	
	source varchar(100),
	time datetime
PRIMARY KEY CLUSTERED 						
(						
	[id] ASC					
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]						
) ON [PRIMARY]						
