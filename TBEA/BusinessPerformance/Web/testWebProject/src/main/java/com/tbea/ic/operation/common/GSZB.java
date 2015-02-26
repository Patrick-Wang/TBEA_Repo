package com.tbea.ic.operation.common;

public enum GSZB {
	LRZE(1),//利润总额
	QZTCNY(2),//    其中：天池能源
	QZXJNY(3),//    其中：新疆能源
	QZYYSLR(4),//    其中：运营商利润
	QZYEYLRWY(5),//    其中：幼儿园利润（万元）
	XSSR(6),//销售收入
	ZZYSR(7),//制造业收入
	QZDJGSR(8),//    其中：多晶硅收入
	QZJQKSR(9),//    其中：加气块收入
	QZGNSR(10),//    其中：国内收入
	QZGJSR(11),//    其中：国际收入
	GCXMSR(12),//工程项目收入
	QZGNSR_1(13),//    其中：国内收入
	QZGJSR_1(14),//    其中：国际收入
	YYSSR(15),//运营商收入
	WLMYSR(16),//物流贸易收入
	QZGNSR_2(17),//    其中：国内收入
	QZGJSR_2(18),//    其中：国际收入
	MTXSSR(19),//煤炭销售收入
	FWLSR(20),//服务类收入
	QZHJCPWXSR(21),//    其中：合金产品外销收入
	QZWBCS(22),//    其中：外部创收
	QZBYCS(23),//    其中：餐饮创收
	FBYCS(24),//非餐饮创收
	QZWXSR(25),//    其中：外销收入
	QZYEYSR(26),//    其中：幼儿园收入
	QZMYSR(27),//    其中：贸易收入
	XSLRL(28),//销售利润率
	JYXJXJL(29),//经营性净现金流
	QZTCNY_1(30),//    其中：天池能源
	QZXJNY_1(31),//    其中：新疆能源
	YSZK(32),//应收账款
	QZYQK(33),//    其中：逾期款
	BL(34),//保理
	CH(35),//存货
	QZJYWY(36),//    其中：积压物资
	QZKCSP(37),//    其中：库存商品
	QZYJXMCH(38),//    其中：自建项目存货
	QZBPBJ(39),//    其中：备品备件
	QZDCMT(40),//    其中：电厂煤炭
	QZDCRY(41),//    其中：电厂燃油
	NBQCCP(42),//逆变器产成品
	NBQBCP(43),//逆变器半成品
	NBQYBL(44),//逆变器原材料
	NBQYYP(45),//逆变器在制品
	GCYWCH(46),//工程业务存货
	CHKHY(47),//存货考核值
	HTQYE(48),//合同签约额
	GJQY(49),//国际签约(万美元）
	QZDJQY(50),//    其中：单机签约
	QZCTQY(51),//    其中：成套签约
	GNQY(52),//国内签约
	QZDJQY_1(53),//    其中：单机签约
	QZCTQY_1(54),//    其中：成套签约
	XKXHQY(55),//现款现货签约
	CKXSE(56),//出口销售额
	ZJHL(57),//资金回笼
	QZTCNY_2(58),//    其中：天池能源
	QZXJNY_2(59),//    其中：新疆能源
	BHSCZ(60),//不含税产值
	RS(61),//人数
	RJLR(62),//人均利润
	RJSR(63),//人均收入
	SXFY(64),//三项费用
	SXFYL(65),//三项费用率
	JZCSYL(66),//净资产收益率
	CL(67),//产量
	BYQ(68),//变压器(万KVA)
	XLYTL(69),//线缆用铜量(吨)
	XLYLL(70),//线缆用铝量(吨)
	DJG(71),//多晶硅(吨)
	GP(72),//硅片(片)
	FDLWD(73),//发电量（万度）
	NBQmw(74),//逆变器（MW）
	MT(75),//煤炭(万吨)
	QZJEKCL(76),//    其中：将二矿产量
	QZNLTKCL(77),//    其中：南露天矿产量
	LBD(78),//铝箔（吨）
	DCYJ(79),//电池组件(MW)
	svgmvar(80),//SVG（Mvar）
	DJBPM(81),//电极箔（平米）
	DJBHCLPM(82),//电极箔化成量（平米）
	YFGSDJGD(83),//一分公司多晶硅（吨）
	EFGSDJGD(84),//二分公司多晶硅（吨）
	FDLWD_1(85),//发电量（万度）
	SWDLWD(86),//上网电量（万度）
	JQKLF(87),//加气块（立方）
	YGDWQWS(88),//转供电（万千瓦时）
	QZDLC(89),//    其中：动力厂
	QZGDJ(90),//    其中：供电局
	SJGSF(91),//水井供水（方）
	YQL(92),//蒸汽量
	CPCLD(93),//成品产量（吨）
	QZHJCPCLD(94),//    其中：合金产品产量（吨）
	YCLD(95),//总产量（吨）
	QZDYLBCLD(96),//    其中：电子铝箔产量（吨）
	FDYBCLD(97),//非电子箔产量（吨）
	XBCL(98),//型材产量(吨)
	JKDL1kv10kvCLkm(99),//架空电缆（1KV、10KV）产量（km）
	GXLJXCLD(100),//钢芯铝绞线产量（吨）
	BDXCLkm(101),//布电线产量（km）
	HCBCLWPM(102),//化成箔产量（万平米）
	QZYCBWPM(103),//其中：自产箔（万平米）
	JGBWPM(104),//加工箔（万平米）
	XL(105),//销量
	DJGD(106),//多晶硅（吨）
	GPWP(107),//硅片（万片）
	NBQmw_1(108),//逆变器（MW）
	SWDLWD_1(109),//上网电量（万度）
	svgmvar_1(110),//SVG（Mvar）
	YYXMYRLmw(111),//自有项目转让量（MW）
	JEKXL(112),//将二矿销量
	DYK(113),//    大中块
	SBK(114),//    三八块
	QT(115),//    其他
	MM(116),//    末煤
	NLTKXL(117),//南露天矿销量
	DYK_1(118),//    大中块
	SBK_1(119),//    三八块
	QT_1(120),//    其他
	MM_1(121),//    末煤
	epcXMHQSL(122),//EPC项目获取数量
	JQKXL(123),//加气块销量
	GCLCPJLGYCCPL(124),//高纯铝产品精铝杆一次成品率（%）
	HJCPLGBYCYHCPL(125),//合金产品铝杆棒一次综合成品率（%）
	LBCPYCCPLHYY(126),//铝箔产品一次成品率（含铸造）%
	DJBCPYHFDLHYCB(127),//电极箔产品综合符单率（含自产箔）%
	LBSBCBL(128),//铝箔失败成本率（%）
	DJBSBCBL(129),//电极箔失败成本率（%）
	GCLYPCLD(130),//高纯铝制品产量（吨）
	QZ4nJYSCPCLD(131),//其中：4N及以上产品产量（吨）
	_4n6JLKYCCPL(132),//4N6精铝块一次成品率（%）
	JLGYCCPL(133),//精铝杆一次成品率（%）
	_4n6JLK13XYSHYppm(134),//4N6精铝块13项元素和值（ppm）
	SBCBL1(135),//失败成本率1（%）
	JLYDDH(136),//精铝液电单耗（kwh/吨）
	LGBYCYHCPL(137),//铝杆棒一次综合成品率（%）
	QZ5154HJGYCCPL(138),//其中：5154合金杆一次成品率（%）
	HJGYCCPL(139),//4043&8030&6201合金杆一次成品率（%）
	GCLGCPYCCPL(140),//高纯铝杆产品一次成品率（%）
	LBCPYCCPL(141),//铝棒产品一次成品率（%）
	LDJGPYB99_90YSDJ13XYSFHLEJYS(142),//铝电解高品质槽99.90%以上等级13项元素符合率（二级以上）（%）
	WBKSL(143),//外部客诉率（%）
	QZGNKSL(144),//    其中：国内客诉率（%）
	GWKSL(145),//国外客诉率（%）
	YLYDDHkwh(146),//原铝液电单耗（kwh/吨）
	YHCPL(147),//综合成品率（%）
	WBKSL_1(148),//外部客诉率（%）
	QZGNKSL_1(149),//其中：国内客诉率（%）
	GWKSL_1(150),//    国外客诉率（%）
	YCBYHFDL(151),//自产箔综合符单率（%）
	WTJGHCBFDL(152),//委托加工化成箔符单率（%）
	WBKSL_2(153),//外部客诉率（%）
	QZGNKSL_2(154),//    其中：国内客诉率（%）
	QZGWKSL(155),//    其中：国外客诉率（%）
	JBCPL(156),//基材成品率（%）
	FMPTCPL(157),//粉末喷涂成品率（%）
	GRCPCPL(158),//隔热产品成品率（%）
	SBCBL(159),//失败成本率（%）
	JKDL1kv10kvHGL(160),//架空电缆（1KV、10KV）合格率（%）
	GXLJXHGL(161),//钢芯铝绞线合格率（%）
	BDXHGL(162),//布电线合格率（%）
	NBQCBY(163),//逆变器成本（元/W）
	YJCBY(164),//组件成本（元/W）
	RJFDLWD(165),//人均发电量（万度/人）
	DWGDCBY(166),//单位供电成本（元/度）
	BMDHg(167),//标煤单耗（g/度）
	CYDL(168),//厂用电率（%）
	JYYHXSSh(169),//机组运行小时数（h）
	XWDLWD(170),//下网电量（万度）
	WGDDWCBY(171),//外购电单位成本（元/度）
	YYXLXYLWD(172),//自有线路下站量（万吨）
	YYXLFYLWD(173),//自有线路发运量（万吨）
	QZXYWSRWY(174),//其中：新业务收入（万元）
	JYYHXSWCL(175),//机组运行小时完成率（%）
	MLR(176),//毛利润
	YYDYRLmw(177),//运营电站容量（MW）
	KFXMBASLmw(178);//开发项目备案数量（MW）

	
	private Integer value;

	GSZB(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public static GSZB valueOf(int Id){
		GSZB[] types = GSZB.values();
		for (int i = types.length - 1; i >= 0; --i){
			if (types[i].getValue() == Id){
				return types[i];
			}
		}
		return null;
	}
}
