insert into navigator_item (name, url, parent, extend) values	('财务-应收应付抹账', 'report/v2/48D8504E91873F9B81A7EF0C7FD1AFE8IWJ.do', 32, 0)

insert into navigator_positive_auth (itemId, authId) values
((select id from navigator_item where url='report/v2/48D8504E91873F9B81A7EF0C7FD1AFE8IWJ.do'), 103)


