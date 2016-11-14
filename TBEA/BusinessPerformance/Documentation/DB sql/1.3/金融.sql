USE [operating_management]
GO

/****** Object:  Table [dbo].[stock_financial]    Script Date: 2016/11/14 17:09:30 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[stock_financial](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[stockid] [varchar](200) NULL,
	[report_dt] [date] NULL,
	[m01] [numeric](18, 2) NULL,
	[m02] [numeric](18, 2) NULL,
	[m03] [numeric](18, 2) NULL,
	[m04] [numeric](18, 2) NULL,
	[m05] [numeric](18, 2) NULL,
	[m06] [numeric](18, 2) NULL,
	[m07] [numeric](18, 2) NULL,
	[m08] [numeric](18, 2) NULL,
	[m09] [numeric](18, 2) NULL,
	[m10] [numeric](18, 2) NULL,
	[m11] [numeric](18, 2) NULL,
	[m12] [numeric](18, 2) NULL,
	[m13] [numeric](18, 2) NULL,
	[m14] [numeric](18, 2) NULL,
	[m15] [numeric](18, 2) NULL,
	[m16] [numeric](18, 2) NULL,
	[m17] [numeric](18, 2) NULL,
	[m18] [numeric](18, 2) NULL,
	[m19] [numeric](18, 2) NULL,
	[m20] [numeric](18, 2) NULL,
	[m21] [numeric](18, 2) NULL,
	[m22] [numeric](18, 2) NULL,
	[m23] [numeric](18, 2) NULL,
	[m24] [numeric](18, 2) NULL,
	[m25] [numeric](18, 2) NULL,
	[m26] [numeric](18, 2) NULL,
	[m27] [numeric](18, 2) NULL,
	[m28] [numeric](18, 2) NULL,
	[m29] [numeric](18, 2) NULL,
	[m30] [numeric](18, 2) NULL,
	[m31] [numeric](18, 2) NULL,
	[m32] [numeric](18, 2) NULL,
	[m33] [numeric](18, 2) NULL,
	[m34] [numeric](18, 2) NULL,
	[m35] [numeric](18, 2) NULL,
	[m36] [numeric](18, 2) NULL,
	[m37] [numeric](18, 2) NULL,
	[m38] [numeric](18, 2) NULL,
	[m39] [numeric](18, 2) NULL,
	[m40] [numeric](18, 2) NULL,
	[m41] [numeric](18, 2) NULL,
	[m42] [numeric](18, 2) NULL,
	[m43] [numeric](18, 2) NULL,
	[m44] [numeric](18, 2) NULL,
	[m45] [numeric](18, 2) NULL,
	[m46] [numeric](18, 2) NULL,
	[m47] [numeric](18, 2) NULL,
	[m48] [numeric](18, 2) NULL,
	[m49] [numeric](18, 2) NULL,
	[m50] [numeric](18, 2) NULL,
	[m51] [numeric](18, 2) NULL,
	[m52] [numeric](18, 2) NULL,
	[m53] [numeric](18, 2) NULL,
	[m54] [numeric](18, 2) NULL,
	[m55] [numeric](18, 2) NULL,
	[m56] [numeric](18, 2) NULL,
	[m57] [numeric](18, 2) NULL,
	[m58] [numeric](18, 2) NULL,
	[m59] [numeric](18, 2) NULL,
	[m60] [numeric](18, 2) NULL,
	[m61] [numeric](18, 2) NULL,
	[m62] [numeric](18, 2) NULL,
	[m63] [numeric](18, 2) NULL,
	[m64] [numeric](18, 2) NULL,
	[m65] [numeric](18, 2) NULL,
	[m66] [numeric](18, 2) NULL,
	[m67] [numeric](18, 2) NULL,
	[m68] [numeric](18, 2) NULL,
	[m69] [numeric](18, 2) NULL,
	[m70] [numeric](18, 2) NULL,
	[m71] [numeric](18, 2) NULL,
	[m72] [numeric](18, 2) NULL,
	[m73] [numeric](18, 2) NULL,
	[m74] [numeric](18, 2) NULL,
	[m75] [numeric](18, 2) NULL,
	[m76] [numeric](18, 2) NULL,
	[m77] [numeric](18, 2) NULL,
	[m78] [numeric](18, 2) NULL,
	[m79] [numeric](18, 2) NULL,
	[m80] [numeric](18, 2) NULL,
	[m81] [numeric](18, 2) NULL,
	[m82] [numeric](18, 2) NULL,
	[m83] [numeric](18, 2) NULL,
	[m84] [numeric](18, 2) NULL,
	[m85] [numeric](18, 2) NULL,
	[source] [nvarchar](200) NULL,
	[time] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


