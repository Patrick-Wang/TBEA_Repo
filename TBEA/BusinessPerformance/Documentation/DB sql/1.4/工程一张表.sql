IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'project_comprehensive_table_nc')
DROP TABLE project_comprehensive_table_nc
CREATE TABLE [dbo].[project_comprehensive_table_nc](
	id int IDENTITY(1,1) NOT NULL,
	xmbh	[varchar](300)	,--	项目编号
	xmqc	[varchar](300)	,--	项目全称
	khqc	[varchar](300)	,--	客户全称（拟转让合作方）NC中客商辅助
	bysjqrsr	[numeric](18, 4)	,--	本月实际确认收入（万元）
	bysjkpje	[numeric](18, 4)	,--	本月实际开票金额（万元）
	bysjml		[numeric](18, 4)    ,--	本月实际毛利（万元）
	bysjrzch	[numeric](18, 4)	,--	本月实际入账存货（万元）

	ljqrsr	[numeric](18, 4)	,--	累计确认收入（万元）
	ljtxml	[numeric](18, 4)	,--	累计体现毛利（万元）
	ljkpje	[numeric](18, 4)	,--	累计开票金额（万元）
	zmysye	[numeric](18, 4)	,--	账面应收余额（万元）
	ljrzch	[numeric](18, 4)	,--	累计入账存货（万元）
	chye	[numeric](18, 4)	,--	存货余额（万元）
	qzzmch	[numeric](18, 4)	,--	其中：账面存货
	qzyxmgszcxsxcdchye	[numeric](18, 4)	--	其中：已项目公司资产形式形成的存货余额
	time datetime	
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

alter table project_comprehensive_table_pm add customer_name varchar(500)
alter table project_comprehensive_table_pm add project_num varchar(500)


