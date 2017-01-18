alter table user_usage add extracted datetime null
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'user_usage_ex')
DROP TABLE user_usage_ex
CREATE TABLE [dbo].[user_usage_ex](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[usageid] [int] NOT NULL,
	[url] [varchar](100),
	[requestTime] datetime,
	[isAjax]  [varchar](10)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]