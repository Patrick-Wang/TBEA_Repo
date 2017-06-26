--��Ŀ��ϸ
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'domestic_market_xmmx')
DROP TABLE domestic_market_xmmx
CREATE TABLE [dbo].[domestic_market_xmmx](
	[id] 	[int] IDENTITY(1,1) NOT NULL	,
	comp_name	varchar(300)	,--	��λ
	project_department	varchar(300)	,--	���´�����
	pro_no	varchar(300)	,--	��Ŀ���
	industry	varchar(300)	,--	������ҵ
	system	varchar(300)	,--	����ϵͳ
	pro_name	varchar(300)	,--	��Ŀ����
	owner_comp	varchar(300)	,--	ҵ����λ
	model	varchar(300)	,--	��Ʒ�ͺ�
	amount	numeric(18,4)	,--	����
	predict_bid_amount	numeric(18,4)	,--	Ԥ��Ͷ����
	predict_bidding_date	date	,--	Ԥ���б�ʱ��
	region	varchar(300)	,--	��Ŀ��������
	project_brief	varchar(1000)	,--	��Ŀ���
	pro_condition	varchar(1000)	,--	Ŀǰ�ƽ�������������ڼƻ�
	person_in_charge_info	varchar(300)	,--	����λ��Ŀ�����˼���ϵ��ʽ
	charge_leader	varchar(300)	,--	����λ�������Ŀ�������쵼
	other_comp	varchar(300)	,--	���ٸ���Ŀ�������ڲ���ҵ����
	bid_condition	varchar(1000)	,--	Ͷ�����
	jydw int,
	source varchar(100),
	time datetime
PRIMARY KEY CLUSTERED 						
(						
	[id] ASC					
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]						
) ON [PRIMARY]						

--Ͷ��ϸ��
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'domestic_market_tbmx')
DROP TABLE domestic_market_tbmx
CREATE TABLE [dbo].[domestic_market_tbmx](
	[id] 	[int] IDENTITY(1,1) NOT NULL	,	
	comp_name	varchar(300)	,--	��λ
	bid_no	varchar(300)	,--	Ͷ����
	pro_no	varchar(300)	,--	��Ŀ��Ϣ���
	auth_no	varchar(300)	,--	��Ȩ���
	project_department	varchar(300)	,--	���°����Ŀ��
	bid_month	int	,--	Ͷ���·�
	bid_date	date	,--	Ͷ������
	industry	varchar(300)	,--	������ҵ
	system	varchar(300)	,--	����ϵͳ
	region	varchar(300)	,--	��Ŀ��������
	pro_name	varchar(300)	,--	��Ŀ����
	owner_comp	varchar(300)	,--	ҵ����λ
	model	varchar(300)	,--	�ͺ�
	voltage_level	varchar(300)	,--	��ѹ�ȼ�
	bid_amount	numeric(18,4)	,--	Ͷ�����������֣�
	bid_capacity	numeric(18,4)	,--	Ͷ�����������֣�(kVA)
	bid_price	numeric(18,4)	,--	"Ͷ��۸����֣�"
	win_bid_compw	varchar(300)	,--	�б곧��
	win_bid_price	numeric(18,4)	,--	�б�۸����֣�
	cause	varchar(1000)	,--	�б��δ�б�ԭ�����
	finally_date	varchar(100)	,--	�����·�
	status	varchar(300)	,--	״̬
	is_need_summary	varchar(100)	,--	�Ƿ���Ͷ���ܽ�
	specific_bid_comp	varchar(300)	,--	����Ͷ�굥λ
	person_in_charge	varchar(100)	,--	������
	jydw int,
	source varchar(100),
	time datetime
PRIMARY KEY CLUSTERED 						
(						
	[id] ASC					
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]						
) ON [PRIMARY]						

--��ͬ��ϸ
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'domestic_market_htmx')
DROP TABLE domestic_market_htmx
CREATE TABLE [dbo].[domestic_market_htmx](
	[id]	[int] IDENTITY(1,1) NOT NULL	,	
	comp_name	varchar(300)	,--	��λ
	contract_no	varchar(300)	,--	��ͬ���
	project_department	varchar(300)	,--	���°����Ŀ��
	sign_month	date	,--	ǩԼ�·�
	industry	varchar(300)	,--	������ҵ
	system	varchar(300)	,--	����ϵͳ
	region	varchar(300)	,--	��Ŀ��������
	pro_name	varchar(300)	,--	��Ŀ����
	owner_comp	varchar(300)	,--	ҵ����λ
	model	varchar(300)	,--	��Ʒ�ͺ�
	voltage_level	varchar(300)	,--	��ѹ�ȼ�
	product_amount	numeric(18,4)	,--	��Ʒ����
	product_capacity	numeric(18,4)	,--	����
	contract_amount	numeric(18,4)	,--	��ͬ���
	payment	varchar(300)	,--	���ʽ
	person_in_charge	varchar(300)	,--	������
	specific_bid_comp	varchar(300)	,--	����Ͷ�굥λ
	deliver_date	date	,--	 ��Ʒ������
	jydw int,
	source varchar(100),
	time datetime
PRIMARY KEY CLUSTERED 						
(						
	[id] ASC					
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]						
) ON [PRIMARY]	