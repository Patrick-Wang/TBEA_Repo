--税金、税负
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financial_tax')
DROP TABLE financial_tax
CREATE TABLE [dbo].[financial_tax](	
	[id] 	[int] IDENTITY(1,1) NOT NULL	,		
	companyName	varchar(100)	,--	"公司名称（请严格参照公司名称对照表）"
	year	int	,--	"年份（只填写数字例如2017,2016）"
	month	int	,--	"月份（只填写数字例如1,2,3）"
	VAT	numeric(18,4)	,--	"增值税(数字 单位元)"
	xssr	numeric(18,4)	,--	"销售收入(数字 单位元)"
	salesIncome	numeric(18,4)	,--	"企业所得税(数字 单位元)"
	totalProfit	numeric(18,4)	,--	利润总额(数字 单位元)
	tariff	numeric(18,4)	,--	"关税(数字 单位元)"
	businessTax	numeric(18,4)	,--	"营业税(数字 单位元)"
	urbanConstructionTax	numeric(18,4)	,--	"城建税(数字 单位元)"
	educationSurtax	numeric(18,4)	,--	"教育费附加(数字 单位元)"
	localEducationSurtax	numeric(18,4)	,--	"地方教育附加(数字 单位元)"
	stampTax	numeric(18,4)	,--	"印花税(数字 单位元)"
	personalIncomeTax 	numeric(18,4)	,--	"个人所得税(数字 单位元)"
	buildingTax	numeric(18,4)	,--	"房产税(数字 单位元)"
	landUseTax	numeric(18,4)	,--	"土地使用税(数字 单位元)"
	landVTA	numeric(18,4)	,--	"土地增值税(数字 单位元)"
	landOccupationTax	numeric(18,4)	,--	"耕地占用税(数字 单位元)"
	vehicleAndVesselTax	numeric(18,4)	,--	"车船使用税(数字 单位元)"
	resourceTax	numeric(18,4)	,--	"资源税(数字 单位元)"
	deedTax	numeric(18,4)	,--	"契税(数字 单位元)"
	floodPreventionCharges	numeric(18,4)	,--	"防洪费(数字 单位元)"
	waterConservancyFund	numeric(18,4)	,--	"水利基金(数字 单位元)"
	mineralResourceCompensation	numeric(18,4)	,--	"矿产资源补偿费(数字 单位元)"
	mtzydfjjfzf	numeric(18,4)	,--	"煤炭资源地方经济发展费(数字 单位元)"
	coalFee	numeric(18,4)	,--	"排污费(数字 单位元)"
	labourUunionExpenditure	numeric(18,4)	,--	"工会经费(数字 单位元)"
	employmentSecurityForDisabledPersons	numeric(18,4)	,--	"残疾人就业保障金(数字 单位元)"
	other	numeric(18,4)	,--	"其他(数字 单位元)"
	source	varchar(100)	,--	"公司名称（请严格参照公司名称对照表）"
	time datetime
PRIMARY KEY CLUSTERED 			
(			
	[id] ASC		
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]			
) ON [PRIMARY]			


--年初计划
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financial_tax_year_plan')
DROP TABLE financial_tax_year_plan
CREATE TABLE [dbo].[financial_tax_year_plan](	
	[id] 	[int] IDENTITY(1,1) NOT NULL	,		
	companyName	varchar(100)	,--	"公司名称（请严格参照公司名称对照表）"
	year	int	,--	"年份月份（只填写数字例如2017,2016）"
	month	int	,--	"月份（只填写数字例如1,2,3）"
	totalTaxBudget	numeric(18,4)	,--	"税金预算合计(数字 单位元)"
	saleIncome	numeric(18,4)	,--	销售收入计划数(数字 单位元)
	VTA	numeric(18,4)	,--	"增值税计划数(数字 单位元)"
	totalProfit	numeric(18,4)	,--	"利润总额计划数(数字 单位元)"
	corporateIncomeTax	numeric(18,4)	,--	企业所得税计划数(数字 单位元)
PRIMARY KEY CLUSTERED 			
(			
	[id] ASC		
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]			
) ON [PRIMARY]	