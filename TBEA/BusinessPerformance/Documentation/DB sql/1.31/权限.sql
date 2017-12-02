EXEC	addAuth	'130182',	96	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	97	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	102	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	98	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	99	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	100	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	101	,	'1,2,3,301,4,5,6,9,10,11,12'


delete from system_extend_auth_t where authId in (82, 83) 

EXEC	addAuth	'130182',	83	,	'705,702,13'
EXEC	addAuth	'155165',	83	,	'705,702,13'
EXEC	addAuth	'天池能源',	83	,	'705,702'
EXEC	addAuth	'昌吉热电厂',	83	,	'705'
EXEC	addAuth	'新疆能源',	83	,	'702'
EXEC	addAuth	'众和公司',	83	,	'13'

EXEC	addAuth	'昌吉热电厂',	82	,	'705'
EXEC	addAuth	'新疆能源',	82	,	'702'
EXEC	addAuth	'众和公司',	82	,	'13'