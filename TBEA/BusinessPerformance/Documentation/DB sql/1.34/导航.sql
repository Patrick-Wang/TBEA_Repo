insert into navigator_item (name, url, parent, extend) values	('财务-即期结售汇业务查看', 'report/v2/50E1A0738E3CC48ECF5E06CC38B0B76CSWJ.do', 167, 0)
insert into navigator_item (name, url, parent, extend) values	('财务-即期结售汇业务录入', 'report/v2/50E1A0738E3CC48ECF5E06CC38B0B76CEWJ.do', 167, 0)
insert into navigator_item (name, url, parent, extend) values	('财务-远期结售汇业务查看', 'report/v2/64EBE83612425900EE5DFED8A902E8FASWJ.do', 167, 0)
insert into navigator_item (name, url, parent, extend) values	('财务-远期结售汇业务录入', 'report/v2/64EBE83612425900EE5DFED8A902E8FAEWJ.do', 167, 0)
insert into navigator_item (name, url, parent, extend) values	('财务-外汇收支平衡附表查看', 'report/v2/FAB719DB827A05373457CA993A90AD77SWJ.do', 167, 0)
insert into navigator_item (name, url, parent, extend) values	('财务-外汇收支平衡附表录入', 'report/v2/FAB719DB827A05373457CA993A90AD77EWJ.do', 167, 0)


insert into navigator_item (name, url, parent, extend) values	('进出口合同', 'report/v2/FAB719DB827A05373457CA993A90AD77EWJ.do', 167, 0)

insert into navigator_positive_auth (itemId, authId) values
((select id from navigator_item where url='report/v2/50E1A0738E3CC48ECF5E06CC38B0B76CSWJ.do'), 96),
((select id from navigator_item where url='report/v2/50E1A0738E3CC48ECF5E06CC38B0B76CSWJ.do'), 98),
((select id from navigator_item where url='report/v2/50E1A0738E3CC48ECF5E06CC38B0B76CSWJ.do'), 99),
((select id from navigator_item where url='report/v2/50E1A0738E3CC48ECF5E06CC38B0B76CEWJ.do'), 102),
((select id from navigator_item where url='report/v2/50E1A0738E3CC48ECF5E06CC38B0B76CEWJ.do'), 100),
((select id from navigator_item where url='report/v2/50E1A0738E3CC48ECF5E06CC38B0B76CEWJ.do'), 101),
((select id from navigator_item where url='report/v2/64EBE83612425900EE5DFED8A902E8FASWJ.do'), 96),
((select id from navigator_item where url='report/v2/64EBE83612425900EE5DFED8A902E8FASWJ.do'), 98),
((select id from navigator_item where url='report/v2/64EBE83612425900EE5DFED8A902E8FASWJ.do'), 99),
((select id from navigator_item where url='report/v2/64EBE83612425900EE5DFED8A902E8FAEWJ.do'), 102),
((select id from navigator_item where url='report/v2/64EBE83612425900EE5DFED8A902E8FAEWJ.do'), 100),
((select id from navigator_item where url='report/v2/64EBE83612425900EE5DFED8A902E8FAEWJ.do'), 101),

((select id from navigator_item where url='report/v2/FAB719DB827A05373457CA993A90AD77SWJ.do'), 96),
((select id from navigator_item where url='report/v2/FAB719DB827A05373457CA993A90AD77SWJ.do'), 98),
((select id from navigator_item where url='report/v2/FAB719DB827A05373457CA993A90AD77SWJ.do'), 99),
((select id from navigator_item where url='report/v2/FAB719DB827A05373457CA993A90AD77EWJ.do'), 102),
((select id from navigator_item where url='report/v2/FAB719DB827A05373457CA993A90AD77EWJ.do'), 100),
((select id from navigator_item where url='report/v2/FAB719DB827A05373457CA993A90AD77EWJ.do'), 101)



update navigator_item set name = SUBSTRING(name, 4, DataLength(name) - 3)  where name like '财务-%' and parent = 167

insert into navigator_item (name, url, parent, extend) values	('已导入进出口外汇合同', 'report/v2/B60D4AAC5CB0C71C5F807D2F68E9F535SWJ.do', 167, 0)
insert into navigator_positive_auth (itemId, authId) values
((select id from navigator_item where url='report/v2/B60D4AAC5CB0C71C5F807D2F68E9F535SWJ.do'), 96),
((select id from navigator_item where url='report/v2/B60D4AAC5CB0C71C5F807D2F68E9F535SWJ.do'), 98),
((select id from navigator_item where url='report/v2/B60D4AAC5CB0C71C5F807D2F68E9F535SWJ.do'), 99),