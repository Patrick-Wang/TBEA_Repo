
--投标订单明细
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'JYGK_SDDB_BIDDING_ORDER_DETAILS')
DROP TABLE JYGK_SDDB_BIDDING_ORDER_DETAILS
CREATE TABLE [dbo].[JYGK_SDDB_BIDDING_ORDER_DETAILS](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	bid_id varchar(50) not null,
	company_name	nvarchar(200)	NOT NULL,--No	投标单位
	bidding_date	date	NOT NULL,--No	投标报价日期
	client_name	nvarchar(1000)	,--Yes	用户名称
	project_name	nvarchar(1000)	,--Yes	项目名称
	ETD_year	[numeric](18, 4)	,--Yes	预计交货年份
	ETD_month	[numeric](18, 4)	,--Yes	预计交货月份
	model	nvarchar(100)	,--Yes	型号
	voltage 	nvarchar(100)	,--Yes	电压
	capacity	nvarchar(100)	,--Yes	容量
	number	[numeric](18, 4)	,--Yes	台数
	tax_inclusive_price	[numeric](18, 4)	,--Yes	合同金额
	not_tax_inclusive_price	[numeric](18, 4)	,--Yes	合同金额（不含税）
	bid_opening_year	[numeric](18, 4)	,--Yes	预计开标年份
	bid_opening_month	[numeric](18, 4)	,--Yes	预计开标月份
	hit_rate	[numeric](18, 4)	,--Yes	销售部门预测的中标概率(%)
	bid_silicon_steel_grades 	nvarchar(100)	,--Yes	投标硅钢牌号
	bid_silicon_steel_amount	[numeric](18, 4)	,--Yes	投标硅钢用量（单台）
	bid_silicon_steel_price	[numeric](18, 4)	,--Yes	投标硅钢单价
	bid_ele_copper_amount	[numeric](18, 4)	,--Yes	投标电解铜用量（单台）
	bid_ele_copper_price	[numeric](18, 4)	,--Yes	投标电解铜单价
	bid_ele_copper_process_cost	[numeric](18, 4)	,	--电解铜加工费
	bid_insulating_oil_amount	[numeric](18, 4)	,--Yes	投标变压器油用量（单台）
	bid_insulating_oil_price	[numeric](18, 4)	,--Yes	投标变压器油单价
	bid_rolled_steel_amount	[numeric](18, 4)	,--Yes	投标钢材用量（单台）
	bid_rolled_steel_price	[numeric](18, 4)	,--Yes	投标钢材单价
	bid_cardboard_amount	[numeric](18, 4)	,--Yes	投标纸板用量（单台）
	bid_cardboard_price	[numeric](18, 4)	,--Yes	投标纸板单价
	other_material_cost [numeric](18, 4), --其他材料成本
	bid_material_total	[numeric](18, 4)	,--Yes	投标材料总计
	labour_cost	[numeric](18, 4)	,--Yes	人工费用
	manufacturing_cost	[numeric](18, 4)	,--Yes	制造费用
	bid_cost	[numeric](18, 4)	,--Yes	投标制造成本
	gross_profit	[numeric](18, 4)	,--Yes	投标毛利（单台）
	period	[numeric](18, 4)	,--Yes	周时间
	is_new_production	nvarchar(10),	--Yes	是否新产品
	source [varchar](50),
	time datetime
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]



--中标订单明细
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'JYGK_SDDB_WIN_BIDDING_ORDER_DETAILS')
DROP TABLE JYGK_SDDB_WIN_BIDDING_ORDER_DETAILS
CREATE TABLE [dbo].[JYGK_SDDB_WIN_BIDDING_ORDER_DETAILS](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	company_name	nvarchar(200)	NOT NULL,--No	投标单位-------------------------------------
	win_bid_year	[numeric](18, 4)	NOT NULL,--No	中标年份
	win_bid_month	[numeric](18, 4)	NOT NULL,--No	中标月份
	client_name	nvarchar(1000)	,--Yes	用户名称
	project_name	nvarchar(1000)	,--Yes	项目名称
	contract_no	nvarchar(100)	NOT NULL,--No	合同编号
	delivery_year	[numeric](18, 4)	,--Yes	交货年份
	delivery_month	[numeric](18, 4)	,--Yes	交货月份
	model	nvarchar(100)	,--Yes	型号
	voltage 	nvarchar(100)	,--Yes	电压
	capacity	nvarchar(100)	,--Yes	容量
	number	[numeric](18, 4)	,--Yes	台数
	code	nvarchar(100)	NOT NULL,--Yes	图号/技术代号
	tax_inclusive_price	[numeric](18, 4)	,--Yes	合同金额
	not_tax_inclusive_price	[numeric](18, 4)	,--Yes	合同金额（不含税）
	win_bid_silicon_steel_grades	nvarchar(100)	,--Yes	中标硅钢牌号
	win_bid_silicon_steel_amount	[numeric](18, 4)	,--Yes	中标硅钢用量
	win_bid_silicon_steel_price	[numeric](18, 4)	,--Yes	中标硅钢单价
	win_bid_ele_copper_amount	[numeric](18, 4)	,--Yes	中标电解铜用量
	win_bid_ele_copper_price	[numeric](18, 4)	,--Yes	中标电解铜单价
	win_bid_ele_copper_process_cost [numeric](18, 4)	,--Yes	中标电解铜加工费
	win_bid_insulating_oil_amount	[numeric](18, 4)	,--Yes	中标变压器油用量
	win_bid_insulating_oil_price	[numeric](18, 4)	,--Yes	中标变压器油单价
	win_bid_rolled_steel_amount	[numeric](18, 4)	,--Yes	中标钢材用量
	win_bid_rolled_steel_price	[numeric](18, 4)	,--Yes	中标钢材单价
	win_bid_cardboard_amount	[numeric](18, 4)	,--Yes	中标纸板用量
	win_bid_cardboard_price	[numeric](18, 4)	,--Yes	中标纸板单价
	
	win_bid_casing_quantity	[numeric](18, 4)	,--Yes	中标套管数量
	win_bid_casing_unit_price	[numeric](18, 4)	,--Yes	中标套管单价
	win_bid_number_of_switches	[numeric](18, 4)	,--Yes	中标开关数量
	win_bid_switch_unit_price	[numeric](18, 4)	,--Yes	中标开关单价
	win_bid_number_of_cooler	[numeric](18, 4)	,--Yes	中标冷却器数量
	win_bid_cooler_unit_price	[numeric](18, 4)	,--Yes	中标冷却器单价
	win_bid_online_monitoring_quantity	[numeric](18, 4)	,--Yes	中标在线监测数量
	win_bid_online_monitoring_unit_price	[numeric](18, 4)	,--Yes	中标在线监测单价
	
	win_bid_other_material_cost	[numeric](18, 4)	,--Yes	中标其他材料成本
	win_bid_material_total	[numeric](18, 4)	,--Yes	中标材料总计
	labour_cost	[numeric](18, 4)	,--Yes	人工费用
	manufacturing_cost	[numeric](18, 4)	,--Yes	制造费用
	win_bid_cost	[numeric](18, 4)	,--Yes	中标材料成本总计
	win_bid_gross_profit_amount	[numeric](18, 4)	,--Yes	中标毛利额
	win_bid_gross_margin	[numeric](18, 4)	,--Yes	中标毛利率
	is_new_production	nvarchar(10),	--Yes	是否新产品
	source [varchar](50),
	time datetime
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

--订单执行阶段明细
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'JYGK_SDDB_ORDER_EXECUTION_DETAILS')
DROP TABLE JYGK_SDDB_ORDER_EXECUTION_DETAILS
CREATE TABLE [dbo].[JYGK_SDDB_ORDER_EXECUTION_DETAILS](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	company_name	nvarchar(200)	NOT NULL,--No	投标单位----------------------------------------------
	win_bid_year	[numeric](18, 4)	NOT NULL,--No	中标年份
	win_bid_month	[numeric](18, 4)	NOT NULL,--No	中标月份
	client_name	nvarchar(1000)	,--Yes	用户名称
	project_name	nvarchar(1000)	,--Yes	项目名称
	contract_no	nvarchar(100)	NOT NULL,--No	合同编号
	execution_year	[numeric](18, 4)	,--Yes	排产年份
	execution_month	[numeric](18, 4)	,--Yes	排产月份
	actual_delivery_year	[numeric](18, 4)	,--Yes	具体交货年份
	actual_delivery_month	[numeric](18, 4)	,--Yes	具体交货月份
	model	nvarchar(100)	,--Yes	型号
	voltage 	nvarchar(100)	,--Yes	电压
	capacity	nvarchar(100)	,--Yes	容量
	number	[numeric](18, 4)	,--Yes	台数
	code	nvarchar(100)	NOT NULL,--Yes	图号/技术代号
	tax_inclusive_price	[numeric](18, 4)	,--Yes	合同金额
	not_tax_inclusive_price	[numeric](18, 4)	,--Yes	合同金额（不含税）
	bidding_transportation_fee	[numeric](18, 4)	,--Yes	费用_投标运输费
	optimizing_transportation_cost	[numeric](18, 4)	,--Yes	费用_优化运输费
	win_bid_cost	[numeric](18, 4)	,--Yes	费用_中标费
	agency_fee	[numeric](18, 4)	,--Yes	费用_代理费
	es_silicon_steel_grades	nvarchar(100)	,--Yes	测算_硅钢牌号
	es_substitute_grade	nvarchar(100)	,--Yes	测算_代用牌号
	es_quantity_of_silicon_steel	[numeric](18, 4)	,--Yes	测算_硅钢数量
	es_unit_price_of_silicon_steel	[numeric](18, 4)	,--Yes	测算_硅钢单价
	es_amount_of_silicon_steel	[numeric](18, 4)	,--Yes	测算_硅钢金额
	es_process_cost	[numeric](18, 4)	,--Yes	测算_加工费
	es_total_amount_of_silicon_steel	[numeric](18, 4)	,--Yes	测算_硅钢总金额
	es_copper_quantity	[numeric](18, 4)	,--Yes	测算_铜数量
	es_copper_price	[numeric](18, 4)	,--Yes	测算_铜单价
	es_copper_processing_fee	[numeric](18, 4)	,--Yes	测算_铜加工费
	es_split_cover	[numeric](18, 4)	,--Yes	测算_分摊套保
	es_copper_amount	[numeric](18, 4)	,--Yes	测算_铜线金额
	es_transformer_oil_specification	nvarchar(100)	,--Yes	测算_变压器油规格
	es_transformer_oil_quantity	[numeric](18, 4)	,--Yes	测算_变压器油数量
	es_unit_price_of_transformer_oil	[numeric](18, 4)	,--Yes	测算_变压器油单价
	es_transformer_oil_amount	[numeric](18, 4)	,--Yes	测算_变压器油金额
	es_quantity_of_steel	[numeric](18, 4)	,--Yes	测算_钢材数量
	es_steel_price	[numeric](18, 4)	,--Yes	测算_钢材单价
	es_amount_of_steel	[numeric](18, 4)	,--Yes	测算_钢材金额
	es_cardboard_quantity	[numeric](18, 4)	,--Yes	测算_纸板数量
	es_cardboard_unit_price	[numeric](18, 4)	,--Yes	测算_纸板单价
	es_amount_of_cardboard	[numeric](18, 4)	,--Yes	测算_纸板金额
	es_casing_quantity	[numeric](18, 4)	,--Yes	测算_套管数量
	es_casing_unit_price	[numeric](18, 4)	,--Yes	测算_套管单价
	es_casing_amount	[numeric](18, 4)	,--Yes	测算_套管金额
	es_number_of_switches	[numeric](18, 4)	,--Yes	测算_开关数量
	es_switch_unit_price	[numeric](18, 4)	,--Yes	测算_开关单价
	es_switch_amount	[numeric](18, 4)	,--Yes	测算_开关金额
	es_number_of_cooler	[numeric](18, 4)	,--Yes	测算_冷却器数量
	es_cooler_unit_price	[numeric](18, 4)	,--Yes	测算_冷却器单价
	es_amount_of_cooler	[numeric](18, 4)	,--Yes	测算_冷却器金额
	es_online_monitoring_quantity	[numeric](18, 4)	,--Yes	测算_在线监测数量
	es_online_monitoring_unit_price	[numeric](18, 4)	,--Yes	测算_在线监测单价
	es_online_monitoring_amount	[numeric](18, 4)	,--Yes	测算_在线监测金额
	es_other_material_costs	[numeric](18, 4)	,--Yes	测算_其他材料成本
	es_total_amount_of_material	[numeric](18, 4)	,--Yes	测算_材料总金额
	es_spare_parts	nvarchar(100)	,--Yes	测算_备品备件
	es_material_cost	[numeric](18, 4)	,--Yes	测算_材料成本
	es_labor_wages	[numeric](18, 4)	,--Yes	测算_人工工资
	es_manufacturing_cost	[numeric](18, 4)	,--Yes	测算_制造费用
	es_total_product_cost	[numeric](18, 4)	,--Yes	测算_产品成本合计
	es_gross_profit_amount	[numeric](18, 4)	,--Yes	测算_毛利额
	es_gross_margin	[numeric](18, 4)	,--Yes	测算_毛利率
	op_silicon_steel_grades	nvarchar(100)	,--Yes	优化阶段_硅钢牌号
	op_substitute_grade	nvarchar(100)	,--Yes	优化阶段_代用牌号
	op_quantity_of_silicon_steel	[numeric](18, 4)	,--Yes	优化阶段_硅钢数量
	op_unit_price_of_silicon_steel	[numeric](18, 4)	,--Yes	优化阶段_硅钢单价
	op_amount_of_silicon_steel	[numeric](18, 4)	,--Yes	优化阶段_硅钢金额
	op_processing_cost_of_silicon_steel	[numeric](18, 4)	,--Yes	优化阶段_硅钢加工费
	op_copper_consumption	[numeric](18, 4)	,--Yes	优化阶段_铜用量
	op_copper_price	[numeric](18, 4)	,--Yes	优化阶段_铜单价
	op_amount_of_copper	[numeric](18, 4)	,--Yes	优化阶段_铜金额
	op_copper_processing_fee	[numeric](18, 4)	,--Yes	优化阶段_铜加工费
	op_transformer_oil_specification	[numeric](18, 4)	,--Yes	优化阶段_变压器油规格
	op_transformer_oil_consumption	[numeric](18, 4)	,--Yes	优化阶段_变压器油用量
	op_unit_price_of_transformer_oil	[numeric](18, 4)	,--Yes	优化阶段_变压器油单价
	op_transformer_oil_amount	[numeric](18, 4)	,--Yes	优化阶段_变压器油金额
	op_steel_dosage	[numeric](18, 4)	,--Yes	优化阶段_钢材用量
	op_steel_price	[numeric](18, 4)	,--Yes	优化阶段_钢材单价
	op_amount_of_steel	[numeric](18, 4)	,--Yes	优化阶段_钢材金额
	op_cardboard_consumption	[numeric](18, 4)	,--Yes	优化阶段_纸板用量
	op_cardboard_unit_price	[numeric](18, 4)	,--Yes	优化阶段_纸板单价
	op_amount_of_cardboard	[numeric](18, 4)	,--Yes	优化阶段_纸板金额
	op_casing_quantity	[numeric](18, 4)	,--Yes	优化阶段_套管数量
	op_casing_unit_price	[numeric](18, 4)	,--Yes	优化阶段_套管单价
	op_casing_subtotal	[numeric](18, 4)	,--Yes	优化阶段_套管小计
	op_number_of_switches	[numeric](18, 4)	,--Yes	优化阶段_开关数量
	op_switch_unit_price	[numeric](18, 4)	,--Yes	优化阶段_开关单价
	op_switch_subtotal	[numeric](18, 4)	,--Yes	优化阶段_开关小计
	op_number_of_cooler	[numeric](18, 4)	,--Yes	优化阶段_冷却器数量
	op_cooler_unit_price	[numeric](18, 4)	,--Yes	优化阶段_冷却器单价
	op_cooler_subtotal	[numeric](18, 4)	,--Yes	优化阶段_冷却器小计
	op_online_monitoring_quantity	[numeric](18, 4)	,--Yes	优化阶段_在线监测数量
	op_online_monitoring_unit_price	[numeric](18, 4)	,--Yes	优化阶段_在线监测单价
	op_online_monitoring_subtotal	[numeric](18, 4)	,--Yes	优化阶段_在线监测小计
	op_other_materials	[numeric](18, 4)	,--Yes	优化阶段_其他材料
	op_cost_of_tax_bearing_materials	[numeric](18, 4)	,--Yes	优化阶段_含税材料成本
	op_spare_parts	nvarchar(100)	,--Yes	优化阶段_备品备件
	op_total_tax_material	[numeric](18, 4)	,--Yes	优化阶段_不含税材料合计
	op_labor_cost	[numeric](18, 4)	,--Yes	优化阶段_人工制造费用
	op_production_costs	[numeric](18, 4)	,--Yes	优化阶段_生产成本
	op_gross_profit_amount	[numeric](18, 4)	,--Yes	优化阶段_毛利额
	op_optimized_gross_margin	[numeric](18, 4)	,--Yes	优化阶段_优化毛利率
	review_status	nvarchar(1000),	--Yes	评审状态
	source [varchar](50),
	time datetime
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

--订单完工阶段明细分析
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'JYGK_SDDB_ORDER_COMPLETION_DETAILS')
DROP TABLE JYGK_SDDB_ORDER_COMPLETION_DETAILS
CREATE TABLE [dbo].[JYGK_SDDB_ORDER_COMPLETION_DETAILS](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	company_name	nvarchar(200)	NOT NULL,--No	投标单位-------------------------------------------------------
	completion_year	[numeric](18, 4)	NOT NULL,--No	完工年份
	completion_month	[numeric](18, 4)	NOT NULL,--No	完工月份
	client_name	nvarchar(1000)	NOT NULL,--No	用户名称
	project_name	nvarchar(1000)	,--Yes	项目名称
	contract_no nvarchar(100)	NOT NULL,--Yes	合同编号
	model	nvarchar(100)	,--Yes	型号
	voltage 	nvarchar(100)	,--No	电压
	capacity	nvarchar(100)	,--Yes	容量
	number	[numeric](18, 4)	,--Yes	台数
	code	nvarchar(100)	NOT NULL,--Yes	图号/技术代号
	tax_inclusive_price	[numeric](18, 4)	,--Yes	合同金额
	not_tax_inclusive_price	[numeric](18, 4)	,--Yes	"合同金额（不含税）"
	actual_amount_of_silicon_steel	[numeric](18, 4)	,--Yes	实际硅钢片用量
	actual_price_of_silicon_steel	[numeric](18, 4)	,--Yes	实际硅钢片单价
	amount_of_actual_silicon_steel	[numeric](18, 4)	,--Yes	实际硅钢片金额
	actual_electrolytic_copper_consumption	[numeric](18, 4)	,--Yes	实际电解铜用量
	copper_price	[numeric](18, 4)	,--Yes	铜单价（含税，无加工费
	copper_processing_fee	[numeric](18, 4)	,--Yes	铜加工费(含税)
	actual_electrolytic_copper_amount	[numeric](18, 4)	,--Yes	实际电解铜金额（含税，含加工费）
	actual_transformer_oil_consumption	[numeric](18, 4)	,--Yes	实际变压器油用量
	actual_transformer_oil_unit_price	[numeric](18, 4)	,--Yes	实际变压器油单价
	actual_transformer_oil_amount	[numeric](18, 4)	,--Yes	实际变压器油金额
	actual_steel_consumption	[numeric](18, 4)	,--Yes	实际钢材用量
	actual_steel_price	[numeric](18, 4)	,--Yes	实际钢材单价
	actual_steel_amount	[numeric](18, 4)	,--Yes	实际钢材金额
	actual_insulation_board_usage	[numeric](18, 4)	,--Yes	实际绝缘纸板用量
	actual_insulation_board_price	[numeric](18, 4)	,--Yes	实际绝缘纸板单价
	actual_insulation_board_amount	[numeric](18, 4)	,--Yes	实际绝缘纸板金额
	casing_quantity	[numeric](18, 4)	,--Yes	套管数量
	casing_unit_price	[numeric](18, 4)	,--Yes	套管单价
	casing_amount	[numeric](18, 4)	,--Yes	套管金额
	number_of_switches	[numeric](18, 4)	,--Yes	开关数量
	switch_unit_price	[numeric](18, 4)	,--Yes	开关单价
	switch_amount	[numeric](18, 4)	,--Yes	开关金额
	number_of_cooler	[numeric](18, 4)	,--Yes	冷却器数量
	cooler_unit_price	[numeric](18, 4)	,--Yes	冷却器单价
	amount_of_cooler	[numeric](18, 4)	,--Yes	冷却器金额
	online_monitoring_quantity	[numeric](18, 4)	,--Yes	在线监测数量
	online_monitoring_unit_price	[numeric](18, 4)	,--Yes	在线监测单价
	online_monitoring_amount	[numeric](18, 4)	,--Yes	在线监测金额
	the_actual_cost_of_materials	[numeric](18, 4)	,--Yes	实际主材成本
	actual_amount_of_other_materials	[numeric](18, 4)	,--Yes	实际其他材料金额
	actual_cost_of_other_materials	[numeric](18, 4)	,--Yes	实际其他材料成本
	the_cost_of_spare_parts	[numeric](18, 4)	,--Yes	备品备件成本
	total_material_cost	[numeric](18, 4)	,--Yes	实际材料成本总计
	actual_labor_cost	[numeric](18, 4)	,--Yes	实际人工费用
	actual_manufacturing_cost	[numeric](18, 4)	,--Yes	实际制造费用
	actual_total_cost	[numeric](18, 4)	,--Yes	实际总成本
	actual_gross_profit	[numeric](18, 4)	,--Yes	实际毛利额
	actual_gross_margin	[numeric](18, 4)	,--Yes	实际毛利率
	is_new_production	nvarchar(10),	--Yes	是否新产品
	source [varchar](50),
	time datetime
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


