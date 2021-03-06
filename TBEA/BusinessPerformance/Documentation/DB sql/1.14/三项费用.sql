IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_glfy_zb')
DROP TABLE identifier_glfy_zb
CREATE TABLE [dbo].[identifier_glfy_zb](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	name [varchar](50)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'glfy')
DROP TABLE glfy
CREATE TABLE [dbo].[glfy](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	nf [int],
	yf [int],
	dwid [int],
	zbid [int],
	sjz	[numeric](18, 4)	,--Yes	合同金额,
	ljz	[numeric](18, 4)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_xsfy_zb')
DROP TABLE identifier_xsfy_zb
CREATE TABLE [dbo].[identifier_xsfy_zb](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	name [varchar](50)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'xsfy')
DROP TABLE xsfy
CREATE TABLE [dbo].[xsfy](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	nf [int],
	yf [int],
	dwid [int],
	zbid [int],
	sjz	[numeric](18, 4)	,--Yes	合同金额,
	ljz	[numeric](18, 4)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cwfy_zb')
DROP TABLE identifier_cwfy_zb
CREATE TABLE [dbo].[identifier_cwfy_zb](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	name [varchar](50)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cwfy')
DROP TABLE cwfy
CREATE TABLE [dbo].[cwfy](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	nf [int],
	yf [int],
	dwid [int],
	zbid [int],
	sjz	[numeric](18, 4)	,--Yes	合同金额,
	ljz	[numeric](18, 4)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

insert into identifier_glfy_zb (name) values 
('管理费用'),
('工资'),
('奖金'),
('福利费'),
('工会经费'),
('职工教育经费'),
('社会保险金'),
('办公费'),
('差旅费'),
('车辆费'),
('业务招待费'),
('折旧费'),
('采暖费'),
('广告费'),
('宣传费'),
('会务费'),
('培训费'),
('劳保费'),
('运输费'),
('电费'),
('水费'),
('保险费'),
('咨询费'),
('会员费'),
('劳务费'),
('质量成本'),
('税金'),
('评估费'),
('人才招聘费'),
('低耗摊销'),
('物料消耗'),
('无形资产摊销'),
('修理费'),
('租赁费'),
('保安费'),
('注册登记费'),
('ERP运行费'),
('物业费'),
('绿化排污费'),
('大修费用'),
('党团经费'),
('清欠费'),
('诉讼费'),
('董事会经费'),
('分红费用'),
('增发费用'),
('科技项目费用'),
('新产品开发费'),
('民政局管理费'),
('认证费'),
('仓储费'),
('审计费'),
('专家费用'),
('专利费用'),
('商标许可费'),
('辞退福利'),
('递延收益'),
('燃气费'),
('网络视频通讯费'),
('河道维护费'),
('环境监测费'),
('消防费'),
('检测费'),
('残疾人就业保障金'),
('安技费'),
('股权激励'),
('其他')

insert into identifier_xsfy_zb (name) values 
('销售费用'),
('工资'),
('奖金'),
('福利费'),
('工会经费'),
('职工教育经费'),
('社会保险金'),
('办公费'),
('差旅费'),
('车辆费'),
('业务招待费'),
('折旧费'),
('采暖费'),
('广告费'),
('宣传费'),
('会务费'),
('培训费'),
('劳保费'),
('运输费'),
('水费'),
('电费'),
('保险费'),
('咨询费'),
('代理费'),
('劳务费'),
('质量成本'),
('标书费'),
('中标费'),
('押运费'),
('低耗摊销'),
('物料消耗'),
('修理费'),
('租车费'),
('入网费'),
('商检费'),
('安装费'),
('仓储费'),
('包装费'),
('商标许可费'),
('网络视频通讯费'),
('翻译费'),
('租赁费'),
('其他')

insert into identifier_cwfy_zb (name) values
('财务费用'),
('代贴现利息收入'),
('担保费'),
('抵消数'),
('合并数'),
('汇兑损益'),
('利率掉期收入'),
('利息收入'),
('利息支出'),
('买方信贷利息收入'),
('票据贴现利息'),
('商标许可费'),
('手续费'),
('银行存款利息'),
('银行借款利息'),
('资金占用利息'),
('资金中心存款利息收入'),
('资金中心借款利息'),
('其他'),
('其他利息收入'),
('其他利息支出')