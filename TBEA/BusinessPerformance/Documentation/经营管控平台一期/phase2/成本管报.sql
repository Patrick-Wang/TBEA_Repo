/***************************************************************************** 成本分类
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cbfx_cbfl')
DROP TABLE identifier_cbfx_cbfl
CREATE TABLE [dbo].[identifier_cbfx_cbfl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[identifier_cbfx_cbfl] ON
INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (0,N'土方剥离爆破成本')
INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (1,N'原煤爆破成本')
INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (2,N'原煤采运成本')
INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (3,N'回筛倒运成本')
INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (4,N'装车成本')
INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (5,N'直接成本合计')
INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (6,N'非可控成本')
INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (7,N'可控成本')
INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (8,N'制造费用小计')
INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (9,N'技改财务费用')
INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (10,N'生产成本合计')
SET IDENTITY_INSERT [dbo].[identifier_cbfx_cbfl] OFF      


/***************************************************************************** 吨煤成本明细
			成本构成	计划	实际
id	nf	yf	cbgc	jhz	sjz
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cbfx_dmcbmx')
DROP TABLE cbfx_dmcbmx
CREATE TABLE [dbo].[cbfx_dmcbmx](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[cbflid] [int] not NULL,
	[jhz] [numeric](18, 4) NULL,
	[sjz] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/***************************************************************************** 能源贸易业务毛利分析
	合作客户	贸易项目	数量	收入	成本	毛利	毛利率
id	hzkh	myxm	sl	sr	cb	ml	mll
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cbfx_nymyywmlfx')
DROP TABLE cbfx_nymyywmlfx
CREATE TABLE [dbo].[cbfx_nymyywmlfx](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[hzkh] [varchar](50) not NULL,
	[myxm] [varchar](50) not NULL,
	[sl] [int] NULL,
	[sr] [numeric](18, 4) NULL,
	[cb] [numeric](18, 4) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

