/***************************************************************************** 产品类别 - 变压器
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cpzlqk_names')
DROP TABLE identifier_cpzlqk_names
CREATE TABLE [dbo].[identifier_cpzlqk_names](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_names] ON
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (0,N'±800kV')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (1,N'±600kV')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (2,N'±500kV')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (3,N'±400kV及以下')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (4,N'1000kV')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (5,N'750kV')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (6,N'500kV')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (7,N'330kV')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (8,N'220kV')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (9,N'66-110kV')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (10,N'35kV')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (11,N'10kV')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (12,N'干变')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (13,N'箱变')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (14,N'干式电抗器')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (15,N'1000k V')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (16,N'750kV')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (17,N'500kV')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (18,N'330kV及以下')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (19,N'隔爆变')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (20,N'整流变')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (21,N'牵引变')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (22,N'油田变')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (23,N'其它')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (24,N'110kV~330kV')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (25,N'35kV及以下配变')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (26,N'换流变')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (27,N'110kV及以上产品')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (28,N'35kV及以下产品')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (29,N'换流变')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (30,N'变压器')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (31,N'干式变压器')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (32,N'电抗器')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (33,N'特种变压器')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (34,N'66kV以上交联电缆（km）')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (35,N'26/35kV及以下交联电力电缆（km）')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (36,N'0.6/1kV及以下交联电力电缆（km）')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (37,N'全塑力缆（km）')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (38,N'架空电缆（km）')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (39,N'控制电缆（km）')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (40,N'特种电缆（km)')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (41,N'布电线（km）')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (42,N'铝绞线（T）')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (43,N'钢芯铝绞线（T）')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (44,N'铝合金导线（T）')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (45,N'一次试验合格率')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (46,N'高压电缆（km）')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (47,N'中低压电缆(km)')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (48,N'导线（t）')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (49,N'合  计')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (50,N'沈变公司')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (51,N'衡变公司')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (52,N'新变厂')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (53,N'天变公司')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (54,N'鲁  缆')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (55,N'新  缆')
INSERT [dbo].[identifier_cpzlqk_names] ([id], [name])VALUES (56,N'德  缆')

SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_names] OFF      
/***************************************************************************** 月度按产品统计结果 - 变压器
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cpzlqk_dwmc')
DROP TABLE cpzlqk_dwmc
CREATE TABLE [dbo].[cpzlqk_dwmc](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[dwid] [int] not NULL,
	[nameid] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[cpzlqk_dwmc] ON
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (0, 1, 50)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (1, 2, 51)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (2, 3, 52)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (3, 301, 53)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (4, 4, 54)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (5, 5, 55)
INSERT [dbo].[cpzlqk_dwmc] ([id], [dwid], [nameid])VALUES (6, 6, 56)
SET IDENTITY_INSERT [dbo].[cpzlqk_dwmc] OFF       
/***************************************************************************** 月度按产品统计结果 - 变压器
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cpzlqk_acptjjg_yd_byq')
DROP TABLE cpzlqk_acptjjg_yd_byq
CREATE TABLE [dbo].[cpzlqk_acptjjg_yd_byq](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cpdl] [int] not NULL,
	[cpxl] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ON
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (0, 29, 0)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (1, 29, 1)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (2, 29, 2)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (3, 29, 3)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (4, 30, 4)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (5, 30, 5)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (6, 30, 6)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (7, 30, 7)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (8, 30, 8)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (9, 30, 9)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (10, 30, 10)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (11, 30, 11)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (12, 31, 12)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (13, 31, 13)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (14, 31, 14)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (15, 32, 15)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (16, 32, 16)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (17, 32, 17)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (18, 32, 18)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (19, 33, 19)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (20, 33, 20)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (21, 33, 21)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (22, 33, 22)
INSERT [dbo].[cpzlqk_acptjjg_yd_byq] ([id], [cpdl], [cpxl])VALUES (23, 33, 23)
SET IDENTITY_INSERT [dbo].[cpzlqk_acptjjg_yd_byq] OFF  
/***************************************************************************** 季度按产品统计结果 - 变压器
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cpzlqk_acptjjg_jd_byq')
DROP TABLE cpzlqk_acptjjg_jd_byq
CREATE TABLE [dbo].[cpzlqk_acptjjg_jd_byq](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cpdl] [int] not NULL,
	[cpxl] [int] not NULL,
	[formul] [varchar](50)  --NULL 跳过，N'this'
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ON
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (0, 29, 29, N'#+(1-5)')
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (1, 29, 0, NULL)
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (2, 29, 1, NULL)
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (3, 29, 2, NULL)
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (4, 29, 3, NULL)
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (5, 30, 4, N'this')
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (6, 30, 5, N'this')
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (7, 30, 6, N'this')
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (8, 30, 24, N'#+(9-11)')
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (9, 30, 7, NULL)
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (10, 30, 8, NULL)
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (11, 30, 9, NULL)
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (12, 30, 25, N'#+(13-15)')
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (13, 30, 10, NULL)
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (14, 30, 11, NULL)
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (15, 30, 12, NULL)
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (16, 32, 15, N'this')
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (17, 32, 16, N'this')
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (18, 32, 17, N'this')
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (19, 32, 18, N'this')
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (20, 33, 33, N'#+(21-25)')
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (21, 33, 19, NULL)
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (22, 33, 20, NULL)
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (23, 33, 21, NULL)
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (24, 33, 22, NULL)
INSERT [dbo].[cpzlqk_acptjjg_jd_byq] ([id], [cpdl], [cpxl], [formul])VALUES (25, 33, 23, NULL)

SET IDENTITY_INSERT [dbo].[cpzlqk_acptjjg_jd_byq] OFF

/***************************************************************************** 按单位统计结果 - 变压器
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cpzlqk_adwtjjg_byq')
DROP TABLE cpzlqk_adwtjjg_byq
CREATE TABLE [dbo].[cpzlqk_adwtjjg_byq](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[dwid] [int],
	[dwmc] [int] not NULL,
	[cpdl] [int] not NULL,
	[cpxl] [int] not NULL,
	[formul] [varchar](50)  --NULL 跳过，N'this'
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[cpzlqk_adwtjjg_byq] ON
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (0, 1, 50, 27, 27,	N'#+(1-10)')
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (1, 1, 50, 27, 0 ,	NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (2, 1, 50, 27, 1 ,	NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (3, 1, 50, 27, 2 ,	NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (4, 1, 50, 27, 3 ,	NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (5, 1, 50, 27, 4 ,	NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (6, 1, 50, 27, 5 ,	NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (7, 1, 50, 27, 6 ,	NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (8, 1, 50, 27, 7 ,	NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (9, 1, 50, 27, 8 ,	NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (10, 1, 50, 27, 9	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (11, 1, 50, 28, 28	,N'#+(12-14)')
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (12, 1, 50, 28, 10	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (13, 1, 50, 28, 11	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (14, 1, 50, 28, 12	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (15, 2, 51, 27, 27	,N'#+(16-25)')
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (16, 2, 51, 27, 0	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (17, 2, 51, 27, 1	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (18, 2, 51, 27, 2	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (19, 2, 51, 27, 3	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (20, 2, 51, 27, 4	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (21, 2, 51, 27, 5	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (22, 2, 51, 27, 6	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (23, 2, 51, 27, 7	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (24, 2, 51, 27, 8	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (25, 2, 51, 27, 9	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (26, 2, 51, 28, 28	,N'#+(27-29)')
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (27, 2, 51, 28, 10	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (28, 2, 51, 28, 11	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (29, 2, 51, 28, 12	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (30, 3, 52, 27, 27	,N'#+(31-40)')
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (31, 3, 52, 27, 0	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (32, 3, 52, 27, 1	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (33, 3, 52, 27, 2	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (34, 3, 52, 27, 3	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (35, 3, 52, 27, 4	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (36, 3, 52, 27, 5	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (37, 3, 52, 27, 6	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (38, 3, 52, 27, 7	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (39, 3, 52, 27, 8	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (40, 3, 52, 27, 9	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (41, 3, 52, 28, 28	,N'#+(42-51)')
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (42, 3, 52, 28, 10	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (43, 3, 52, 28, 11	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (44, 3, 52, 28, 12	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (45, 301, 53, 27, 27	,N'#+(46-55)')
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (46, 301, 53, 27, 0	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (47, 301, 53, 27, 1	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (48, 301, 53, 27, 2	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (49, 301, 53, 27, 3	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (50, 301, 53, 27, 4	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (51, 301, 53, 27, 5	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (52, 301, 53, 27, 6	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (53, 301, 53, 27, 7	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (54, 301, 53, 27, 8	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (55, 301, 53, 27, 9	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (56, 301, 53, 28, 28	,N'#+(57-59)')
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (57, 301, 53, 28, 10	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (58, 301, 53, 28, 11	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (59, 301, 53, 28, 12	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (60, NULL, 49, 27, 27	,N'#0+#15+#30+#45')
INSERT [dbo].[cpzlqk_adwtjjg_byq] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (61, NULL, 49, 28, 28	,N'#11+#26+#41+#56')
SET IDENTITY_INSERT [dbo].[cpzlqk_adwtjjg_byq] OFF

/***************************************************************************** 按产品统计结果 - 线缆
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cpzlqk_acptjjg_xl')
DROP TABLE cpzlqk_acptjjg_xl
CREATE TABLE [dbo].[cpzlqk_acptjjg_xl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cpdl] [int] not NULL,
	[cpxl] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[cpzlqk_acptjjg_xl] ON
INSERT [dbo].[cpzlqk_acptjjg_xl] ([id], [cpdl], [cpxl])VALUES (0, 45, 34)
INSERT [dbo].[cpzlqk_acptjjg_xl] ([id], [cpdl], [cpxl])VALUES (1, 45, 35)
INSERT [dbo].[cpzlqk_acptjjg_xl] ([id], [cpdl], [cpxl])VALUES (2, 45, 36)
INSERT [dbo].[cpzlqk_acptjjg_xl] ([id], [cpdl], [cpxl])VALUES (3, 45, 37)
INSERT [dbo].[cpzlqk_acptjjg_xl] ([id], [cpdl], [cpxl])VALUES (4, 45, 38)
INSERT [dbo].[cpzlqk_acptjjg_xl] ([id], [cpdl], [cpxl])VALUES (5, 45, 39)
INSERT [dbo].[cpzlqk_acptjjg_xl] ([id], [cpdl], [cpxl])VALUES (6, 45, 40)
INSERT [dbo].[cpzlqk_acptjjg_xl] ([id], [cpdl], [cpxl])VALUES (7, 45, 41)
INSERT [dbo].[cpzlqk_acptjjg_xl] ([id], [cpdl], [cpxl])VALUES (8, 45, 42)
INSERT [dbo].[cpzlqk_acptjjg_xl] ([id], [cpdl], [cpxl])VALUES (9, 45, 43)
INSERT [dbo].[cpzlqk_acptjjg_xl] ([id], [cpdl], [cpxl])VALUES (10, 45, 44)
SET IDENTITY_INSERT [dbo].[cpzlqk_acptjjg_xl] OFF  
/***************************************************************************** 按单位统计结果 - 线缆
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cpzlqk_adwtjjg_xl')
DROP TABLE cpzlqk_adwtjjg_xl
CREATE TABLE [dbo].[cpzlqk_adwtjjg_xl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[dwid] [int],
	[dwmc] [int],
	[cpdl] [int] not NULL,
	[cpxl] [int] not NULL,
	[formul] [varchar](50)  --NULL 跳过，N'this'
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
SET IDENTITY_INSERT [dbo].[cpzlqk_adwtjjg_xl] ON
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (0, 4, 54, 46, 34	,N'this')
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (1, 4, 54, 47, 47	,N'#+(2-8)')
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (2, 4, 54, 47, 35	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (3, 4, 54, 47, 36	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (4, 4, 54, 47, 37	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (5, 4, 54, 47, 38	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (6, 4, 54, 47, 39	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (7, 4, 54, 47, 40	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (8, 4, 54, 47, 41	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (9, 4, 54, 48, 48	,N'#+(10-12)')
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (10, 4, 54, 48, 42	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (11, 4, 54, 48, 43	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (12, 4, 54, 48, 44	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (13, 5, 55, 47, 47	,N'#+(14-20)')
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (14, 5, 55, 47, 35	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (15, 5, 55, 47, 36	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (16, 5, 55, 47, 37	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (17, 5, 55, 47, 38	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (18, 5, 55, 47, 39	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (19, 5, 55, 47, 40	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (20, 5, 55, 47, 41	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (21, 5, 55, 48, 48	,N'#+(22-24)')
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (22, 5, 55, 48, 42	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (23, 5, 55, 48, 43	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (24, 5, 55, 48, 44	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (25, 6, 56, 47, 47	,N'#+(26-32)')
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (26, 6, 56, 47, 35	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (27, 6, 56, 47, 36	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (28, 6, 56, 47, 37	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (29, 6, 56, 47, 38	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (30, 6, 56, 47, 39	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (31, 6, 56, 47, 40	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (32, 6, 56, 47, 41	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (33, 6, 56, 48, 48	,N'#+(34-36)')
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (34, 6, 56, 48, 42	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (35, 6, 56, 48, 43	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (36, 6, 56, 48, 44	,NULL)
INSERT [dbo].[cpzlqk_adwtjjg_xl] ([id], [dwid], [dwmc], [cpdl], [cpxl], [formul])VALUES (37, NULL, NULL, 49, 49,	N'#0+#1+#9+#13+#21+#25+#33')
SET IDENTITY_INSERT [dbo].[cpzlqk_adwtjjg_xl] OFF                                                       
/***************************************************************************** 不合格类别 - 变压器      
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cpzlqk_bhglb_byq')
DROP TABLE identifier_cpzlqk_bhglb_byq
CREATE TABLE [dbo].[identifier_cpzlqk_bhglb_byq](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ON
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (0,N'局放超标')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (1,N'介损超标')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (2,N'局部放电')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (3,N'工频击穿')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (4,N'阻抗超标')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (5,N'损耗超标')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (6,N'直阻不平')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (7,N'耐压放电')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (8,N'匝间短路')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (9,N'声级超标')
INSERT [dbo].[identifier_cpzlqk_bhglb_byq] ([id], [name])VALUES (10,N'其他')
SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_bhglb_byq] OFF      

/***************************************************************************** 不合格类别 - 线缆
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cpzlqk_bhglb_xl')
DROP TABLE identifier_cpzlqk_bhglb_xl
CREATE TABLE [dbo].[identifier_cpzlqk_bhglb_xl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_bhglb_xl] ON
INSERT [dbo].[identifier_cpzlqk_bhglb_xl] ([id], [name])VALUES (0,N'外观问题')
INSERT [dbo].[identifier_cpzlqk_bhglb_xl] ([id], [name])VALUES (1,N'结构尺寸')
INSERT [dbo].[identifier_cpzlqk_bhglb_xl] ([id], [name])VALUES (2,N'电性能')
INSERT [dbo].[identifier_cpzlqk_bhglb_xl] ([id], [name])VALUES (3,N'机械性能')
INSERT [dbo].[identifier_cpzlqk_bhglb_xl] ([id], [name])VALUES (4,N'印刷标识问题')
INSERT [dbo].[identifier_cpzlqk_bhglb_xl] ([id], [name])VALUES (5,N'计米长度及重量不足问题')
SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_bhglb_xl] OFF     

/***************************************************************************** 责任类别 - 变压器
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cpzlqk_zrlb_byq')
DROP TABLE identifier_cpzlqk_zrlb_byq
CREATE TABLE [dbo].[identifier_cpzlqk_zrlb_byq](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_zrlb_byq] ON
INSERT [dbo].[identifier_cpzlqk_zrlb_byq] ([id], [name])VALUES (0,N'原材料组配件原因')
INSERT [dbo].[identifier_cpzlqk_zrlb_byq] ([id], [name])VALUES (1,N'设计原因')
INSERT [dbo].[identifier_cpzlqk_zrlb_byq] ([id], [name])VALUES (2,N'过程制造原因')
SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_zrlb_byq] OFF 

/***************************************************************************** 责任类别 - 线缆
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cpzlqk_zrlb_xl')
DROP TABLE identifier_cpzlqk_zrlb_xl
CREATE TABLE [dbo].[identifier_cpzlqk_zrlb_xl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_zrlb_xl] ON
INSERT [dbo].[identifier_cpzlqk_zrlb_xl] ([id], [name])VALUES (0,N'原材料组配件原因')
INSERT [dbo].[identifier_cpzlqk_zrlb_xl] ([id], [name])VALUES (1,N'技术工艺原因')
INSERT [dbo].[identifier_cpzlqk_zrlb_xl] ([id], [name])VALUES (2,N'过程制造原因')
SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_zrlb_xl] OFF


/***************************************************************************** 输变电产品质量情况-统计结果
				不合格数	总数	
id	nf	yf	dwid	bhg	zs	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cpzlqk_zltjjg')
DROP TABLE cpzlqk_zltjjg
CREATE TABLE [dbo].[cpzlqk_zltjjg](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[cpid] [int] not NULL,
	[bhgs] [int],
	[zs] [int],
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] 

/***************************************************************************** 输变电产品质量情况-一次送试不合格问题明细 - 变压器
				产品类型	生产号	产品型号	试验不合格现象	不合格类别	原因分析	处理措施		处理结果		责任类别
id	nf	yf	dwid	cplx	sch		cpxh		sybhgxx			bhglx		yyfx		clcs			cljg			zrlb
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cpzlqk_bhgwtmx_byq')
DROP TABLE cpzlqk_bhgwtmx_byq
CREATE TABLE [dbo].[cpzlqk_bhgwtmx_byq](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[tjfs] [int] not NULL,  --0  --> 110kv 以上   1 --> 配变产品
	[cplx] [varchar](200) not NULL,
	[sch] [varchar](200) not NULL,
	[cpxh] [varchar](200) not NULL,
	[sybhgxx] [varchar](2000),
	[bhglxid] [int] not NULL,
	[yyfx] [varchar](2000),
	[clcs] [varchar](2000),
	[cljg] [varchar](2000),
	[zrlbid] [int] not NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/***************************************************************************** 输变电产品质量情况-不合格问题明细  - 线缆
				产品类型	生产号	产品型号	不合格数量	试验不合格现象	不合格类别	原因分析	处理措施		处理结果		责任类别
id	nf	yf	dwid	cplx	sch		cpxh		bhgsl		sybhgxx			bhglx		yyfx		clcs			cljg			zrlb
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cpzlqk_bhgwtmx_xl')
DROP TABLE cpzlqk_bhgwtmx_xl
CREATE TABLE [dbo].[cpzlqk_bhgwtmx_xl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[cplx] [varchar](200) not NULL,
	[sch] [varchar](200) not NULL,
	[cpxh] [varchar](200) not NULL,
	[bhgsl] [int],
	[sybhgxx] [varchar](2000),
	[bhglxid] [int] not NULL,
	[yyfx] [varchar](2000),
	[clcs] [varchar](2000),
	[cljg] [varchar](2000),
	[zrlbid] [int] not NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]