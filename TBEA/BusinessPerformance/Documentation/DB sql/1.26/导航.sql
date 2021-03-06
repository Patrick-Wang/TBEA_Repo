

insert into navigator_item (name, url, parent, extend) values	('用户管理', 'report/v2/userMgr.do', 1, 0)
insert into navigator_item (name, url, parent, extend) values	('角色管理', 'report/v2/roleMgr.do', 1, 0)
insert into navigator_positive_auth (itemId, authId) values
((select id from navigator_item where url='report/v2/userMgr.do'), 88),
((select id from navigator_item where url='report/v2/roleMgr.do'), 88)

--update navigator_item set name='财务-融资业务明细'  where url = 'report/v2/BAEA6B3D5073197672D2E28C858F9109ShowWrapperJsp.do'

--update navigator_item set name='财务-融资业务导入', parent=32  where url = 'report/v2/BAEA6B3D5073197672D2E28C858F9109ImportWrapperJsp.do'


insert into navigator_item (name, url, parent, extend) values	('预警台账', null, 5, 0)
insert into navigator_item (name, url, parent, extend) values	('预警台账查看', 'report/v2/3AADA80D5B18E419386688AD36592212ShowWrapperJsp.do', (select id from navigator_item where name='预警台账'), 0)
insert into navigator_item (name, url, parent, extend) values	('预警台账录入', 'report/v2/3AADA80D5B18E419386688AD36592212EntryWrapperJsp.do', (select id from navigator_item where name='预警台账'), 0)
insert into navigator_item (name, url, parent, extend) values	('预警台账导入', 'report/v2/3AADA80D5B18E419386688AD36592212ImportWrapperJsp.do', (select id from navigator_item where name='预警台账'), 0)


insert into navigator_positive_auth (itemId, authId) values
((select id from navigator_item where  name='预警台账'), 0),
((select id from navigator_item where url='report/v2/3AADA80D5B18E419386688AD36592212ShowWrapperJsp.do'), 90),
((select id from navigator_item where url='report/v2/3AADA80D5B18E419386688AD36592212ShowWrapperJsp.do'), 92),
((select id from navigator_item where url='report/v2/3AADA80D5B18E419386688AD36592212EntryWrapperJsp.do'), 89),
((select id from navigator_item where url='report/v2/3AADA80D5B18E419386688AD36592212EntryWrapperJsp.do'), 91),
((select id from navigator_item where url='report/v2/3AADA80D5B18E419386688AD36592212ImportWrapperJsp.do'), 89),
((select id from navigator_item where url='report/v2/3AADA80D5B18E419386688AD36592212ImportWrapperJsp.do'), 91)

