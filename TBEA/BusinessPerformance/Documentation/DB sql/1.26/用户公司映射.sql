
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'user_companys')
DROP TABLE user_companys
CREATE TABLE [dbo].[user_companys](
	[id] [int] IDENTITY(1,1) NOT NULL,
	userId int, --用户
	companyId int --公司
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

insert into user_companys (userId, companyId) values
(9,	1	),
(	10	,	101	),
(	11	,	102	),
(	12	,	103	),
(	13	,	104	),
(	14	,	105	),
(	15	,	106	),
(	16	,	107	),
(	17	,	108	),
(	18	,	109	),
(	19	,	110	),
(	20	,	111	),
(	21	,	112	),
(	22	,	113	),
(	23	,	114	),
(	24	,	115	),
(	25	,	2	),
(	26	,	201	),
(	27	,	202	),
(	28	,	203	),
(	29	,	204	),
(	30	,	205	),
(	31	,	206	),
(	32	,	207	),
(	33	,	3	),
(	34	,	301	),
(	35	,	302	),
(	36	,	303	),
(	37	,	304	),
(	38	,	305	),
(	39	,	306	),
(	41	,	308	),
(	42	,	309	),
(	43	,	4	),
(	44	,	401	),
(	45	,	402	),
(	46	,	403	),
(	47	,	404	),
(	48	,	405	),
(	49	,	406	),
(	50	,	5	),
(	51	,	501	),
(	52	,	502	),
(	53	,	503	),
(	54	,	504	),
(	55	,	505	),
(	56	,	506	),
(	57	,	507	),
(	58	,	508	),
(	59	,	509	),
(	60	,	510	),
(	61	,	6	),
(	62	,	601	),
(	63	,	602	),
(	64	,	603	),
(	65	,	604	)
