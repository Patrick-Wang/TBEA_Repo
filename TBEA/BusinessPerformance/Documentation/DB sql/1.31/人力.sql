IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'hr_employee')
DROP TABLE hr_employee
CREATE TABLE [dbo].[hr_employee](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](100) NULL,
	[category] [varchar](100) NULL,
	[company] [varchar](100) NULL,
	[depart] [varchar](100) NULL,
	[isInPost] [varchar](100) NULL,
	[leaveDate] [varchar](100) NULL,
	[mdmCode] [varchar](100) NULL,
	[mdmDataUuid] [varchar](100) NULL,
	[mdmBatch] [varchar](100) NULL,
	[updateTime] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

create view hr_employee_v as
select *, 
case company
	when '特变电工股份有限公司' then '股份公司'
	when '特变电工沈阳变压器集团有限公司' then '沈变'
	when '特变电工衡阳变压器有限公司' then '衡变'
	when '特变电工股份有限公司新疆变压器厂' then '新变'
	when '天津市特变电工变压器有限公司' then '天变'
	when '特变电工山东鲁能泰山电缆有限公司' then '鲁缆'
	when '特变电工新疆线缆厂' then '新缆'
	when '特变电工（德阳）电缆股份有限公司' then '德缆'
	when '特变电工新疆新能源股份有限公司' then '新能源（含佳阳）'
	when '新特能源股份有限公司' then '新特能源'
	when '新疆天池能源有限责任公司' then '天池能源'
	when '特变电工股份有限公司能源动力分公司' then '能动'
	when '特变电工股份有限公司进出口公司' then '进出口'
	when '特变电工国际工程有限公司' then '工程公司'
	when '新疆特变电工工业文化旅游有限责任公司' then '工业旅游公司'
	when '特变电工股份有限公司后勤管理分公司' then '后勤公司'
	when '新疆特变电工集团有限公司' then '集团'
	when '新疆众和股份有限公司' then '众和'
	else '其它' end dwmc  from hr_employee

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'hr_org')
DROP TABLE hr_org
CREATE TABLE [dbo].[hr_org](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](100) NULL,
	[code] [varchar](100) NULL,
	[shortname] [varchar](100) NULL,
	[type] [varchar](100) NULL,
	[legalPerson] [varchar](100) NULL,
	[responsiblePersonCode] [varchar](100) NULL,
	[responsiblePersonName] [varchar](100) NULL,
	[secondResponsiblePersonCode] [varchar](100) NULL,
	[secondResponsiblePersonName] [varchar](100) NULL,
	[parentOrgCode] [varchar](100) NULL,
	[parentOrgName] [varchar](100) NULL,
	[status] [varchar](100) NULL,
	[englistName] [varchar](100) NULL,
	[responsiblePersonPostCode] [varchar](100) NULL,
	[secondResponsiblePersonPostCode] [varchar](100) NULL,
	[isSealed] [varchar](100) NULL,
	[isCanceled] [varchar](100) NULL,
	[cancelDate] [varchar](100) NULL,
	[isLegalEntity] [varchar](100) NULL,
	[mdmCode] [varchar](100) NULL,
	[mdmDataUuid] [varchar](100) NULL,
	[mdmBatch] [varchar](100) NULL,
	[updateTime] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
