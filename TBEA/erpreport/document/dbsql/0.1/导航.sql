CREATE TABLE CUX_NAVIGATEITEM_T(
	id number(8) NOT NULL,
	parent number(8),
	title varchar(100) NOT NULL,
	icon varchar(100), 
	spread number(4), --0, 1
	url varchar(100),
	type number(4), --0 top, 1 left
	PRIMARY KEY(ID)
);

insert into CUX_NAVIGATEITEM_T (id, parent, title, icon, spread, url, type) values
(1, null, '财务', 'fa fa-fax', 0, null, 0);
insert into CUX_NAVIGATEITEM_T (id, parent, title, icon, spread, url, type) values
(2, null, '生产', 'fa fa-fax', 0, null, 0);
insert into CUX_NAVIGATEITEM_T (id, parent, title, icon, spread, url, type) values
(3, 1, '总账', 'fa fa-fax', 0, null, 1);
insert into CUX_NAVIGATEITEM_T (id, parent, title, icon, spread, url, type) values
(4, 1, '应付', 'fa fa-fax', 0, null, 1);
insert into CUX_NAVIGATEITEM_T (id, parent, title, icon, spread, url, type) values
(5, 1, '应收', 'fa fa-fax', 0, null, 1);
insert into CUX_NAVIGATEITEM_T (id, parent, title, icon, spread, url, type) values
(6, 2, '在制品管理', 'fa fa-fax', 0, null, 1);
insert into CUX_NAVIGATEITEM_T (id, parent, title, icon, spread, url, type) values
(7, 3, '现金流量表', 'fa fa-fax', 0, '/', 1);
insert into CUX_NAVIGATEITEM_T (id, parent, title, icon, spread, url, type) values
(8, 3, '科目余额查询', 'fa fa-fax', 0, '/erpreport/report/37CB4530FDDDB6A0000FFEBF3C178862SHOWCLR.do', 1);
insert into CUX_NAVIGATEITEM_T (id, parent, title, icon, spread, url, type) values
(9, 4, '应付账龄报表', 'fa fa-fax', 0, '/', 1);
insert into CUX_NAVIGATEITEM_T (id, parent, title, icon, spread, url, type) values
(10, 5, '应收收款报表', 'fa fa-fax', 0, '/', 1);
insert into CUX_NAVIGATEITEM_T (id, parent, title, icon, spread, url, type) values
(11, 6, '车间月度完工入库清单', 'fa fa-fax', 0, '/', 1);

commit;

CREATE TABLE CUX_NAVIGATEAUTHORITY_T(
	navigator_item_id number(8) NOT NULL,
	authority varchar(100)
);
insert into CUX_NAVIGATEAUTHORITY_T (navigator_item_id, authority) values
(7, '1001AP_股份本部_应付会计');
insert into CUX_NAVIGATEAUTHORITY_T (navigator_item_id, authority) values
(8, '1001AP_股份本部_应付会计');
insert into CUX_NAVIGATEAUTHORITY_T (navigator_item_id, authority) values
(9, '1001AP_股份本部_应付会计');
insert into CUX_NAVIGATEAUTHORITY_T (navigator_item_id, authority) values
(10, '1001AP_股份本部_应付会计');
insert into CUX_NAVIGATEAUTHORITY_T (navigator_item_id, authority) values
(11, '1001AP_股份本部_应付会计');
commit;

--测试用户DCSUP06
CREATE TABLE CUX_USREREQUEST_T(
	id number(8) NOT NULL,
	usageId number(8) not null;
	requestTime datetime;
	responseTime datetime;
	url varchar(200);
	isAjax number(4);
	PRIMARY KEY(ID)
);
CREATE SEQUENCE CUX_USREREQUEST_T_ID_SEQ MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE OR REPLACE TRIGGER CUX_USREREQUEST_T_INS_TRG BEFORE INSERT ON CUX_USREREQUEST_T FOR EACH ROW WHEN(NEW.ID IS NULL)
BEGIN
SELECT CUX_USREREQUEST_T_ID_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
END;

CREATE TABLE CUX_USREUSAGE_T(
	id number(8) NOT NULL,
	userName varchar(100); 
	loginTime datetime;
	logoutTime datetime;
	lastAccessedTime datetime;
	ip varchar(100);
	PRIMARY KEY(ID)
);
CREATE SEQUENCE CUX_USREUSAGE_T_ID_SEQ MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;
 
CREATE OR REPLACE TRIGGER CUX_USREUSAGE_T_INS_TRG BEFORE INSERT ON CUX_USREUSAGE_T FOR EACH ROW WHEN(NEW.ID IS NULL)
BEGIN
SELECT CUX_USREUSAGE_T_ID_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
END;