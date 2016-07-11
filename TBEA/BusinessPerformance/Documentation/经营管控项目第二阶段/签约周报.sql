/***************************************************************************** 新能源公司制造业项目公司周报表
	开始日期	截止日期	项目公司	客户名称	产品名称	签订日期	签约量	签约单价	签约金额
id start end xmgs cpzl cpxh bzscl bzfhl bzxsjg
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'xny_qyzb')
DROP TABLE xnyzb
CREATE TABLE [dbo].[xnyzb](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ksrq] [date] NOT NULL,
	[jzrq] [date] NOT NULL,
	[xmgs] [int] NOT NULL,
	[khmc] [varchar](50),
	[cpmc] [varchar](50),
	[ddrq] [date],
	[qyl] [numeric](18, 4),
	[qydj] [numeric](18, 4),
	[qyje] [numeric](18, 4),
	[zt] [int] NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
