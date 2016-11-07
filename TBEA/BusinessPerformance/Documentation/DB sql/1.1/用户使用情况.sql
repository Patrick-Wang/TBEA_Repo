IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'user_usage')
DROP TABLE user_usage
CREATE TABLE [dbo].[user_usage](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userName] [varchar](50) not NULL,
	[userId] int,
	[ip]  [varchar](50),
	[loginTime] datetime,
	[logoutTime] datetime,
	[lastAccessedTime] datetime
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]