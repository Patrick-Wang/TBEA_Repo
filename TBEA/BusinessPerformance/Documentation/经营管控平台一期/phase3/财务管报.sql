/***************************************************************************** 税种
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cwgb_sz')
DROP TABLE identifier_cwgb_sz
CREATE TABLE [dbo].[identifier_cwgb_sz](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET IDENTITY_INSERT [dbo].[identifier_cwgb_sz] ON
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (0,N'增值税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (1,N'消费税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (2,N'营业税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (3,N'城建税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (4,N'教育费附加(包括地方教育费附加)')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (5,N'企业所得税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (6,N'土地使用税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (7,N'房产税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (8,N'印花税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (9,N'个人所得税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (10,N'资源税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (11,N'关税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (12,N'其他税费')
SET IDENTITY_INSERT [dbo].[identifier_cwgb_sz] OFF      

/***************************************************************************** 产业
id name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cwgb_cy')
DROP TABLE identifier_cwgb_cy
CREATE TABLE [dbo].[identifier_cwgb_cy](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
SET IDENTITY_INSERT [dbo].[identifier_cwgb_cy] ON
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (0,N'变压器产业')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (1,N'线缆产业')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (2,N'天池能源')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (3,N'光伏产业')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (4,N'工程劳务')
SET IDENTITY_INSERT [dbo].[identifier_cwgb_cy] OFF

/***************************************************************************** 产品分类
id cy name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cwgb_cpfl')
DROP TABLE identifier_cwgb_cpfl
CREATE TABLE [dbo].[identifier_cwgb_cpfl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cy] [int] not NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
SET IDENTITY_INSERT [dbo].[identifier_cwgb_cpfl] ON
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (0,0, N'110kV及以下')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (1,0, N'220kV')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (2,0, N'330kV')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (3,0, N'500kV及以上')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (4,0, N'直流产品')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (5,0, N'电抗器')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (6,0, N'物流贸易')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (7,0, N'工程')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (8,0, N'其中：国内工程')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (9,0, N'其中：国际工程')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (10,0, N'其他类')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (11,1, N'导线')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (12,1, N'交联')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (13,1, N'其中:高压交联(66KV以上)')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (14,1, N'     中压交联(3.6-66KV)')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (15,1, N'     低压交联(1KV以下)')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (16,1, N'电力电缆')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (17,1, N'控制电缆')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (18,1, N'架空线')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (19,1, N'布电线')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (20,1, N'特种电缆')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (21,1, N'物流贸易')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (22,1, N'工程')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (23,1, N'其中：国内工程')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (24,1, N'其中：国际工程')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (25,1, N'其他')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (26,2, N'大块')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (27,2, N'中块')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (28,2, N'三八块')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (29,2, N'沫煤')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (30,2, N'混煤')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (31,2, N'物流贸易')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (32,2, N'工程')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (33,2, N'其中：国内工程')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (34,2, N'其中：国际工程')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (35,2, N'其他')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (36,3, N'硅片')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (37,3, N'光伏电站建设')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (38,3, N'风电工程建设')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (39,3, N'逆变器')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (40,3, N'电池组件制造及离网电站建设')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (41,3, N'设计调试服务')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (42,3, N'物流贸易')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (43,3, N'工程')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (44,3, N'其中：国内工程')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (45,3, N'其中：国际工程')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (46,3, N'其他')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (47,3, N'多晶硅')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (48,4, N'物流贸易')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (49,4, N'工程')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (50,4, N'其中：国内工程')
INSERT [dbo].[identifier_cwgb_cpfl] ([id], [cy], [name])VALUES (51,4, N'其中：国际工程')
SET IDENTITY_INSERT [dbo].[identifier_cwgb_cpfl] OFF
/***************************************************************************** 科目
id	name
*****************************************************************************/

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cwgb_km')
DROP TABLE identifier_cwgb_km
CREATE TABLE [dbo].[identifier_cwgb_km](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET IDENTITY_INSERT [dbo].[identifier_cwgb_km] ON
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (0,N'销售商品、提供劳务收到的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (1,N'收到的税费返还')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (2,N'收到的其他与经营活动有关的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (3,N'其中：罚款所收到的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (4,N'其中：政府补助所收到的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (5,N'其中：收到本单位向外投标退回所收到的投标保证金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (6,N'其中：收到外单位投标保证金所收到的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (7,N'其中：日常业务借支退回所收到的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (8,N'其中：银行存款利息所收到到的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (9,,N'其中：收到的其他与经营活动有关的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (10,N'现金流入小计')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (11,N'购买商品、接受劳务所支付的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (12,N'支付给职工以及为职工支付的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (13,N'支付的各项税费')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (14,N'支付的其他与经营活动有关的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (15,N'其中：本单位向外投标所支付的投标保证金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (16,N'其中：退付外单位投标保证金所支付的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (17,N'其中：代理咨询费所支付的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (18,N'其中：中标服务费所支付的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (19,N'其中：日常业务借支所支付的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (20,N'其中：银行相关业务手续费所支付的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (21,N'其中：支付的其他与经营活动有关的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (22,N'现金流出小计')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (23,N'经营活动产生的现金流量净额')
SET IDENTITY_INSERT [dbo].[identifier_cwgb_km] OFF
/***************************************************************************** 应交税费
	税种	应交数	已交数	未交数	累积应交	累计已交	累计未交	 期末数
yf	sz	yjs	yijs	wjs	ljyj	ljyij	ljwj	qms
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cwgb_yjsf')
DROP TABLE cwgb_yjsf
CREATE TABLE [dbo].[cwgb_yjsf](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[sz] [int] not NULL,
	[yjs] [numeric](18, 4) NULL,
	[yijs] [numeric](18, 4) NULL,
	[wjs] [numeric](18, 4) NULL,
	[ljyj] [numeric](18, 4) NULL,
	[ljyij] [numeric](18, 4) NULL,
	[ljwj] [numeric](18, 4) NULL,
	[qms] [numeric](18, 4) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/***************************************************************************** 年度期初数
	年		期初数
id	nf	sz	qcs
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cwgb_ndqcs')
DROP TABLE cwgb_ndqcs
CREATE TABLE [dbo].[cwgb_ndqcs](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[sz] [int] not NULL,
	[qcs] [numeric](18, 4) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/***************************************************************************** 产品大类毛利表
			产业	产品大类	累计收入	累计成本	期货配比情况	去年全年收入	去年全年成本
id	nf	yf	cy	cpdl	ljsr	ljcb	qhpbqk	qnqnsr	qnqncb
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cwgb_cpdlml')
DROP TABLE cwgb_cpdlml
CREATE TABLE [dbo].[cwgb_cpdlml](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[cy] [int] not NULL,
	[cpdl] [int] not NULL,
	[ljsr] [numeric](18, 4) NULL,
	[ljcb] [numeric](18, 4) NULL,
	[qhpbqk] [numeric](18, 4) NULL,
	[qnqnsr] [numeric](18, 4) NULL,
	[qnqncb] [numeric](18, 4) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
/***************************************************************************** 经营性现金流 计划值表
			科目	计划值	状态
id	nf	yf	km	jhz	zt

*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cwgb_jyxxjl_jh')
DROP TABLE cwgb_jyxxjl_jh
CREATE TABLE [dbo].[cwgb_jyxxjl_jh](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[km] [int] not NULL,
	[jhz] [numeric](18, 4) NULL,
	[zt] [int] NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
/***************************************************************************** 经营性现金流 实际值表
			科目	实际值	本年累计
id	nf	yf	km	sjz	ndlj
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cwgb_jyxxjl_sj')
DROP TABLE cwgb_jyxxjl_sj
CREATE TABLE [dbo].[cwgb_jyxxjl_sj](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[km] [int] not NULL,
	[sjz] [numeric](18, 4) NULL,
	[ndlj] [numeric](18, 4) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]