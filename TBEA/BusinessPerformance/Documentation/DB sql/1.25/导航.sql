

insert into navigator_item (name, url, parent, extend) values	('用户管理', 'report/v2/userMgr.do', 1, 0)
insert into navigator_item (name, url, parent, extend) values	('角色管理', 'report/v2/roleMgr.do', 1, 0)
insert into navigator_positive_auth (itemId, authId) values
((select id from navigator_item where url='report/v2/userMgr.do'), 80),
((select id from navigator_item where url='report/v2/roleMgr.do'), 80)

--update navigator_item set name='财务-融资业务明细'  where url = 'report/v2/BAEA6B3D5073197672D2E28C858F9109ShowWrapperJsp.do'

--update navigator_item set name='财务-融资业务导入', parent=32  where url = 'report/v2/BAEA6B3D5073197672D2E28C858F9109ImportWrapperJsp.do'
