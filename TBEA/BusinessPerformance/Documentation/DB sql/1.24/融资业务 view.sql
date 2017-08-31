create view financing_bank_RMB_credit_v as select 
	case bankName 
		when '国开行' then '国开行'
		when '进出口银行' then '进出口银行'
		when '工商银行' then '工商银行'
		when '农业银行' then '农业银行'
		when '中国银行' then '中国银行'
		when '建设银行' then '建设银行'
		when '交通银行' then '交通银行'
		when '浦发银行' then '浦发银行'
		when '兴业银行' then '兴业银行'
		when '招商银行' then '招商银行'
		else '其它中小银行' end 	bankNo,
	*
from financing_bank_RMB_credit;
