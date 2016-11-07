
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_dbxq_zb')
DROP TABLE identifier_dbxq_zb
CREATE TABLE [dbo].[identifier_dbxq_zb](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](30) NULL
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

insert into identifier_dbxq_zb (name) values(	N'净资产收益率（%）')
insert into identifier_dbxq_zb (name) values(	N'总资产报酬率（%）')
insert into identifier_dbxq_zb (name) values(	N'销售（营业）利润率（%）')
insert into identifier_dbxq_zb (name) values(	N'盈余现金保障倍数')
insert into identifier_dbxq_zb (name) values(	N'成本费用利润率（%）')
insert into identifier_dbxq_zb (name) values(	N'资本收益率（%）')
insert into identifier_dbxq_zb (name) values(	N'总资产周转率（次）')
insert into identifier_dbxq_zb (name) values(	N'应收账款周转率（次）')
insert into identifier_dbxq_zb (name) values(	N'不良资产比率（次）')
insert into identifier_dbxq_zb (name) values(	N'流动资产周转率（次）')
insert into identifier_dbxq_zb (name) values(	N'资产现金回收率（%）')
insert into identifier_dbxq_zb (name) values(	N'资产负债率（%）')
insert into identifier_dbxq_zb (name) values(	N'已获利息倍数')
insert into identifier_dbxq_zb (name) values(	N'速度比率（%）')
insert into identifier_dbxq_zb (name) values(	N'现金流动负债比率（%）')
insert into identifier_dbxq_zb (name) values(	N'带息负债比率（%）')
insert into identifier_dbxq_zb (name) values(	N'或有负债比率（%）')
insert into identifier_dbxq_zb (name) values(	N'销售（营业）增长率（%）')
insert into identifier_dbxq_zb (name) values(	N'资本保值增值率（%）')
insert into identifier_dbxq_zb (name) values(	N'销售（营业）利润增长率（%）')
insert into identifier_dbxq_zb (name) values(	N'总资产增长率（%）')
insert into identifier_dbxq_zb (name) values(	N'技术投入比率（%）')
insert into identifier_dbxq_zb (name) values(	N'存货周转率（次）')
insert into identifier_dbxq_zb (name) values(	N'两金占流动资产比重（%）')
insert into identifier_dbxq_zb (name) values(	N'成本费用占营业收入比重（%）')
insert into identifier_dbxq_zb (name) values(	N'经济增加值率（%）')
insert into identifier_dbxq_zb (name) values(	N'EBITDA率（%）')
insert into identifier_dbxq_zb (name) values(	N'资本积累率（%）')


IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'dbxq')
DROP TABLE dbxq
CREATE TABLE [dbo].[dbxq](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[nf] int not null,
	[yf] int not null,
	[dwid] int not null,
	[zbid] [int] NULL,
	[value] [numeric](18, 4) NULL
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
