/***************************************************************************** 新能源公司制造业项目公司周报表
	开始日期	截止日期	项目公司	项目名称	客户名称	合同金额	应收账款金额	逾期款金额	逾期款时间（月）
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'yszkhkzb')
DROP TABLE yszkhkzb
CREATE TABLE [dbo].[yszkhkzb](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ksrq] [date] NOT NULL,
	[jzrq] [date] NOT NULL,
	[xmgs] [int] NOT NULL,
	[xmmc] [varchar](100),
	[khmc] [varchar](50),
	[htje] [numeric](18, 4),
	[yszkje] [numeric](18, 4),
	[yqkje] [numeric](18, 4),
	[yqksj] [int]
	[zt] [int] NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
