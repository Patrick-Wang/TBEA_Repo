insert into navigator_item (name) values('自定义报表')
insert into navigator_item (name, parent, url) values('新建报表', (select id from navigator_item where name='自定义报表'), 'http://localhost:8081/saiku3/index.html?type=new')

insert into navigator_positive_auth (itemId, authId) values
((select id from navigator_item where name='自定义报表'), 0),
((select id from navigator_item where name='新建报表'), 3)