IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cpzlqk_acptjjg_jd_xk')
DROP TABLE cpzlqk_acptjjg_jd_xk
CREATE TABLE [dbo].[cpzlqk_acptjjg_jd_xk](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	cpdl [int],
	cpxl int,
	formul [varchar](50)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cpzlqk_acptjjg_yd_xk')
DROP TABLE cpzlqk_acptjjg_yd_xk
CREATE TABLE [dbo].[cpzlqk_acptjjg_yd_xk](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	cpdl [int],
	cpxl int,
	formul [varchar](50)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'cpzlqk_adwtjjg_xk')
DROP TABLE cpzlqk_adwtjjg_xk
CREATE TABLE [dbo].[cpzlqk_adwtjjg_xk](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	dwid [int],
	dwmc [int],
	cpdl [int],
	cpxl int,
	formul [varchar](50)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];

SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_names] ON
INSERT INTO [dbo].[identifier_cpzlqk_names] (id, [name]) VALUES (74, '西科公司');
INSERT INTO [dbo].[identifier_cpzlqk_names] (id, [name]) VALUES (75, 'SVG');
INSERT INTO [dbo].[identifier_cpzlqk_names] (id, [name]) VALUES (76, '集中式逆变器');
INSERT INTO [dbo].[identifier_cpzlqk_names] (id, [name]) VALUES (77, '组串式逆变器');
INSERT INTO [dbo].[identifier_cpzlqk_names] (id, [name]) VALUES (78, '汇流箱');
SET IDENTITY_INSERT [dbo].[identifier_cpzlqk_names] OFF


INSERT INTO [dbo].[cpzlqk_dwmc] (dwid, [nameid]) VALUES (903, 74);


INSERT INTO [dbo].[cpzlqk_acptjjg_jd_xk] (cpdl, cpxl, formul) VALUES (74, 75, 'this');
INSERT INTO [dbo].[cpzlqk_acptjjg_jd_xk] (cpdl, cpxl, formul) VALUES (74, 76, 'this');
INSERT INTO [dbo].[cpzlqk_acptjjg_jd_xk] (cpdl, cpxl, formul) VALUES (74, 77, 'this');
INSERT INTO [dbo].[cpzlqk_acptjjg_jd_xk] (cpdl, cpxl, formul) VALUES (74, 78, 'this');
INSERT INTO [dbo].[cpzlqk_acptjjg_jd_xk] (cpdl, cpxl, formul) VALUES (74, 49, '#+(1-4)');

INSERT INTO [dbo].[cpzlqk_acptjjg_yd_xk] (cpdl, cpxl, formul) VALUES (74, 75, 'this');
INSERT INTO [dbo].[cpzlqk_acptjjg_yd_xk] (cpdl, cpxl, formul) VALUES (74, 76, 'this');
INSERT INTO [dbo].[cpzlqk_acptjjg_yd_xk] (cpdl, cpxl, formul) VALUES (74, 77, 'this');
INSERT INTO [dbo].[cpzlqk_acptjjg_yd_xk] (cpdl, cpxl, formul) VALUES (74, 78, 'this');
INSERT INTO [dbo].[cpzlqk_acptjjg_yd_xk] (cpdl, cpxl, formul) VALUES (74, 49, '#+(1-4)');

INSERT INTO [dbo].[cpzlqk_adwtjjg_xk] (dwid, dwmc, cpdl, cpxl, formul) VALUES (903, 74, 74, 75, 'this');
INSERT INTO [dbo].[cpzlqk_adwtjjg_xk] (dwid, dwmc, cpdl, cpxl, formul) VALUES (903, 74, 74, 76, 'this');
INSERT INTO [dbo].[cpzlqk_adwtjjg_xk] (dwid, dwmc, cpdl, cpxl, formul) VALUES (903, 74, 74, 77, 'this');
INSERT INTO [dbo].[cpzlqk_adwtjjg_xk] (dwid, dwmc, cpdl, cpxl, formul) VALUES (903, 74, 74, 78, 'this');
INSERT INTO [dbo].[cpzlqk_adwtjjg_xk] (dwid, dwmc, cpdl, cpxl, formul) VALUES (903, 74, 74, 49, '#+(1-4)');


INSERT INTO [dbo].[zl_yclhglqk_cldl] (name, dw, cy) values ('结构件类','套',2	);
INSERT INTO [dbo].[zl_yclhglqk_cldl] (name, dw, cy) values ('线缆类','套',2	);
INSERT INTO [dbo].[zl_yclhglqk_cldl] (name, dw, cy) values ('功率器件','个',2	);
INSERT INTO [dbo].[zl_yclhglqk_cldl] (name, dw, cy) values ('散热器件','个',2	);
INSERT INTO [dbo].[zl_yclhglqk_cldl] (name, dw, cy) values ('PCBA','块',2	);
INSERT INTO [dbo].[zl_yclhglqk_cldl] (name, dw, cy) values ('交流接触器，交直流断路器','个',2	);
INSERT INTO [dbo].[zl_yclhglqk_cldl] (name, dw, cy) values ('变压器','个',2	);
INSERT INTO [dbo].[zl_yclhglqk_cldl] (name, dw, cy) values ('电抗器','个',2	);
INSERT INTO [dbo].[zl_yclhglqk_cldl] (name, dw, cy) values ('电流传感器','个',2	);
INSERT INTO [dbo].[zl_yclhglqk_cldl] (name, dw, cy) values ('电容','个',2	);
INSERT INTO [dbo].[zl_yclhglqk_cldl] (name, dw, cy) values ('辅材','个',2	);




