IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'storage_swccptz')
DROP TABLE storage_swccptz
CREATE TABLE [dbo].[storage_swccptz](
	[id] 	[int] IDENTITY(1,1) NOT NULL	,			
	statistics_year	int	,	--	ͳ�����	
	statistics_month	int	,	--	ͳ���·�	
	status	varchar(100)	,	--	״̬	
	company_name	varchar(300)	,	--	��Ŀ��˾	
	contract_no	varchar(300)	,	--	��ͬ���	
	customer_name	varchar(300)	,	--	�ͻ���λ����	
	deliver_date	varchar(300)	,	--	��ͬԼ��������	
	contract_amount	numeric(18, 4)	,	--	��ͬ���	
	level	varchar(300)	,	--	����ͺż���ѹ�ȼ�/��Ʒ����	
	stock_amount	numeric(18, 4)	,	--	�������	
	stock_money	numeric(18, 4)	,	--	�����	
	complete_date	date	,	--	�깤�������	
	stock_contract_date	numeric(18, 4)	,	--	��沿�ֺ�ͬ���	
	amount_of_paid	numeric(18, 4)	,	--	��沿���Ѹ�����	���
	ratio_of_paid	numeric(18, 4)	,	--		ռ����ͬ������
	plan_of_send_date	varchar(300)	,	--	Ԥ�Ʒ���ʱ��	
	reason_of_nondeliver	varchar(300)	,	--	δ����ԭ��	
	risk	varchar(300)	,	--	��������	
	added_month	varchar(300)	,	--	��������·�	
	is_overstock	varchar(10)	,	--	�Ƿ��γɻ�ѹ	
	person_liable	varchar(100),		--	������	
	source varchar(100),
	time datetime
PRIMARY KEY CLUSTERED 						
(						
	[id] ASC					
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]						
) ON [PRIMARY]						
