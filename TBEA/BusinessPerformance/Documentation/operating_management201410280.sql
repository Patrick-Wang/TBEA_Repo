USE [master]
GO
/****** Object:  Database [operating_management]    Script Date: 2014/10/28 19:19:57 ******/
CREATE DATABASE [operating_management]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'receivables', FILENAME = N'D:\Microsoft SQL Server\MSSQL11.MYMSSQLSERVER\MSSQL\DATA\receivables.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'receivables_log', FILENAME = N'D:\Microsoft SQL Server\MSSQL11.MYMSSQLSERVER\MSSQL\DATA\receivables_log.ldf' , SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [operating_management] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [operating_management].[dbo].[sp_fulltext_database] @action = 'disable'
end
GO
ALTER DATABASE [operating_management] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [operating_management] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [operating_management] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [operating_management] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [operating_management] SET ARITHABORT OFF 
GO
ALTER DATABASE [operating_management] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [operating_management] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [operating_management] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [operating_management] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [operating_management] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [operating_management] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [operating_management] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [operating_management] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [operating_management] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [operating_management] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [operating_management] SET  DISABLE_BROKER 
GO
ALTER DATABASE [operating_management] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [operating_management] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [operating_management] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [operating_management] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [operating_management] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [operating_management] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [operating_management] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [operating_management] SET RECOVERY FULL 
GO
ALTER DATABASE [operating_management] SET  MULTI_USER 
GO
ALTER DATABASE [operating_management] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [operating_management] SET DB_CHAINING OFF 
GO
ALTER DATABASE [operating_management] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [operating_management] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
EXEC sys.sp_db_vardecimal_storage_format N'operating_management', N'ON'
GO
USE [operating_management]
GO
/****** Object:  Table [dbo].[blhtdqqkhzb]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[blhtdqqkhzb](
	[ID] [int] NOT NULL,
	[ny] [varchar](10) NULL,
	[dqfkhfxsblye] [numeric](16, 4) NULL,
	[Dqfkhfxsblfs] [int] NULL,
	[Dqkhfxsblye] [numeric](16, 4) NULL,
	[Dqkhfxsblfs] [int] NULL,
	[Dqblje] [numeric](16, 4) NULL,
	[Dqblfs] [int] NULL,
	[Dqblzyhkje] [numeric](16, 4) NULL,
	[Dqblzyhkfs] [int] NULL,
 CONSTRAINT [PK__blhtdqqkhzb] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[cqk]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[cqk](
	[ID] [int] NOT NULL,
	[Hy] [varchar](50) NULL,
	[2n] [numeric](16, 4) NULL,
	[3n] [numeric](16, 4) NULL,
	[4njzq] [numeric](16, 4) NULL,
	[zj] [numeric](16, 4) NULL,
 CONSTRAINT [PK__cqk] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[yqkqsbhb]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[yqkqsbhb](
	[ID] [int] NOT NULL,
	[ny] [varchar](10) NULL,
	[yq1yyn] [numeric](16, 4) NULL,
	[yq1_3y] [numeric](16, 4) NULL,
	[yq3_6y] [numeric](16, 4) NULL,
	[yq6_12y] [numeric](16, 4) NULL,
	[yq1nys] [numeric](16, 4) NULL,
 CONSTRAINT [PK__yqkqsbhb] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[yszk_zj_bl]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[yszk_zj_bl](
	[ID] [int] NOT NULL,
	[gxrq] [datetime] NOT NULL,
	[blbh] [nvarchar](30) NULL,
	[Htbh] [nvarchar](50) NOT NULL,
	[blrq] [datetime] NOT NULL,
	[Kxxz] [numeric](2, 0) NOT NULL,
	[Blje] [numeric](16, 4) NOT NULL,
	[bldqr] [datetime] NULL,
	[Blhkje] [numeric](16, 4) NULL,
	[Blye] [numeric](16, 4) NOT NULL,
	[Sfdrwc] [nvarchar](1) NULL,
	[qybh] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[yszk_zj_fh]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[yszk_zj_fh](
	[id] [int] NOT NULL,
	[htbh] [nvarchar](50) NULL,
	[thdh] [nvarchar](50) NULL,
	[fhje] [numeric](18, 4) NULL,
	[fhrq] [datetime] NULL,
	[qybh] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[yszk_zj_htfkfstj_byq_fdw]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[yszk_zj_htfkfstj_byq_fdw](
	[ID] [int] NOT NULL,
	[gxrq] [date] NULL,
	[gsbm] [varchar](10) NULL,
	[fdwhtddzlbs] [int] NULL,
	[fdwhtddzlje] [numeric](16, 4) NULL,
	[wyfkhtbs] [int] NULL,
	[wyfkhtje] [numeric](16, 4) NULL,
	[yfkxybfzshtbs] [int] NULL,
	[yfkxybfzshtje] [numeric](16, 4) NULL,
	[yfkzbfzsdsszjhtbs] [int] NULL,
	[yfkzbfzsdsszjhtje] [numeric](16, 4) NULL,
	[hwjfhfkblxybfzbshtbs] [int] NULL,
	[hwjfhfkblxybfzbshtje] [numeric](16, 4) NULL,
	[wddsjhtbs] [int] NULL,
	[wddsjhtje] [numeric](16, 4) NULL,
	[zbqdysegyhtbs] [int] NULL,
	[zbqdysegyhtje] [numeric](16, 4) NULL,
	[xkxhhtbs] [int] NULL,
	[xkxhhtje] [numeric](16, 4) NULL,
	[sfdrwc] [varchar](50) NULL,
	[qybh] [int] NULL,
 CONSTRAINT [PK__htfkfstj_byq_fdw] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[yszk_zj_htfkfstj_byq_gwfk]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[yszk_zj_htfkfstj_byq_gwfk](
	[ID] [int] NOT NULL,
	[gxrq] [date] NULL,
	[gsbm] [varchar](10) NULL,
	[gwhtddzlbs] [int] NULL,
	[gwhtddzlje] [numeric](16, 4) NULL,
	[3_4_2_1bs] [int] NULL,
	[3_4_2_1je] [numeric](16, 4) NULL,
	[3_4_2d5_0d5bs] [int] NULL,
	[3_4_2d5_0d5je] [numeric](16, 4) NULL,
	[0_9_0_1bs] [int] NULL,
	[0_9_0_1je] [numeric](16, 4) NULL,
	[1_4_4_1bs] [int] NULL,
	[1_4_4_1je] [numeric](16, 4) NULL,
	[1_4_4d5_0d5bs] [int] NULL,
	[1_4_4d5_0d5je] [numeric](16, 4) NULL,
	[0_10_0_0bs] [int] NULL,
	[0_10_0_0je] [numeric](16, 4) NULL,
	[9d5_0d5bs] [int] NULL,
	[9d5_0d5je] [numeric](16, 4) NULL,
	[qtbs] [int] NULL,
	[qtje] [numeric](16, 4) NULL,
	[sfdrwc] [varchar](50) NULL,
	[qybh] [int] NULL,
 CONSTRAINT [PK__htfkfstj_byq_gwfk] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[yszk_zj_htfkfstj_byq_nwfk]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[yszk_zj_htfkfstj_byq_nwfk](
	[ID] [int] NOT NULL,
	[gxrq] [date] NULL,
	[gsbm] [varchar](10) NULL,
	[nwhtddzlbs] [int] NULL,
	[nwhtddzlje] [numeric](16, 4) NULL,
	[3_3_3_1bs] [int] NULL,
	[3_3_3_1je] [numeric](16, 4) NULL,
	[1_4_4_0d5_0d5bs] [int] NULL,
	[1_4_4_0d5_0d5je] [numeric](16, 4) NULL,
	[1_2_6d5_0d5bs] [int] NULL,
	[1_2_6d5_0d5je] [numeric](16, 4) NULL,
	[1_4_4d5_0d5bs] [int] NULL,
	[1_4_4d5_0d5je] [numeric](16, 4) NULL,
	[qtybs] [int] NULL,
	[qtyje] [numeric](16, 4) NULL,
	[qtebs] [int] NULL,
	[qteje] [numeric](16, 4) NULL,
	[sfdrwc] [varchar](50) NULL,
	[qybh] [int] NULL,
 CONSTRAINT [PK__htfkfstj_byq_nwfk] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[yszk_zj_htfkfstj_xl_fdw]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[yszk_zj_htfkfstj_xl_fdw](
	[ID] [int] NOT NULL,
	[gxrq] [date] NULL,
	[gsbm] [varchar](10) NULL,
	[khbh] [varchar](50) NULL,
	[ddzlbs] [int] NULL,
	[ddzlje] [numeric](16, 4) NULL,
	[wyfkhtbs] [int] NULL,
	[wyfkhtje] [numeric](16, 4) NULL,
	[yfkxybfzshtbs] [int] NULL,
	[yfkxybfzshtje] [numeric](16, 4) NULL,
	[yfkzbfzsdsszjhtbs] [int] NULL,
	[yfkzbfzsdsszjhtje] [numeric](16, 4) NULL,
	[hwjfhfkblxybfzbshtbs] [int] NULL,
	[hwjfhfkblxybfzbshtje] [numeric](16, 4) NULL,
	[zbjbfzshtbs] [int] NULL,
	[zbjbfzshtje] [numeric](16, 4) NULL,
	[zbjbfzwhtbs] [int] NULL,
	[zbjbfzwhtje] [numeric](16, 4) NULL,
	[wzbjhtbs] [int] NULL,
	[wzbjhtje] [numeric](16, 4) NULL,
	[zbqcgynhtbs] [int] NULL,
	[zbqcgynhtje] [numeric](16, 4) NULL,
	[wddsjhtbs] [int] NULL,
	[wddsjhtje] [numeric](16, 4) NULL,
	[xkxhhtbs] [int] NULL,
	[xkxhhtje] [numeric](16, 4) NULL,
	[sfdrwc] [varchar](50) NULL,
	[qybh] [int] NULL,
 CONSTRAINT [PK__htfkfstj_xl_fdw] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[yszk_zj_htfkfstj_xl_gwfk]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[yszk_zj_htfkfstj_xl_gwfk](
	[ID] [int] NOT NULL,
	[gxrq] [date] NULL,
	[gsbm] [varchar](10) NULL,
	[sfjzzb] [varchar](50) NULL,
	[gwhtddzlbs] [int] NULL,
	[gwhtddzlje] [numeric](16, 4) NULL,
	[3_06_0_01bs] [int] NULL,
	[3_06_0_01je] [numeric](16, 4) NULL,
	[0_09_0_01bs] [int] NULL,
	[0_09_0_01je] [numeric](16, 4) NULL,
	[3_4_2_1bs] [int] NULL,
	[3_4_2_1je] [numeric](16, 4) NULL,
	[2_5_2_1bs] [int] NULL,
	[2_5_2_1je] [numeric](16, 4) NULL,
	[2_5_2d5_0d5bs] [int] NULL,
	[2_5_2d5_0d5je] [numeric](16, 4) NULL,
	[0_10_0_0bs] [int] NULL,
	[0_10_0_0je] [numeric](16, 4) NULL,
	[0_9d5_0d5bs] [int] NULL,
	[0_9d5_0d5je] [numeric](16, 4) NULL,
	[qtbs] [int] NULL,
	[qtje] [numeric](16, 4) NULL,
	[wzbjhtbs] [int] NULL,
	[wzbjhtje] [numeric](16, 4) NULL,
	[zbqcgynhtbs] [int] NULL,
	[zbqcgynhtje] [numeric](16, 4) NULL,
	[sfdrwc] [varchar](50) NULL,
	[qybh] [int] NULL,
 CONSTRAINT [PK__htfkfstj_xl_gwfk] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[yszk_zj_htfkfstj_xl_nwfk]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[yszk_zj_htfkfstj_xl_nwfk](
	[ID] [int] NOT NULL,
	[gxrq] [date] NULL,
	[gsbm] [varchar](10) NULL,
	[sfjzzb] [varchar](50) NULL,
	[nwhtddzlbs] [int] NULL,
	[nwhtddzlje] [numeric](16, 4) NULL,
	[1_6_2_1bs] [int] NULL,
	[1_6_2_1je] [numeric](16, 4) NULL,
	[1_2_6_1bs] [int] NULL,
	[1_2_6_1je] [numeric](16, 4) NULL,
	[0_09_01bs] [int] NULL,
	[0_09_01je] [numeric](16, 4) NULL,
	[qtbs] [int] NULL,
	[qtje] [numeric](16, 4) NULL,
	[zbqcgynhtbs] [int] NULL,
	[zbqcgynhtje] [numeric](16, 4) NULL,
	[sfdrwc] [varchar](50) NULL,
	[qybh] [int] NULL,
 CONSTRAINT [PK__htfkfstj_xl_nwfk] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[yszk_zj_htxx]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[yszk_zj_htxx](
	[ID] [int] NOT NULL,
	[gxrq] [datetime] NOT NULL,
	[htbh] [nvarchar](50) NOT NULL,
	[Xmxx] [nvarchar](100) NULL,
	[Sspq] [nvarchar](10) NULL,
	[Khbh] [nvarchar](30) NOT NULL,
	[Khmc] [nvarchar](100) NULL,
	[Khsshy] [nvarchar](4) NULL,
	[Qdrq] [datetime] NULL,
	[Cpje] [numeric](16, 4) NOT NULL,
	[fy] [numeric](16, 4) NOT NULL,
	[Zje] [numeric](16, 4) NOT NULL,
	[htzt] [nvarchar](4) NOT NULL,
	[Sfdrwc] [nvarchar](1) NULL,
	[qybh] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[yszk_zj_htxx_fkfs]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[yszk_zj_htxx_fkfs](
	[ID] [int] NOT NULL,
	[Htbh] [nvarchar](50) NOT NULL,
	[Kxlb] [nvarchar](4) NOT NULL,
	[bl] [numeric](6, 4) NOT NULL,
	[Je] [numeric](16, 4) NOT NULL,
	[Zbq] [numeric](4, 0) NULL,
	[Sfdrwc] [nvarchar](1) NULL,
	[qybh] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[yszk_zj_kp]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[yszk_zj_kp](
	[id] [int] NOT NULL,
	[htbh] [nvarchar](50) NULL,
	[fpbh] [nvarchar](50) NULL,
	[kpje] [numeric](18, 4) NULL,
	[kprq] [datetime] NULL,
	[qybh] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[yszk_zj_mrhk]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[yszk_zj_mrhk](
	[ID] [int] NOT NULL,
	[gxrq] [datetime] NOT NULL,
	[Xmgs] [nvarchar](50) NOT NULL,
	[Hkxz] [nvarchar](4) NOT NULL,
	[Hkrq] [datetime] NULL,
	[Hkje] [numeric](16, 4) NOT NULL,
	[qzqbbc] [numeric](16, 4) NOT NULL,
	[qzzqbc] [numeric](16, 4) NOT NULL,
	[yhkzkjysdhkje] [numeric](16, 4) NOT NULL,
	[jzydyszkzmye] [numeric](16, 4) NOT NULL,
	[jtxdydzjhlzb] [numeric](16, 4) NOT NULL,
	[Sfdrwc] [nvarchar](1) NULL,
	[qybh] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[yszk_zj_tbbzjxx]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[yszk_zj_tbbzjxx](
	[ID] [int] NOT NULL,
	[gxrq] [date] NULL,
	[gsbm] [varchar](10) NULL,
	[nf] [int] NULL,
	[yf] [int] NULL,
	[je] [numeric](16, 4) NULL,
	[sfdrwc] [varchar](50) NULL,
	[qybh] [int] NULL,
 CONSTRAINT [PK__tbbzjxx] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[yszk_zj_ydhkjhjgb]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[yszk_zj_ydhkjhjgb](
	[ID] [int] NOT NULL,
	[gxrq] [date] NULL,
	[gsbm] [varchar](10) NULL,
	[qbkhyqyszk] [numeric](16, 4) NULL,
	[qbkhyqk] [numeric](16, 4) NULL,
	[qbkhwdqyszk] [numeric](16, 4) NULL,
	[qbkhwdqk] [numeric](16, 4) NULL,
	[zqkhyqyszk] [numeric](16, 4) NULL,
	[zqkhyqk] [numeric](16, 4) NULL,
	[zqkhwdqyszk] [numeric](16, 4) NULL,
	[zqkhwdqk] [numeric](16, 4) NULL,
	[xyqsk] [numeric](16, 4) NULL,
	[gyqsk] [numeric](16, 4) NULL,
	[jtxdydzjhlzb] [numeric](16, 4) NULL,
	[sfdrwc] [varchar](1) NULL,
	[qybh] [int] NULL,
 CONSTRAINT [PK__ydhkjh] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[yszk_zj_ydsjhkqk]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[yszk_zj_ydsjhkqk](
	[ID] [int] NOT NULL,
	[gxrq] [date] NULL,
	[gsbm] [varchar](10) NULL,
	[yqyszksjhk] [numeric](16, 4) NULL,
	[yqksjhk] [numeric](16, 4) NULL,
	[wdqyszksjhk] [numeric](16, 4) NULL,
	[wdqksjhk] [numeric](16, 4) NULL,
	[qbkhhk] [numeric](16, 4) NULL,
	[zqkhhk] [numeric](16, 4) NULL,
	[xkxhhk] [numeric](16, 4) NULL,
	[jhwhk] [numeric](16, 4) NULL,
	[sfdrwc] [varchar](1) NULL,
	[qybh] [int] NULL,
 CONSTRAINT [PK__ydsjhkqk] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[yszk_zj_yszkpzgh]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[yszk_zj_yszkpzgh](
	[ID] [int] NOT NULL,
	[yf] [int] NULL,
	[gsbm] [varchar](10) NULL,
	[symljxssr] [numeric](16, 4) NULL,
	[byjhxssr] [numeric](16, 4) NULL,
	[byysnkzb] [numeric](16, 4) NULL,
	[symzmysye] [numeric](16, 4) NULL,
	[byxssrxzysje] [numeric](16, 4) NULL,
	[bykjyszjhlje] [numeric](16, 4) NULL,
	[byghblzjysje] [numeric](16, 4) NULL,
	[byxzblhkcjysje] [numeric](16, 4) NULL,
	[symykpwfhcsysje] [numeric](16, 4) NULL,
	[symyfhwkpzjsjysje] [numeric](16, 4) NULL,
	[symblhkcjysje] [numeric](16, 4) NULL,
	[symyscjysje] [numeric](16, 4) NULL,
	[qtcjys] [numeric](16, 4) NULL,
	[byfhcpxzysje] [numeric](16, 4) NULL,
	[byhkjdysje] [numeric](16, 4) NULL,
	[sfdrwc] [varchar](50) NULL,
	[qybh] [int] NULL,
 CONSTRAINT [PK__yszkpzgh] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[yszk_zj_yszktz]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[yszk_zj_yszktz](
	[ID] [int] NOT NULL,
	[Gxrq] [datetime] NOT NULL,
	[Htbh] [nvarchar](50) NOT NULL,
	[Khbh] [nvarchar](30) NOT NULL,
	[Khmc] [nvarchar](100) NOT NULL,
	[Khsshy] [nvarchar](4) NOT NULL,
	[Kxlb] [nvarchar](4) NOT NULL,
	[kxzt] [nvarchar](4) NOT NULL,
	[ysje] [numeric](16, 4) NOT NULL,
	[dqrq] [datetime] NULL,
	[yhxje] [numeric](16, 4) NOT NULL,
	[yfhje] [numeric](16, 4) NOT NULL,
	[fhrq] [datetime] NULL,
	[ykpje] [numeric](16, 4) NULL,
	[kprq] [datetime] NULL,
	[yqyyfl] [int] NOT NULL,
	[sftgflsdqs] [nvarchar](1) NULL,
	[Sfdrwc] [nvarchar](1) NULL,
	[qybh] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[yszk_zj_ztyszkfxb]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[yszk_zj_ztyszkfxb](
	[ID] [int] NOT NULL,
	[gxrq] [date] NULL,
	[gsbm] [varchar](10) NULL,
	[byzmyszkye] [numeric](16, 4) NULL,
	[byblkzye] [numeric](16, 4) NULL,
	[byyszksjs] [numeric](16, 4) NULL,
	[bysr] [numeric](16, 4) NULL,
	[qntqzmyszkye] [numeric](16, 4) NULL,
	[qntqblye] [numeric](16, 4) NULL,
	[qntqyszksjs] [numeric](16, 4) NULL,
	[qntqsr] [numeric](16, 4) NULL,
	[sfdrwc] [varchar](1) NULL,
	[qybh] [int] NULL,
 CONSTRAINT [PK__ztyszkfxb] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[yszkjgqkb]    Script Date: 2014/10/28 19:19:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[yszkjgqkb](
	[ID] [int] NOT NULL,
	[ht] [varchar](30) NULL,
	[ysje] [numeric](16, 4) NULL,
	[zqbbl] [varchar](10) NULL,
	[yq1yyn] [numeric](16, 4) NULL,
	[yq1_3y] [numeric](16, 4) NULL,
	[yq3_6y] [numeric](16, 4) NULL,
	[yq6_12y] [numeric](16, 4) NULL,
	[yq1nys] [numeric](16, 4) NULL,
	[wdqk] [numeric](16, 4) NULL,
	[qdqzbj] [numeric](16, 4) NULL,
	[yszkhj] [numeric](16, 4) NULL,
 CONSTRAINT [PK__yszkjgqkb] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
USE [master]
GO
ALTER DATABASE [operating_management] SET  READ_WRITE 
GO
