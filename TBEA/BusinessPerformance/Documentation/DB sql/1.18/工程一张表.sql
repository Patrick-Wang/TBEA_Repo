IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'project_comprehensive_table_pm_ex')
DROP TABLE project_comprehensive_table_pm_ex
CREATE TABLE [dbo].[project_comprehensive_table_pm_ex](	
	[id] 	[int] IDENTITY(1,1) NOT NULL	,		
	statistical_year	int	,--	数据统计年份
	statistical_month	int	,--	数据统计月份
	pro_company	varchar(300)	,--	项目公司/事业部
	pro_no	varchar(100)	,--	项目编号
	pro_name	varchar(500)	,--	项目全称
	pro_type	varchar(50)	,--	项目类型（风电、光伏、输变电、调试、其他）
	pro_category	varchar(10)	,--	工程类别（BT、EPC、PC、C、BOO、BOT、PPP）
	pro_capacity	numeric(18, 4)	,--	项目容量（MW）
	is_sign_by_jointstock_company	varchar(10)	,--	是否是以股份公司名义签订合同
	customer_name	varchar(500)	,--	客户全称（拟转让合作方）
	contract_amount	date	,--	合同金额（按最新约定）
	contract_amount_carry_down	numeric(18, 4)	,--	含结转到今年的合同额
	design_company_name	varchar(500)	,--	设计单位名称
	contract_sign_date	date	,--	签约日期（2016****）
	contract_start_time	date	,--	合同约定开工时间
	contract_completion_time	date	,--	合同约定竣工时间
	actual_start_time	date	,--	实际开工时间
	payment_mode	varchar(500)	,--	合同付款方式
	prepayment	numeric(18, 4)	,--	预付款金额（万元）
	shareholding_ratio	numeric(18, 4)	,--	项目持股比例
	amount_received	numeric(18, 4)	,--	收入金额（万元，按最新约定，不含税）
	bid_budget_cost	numeric(18, 4)	,--	投标预算总成本（万元）
	target_cost	numeric(18, 4)	,--	目标总成本（万元）
	dynamic_cost	numeric(18, 4)	,--	动态总成本（万元）
	unit_dynamic_cost	numeric(18, 4)	,--	单位动态总成本（元/瓦）
	earned_value	numeric(18, 4)	,--	已完工程计划成本（自项目开始起）
	actual_cost	numeric(18, 4)	,--	已完工程实际成本（自项目开始起）
	finacial_record_gross_profit_rate	numeric(18, 4)	,--	财务备案毛利率（%）
	actual_revenue_current_month	numeric(18, 4)	,--	本月实际确认收入（万元）
	make_out_the_invoice	numeric(18, 4)	,--	本月实际开票金额（万元）
	gross_profit_current_month	numeric(18, 4)	,--	本月实际毛利（万元）
	recorded_inventory_current_mont	numeric(18, 4)	,--	本月实际入账存货（万元）
	fund_restream_current_month	numeric(18, 4)	,--	本月实际回款金额（万元）
	accu_revenue	numeric(18, 4)	,--	累计确认收入（万元）
	accu_gross_profit	numeric(18, 4)	,--	累计体现毛利（万元）
	accu_make_out_the_invoice	numeric(18, 4)	,--	累计开票金额（万元）
	accu_fund_restream	numeric(18, 4)	,--	累计回款金额（万元）
	accu_fund_restream_of_plan	numeric(18, 4)	,--	累计应回款金额（根据合同）
	amount_receivable	numeric(18, 4)	,--	账面应收余额（万元）
	accu_recorded_inventory	numeric(18, 4)	,--	累计入账存货（万元）
	balance_of_inventory	numeric(18, 4)	,--	存货余额（万元）
	book_inventory	numeric(18, 4)	,--	其中：账面存货
	balance_inventory	numeric(18, 4)	,--	其中：已项目公司资产形式形成的存货余额
	carry_down_inventory	numeric(18, 4)	,--	已结转存货
	dynamic_gross_profit_rate	numeric(18, 4)	,--	动态毛利率（%）
	overdue_payment	numeric(18, 4)	,--	逾期款金额
	revenue_next_month	numeric(18, 4)	,--	下月计划确认收入（万元）
	make_out_the_invoice_next_month	numeric(18, 4)	,--	下月计划开票金额（万元）
	recorded_inventory_next_month	numeric(18, 4)	,--	下月计划入账存货（万元）
	received_payment_next_month	numeric(18, 4)	,--	下月计划回款金额（万元）
	payment_next_month	numeric(18, 4)	,--	下月计划支付金额（万元）
	cost_control_of_plan_amount	numeric(18, 4)	,--	成本管控目标金额（万元）
	accu_completion_of_plan_amount	numeric(18, 4)	,--	累计计划完成目标金额（万元）
	accu_completion_of_actual_amount	numeric(18, 4)	,--	累计实际完成目标金额（万元）
	completion_target_plan_this_month	numeric(18, 4)	,--	本月计划完成目标金额（万元）
	completion_target_actual_this_month	numeric(18, 4)	,--	本月实际完成目标金额（万元）
	completion_target_plan_next_month	numeric(18, 4)	,--	下月计划完成目标金额（万元）
	payment_this_month	numeric(18, 4)	,--	本月付款（万元）
	accu_payment_this_month	numeric(18, 4)	,--	累计付款（万元）
	commencement_date	date	,--	进场施工日期（2016****）
	visual_schedule_percent	numeric(18, 4)	,--	形象进度百分比（%）
	actual_installed_capacity	numeric(18, 4)	,--	实际装机容量（MW）
	estimated_completion_date	date	,--	项目预计竣工日期（2016****）
	actual_completion_date	date	,--	项目实际竣工日期（2016****）
	actual_final_date	date	,--	项目实际（预计）决算日期（2016****）
	is_booster_station_build_byself	varchar(10)	,--	是否自建升压站
	booster_station_voltage_level	numeric(18, 4)	,--	升压站电压等级(kV)
	booster_station_capacity	numeric(18, 4)	,--	升压站容量(kVA)
	is_outside_connections_build_byself	varchar(10)	,--	是否自建外线
	outside_connections_voltage_level	numeric(18, 4)	,--	外线电压等级(kV)
	outside_connections_length	numeric(18, 4)	,--	外线长度(kM)
	project_verification_owner	numeric(18, 4)	,--	本月签证金额（业主）已确认
	project_verification_simulation 	numeric(18, 4)	,--	本月签证金额（业主）拟提交
	accu_project_verification_owner	numeric(18, 4)	,--	签证累计金额（业主）已确认
	accu_project_verification_simulation 	numeric(18, 4)	,--	签证累计金额（业主）拟提交
	project_verification_change_owner	numeric(18, 4)	,--	本月变更金额（业主）已确认
	project_verification_change_simulation 	numeric(18, 4)	,--	本月变更金额（业主）拟提交
	accu_project_verification_change_owner	numeric(18, 4)	,--	变更累计金额（业主）已确认
	accu_project_verification_change_simulation 	numeric(18, 4)	,--	变更累计金额（业主）拟提交
	project_payment_for_approval	numeric(18, 4)	,--	工程结算（业务）报审金额
	project_payment_for_authorization	numeric(18, 4)	,--	工程结算（业务）审定金额
	grid_onnection_all_stations	date	,--	全站并网日期（2016****）
	grid_connectioned_capacity	numeric(18, 4)	,--	已并网容量（MW）
	generate_electricity_hours	numeric(18, 5)	,--	年均理论有效发电小时数（填报开工申请数据）
	average_load	numeric(18, 6)	,--	本月平均负荷
	generating_capacity_plan	numeric(18, 7)	,--	本月计划发电量（万kWh）
	generating_capacity_actual	numeric(18, 8)	,--	本月实际发电量（万kWh）
	accu_ongrid_energy	numeric(18, 9)	,--	年度累计上网电量（万kWh）
	generating_capacity_plan_next_month	numeric(18, 10)	,--	下月计划发电量（万kWh）
	time_of_warranty_contract	numeric(18, 11)	,--	合同约定质保期（月）
	time_of_warranty_standard	varchar(500)	,--	质保期起算标准
	time_of_warranty_start_time	date	,--	质保期起算时间
	is_replace_by_guarantee	varchar(10)	,--	是否保函换质保金
	deal_with_in_warranty	varchar(500)	,--	质保期间的问题反馈沟通处理
	registered_principal_customer	numeric(18, 4)	,--	客户注册资本金
	enterprise_nature_customer	varchar(100)	,--	客户企业性质（国企、民企、私企、外企）
	guarantee_status_customer	varchar(500)	,--	客户担保、抵押、付款保函情况
	is_advance_money	varchar(10)	,--	是否垫资业务
	is_legitimate_of_project	varchar(10)	,--	项目是否合法合规
	is_delay	varchar(10)	,--	工期是否延误及产生的风险
	is_cover_EPC	varchar(10)	,--	关键设备采购合同质保期及起算时间是否覆盖EPC合同
	generating_capacity_promise	varchar(500)	,--	发电量承诺情况
	is_show_guarantee_to_customer	varchar(10)	,--	是否向业主出具过保函、保证文件
	project_risk	varchar(500)	,--	项目风险及隐患
	exchange_rate_risk	varchar(500)	,--	汇率风险
	multicurrency_risk	varchar(500)	,--	多币种风险
	local_labour_services_risk	varchar(500)	,--	当地劳务风险（民工工资发放）
	equipment_logistics_material_risk	varchar(500)	,--	设备、物流、物资风险
PRIMARY KEY CLUSTERED 			
(			
	[id] ASC		
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]			
) ON [PRIMARY]			
