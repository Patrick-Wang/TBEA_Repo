SELECT  gl.name ledger_name, --分类账
    gb.ledger_id, --分类账id
    gb.currency_code, --币种
    gb.period_name, --期间    
    gb.last_update_date, --最后更新时间    
    gcc.segment1 company_code, --公司段    
    gcc.segment2 depart_code, --部门段    
    gcc.segment3 account_code, --科目段    
    gcc.concatenated_segments account_segments, --科目组合    
    apps.gl_flexfields_pkg.Get_Concat_Description(gl.CHART_OF_ACCOUNTS_ID, gcc.code_combination_id) account_segments_DESC, --科目组合说明    
    (
     SELECT nvl(gb.begin_balance_dr_beq, 0) -
          nvl(gb.begin_balance_cr_beq, 0)
       FROM apps.gl_balances gb1
      WHERE gb1.actual_flag = 'A'
        AND gb1.template_id IS NULL
        AND gb1.period_name = '2017-01'
        AND gb1.code_combination_id = gb.code_combination_id
        AND gb1.currency_code = gb.currency_code)--年初余额 ,
    gb.begin_balance_dr, --期初原币 借方    
    gb.begin_balance_dr_beq, --期初本币 借方    
    gb.begin_balance_cr, --期初原币 贷方    
    gb.begin_balance_cr_beq, --期初本币 贷方    
    nvl(gb.begin_balance_dr, 0) - nvl(gb.begin_balance_cr, 0) begin_balance, --期初原币 余额    
    (nvl(gb.begin_balance_dr_beq, 0) - nvl(gb.begin_balance_cr, 0)) begin_balance_beq, --期初本币 余额     
    gb.period_net_dr, --本期发生原币 借方    
    gb.period_net_dr_beq, --本期发生本币 借方    
    gb.period_net_cr, --本期发生原币 贷方    
    gb.period_net_cr_beq, --本期发生本币 贷方     
    (nvl(gb.begin_balance_dr, 0) - nvl(gb.begin_balance_cr, 0) +
    nvl(gb.period_net_dr, 0) - nvl(gb.period_net_cr, 0)) end_balance, --期末原币 余额    
    (nvl(gb.begin_balance_dr_beq, 0) - nvl(gb.begin_balance_cr_beq, 0) +
    nvl(gb.period_net_dr_beq, 0) - nvl(gb.period_net_cr_beq, 0)) end_balance_beq --期末本币 余额 
FROM apps.gl_balances        gb,
    apps.gl_ledgers         gl,
    apps.gl_code_combinations_kfv gcc
