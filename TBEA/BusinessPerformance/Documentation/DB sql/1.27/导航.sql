insert into navigator_item (name, url, parent, extend) values	('应收账款管理', null, 5, 0)
insert into navigator_item (name, url, parent, extend) values	('回款计划查看', 'report/v2/DB508EDBC9A8A56FFBDF6604877A374AShowWrapperJsp.do', (select id from navigator_item where name='应收账款管理'), 0)
insert into navigator_item (name, url, parent, extend) values	('回款计划导入', 'report/v2/DB508EDBC9A8A56FFBDF6604877A374AImportWrapperJsp.do', (select id from navigator_item where name='应收账款管理'), 0)


insert into navigator_positive_auth (itemId, authId) values
((select id from navigator_item where  name='应收账款管理'), 0),
((select id from navigator_item where url='report/v2/DB508EDBC9A8A56FFBDF6604877A374AShowWrapperJsp.do'), 94),
((select id from navigator_item where url='report/v2/DB508EDBC9A8A56FFBDF6604877A374AImportWrapperJsp.do'), 93)

