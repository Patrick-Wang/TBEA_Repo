/***************************************************************************** 输变电-产值完成情况
				产业	产品	产值	
id	nf	yf		cy	cp	cz	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'sbdclczgb_cz')
DROP TABLE sbdclczgb_cz
CREATE TABLE [dbo].[sbdclczgb_cz](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[cy] [int] not NULL,
	[cpid] [int] not NULL,
	[cz] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/***************************************************************************** 输变电-产量完成情况-分产品
			产品	产量	
id	nf	yf	cp	cl	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'sbdclczgb_cl_fcp')
DROP TABLE sbdclczgb_cl_fcp
CREATE TABLE [dbo].[sbdclczgb_cl_fcp](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[cy] [int] not NULL,
	[cpid] [int] not NULL,
	[cl] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/***************************************************************************** 输变电-产量完成情况-分材料
			主材	产量	
id	nf	yf	zc	cl	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'sbdclczgb_cl_fcl')
DROP TABLE sbdclczgb_cl_fcl
CREATE TABLE [dbo].[sbdclczgb_cl_fcl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[cy] [int] not NULL,
	[zcid] [int] not NULL,
	[cl] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

