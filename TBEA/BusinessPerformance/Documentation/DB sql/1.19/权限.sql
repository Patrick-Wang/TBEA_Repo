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

INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (1, N'疆外', N'特变电工（德阳）电缆股份有限公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (2, N'疆外', N'特变电工山东鲁能泰山电缆有限公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (3, N'疆外', N'特变电工衡阳变压器有限公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (4, N'疆外', N'天津市特变电工变压器有限公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (5, N'疆外', N'特变电工沈阳变压器集团有限公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (6, N'昌吉市', N'特变电工股份有限公司新疆线缆厂本部', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (7, N'昌吉市', N'特变电工新疆电工材料有限公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (8, N'昌吉市', N'西北电线电缆检测中心有限公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (9, N'昌吉市', N'新疆中特物流有限公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (10, N'昌吉市', N'特变电工股份有限公司新疆变压器厂本部', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (11, N'昌吉市', N'特变电工智能电器有限公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (12, N'昌吉市', N'特变电工超高压电气有限公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (13, N'昌吉市', N'新疆特变电工国际贸易有限公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (14, N'昌吉市', N'新疆新特国际物流有限公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (15, N'昌吉市', N'特变电工股份有限公司（机关）', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (16, N'昌吉州', N'新疆天池能源有限责任公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (17, N'昌吉市', N'特变电工国际工程有限公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (18, N'昌吉市', N'特变电工股份有限公司新疆后勤管理分公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (19, N'昌吉市', N'新疆特变电工工业文化旅游有限责任公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (20, N'昌吉市', N'特变电工股份有限公司能源动力分公司）', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (21, N'昌吉州', N'新特能源股份有限公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (22, N'新疆', N'特变电工新疆新能源股份有限公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (23, N'新疆', N'新疆众和股份有限公司', N'130182')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (24, N'新疆', N'新疆特变电工集团有限公司', N'130182')