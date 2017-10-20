    SELECT gl.name ledger_name --������
      ,gb.ledger_id --������id
      ,gb.currency_code --����
      ,gb.period_name --�ڼ�
      ,gb.last_update_date --������ʱ��
      ,gcc.segment1 company_code --��˾��
      ,gcc.segment2 depart_code --���Ŷ�
      ,gcc.segment3 account_code --��Ŀ�� 
      ,gcc.concatenated_segments account_segments --��Ŀ���
      ,gl_flexfields_pkg.Get_Concat_Description(gl.CHART_OF_ACCOUNTS_ID,gcc.code_combination_id) account_segments_DESC --��Ŀ���˵��
      ,(
       --����� 
   SELECT nvl(gb.begin_balance_dr_beq,0) -
          nvl(gb.begin_balance_cr_beq
             ,0)
     FROM gl_balances gb1
    WHERE gb1.actual_flag = 'A'
      AND gb1.template_id IS NULL
      AND gb1.period_name = '2017-01'
      AND gb1.code_combination_id = gb.code_combination_id
      AND gb1.currency_code = gb.currency_code
      )
      ,gb.begin_balance_dr --�ڳ�ԭ�� �跽
      ,gb.begin_balance_dr_beq --�ڳ����� ���� 
      ,gb.begin_balance_cr --�ڳ�ԭ�� ����
      ,gb.begin_balance_cr_beq --�ڳ����� ����
      ,nvl(gb.begin_balance_dr
          ,0) - nvl(gb.begin_balance_cr
                   ,0) begin_balance --�ڳ�ԭ�� ���
      ,(nvl(gb.begin_balance_dr_beq
           ,0) - nvl(gb.begin_balance_cr
                     ,0)) begin_balance_beq --�ڳ����� ��� 
      ,gb.period_net_dr --���ڷ���ԭ�� �跽
      ,gb.period_net_dr_beq --���ڷ������� ����
      ,gb.period_net_cr --���ڷ���ԭ�� ����
      ,gb.period_net_cr_beq --���ڷ������� ���� 
       
      ,(nvl(gb.begin_balance_dr
           ,0) - nvl(gb.begin_balance_cr
                     ,0) + nvl(gb.period_net_dr
                               ,0) -
       nvl(gb.period_net_cr
           ,0)) end_balance --�ڳ�ԭ�� ���
      ,(nvl(gb.begin_balance_dr_beq
           ,0) - nvl(gb.begin_balance_cr_beq
                     ,0) + nvl(gb.period_net_dr_beq
                               ,0) -
       nvl(gb.period_net_cr_beq
           ,0)) end_balance_beq --�ڳ����� ��� 

  FROM gl_balances              gb
      ,gl_ledgers               gl
      ,gl_code_combinations_kfv gcc
 WHERE gb.code_combination_id = gcc.code_combination_id
   AND gb.actual_flag = 
   
   
   
   --������
SELECT gl.name ledger_name
      ,gl.ledger_id
  FROM gl_ledgers gl;

--����
SELECT fc.currency
      ,fc.description
  FROM fnd_currencies fc
 WHERE fc.currency_flag = 'Y'
   AND fc.enabled_flag = 'Y';

--�ڼ�  
SELECT gps.period_name
  FROM gl_period_statuses gps
 WHERE gps.application_id = 101
   AND gps.adjustment_period_flag = 'Y'
   AND gps.ledger_id =;

--��Ŀ
SELECT flvv.flex_value
      ,flvv.description
  FROM fnd_flex_value_sets flvs
      ,fnd_flex_values_vl  flvv
 WHERE flvs.flex_value_set_id = flvv.flex_value_set_id
   AND flvs.flex_value_set_name = 'TBEA_COA_AC'
 ORDER BY flvs.flex_value;