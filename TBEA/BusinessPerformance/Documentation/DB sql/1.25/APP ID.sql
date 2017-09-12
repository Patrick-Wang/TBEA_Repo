IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'apps')
DROP TABLE apps
CREATE TABLE [dbo].[apps](
	[id] [int] IDENTITY(1,1) NOT NULL,
	appId varchar(100), --appid
	accountId int --用户
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


insert into jygk_account (name, password, role, appId) values ('风控系统', '1234', 0, 1)
insert into apps (appId, accountId) values ('03365721fe543f89032486fd9e90eb42', (select id from jygk_account where name = '风控系统'))
--substring(sys.fn_sqlvarbasetostr(HashBytes('MD5',convert(varchar(100), newid()))),3,32)