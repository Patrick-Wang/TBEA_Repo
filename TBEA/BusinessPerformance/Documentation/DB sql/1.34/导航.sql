insert into navigator_item (name, url, parent, extend) values	('财务-即期结售汇业务查看', 'report/v2/50E1A0738E3CC48ECF5E06CC38B0B76CSWJ.do', 167, 0)
insert into navigator_item (name, url, parent, extend) values	('财务-即期结售汇业务录入', 'report/v2/50E1A0738E3CC48ECF5E06CC38B0B76CEWJ.do', 167, 0)
insert into navigator_item (name, url, parent, extend) values	('财务-远期结售汇业务查看', 'report/v2/64EBE83612425900EE5DFED8A902E8FASWJ.do', 167, 0)
insert into navigator_item (name, url, parent, extend) values	('财务-远期结售汇业务录入', 'report/v2/64EBE83612425900EE5DFED8A902E8FAEWJ.do', 167, 0)

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
((select id from navigator_item where url='report/v2/64EBE83612425900EE5DFED8A902E8FAEWJ.do'), 101)
