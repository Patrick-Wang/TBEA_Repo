  /***************************************************************************** 产品名称
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cpmc')
DROP TABLE identifier_cpmc
CREATE TABLE [dbo].[identifier_cpmc](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET IDENTITY_INSERT [dbo].[identifier_cpmc] ON

INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (1 ,N'其它')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (2 ,N'成套项目(国内工程)')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (3 ,N'        输变电-EPC模式')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (4 ,N'        输变电-BT模式')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (5 ,N'        输变电-其它模式')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (6 ,N'成套项目(国际工程)')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (7 ,N'        输变电-EPC模式')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (8 ,N'        输变电-BT模式')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (9 ,N'        输变电-其它模式')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (10 ,N'物流贸易')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (11 ,N'服务类')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (12 ,N'        会议费收入')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (13 ,N'        物业费收入')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (14 ,N'        劳务收入')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (15 ,N'        花苗收入')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (16 ,N'        住宿收入')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (17 ,N'        机票代理收入')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (18 ,N'        日用百货收入')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (19 ,N'        电费收入')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (20 ,N'        水汽暖收入')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (21 ,N'        餐饮收入')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (22 ,N'        服务类其它收入')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (23 ,N'交流变压器')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (24 ,N'        35KV及以下')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (25 ,N'        66-110KV')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (26 ,N'        220KV')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (27 ,N'        330KV')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (28 ,N'        500KV')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (29 ,N'        750kV')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (30 ,N'        1000kV')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (31 ,N'直流变压器')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (32 ,N'        ±400kv及以下')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (33 ,N'        ±500kv')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (34 ,N'        ±600kv')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (35 ,N'        ±800kv')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (36 ,N'        平波电抗器')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (37 ,N'电抗器')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (38 ,N'        330kV及以下')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (39 ,N'        500kV电')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (40 ,N'        750kV电')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (41 ,N'        1000kV电')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (42 ,N'干式变压器')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (43 ,N'        F级干变')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (44 ,N'        H级干变')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (45 ,N'        箱式变电站')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (46 ,N'        干式电抗器')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (47 ,N'特种变压器')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (48 ,N'        隔爆变')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (49 ,N'        整流变')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (50 ,N'        牵引变')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (51 ,N'        油田变')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (52 ,N'        特种变压器其它')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (53 ,N'产业链延伸类')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (54 ,N'        配网自动化')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (55 ,N'        开关')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (56 ,N'        套管')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (57 ,N'        互感器')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (58 ,N'        维修备件')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (85 ,N'        产业链延伸类其它')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (59 ,N'导线')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (60 ,N'布电线')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (61 ,N'架空线')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (62 ,N'控制电缆')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (63 ,N'交联电缆')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (64 ,N'电力电缆')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (65 ,N'电磁线')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (66 ,N'特种电缆')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (67 ,N'电缆附件')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (68 ,N'铜杆')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (69 ,N'铝杆')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (70 ,N'PVC料')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (71 ,N'工装轮')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (72 ,N'        非晶合金变')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (73 ,N'        卷铁芯')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (74 ,N'特殊产品')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (75 ,N'国内工程')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (76 ,N'国际工程')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (77 ,N'配电变压器')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (78 ,N'        非晶合金')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (79 ,N'        卷铁芯')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (80 ,N'        叠铁芯')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (86 ,N'        配电变压器其它')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (81 ,N'箱式变电站')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (82 ,N'        欧式变电站')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (83 ,N'        美式变电站')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (84 ,N'        华式变电站')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (87 ,N'        箱式变电站其它')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (88 ,N'集成服务业务')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (89 ,N'其中：国内成套')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (90 ,N'        国际成套')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (91 ,N'        检测修试')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (92 ,N'        国内成套其它')
INSERT [dbo].[identifier_cpmc] ([id], [name]) VALUES (93 ,N'其它')



SET IDENTITY_INSERT [dbo].[identifier_cpmc] OFF


 /***************************************************************************** 材料名称
id name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_clmc')
DROP TABLE identifier_clmc
CREATE TABLE [dbo].[identifier_clmc](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[identifier_clmc] ON
INSERT [dbo].[identifier_clmc] ([id], [name]) VALUES (1,N'铜')
INSERT [dbo].[identifier_clmc] ([id], [name]) VALUES (2,N'铝')
INSERT [dbo].[identifier_clmc] ([id], [name]) VALUES (3,N'交联料')
INSERT [dbo].[identifier_clmc] ([id], [name]) VALUES (4,N'PVC料')
INSERT [dbo].[identifier_clmc] ([id], [name]) VALUES (5,N'钢带')
INSERT [dbo].[identifier_clmc] ([id], [name]) VALUES (6,N'钢丝')
INSERT [dbo].[identifier_clmc] ([id], [name]) VALUES (7,N'橡胶')
INSERT [dbo].[identifier_clmc] ([id], [name]) VALUES (8,N'硅钢片')
INSERT [dbo].[identifier_clmc] ([id], [name]) VALUES (9,N'铜线')
INSERT [dbo].[identifier_clmc] ([id], [name]) VALUES (10,N'纸板')
INSERT [dbo].[identifier_clmc] ([id], [name]) VALUES (11,N'钢板')
INSERT [dbo].[identifier_clmc] ([id], [name]) VALUES (12,N'变压器油')
SET IDENTITY_INSERT [dbo].[identifier_clmc] OFF
