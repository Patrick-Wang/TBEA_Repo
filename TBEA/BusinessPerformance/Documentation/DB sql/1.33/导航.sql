insert into navigator_item (name, url, parent, extend) values	('财务-外汇收支平衡查看', 'report/v2/9569BB9CEF88B0ADDFF9A7FCA0B46FA6SWJ.do', 167, 0)
insert into navigator_item (name, url, parent, extend) values	('财务-外汇收支平衡录入', 'report/v2/9569BB9CEF88B0ADDFF9A7FCA0B46FA6EWJ.do', 167, 0)

insert into navigator_positive_auth (itemId, authId) values
((select id from navigator_item where url='report/v2/9569BB9CEF88B0ADDFF9A7FCA0B46FA6SWJ.do'), 104)
((select id from navigator_item where url='report/v2/9569BB9CEF88B0ADDFF9A7FCA0B46FA6SWJ.do'), 106)
((select id from navigator_item where url='report/v2/9569BB9CEF88B0ADDFF9A7FCA0B46FA6SWJ.do'), 107)
((select id from navigator_item where url='report/v2/9569BB9CEF88B0ADDFF9A7FCA0B46FA6EWJ.do'), 105)
((select id from navigator_item where url='report/v2/9569BB9CEF88B0ADDFF9A7FCA0B46FA6EWJ.do'), 108)
((select id from navigator_item where url='report/v2/9569BB9CEF88B0ADDFF9A7FCA0B46FA6EWJ.do'), 109)


