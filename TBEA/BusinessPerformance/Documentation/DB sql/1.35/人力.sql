Drop table [dbo].[hr_employee];
CREATE TABLE [dbo].[hr_employee](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[workNum] [varchar](100) NOT NULL,
	[name] [varchar](100) NULL,
	[postCode] [varchar](100) NULL,
	[sex] [varchar](100) NULL,
	[companyCode] [varchar](100) NULL,
	[companyName] [varchar](100) NULL,
	[operationUnitCode] [varchar](100) NULL,
	[operationUnitName] [varchar](100) NULL,
	[departCode] [varchar](100) NULL,
	[departName] [varchar](100) NULL,
	position [varchar](100) NULL,
	[isInPost] [varchar](100) NULL,
	[employeeCategoryCode] [varchar](100) NULL,
	[employeeCategory] [varchar](100) NULL,
	[firstEnterDate] [varchar](100) NULL,
	[mdmCode] [varchar](100) NULL,
	[mdmDataUuid] [varchar](100) NULL,
    approveTime [varchar](100) NULL,
    updateOrAdd [varchar](100) NULL,
	[mdmBatch] [varchar](100) NULL,
	[updateTime] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

create view [dbo].[hr_employee_v] as
SELECT   *, 
                CASE companyName WHEN '特变电工股份有限公司' THEN '股份公司' WHEN '特变电工沈阳变压器集团有限公司' THEN '沈变'
                 WHEN '特变电工衡阳变压器有限公司' THEN '衡变' WHEN '特变电工股份有限公司新疆变压器厂' THEN '新变' WHEN '天津市特变电工变压器有限公司'
                 THEN '天变' WHEN '特变电工山东鲁能泰山电缆有限公司' THEN '鲁缆' WHEN '特变电工新疆线缆厂' THEN '新缆' WHEN
                 '特变电工（德阳）电缆股份有限公司' THEN '德缆' WHEN '特变电工新疆新能源股份有限公司' THEN '新能源（含佳阳）'
                 WHEN '新特能源股份有限公司' THEN '新特能源' WHEN '新疆天池能源有限责任公司' THEN '天池能源' WHEN '特变电工股份有限公司能源动力分公司'
                 THEN '能动' WHEN '特变电工股份有限公司进出口公司' THEN '进出口' WHEN '特变电工国际工程有限公司' THEN '工程公司'
                 WHEN '新疆特变电工工业文化旅游有限责任公司' THEN '工业旅游公司' WHEN '特变电工股份有限公司后勤管理分公司'
                 THEN '后勤公司' WHEN '新疆特变电工集团有限公司' THEN '集团' WHEN '新疆众和股份有限公司' THEN '众和' ELSE '其它'
                 END AS dwmc
FROM      dbo.hr_employee