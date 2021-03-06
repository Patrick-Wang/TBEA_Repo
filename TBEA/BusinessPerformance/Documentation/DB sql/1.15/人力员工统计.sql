IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'hr_employee_statistics')
DROP TABLE hr_employee_statistics
CREATE TABLE [dbo].[hr_employee_statistics](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	nf [int],
	yf [int],
	dwmc [varchar](100),
	formal_employee [int],
	labor_dispatch [int],
	time [datetime]
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
