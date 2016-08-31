/***************************************************************************** zl_wbyclzlwt
"公司名称
"	"问题发生时间
"	"产品类型
"	"生产号
"	"产品型号
"	"一级问题类型
"	"二级问题类型
"	"质量故障现象
"	"问题详细描述
"	"原材料数量
"	"计量单
"	"供应商名称
"	"责任部门
"	"填报人
"	"产品发货日期
"	"故障主体名称
"	原材料问题处理措施	"现场处理措施
"	"现场处理结果
"	"用户单位名称
"	"本单位现场售后人员
 "	本单位现场售后人员电话
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'zl_wbyclzlwt')
DROP TABLE zl_wbyclzlwt
CREATE TABLE [dbo].[zl_wbyclzlwt](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[company_name] [varchar](50),
	[issue_happen_date] date,
	[product_type] [varchar](50) ,
	[production_num] [varchar](50) ,
	[production_model] [varchar](50) ,
	[issue_type] [varchar](50) ,
	[sub_issue_type] [varchar](50) ,
	[quality_phenomenon] [varchar](50) ,
	[detail] [varchar](50)  ,
	[material_count] [numeric](18, 4),
	[measurement_units] [varchar](50) ,
	[suppier] [varchar](50),
	[responsibility_department] [varchar](50) ,
	[filling_personnel] [varchar](50) ,
	[product_delivery_date] date,
	[failure_subject] [varchar](50) ,
	[material_treatment_measure] [varchar](50) ,
	[onsite_treatment_measure] [varchar](500) ,
	[onsite_treatment_result] [varchar](500) ,
	[user_unit] [varchar](50) ,
	[onsite_after_sales] [varchar](50) ,
	[after_sales_tel] [varchar](50),
	[source] [varchar](50),
	[time] [datetime]
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/***************************************************************************** zl_nbyclzlwt
"公司名称

"	问题发生时间	
"产品类型
"	"生产号
"	产品型号	
一级问题类型	
二级问题类型	
"原材料质量故障现象
"	问题详细描述	
原材料问题发生阶段	
原材料数量	
计量单位	
供应商名称	
问题所在工序	
责任部门	
原材料问题处理措施	
现场处理措施	
"现场处理结果"	
原因分析	
考核情况	
填报人
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'zl_nbyclzlwt')
DROP TABLE zl_nbyclzlwt
CREATE TABLE [dbo].[zl_nbyclzlwt](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[company_name] [varchar](50),
	[issue_happen_date] date,
	[product_type] [varchar](50) ,
	[production_num] [varchar](50) ,
	[production_model] [varchar](50) ,
	[issue_type] [varchar](50) ,
	[sub_issue_type] [varchar](50) ,
	[material_quality_phenomenon] [varchar](50) ,
	[detail] [varchar](50)  ,
	[material_happen_phase] [varchar](50) ,
	[material_count] [numeric](18, 4),
	[measurement_units] [varchar](50) ,
	[suppier] [varchar](50),
	[issue_process] [varchar](50) ,
	[responsibility_department] [varchar](50) ,
	[material_treatment_measure] [varchar](50) ,
	[onsite_treatmen_measure] [varchar](500) ,
	[onsite_treatment_result] [varchar](500) ,
	[causa_analysis] [varchar](500) ,
	[assessment] [varchar](500),
	[filling_personnel] [varchar](50),
	[source] [varchar](50),
	[time] [datetime]
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/*****

/***************************************************************************** zl_yclzlwt_cplx_byq
产品类型
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'zl_yclzlwt_cplx_byq')
DROP TABLE zl_yclzlwt_cplx_byq
CREATE TABLE [dbo].[zl_yclzlwt_cplx_byq](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[product_type] [varchar](50)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[zl_yclzlwt_cplx_byq] ON
INSERT [dbo].[zl_yclzlwt_cplx_byq] ([id], [product_type])VALUES (0, N'换流变')
INSERT [dbo].[zl_yclzlwt_cplx_byq] ([id], [product_type])VALUES (1, N'交流变压器')
INSERT [dbo].[zl_yclzlwt_cplx_byq] ([id], [product_type])VALUES (2, N'电抗器')
INSERT [dbo].[zl_yclzlwt_cplx_byq] ([id], [product_type])VALUES (3, N'油浸式变压器')
INSERT [dbo].[zl_yclzlwt_cplx_byq] ([id], [product_type])VALUES (4, N'干式变压器')
INSERT [dbo].[zl_yclzlwt_cplx_byq] ([id], [product_type])VALUES (5, N'其它变压器(变压器装填)')
SET IDENTITY_INSERT [dbo].[zl_yclzlwt_cplx_byq] OFF     

/***************************************************************************** zl_yclzlwt_cplx_xl
产品类型
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'zl_yclzlwt_cplx_xl')
DROP TABLE zl_yclzlwt_cplx_xl
CREATE TABLE [dbo].[zl_yclzlwt_cplx_xl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[product_type] [varchar](50)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[zl_yclzlwt_cplx_xl] ON
INSERT [dbo].[zl_yclzlwt_cplx_xl] ([id], [product_type])VALUES (0, N'66kV以上交联电缆（km）')
INSERT [dbo].[zl_yclzlwt_cplx_xl] ([id], [product_type])VALUES (1, N'26/35kV及以下交联电力电缆（km）')
INSERT [dbo].[zl_yclzlwt_cplx_xl] ([id], [product_type])VALUES (2, N'0.6/1kV及以下交联电力电缆（km）')
INSERT [dbo].[zl_yclzlwt_cplx_xl] ([id], [product_type])VALUES (3, N'全塑力缆（km）')
INSERT [dbo].[zl_yclzlwt_cplx_xl] ([id], [product_type])VALUES (4, N'架空电缆（km）')
INSERT [dbo].[zl_yclzlwt_cplx_xl] ([id], [product_type])VALUES (5, N'控制电缆（km）')
INSERT [dbo].[zl_yclzlwt_cplx_xl] ([id], [product_type])VALUES (6 N'特种电缆（km)')
INSERT [dbo].[zl_yclzlwt_cplx_xl] ([id], [product_type])VALUES (7, N'布电线（km）')
INSERT [dbo].[zl_yclzlwt_cplx_xl] ([id], [product_type])VALUES (8, N'铝绞线（T）')
INSERT [dbo].[zl_yclzlwt_cplx_xl] ([id], [product_type])VALUES (9, N'钢芯铝绞线（T）')
INSERT [dbo].[zl_yclzlwt_cplx_xl] ([id], [product_type])VALUES (10, N'铝合金导线（T）')
INSERT [dbo].[zl_yclzlwt_cplx_xl] ([id], [product_type])VALUES (11, N'其它线缆(线缆装填)')
SET IDENTITY_INSERT [dbo].[zl_yclzlwt_cplx_xl] OFF


/***************************************************************************** zl_yclzlwt_wtlx_byq 变压器问题类型
产品类型
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'zl_yclzlwt_wtlx_byq')
DROP TABLE zl_yclzlwt_wtlx_byq
CREATE TABLE [dbo].[zl_yclzlwt_wtlx_byq](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[issue_type] [varchar](50),
	[parent] [int]
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[zl_yclzlwt_wtlx_byq] ON
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (0, N'设计质量问题', null)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (1, N'设计问题', 0)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (2, N'原材料质量问题', null)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (3, N'生产制造质量问题', null)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (4, N'绝缘问题', 3)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (5, N'铁芯问题', 3)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (6, N'线圈问题', 3)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (7, N'油箱问题', 3)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (8, N'装配问题', 3)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (9, N'后段质量问题', null)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (10, N'缺少及错发材料', 9)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (11, N'运输问题', 9)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (12, N'安装问题', 9)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (13, N'试验问题', 9)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (14, N'外协问题', null)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (15, N'外协问题', 14)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (16, N'用户问题', null)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (17, N'用户问题', 16)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (18, N'渗漏问题', null)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (19, N'渗漏问题', 18)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (20, N'气体问题', null)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (21, N'气体问题', 20)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (22, N'其它问题', null)
INSERT [dbo].[zl_yclzlwt_wtlx_byq] ([id], [issue_type], [parent])VALUES (23, N'其它', 22)
SET IDENTITY_INSERT [dbo].[zl_yclzlwt_wtlx_byq] OFF


/***************************************************************************** zl_yclzlwt_wtlx_xl 线缆问题类型

产品类型
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'zl_yclzlwt_wtlx_xl')
DROP TABLE zl_yclzlwt_wtlx_xl
CREATE TABLE [dbo].[zl_yclzlwt_wtlx_xl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[issue_type] [varchar](50),
	[parent] [int]
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[zl_yclzlwt_wtlx_xl] ON
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (0, N'工艺质量问题', null)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (1, N'工艺-外观', 0)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (2, N'工艺-结构尺寸', 0)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (3, N'工艺-电性能', 0)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (4, N'工艺-机械性能', 0)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (5, N'工艺-计米长度及重量不足问题', 0)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (6, N'工艺-印刷标识问题', 0)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (7, N'工艺-其它问题', 0)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (8, N'原材料质量问题', null)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (9, N'生产制造质量问题', null)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (10, N'生产制造-外观', 9)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (11, N'生产制造-结构尺寸', 9)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (12, N'生产制造-电性能', 9)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (13, N'生产制造-机械性能', 9)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (14, N'生产制造-计米长度及重量不足问题', 9)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (15, N'生产制造-印刷标识问题', 9)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (16, N'生产制造-其它问题', 9)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (17, N'运输装卸及施工设施', null)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (18, N'运输装卸及施工设施', 17)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (19, N'其它线缆问题', null)
INSERT [dbo].[zl_yclzlwt_wtlx_xl] ([id], [issue_type], [parent])VALUES (20, N'其它线缆问题', 19)
SET IDENTITY_INSERT [dbo].[zl_yclzlwt_wtlx_xl] OFF


/***************************************************************************** zl_yclzlwt_zlgzxx 质量故障现象
产品类型
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'zl_yclzlwt_zlgzxx')
DROP TABLE zl_yclzlwt_zlgzxx
CREATE TABLE [dbo].[zl_yclzlwt_zlgzxx](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[quality_phenomenon] [varchar](50)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[zl_yclzlwt_zlgzxx] ON
INSERT [dbo].[zl_yclzlwt_zlgzxx] ([id], [quality_phenomenon])VALUES (0, N'包装问题')
INSERT [dbo].[zl_yclzlwt_zlgzxx] ([id], [quality_phenomenon])VALUES (1, N'外观问题')
INSERT [dbo].[zl_yclzlwt_zlgzxx] ([id], [quality_phenomenon])VALUES (2, N'尺寸匹配问题')
INSERT [dbo].[zl_yclzlwt_zlgzxx] ([id], [quality_phenomenon])VALUES (3, N'性能问题')
SET IDENTITY_INSERT [dbo].[zl_yclzlwt_zlgzxx] OFF

/***************************************************************************** zl_yclzlwt_fsjd 质量问题发生阶段
产品类型
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'zl_yclzlwt_fsjd')
DROP TABLE zl_yclzlwt_fsjd
CREATE TABLE [dbo].[zl_yclzlwt_fsjd](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[material_happen_phase] [varchar](50)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

SET IDENTITY_INSERT [dbo].[zl_yclzlwt_fsjd] ON
INSERT [dbo].[zl_yclzlwt_fsjd] ([id], [material_happen_phase])VALUES (0, N'入场检测')
INSERT [dbo].[zl_yclzlwt_fsjd] ([id], [material_happen_phase])VALUES (1, N'生产过程')
SET IDENTITY_INSERT [dbo].[zl_yclzlwt_fsjd] OFF
*******/