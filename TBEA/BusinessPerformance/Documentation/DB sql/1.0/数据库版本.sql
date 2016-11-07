/***************************************************************************** 数据库版本

*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'db_version')
DROP TABLE db_version
CREATE TABLE [dbo].[db_version](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[version] [varchar](50) not NULL,
	[deployTime] datetime not NULL,
	[instruction] [varchar](500)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


INSERT [dbo].[db_version] ([version], [deployTime])	VALUES (N'1.0', GETDATE())