create or replace view CUX_ACCOUNTBALANCE_V as
SELECT
	rownum id,
    gcc.segment3 account_code, --��Ŀ��    
    gcc.concatenated_segments account_segments, --��Ŀ���  
    (SELECT nvl(gb.begin_balance_dr_beq, 0) -
          nvl(gb.begin_balance_cr_beq, 0)
       FROM apps.gl_balances gb1
      WHERE gb1.actual_flag = 'A'
        AND gb1.template_id IS NULL
        AND gb1.period_name = substr(gb.period_name, 1, 4) || '-01'
        AND gb1.code_combination_id = gb.code_combination_id
        AND gb1.currency_code = gb.currency_code) year_begin_balance,--������ ,
     nvl(gb.begin_balance_dr, 0) - nvl(gb.begin_balance_cr, 0) begin_balance, --�ڳ�ԭ�� ��� 
     gb.period_net_dr, --���ڷ���ԭ�� �跽 
     gb.period_net_cr, --���ڷ���ԭ�� ����   
      (nvl(gb.begin_balance_dr, 0) - nvl(gb.begin_balance_cr, 0) +
    nvl(gb.period_net_dr, 0) - nvl(gb.period_net_cr, 0)) end_balance, --��ĩԭ�� ���  
     gb.begin_balance_dr +  gb.period_net_dr - (
     SELECT nvl(gb.begin_balance_dr_beq, 0)
       FROM apps.gl_balances gb1
      WHERE gb1.actual_flag = 'A'
        AND gb1.template_id IS NULL
        AND gb1.period_name = substr(gb.period_name, 1, 4) || '-01'
        AND gb1.code_combination_id = gb.code_combination_id
        AND gb1.currency_code = gb.currency_code) year_acc_dr, --�����ۼƽ跽
    gb.begin_balance_cr +  gb.period_net_cr - (
     SELECT nvl(gb.begin_balance_cr_beq, 0)
       FROM apps.gl_balances gb1
      WHERE gb1.actual_flag = 'A'
        AND gb1.template_id IS NULL
        AND gb1.period_name = substr(gb.period_name, 1, 4) || '-01'
        AND gb1.code_combination_id = gb.code_combination_id
        AND gb1.currency_code = gb.currency_code)  year_acc_cr, --�����ۼƴ���
    apps.gl_flexfields_pkg.Get_Concat_Description(gl.CHART_OF_ACCOUNTS_ID, gcc.code_combination_id) account_segments_desc, --��Ŀ���˵��
	to_char(gb.ledger_id) ledger_id,
	gb.currency_code,
	gb.period_name,
	gcc.segment1 company_code --��˾��
FROM apps.gl_balances        gb,
    apps.gl_ledgers         gl,
    apps.gl_code_combinations_kfv gcc
 WHERE gb.code_combination_id = gcc.code_combination_id AND
	gb.ledger_id = gl.ledger_id
