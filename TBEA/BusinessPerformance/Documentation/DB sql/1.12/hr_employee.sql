IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'hr_employee')
DROP TABLE hr_employee
CREATE TABLE [dbo].[hr_employee](
	[id] [int] IDENTITY(1,1) NOT NULL,
	code varchar(100),
	name varchar(100),
	spell varchar(100),
	sex varchar(100),
	category varchar(100),
	companyId varchar(100),
	company varchar(100),
	departId varchar(100),
	depart varchar(100),
	citizenId varchar(100),
	telephone varchar(100),
	cellphone varchar(100),
	e_mail varchar(100),
	position varchar(100),
	post varchar(100),
	isInPost varchar(100),
	mdmCode varchar(100),
	mdmDataUuid varchar(100),
	mdmBatch varchar(100),
	updateTime bigint
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]