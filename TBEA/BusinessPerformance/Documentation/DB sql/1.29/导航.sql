insert into navigator_item (name, url, parent, extend) values	('存货明细', 'report/v2/183377BE33E01D50D0178B54FD00AD9BSWJ.do', 87, 0)

insert into navigator_positive_auth (itemId, authId) values
((select id from navigator_item where url='report/v2/183377BE33E01D50D0178B54FD00AD9BSWJ.do'), 69)

