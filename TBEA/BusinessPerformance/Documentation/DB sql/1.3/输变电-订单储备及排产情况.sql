/***************************************************************************** 线缆可供履约订单产品类别
  id name
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'identifier_sbdddcbjpcqk_xlcplb')
DROP TABLE identifier_sbdddcbjpcqk_xlcplb
CREATE TABLE [dbo].[identifier_sbdddcbjpcqk_xlcplb](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
SET IDENTITY_INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ON
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (0, N'导线')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (1, N'布电线')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (2, N'架空线')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (3, N'控制电缆')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (4, N'高压交联电缆')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (5, N'中压交联电缆')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (6, N'低压交联电缆')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (7, N'电力电缆')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (8, N'电磁线')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (9, N'特种电缆')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (10, N'电缆附件')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (11, N'铜杆')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (12, N'铝杆')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (13, N'PVC料')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (14, N'工装轮')
INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] ([id], [name]) VALUES (15, N'其它')
SET IDENTITY_INSERT [dbo].[identifier_sbdddcbjpcqk_xlcplb] OFF
 