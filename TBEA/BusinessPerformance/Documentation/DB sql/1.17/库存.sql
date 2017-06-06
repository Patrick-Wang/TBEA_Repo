--ʵ���Ʒ̨������
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'storage_swccptz')
DROP TABLE storage_swccptz
CREATE TABLE [dbo].[storage_swccptz](
	[id] 	[int] IDENTITY(1,1) NOT NULL	,
	status	varchar(100)	,	--	״̬	
	company_name	varchar(300)	,	--	��Ŀ��˾	
	contract_no	varchar(300)	,	--	��ͬ���	
	customer_name	varchar(300)	,	--	�ͻ���λ����	
	deliver_date	date	,	--	��ͬԼ��������	
	contract_amount	numeric(18, 4)	,	--	��ͬ���	
	level	varchar(300)	,	--	����ͺż���ѹ�ȼ�/��Ʒ����	
	stock_amount	numeric(18, 4)	,	--	�������	
	stock_money	numeric(18, 4)	,	--	�����	
	complete_date	date	,	--	�깤�������	
	stock_contract_date	numeric(18, 4)	,	--	��沿�ֺ�ͬ���	
	amount_of_paid	numeric(18, 4)	,	--	��沿���Ѹ�����	���
	ratio_of_paid	numeric(18, 4)	,	--		ռ����ͬ������
	plan_of_send_date	date	,	--	Ԥ�Ʒ���ʱ��	
	reason_of_nondeliver	varchar(300)	,	--	δ����ԭ��	
	risk	varchar(300)	,	--	��������	
	added_month	date	,	--	��������·�	
	is_overstock	varchar(10)	,	--	�Ƿ��γɻ�ѹ	
	person_liable	varchar(100),		--	������	
	source varchar(100),
	time datetime
PRIMARY KEY CLUSTERED 						
(						
	[id] ASC					
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]						
) ON [PRIMARY]						

--ԭ������ϸ��
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'storage_yclmx')
DROP TABLE storage_yclmx
CREATE TABLE [dbo].[storage_yclmx](
	[id] 	[int] IDENTITY(1,1) NOT NULL	,	
	company_name	varchar(300)	,--	��Ŀ��˾	
	material_name	varchar(300)	,--	��������	
	model	varchar(300)	,--	����ͺ�	
	provider	varchar(300)	,--	��Ӧ��	
	measurement_unit	varchar(300)	,--	������λ	
	amount	numeric(18, 4)	,--	����	
	unit_price	numeric(18, 4)	,--	����	
	amount_of_money	numeric(18, 4)	,--	����Ԫ��	
	storage_date	date	,--	"�������(��/��/��)"	
	stock_duration	numeric(18, 2)	,--	���ʱ�����£�	
	cause	varchar(300)	,--	�γ�ԭ��	
	treatment	varchar(300)	,--	���鴦�ô�ʩ	
	risk_assessment	varchar(300)	,--	����Ԥ������	
	added_month	date	,--	"�����·�(X��X��)"	
	is_overstock	varchar(10)	,--	�Ƿ��γɻ�ѹ	
	person_liable	varchar(100)	,--	������	
	comletion_date	date	,--	"���ʱ��(��/��/��)"	
	amount_of_this_month	numeric(18, 4)	,--	����������棨����ѹ���ʣ�	����
	money_of_this_month	numeric(18, 4)	,--		����Ԫ��
	action_of_this_month	varchar(300)	,--		���ô�ʩ
	source varchar(100),
	time datetime
PRIMARY KEY CLUSTERED 						
(						
	[id] ASC					
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]						
) ON [PRIMARY]						

--���Ʒͳ�Ʊ�
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'storage_bcpmx')
DROP TABLE storage_bcpmx
CREATE TABLE [dbo].[storage_bcpmx](
	[id]	[int] IDENTITY(1,1) NOT NULL	,	
	company_name	varchar(300)	,--	��Ŀ��λ	
	type_name	varchar(300)	,--	��������	
	model	varchar(300)	,--	����ͺ�	
	amount	numeric(18, 4)	,--	����	
	measurement_unit	varchar(300)	,--	������λ	
	storage_date	date	,--	"�γ����ڣ���/��/�գ�"	
	stock_duration	numeric(18, 2)	,--	���ʱ�����£�	
	cause	varchar(300)	,--	�γ�ԭ��	
	treatment	varchar(300)	,--	������	
	risk_assessment	varchar(300)	,--	��������	
	added_month	date	,--	"�����·ݣ�X��X�£�"	
	is_overstock	varchar(10)	,--	�Ƿ��γɻ�ѹ	
	person_liable	varchar(100)	,--	������	
	comletion_date	date	,--	"���ʱ�䣨��/��/�գ�"	
	amount_of_this_month	numeric(18, 4)	,--	�����������������ѹ���ʣ�	����
	money_of_this_month	numeric(18, 4)	,--		���(��Ԫ)
	action_of_this_month	varchar(300)	,--		���ô�ʩ
	source varchar(100),
	time datetime
PRIMARY KEY CLUSTERED 						
(						
	[id] ASC					
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]						
) ON [PRIMARY]	