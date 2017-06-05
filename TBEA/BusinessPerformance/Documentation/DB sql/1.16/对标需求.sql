if not exists(select * from syscolumns where id = object_id('identifier_dbxq_zb') and name='SN') begin
	alter table identifier_dbxq_zb add SN int;
end

update identifier_dbxq_zb set SN = 100 where name like '净资产收益率%';
update identifier_dbxq_zb set SN = 200 where name like '总资产报酬率%';
update identifier_dbxq_zb set SN = 300 where name like '销售（营业）利润率%';
update identifier_dbxq_zb set SN = 400 where name like '盈余现金保障倍数%';
update identifier_dbxq_zb set SN = 500 where name like '资本收益率%';
update identifier_dbxq_zb set SN = 600 where name like '总资产周转率（次）%';
update identifier_dbxq_zb set SN = 700 where name like '应收账款周转率（次）%';
update identifier_dbxq_zb set name = '资产现金收回率（%）' where name like '资产现金%';
update identifier_dbxq_zb set SN = 800 where name like '资产现金%';
update identifier_dbxq_zb set SN = 900 where name like '流动资产周转率%';
update identifier_dbxq_zb set SN = 1000 where name like '资产负债率%';
update identifier_dbxq_zb set SN = 1100 where name like '已获利息倍数%';
update identifier_dbxq_zb set name = '速动比率（%）' where name like '速度比率%';
update identifier_dbxq_zb set SN = 1200 where name like '速动比率（%）';
update identifier_dbxq_zb set SN = 1300 where name like '现金流动负债比率%';
update identifier_dbxq_zb set SN = 1400 where name like '带息负债比率%';
update identifier_dbxq_zb set SN = 1500 where name like '或有负债比率%';
update identifier_dbxq_zb set SN = 1600 where name like '销售（营业）增长率%';
update identifier_dbxq_zb set SN = 1700 where name like '资本保值增值率%';
update identifier_dbxq_zb set SN = 1800 where name like '总资产增长率%';
update identifier_dbxq_zb set SN = 1900 where name like '销售（营业）利润增长率%';
update identifier_dbxq_zb set SN = 2000 where name like '存货周转率%';
update identifier_dbxq_zb set SN = 2100 where name like '资本积累率%';
update identifier_dbxq_zb set SN = 2200 where name like '两金占流动资产比重%';

DBCC CHECKIDENT(identifier_dbxq_zb, RESEED, 28)
delete from identifier_dbxq_zb where SN % 100 != 0
insert into identifier_dbxq_zb (SN, name) values
(	101	,'	净利润')	,
(	102	,'	期初所有者权益余额'    )	,
(	103	,'	期末所有者权益余额'    )	,

(	201	,'	净利润'    )	,
(	202	,'	期初资产总额'    )	,
(	203	,'	期末资产总额'    )	,

(	301	,'	营业总收入'    )	,
(	302	,'	营业利润'    )	,

(	401	,'	经营现金净流量'    )	,
(	402	,'	净利润'    )	,

(	501	,'	净利润'    )	,
(	502	,'	期初实收资本'    )	,
(	503	,'	期末实收资本'    )	,
(	504	,'	期初资本公积'    )	,
(	505	,'	期末资本公积'    )	,

(	601	,'	营业总收入')	,
(	602	,'	期初资产总额')	,
(	603	,'	期末资产总额')	,

(	701	,'	营业总收入')	,
(	702	,'	应收账款期初数')	,
(	703	,'	应收账款期末数')	,

(	801	,'	经营现金净流量')	,
(	802	,'	期初资产总额')	,
(	803	,'	期末资产总额')	,

(	901	,'	营业总收入')	,
(	902	,'	期初流动资产总额')	,
(	903	,'	期末流动资产总额')	,

(	1001	,'	负债总额')	,
(	1002	,'	资产总额')	,

(	1101	,'	利润总额')	,
(	1102	,'	利息支出')	,

(	1201	,'	流动资产')	,
(	1202	,'	存货')	,
(	1203	,'	预付账款')	,
(	1204	,'	流动负债')	,

(	1301	,'	经营现金净流量')	,
(	1302	,'	流动负债')	,

(	1401	,'	短期借款')	,
(	1402	,'	一年内到期的非流动负债')	,
(	1403	,'	其他流动负债（其中的短期融资券）')	,
(	1404	,'	长期借款')	,
(	1405	,'	应付债券')	,
(	1406	,'	应付利息')	,
(	1407	,'	负债总额')	,

(	1501	,'	预计负债余额')	,
(	1502	,'	所有者权益')	,

(	1601	,'	本年营业总收入')	,
(	1602	,'	上年主营业务收入')	,

(	1701	,'	本期净利润')	,
(	1702	,'	期初所有者权益')	,

(	1801	,'	期末资产总额')	,
(	1802	,'	期初资产总额')	,

(	1901	,'	本年营业利润总额')	,
(	1902	,'	上年营业利润总额')	,

(	2001	,'	主营业务成本')	,
(	2002	,'	期初存货')	,
(	2003	,'	期末存货')	,

(	2101	,'	期末所有者权益')	,
(	2102	,'	期初所有者权益')	,

(	2201	,'	应收账款')	,
(	2202	,'	存货')	,
(	2203	,'	流动资产');
