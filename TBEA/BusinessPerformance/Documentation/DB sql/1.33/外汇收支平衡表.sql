--外汇收支平衡表
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'foreign_exchange_budget')
DROP TABLE foreign_exchange_budget
CREATE TABLE [dbo].[foreign_exchange_budget](
	[id] [int] IDENTITY(1,1) NOT NULL,
	dwmc varchar(100), --单位名称
	bz varchar(100), --币种
	yhzh numeric(18, 4), --银行账户
	sczj numeric(18, 4), --上存资金
	xj1 numeric(18, 4), --小计
	dq numeric(18, 4), --定期
	tz numeric(18, 4), --通知
	xj2 numeric(18, 4), --小计
	hj numeric(18, 4), --合计
	N numeric(18, 4), --N月
	N1 numeric(18, 4), --N1月
	N2 numeric(18, 4), --N2月
	shxj numeric(18, 4), --收汇小计
	T1zf numeric(18, 4), --TT支付
	ghbl numeric(18, 4), --归还保理
	hdk numeric(18, 4), --还贷款
	xyz numeric(18, 4), --信用证
	fhxj numeric(18, 4), --付汇小计
	whqkje numeric(18, 4), --外汇缺口金额
	yshje numeric(18, 4), --已锁汇金额
	nf int, --年份
	yf numeric(18, 4), --月份
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


--外汇币种
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'foreign_exchange_currency')
DROP TABLE foreign_exchange_currency
CREATE TABLE [dbo].[foreign_exchange_currency](
	[id] [int] IDENTITY(1,1) NOT NULL,
	name varchar(100)
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

insert into foreign_exchange_currency (name) values
('AED阿联酋迪拉姆'),
('AFN阿富汗尼'),
('ALL阿尔巴尼列克'),
('AMD亚美尼亚德拉姆'),
('ANG荷兰盾'),
('AOA安哥拉宽扎'),
('ARS阿根廷比索'),
('AUD澳元'),
('AWG阿鲁巴弗罗林'),
('AZN阿塞拜疆马纳特'),
('BAM波黑可兑换马克'),
('BBD巴巴多斯元'),
('BDT孟加拉国塔卡'),
('BGN保加利亚列弗'),
('BHD巴林第纳尔'),
('BIF布隆迪法郎'),
('BMD百慕大元'),
('BND文莱元'),
('BOB玻利维亚诺'),
('BRL巴西雷亚尔'),
('BSD巴哈马元'),
('BTN不丹努扎姆'),
('BWP博茨瓦纳普拉'),
('BYR白俄罗斯卢布'),
('BZD伯利兹元'),
('CAD加元'),
('CDF刚果法郎'),
('CHF瑞士法郎'),
('CLF智利比索基金'),
('CLP智利比索'),
('CNH中国离岸人民币'),
('CNY人民币'),
('COP哥伦比亚比索'),
('CRC哥斯达黎加科朗'),
('CUP古巴比索'),
('CVE佛得角埃斯库多'),
('CYP塞普路斯镑'),
('CZK捷克克朗'),
('DEM德国马克'),
('DJF吉布提法郎'),
('DKK丹麦克朗'),
('DOP多米尼加比索'),
('DZD阿尔及利亚第纳尔'),
('ECS厄瓜多尔苏克雷'),
('EGP埃及镑'),
('ERN厄立特里亚纳克法'),
('ETB埃塞俄比亚比尔'),
('EUR欧元'),
('FJD斐济元'),
('FKP福克兰群岛镑'),
('FRF法国法郎'),
('GBP英镑'),
('GEL格鲁吉亚拉里'),
('GHS加纳塞地'),
('GIP直布罗陀镑'),
('GMD冈比亚达拉西'),
('GNF几内亚法郎'),
('GTQ危地马拉格查尔'),
('GYD圭亚那元'),
('HKD港币'),
('HNL洪都拉斯伦皮拉'),
('HRK克罗地亚库纳'),
('HTG海地古德'),
('HUF匈牙利福林'),
('IDR印度尼西亚卢比'),
('IEP爱尔兰镑'),
('ILS以色列新谢克尔'),
('INR印度卢比'),
('IQD伊拉克第纳尔'),
('IRR伊朗里亚尔'),
('ISK冰岛克郎'),
('ITL意大利里拉'),
('JMD牙买加元'),
('JOD约旦第纳尔'),
('JPY日元'),
('KES肯尼亚先令'),
('KGS吉尔吉斯斯坦索姆'),
('KHR柬埔寨瑞尔'),
('KMF科摩罗法郎'),
('KPW朝鲜元'),
('KRW韩元'),
('KWD科威特第纳尔'),
('KYD开曼群岛元'),
('KZT哈萨克斯坦坚戈'),
('LAK老挝基普'),
('LBP黎巴嫩镑'),
('LKR斯里兰卡卢比'),
('LRD利比里亚元'),
('LSL莱索托洛蒂'),
('LTL立陶宛立特'),
('LVL拉脱维亚拉特'),
('LYD利比亚第纳尔'),
('MAD摩洛哥迪拉姆'),
('MDL摩尔多瓦列伊'),
('MGA马达加斯加阿里亚里'),
('MKD马其顿代纳尔'),
('MMK缅甸元'),
('MNT蒙古图格里克'),
('MOP澳门元'),
('MRO毛里塔尼亚乌吉亚'),
('MUR毛里求斯卢比'),
('MVR马尔代夫拉菲亚'),
('MWK马拉维克瓦查'),
('MXN墨西哥比索'),
('MXV墨西哥'),
('MYR林吉特'),
('MZN莫桑比克新梅蒂卡尔'),
('NAD纳米比亚元'),
('NGN尼日利亚奈拉'),
('NIO尼加拉瓜新科多巴'),
('NOK挪威克朗'),
('NPR尼泊尔卢比'),
('NZD新西兰元'),
('OMR阿曼里亚尔'),
('PAB巴拿马巴波亚'),
('PEN秘鲁新索尔'),
('PGK巴布亚新几内亚基那'),
('PHP菲律宾比索'),
('PKR巴基斯坦卢比'),
('PLN波兰兹罗提'),
('PYG巴拉圭瓜拉尼'),
('QAR卡塔尔里亚尔'),
('RON罗马尼亚列伊'),
('RSD塞尔维亚第纳尔'),
('RUB俄罗斯卢布'),
('RWF卢旺达法郎'),
('SAR沙特里亚尔'),
('SBD所罗门群岛元'),
('SCR塞舌尔卢比'),
('SDG苏丹磅'),
('SEK瑞典克朗'),
('SGD新加坡元'),
('SHP圣赫勒拿镑'),
('SIT斯洛文尼亚托拉尔'),
('SLL塞拉利昂利昂'),
('SOS索马里先令'),
('SRD苏里南元'),
('STD圣多美多布拉'),
('SVC萨尔瓦多科朗'),
('SYP叙利亚镑'),
('SZL斯威士兰里兰吉尼'),
('THB泰铢'),
('TJS塔吉克斯坦索莫尼'),
('TMT土库曼斯坦马纳特'),
('TND突尼斯第纳尔'),
('TOP汤加潘加'),
('TRY土耳其里拉'),
('TTD特立尼达多巴哥元'),
('TWD新台币'),
('TZS坦桑尼亚先令'),
('UAH乌克兰格里夫纳'),
('UGX乌干达先令'),
('USD美元'),
('UYU乌拉圭比索'),
('UZS乌兹别克斯坦苏姆'),
('VEF委内瑞拉玻利瓦尔'),
('VND越南盾'),
('VUV瓦努阿图瓦图'),
('WST萨摩亚塔拉'),
('XAF中非法郎'),
('XAG银价盎司'),
('XAU金价盎司'),
('XCD东加勒比元'),
('XCP铜价盎司'),
('XDRIMF特别提款权'),
('XOF西非法郎'),
('XPD钯价盎司'),
('XPF太平洋法郎'),
('XPT珀价盎司'),
('YER也门里亚尔'),
('ZAR南非兰特'),
('ZMW赞比亚克瓦查'),
('ZWL津巴布韦元')

