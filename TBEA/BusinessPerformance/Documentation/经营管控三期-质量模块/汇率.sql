/***************************************************************************** 汇率
id nf rate
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'exchange_rate')
DROP TABLE exchange_rate
CREATE TABLE [dbo].[exchange_rate](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[rate] [numeric](18, 4) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[exchange_rate] ON
INSERT [dbo].[exchange_rate] ([id], [nf], [rate])	VALUES (0, 2014, 6)
INSERT [dbo].[exchange_rate] ([id], [nf], [rate])	VALUES (1, 2015, 6)
INSERT [dbo].[exchange_rate] ([id], [nf], [rate])	VALUES (2, 2016, 6)
SET IDENTITY_INSERT [dbo].[exchange_rate] OFF