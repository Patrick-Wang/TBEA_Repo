SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (73, '财务税费导入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (74, '财务税费查看')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF

insert into system_extend_auth (account_id, auth_type) values
((select id from jygk_account where name='130182'), 73),
((select id from jygk_account where name='130182'), 74)


IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'tax_auth')
DROP TABLE tax_auth
CREATE TABLE [dbo].[tax_auth](	
	[id] 	[int] IDENTITY(1,1) NOT NULL	,		
	area varchar(50),
	company	varchar(100)	,--	"公司名称（请严格参照公司名称对照表）"
	userName	varchar(1000)	--	"用户名"
PRIMARY KEY CLUSTERED 			
(			
	[id] ASC		
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]			
) ON [PRIMARY]	