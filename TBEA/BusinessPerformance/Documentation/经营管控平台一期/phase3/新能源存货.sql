/***************************************************************************** 新能源存货
				账面净额合计	EPC项目存货	已转让自有项目存货	未转让自有项目存货	已转固自有项目存货	资本化前期费用	
id	nf	yf	zmjehj	epcxmch	yzrzyxmch	wzrzyxmch	yzgzyxmch	zbhqqfy	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'xnych_xnych')
DROP TABLE xnych_xnych
CREATE TABLE [dbo].[xnych_xnych](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[zmjehj] [numeric](18, 4) NULL,
	[epcxmch] [numeric](18, 4) NULL,
	[yzrzyxmch] [numeric](18, 4) NULL,
	[wzrzyxmch] [numeric](18, 4) NULL,
	[yzgzyxmch] [numeric](18, 4) NULL,
	[zbhqqfy] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]