insert into navigator_item (parent, name, url, extend) values (32, '财务-融资业务明细', 'report/v2/BAEA6B3D5073197672D2E28C858F9109ShowWrapperJsp.do', 0)
insert into navigator_item (parent, name, url, extend) values (32, '财务-融资业务导入', 'report/v2/BAEA6B3D5073197672D2E28C858F9109ImportWrapperJsp.do', 0)

insert into navigator_positive_auth (itemId, authId, authName) values 
((select id from navigator_item where url = 'report/v2/BAEA6B3D5073197672D2E28C858F9109ShowWrapperJsp.do'),	84,null),
((select id from navigator_item where url = 'report/v2/BAEA6B3D5073197672D2E28C858F9109ImportWrapperJsp.do'),	85,null);





--update navigator_item set name='财务-融资业务明细'  where url = 'report/v2/BAEA6B3D5073197672D2E28C858F9109ShowWrapperJsp.do'

--update navigator_item set name='财务-融资业务导入', parent=32  where url = 'report/v2/BAEA6B3D5073197672D2E28C858F9109ImportWrapperJsp.do'
