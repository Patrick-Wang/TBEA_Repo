--众合季度指标
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'jygk_zhjdzb')
DROP TABLE jygk_zhjdzb
CREATE TABLE [dbo].[jygk_zhjdzb](
	[id] [int] IDENTITY(1,1) NOT NULL,
	dwid int not null,
	zbid int not null,
	nf int not null,
	jd int not null,
	jdjhz [numeric](18, 4),
	jdljz [numeric](18, 4),
	zt int,
	xgsj datetime
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

--众合年度累计指标
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'jygk_zhndljzb')
DROP TABLE jygk_zhndljzb
CREATE TABLE [dbo].[jygk_zhndljzb](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	dwid int not null,
	zbid int not null,
	nf int not null,
	ndljz [numeric](18, 4),
	zt int,
	xgsj datetime
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

--众合质量单位指标映射
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'jygk_dw_ref_zb_zhzl')
DROP TABLE jygk_dw_ref_zb_zhzl
CREATE TABLE [dbo].[jygk_dw_ref_zb_zhzl](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	dwid int not null,
	zbid int not null
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

insert into jygk_dw_ref_zb_zhzl (dwid, zbid) values 
--高纯铝制品公司
(1301,132),
(1301,133),
(1301,134),
(1301,135),
--合金材料公司
(1302,137),
(1302,138),
(1302,139),
(1302,140),
(1302,141),
(1302,142),
(1302,135),
(1302,143),
--铝箔公司
(1303,147),
(1303,135),
(1303,148),
--电极箔公司
(1304,151),
(1304,152),
(1304,135),
(1304,153),
--金属结构与炭素材料公司
(1305,156),
(1305,157),
(1305,158),
(1305,159),
--新疆五元电线电缆有限公司
(1306,147)




