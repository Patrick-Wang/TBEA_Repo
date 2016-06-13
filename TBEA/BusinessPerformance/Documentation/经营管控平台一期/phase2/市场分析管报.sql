/***************************************************************************** 行业
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_scfxgb_hy')
DROP TABLE identifier_scfxgb_hy
CREATE TABLE [dbo].[identifier_scfxgb_hy](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET IDENTITY_INSERT [dbo].[identifier_scfxgb_hy] ON
INSERT [dbo].[identifier_scfxgb_hy] ([id], [name])	VALUES (0,N'国网')
INSERT [dbo].[identifier_scfxgb_hy] ([id], [name])	VALUES (1,N'南网')
INSERT [dbo].[identifier_scfxgb_hy] ([id], [name])	VALUES (2,N'火电')
INSERT [dbo].[identifier_scfxgb_hy] ([id], [name])	VALUES (3,N'水电')
INSERT [dbo].[identifier_scfxgb_hy] ([id], [name])	VALUES (4,N'风电')
INSERT [dbo].[identifier_scfxgb_hy] ([id], [name])	VALUES (5,N'光伏')
INSERT [dbo].[identifier_scfxgb_hy] ([id], [name])	VALUES (6,N'核电')
INSERT [dbo].[identifier_scfxgb_hy] ([id], [name])	VALUES (7,N'轨道交通')
INSERT [dbo].[identifier_scfxgb_hy] ([id], [name])	VALUES (8,N'石油石化')
INSERT [dbo].[identifier_scfxgb_hy] ([id], [name])	VALUES (9,N'煤炭及煤化工')
INSERT [dbo].[identifier_scfxgb_hy] ([id], [name])	VALUES (10,N'钢铁冶金')
INSERT [dbo].[identifier_scfxgb_hy] ([id], [name])	VALUES (11,N'航天军工')
INSERT [dbo].[identifier_scfxgb_hy] ([id], [name])	VALUES (12,N'内贸')
INSERT [dbo].[identifier_scfxgb_hy] ([id], [name])	VALUES (13,N'其它')
INSERT [dbo].[identifier_scfxgb_hy] ([id], [name])	VALUES (14,N'连锁经营')
SET IDENTITY_INSERT [dbo].[identifier_scfxgb_hy] OFF        


/***************************************************************************** 输变电-细分市场签约 
			行业	签约额	
id	nf	yf	hy	qye	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'scfxgb_sbdxfscqy')
DROP TABLE scfxgb_sbdxfscqy
CREATE TABLE [dbo].[scfxgb_sbdxfscqy](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid]  [int] not NULL,
	[hyid] [int] not NULL,
	[qye] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/***************************************************************************** 输变电-细分产品签约情况及趋势
			产业	产品	签约额	
id	nf	yf	cy	cp	qye	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'scfxgb_xfcpqy')
DROP TABLE scfxgb_xfcpqy
CREATE TABLE [dbo].[scfxgb_xfcpqy](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[cpid] [int] not NULL,
	[tjfs] [int] not NULL,	-- 1->变压器	2->线缆
	[qye] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

