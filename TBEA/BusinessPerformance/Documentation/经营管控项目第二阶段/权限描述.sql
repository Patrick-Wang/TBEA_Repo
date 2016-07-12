IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'auth_instruction')
DROP TABLE auth_instruction
CREATE TABLE [dbo].auth_instruction(
	[id] [int] IDENTITY(1,1) NOT NULL,
	[instruction] [varchar](100) NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
SET IDENTITY_INSERT [dbo].[auth_instruction] ON
insert auth_instruction (id,instruction) values (1,	N'YSZKDailyReportEntry')
insert auth_instruction (id,instruction) values (2,	N'JYAnalysisEntry')
insert auth_instruction (id,instruction) values (3,	N'JYAnalysisLookup')
insert auth_instruction (id,instruction) values (4,	N'YSZKDialyLookup')
insert auth_instruction (id,instruction) values (5,	N'XJLDialyLookup')
insert auth_instruction (id,instruction) values (6,	N'JYEntryLookup')
insert auth_instruction (id,instruction) values (7,	N'PriceLib')
insert auth_instruction (id,instruction) values (8,	N'YszkgbEntry')
insert auth_instruction (id,instruction) values (9,	N'YszkgbLookup')
insert auth_instruction (id,instruction) values (10,	N'ChgbEntry')
insert auth_instruction (id,instruction) values (11,	N'ChgbLookup')
insert auth_instruction (id,instruction) values (12,	N'SbdgbEntry')
insert auth_instruction (id,instruction) values (13,	N'SbdgbLookup')
insert auth_instruction (id,instruction) values (14,	N'NygbEntry')
insert auth_instruction (id,instruction) values (15,	N'NygbLookup')
insert auth_instruction (id,instruction) values (16,	N'XnygbEntry')
insert auth_instruction (id,instruction) values (17,	N'XnygbLookup')
insert auth_instruction (id,instruction) values (18,	N'NYzbscqkEntry')
insert auth_instruction (id,instruction) values (19,	N'NYzbscqkLookup')
insert auth_instruction (id,instruction) values (20,	N'QualityLookup')
insert auth_instruction (id,instruction) values (21,	N'QualityEntry')
insert auth_instruction (id,instruction) values (22,	N'QualityApprove')
insert auth_instruction (id,instruction) values (23,	N'FinanceLookup')
insert auth_instruction (id,instruction) values (24,	N'FinanceEntry')
insert auth_instruction (id,instruction) values (25,	N'生产、发货和价格周报表 录入')
insert auth_instruction (id,instruction) values (26,	N'生产、发货和价格周报表 查看')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF