insert into navigator_item (parent, name, url, extend) values (32, '融资业务', 'report/v2/BAEA6B3D5073197672D2E28C858F9109ShowWrapperJsp.do', 0)
insert into navigator_item (parent, name, url, extend) values (122, '融资业务导入', 'report/v2/BAEA6B3D5073197672D2E28C858F9109ImportWrapperJsp', 0)

insert into navigator_positive_auth (itemId, authId, authName) values 
((select id from navigator_item where url = 'report/v2/BAEA6B3D5073197672D2E28C858F9109ShowWrapperJsp.do'),	84,null),
((select id from navigator_item where url = 'report/v2/BAEA6B3D5073197672D2E28C858F9109ImportWrapperJsp.do'),	85,null);
