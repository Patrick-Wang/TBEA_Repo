/***************************************************************************** 新能源公司制造业项目公司周报表
	开始日期	截止日期	项目公司	产品种类	产品型号	本周生产量	本周发货量	本周销售价格
id start end xmgs cpzl cpxh bzscl bzfhl bzxsjg
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'xnyzb')
DROP TABLE xnyzb
CREATE TABLE [dbo].[xnyzb](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ksrq] [date] NOT NULL,
	[jzrq] [date] NOT NULL,
	[xmgs] [int] NOT NULL,
	[cpzl] [varchar](50),
	[cpxh] [varchar](50),
	[bzscl] [int],
	[bzfhl] [int],
	[bzxsjg] [varchar](50)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/***************************************************************************** 产品种类
	项目公司	产品种类
id	xmgs cpzl
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'xnyzb_cpzl')
DROP TABLE xnyzb_cpzl
CREATE  TABLE [dbo].[xnyzb_cpzl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[xmgs] [varchar](50) NOT NULL,
	[cpzl] [varchar](50) NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[xnyzb_cpzl] ON
INSERT [dbo].[xnyzb_cpzl] ([id], [xmgs], [cpzl])VALUES (0, 903, N'逆变器（单位MW）')
INSERT [dbo].[xnyzb_cpzl] ([id], [xmgs], [cpzl])VALUES (1, 903, N'汇流箱（单位：台）')
INSERT [dbo].[xnyzb_cpzl] ([id], [xmgs], [cpzl])VALUES (2, 903, N'充电桩（单位：台）')
INSERT [dbo].[xnyzb_cpzl] ([id], [xmgs], [cpzl])VALUES (3, 903, N'APF（单位：台）')
INSERT [dbo].[xnyzb_cpzl] ([id], [xmgs], [cpzl])VALUES (4, 905, N'硅片（单位：万片）')
INSERT [dbo].[xnyzb_cpzl] ([id], [xmgs], [cpzl])VALUES (5, 905, N'硅棒（单位：kg）')
INSERT [dbo].[xnyzb_cpzl] ([id], [xmgs], [cpzl])VALUES (6, 911, N'SVG（单位：MVar）')
SET IDENTITY_INSERT [dbo].[identifier_cwgb_sz] OFF      
