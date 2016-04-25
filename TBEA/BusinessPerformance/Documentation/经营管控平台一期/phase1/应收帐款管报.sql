/***************************************************************************** ��1 Ӧ���ʿ������ 
			��˾	���澻��	����׼��	ԭֵ
id	nf	yf	dwid	zmje	hzzb	yz
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'yszkgb_yszkzm')
DROP TABLE yszkgb_yszkzm
CREATE TABLE [dbo].[yszkgb_yszkzm](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[zmje] [numeric](18, 4) NULL,
	[hzzb] [numeric](18, 4) NULL,
	[yz] [numeric](18, 4) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/***************************************************************************** ��2 Ӧ���˿�����仯 
			��˾	5������	4-5��	3-4��	2-3��	1-2��	1������	�ϼ�
ID	nf	yf	dwid	zl5nys	zl4z5n	zl3z4n	zl2z3n	zl1z2n	zl1nyn	hj
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'yszkgb_yszkzl')
DROP TABLE yszkgb_yszkzl
CREATE TABLE [dbo].[yszkgb_yszkzl](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[zl5nys] [numeric](18, 4) NULL,
	[zl4z5n] [numeric](18, 4) NULL,
	[zl3z4n] [numeric](18, 4) NULL,
	[zl2z3n] [numeric](18, 4) NULL,
	[zl1z2n] [numeric](18, 4) NULL,
	[zl1nyn] [numeric](18, 4) NULL,
	[hj] [numeric](18, 4) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/***************************************************************************** ��3 Ӧ���˿����������� 
			��˾	����0-1����	����1-3��	����3-6��	����6-12��	����1������	δ����(�����ʱ���)	δ�����ʱ���	
ID	nf	yf	dwid	yq0z1y	yq1z3y	yq3z6y	yq6z12y	yq1nys	wdq	wdqzbj	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'yszkgb_yszkkxxzqk')
DROP TABLE yszkgb_yszkkxxzqk
CREATE TABLE [dbo].[yszkgb_yszkkxxzqk](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[yq0z1y] [numeric](18, 4) NULL,
	[yq1z3y] [numeric](18, 4) NULL,
	[yq3z6y] [numeric](18, 4) NULL,
	[yq6z12y] [numeric](18, 4) NULL,
	[yq1nys] [numeric](18, 4) NULL,
	[wdq] [numeric](18, 4) NULL,
	[wdqzbj] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/***************************************************************************** ��4 ����Ӧ���˲������� 
			��˾	�ڲ�����	�ͻ�����	��������	��Ŀ�仯	��ͬ����	��������	����	
ID	nf	yf	dwid	nbys	khzx	gdfk	xmbh	htys	sxbl	ss	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'yszkgb_yqyszcsys')
DROP TABLE yszkgb_yqyszcsys
CREATE TABLE [dbo].[yszkgb_yqyszcsys](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[nbys] [numeric](18, 4) NULL,
	[khzx] [numeric](18, 4) NULL,
	[gdfk] [numeric](18, 4) NULL,
	[xmbh] [numeric](18, 4) NULL,
	[htys] [numeric](18, 4) NULL,
	[sxbl] [numeric](18, 4) NULL,
	[ss] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/***************************************************************************** Ӧ���˿�������Ԥ��̨�˵������Ʊ�5��
			��˾	��������Ӧ�վ������	���������	����Ʊδ�������	Ʊ����δ�������	Ԥ�տ���Ӧ�գ����	����֤���Ӧ�գ����	����Ӧ�տ�ĿӰ�죨���	Ԥ��̨��Ӧ���˿���� 	
												
ID	nf	yf	dwid	cwzmysjsye	blye	hfpwkje	pkhwfje	yskcjys	xyzcjys	qtyskmyx	yjtzyszkye	zt
*****************************************************************************/
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'yszkgb_yszkzmyyjtztjqs')
DROP TABLE yszkgb_yszkzmyyjtztjqs
CREATE TABLE [dbo].[yszkgb_yszkzmyyjtztjqs](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nf] [int] not NULL,
	[yf] [int] not NULL,
	[dwid] [int] not NULL,
	[cwzmysjsye] [numeric](18, 4) NULL,
	[blye] [numeric](18, 4) NULL,
	[hfpwkje] [numeric](18, 4) NULL,
	[pkhwfje] [numeric](18, 4) NULL,
	[yskcjys] [numeric](18, 4) NULL,
	[xyzcjys] [numeric](18, 4) NULL,
	[qtyskmyx] [numeric](18, 4) NULL,
	[yjtzyszkye] [numeric](18, 4) NULL,
	[zt] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

