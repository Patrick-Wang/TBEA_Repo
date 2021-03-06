IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_tcnycsdc_daily_zb')
DROP TABLE identifier_tcnycsdc_daily_zb
CREATE TABLE [dbo].[identifier_tcnycsdc_daily_zb](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	category [varchar](50),
	name [varchar](50),
	unit [varchar](50)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_tcnycsdc_monthly_zb')
DROP TABLE identifier_tcnycsdc_monthly_zb
CREATE TABLE [dbo].[identifier_tcnycsdc_monthly_zb](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	category [varchar](50),
	name [varchar](50),
	unit [varchar](50),
	dailyId int
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'tcnycsdc_year_plan_zb')
DROP TABLE tcnycsdc_year_plan_zb
CREATE TABLE [dbo].[tcnycsdc_year_plan_zb](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	zbid [int],
	year [int],
	value	[numeric](18, 4),
	source [varchar](50),
	time datetime
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'tcnycsdc_month_plan_zb')
DROP TABLE tcnycsdc_month_plan_zb
CREATE TABLE [dbo].[tcnycsdc_month_plan_zb](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	zbid [int],
	year [int],
	month [int],
	value	[numeric](18, 4),
	source [varchar](50),
	time datetime
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'tcnycsdc_season_plan_zb')
DROP TABLE tcnycsdc_season_plan_zb
CREATE TABLE [dbo].[tcnycsdc_season_plan_zb](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	zbid [int],
	year [int],
	season [int], --(1,2,3,4)
	value	[numeric](18, 4),
	source [varchar](50),
	time datetime
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'tcnycsdc_day_plan_zb')
DROP TABLE tcnycsdc_day_plan_zb
CREATE TABLE [dbo].[tcnycsdc_day_plan_zb](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	zbid [int],
	day date,
	value	[numeric](18, 4),
	source [varchar](50),
	time datetime
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];

insert into identifier_tcnycsdc_monthly_zb (category, name, unit, dailyId) values 
('生产运营指标','发电量','万kWh','1'),
('生产运营指标','上网电量','万kWh','2'),
('生产运营指标','外售蒸汽量','T','3'),
('生产运营指标','外售蒸汽量','GJ','4'),
('生产运营指标','综合厂用电率','%','5'),
('生产运营指标','发电厂用电率','%','6'),
('生产运营指标','入厂煤热值','大卡/kg','7'),
('生产运营指标','入炉煤热值','大卡/kg','8'),
('生产运营指标','耗原煤量','T','9'),
('生产运营指标','耗油量','T','10'),
('生产运营指标','耗水量','T','11'),
('生产运营指标','库存煤','T','12'),
('生产运营指标','库存油','T','13'),
('生产运营指标','运行小时数','h','14'),
('生产运营指标','利用小时数','h','15'),
('环保指标','除尘器月平均投运率','%','16'),
('环保指标','脱硫月平均投运率','%','17'),
('环保指标','脱硝月平均投运率','%','18'),
('环保指标','烟尘月平均排放浓度','mg/Nm3','19'),
('环保指标','烟尘超标排放时间','h','20'),
('环保指标','SO2月平均排放浓度','mg/Nm3','21'),
('环保指标','SO2超标排放时间','h','22'),
('环保指标','脱硫月平均脱硫效率','%','23'),
('环保指标','NOx月平均排放浓度','mg/Nm3','24'),
('环保指标','脱硝月平均氨逃逸','ppm','25'),
('环保指标','NOX超标排放时间','h','26'),
('环保指标','脱硝月平均脱硝效率','%','27'),
('供热指标','高温网供水平均温度','℃','37'),
('供热指标','高温网回水平均温度','℃','38'),
('供热指标','低温网供水平均温度','℃','41'),
('供热指标','低温网回水平均温度','℃','42');

insert into identifier_tcnycsdc_daily_zb (category, name, unit) values 
('生产运营指标','发电量','万kWh'),
('生产运营指标','上网电量','万kWh'),
('生产运营指标','外售蒸汽量','T'),
('生产运营指标','外售蒸汽量','GJ'),
('生产运营指标','发电厂用电率','%'),
('生产运营指标','综合厂用电率','%'),
('生产运营指标','入厂煤热值','大卡/kg'),
('生产运营指标','入炉煤热值','大卡/kg'),
('生产运营指标','日耗原煤','T'),
('生产运营指标','日耗油','T'),
('生产运营指标','日来水','T'),
('生产运营指标','库存煤','T'),
('生产运营指标','库存油','T'),
('生产运营指标','运行小时数','h'),
('生产运营指标','利用小时数','h'),
('环保指标','除尘器日平均投运率','%'),
('环保指标','脱硫日平均投运率','%'),
('环保指标','脱硝日平均投运率','%'),
('环保指标','烟尘日平均排放浓度','mg/Nm3'),
('环保指标','烟尘超标排放时间','h'),
('环保指标','SO2日平均排放浓度','mg/Nm3'),
('环保指标','SO2超标排放时间','h'),
('环保指标','脱硫日平均脱硫效率','%'),
('环保指标','NOx日平均排放浓度','mg/Nm3'),
('环保指标','脱硝日平均氨逃逸','ppm'),
('环保指标','NOX超标排放时间','h'),
('环保指标','脱硝日平均脱硝效率','%'),
('供热指标','加热器运行台数','台'),
('供热指标','加热器备用台数','台'),
('供热指标','加热器检修台数','台'),
('供热指标','热网循环泵运行台数','台'),
('供热指标','热网循环泵备用台数','台'),
('供热指标','热网循环泵检修台数','台'),
('供热指标','补水泵运行台数','台'),
('供热指标','补水泵备用台数','台'),
('供热指标','补水泵检修台数','台'),
('供热指标','高温网供水温度','℃'),
('供热指标','高温网回水温度','℃'),
('供热指标','高温网供水流量','t/h'),
('供热指标','高温网补水量','t/h'),
('供热指标','低温网供水温度','℃'),
('供热指标','低温网回水温度','℃'),
('供热指标','低温网供水流量','t/h'),
('供热指标','低温网补水量','t/h'),
('容量及机组状态','装机容量','MW'),
('容量及机组状态','运行容量','MW'),
('容量及机组状态','运行机组','号'),
('容量及机组状态','备用机组','号'),
('容量及机组状态','检修机组','号'),
('容量及机组状态','脱硫机组','号'),
('容量及机组状态','脱硫运行','号');