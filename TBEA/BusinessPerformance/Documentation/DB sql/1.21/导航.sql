IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'navigator_item')
DROP TABLE navigator_item
CREATE TABLE [dbo].[navigator_item](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	name [varchar](200),
	url [varchar](200),
	extend int, --0 false  1 true
	iconClose [varchar](200),
	iconOpen [varchar](200),
	parent int
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'navigator_positive_auth')
DROP TABLE navigator_positive_auth
CREATE TABLE [dbo].[navigator_positive_auth](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	itemId int,
	authId int,
	authName [varchar](50)
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'navigator_negative_auth')
DROP TABLE navigator_negative_auth
CREATE TABLE [dbo].[navigator_negative_auth](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	itemId int,
	authId int,
	authName [varchar](50),
	type int -- 0 无, 1 有
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];


insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	null	,'管理控制台',null,	0	,'fa fa-tachometer','fa fa-tachometer');
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	1	,'在线用户','dashboard/user_status.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	null	,'经营驾驶舱',null,	1	,'fa fa-bar-chart','fa fa-bar-chart');
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	3	,'经营驾驶舱','dashboard/dashboard.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	null	,'报表信息',null,	1	,'fa fa-table', 'fa fa-table');
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	5	,'市场签约信息',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	6	,'市场签约统计情况','report/v2/scqytjqk.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	6	,'重点产品签约情况查看','report/v2/scqy.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	6	,'行业签约情况查看','report/v2/scjb.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	6	,'重点产品签约情况录入','report/v2/scqyEntry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	6	,'行业签约情况录入','report/v2/scjbEntry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	5	,'经营指标完成情况',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	12	,'公司整体指标完成情况','ydzb/v2/hzb_zbhz.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	12	,'单位指标完成情况','ydzb/v2/hzb_companys.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	12	,'产业重点指标完成情况','ydzb/v2/gcy_zbhz.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	12	,'单位重点指标完成情况','ydzb/v2/gdw_zbhz.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	12	,'单位数据子库','report/v2/zk.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	12	,'产业指标汇总','report/v2/acyhzzb.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	12	,'整体指标预测完成情况','ydzb/v2/hzb_zbhz_prediction.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	12	,'单位指标预测完成情况','ydzb/v2/hzb_companys_prediction.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	12	,'产业重点指标预测完成情况','ydzb/v2/financial_zbhz_prediction.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	12	,'单位重点指标预测完成情况','ydzb/v2/gdw_zbhz_prediction.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	12	,'指标填报情况汇总','dashboard/v2/status.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	12	,'众和质量指标','report/v2/zhzl.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	12	,'众和分子公司累计汇总','report/v2/zhgsljzbhz.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	12	,'众和分子公司月度汇总','report/v2/zhgsljzbhz.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	12	,'众和分产业关键指标汇总','report/v2/zhgsfcyUnion.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	5	,'经营指标分析',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	28	,'应收账款日报','yszkrb/v2/yszk.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	28	,'现金流日报','ydzb/v2/xjlrb.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	28	,'各单位经营指标排名情况','ydzbRanking/v2/companys_ranking.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	5	,'财务数据分析',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	32	,'公司财务指标完成情况','NCzb/v2/AllCompanysNC_overview.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	32	,'单位财务指标完成情况','NCzb/v2/CompanysNC.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	32	,'财务对标需求','report/v2/dbxq.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	32	,'经济增加值','report/v2/jjzjz.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	32	,'财务-应交税费','cwyjsf/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	32	,'财务-产品大类毛利表','cwcpdlml/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	32	,'财务-经营性现金流','cwgbjyxxjl/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	32	,'财务-三项费用明细','report/v2/sxfy.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	32	,'外部单位数据总体分析','report/v2/ztdbfx.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	32	,'外部单位数据分类分析','report/v2/fldbfx.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	32	,'税务-预算统计','report/v2/cwsfysjhWrapper.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	32	,'税务-税金税负统计','report/v2/cwsfsjsfWrapper.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	32	,'税务-数据维护','report/v2/cwsfImportWrapperJsp.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	5	,'订单全过程管控',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	46	,'投标订单汇总','report/v2/bidCollectionWrapper.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	46	,'中标产品分型号汇总','report/v2/WinBidModelCollection.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	46	,'分阶段单位订单成本汇总','report/v2/gdwddfxhCollection.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	46	,'订单成本汇总表(按型号)','report/v2/gdwddCollection.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	46	,'公司订单分阶段汇总分析','report/v2/ddfjdCollection.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	46	,'完工订单分图号对比分析','report/v2/codeCollection.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	46	,'完工订单分型号对比分析','report/v2/modelCollection.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	46	,'订单信息明细','report/v2/ddDetailJsp.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	46	,'订单信息导入','report/v2/sddbImportWrapperJsp.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	5	,'市场分析',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	56	,'市场签约统计情况','report/v2/scqytjqk.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	56	,'重点产品签约情况查看','report/v2/scqy.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	56	,'行业签约情况查看','report/v2/scjb.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	56	,'重点产品签约情况录入','report/v2/scqyEntry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	56	,'行业签约情况录入','report/v2/scjbEntry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	56	,'国内市场项目签约导入','report/v2/gnscxmqywrapperImportJsp.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	56	,'合同明细','report/v2/gnscxmqyHtmx.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	56	,'投标明细','report/v2/gnscxmqyTbmx.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	56	,'项目明细','report/v2/gnscxmqyXmmx.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	5	,'价格库数据汇总',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	66	,'价格库数据展示','jcycljg/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	66	,'价格库数据录入','jcycljg/v2/import/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	5	,'经营管报管控',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	69	,'应收账款管报汇总','yszkgb/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	69	,'存货管报汇总','chgb/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	69	,'大宗物资管报','dzwzgb/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	69	,'未履约订单情况','wlydd/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	69	,'输变电产值/产量完成情况','sbdczclwcqk/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	69	,'输变电市场签约情况','sbdscqyqk/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	69	,'完工产品情况','wgcpqk/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	69	,'成本分析','cbfx/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	69	,'能源-周边市场情况','nyzbscqk/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	69	,'新能源存货','xnychFrame/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	5	,'新能源产业经营管控',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	80	,'工程一张表','report/v2/gcyzb.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	80	,'工程一张表数据导入','report/v2/gcyzbImportJsp.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	80	,'应收账款回款周报','report/v2/yszkhkzb.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	80	,'生产、发货和价格周报','report/v2/xnyzb.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	80	,'签约周报','report/v2/xnyqyzb.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	80	,'新特能源日报','report/v2/xtnyrb.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	5	,'存货管控',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	87	,'存货入库年份统计','report/v2/kcchrunftj.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	87	,'积压物资汇总','report/v2/kcjywz.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	87	,'存货导入','report/v2/kcwrapperImportJsp.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	5	,'人力资源信息',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	91	,'人力资源系统报表','report/v2/rlzyxibb.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	91	,'人力资源信息导入','report/v2/rlzyxtbbImportJsp.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	5	,'新产品信息', null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	5	,'质量信息指标录入','report/v2/zhzlEntry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	null	,'数据录入',null,	0	,'fa fa-pencil-square-o','fa fa-pencil-square-o');
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	96	,'计划指标录入',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	97	,'全年计划指标录入','entry/v2/zb.do?entryType=0',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	97	,'季度-月度末计划值录入','entry/v2/zb.do?entryType=1',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	96	,'预计/实际指标录入',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	100	,'20号预计指标录入','entry/v2/zb.do?entryType=2',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	100	,'28号预计指标录入','entry/v2/zb.do?entryType=3',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	100	,'实际指标录入','entry/v2/zb.do?entryType=4',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	96	,'经营分析录入',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	104	,'应收账款日报录入','dailyReport/v2/yszk.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	104	,'新能源签约周报录入','report/v2/xnyqyzbEntryJsp.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	104	,'新能源生产、发货和价格周报录入','report/v2/xnyzbEntryJsp.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	104	,'新能源应收账款回款周报导入','report/v2/yszkhkzbImportJsp.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	104	,'新特能源日报录入','report/v2/xtnyrbEntry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	104	,'新能源存货录入','xnychFrame/v2/entry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	96	,'通用经营管报录入',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	111	,'应收账款管报录入','yszkgb/v2/entry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	111	,'存货管报录入','chgb/v2/entry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	96	,'输变电产业经营管报录入',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	114	,'未履约订单情况录入','wlydd/v2/entry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	114	,'输变电产值/产量完成情况录入','sbdczclwcqk/v2/entry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	114	,'输变电市场签约情况录入','sbdscqyqk/v2/entry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	114	,'材料使用情况录入','wgcpqk/v2/entry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	96	,'能源产业经营报表录入',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	119	,'成本分析录入','cbfx/v2/entry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	119	,'能源-周边市场情况录入','nyzbscqk/v2/entry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	96	,'财务报表录入',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	122	,'财务-经营性现金流录入','cwgbjyxxjl/v2/entry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	null	,'数据审核',null,	0	,'fa fa-check-square-o','fa fa-check-square-o');
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	124	,'计划指标审核',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	125	,'全年计划指标审核','approve/v2/zb.do?approveType=0',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	125	,'季度-月度末计划值审核','approve/v2/zb.do?approveType=1',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	124	,'预计/实际指标审核',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	128	,'20预计指标审核','approve/v2/zb.do?approveType=2',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	128	,'28号预计指标审核','approve/v2/zb.do?approveType=3',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	128	,'实际指标审核','approve/v2/zb.do?approveType=4',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	null	,'质量数据管控', null,	1	,'fa fa-cogs','fa fa-cogs');
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	132	,'质量信息汇总分析',null,	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	133	,'产品一次送试及原材料合格率汇总及分析','cpzlqk/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	133	,'内外部质量问题汇总及分析','nwbzlqk/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	133	,'产品一次送试及原材料合格率审核及上报','cpzlqk/v2/approve.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	133	,'内外部质量问题审核及上报','nwbzlqk/v2/approve.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	133	,'产品一次送试录入','cpzlqk/v2/entry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	133	,'原材料合格率录入','report/v2/yclhglqktjEntry.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	133	,'内部质量问题导入','report/v2/nbzlqkImportJsp.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	133	,'外部质量问题导入','report/v2/wbzlqkImportJsp.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	null	,'质量数据管控',null,	0	,'fa fa-cogs', 'fa fa-cogs');
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	142	,'产品一次送试及原材料合格率汇总及分析','cpzlqk/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	142	,'内外部质量问题汇总及分析','nwbzlqk/v2/show.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	142	,'产品一次送试及原材料合格率审核及上报','cpzlqk/v2/approve.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	142	,'内外部质量问题审核及上报','nwbzlqk/v2/approve.do',	0	,null,null);
insert into navigator_item (parent, name, url, extend, iconClose, iconOpen) values 	(	94	,'新产品信息','report/v2/xcpjjxy.do',	0	,null,null);

insert into navigator_positive_auth (itemId, authId, authName) values 
(	1	,	0	,null),
(	2	,	null	,'admin'),
(	3	,	77	,null),
(	4	,	0	,null),
(	5	,	0	,null),
(	6	,	null	,'MarketAuth'),
(	7	,	null	,'scbsjLookup'),
(	8	,	null	,'scbsjLookup'),
(	9	,	null	,'scbsjLookup'),
(	10	,	null	,'scbsjEntry'),
(	11	,	null	,'scbsjEntry'),
(	12	,	0	,''),
(	13	,	null	,'CorpAuth'),
(	14	,	75	,null),
(	15	,	null	,'CorpAuth'),
(	16	,	null	,'CorpAuth'),
(	17	,	null	,'CorpAuth'),
(	18	,	null	,'CorpAuth'),
(	19	,	null	,'CorpAuth'),
(	20	,	76	,null),
(	21	,	null	,'CorpAuth'),
(	22	,	null	,'CorpAuth'),
(	23	,	null	,'JYEntryLookup'),
(	23	,	null	,'zhAuth'),
(	24	,	null	,'zhzlLookup'),
(	25	,	null	,'zhJyfxLookupAuth'),
(	26	,	null	,'zhJyfxLookupAuth'),
(	27	,	null	,'zhJyfxLookupAuth'),
(	28	,	null	,'JYAnalysisLookup'),
(	29	,	null	,'YSZKDialyLookup'),
(	30	,	null	,'XJLDialyLookup'),
(	31	,	null	,'CorpAuth'),
(	32	,	0	,null),
(	33	,	null	,'CorpAuth'),
(	34	,	null	,'isJydw'),
(	34	,	null	,'scgsdbqx'),
(	35	,	null	,'isJydw'),
(	35	,	null	,'scgsdbqx'),
(	36	,	null	,'isJydw'),
(	36	,	null	,'scgsdbqx'),
(	37	,	null	,'FinanceLookup'),
(	38	,	null	,'FinanceLookup'),
(	39	,	null	,'FinanceLookup'),
(	40	,	null	,'FinanceLookup'),
(	41	,	null	,'scgsdbqx'),
(	42	,	null	,'scgsdbqx'),
(	43	,	74	,null),
(	44	,	74	,null),
(	45	,	73	,null),
(	46	,	0	,null),
(	47	,	null	,'sddbLookup'),
(	48	,	null	,'sddbLookup'),
(	49	,	null	,'sddbLookup'),
(	50	,	null	,'sddbLookup'),
(	51	,	null	,'sddbLookup'),
(	52	,	null	,'sddbLookup'),
(	53	,	null	,'sddbLookup'),
(	54	,	null	,'sddbLookup'),
(	55	,	null	,'sddbImport'),
(	56	,	0	,null),
(	57	,	null	,'scbsjLookup'),
(	58	,	null	,'scbsjLookup'),
(	59	,	null	,'scbsjLookup'),
(	60	,	null	,'scbsjEntry'),
(	61	,	null	,'scbsjEntry'),
(	62	,	72	,null),
(	63	,	71	,null),
(	64	,	71	,null),
(	65	,	71	,null),
(	66	,	0	,null),
(	67	,	null	,'PriceLibAuth'),
(	68	,	null	,'PriceLibAuth'),
(	69	,	0	,null),
(	70	,	null	,'YszkgbLookup'),
(	71	,	null	,'ChgbLookup'),
(	72	,	null	,'SbdgbLookup'),
(	73	,	null	,'SbdgbLookup'),
(	74	,	null	,'SbdgbLookup'),
(	75	,	null	,'SbdgbLookup'),
(	76	,	null	,'SbdgbLookup'),
(	77	,	null	,'NygbLookup'),
(	78	,	null	,'NygbLookup'),
(	79	,	null	,'XnygbLookup'),
(	80	,	0	,null),
(	81	,	null	,'gcyzbLookup'),
(	82	,	null	,'gcyzbImport'),
(	83	,	null	,'xnyJyfxLookupAuth'),
(	84	,	null	,'xnyJyfxLookupAuth'),
(	85	,	null	,'xnyJyfxLookupAuth'),
(	86	,	null	,'xtnyrbLookupAuth'),
(	87	,	0	,null),
(	88	,	69	,null),
(	89	,	69	,null),
(	90	,	70	,null),
(	91	,	0	,null),
(	92	,	66	,null),
(	93	,	68	,null),
(	94	,	0	,null),
(	95	,	null	,'zhzlEntry'),
(	96	,	0	,null),
(	97	,	null	,'entryPlan'),
(	98	,	null	,'entryPlan'),
(	99	,	null	,'entryPlan'),
(	100	,	0	,null),
(	101	,	null	,'entryPredict'),
(	102	,	null	,'entryPredict'),
(	103	,	null	,'entryPredict'),
(	104	,	null	,'isJydw'),
(	105	,	null	,'isJydw'),
(	106	,	null	,'xnyJyfxEntryAuth'),
(	107	,	null	,'xnyJyfxEntryAuth'),
(	108	,	null	,'xnyJyfxEntryAuth'),
(	109	,	null	,'ZXtnyrbEntry'),
(	110	,	null	,'XnygbEntry'),
(	111	,	0	,null),
(	112	,	null	,'YszkgbEntry'),
(	113	,	null	,'ChgbEntry'),
(	114	,	null	,'SbdgbEntry'),
(	115	,	null	,'SbdgbEntry'),
(	116	,	null	,'SbdgbEntry'),
(	117	,	null	,'SbdgbEntry'),
(	118	,	null	,'SbdgbEntry'),
(	119	,	null	,'NygbEntry'),
(	120	,	null	,'NygbEntry'),
(	121	,	null	,'NYzbscqkEntry'),
(	122	,	null	,'FinanceEntry'),
(	123	,	null	,'FinanceEntry'),
(	124	,	0	,null),
(	125	,	null	,'approvePlan'),
(	126	,	null	,'approvePlan'),
(	127	,	null	,'approvePlan'),
(	128	,	null	,'approvePredict'),
(	129	,	null	,'approvePredict'),
(	130	,	null	,'approvePredict'),
(	131	,	null	,'approvePredict'),
(	132	,	null	,'QualityAuth'),
(	133	,	0	,'QualityAuth'),
(	134	,	null	,'QualityLookup'),
(	135	,	null	,'QualityLookup'),
(	136	,	null	,'QualityApprove'),
(	137	,	null	,'QualityApprove'),
(	138	,	null	,'QualityEntry'),
(	139	,	null	,'QualityEntry'),
(	140	,	null	,'I_EQualityImport'),
(	141	,	null	,'I_EQualityImport'),
(	142	,	0	, null),
(	143	,	null	,'QualityLookup'),
(	144	,	null	,'QualityLookup'),
(	145	,	null	,'QualityApprove'),
(	146	,	null	,'QualityApprove'),
(	147	,	67	, null);



insert into navigator_negative_auth (itemId, authId, authName, type) values 
(	12	,	null	,'MarketAuth', 1),
(	12	,	null	,'QualityAuth', 1),
(	28	,	null	,'MarketAuth', 1),
(	28	,	null	,'QualityAuth', 1),
(	32	,	null	,'MarketAuth', 1),
(	32	,	null	,'QualityAuth', 1),
(	46	,	null	,'MarketAuth', 1),
(	46	,	null	,'QualityAuth', 1),
(	56	,	null	,'MarketAuth', 1),
(	56	,	null	,'QualityAuth', 1),
(	66	,	null	,'MarketAuth', 1),
(	66	,	null	,'QualityAuth', 1),
(	69	,	null	,'MarketAuth', 1),
(	69	,	null	,'QualityAuth', 1),
(	80	,	null	,'MarketAuth', 1),
(	80	,	null	,'QualityAuth', 1),
(	87	,	null	,'MarketAuth', 1),
(	87	,	null	,'QualityAuth', 1),
(	91	,	null	,'MarketAuth', 1),
(	91	,	null	,'QualityAuth', 1),
(	94	,	null	,'MarketAuth', 1),
(	94	,	null	,'QualityAuth', 1),
(	95	,	null	,'MarketAuth', 1),
(	95	,	null	,'QualityAuth', 1),
(	96	,	null	,'MarketAuth', 1),
(	96	,	null	,'QualityAuth', 1),
(	124	,	null	,'MarketAuth', 1),
(	124	,	null	,'QualityAuth', 1),
(	133	,	null	,'QualityAuth', 0),
(	142	,	null	,'MarketAuth', 1),
(	142	,	null	,'QualityAuth', 1);
