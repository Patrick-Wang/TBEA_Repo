SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (93, '回款计划录入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (94, '回款计划查看')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF

delete from system_extend_auth_t where authId in (89, 90, 91, 92)

EXEC	addAuth	'沈变公司',	89	,	'1'
EXEC	addAuth	'沈变国际贸易成套公司',	89	,	'101'
EXEC	addAuth	'沈变中特分公司',	89	,	'102'
EXEC	addAuth	'沈变康嘉互感器',	89	,	'103'
EXEC	addAuth	'和新套管',	89	,	'104'
EXEC	addAuth	'沈变电力自动化公司',	89	,	'105'
EXEC	addAuth	'上开公司',	89	,	'106'
EXEC	addAuth	'沈变修试中心',	89	,	'107'
EXEC	addAuth	'沈变现代物流公司',	89	,	'108'
EXEC	addAuth	'特变电工沈阳电力勘测设计有限公司',	89	,	'109'
EXEC	addAuth	'新利钢公司',	89	,	'110'
EXEC	addAuth	'沈变中型公司',	89	,	'111'
EXEC	addAuth	'沈变物业公司',	89	,	'112'
EXEC	addAuth	'开关研究所',	89	,	'113'
EXEC	addAuth	'沈变新能源',	89	,	'114'
EXEC	addAuth	'特变电工印度能源有限公司',	89	,	'115'
EXEC	addAuth	'衡变公司',	89	,	'2'
EXEC	addAuth	'衡变电气分公司',	89	,	'201'
EXEC	addAuth	'湖南国际物流公司',	89	,	'202'
EXEC	addAuth	'湖南工程公司',	89	,	'203'
EXEC	addAuth	'衡变众业公司',	89	,	'204'
EXEC	addAuth	'湖南智能电气公司',	89	,	'205'
EXEC	addAuth	'南京智能电气公司',	89	,	'206'
EXEC	addAuth	'湖南园林公司',	89	,	'207'
EXEC	addAuth	'新变厂',	89	,	'3'
EXEC	addAuth	'天变公司',	89	,	'301'
EXEC	addAuth	'新变中特公司',	89	,	'302'
EXEC	addAuth	'新变箱变公司',	89	,	'303'
EXEC	addAuth	'新变国际成套工程公司',	89	,	'304'
EXEC	addAuth	'新变国内工程检修公司',	89	,	'305'
EXEC	addAuth	'新疆新特国际物流贸易公司',	89	,	'306'
EXEC	addAuth	'国际事业部',	89	,	'308'
EXEC	addAuth	'津疆物流',	89	,	'309'
EXEC	addAuth	'鲁缆公司',	91	,	'4'
EXEC	addAuth	'通用电线项目公司',	91	,	'401'
EXEC	addAuth	'节能导线项目公司',	91	,	'402'
EXEC	addAuth	'橡套电缆项目公司',	91	,	'403'
EXEC	addAuth	'电缆经销事业部',	91	,	'404'
EXEC	addAuth	'电缆工程公司',	91	,	'405'
EXEC	addAuth	'山东电力工程公司',	91	,	'406'
EXEC	addAuth	'新缆厂',	91	,	'5'
EXEC	addAuth	'特缆项目公司',	91	,	'501'
EXEC	addAuth	'通用项目公司',	91	,	'502'
EXEC	addAuth	'电材公司',	91	,	'503'
EXEC	addAuth	'国贸部',	91	,	'504'
EXEC	addAuth	'新缆国际工程公司',	91	,	'505'
EXEC	addAuth	'中特物流公司',	91	,	'506'
EXEC	addAuth	'销售总公司',	91	,	'507'
EXEC	addAuth	'新疆分公司',	91	,	'508'
EXEC	addAuth	'北京分公司',	91	,	'509'
EXEC	addAuth	'国内分公司',	91	,	'510'
EXEC	addAuth	'德缆公司',	91	,	'6'
EXEC	addAuth	'特缆公司',	91	,	'601'
EXEC	addAuth	'新特公司',	91	,	'602'
EXEC	addAuth	'德缆工程公司',	91	,	'603'
EXEC	addAuth	'西南电气工程公司',	91	,	'604'

EXEC	addAuth	'沈变公司',	90	,	'1'
EXEC	addAuth	'沈变国际贸易成套公司',	90	,	'101'
EXEC	addAuth	'沈变中特分公司',	90	,	'102'
EXEC	addAuth	'沈变康嘉互感器',	90	,	'103'
EXEC	addAuth	'和新套管',	90	,	'104'
EXEC	addAuth	'沈变电力自动化公司',	90	,	'105'
EXEC	addAuth	'上开公司',	90	,	'106'
EXEC	addAuth	'沈变修试中心',	90	,	'107'
EXEC	addAuth	'沈变现代物流公司',	90	,	'108'
EXEC	addAuth	'特变电工沈阳电力勘测设计有限公司',	90	,	'109'
EXEC	addAuth	'新利钢公司',	90	,	'110'
EXEC	addAuth	'沈变中型公司',	90	,	'111'
EXEC	addAuth	'沈变物业公司',	90	,	'112'
EXEC	addAuth	'开关研究所',	90	,	'113'
EXEC	addAuth	'沈变新能源',	90	,	'114'
EXEC	addAuth	'特变电工印度能源有限公司',	90	,	'115'
EXEC	addAuth	'衡变公司',	90	,	'2'
EXEC	addAuth	'衡变电气分公司',	90	,	'201'
EXEC	addAuth	'湖南国际物流公司',	90	,	'202'
EXEC	addAuth	'湖南工程公司',	90	,	'203'
EXEC	addAuth	'衡变众业公司',	90	,	'204'
EXEC	addAuth	'湖南智能电气公司',	90	,	'205'
EXEC	addAuth	'南京智能电气公司',	90	,	'206'
EXEC	addAuth	'湖南园林公司',	90	,	'207'
EXEC	addAuth	'新变厂',	90	,	'3'
EXEC	addAuth	'天变公司',	90	,	'301'
EXEC	addAuth	'新变中特公司',	90	,	'302'
EXEC	addAuth	'新变箱变公司',	90	,	'303'
EXEC	addAuth	'新变国际成套工程公司',	90	,	'304'
EXEC	addAuth	'新变国内工程检修公司',	90	,	'305'
EXEC	addAuth	'新疆新特国际物流贸易公司',	90	,	'306'
EXEC	addAuth	'国际事业部',	90	,	'308'
EXEC	addAuth	'津疆物流',	90	,	'309'
EXEC	addAuth	'鲁缆公司',	92	,	'4'
EXEC	addAuth	'通用电线项目公司',	92	,	'401'
EXEC	addAuth	'节能导线项目公司',	92	,	'402'
EXEC	addAuth	'橡套电缆项目公司',	92	,	'403'
EXEC	addAuth	'电缆经销事业部',	92	,	'404'
EXEC	addAuth	'电缆工程公司',	92	,	'405'
EXEC	addAuth	'山东电力工程公司',	92	,	'406'
EXEC	addAuth	'新缆厂',	92	,	'5'
EXEC	addAuth	'特缆项目公司',	92	,	'501'
EXEC	addAuth	'通用项目公司',	92	,	'502'
EXEC	addAuth	'电材公司',	92	,	'503'
EXEC	addAuth	'国贸部',	92	,	'504'
EXEC	addAuth	'新缆国际工程公司',	92	,	'505'
EXEC	addAuth	'中特物流公司',	92	,	'506'
EXEC	addAuth	'销售总公司',	92	,	'507'
EXEC	addAuth	'新疆分公司',	92	,	'508'
EXEC	addAuth	'北京分公司',	92	,	'509'
EXEC	addAuth	'国内分公司',	92	,	'510'
EXEC	addAuth	'德缆公司',	92	,	'6'
EXEC	addAuth	'特缆公司',	92	,	'601'
EXEC	addAuth	'新特公司',	92	,	'602'
EXEC	addAuth	'德缆工程公司',	92	,	'603'
EXEC	addAuth	'西南电气工程公司',	92	,	'604'

EXEC	addAuth	'沈变公司'	,	90	,	'101,102,103,104,105,107,108,112,115,116'
EXEC	addAuth	'衡变公司'	,	90	,	'106,201,202,203,204,205,206,207'
EXEC	addAuth	'新变厂'	,	90	,	'301,302,303,304,305,306,310'
EXEC	addAuth	'鲁缆公司'	,	92	,	'404'
EXEC	addAuth	'新缆厂'	,	92	,	'501,502,503,511'
EXEC	addAuth	'德缆公司'	,	92	,	'601,602,603,604'

EXEC	addAuth '130182' , 90, '1,101,102,103,104,105,107,108,112,115,116,2,106,201,202,203,204,205,206,207,3,301,302,303,304,305,306,310'
EXEC	addAuth '130182' , 92, '4,404,5,501,502,503,511,6,601,602,603,604'

EXEC	addAuth '155165' , 90, '1,101,102,103,104,105,107,108,112,115,116,2,106,201,202,203,204,205,206,207,3,301,302,303,304,305,306,310'
EXEC	addAuth '155165' , 92, '4,404,5,501,502,503,511,6,601,602,603,604'

delete from system_extend_auth_t where authId in (89,90,91,92) and companyId in (select id from jygk_dwxx where parent_ID = 12345)



EXEC	addAuth	'沈变公司',	93	,	'1'
EXEC	addAuth	'沈变国际贸易成套公司',	93	,	'101'
EXEC	addAuth	'沈变中特分公司',	93	,	'102'
EXEC	addAuth	'沈变康嘉互感器',	93	,	'103'
EXEC	addAuth	'和新套管',	93	,	'104'
EXEC	addAuth	'沈变电力自动化公司',	93	,	'105'
EXEC	addAuth	'上开公司',	93	,	'106'
EXEC	addAuth	'沈变修试中心',	93	,	'107'
EXEC	addAuth	'沈变现代物流公司',	93	,	'108'
EXEC	addAuth	'特变电工沈阳电力勘测设计有限公司',	93	,	'109'
EXEC	addAuth	'新利钢公司',	93	,	'110'
EXEC	addAuth	'沈变中型公司',	93	,	'111'
EXEC	addAuth	'沈变物业公司',	93	,	'112'
EXEC	addAuth	'开关研究所',	93	,	'113'
EXEC	addAuth	'沈变新能源',	93	,	'114'
EXEC	addAuth	'特变电工印度能源有限公司',	93	,	'115'
EXEC	addAuth	'衡变公司',	93	,	'2'
EXEC	addAuth	'衡变电气分公司',	93	,	'201'
EXEC	addAuth	'湖南国际物流公司',	93	,	'202'
EXEC	addAuth	'湖南工程公司',	93	,	'203'
EXEC	addAuth	'衡变众业公司',	93	,	'204'
EXEC	addAuth	'湖南智能电气公司',	93	,	'205'
EXEC	addAuth	'南京智能电气公司',	93	,	'206'
EXEC	addAuth	'湖南园林公司',	93	,	'207'
EXEC	addAuth	'新变厂',	93	,	'3'
EXEC	addAuth	'天变公司',	93	,	'301'
EXEC	addAuth	'新变中特公司',	93	,	'302'
EXEC	addAuth	'新变箱变公司',	93	,	'303'
EXEC	addAuth	'新变国际成套工程公司',	93	,	'304'
EXEC	addAuth	'新变国内工程检修公司',	93	,	'305'
EXEC	addAuth	'新疆新特国际物流贸易公司',	93	,	'306'
EXEC	addAuth	'国际事业部',	93	,	'308'
EXEC	addAuth	'津疆物流',	93	,	'309'
EXEC	addAuth	'鲁缆公司',	93	,	'4'
EXEC	addAuth	'通用电线项目公司',	93	,	'401'
EXEC	addAuth	'节能导线项目公司',	93	,	'402'
EXEC	addAuth	'橡套电缆项目公司',	93	,	'403'
EXEC	addAuth	'电缆经销事业部',	93	,	'404'
EXEC	addAuth	'电缆工程公司',	93	,	'405'
EXEC	addAuth	'山东电力工程公司',	93	,	'406'
EXEC	addAuth	'新缆厂',	93	,	'5'
EXEC	addAuth	'特缆项目公司',	93	,	'501'
EXEC	addAuth	'通用项目公司',	93	,	'502'
EXEC	addAuth	'电材公司',	93	,	'503'
EXEC	addAuth	'国贸部',	93	,	'504'
EXEC	addAuth	'新缆国际工程公司',	93	,	'505'
EXEC	addAuth	'中特物流公司',	93	,	'506'
EXEC	addAuth	'销售总公司',	93	,	'507'
EXEC	addAuth	'新疆分公司',	93	,	'508'
EXEC	addAuth	'北京分公司',	93	,	'509'
EXEC	addAuth	'国内分公司',	93	,	'510'
EXEC	addAuth	'德缆公司',	93	,	'6'
EXEC	addAuth	'特缆公司',	93	,	'601'
EXEC	addAuth	'新特公司',	93	,	'602'
EXEC	addAuth	'德缆工程公司',	93	,	'603'
EXEC	addAuth	'西南电气工程公司',	93	,	'604'

EXEC	addAuth	'沈变公司',	94	,	'1'
EXEC	addAuth	'沈变国际贸易成套公司',	94	,	'101'
EXEC	addAuth	'沈变中特分公司',	94	,	'102'
EXEC	addAuth	'沈变康嘉互感器',	94	,	'103'
EXEC	addAuth	'和新套管',	94	,	'104'
EXEC	addAuth	'沈变电力自动化公司',	94	,	'105'
EXEC	addAuth	'上开公司',	94	,	'106'
EXEC	addAuth	'沈变修试中心',	94	,	'107'
EXEC	addAuth	'沈变现代物流公司',	94	,	'108'
EXEC	addAuth	'特变电工沈阳电力勘测设计有限公司',	94	,	'109'
EXEC	addAuth	'新利钢公司',	94	,	'110'
EXEC	addAuth	'沈变中型公司',	94	,	'111'
EXEC	addAuth	'沈变物业公司',	94	,	'112'
EXEC	addAuth	'开关研究所',	94	,	'113'
EXEC	addAuth	'沈变新能源',	94	,	'114'
EXEC	addAuth	'特变电工印度能源有限公司',	94	,	'115'
EXEC	addAuth	'衡变公司',	94	,	'2'
EXEC	addAuth	'衡变电气分公司',	94	,	'201'
EXEC	addAuth	'湖南国际物流公司',	94	,	'202'
EXEC	addAuth	'湖南工程公司',	94	,	'203'
EXEC	addAuth	'衡变众业公司',	94	,	'204'
EXEC	addAuth	'湖南智能电气公司',	94	,	'205'
EXEC	addAuth	'南京智能电气公司',	94	,	'206'
EXEC	addAuth	'湖南园林公司',	94	,	'207'
EXEC	addAuth	'新变厂',	94	,	'3'
EXEC	addAuth	'天变公司',	94	,	'301'
EXEC	addAuth	'新变中特公司',	94	,	'302'
EXEC	addAuth	'新变箱变公司',	94	,	'303'
EXEC	addAuth	'新变国际成套工程公司',	94	,	'304'
EXEC	addAuth	'新变国内工程检修公司',	94	,	'305'
EXEC	addAuth	'新疆新特国际物流贸易公司',	94	,	'306'
EXEC	addAuth	'国际事业部',	94	,	'308'
EXEC	addAuth	'津疆物流',	94	,	'309'
EXEC	addAuth	'鲁缆公司',	94	,	'4'
EXEC	addAuth	'通用电线项目公司',	94	,	'401'
EXEC	addAuth	'节能导线项目公司',	94	,	'402'
EXEC	addAuth	'橡套电缆项目公司',	94	,	'403'
EXEC	addAuth	'电缆经销事业部',	94	,	'404'
EXEC	addAuth	'电缆工程公司',	94	,	'405'
EXEC	addAuth	'山东电力工程公司',	94	,	'406'
EXEC	addAuth	'新缆厂',	94	,	'5'
EXEC	addAuth	'特缆项目公司',	94	,	'501'
EXEC	addAuth	'通用项目公司',	94	,	'502'
EXEC	addAuth	'电材公司',	94	,	'503'
EXEC	addAuth	'国贸部',	94	,	'504'
EXEC	addAuth	'新缆国际工程公司',	94	,	'505'
EXEC	addAuth	'中特物流公司',	94	,	'506'
EXEC	addAuth	'销售总公司',	94	,	'507'
EXEC	addAuth	'新疆分公司',	94	,	'508'
EXEC	addAuth	'北京分公司',	94	,	'509'
EXEC	addAuth	'国内分公司',	94	,	'510'
EXEC	addAuth	'德缆公司',	94	,	'6'
EXEC	addAuth	'特缆公司',	94	,	'601'
EXEC	addAuth	'新特公司',	94	,	'602'
EXEC	addAuth	'德缆工程公司',	94	,	'603'
EXEC	addAuth	'西南电气工程公司',	94	,	'604'

EXEC	addAuth	'沈变公司'	,	94	,	'101,102,103,104,105,107,108,112,115,116'
EXEC	addAuth	'衡变公司'	,	94	,	'106,201,202,203,204,205,206,207'
EXEC	addAuth	'新变厂'	,	94	,	'301,302,303,304,305,306,310'
EXEC	addAuth	'鲁缆公司'	,	94	,	'404'
EXEC	addAuth	'新缆厂'	,	94	,	'501,502,503,511'
EXEC	addAuth	'德缆公司'	,	94	,	'601,602,603,604'



EXEC	addAuth '130182' , 94, '1,101,102,103,104,105,107,108,112,115,116,2,106,201,202,203,204,205,206,207,3,301,302,303,304,305,306,310,4,404,5,501,502,503,511,6,601,602,603,604'
EXEC	addAuth '155165' , 94, '1,101,102,103,104,105,107,108,112,115,116,2,106,201,202,203,204,205,206,207,3,301,302,303,304,305,306,310,4,404,5,501,502,503,511,6,601,602,603,604'
delete from system_extend_auth_t where authId in (94, 93) and companyId in (select id from jygk_dwxx where parent_ID = 12345)




