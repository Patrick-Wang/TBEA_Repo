/***************************************************************************** 变压器可供履约订单产品类别
  id name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_sbdddcbjpcqk_byqcplb')
DROP TABLE identifier_sbdddcbjpcqk_byqcplb
CREATE TABLE [dbo].[identifier_sbdddcbjpcqk_byqcplb](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ON
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (0, N'交流变压器')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (1, N'        35KV及以下')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (2, N'        66-110KV')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (3, N'        220KV')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (4, N'        330KV')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (5, N'        500KV')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (6, N'        750kV')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (7, N'        1000kV')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (8, N'直流变压器')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (9, N'        ±400kv及以下')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (10, N'        ±500kv')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (11, N'        ±600kv')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (12, N'        ±800kv')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (13, N'        平波电抗器')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (14, N'电抗器')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (15, N'        330kV及以下')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (16, N'        500kV电')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (17, N'        750kV电')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (18, N'        1000kV电')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (19, N'干式变压器')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (20, N'        F级干变')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (21, N'        H级干变')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (22, N'        干式电抗器')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (23, N'配电变压器')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (24, N'        非晶合金')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (25, N'        卷铁芯')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (26, N'        叠铁芯')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (27, N'        其它')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (28, N'箱式变电站')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (29, N'        欧式变电站')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (30, N'        美式变电站')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (31, N'        华式变电站')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (32, N'        其它')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (33, N'特种变压器')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (34, N'        整流变')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (35, N'        牵引变')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (36, N'        隔爆变')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (37, N'        油田变')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (38, N'        其它')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (39, N'产业链延伸类')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (40, N'        配网自动化')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (41, N'        开关')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (42, N'        套管')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (43, N'        互感器')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (44, N'        维修备件')
INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] ([id], [name]) VALUES (45, N'        其它')

SET IDENTITY_INSERT [dbo].[identifier_sbdddcbjpcqk_byqcplb] OFF
/***************************************************************************** 线缆可供履约订单产品类别
  id name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_sbdddcbjpcqk_xlcplb')
DROP TABLE identifier_sbdddcbjpcqk_xlcplb
CREATE TABLE [dbo].[identifier_sbdddcbjpcqk_xlcplb](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
SET IDENTITY_INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ON
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (0, N'导线')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (1, N'布电线')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (2, N'架空线')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (3, N'控制电缆')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (4, N'高压交联电缆')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (5, N'中压交联电缆')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (6, N'低压交联电缆')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (7, N'电力电缆')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (8, N'电磁线')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (9, N'特种电缆')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (10, N'电缆附件')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (11, N'铜杆')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (12, N'铝杆')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (13, N'PVC料')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (14, N'工装轮')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (15, N'其他')
SET IDENTITY_INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] OFF
 /***************************************************************************** 变压器可供履约订单变化情况 
			生产单元	月产出能力		所有可供履约订单总量		当年可供履约订单总量		其中：当季度排产订单			其中：下季度排产订单			次年及以后可供履约订单		交货期待定		
																				
				产值	产量	产值	产量	产值	产量	产值	产量	产能发挥率	产值	产量	产能发挥率	产值	产量	产值	产量	
id	nf	yf	type scdy	yccnlcz	yccnlcl	skglyddzlcz	sykglyddzlcl	dnkglyddzlcz	dnkglyddzlcl	djdpcddcz	djdpcddcl	djdpcddcnfhl	xjdpcddcz	xjdpcddcl	xjdpcddcnfhl	cnjyhkglyddcz	cnjyhkglyddcl	jhqddcz	jhqddcl	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'sbdddcbjpcqk_byqkglydd')
DROP TABLE sbdddcbjpcqk_byqkglydd
CREATE TABLE [dbo].[sbdddcbjpcqk_byqkglydd](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[type] [int] not NULL,		--1 sclx-生产单元   2 sclx-生产类别
	[sclx] [varchar](50) not NULL,
	[yccnlcz] [numeric](18, 4) NULL,
	[yccnlcl] [numeric](18, 4) NULL,
	[sykglyddzlcz] [numeric](18, 4) NULL,
	[sykglyddzlcl] [numeric](18, 4) NULL,
	[dnkglyddzlcz] [numeric](18, 4) NULL,
	[dnkglyddzlcl] [numeric](18, 4) NULL,
	[nj1yddlcz] [numeric](18, 4) NULL,
	[nj1yddlcl] [numeric](18, 4) NULL,
	[nj2yddlcz] [numeric](18, 4) NULL,
	[nj2yddlcl] [numeric](18, 4) NULL,
	[nj3yddlcz] [numeric](18, 4) NULL,
	[nj3yddlcl] [numeric](18, 4) NULL,
	[nj4yddlcz] [numeric](18, 4) NULL,
	[nj4yddlcl] [numeric](18, 4) NULL,
	[nj5yddlcz] [numeric](18, 4) NULL,
	[nj5yddlcl] [numeric](18, 4) NULL,
	[nj6yddlcz] [numeric](18, 4) NULL,
	[nj6yddlcl] [numeric](18, 4) NULL,
	[nj6yyhkglyddlcz] [numeric](18, 4) NULL,
	[nj6yyhkglyddlcl] [numeric](18, 4) NULL,
	[cnjyhkglyddcz] [numeric](18, 4) NULL,
	[cnjyhkglyddcl] [numeric](18, 4) NULL,
	[jhqddcz] [numeric](18, 4) NULL,
	[jhqddcl] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


 /***************************************************************************** 线缆可供履约订单
			生产单元	产品类别	月产出能力（产值）	所有可供履约订单总量产值	当年可供履约订单总量产值	其中：当季度排产订单		其中：下季度排产订单		次年及以后可供履约订单排产值	交货期待定产值	
								产值	产能发挥率	产值	产能发挥率			
														
id	nf	yf	type sclx	cplb	yccnl	sykglyddzlcz	dnkglyddzlcz	djdpcddcz	djdpcddcnfhl	xjdpcddcz	xjdpcddcnfhl	cnjyhkglyddpcz	jhqddcz	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'sbdddcbjpcqk_xlkglydd')
DROP TABLE sbdddcbjpcqk_xlkglydd
CREATE TABLE [dbo].[sbdddcbjpcqk_xlkglydd](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[type] [int] not NULL,		--1 sclx-生产单元   2 sclx-生产类别
	[sclx] [varchar](50) not NULL,
	[yccnl] [numeric](18, 4) NULL,
	[wlyddzl] [numeric](18, 4) NULL,
	[dnwlyddzl] [numeric](18, 4) NULL,
	[nj1yddlypc] [numeric](18, 4) NULL,
	[nj1yddlwpc] [numeric](18, 4) NULL,
	[nj2yddlypc] [numeric](18, 4) NULL,
	[nj2yddlwpc] [numeric](18, 4) NULL,
	[nj3yddlypc] [numeric](18, 4) NULL,
	[nj3yddlwpc] [numeric](18, 4) NULL,
	[nj3yyhlydd] [numeric](18, 4) NULL,
	[cnjyhkglyddpcz] [numeric](18, 4) NULL,
	[jhqdd] [numeric](18, 4) NULL,
	[wx] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

