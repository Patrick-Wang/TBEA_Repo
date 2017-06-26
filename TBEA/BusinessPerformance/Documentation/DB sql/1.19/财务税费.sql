--˰��˰��
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financial_tax')
DROP TABLE financial_tax
CREATE TABLE [dbo].[financial_tax](	
	[id] 	[int] IDENTITY(1,1) NOT NULL	,		
	companyName	varchar(100)	,--	"��˾���ƣ����ϸ���չ�˾���ƶ��ձ�"
	year	int	,--	"��ݣ�ֻ��д��������2017,2016��"
	month	int	,--	"�·ݣ�ֻ��д��������1,2,3��"
	VAT	numeric(18,4)	,--	"��ֵ˰(���� ��λԪ)"
	xssr	numeric(18,4)	,--	"��������(���� ��λԪ)"
	salesIncome	numeric(18,4)	,--	"��ҵ����˰(���� ��λԪ)"
	totalProfit	numeric(18,4)	,--	�����ܶ�(���� ��λԪ)
	tariff	numeric(18,4)	,--	"��˰(���� ��λԪ)"
	businessTax	numeric(18,4)	,--	"Ӫҵ˰(���� ��λԪ)"
	urbanConstructionTax	numeric(18,4)	,--	"�ǽ�˰(���� ��λԪ)"
	educationSurtax	numeric(18,4)	,--	"�����Ѹ���(���� ��λԪ)"
	localEducationSurtax	numeric(18,4)	,--	"�ط���������(���� ��λԪ)"
	stampTax	numeric(18,4)	,--	"ӡ��˰(���� ��λԪ)"
	personalIncomeTax 	numeric(18,4)	,--	"��������˰(���� ��λԪ)"
	buildingTax	numeric(18,4)	,--	"����˰(���� ��λԪ)"
	landUseTax	numeric(18,4)	,--	"����ʹ��˰(���� ��λԪ)"
	landVTA	numeric(18,4)	,--	"������ֵ˰(���� ��λԪ)"
	landOccupationTax	numeric(18,4)	,--	"����ռ��˰(���� ��λԪ)"
	vehicleAndVesselTax	numeric(18,4)	,--	"����ʹ��˰(���� ��λԪ)"
	resourceTax	numeric(18,4)	,--	"��Դ˰(���� ��λԪ)"
	deedTax	numeric(18,4)	,--	"��˰(���� ��λԪ)"
	floodPreventionCharges	numeric(18,4)	,--	"�����(���� ��λԪ)"
	waterConservancyFund	numeric(18,4)	,--	"ˮ������(���� ��λԪ)"
	mineralResourceCompensation	numeric(18,4)	,--	"�����Դ������(���� ��λԪ)"
	mtzydfjjfzf	numeric(18,4)	,--	"ú̿��Դ�ط����÷�չ��(���� ��λԪ)"
	coalFee	numeric(18,4)	,--	"���۷�(���� ��λԪ)"
	labourUunionExpenditure	numeric(18,4)	,--	"���ᾭ��(���� ��λԪ)"
	employmentSecurityForDisabledPersons	numeric(18,4)	,--	"�м��˾�ҵ���Ͻ�(���� ��λԪ)"
	other	numeric(18,4)	,--	"����(���� ��λԪ)"
	source	varchar(100)	,--	"��˾���ƣ����ϸ���չ�˾���ƶ��ձ�"
	time datetime
PRIMARY KEY CLUSTERED 			
(			
	[id] ASC		
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]			
) ON [PRIMARY]			


--����ƻ�
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'financial_tax_year_plan')
DROP TABLE financial_tax_year_plan
CREATE TABLE [dbo].[financial_tax_year_plan](	
	[id] 	[int] IDENTITY(1,1) NOT NULL	,		
	companyName	varchar(100)	,--	"��˾���ƣ����ϸ���չ�˾���ƶ��ձ�"
	year	int	,--	"����·ݣ�ֻ��д��������2017,2016��"
	month	int	,--	"�·ݣ�ֻ��д��������1,2,3��"
	totalTaxBudget	numeric(18,4)	,--	"˰��Ԥ��ϼ�(���� ��λԪ)"
	saleIncome	numeric(18,4)	,--	��������ƻ���(���� ��λԪ)
	VTA	numeric(18,4)	,--	"��ֵ˰�ƻ���(���� ��λԪ)"
	totalProfit	numeric(18,4)	,--	"�����ܶ�ƻ���(���� ��λԪ)"
	corporateIncomeTax	numeric(18,4)	,--	��ҵ����˰�ƻ���(���� ��λԪ)
PRIMARY KEY CLUSTERED 			
(			
	[id] ASC		
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]			
) ON [PRIMARY]	