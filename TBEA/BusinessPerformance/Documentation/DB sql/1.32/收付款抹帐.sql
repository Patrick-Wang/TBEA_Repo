--应收账款余额明细表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'fireceivable')
DROP TABLE fireceivable
CREATE TABLE [dbo].[fireceivable](
	[id] [int] IDENTITY(1,1) NOT NULL,
	custname varchar(100), --客户名称
	compname varchar(100), --公司名称
	amount numeric(18, 4), --金额
	fiyear int, --年份
	fimonth int, --月份
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
--应付账款余额明细表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'fipayable')
DROP TABLE fipayable
CREATE TABLE [dbo].[fipayable](
	[id] [int] IDENTITY(1,1) NOT NULL,
	supyname varchar(100), --供应商名称
	compname varchar(100), --公司名称
	amount numeric(18, 4), --金额
	fiyear int, --年份
	fimonth int, --月份
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/*##############################################*/
/*#### 应收应付抹帐统计表               ########*/
/*##############################################*/
CREATE TABLE [dbo].[fiwipeout](
	[cusupyname] [nvarchar](50) NOT NULL,
	[compname] [nvarchar](50) NOT NULL,
	[amount] [numeric](18, 2) NOT NULL,
	[fiyear] [int] NULL,
	[fimonth] [int] NULL,
	[tag1] [int] NULL,
	[tag2] [int] NULL
) ON [PRIMARY]


create view fiwipeout_v as
SELECT dbo.fiwipeout.*
FROM dbo.fiwipeout
LEFT JOIN
(
	SELECT
		cusupyname
		,compname
		,COUNT(compname) as cnt
	FROM dbo.fiwipeout
	GROUP BY
		cusupyname
		,compname
	HAVING COUNT(compname) > 1) st1
ON dbo.fiwipeout.cusupyname = st1.cusupyname
AND dbo.fiwipeout.compname = st1.compname
WHERE isnull(cnt,0) = 0