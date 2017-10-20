SELECT  gl.name ledger_name, --������
    gb.ledger_id, --������id
    gb.currency_code, --����
    gb.period_name, --�ڼ�    
    gb.last_update_date, --������ʱ��    
    gcc.segment1 company_code, --��˾��    
    gcc.segment2 depart_code, --���Ŷ�    
    gcc.segment3 account_code, --��Ŀ��    
    gcc.concatenated_segments account_segments, --��Ŀ���    
    apps.gl_flexfields_pkg.Get_Concat_Description(gl.CHART_OF_ACCOUNTS_ID, gcc.code_combination_id) account_segments_DESC, --��Ŀ���˵��    
    (
     SELECT nvl(gb.begin_balance_dr_beq, 0) -
          nvl(gb.begin_balance_cr_beq, 0)
       FROM apps.gl_balances gb1
      WHERE gb1.actual_flag = 'A'
        AND gb1.template_id IS NULL
        AND gb1.period_name = '2017-01'
        AND gb1.code_combination_id = gb.code_combination_id
        AND gb1.currency_code = gb.currency_code)--������ ,
    gb.begin_balance_dr, --�ڳ�ԭ�� �跽    
    gb.begin_balance_dr_beq, --�ڳ����� �跽    
    gb.begin_balance_cr, --�ڳ�ԭ�� ����    
    gb.begin_balance_cr_beq, --�ڳ����� ����    
    nvl(gb.begin_balance_dr, 0) - nvl(gb.begin_balance_cr, 0) begin_balance, --�ڳ�ԭ�� ���    
    (nvl(gb.begin_balance_dr_beq, 0) - nvl(gb.begin_balance_cr, 0)) begin_balance_beq, --�ڳ����� ���     
    gb.period_net_dr, --���ڷ���ԭ�� �跽    
    gb.period_net_dr_beq, --���ڷ������� �跽    
    gb.period_net_cr, --���ڷ���ԭ�� ����    
    gb.period_net_cr_beq, --���ڷ������� ����     
    (nvl(gb.begin_balance_dr, 0) - nvl(gb.begin_balance_cr, 0) +
    nvl(gb.period_net_dr, 0) - nvl(gb.period_net_cr, 0)) end_balance, --��ĩԭ�� ���    
    (nvl(gb.begin_balance_dr_beq, 0) - nvl(gb.begin_balance_cr_beq, 0) +
    nvl(gb.period_net_dr_beq, 0) - nvl(gb.period_net_cr_beq, 0)) end_balance_beq --��ĩ���� ��� 
FROM apps.gl_balances        gb,
    apps.gl_ledgers         gl,
    apps.gl_code_combinations_kfv gcc
