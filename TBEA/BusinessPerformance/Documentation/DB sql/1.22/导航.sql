update  navigator_positive_auth set authName = null, authId = 1 where authName = 'isJydw';
update  navigator_positive_auth set authName = null, authId = 2 where authName = 'JYAnalysisEntry';
update  navigator_positive_auth set authName = null, authId = 5 where authName = 'XJLDialyLookup';
update  navigator_positive_auth set authName = null, authId = 6 where authName = 'JYEntryLookup';
update  navigator_positive_auth set authName = null, authId = 7 where authName = 'PriceLibAuth';
update  navigator_positive_auth set authName = null, authId = 11 where authName = 'ChgbLookup';
update  navigator_positive_auth set authName = null, authId = 9 where authName = 'YszkgbLookup';
update  navigator_positive_auth set authName = null, authId = 13 where authName = 'SbdgbLookup';
update  navigator_positive_auth set authName = null, authId = 17 where authName = 'XnygbLookup';
update  navigator_positive_auth set authName = null, authId = 15 where authName = 'NygbLookup';
update  navigator_positive_auth set authName = null, authId = 10 where authName = 'ChgbEntry';
update  navigator_positive_auth set authName = null, authId = 8 where authName = 'YszkgbEntry';
update  navigator_positive_auth set authName = null, authId = 12 where authName = 'SbdgbEntry';
update  navigator_positive_auth set authName = null, authId = 16 where authName = 'XnygbEntry';
update  navigator_positive_auth set authName = null, authId = 14 where authName = 'NygbEntry';
update  navigator_positive_auth set authName = null, authId = 18 where authName = 'NYzbscqkEntry';
update  navigator_positive_auth set authName = null, authId = 21 where authName = 'QualityEntry';
update  navigator_positive_auth set authName = null, authId = 20 where authName = 'QualityLookup';
update  navigator_positive_auth set authName = null, authId = 23 where authName = 'FinanceLookup';
update  navigator_positive_auth set authName = null, authId = 24 where authName = 'FinanceEntry';
update  navigator_positive_auth set authName = null, authId = 34 where authName = 'zhJyfxLookupAuth';
update  navigator_positive_auth set authName = null, authId = 35 where authName = 'xtnyrbEntryAuth';
update  navigator_positive_auth set authName = null, authId = 36 where authName = 'xtnyrbLookupAuth';
update  navigator_positive_auth set authName = null, authId = 78 where authName = 'I_EQualityImport';
update  navigator_positive_auth set authName = null, authId = 56 where authName = 'scgsdbqx';
update  navigator_positive_auth set authName = null, authId = 57 where authName = 'scbsjLookup';
update  navigator_positive_auth set authName = null, authId = 58 where authName = 'scbsjEntry';
update  navigator_positive_auth set authName = null, authId = 59 where authName = 'gcyzbLookup';
update  navigator_positive_auth set authName = null, authId = 60 where authName = 'gcyzbImport';
update  navigator_positive_auth set authName = null, authId = 61 where authName = 'sddbLookup';
update  navigator_positive_auth set authName = null, authId = 62 where authName = 'sddbImport';
update  navigator_positive_auth set authName = null, authId = 63 where authName = 'zhzlEntry';
update  navigator_positive_auth set authName = null, authId = 64 where authName = 'zhzlLookup';
update  navigator_positive_auth set authName = null, authId = 65 where authName = 'zk';
update  navigator_positive_auth set authName = null, authId = 66 where authName = 'rl';
update  navigator_positive_auth set authName = null, authId = 67 where authName = 'xcp';
update  navigator_positive_auth set authName = null, authId = 68 where authName = 'rlImport';
update  navigator_positive_auth set authName = null, authId = 80 where authName = 'admin';
update  navigator_positive_auth set authName = null, authId = 81 where authName = 'zhAuth';


update  navigator_positive_auth set authName = null, authId = 3 where authName = 'JYAnalysisLookup';
insert into navigator_positive_auth (itemId, authId, authName) values 
(	28	,	4	,null),
(	28	,	27	,null),
(	28	,	28	,null);


update  navigator_positive_auth set authName = null, authId = 4 where authName = 'YSZKDialyLookup';
insert into navigator_positive_auth (itemId, authId, authName) values 
(	29	,	27	,null),
(	29	,	28	,null);





update navigator_positive_auth set authName = null where id in (12, 137)
update navigator_positive_auth set authName = null, authId = 22 where id in (140,141,149,150)

insert into navigator_positive_auth (itemId, authId, authName) values 
(	136	,	53	,null),
(	136	,	54	,null),
(	136	,	55	,null),
(	137	,	53	,null),
(	137	,	54	,null),
(	137	,	55	,null),
(	145	,	53	,null),
(	145	,	54	,null),
(	145	,	55	,null),
(	146	,	53	,null),
(	146	,	54	,null),
(	146	,	55	,null);

update navigator_positive_auth set authName = null, authId = 0 where id = 108;
insert into navigator_positive_auth (itemId, authId, authName) values 
(	105	,	1	,null),
(	105	,	25	,null),
(	105	,	29	, null),
(	105	,	31	, null),
(	105	,	35	, null);

update navigator_positive_auth set authName = null, authId = 25 where id in (110,111,112)
insert into navigator_positive_auth (itemId, authId, authName) values 
(	106	,	29	,null),
(	106	,	31	,null),
(	107	,	29	,null),
(	107	,	31	,null),
(	108	,	29	,null),
(	108	,	31	,null);

update navigator_positive_auth set authName = null, authId = 26 where id in (83,84,85)
insert into navigator_positive_auth (itemId, authId, authName) values 
(	83	,	30	,null),
(	83	,	32	,null),
(	84	,	30	,null),
(	84	,	32	,null),
(	85	,	30	,null),
(	85	,	32	,null);


update navigator_item set extend = 1 where id = 1;
update navigator_item set url = 'redirector/redirect.do?url=dashboard/dashboard.do' where id = 4;


insert into navigator_item (parent, name, url, extend) values (104, '天池能源报表导入', 'report/v2/tcnycrdcscbbImportWrapperJsp.do', 0)
insert into navigator_item (parent, name, url, extend) values (69, '天池能源日报', 'report/v2/tcnycrdcDayReport.do', 0)
insert into navigator_item (parent, name, url, extend) values (69, '天池能源月报', 'report/v2/tcnycrdcMonthReport.do', 0)
insert into navigator_positive_auth (itemId, authId, authName) values 
((select id from navigator_item where name = '天池能源报表导入'),	82	,null),
((select id from navigator_item where name = '天池能源日报'),	83	,null),
((select id from navigator_item where name = '天池能源月报'),	83	,null)