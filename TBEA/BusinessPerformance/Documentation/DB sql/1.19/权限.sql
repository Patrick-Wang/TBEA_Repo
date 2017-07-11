SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (73, '财务税费导入')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (74, '财务税费查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (75, '经营指标完成情况查看')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (76, '经营指标预测情况查看')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF

insert into system_extend_auth (account_id, auth_type) values
((select id from jygk_account where name='539326'), 73),
((select id from jygk_account where name='223203'), 73),
((select id from jygk_account where name='525601'), 73),
((select id from jygk_account where name='356805'), 73),
((select id from jygk_account where name='016396'), 73),
((select id from jygk_account where name='561619'), 73),
((select id from jygk_account where name='820998'), 73),
((select id from jygk_account where name='690939'), 73),
((select id from jygk_account where name='052861'), 73),
((select id from jygk_account where name='395952'), 73),
((select id from jygk_account where name='091662'), 73),
((select id from jygk_account where name='390188'), 73),
((select id from jygk_account where name='085026'), 73),
((select id from jygk_account where name='108612'), 73),

((select id from jygk_account where name='039900'), 74),
((select id from jygk_account where name='528569'), 74),
((select id from jygk_account where name='201986'), 74),
((select id from jygk_account where name='530921'), 74),
((select id from jygk_account where name='156016'), 74),
((select id from jygk_account where name='252369'), 74),
((select id from jygk_account where name='923253'), 74),
((select id from jygk_account where name='818099'), 74),
((select id from jygk_account where name='938952'), 74),
((select id from jygk_account where name='631336'), 74),
((select id from jygk_account where name='185856'), 74),
((select id from jygk_account where name='105308'), 74),
((select id from jygk_account where name='891965'), 74),
((select id from jygk_account where name='092932'), 74),
((select id from jygk_account where name='961513'), 74),
((select id from jygk_account where name='308352'), 74),
((select id from jygk_account where name='018853'), 74),
((select id from jygk_account where name='952220'), 74),
((select id from jygk_account where name='261990'), 74),
((select id from jygk_account where name='902991'), 74),
((select id from jygk_account where name='052616'), 74),
((select id from jygk_account where name='533952'), 74),
((select id from jygk_account where name='929185'), 74),
((select id from jygk_account where name='281909'), 74),
((select id from jygk_account where name='298835'), 74),
((select id from jygk_account where name='650692'), 74),
((select id from jygk_account where name='300619'), 74),
((select id from jygk_account where name='155581'), 74),
((select id from jygk_account where name='002202'), 74),
((select id from jygk_account where name='936203'), 74),
((select id from jygk_account where name='503655'), 74),
((select id from jygk_account where name='923530'), 74),
((select id from jygk_account where name='382351'), 74),
((select id from jygk_account where name='221808'), 74),
((select id from jygk_account where name='135929'), 74),
((select id from jygk_account where name='256118'), 74),
((select id from jygk_account where name='010200'), 74),
((select id from jygk_account where name='519593'), 74),
((select id from jygk_account where name='529601'), 74),
((select id from jygk_account where name='101086'), 74),
((select id from jygk_account where name='658650'), 74),
((select id from jygk_account where name='686931'), 74),
((select id from jygk_account where name='002266'), 74),
((select id from jygk_account where name='188218'), 74),
((select id from jygk_account where name='151291'), 74),
((select id from jygk_account where name='585110'), 74),
((select id from jygk_account where name='595191'), 74),
((select id from jygk_account where name='529611'), 74),
((select id from jygk_account where name='539326'), 74),
((select id from jygk_account where name='820998'), 74),
((select id from jygk_account where name='395952'), 74),


((select id from jygk_account where name='155165'), 59);



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

SET IDENTITY_INSERT [dbo].[tax_auth] ON
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (1, N'疆外', N'特变电工（德阳）电缆股份有限公司', N'039900、528569、201986、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (2, N'疆外', N'特变电工山东鲁能泰山电缆有限公司', N'156016、252369、539326、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (3, N'疆外', N'特变电工衡阳变压器有限公司', N'923253、818099、938952、631336、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (4, N'疆外', N'天津市特变电工变压器有限公司', N'185856、105308、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (5, N'疆外', N'特变电工沈阳变压器集团有限公司', N'891965、092932、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (6, N'昌吉市', N'特变电工股份有限公司新疆线缆厂', N'9961513、308352、018853、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (7, N'昌吉市', N'特变电工新疆电工材料有限公司', N'961513、308352、952220、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (8, N'昌吉市', N'西北电线电缆检测中心有限公司', N'961513、308352、261990、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (9, N'昌吉市', N'新疆中特物流有限公司', N'961513、308352、902991、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (10, N'昌吉市', N'特变电工股份有限公司新疆变压器厂', N'561619、052616、533952、929185、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (11, N'昌吉市', N'特变电工智能电器有限公司', N'561619、052616、533952、929185、281909、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (12, N'昌吉市', N'特变电工超高压电气有限公司', N'561619、052616、533952、929185、298835、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (13, N'昌吉市', N'新疆特变电工国际贸易有限公司', N'561619、052616、533952、929185、650692、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (14, N'昌吉市', N'新疆新特国际物流有限公司', N'561619、052616、533952、929185、650692、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (15, N'昌吉市', N'特变电工股份有限公司', N'820998、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (16, N'昌吉州', N'新疆天池能源有限责任公司', N'603199、300619、155581、002202、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (17, N'昌吉市', N'特变电工国际工程有限公司', N'936203、503655、923530、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (18, N'昌吉市', N'特变电工股份有限公司新疆后勤管理分公司', N'561619、382351、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (19, N'昌吉市', N'新疆特变电工工业文化旅游有限责任公司', N'395952、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (20, N'昌吉市', N'特变电工股份有限公司能源动力分公司）', N'221808、135929、256118、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (21, N'昌吉州', N'新特能源股份有限公司', N'010200、519593、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (22, N'新疆', N'特变电工新疆新能源股份有限公司', N'529601、101086、658650、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (23, N'新疆', N'新疆众和股份有限公司', N'686931、002266、188218、151291、585110、052861、530921')
INSERT [dbo].[tax_auth] ([id], [area], [company], [userName]) VALUES (24, N'新疆', N'新疆特变电工集团有限公司', N'595191、529611、052861、530921')
SET IDENTITY_INSERT [dbo].[tax_auth] OFF