--用户不可见指标
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'user_invisible_indicator')
DROP TABLE user_invisible_indicator
CREATE TABLE [dbo].[user_invisible_indicator](
	[id] [int] IDENTITY(1,1) NOT NULL,
	account_id	int	NOT NULL,
	dwid	int not null,
	zbid	int	NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

--用户不可见列
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'user_invisible_column')
DROP TABLE user_invisible_column
CREATE TABLE [dbo].[user_invisible_column](
	[id] [int] IDENTITY(1,1) NOT NULL,
	account_id	int	NOT NULL,
	dwid	int not null,
	col	int	NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

--列名字
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'column_name')
DROP TABLE column_name
CREATE TABLE [dbo].[column_name](
	[id] [int] IDENTITY(1,1) NOT NULL,
	name varchar(100) not null
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
insert into column_name (name) values 
(N'全年计划'),
(N'月度计划'),
(N'月度实际'),
(N'月度计划完成率'),
(N'月度去年同期'),
(N'月度同比增幅'),
(N'季度计划'),
(N'季度累计'),
(N'季度计划完成率'),
(N'季度去年同期'),
(N'季度同比增幅'),
(N'年度累计'),
(N'年度计划完成率'),
(N'年度去年同期'),
(N'年度同比增幅')
