
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'stock_report_code')
DROP TABLE stock_report_code
CREATE TABLE [dbo].[stock_report_code](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] varchar(50) not NULL,
	[name] nvarchar(100) not NULL,
	[startUsing] int not null
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT [dbo].[stock_report_code] ([code], [name], [startUsing])VALUES (N'zxcwzb',N'最新财务指标',0)
INSERT [dbo].[stock_report_code] ([code], [name], [startUsing])VALUES (N'cwbl',N'财务比率表',0)
INSERT [dbo].[stock_report_code] ([code], [name], [startUsing])VALUES (N'zcfz',N'资产负债表',1)
INSERT [dbo].[stock_report_code] ([code], [name], [startUsing])VALUES (N'lr',N'利润表',1)
INSERT [dbo].[stock_report_code] ([code], [name], [startUsing])VALUES (N'xjll',N'现金流量表',1)
INSERT [dbo].[stock_report_code] ([code], [name], [startUsing])VALUES (N'zcjz',N'资产减值表',0)
INSERT [dbo].[stock_report_code] ([code], [name], [startUsing])VALUES (N'yszk',N'应收账款表',0)
INSERT [dbo].[stock_report_code] ([code], [name], [startUsing])VALUES (N'qtyszk',N'其他应收账款表',0)
INSERT [dbo].[stock_report_code] ([code], [name], [startUsing])VALUES (N'zysrfb',N'主营收入分布',0)          