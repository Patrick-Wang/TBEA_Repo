/***************************************************************************** 税种
id	name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cwgb_sz')
DROP TABLE identifier_cwgb_sz
CREATE TABLE [dbo].[identifier_cwgb_sz](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET IDENTITY_INSERT [dbo].[identifier_cwgb_sz] ON
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (0,N'增值税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (1,N'消费税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (2,N'营业税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (3,N'城建税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (4,N'教育费附加(包括地方教育费附加)')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (5,N'企业所得税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (6,N'土地使用税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (7,N'土地增值税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (8,N'车船使用税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (9,N'房产税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (10,N'印花税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (11,N'个人所得税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (12,N'资源税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (13,N'关税')
INSERT [dbo].[identifier_cwgb_sz] ([id], [name])VALUES (14,N'其他税费')
SET IDENTITY_INSERT [dbo].[identifier_cwgb_sz] OFF      

/***************************************************************************** 产业
id name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cwgb_cy')
DROP TABLE identifier_cwgb_cy
CREATE TABLE [dbo].[identifier_cwgb_cy](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
SET IDENTITY_INSERT [dbo].[identifier_cwgb_cy] ON
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (0,	N'变压器行业(按产品电压等级分类)')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (1,	N'变压器行业（按产品类型分类）')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (2,	N'线缆行业')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (3,	N'新能源行业-硅片')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (4,	N'新能源行业-西科')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (5,	N'新能源行业-柔输')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (6,	N'新能源行业-硅业')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (7,	N'新能源行业')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (8,	N'工程分类')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (9,	N'运营商类')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (10,	N'煤炭产业')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (11,	N'物流贸易类')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (12,	N'服务类')
INSERT [dbo].[identifier_cwgb_cy] ([id], [name])VALUES (13,	N'合计')
SET IDENTITY_INSERT [dbo].[identifier_cwgb_cy] OFF

/***************************************************************************** 产品分类
id cy name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cwgb_cy_cpfl')
DROP TABLE cwgb_cy_cpfl
CREATE TABLE [dbo].[cwgb_cy_cpfl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cy] [int] not NULL,
	[name] [varchar](50) not NULL,
	[type] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
SET IDENTITY_INSERT [dbo].[cwgb_cy_cpfl] ON
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (0,	0,	N'交流变压器'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (1,	0,	N'其中：35KV及以下'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (2,	0,	N'66-110KV'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (3,	0,	N'220KV'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (4,	0,	N'330KV'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (5,	0,	N'500KV'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (6,	0,	N'750kV'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (7,	0,	N'1000kV'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (8,	0,	N'直流变压器'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (9,	0,	N'其中：±400kv及以下'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (10,	0,	N'±500kv'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (11,	0,	N'±600kv'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (12,	0,	N'±800kv'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (13,	0,	N'平波电抗器'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (14,	0,	N'电抗器'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (15,	0,	N'其中：330kV及以下'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (16,	0,	N'500kV电'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (17,	0,	N'750kV电'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (18,	0,	N'1000kV电'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (19,	0,	N'小计'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (20,	1,	N'干式变压器'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (21,	1,	N'其中：F级干变'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (22,	1,	N'H级干变'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (23,	1,	N'箱式变电站'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (24,	1,	N'干式电抗器'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (25,	1,	N'特种变压器'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (26,	1,	N'其中：隔爆变'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (27,	1,	N'整流变'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (28,	1,	N'牵引变'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (29,	1,	N'油田变'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (30,	1,	N'其它'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (31,	1,	N'延伸类'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (32,	1,	N'配电自动化'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (33,	1,	N'开关'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (34,	1,	N'套管'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (35,	1,	N'互感器'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (36,	1,	N'维修备件'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (37,	1,	N'小计'	,	1)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (38,	2,	N'导线'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (39,	2,	N'布电线'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (40,	2,	N'架空线'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (41,	2,	N'控制电缆'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (42,	2,	N'交联电缆'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (43,	2,	N'电力电缆'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (44,	2,	N'电磁线'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (45,	2,	N'特种电缆'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (46,	2,	N'电缆附件'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (47,	2,	N'铜杆'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (48,	2,	N'铝杆'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (49,	2,	N'PVC料'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (50,	2,	N'工装轮'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (51,	2,	N'其他'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (52,	2,	N'小计'	,	1)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (53,	3,	N'单晶硅片'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (54,	3,	N'多晶硅片'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (55,	3,	N'准方棒'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (56,	3,	N'多晶锭'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (57,	3,	N'圆棒'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (58,	4,	N'并网逆变器'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (59,	4,	N'汇流箱'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (60,	4,	N'配电柜'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (61,	4,	N'一体化机房'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (62,	4,	N'充电桩产品'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (63,	4,	N'APF'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (64,	5,	N'SVG'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (65,	5,	N'SVG半成品'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (66,	6,	N'多晶硅'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (67,	6,	N'白炭黑'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (68,	6,	N'加气块'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (69,	7,	N'其他'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (70,	7,	N'小计'	,	1)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (71,	8,	N'国内工程'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (72,	8,	N'其中：输变电工程'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (73,	8,	N'        输变电-EPC模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (74,	8,	N'        输变电-BT模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (75,	8,	N'        输变电-其他模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (76,	8,	N'      光伏工程'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (77,	8,	N'        光伏-EPC模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (78,	8,	N'        光伏-BT模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (79,	8,	N'        光伏-其他模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (80,	8,	N'      风电工程'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (81,	8,	N'        风电-EPC模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (82,	8,	N'        风电-BT模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (83,	8,	N'        风电-其他模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (84,	8,	N'国际工程'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (85,	8,	N'其中：输变电工程'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (86,	8,	N'        输变电-EPC模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (87,	8,	N'        输变电-BT模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (88,	8,	N'        输变电-其他模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (89,	8,	N'      光伏工程'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (90,	8,	N'        光伏-EPC模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (91,	8,	N'        光伏-BT模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (92,	8,	N'        光伏-其他模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (93,	8,	N'      风电工程'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (94,	8,	N'        风电-EPC模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (95,	8,	N'        风电-BT模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (96,	8,	N'        风电-其他模式'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (97,	8,	N'小计'	,	1)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (98,	9,	N'自备电厂'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (99,	9,	N'火电电厂'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (100,	9,	N'光伏电厂'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (101,	9,	N'风能电厂'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (102,	9,	N'充电站'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (103,	9,	N'售电公司'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (104,	9,	N'供热公司'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (105,	9,	N'其他'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (106,	9,	N'小计'	,	1)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (107,	10,	N'原煤'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (108,	10,	N'大块'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (109,	10,	N'中块'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (110,	10,	N'小中块'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (111,	10,	N'三八块'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (112,	10,	N'二五块'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (113,	10,	N'四六块'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (114,	10,	N'锯采煤'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (115,	10,	N'末煤'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (116,	10,	N'其他'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (117,	10,	N'小计'	,	1)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (118,	11,	N'物流贸易'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (119,	11,	N'小计'	,	1)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (120,	12,	N'会议费收入'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (121,	12,	N'物业费收入'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (122,	12,	N'劳务收入'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (123,	12,	N'花苗收入'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (124,	12,	N'住宿收入'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (125,	12,	N'机票代理收入'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (126,	12,	N'日用百货收入'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (127,	12,	N'电费收入'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (128,	12,	N'水汽暖收入'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (129,	12,	N'餐饮收入'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (130,	12,	N'其他收入'	,	0)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (131,	12,	N'小计'	,	1)
INSERT [dbo].[cwgb_cy_cpfl] ([id], [cy], [name], [type])VALUES (132,	13,	N'合计'	,	2)
SET IDENTITY_INSERT [dbo].[cwgb_cy_cpfl] OFF
/***************************************************************************** 科目
id	name
*****************************************************************************/

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_cwgb_km')
DROP TABLE identifier_cwgb_km
CREATE TABLE [dbo].[identifier_cwgb_km](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET IDENTITY_INSERT [dbo].[identifier_cwgb_km] ON
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (0,N'销售商品、提供劳务收到的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (1,N'收到的税费返还')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (2,N'收到的其他与经营活动有关的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (3,N'其中：罚款所收到的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (4,N'其中：政府补助所收到的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (5,N'其中：收到本单位向外投标退回所收到的投标保证金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (6,N'其中：收到外单位投标保证金所收到的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (7,N'其中：日常业务借支退回所收到的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (8,N'其中：银行存款利息所收到到的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (9,N'其中：收到的其他与经营活动有关的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (10,N'		现金流入小计')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (11,N'购买商品、接受劳务所支付的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (12,N'支付给职工以及为职工支付的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (13,N'支付的各项税费')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (14,N'支付的其他与经营活动有关的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (15,N'其中：本单位向外投标所支付的投标保证金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (16,N'其中：退付外单位投标保证金所支付的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (17,N'其中：代理咨询费所支付的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (18,N'其中：中标服务费所支付的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (19,N'其中：日常业务借支所支付的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (20,N'其中：银行相关业务手续费所支付的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (21,N'其中：支付的其他与经营活动有关的现金')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (22,N'		现金流出小计')
INSERT [dbo].[identifier_cwgb_km] ([id], [name])VALUES (23,N'	经营活动产生的现金流量净额')
SET IDENTITY_INSERT [dbo].[identifier_cwgb_km] OFF
/***************************************************************************** 应交税费
	税种	应交数	已交数	未交数	累积应交	累计已交	累计未交	 期末数
yf	sz	yjs	yijs	wjs	ljyj	ljyij	ljwj	qms
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cwgb_yjsf')
DROP TABLE cwgb_yjsf
CREATE TABLE [dbo].[cwgb_yjsf](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[sz] [int] not NULL,
	[yjs] [numeric](18, 4) NULL,
	[yijs] [numeric](18, 4) NULL,
	[wjs] [numeric](18, 4) NULL,
	[ljyj] [numeric](18, 4) NULL,
	[ljyij] [numeric](18, 4) NULL,
	[ljwj] [numeric](18, 4) NULL,
	[qms] [numeric](18, 4) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/***************************************************************************** 年度期初数
	年		期初数
id	nf	sz	qcs
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cwgb_ndqcs')
DROP TABLE cwgb_ndqcs
CREATE TABLE [dbo].[cwgb_ndqcs](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[dwid] [int] not NULL,
	[sz] [int] not NULL,
	[qcs] [numeric](18, 4) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/***************************************************************************** 产品大类毛利表
			产品大类	累计收入	累计成本	期货配比情况	去年全年收入	去年全年成本
id	nf	yf	cpdl	ljsr	ljcb	qhpbqk	qnqnsr	qnqncb
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cwgb_cpdlml')
DROP TABLE cwgb_cpdlml
CREATE TABLE [dbo].[cwgb_cpdlml](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[cpdl] [int] not NULL,
	[ljsr] [numeric](18, 4) NULL,
	[ljcb] [numeric](18, 4) NULL,
	[qnqnsr] [numeric](18, 4) NULL,
	[qnqncb] [numeric](18, 4) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/***************************************************************************** 经营性现金流 计划值表
			科目	计划值	状态
id	nf	yf	km	jhz	zt

*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cwgb_jyxxjl_jh')
DROP TABLE cwgb_jyxxjl_jh
CREATE TABLE [dbo].[cwgb_jyxxjl_jh](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[km] [int] not NULL,
	[jhz] [numeric](18, 4) NULL,
	[zt] [int] NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
/***************************************************************************** 经营性现金流 实际值表
			科目	实际值	本年累计
id	nf	yf	km	sjz	ndlj
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cwgb_jyxxjl_sj')
DROP TABLE cwgb_jyxxjl_sj
CREATE TABLE [dbo].[cwgb_jyxxjl_sj](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[km] [int] not NULL,
	[sjz] [numeric](18, 4) NULL,
	[ndlj] [numeric](18, 4) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/***************************************************************************** 经营性现金流
			科目	计划值	状态
id	nf	yf	km	jhz	zt

*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cwgb_jyxxjl')
DROP TABLE cwgb_jyxxjl
CREATE TABLE [dbo].[cwgb_jyxxjl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[km] [int] not NULL,
	[jhz] [numeric](18, 4) ,
	[sjz] [numeric](18, 4) ,
	[ndlj] [numeric](18, 4) ,	
	[zt] [int] NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]