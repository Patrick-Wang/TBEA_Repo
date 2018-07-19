IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'sjzk_cache')
Drop table [dbo].[sjzk_cache];
CREATE TABLE [dbo].[sjzk_cache](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cy] [int] NOT NULL,--0:总体（制造业）;1:总体（集成服务业）;2:总体（物流贸易业务）;3:总体（三项费用）
	[zbid] [int] NOT NULL,
	[nf] [int] NULL,
	[yf] [int] NULL,
	[type] [varchar](100) NULL,
	[dwmc] [varchar](100) NULL,
	[ndjh] [numeric](18, 4) NULL,
	[dyjh] [numeric](18, 4) NULL,
	[dysj] [numeric](18, 4) NULL,
	[dyjhwcl] [numeric](18, 4) NULL,
	[dysjqntq] [numeric](18, 4) NULL,
	[dysjtbzf] [numeric](18, 4) NULL,
	[jdjh] [numeric](18, 4) NULL,
	[jdlj] [numeric](18, 4) NULL,
	[jdjhwcl] [numeric](18, 4) NULL,
	[jdljqntq] [numeric](18, 4) NULL,
	[jdljtbzf] [numeric](18, 4) NULL,
	[ndljwc] [numeric](18, 4) NULL,
	[ndjhwcl] [numeric](18, 4) NULL,
	[ndljwcqntq] [numeric](18, 4) NULL,
	[ndljtbzf] [numeric](18, 4) NULL,
	[batchTime] [datetime] NULL,
	[updateTime] [datetime] NULL,
	[source] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]