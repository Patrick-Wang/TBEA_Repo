/***************************************************************************** 不合格类别 - 变压器
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cpzlqk_bhglb_byq')
DROP TABLE identifier_cpzlqk_bhglb_byq
CREATE TABLE [dbo].[identifier_cpzlqk_bhglb_byq](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ON
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (0,N'局放超标')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (1,N'介损超标')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (2,N'局部放电')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (3,N'工频击穿')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (4,N'阻抗超标')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (5,N'损耗超标')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (6,N'直阻不平')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (7,N'耐压放电')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (8,N'匝间短路')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (9,N'声级超标')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (10,N'其他')
SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_bhglb_byq] OFF      

/***************************************************************************** 不合格类别 - 线缆
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cpzlqk_bhglb_xl')
DROP TABLE identifier_cpzlqk_bhglb_xl
CREATE TABLE [dbo].[identifier_cpzlqk_bhglb_xl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_bhglb_xl] ON
INSERT [dbo].[identifier_cpzlqk_bhglb_xl] ([id], [name])VALUES (0,N'外观问题')
INSERT [dbo].[identifier_cpzlqk_bhglb_xl] ([id], [name])VALUES (1,N'结构尺寸')
INSERT [dbo].[identifier_cpzlqk_bhglb_xl] ([id], [name])VALUES (2,N'电性能')
INSERT [dbo].[identifier_cpzlqk_bhglb_xl] ([id], [name])VALUES (3,N'机械性能')
INSERT [dbo].[identifier_cpzlqk_bhglb_xl] ([id], [name])VALUES (4,N'印刷标识问题')
INSERT [dbo].[identifier_cpzlqk_bhglb_xl] ([id], [name])VALUES (5,N'计米长度及重量不足问题')
SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_bhglb_xl] OFF     

/***************************************************************************** 责任类别 - 变压器
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cpzlqk_zrlb_byq')
DROP TABLE identifier_cpzlqk_zrlb_byq
CREATE TABLE [dbo].[identifier_cpzlqk_zrlb_byq](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_zrlb_byq] ON
INSERT [dbo].[identifier_cpzlqk_zrlb_byq] ([id], [name])VALUES (0,N'原材料组配件原因')
INSERT [dbo].[identifier_cpzlqk_zrlb_byq] ([id], [name])VALUES (1,N'设计原因')
INSERT [dbo].[identifier_cpzlqk_zrlb_byq] ([id], [name])VALUES (2,N'过程制造原因')
SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_zrlb_byq] OFF 

/***************************************************************************** 责任类别 - 线缆
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cpzlqk_zrlb_xl')
DROP TABLE identifier_cpzlqk_zrlb_xl
CREATE TABLE [dbo].[identifier_cpzlqk_zrlb_xl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_zrlb_xl] ON
INSERT [dbo].[identifier_cpzlqk_zrlb_xl] ([id], [name])VALUES (0,N'原材料组配件原因')
INSERT [dbo].[identifier_cpzlqk_zrlb_xl] ([id], [name])VALUES (1,N'技术工艺原因')
INSERT [dbo].[identifier_cpzlqk_zrlb_xl] ([id], [name])VALUES (2,N'过程制造原因')
SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_zrlb_xl] OFF


/***************************************************************************** 输变电产品质量情况-统计结果
				不合格数	总数	
id	nf	yf	dwid	bhg	zs	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'sbdcpzlqk_zltjjg')
DROP TABLE sbdcpzlqk_zltjjg
CREATE TABLE [dbo].[sbdcpzlqk_zltjjg](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[tjfs] [int] not NULL,
	[cpid] [int] not NULL,
	[bhgs] [int] not NULL,
	[zs] [int] not NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] 

/***************************************************************************** 输变电产品质量情况-一次送试不合格问题明细 - 变压器
				产品类型	生产号	产品型号	试验不合格现象	不合格类别	原因分析	处理措施		处理结果		责任类别
id	nf	yf	dwid	cplx	sch		cpxh		sybhgxx			bhglx		yyfx		clcs			cljg			zrlb
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'sbdcpzlqk_bhgwtmx_byq')
DROP TABLE sbdcpzlqk_bhgwtmx_byq
CREATE TABLE [dbo].[sbdcpzlqk_bhgwtmx_byq](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[cplx] [varchar](200) not NULL,
	[sch] [varchar](200) not NULL,
	[cpxh] [varchar](200) not NULL,
	[sybhgxx] [varchar](2000),
	[bhglxid] [int] not NULL,
	[yyfx] [varchar](2000),
	[clcs] [varchar](2000),
	[cljg] [varchar](2000),
	[zrlbid] [int] not NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/***************************************************************************** 输变电产品质量情况-不合格问题明细  - 线缆
				产品类型	生产号	产品型号	不合格数量	试验不合格现象	不合格类别	原因分析	处理措施		处理结果		责任类别
id	nf	yf	dwid	cplx	sch		cpxh		bhgsl		sybhgxx			bhglx		yyfx		clcs			cljg			zrlb
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'sbdcpzlqk_bhgwtmx_xl')
DROP TABLE sbdcpzlqk_bhgwtmx_xl
CREATE TABLE [dbo].[sbdcpzlqk_bhgwtmx_xl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[cplx] [varchar](200) not NULL,
	[sch] [varchar](200) not NULL,
	[cpxh] [varchar](200) not NULL,
	[bhgsl] [int],
	[sybhgxx] [varchar](2000),
	[bhglxid] [int] not NULL,
	[yyfx] [varchar](2000),
	[clcs] [varchar](2000),
	[cljg] [varchar](2000),
	[zrlbid] [int] not NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]