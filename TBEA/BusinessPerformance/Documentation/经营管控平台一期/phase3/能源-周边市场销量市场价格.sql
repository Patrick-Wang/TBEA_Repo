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
INSERT [dbo].[identifier_nyzbscxljg_kq] ([id], [name])VALUES (0,N'将二矿')
INSERT [dbo].[identifier_nyzbscxljg_kq] ([id], [name])VALUES (1,N'北山')
INSERT [dbo].[identifier_nyzbscxljg_kq] ([id], [name])VALUES (2,N'红沙泉')
INSERT [dbo].[identifier_nyzbscxljg_kq] ([id], [name])VALUES (3,N'义马')
INSERT [dbo].[identifier_nyzbscxljg_kq] ([id], [name])VALUES (4,N'明基凯源')
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

/***************************************************************************** 销量价格
			矿区	煤种	销量	价格	
id	nf	yf	kq	mz	xl	jg	zt

*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'nyzbscxljg_xljg')
DROP TABLE nyzbscxljg_xljg
CREATE TABLE [dbo].[nyzbscxljg_xljg](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[kq] [int] not NULL,
	[mz] [int] not NULL,
	[xl] [numeric](18, 4) NULL,
	[jg] [numeric](18, 4) NULL,
	[zt] [int] not NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]