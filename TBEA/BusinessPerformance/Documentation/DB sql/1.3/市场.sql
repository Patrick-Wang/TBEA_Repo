
 /***************************************************************************** 年度签约指标
id name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'market_ndqy')
DROP TABLE market_ndqy
CREATE TABLE [dbo].[market_ndqy](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] int not null,
	[zbid] int not null,
	[dwid] int not null,
	[qye] numeric(18, 4),
	[zt] int not null
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


 /***************************************************************************** 月度签约指标
id name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'market_ydqy')
DROP TABLE market_ydqy
CREATE TABLE [dbo].[market_ydqy](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] int not null,
	[yf] int not null,
	[zbid] int not null,
	[dwid] int not null,
	[qye] numeric(18, 4),
	[zt] int not null
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/***************************************************************************** 竞标
id name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'market_scjb')
DROP TABLE market_scjb
CREATE TABLE [dbo].market_scjb(
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] int not null,
	[yf] int not null,
	[dwid] int not null,
	[hyid] int not null,
	[tbje] numeric(18, 4),
	[zbje] numeric(18, 4),
	[zt] int not null
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

