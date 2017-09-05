create view financing_bank_currency_credit_v as 
select 
	case
		when bankName = '国家开发银行' and currency='RMB' then '国开行'
		when bankName = '中国进出口银行' and currency='RMB'  then '进出口银行'
		when bankName = '中国工商银行' and currency='RMB'  then '工商银行'
		when bankName = '中国农业银行' and currency='RMB'  then '农业银行'
		when bankName = '中国银行' and currency='RMB'  then '中国银行'
		when bankName = '中国建设银行' and currency='RMB'  then '建设银行'
		when bankName = '交通银行' and currency='RMB'  then '交通银行'
		when bankName = '上海浦东发展银行' and currency='RMB'  then '浦发银行'
		when bankName = '兴业银行' and currency='RMB'  then '兴业银行'
		when bankName = '招商银行' and currency='RMB'  then '招商银行'
		when bankName in ('中国光大银行',
			'广发银行',
			'中信银行',
			'华夏银行',
			'中国民生银行',
			'平安银行',
			'青岛银行',
			'中国邮政储蓄银行',
			'乌鲁木齐银行',
			'北京银行') and currency='RMB'  then '其他中小银行'
		else '--' end  	bankNo,
	*
from financing_bank_currency_credit;

create view financing_bank_currency_credit_total_v as 
select 
case 					
	 when bankName = '国家开发银行' then '国家开发银行'
	 when bankName = '进出口银行' then '进出口银行'
	 when bankName = '工商银行' then '工商银行'
	 when bankName = '农业银行' then '农业银行'
	 when bankName = '中国银行' then '中国银行'
	 when bankName = '建设银行' then '建设银行'
	 when bankName = '交通银行' then '交通银行'
	 when bankName = '浦发银行' then '浦发银行'
	 when bankName = '兴业银行' then '兴业银行'
	 when bankName = '招商银行' then '招商银行'
	 when bankName = '光大银行' then '光大银行'
	 when bankName = '广发银行' then '广发银行'
	 when bankName = '中信银行' then '中信银行'
	 when bankName = '华夏银行' then '华夏银行'
	 when bankName = '花旗银行' then '花旗银行'
	 when bankName = '民生银行' then '民生银行'
	 when bankName = '平安银行' then '平安银行'
	 when bankName = '青岛银行' then '青岛银行'
	 when bankName = '邮储行' then '邮储行'
	 when bankName = '汇丰（中国）' then '汇丰（中国）'
	 when bankName = '澳新银行（中国）' then '澳新银行（中国）'
	 when bankName = '汇丰银行' then '汇丰银行'
	 when bankName = 'HDFC银行' then 'HDFC银行'
	 when bankName = '印度SBI银行' then '印度SBI银行'
	 when bankName = '印度ICBC银行' then '印度ICBC银行'
	 when bankName = 'Deuetsche德意志银行' then 'Deuetsche德意志银行'
	 when currency='RMB' then '其他商业银行'
	 else bankName end	bankNo,
	*
from financing_bank_currency_credit 

