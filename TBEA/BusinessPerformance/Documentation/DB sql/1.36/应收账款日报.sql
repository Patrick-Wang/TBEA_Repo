/****** Object: Table [dbo].[jygk_ysdaily_new]   Script Date: 2018/7/12 ÐÇÆÚËÄ ÏÂÎç 1:46:49 ******/
USE [operating_management];
GO
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[jygk_ysdaily_new]') AND type in (N'U'))
BEGIN
DROP TABLE [dbo].[jygk_ysdaily_new];
END
GO

SET ANSI_NULLS ON;
GO
SET QUOTED_IDENTIFIER ON;
GO
CREATE TABLE [dbo].[jygk_ysdaily_new] (
[id] int IDENTITY(1, 1) NOT NULL,
[lrnf] int NOT NULL,
[lryf] int NOT NULL,
[lrrq] int NOT NULL,
[dwID] int NOT NULL,
[yszkzb] numeric(18, 2) NULL,
[hkjh] numeric(18, 2) NULL,
[qbkx] numeric(18, 2) NULL,
[zqkx] numeric(18, 2) NULL,
[syysye] numeric(18, 2) NULL,
[jrxzyszk] numeric(18, 2) NULL,
[jrhk] numeric(18, 2) NULL,
[ljkjyshk] numeric(18, 2) NULL)
ON [PRIMARY]
WITH (DATA_COMPRESSION = NONE);
GO
ALTER TABLE [dbo].[jygk_ysdaily_new] SET (LOCK_ESCALATION = TABLE);
GO
