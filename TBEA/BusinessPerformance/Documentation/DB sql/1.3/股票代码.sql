
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'stock_code')
DROP TABLE stock_code
CREATE TABLE [dbo].[stock_code](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] varchar(50) not NULL,
	[name] nvarchar(100) not NULL,
	[startUsing] int not null
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'000400',N'许继电气',1)
INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'601126',N'四方股份',1)
INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'600550',N'保变电气',1)
INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'600312',N'平高电气',1)
INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'601179',N'中国西电',1)
INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'600406',N'国电南瑞',1)
INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'002028',N'思源电气',1)
INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'002276',N'万马股份',1)
INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'002471',N'中超控股',1)
INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'002498',N'汉缆股份',1)
INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'002300',N'太阳电缆',1)
INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'002533',N'金杯电工',1)
INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'002560',N'通达股份',1)
INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'002212',N'南洋股份',1)
INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'600869',N'智慧能源',1)
INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'600973',N'宝胜股份',1)
INSERT [dbo].[stock_code] ([code], [name], [startUsing])VALUES (N'600522',N'中天科技',1)              