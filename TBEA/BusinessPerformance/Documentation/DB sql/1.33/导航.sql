insert into navigator_item (name, url, parent, extend) values	('财务-外汇收支平衡查看', 'report/v2/9569BB9CEF88B0ADDFF9A7FCA0B46FA6SWJ.do', 167, 0)
insert into navigator_item (name, url, parent, extend) values	('财务-外汇收支平衡录入', 'report/v2/9569BB9CEF88B0ADDFF9A7FCA0B46FA6EWJ.do', 167, 0)

insert into navigator_positive_auth (itemId, authId) values
((select id from navigator_item where url='report/v2/9569BB9CEF88B0ADDFF9A7FCA0B46FA6SWJ.do'), 96),
((select id from navigator_item where url='report/v2/9569BB9CEF88B0ADDFF9A7FCA0B46FA6SWJ.do'), 98),
((select id from navigator_item where url='report/v2/9569BB9CEF88B0ADDFF9A7FCA0B46FA6SWJ.do'), 99),
((select id from navigator_item where url='report/v2/9569BB9CEF88B0ADDFF9A7FCA0B46FA6EWJ.do'), 102),
((select id from navigator_item where url='report/v2/9569BB9CEF88B0ADDFF9A7FCA0B46FA6EWJ.do'), 100),
((select id from navigator_item where url='report/v2/9569BB9CEF88B0ADDFF9A7FCA0B46FA6EWJ.do'), 101)


