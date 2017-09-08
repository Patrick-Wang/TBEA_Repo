insert into system_extend_auth (account_id, company_id, auth_type) values
((select id from jygk_account where name='892603'),	3	, 	 85),
((select id from jygk_account where name='902991'),	5	, 	 85),
((select id from jygk_account where name='256118'),	8	, 	 85),
((select id from jygk_account where name='282208'),	11	, 	 85),
((select id from jygk_account where name='838582'),	9	, 	 85),
((select id from jygk_account where name='300022'),	9	, 	 85),
((select id from jygk_account where name='899628'),	10	, 	 85),
((select id from jygk_account where name='095961'),	7	, 	 85),
((select id from jygk_account where name='928006'),	1	, 	 85),
((select id from jygk_account where name='601858'),	4	, 	 85),
((select id from jygk_account where name='232321'),	6	, 	 85),
((select id from jygk_account where name='008382'),	2	, 	 85),
((select id from jygk_account where name='565839'),	2	, 	 85),
((select id from jygk_account where name='818099'),	2	, 	 85),
((select id from jygk_account where name='235021'),	301	, 	 85),
((select id from jygk_account where name='636988'),	2000	, 	 85),
((select id from jygk_account where name='印能融资'),	115	, 	 85),
((select id from jygk_account where name='上开融资'),	106	, 	 85),

((select id from jygk_account where name='068903'),	0	, 	 85),
((select id from jygk_account where name='892603'),	3	, 	 84),
((select id from jygk_account where name='902991'),	5	, 	 84),
((select id from jygk_account where name='256118'),	8	, 	 84),
((select id from jygk_account where name='282208'),	11	, 	 84),
((select id from jygk_account where name='838582'),	9	, 	 84),
((select id from jygk_account where name='300022'),	9	, 	 84),
((select id from jygk_account where name='899628'),	10	, 	 84),
((select id from jygk_account where name='095961'),	7	, 	 84),
((select id from jygk_account where name='601858'),	4	, 	 84),
((select id from jygk_account where name='232321'),	6	, 	 84),
((select id from jygk_account where name='008382'),	2	, 	 84),
((select id from jygk_account where name='565839'),	2	, 	 84),
((select id from jygk_account where name='818099'),	2	, 	 84),
((select id from jygk_account where name='235021'),	301	, 	 84),
((select id from jygk_account where name='636988'),	2000	, 	 84),
((select id from jygk_account where name='印能融资'),	115	, 	 84),
((select id from jygk_account where name='上开融资'),	106	, 	 84),

EXEC	addAuth
		@account = '928006',
		@auth = 84,
		@comps = '1,115,106';

EXEC	addAuth
		@account = '688020',
		@auth = 84,
		@comps = '3,5,8,11,9,10,7,1,4,6,2,301,2000,0,115,106';
                                          
EXEC	addAuth
		@account = '068903',
		@auth = 84,
		@comps = '3,5,8,11,9,10,7,1,4,6,2,301,2000,0,115,106';		

EXEC	addAuth
		@account = '102589',
		@auth = 84,
		@comps = '3,5,8,11,9,10,7,1,4,6,2,301,2000,0,115,106';			

EXEC	addAuth
		@account = '363560',
		@auth = 84,
		@comps = '3,5,8,11,9,10,7,1,4,6,2,301,2000,0,115,106';	

EXEC	addAuth
		@account = '130182',
		@auth = 84,
		@comps = '3,5,8,11,9,10,7,1,4,6,2,301,2000,0,115,106';	

EXEC	addAuth
		@account = '155165',
		@auth = 84,
		@comps = '3,5,8,11,9,10,7,1,4,6,2,301,2000,0,115,106';

