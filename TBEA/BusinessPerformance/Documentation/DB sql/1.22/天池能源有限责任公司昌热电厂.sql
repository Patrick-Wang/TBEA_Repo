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
('生产运营指标','发电量1号机组','万kWh',	1	),
('生产运营指标','发电量2号机组','万kWh',	2	),
('生产运营指标','发电量合计','万kWh',	3	),
('生产运营指标','上网电量1号机组','万kWh',	4	),
('生产运营指标','上网电量2号机组','万kWh',	5	),
('生产运营指标','上网电量合计','万kWh',	6	),
('生产运营指标','月负荷率','%',	9	),
('生产运营指标','采暖供热量','GJ',	10	),
('生产运营指标','外售蒸汽量','T',	11	),
('生产运营指标','外售蒸汽量','GJ',	12	),
('生产运营指标','厂用电率1号机生产厂用电率','%',	13	),
('生产运营指标','厂用电率2号机生产厂用电率','%',	14	),
('生产运营指标','厂用电率全厂综合厂用电率','%',	15	),
('生产运营指标','入厂煤热值','kj/kg',	16	),
('生产运营指标','入炉煤热值','kj/kg',	17	),
('生产运营指标','月来煤','T',	18	),
('生产运营指标','耗原煤量','T',	19	),
('生产运营指标','耗油量','T',	20	),
('生产运营指标','耗水量','T',	21	),
('生产运营指标','标准煤耗','g/kWh',	1000	),
('生产运营指标','库存煤','T',	22	),
('生产运营指标','库存油','T',	23	),
('生产运营指标','运行小时数','h',	24	),
('生产运营指标','利用小时数','h',	25	),
('可靠性指标','运行机组','号',	57	),
('可靠性指标','备用机组','号',	58	),
('可靠性指标','检修机组','号',	59	),
('可靠性指标','计划停运','次',	1001	),
('可靠性指标','计划停运','h',	1002	),
('可靠性指标','非计划停运','次',	1003	),
('可靠性指标','非计划停运','h',	1004	),
('可靠性指标','强迫停运','次',	1005	),
('可靠性指标','强迫停运','h',	1006	),
('环保指标','除尘器月平均投运率','%',	26	),
('环保指标','脱硫月平均投运率','%',	27	),
('环保指标','脱硝月平均投运率','%',	28	),
('环保指标','烟尘月平均排放浓度','mg/Nm3',	29	),
('环保指标','烟尘超标排放时间','h',	30	),
('环保指标','SO2月平均排放浓度','mg/Nm3',	31	),
('环保指标','SO2超标排放时间','h',	32	),
('环保指标','脱硫月平均脱硫效率','%',	33	),
('环保指标','NOx月平均排放浓度','mg/Nm3',	34	),
('环保指标','脱硝月平均氨逃逸','ppm',	35	),
('环保指标','NOX超标排放时间','h',	36	),
('环保指标','脱硝月平均脱硝效率','%',	37	),
('供热指标','高温网供水平均温度','℃',	47	),
('供热指标','高温网回水平均温度','℃',	48	),
('供热指标','低温网供水平均温度','℃',	51	),
('供热指标','低温网回水平均温度','℃',	52	);


SET IDENTITY_INSERT [dbo].[identifier_tcnycsdc_daily_zb] ON
insert into identifier_tcnycsdc_daily_zb (id, category, name, unit) values 
(	1	,'生产运营指标','发电量1号机组','万kWh'),
(	2	,'生产运营指标','发电量2号机组','万kWh'),
(	3	,'生产运营指标','发电量合计','万kWh'),
(	4	,'生产运营指标','上网电量1号机组','万kWh'),
(	5	,'生产运营指标','上网电量2号机组','万kWh'),
(	6	,'生产运营指标','上网电量合计','万kWh'),
(	7	,'生产运营指标','日负荷率1号机组','%'),
(	8	,'生产运营指标','日负荷率2号机组','%'),
(	9	,'生产运营指标','日负荷综合负荷','%'),
(	10	,'生产运营指标','采暖供热量','GJ'),
(	11	,'生产运营指标','外售蒸汽量','T'),
(	12	,'生产运营指标','外售蒸汽量','GJ'),
(	13	,'生产运营指标','厂用电率1号机生产厂用电率','%'),
(	14	,'生产运营指标','厂用电率2号机生产厂用电率','%'),
(	15	,'生产运营指标','厂用电率全厂综合厂用电率','%'),
(	16	,'生产运营指标','入厂煤热值','kj/kg'),
(	17	,'生产运营指标','入炉煤热值','kj/kg'),
(	18	,'生产运营指标','日来煤','T'),
(	19	,'生产运营指标','日耗原煤','T'),
(	20	,'生产运营指标','日耗油','T'),
(	21	,'生产运营指标','日来水','T'),
(	22	,'生产运营指标','库存煤','T'),
(	23	,'生产运营指标','库存油','T'),
(	24	,'生产运营指标','运行小时数','h'),
(	25	,'生产运营指标','利用小时数','h'),
(	26	,'环保指标','除尘器日平均投运率','%'),
(	27	,'环保指标','脱硫日平均投运率','%'),
(	28	,'环保指标','脱硝日平均投运率','%'),
(	29	,'环保指标','烟尘日平均排放浓度','mg/Nm3'),
(	30	,'环保指标','烟尘超标排放时间','h'),
(	31	,'环保指标','SO2日平均排放浓度','mg/Nm3'),
(	32	,'环保指标','SO2超标排放时间','h'),
(	33	,'环保指标','脱硫日平均脱硫效率','%'),
(	34	,'环保指标','NOx日平均排放浓度','mg/Nm3'),
(	35	,'环保指标','脱硝日平均氨逃逸','ppm'),
(	36	,'环保指标','NOX超标排放时间','h'),
(	37	,'环保指标','脱硝日平均脱硝效率','%'),
(	38	,'供热指标','加热器运行台数','台'),
(	39	,'供热指标','加热器备用台数','台'),
(	40	,'供热指标','加热器检修台数','台'),
(	41	,'供热指标','热网循环泵运行台数','台'),
(	42	,'供热指标','热网循环泵备用台数','台'),
(	43	,'供热指标','热网循环泵检修台数','台'),
(	44	,'供热指标','补水泵运行台数','台'),
(	45	,'供热指标','补水泵备用台数','台'),
(	46	,'供热指标','补水泵检修台数','台'),
(	47	,'供热指标','高温网供水温度','℃'),
(	48	,'供热指标','高温网回水温度','℃'),
(	49	,'供热指标','高温网供水流量','t/h'),
(	50	,'供热指标','高温网补水量','t/h'),
(	51	,'供热指标','低温网供水温度','℃'),
(	52	,'供热指标','低温网回水温度','℃'),
(	53	,'供热指标','低温网供水流量','t/h'),
(	54	,'供热指标','低温网补水量','t/h'),
(	55	,'容量及机组状态','装机容量','MW'),
(	56	,'容量及机组状态','运行容量','MW'),
(	57	,'容量及机组状态','运行机组','号'),
(	58	,'容量及机组状态','备用机组','号'),
(	59	,'容量及机组状态','检修机组','号'),
(	60	,'容量及机组状态','脱硫机组','号'),
(	61	,'容量及机组状态','脱硫运行','号');
SET IDENTITY_INSERT [dbo].[identifier_tcnycsdc_daily_zb] OFF