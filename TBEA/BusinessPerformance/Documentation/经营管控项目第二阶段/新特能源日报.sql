/*****************************************************************************多晶硅日报
单位信息	指标名称	当日完成	库存结余
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'djgrb')
DROP TABLE djgrb
CREATE TABLE [dbo].[djgrb](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[date] [date] not null,
	[dwid] [int] NOT NULL,
	[zbid] [date] NOT NULL,
	[drwc] [numeric](18, 4),
	[kcjy] [numeric](18, 4),
	[zt] [int] NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/*****************************************************************************自备电厂日报
单位信息	指标名称	当日完成
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'zbdcrb')
DROP TABLE zbdcrb
CREATE TABLE [dbo].[zbdcrb](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[date] [date] not null,
	[dwid] [int] NOT NULL,
	[zbid] [date] NOT NULL,
	[drwc] [numeric](18, 4),
	[zt] [int] NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/*****************************************************************************新能源月计划
单位信息	指标名称	月计划
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'xnyyjh')
DROP TABLE xnyyjh
CREATE TABLE [dbo].[xnyyjh](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not null,
	[yf] [int] not null,
	[dwid] [int] NOT NULL,
	[zbid] [date] NOT NULL,
	[yjh] [numeric](18, 4),
	[zt] [int] NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/*****************************************************************************新能源年计划
单位信息	指标名称	月计划
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'xnynjh')
DROP TABLE xnynjh
CREATE TABLE [dbo].[xnynjh](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not null,
	[dwid] [int] NOT NULL,
	[zbid] [date] NOT NULL,
	[njh] [numeric](18, 4),
	[zt] [int] NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]