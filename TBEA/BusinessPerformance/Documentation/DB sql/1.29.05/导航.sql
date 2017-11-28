insert into navigator_item (name, url, parent, extend) values	('经营分析报告', 'report/v2/operWorkReportShowClr.do', 28, 0)
insert into navigator_item (name, parent, extend) values	('财务外汇管理', 5, 0)
insert into navigator_item (name, url, parent, extend) values	('进出口外汇合同导入', 'report/v2/B60D4AAC5CB0C71C5F807D2F68E9F535IWJ.do', (select id from navigator_item where name='财务外汇管理'), 0)
insert into navigator_item (name, url, parent, extend) values	('进口外汇合同录入', 'report/v2/D502EC08D6FB66F4F3B05B3E9B014133EWJ.do', (select id from navigator_item where name='财务外汇管理'), 0)
insert into navigator_item (name, url, parent, extend) values	('出口外汇合同录入', 'report/v2/51480AEBDE770C9A1AB0824BF8685B66EWJ.do', (select id from navigator_item where name='财务外汇管理'), 0)
insert into navigator_item (name, url, parent, extend) values	('进口外汇合同查看', 'report/v2/D502EC08D6FB66F4F3B05B3E9B014133SWJ.do', (select id from navigator_item where name='财务外汇管理'), 0)
insert into navigator_item (name, url, parent, extend) values	('出口外汇合同查看', 'report/v2/51480AEBDE770C9A1AB0824BF8685B66SWJ.do', (select id from navigator_item where name='财务外汇管理'), 0)


insert into navigator_positive_auth (itemId, authId) values
((select id from navigator_item where url='report/v2/operWorkReportShowClr.do'), 95),

((select id from navigator_item where name='财务外汇管理'), 0),
((select id from navigator_item where name='进出口外汇合同导入'), 97),

((select id from navigator_item where name='进口外汇合同录入'), 100),
((select id from navigator_item where name='出口外汇合同录入'), 100),
((select id from navigator_item where name='进口外汇合同录入'), 101),
((select id from navigator_item where name='出口外汇合同录入'), 101),
((select id from navigator_item where name='进口外汇合同录入'), 102),
((select id from navigator_item where name='出口外汇合同录入'), 102),

((select id from navigator_item where name='进口外汇合同查看'), 96),
((select id from navigator_item where name='出口外汇合同查看'), 96),
((select id from navigator_item where name='进口外汇合同查看'), 98),
((select id from navigator_item where name='出口外汇合同查看'), 98),
((select id from navigator_item where name='进口外汇合同查看'), 99),
((select id from navigator_item where name='出口外汇合同查看'), 99)


