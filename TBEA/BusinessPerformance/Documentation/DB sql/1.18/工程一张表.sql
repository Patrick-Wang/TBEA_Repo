IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'project_comprehensive_table_pm_ex')
DROP TABLE project_comprehensive_table_pm_ex
CREATE TABLE [dbo].[project_comprehensive_table_pm_ex](	
	[id] 	[int] IDENTITY(1,1) NOT NULL	,		
	statistical_year	int	,--	����ͳ�����
	statistical_month	int	,--	����ͳ���·�
	pro_company	varchar(300)	,--	��Ŀ��˾/��ҵ��
	pro_no	varchar(100)	,--	��Ŀ���
	pro_name	varchar(500)	,--	��Ŀȫ��
	pro_type	varchar(50)	,--	��Ŀ���ͣ���硢��������硢���ԡ�������
	pro_category	varchar(10)	,--	�������BT��EPC��PC��C��BOO��BOT��PPP��
	pro_capacity	numeric(18, 4)	,--	��Ŀ������MW��
	is_sign_by_jointstock_company	varchar(10)	,--	�Ƿ����Թɷݹ�˾����ǩ����ͬ
	customer_name	varchar(500)	,--	�ͻ�ȫ�ƣ���ת�ú�������
	contract_amount	date	,--	��ͬ��������Լ����
	contract_amount_carry_down	numeric(18, 4)	,--	����ת������ĺ�ͬ��
	design_company_name	varchar(500)	,--	��Ƶ�λ����
	contract_sign_date	date	,--	ǩԼ���ڣ�2016****��
	contract_start_time	date	,--	��ͬԼ������ʱ��
	contract_completion_time	date	,--	��ͬԼ������ʱ��
	actual_start_time	date	,--	ʵ�ʿ���ʱ��
	payment_mode	varchar(500)	,--	��ͬ���ʽ
	prepayment	numeric(18, 4)	,--	Ԥ�������Ԫ��
	shareholding_ratio	numeric(18, 4)	,--	��Ŀ�ֹɱ���
	amount_received	numeric(18, 4)	,--	�������Ԫ��������Լ��������˰��
	bid_budget_cost	numeric(18, 4)	,--	Ͷ��Ԥ���ܳɱ�����Ԫ��
	target_cost	numeric(18, 4)	,--	Ŀ���ܳɱ�����Ԫ��
	dynamic_cost	numeric(18, 4)	,--	��̬�ܳɱ�����Ԫ��
	unit_dynamic_cost	numeric(18, 4)	,--	��λ��̬�ܳɱ���Ԫ/�ߣ�
	earned_value	numeric(18, 4)	,--	���깤�̼ƻ��ɱ�������Ŀ��ʼ��
	actual_cost	numeric(18, 4)	,--	���깤��ʵ�ʳɱ�������Ŀ��ʼ��
	finacial_record_gross_profit_rate	numeric(18, 4)	,--	���񱸰�ë���ʣ�%��
	actual_revenue_current_month	numeric(18, 4)	,--	����ʵ��ȷ�����루��Ԫ��
	make_out_the_invoice	numeric(18, 4)	,--	����ʵ�ʿ�Ʊ����Ԫ��
	gross_profit_current_month	numeric(18, 4)	,--	����ʵ��ë������Ԫ��
	recorded_inventory_current_mont	numeric(18, 4)	,--	����ʵ�����˴������Ԫ��
	fund_restream_current_month	numeric(18, 4)	,--	����ʵ�ʻؿ����Ԫ��
	accu_revenue	numeric(18, 4)	,--	�ۼ�ȷ�����루��Ԫ��
	accu_gross_profit	numeric(18, 4)	,--	�ۼ�����ë������Ԫ��
	accu_make_out_the_invoice	numeric(18, 4)	,--	�ۼƿ�Ʊ����Ԫ��
	accu_fund_restream	numeric(18, 4)	,--	�ۼƻؿ����Ԫ��
	accu_fund_restream_of_plan	numeric(18, 4)	,--	�ۼ�Ӧ�ؿ�����ݺ�ͬ��
	amount_receivable	numeric(18, 4)	,--	����Ӧ������Ԫ��
	accu_recorded_inventory	numeric(18, 4)	,--	�ۼ����˴������Ԫ��
	balance_of_inventory	numeric(18, 4)	,--	�������Ԫ��
	book_inventory	numeric(18, 4)	,--	���У�������
	balance_inventory	numeric(18, 4)	,--	���У�����Ŀ��˾�ʲ���ʽ�γɵĴ�����
	carry_down_inventory	numeric(18, 4)	,--	�ѽ�ת���
	dynamic_gross_profit_rate	numeric(18, 4)	,--	��̬ë���ʣ�%��
	overdue_payment	numeric(18, 4)	,--	���ڿ���
	revenue_next_month	numeric(18, 4)	,--	���¼ƻ�ȷ�����루��Ԫ��
	make_out_the_invoice_next_month	numeric(18, 4)	,--	���¼ƻ���Ʊ����Ԫ��
	recorded_inventory_next_month	numeric(18, 4)	,--	���¼ƻ����˴������Ԫ��
	received_payment_next_month	numeric(18, 4)	,--	���¼ƻ��ؿ����Ԫ��
	payment_next_month	numeric(18, 4)	,--	���¼ƻ�֧������Ԫ��
	cost_control_of_plan_amount	numeric(18, 4)	,--	�ɱ��ܿ�Ŀ�����Ԫ��
	accu_completion_of_plan_amount	numeric(18, 4)	,--	�ۼƼƻ����Ŀ�����Ԫ��
	accu_completion_of_actual_amount	numeric(18, 4)	,--	�ۼ�ʵ�����Ŀ�����Ԫ��
	completion_target_plan_this_month	numeric(18, 4)	,--	���¼ƻ����Ŀ�����Ԫ��
	completion_target_actual_this_month	numeric(18, 4)	,--	����ʵ�����Ŀ�����Ԫ��
	completion_target_plan_next_month	numeric(18, 4)	,--	���¼ƻ����Ŀ�����Ԫ��
	payment_this_month	numeric(18, 4)	,--	���¸����Ԫ��
	accu_payment_this_month	numeric(18, 4)	,--	�ۼƸ����Ԫ��
	commencement_date	date	,--	����ʩ�����ڣ�2016****��
	visual_schedule_percent	numeric(18, 4)	,--	������Ȱٷֱȣ�%��
	actual_installed_capacity	numeric(18, 4)	,--	ʵ��װ��������MW��
	estimated_completion_date	date	,--	��ĿԤ�ƿ������ڣ�2016****��
	actual_completion_date	date	,--	��Ŀʵ�ʿ������ڣ�2016****��
	actual_final_date	date	,--	��Ŀʵ�ʣ�Ԥ�ƣ��������ڣ�2016****��
	is_booster_station_build_byself	varchar(10)	,--	�Ƿ��Խ���ѹվ
	booster_station_voltage_level	numeric(18, 4)	,--	��ѹվ��ѹ�ȼ�(kV)
	booster_station_capacity	numeric(18, 4)	,--	��ѹվ����(kVA)
	is_outside_connections_build_byself	varchar(10)	,--	�Ƿ��Խ�����
	outside_connections_voltage_level	numeric(18, 4)	,--	���ߵ�ѹ�ȼ�(kV)
	outside_connections_length	numeric(18, 4)	,--	���߳���(kM)
	project_verification_owner	numeric(18, 4)	,--	����ǩ֤��ҵ������ȷ��
	project_verification_simulation 	numeric(18, 4)	,--	����ǩ֤��ҵ�������ύ
	accu_project_verification_owner	numeric(18, 4)	,--	ǩ֤�ۼƽ�ҵ������ȷ��
	accu_project_verification_simulation 	numeric(18, 4)	,--	ǩ֤�ۼƽ�ҵ�������ύ
	project_verification_change_owner	numeric(18, 4)	,--	���±����ҵ������ȷ��
	project_verification_change_simulation 	numeric(18, 4)	,--	���±����ҵ�������ύ
	accu_project_verification_change_owner	numeric(18, 4)	,--	����ۼƽ�ҵ������ȷ��
	accu_project_verification_change_simulation 	numeric(18, 4)	,--	����ۼƽ�ҵ�������ύ
	project_payment_for_approval	numeric(18, 4)	,--	���̽��㣨ҵ�񣩱�����
	project_payment_for_authorization	numeric(18, 4)	,--	���̽��㣨ҵ���󶨽��
	grid_onnection_all_stations	date	,--	ȫվ�������ڣ�2016****��
	grid_connectioned_capacity	numeric(18, 4)	,--	�Ѳ���������MW��
	generate_electricity_hours	numeric(18, 5)	,--	���������Ч����Сʱ����������������ݣ�
	average_load	numeric(18, 6)	,--	����ƽ������
	generating_capacity_plan	numeric(18, 7)	,--	���¼ƻ�����������kWh��
	generating_capacity_actual	numeric(18, 8)	,--	����ʵ�ʷ���������kWh��
	accu_ongrid_energy	numeric(18, 9)	,--	����ۼ�������������kWh��
	generating_capacity_plan_next_month	numeric(18, 10)	,--	���¼ƻ�����������kWh��
	time_of_warranty_contract	numeric(18, 11)	,--	��ͬԼ���ʱ��ڣ��£�
	time_of_warranty_standard	varchar(500)	,--	�ʱ��������׼
	time_of_warranty_start_time	date	,--	�ʱ�������ʱ��
	is_replace_by_guarantee	varchar(10)	,--	�Ƿ񱣺����ʱ���
	deal_with_in_warranty	varchar(500)	,--	�ʱ��ڼ�����ⷴ����ͨ����
	registered_principal_customer	numeric(18, 4)	,--	�ͻ�ע���ʱ���
	enterprise_nature_customer	varchar(100)	,--	�ͻ���ҵ���ʣ���������˽������
	guarantee_status_customer	varchar(500)	,--	�ͻ���������Ѻ����������
	is_advance_money	varchar(10)	,--	�Ƿ����ҵ��
	is_legitimate_of_project	varchar(10)	,--	��Ŀ�Ƿ�Ϸ��Ϲ�
	is_delay	varchar(10)	,--	�����Ƿ����󼰲����ķ���
	is_cover_EPC	varchar(10)	,--	�ؼ��豸�ɹ���ͬ�ʱ��ڼ�����ʱ���Ƿ񸲸�EPC��ͬ
	generating_capacity_promise	varchar(500)	,--	��������ŵ���
	is_show_guarantee_to_customer	varchar(10)	,--	�Ƿ���ҵ�����߹���������֤�ļ�
	project_risk	varchar(500)	,--	��Ŀ���ռ�����
	exchange_rate_risk	varchar(500)	,--	���ʷ���
	multicurrency_risk	varchar(500)	,--	����ַ���
	local_labour_services_risk	varchar(500)	,--	����������գ��񹤹��ʷ��ţ�
	equipment_logistics_material_risk	varchar(500)	,--	�豸�����������ʷ���
PRIMARY KEY CLUSTERED 			
(			
	[id] ASC		
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]			
) ON [PRIMARY]			
