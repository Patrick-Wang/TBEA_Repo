/***************************************************************************** 矿区
id name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_nyzbscxljg_kq')
DROP TABLE identifier_nyzbscxljg_kq
CREATE TABLE [dbo].[identifier_nyzbscxljg_kq](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
SET IDENTITY_INSERT [dbo].[identifier_nyzbscxljg_kq] ON
INSERT [dbo].[identifier_nyzbscxljg_kq] ([id], [name])VALUES (0,N'南矿')
INSERT [dbo].[identifier_nyzbscxljg_kq] ([id], [name])VALUES (1,N'神华准东')
INSERT [dbo].[identifier_nyzbscxljg_kq] ([id], [name])VALUES (2,N'神东天隆')
INSERT [dbo].[identifier_nyzbscxljg_kq] ([id], [name])VALUES (3,N'湖北宜化')
INSERT [dbo].[identifier_nyzbscxljg_kq] ([id], [name])VALUES (4,N'大成国际')
INSERT [dbo].[identifier_nyzbscxljg_kq] ([id], [name])VALUES (5,N'将二矿')
INSERT [dbo].[identifier_nyzbscxljg_kq] ([id], [name])VALUES (6,N'北山')
INSERT [dbo].[identifier_nyzbscxljg_kq] ([id], [name])VALUES (7,N'红沙泉')
INSERT [dbo].[identifier_nyzbscxljg_kq] ([id], [name])VALUES (8,N'义马')
INSERT [dbo].[identifier_nyzbscxljg_kq] ([id], [name])VALUES (9,N'明基凯源')
SET IDENTITY_INSERT [dbo].[identifier_nyzbscxljg_kq] OFF
/***************************************************************************** 煤种
id name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_nyzbscxljg_mz')
DROP TABLE identifier_nyzbscxljg_mz
CREATE TABLE [dbo].[identifier_nyzbscxljg_mz](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
SET IDENTITY_INSERT [dbo].[identifier_nyzbscxljg_mz] ON
INSERT [dbo].[identifier_nyzbscxljg_mz] ([id], [name])VALUES (0,N'大块')
INSERT [dbo].[identifier_nyzbscxljg_mz] ([id], [name])VALUES (1,N'中块')
INSERT [dbo].[identifier_nyzbscxljg_mz] ([id], [name])VALUES (2,N'三八块')
SET IDENTITY_INSERT [dbo].[identifier_nyzbscxljg_mz] OFF

/***************************************************************************** 销量
			矿区	煤种	销量	
id	nf	yf	kq	mz	xl	zt

*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'nyzbscxl_xl')
DROP TABLE nyzbscxl_xl
CREATE TABLE [dbo].[nyzbscxl_xl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[dwid][int] not NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[kq] [int] not NULL,
	[mz] [int] not NULL,
	[xl] [numeric](18, 4) NULL,
	[zt] [int] not NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/***************************************************************************** 价格
			矿区	煤种	价格	
id	nf	yf	kq	mz	jg	zt

*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'nyzbscjg_jg')
DROP TABLE nyzbscjg_jg
CREATE TABLE [dbo].[nyzbscjg_jg](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[dwid][int] not NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[kq] [int] not NULL,
	[mz] [int] not NULL,
	[jg] [numeric](18, 4) NULL,
	[zt] [int] not NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/***************************************************************************** 矿区映射表
			单位	矿区
id	dwid kqid

*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_nyzbsckqmatch')
DROP TABLE identifier_nyzbsckqmatch
CREATE TABLE [dbo].[identifier_nyzbsckqmatch](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[dwid][int] not NULL,
	[kqid][int] not NULL,
	[mzid][int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
SET IDENTITY_INSERT [dbo].[identifier_nyzbsckqmatch] ON 
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (0,701,0,0)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (1,701,0,1)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (2,701,0,2)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (3,701,1,0)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (4,701,1,1)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (5,701,1,2)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (6,701,2,0)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (7,701,2,1)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (8,701,2,2)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (9,701,3,0)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (10,701,3,1)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (11,701,3,2)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (12,701,4,0)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (13,701,4,1)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (14,701,4,2)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (15,702,5,0)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (16,702,5,1)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (17,702,5,2)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (18,702,6,0)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (19,702,6,1)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (20,702,6,2)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (21,702,7,0)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (22,702,7,1)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (23,702,7,2)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (24,702,8,0)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (25,702,8,1)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (26,702,8,2)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (27,702,9,0)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (28,702,9,1)
INSERT [dbo].[identifier_nyzbsckqmatch] ([id], [dwid],[kqid],[mzid])VALUES (29,702,9,2)

SET IDENTITY_INSERT [dbo].[identifier_nyzbsckqmatch] OFF

