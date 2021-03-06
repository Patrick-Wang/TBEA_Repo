SELECT
    gcc.segment3 account_code, --科目段    
    gcc.concatenated_segments account_segments, --科目组合  
    (SELECT nvl(gb.begin_balance_dr_beq, 0) -
          nvl(gb.begin_balance_cr_beq, 0)
       FROM apps.gl_balances gb1
      WHERE gb1.actual_flag = 'A'
        AND gb1.template_id IS NULL
        AND gb1.period_name = '2017-01'
        AND gb1.code_combination_id = gb.code_combination_id
        AND gb1.currency_code = gb.currency_code),--年初余额 ,
     nvl(gb.begin_balance_dr, 0) - nvl(gb.begin_balance_cr, 0) begin_balance, --期初原币 余额 
     gb.period_net_dr, --本期发生原币 借方 
     gb.period_net_cr, --本期发生原币 贷方   
      (nvl(gb.begin_balance_dr, 0) - nvl(gb.begin_balance_cr, 0) +
    nvl(gb.period_net_dr, 0) - nvl(gb.period_net_cr, 0)) end_balance, --期末原币 余额  
     gb.begin_balance_dr +  gb.period_net_dr - (
     SELECT nvl(gb.begin_balance_dr_beq, 0)
       FROM apps.gl_balances gb1
      WHERE gb1.actual_flag = 'A'
        AND gb1.template_id IS NULL
        AND gb1.period_name = '2017-01'
        AND gb1.code_combination_id = gb.code_combination_id
        AND gb1.currency_code = gb.currency_code), --本年累计借方
    gb.begin_balance_cr +  gb.period_net_cr - (
     SELECT nvl(gb.begin_balance_cr_beq, 0)
       FROM apps.gl_balances gb1
      WHERE gb1.actual_flag = 'A'
        AND gb1.template_id IS NULL
        AND gb1.period_name = '2017-01'
        AND gb1.code_combination_id = gb.code_combination_id
        AND gb1.currency_code = gb.currency_code), --本年累计贷方
    apps.gl_flexfields_pkg.Get_Concat_Description(gl.CHART_OF_ACCOUNTS_ID, gcc.code_combination_id) account_segments_DESC --科目组合说明    
FROM apps.gl_balances        gb,
    apps.gl_ledgers         gl,
    apps.gl_code_combinations_kfv gcc
