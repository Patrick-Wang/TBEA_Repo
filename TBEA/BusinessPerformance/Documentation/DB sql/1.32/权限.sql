EXEC	addAuth	'130182',	96	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	97	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	102	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	98	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	99	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	100	,	'1,2,3,301,4,5,6,9,10,11,12'
EXEC	addAuth	'130182',	101	,	'1,2,3,301,4,5,6,9,10,11,12'


SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (103, '首付款抹账')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF


EXEC	addAuth	'130182',	103	,	''
EXEC	addAuth	'155165',	103	,	''
