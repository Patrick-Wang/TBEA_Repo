IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'hr_org')
DROP TABLE hr_org
CREATE TABLE [dbo].[hr_org](
	[id] [int] IDENTITY(1,1) NOT NULL,
	name varchar(100),
	code varchar(100),
	shortname varchar(100),
	type varchar(100),
	legalPerson varchar(100),
	responsiblePersonCode varchar(100),
	responsiblePersonName varchar(100),
	secondResponsiblePersonCode varchar(100),
	secondResponsiblePersonName varchar(100),
	parentOrgCode varchar(100),
	parentOrgName varchar(100),
	status varchar(100),
	englistName varchar(100),
	responsiblePersonPostCode varchar(100),
	secondResponsiblePersonPostCode varchar(100),
	isSealed varchar(100),
	isCanceled varchar(100),
	cancelDate varchar(100),
	isLegalEntity varchar(100),
	mdmCode varchar(100),
	mdmDataUuid varchar(100),
	mdmBatch varchar(100),
	updateTime bigint
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]