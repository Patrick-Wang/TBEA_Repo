IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'project_comprehensive_table_pm')
DROP TABLE project_comprehensive_table_pm
CREATE TABLE [dbo].[project_comprehensive_table_pm](
	project_name	[varchar](300)	,--	项目全称
	project_category	[varchar](20)	,--	工程类别（BT、EPC、PC、C、BOO）
	contract_amount	[numeric](18, 4)	,--	合同金额（按最新约定）(万元)
	contract_date	date	,--	签约日期（2016****）
	completion_date	date	,--	要求完工日期（2016****）
	payment_mode	[varchar](5000)	,--	合同付款方式
	prepayment	[numeric](18, 4)	,--	预付款金额（万元）
	income	[numeric](18, 4)	,--	收入金额（万元，按最新约定，不含税）
	bid_budget_cost	[numeric](18, 4)	,--	投标预算总成本（万元）
	target_cost	[numeric](18, 4)	,--	目标总成本（万元）
	dynamic_cost	[numeric](18, 4)	,--	动态总成本（万元）
	unit_dynamic_cost	[numeric](18, 4)	,--	单位动态总成本（元/瓦）
	finacial_record_gross_profit_rate	[numeric](18, 4)	,--	财务备案毛利率（%）
	current_month_fund_restream	[numeric](18, 4)	,--	本月实际回款金额（万元）
	cumulative_fund_restream	[numeric](18, 4)	,--	累计回款金额（万元）
	dynamic_gross_profit_rate	[numeric](18, 4)	,--	动态毛利率（%）
	next_month_fund_restream	[numeric](18, 4)	,--	下月计划回款金额（万元）
	progress_percentage	[numeric](18, 4)	,--	形象进度百分比（%）
	overdue_payment	[numeric](18, 4)	--	逾期款金额（元）
)--19

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'project_comprehensive_table_imported')
DROP TABLE project_comprehensive_table_imported
CREATE TABLE [dbo].[project_comprehensive_table_imported](
	lsh	[varchar](20)	,--	流水号	XNY+六位流水号(000001)
	xmqc	[varchar](300) 	not NULL,--	项目全称	"需要跟PM系统中的项目名称匹配"
	xmgs	[varchar](300)	,--	项目公司/事业部	（集成、风能、风电、国际）
	xmlx	[varchar](20)	,--	项目类型	"（风电、光伏、输变电、调试、其他）"
	project_capacity	[numeric](18, 4)	,--	项目容量（MW）	
	xyjhqrsr	[numeric](18, 4)	,--	下月计划确认收入（万元）	填写数值(没有就为空)
	xyjhkpje	[numeric](18, 4)	,--	下月计划开票金额（万元）	填写数值(没有就为空)
	cost_control_plan_amount [numeric](18, 4)	,--	成本管控目标金额（万元）	填写数值(没有就为空)
	achieve_amount_of_cumup [numeric](18, 4)	,--	累计计划完成目标金额（万元）	填写数值(没有就为空)
	achieve_amount_of_cumua [numeric](18, 4)	,--	累计实际完成目标金额（万元）	填写数值(没有就为空)
	achieve_amount_of_curp	[numeric](18, 4)	,--	本月计划完成目标金额（万元）	填写数值(没有就为空)
	achieve_amount_of_cura	[numeric](18, 4)	,--	本月实际完成目标金额（万元）	填写数值(没有就为空)
	achieve_amount_of_nextp	[numeric](18, 4)	,--	下月计划完成目标金额（万元）	填写数值(没有就为空)
	payment_of_current_month [numeric](18, 4)	,--	本月付款（万元）	填写数值(没有就为空)
	cumulative_payment [numeric](18, 4)	,--	累计付款（万元）	填写数值(没有就为空)
	xyjhrzch	[numeric](18, 4)	,--	下月计划入账存货（万元）	填写数值(没有就为空)
	jcsgrq	date	,--	进场施工日期	日期类型请严格遵照2016/2/1格式
	installed_capacity [numeric](18, 4),--	实际装机容量（MW）	填写数值(没有就为空)
	xmsjjgrq	date	,--	"项目实际（预计）竣工日期"	日期类型请严格遵照2016/2/1格式
	xmsjjsrq	date	,--	"项目实际（预计）决算日期"	日期类型请严格遵照2016/2/1格式
	sfzjsyz	[varchar](10)	,--	是否自建升压站	填写是或者否
	syzdydj	[numeric](18, 4)	,--	升压站电压等级(kV)	填写数值(没有就为空)
	syzrl	[numeric](18, 4)	,--	升压站容量(kVA)	填写数值(没有就为空)
	sfzjwx	[varchar](10)	,--	是否自建外线	填写是或者否
	wxdydj	[numeric](18, 4)	,--	外线电压等级(kV)	填写数值(没有就为空)
	wxcd	[numeric](18, 4)	,--	外线长度(kM)	填写数值(没有就为空)
	qzbwrq	date	,--	全站并网日期	日期类型请严格遵照2016/2/1格式
	ybwrl	[numeric](18, 4)	,--	已并网容量（MW）	填写数值(没有就为空)
	njllyxfdxss	[numeric](18, 4)	,--	年均理论有效发电小时数（填报开工申请数据）	填写数值(没有就为空)
	bypjfh	[numeric](18, 4)	,--	本月平均负荷	
	byjhfdl	[numeric](18, 4)	,--	本月计划发电量（万kWh）	填写数值(没有就为空)
	bysjfdl	[numeric](18, 4)	,--	本月实际发电量（万kWh）	填写数值(没有就为空)
	ndljswdl	[numeric](18, 4)	,--	年度累计上网电量（万kWh）	填写数值(没有就为空)
	xyjhfdl	[numeric](18, 4)	,--	下月计划发电量（万kWh）	填写数值(没有就为空)
	htydzbq	[numeric](18, 4)	,--	合同约定质保期（月）	填写数值(没有就为空)
	zbqqsbz	[varchar](300)	,--	质保期起算标准	
	zbqqssj	date	,--	质保期起算时间	
	sfbhhzbj	[varchar](10)	,--	是否保函换质保金	填写是或者否
	khzczbj	[numeric](18, 4)	,--	客户注册资本金	单位没有
	khqyxz	[varchar](10)	,--	客户企业性质	"（国企、民企、	私企、外企）"
	khdbdyfkbhqk	[varchar](300)	,--	客户担保、抵押、付款保函情况	
	sfdzyw	[varchar](10)	,--	是否垫资业务	
	xmsfhfhg	[varchar](10)	,--	项目是否合法合规	
	gqsfywjcsdfx	[varchar](10)	,--	工期是否延误及产生的风险	
	gjsbcghtzbqjqssjsffgEPCht	[varchar](10)	,--	关键设备采购合同质保期及起算时间是否覆盖EPC合同	
	fdlcnqk	[varchar](300)	,--	发电量承诺情况	
	sfxyzcjgbhbzwj	[varchar](10)	,--	是否向业主出具过保函、保证文件	
	xmfxjyh	[varchar](300),	--	项目风险及隐患
	source [varchar](50),
	time datetime
)

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'project_comprehensive_table_nc')
DROP TABLE project_comprehensive_table_nc
CREATE TABLE [dbo].[project_comprehensive_table_nc](
	id int IDENTITY(1,1) NOT NULL,
	xmbh	[varchar](300)	,--	项目编号
	xmqc	[varchar](300)	,--	项目全称
	khqc	[varchar](300)	,--	客户全称（拟转让合作方）NC中客商辅助
	bysjqrsr	[numeric](18, 4)	,--	本月实际确认收入（万元）
	bysjkpje	[numeric](18, 4)	,--	本月实际开票金额（万元）
	bysjml		[numeric](18, 4)    ,--	本月实际毛利（万元）
	bysjrzch	[numeric](18, 4)	,--	本月实际入账存货（万元）

	ljqrsr	[numeric](18, 4)	,--	累计确认收入（万元）
	ljtxml	[numeric](18, 4)	,--	累计体现毛利（万元）
	ljkpje	[numeric](18, 4)	,--	累计开票金额（万元）
	zmysye	[numeric](18, 4)	,--	账面应收余额（万元）
	ljrzch	[numeric](18, 4)	,--	累计入账存货（万元）
	chye	[numeric](18, 4)	,--	存货余额（万元）
	qzzmch	[numeric](18, 4)	,--	其中：账面存货
	qzyxmgszcxsxcdchye	[numeric](18, 4)	,--	其中：已项目公司资产形式形成的存货余额
	yjzch	[numeric](18, 4)	--	已结转存货
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

