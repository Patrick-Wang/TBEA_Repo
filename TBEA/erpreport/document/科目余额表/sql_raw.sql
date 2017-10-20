    SELECT gl.name ledger_name --分类账
      ,gb.ledger_id --分类账id
      ,gb.currency_code --币种
      ,gb.period_name --期间
      ,gb.last_update_date --最后更新时间
      ,gcc.segment1 company_code --公司段
      ,gcc.segment2 depart_code --部门段
      ,gcc.segment3 account_code --科目段 
      ,gcc.concatenated_segments account_segments --科目组合
      ,gl_flexfields_pkg.Get_Concat_Description(gl.CHART_OF_ACCOUNTS_ID,gcc.code_combination_id) account_segments_DESC --科目组合说明
      ,(
       --年初数 
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
      ,gb.begin_balance_dr --期初原币 借方
      ,gb.begin_balance_dr_beq --期初本币 贷方 
      ,gb.begin_balance_cr --期初原币 贷方
      ,gb.begin_balance_cr_beq --期初本币 贷方
      ,nvl(gb.begin_balance_dr
          ,0) - nvl(gb.begin_balance_cr
                   ,0) begin_balance --期初原币 余额
      ,(nvl(gb.begin_balance_dr_beq
           ,0) - nvl(gb.begin_balance_cr
                     ,0)) begin_balance_beq --期初本币 余额 
      ,gb.period_net_dr --本期发生原币 借方
      ,gb.period_net_dr_beq --本期发生本币 贷方
      ,gb.period_net_cr --本期发生原币 贷方
      ,gb.period_net_cr_beq --本期发生本币 贷方 
       
      ,(nvl(gb.begin_balance_dr
           ,0) - nvl(gb.begin_balance_cr
                     ,0) + nvl(gb.period_net_dr
                               ,0) -
       nvl(gb.period_net_cr
           ,0)) end_balance --期初原币 余额
      ,(nvl(gb.begin_balance_dr_beq
           ,0) - nvl(gb.begin_balance_cr_beq
                     ,0) + nvl(gb.period_net_dr_beq
                               ,0) -
       nvl(gb.period_net_cr_beq
           ,0)) end_balance_beq --期初本币 余额 

  FROM gl_balances              gb
      ,gl_ledgers               gl
      ,gl_code_combinations_kfv gcc
 WHERE gb.code_combination_id = gcc.code_combination_id
   AND gb.actual_flag = 
   
   
   
   --分类账
SELECT gl.name ledger_name
      ,gl.ledger_id
  FROM gl_ledgers gl;

--币种
SELECT fc.currency
      ,fc.description
  FROM fnd_currencies fc
 WHERE fc.currency_flag = 'Y'
   AND fc.enabled_flag = 'Y';

--期间  
SELECT gps.period_name
  FROM gl_period_statuses gps
 WHERE gps.application_id = 101
   AND gps.adjustment_period_flag = 'Y'
   AND gps.ledger_id =;

--科目
SELECT flvv.flex_value
      ,flvv.description
  FROM fnd_flex_value_sets flvs
      ,fnd_flex_values_vl  flvv
 WHERE flvs.flex_value_set_id = flvv.flex_value_set_id
   AND flvs.flex_value_set_name = 'TBEA_COA_AC'
 ORDER BY flvs.flex_value;