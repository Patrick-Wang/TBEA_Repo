
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_jjzjz_zb')
DROP TABLE identifier_jjzjz_zb
CREATE TABLE [dbo].[identifier_jjzjz_zb](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](30) NULL
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

insert into identifier_jjzjz_zb (name) values(	N'净利润')
insert into identifier_jjzjz_zb (name) values(	N'利息支出')
insert into identifier_jjzjz_zb (name) values(	N'研发费用')
insert into identifier_jjzjz_zb (name) values(	N'变卖优质资产取得的非经营性收益')
insert into identifier_jjzjz_zb (name) values(	N'税后净营业利润')
insert into identifier_jjzjz_zb (name) values(	N'平均所有者权益')
insert into identifier_jjzjz_zb (name) values(	N'平均负债合计')
insert into identifier_jjzjz_zb (name) values(	N'平均无息流动负债')
insert into identifier_jjzjz_zb (name) values(	N'平均在建工程')
insert into identifier_jjzjz_zb (name) values(	N'调整后资本')
insert into identifier_jjzjz_zb (name) values(	N'平均资本成本率')
insert into identifier_jjzjz_zb (name) values(	N'资本成本')
insert into identifier_jjzjz_zb (name) values(	N'人数')
insert into identifier_jjzjz_zb (name) values(	N'经济增加值')
insert into identifier_jjzjz_zb (name) values(	N'人均经济增加值')


IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'jjzjz')
DROP TABLE jjzjz
CREATE TABLE [dbo].[jjzjz](
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
