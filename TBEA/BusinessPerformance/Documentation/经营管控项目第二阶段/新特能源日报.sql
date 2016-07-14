/*****************************************************************************多晶硅日报
单位信息	日期	当日完成	库存结余
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'djgrb')
DROP TABLE djgrb
CREATE TABLE [dbo].[djgrb](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[date] [date] not null,
	[dwid] [int] NOT NULL,
	[drwc] [numeric](18, 4),
	[kcjy] [numeric](18, 4),
	[zt] [int] NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/*****************************************************************************多晶硅年计划
单位信息	日期	当日完成	库存结余
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'djgnjh')
DROP TABLE djgnjh
CREATE TABLE [dbo].[djgnjh](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not null,
	[dwid] [int] NOT NULL,
	[njh] [numeric](18, 4),
	[zt] [int] NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/*****************************************************************************多晶硅月计划
单位信息	日期	当日完成	库存结余
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'djgyjh')
DROP TABLE djgyjh
CREATE TABLE [dbo].[djgyjh](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not null,
	[yf] [int] not null,
	[dwid] [int] NOT NULL,
	[yjh] [numeric](18, 4),
	[zt] [int] NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/*****************************************************************************自备电厂日报
单位信息	日期	指标名称	当日完成
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'zbdcrb')
DROP TABLE zbdcrb
CREATE TABLE [dbo].[zbdcrb](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[date] [date] not null,
	[zbmc] [varchar](100),
	[drwc] [numeric](18, 4),
	[zt] [int] NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
/*****************************************************************************自备电厂年计划
单位信息	日期	当日完成	库存结余
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'zbdcnjh')
DROP TABLE zbdcnjh
CREATE TABLE [dbo].[zbdcnjh](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not null,
	[zbmc] [varchar](100) NOT NULL,
	[njh] [numeric](18, 4),
	[zt] [int] NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/*****************************************************************************自备电厂月计划
单位信息	日期	当日完成	库存结余
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'zbdcyjh')
DROP TABLE zbdcyjh
CREATE TABLE [dbo].[zbdcyjh](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not null,
	[yf] [int] not null,
	[zbmc] [varchar](100) NOT NULL,
	[yjh] [numeric](18, 4),
	[zt] [int] NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
