insert into navigator_item (name, url, parent, extend) values	('逾期应收账查看', 'report/v2/1560C88098A32BB156FE0967D1FB6EE0ShowWrapperJsp.do', (select id from navigator_item where name='应收账款管理'), 0)
insert into navigator_item (name, url, parent, extend) values	('逾期应收账录入', 'report/v2/1560C88098A32BB156FE0967D1FB6EE0EntryWrapperJsp.do', (select id from navigator_item where name='应收账款管理'), 0)


insert into navigator_positive_auth (itemId, authId) values
((select id from navigator_item where url='report/v2/1560C88098A32BB156FE0967D1FB6EE0ShowWrapperJsp.do'), 90),
((select id from navigator_item where url='report/v2/1560C88098A32BB156FE0967D1FB6EE0ShowWrapperJsp.do'), 92),
((select id from navigator_item where url='report/v2/1560C88098A32BB156FE0967D1FB6EE0EntryWrapperJsp.do'), 89),
((select id from navigator_item where url='report/v2/1560C88098A32BB156FE0967D1FB6EE0EntryWrapperJsp.do'), 91)

