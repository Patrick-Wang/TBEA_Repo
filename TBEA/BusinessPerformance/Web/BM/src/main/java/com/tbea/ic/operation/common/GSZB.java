package com.tbea.ic.operation.common;

public enum GSZB {
	// LRZE1(1),//利润总额
	// QZTCNY(2),//    其中_天池能源
	// QZXJNY(3),//    其中_新疆能源
	// QZYYSLR(4),//    其中_运营商利润
	// QZYEYLRWY(5),//    其中_幼儿园利润（万元）
	// XSSR6(6),//销售收入
	// ZZYSR(7),//制造业收入
	// QZDJGSR(8),//    其中_多晶硅收入
	// QZJQKSR(9),//    其中_加气块收入
	// QZGNSR(10),//    其中_国内收入
	// QZGJSR(11),//    其中_国际收入
	// GCXMSR(12),// 工程项目收入
	// QZGNSR_1(13),//    其中_国内收入
	// QZGJSR_1(14),//    其中_国际收入
	// YYSSR(15),//运营商收入
	// WLMYSR(16),//物流贸易收入
	// QZGNSR_2(17),//    其中_国内收入
	// QZGJSR_2(18),//    其中_国际收入
	// MTXSSR(19),//煤炭销售收入
	// FWLSR(20),//服务类收入
	// QZHJCPWXSR(21),//    其中_合金产品外销收入
	// QZWBCS(22),//    其中_外部创收
	// QZBYCS(23),//    其中_餐饮创收
	// FBYCS(24),//非餐饮创收
	// QZWXSR(25),//    其中_外销收入
	// QZYEYSR(26),//    其中_幼儿园收入
	// QZMYSR(27),//    其中_贸易收入
	// XSLRL28(28),//销售利润率
	// JYXJXJL29(29),//经营性净现金流
	// QZTCNY_1(30),//    其中_天池能源
	// QZXJNY_1(31),//    其中_新疆能源
	// YSZK32(32),//应收账款
	// QZYQK33(33),//    其中_逾期款
	// BL34(34),//保理
	// CH35(35),//存货
	// QZJYWY36(36),//    其中_积压物资
	// QZKCSP37(37),//    其中_库存商品
	// QZZJXMCH38(38),//    其中_自建项目存货
	// QZBPBJ(39),//    其中_备品备件
	// QZDCMT(40),//    其中_电厂煤炭
	// QZDCRY(41),//    其中_电厂燃油
	// NBQCCP(42),//逆变器产成品
	// NBQBCP(43),//逆变器半成品
	// NBQYBL(44),//逆变器原材料
	// NBQYYP(45),//逆变器在制品
	// GCYWCH(46),//工程业务存货
	// CHKHY(47),//存货考核值
	// HTQYE(48),//合同签约额
	// GJQY(49),//国际签约(万美元）
	// QZDJQY(50),//    其中_单机签约
	// QZCTQY(51),//    其中_成套签约
	// GNQY(52),//国内签约
	// QZDJQY_1(53),//    其中_单机签约
	// QZCTQY_1(54),//    其中_成套签约
	// XKXHQY(55),//现款现货签约
	// CKXSE(56),//出口销售额
	// ZJHL(57),//资金回笼
	// QZTCNY_2(58),//    其中_天池能源
	// QZXJNY_2(59),//    其中_新疆能源
	// BHSCZ(60),//不含税产值
	// RS61(61),//人数
	// RJLR62(62),//人均利润
	// RJSR63(63),//人均收入
	// SXFY64(64),//三项费用
	// SXFYL65(65),//三项费用率
	// JZCSYL66(66),//净资产收益率
	// CL67(67),//产量
	// BYQ(68),//变压器(万KVA)
	// XLYTL(69),//线缆用铜量(吨)
	// XLYLL(70),//线缆用铝量(吨)
	// DJG(71),//多晶硅(吨)
	// GP(72),//硅片(片)
	// FDLWD(73),//发电量（万度）
	// NBQmw(74),//逆变器（MW）
	// MT(75),//煤炭(万吨)
	// QZJEKCL(76),//    其中_将二矿产量
	// QZNLTKCL(77),//    其中_南露天矿产量
	// LBD(78),//铝箔（吨）
	// DCYJ(79),//电池组件(MW)
	// svgmvar(80),//SVG（Mvar）
	// DJBPM(81),//电极箔（平米）
	// DJBHCLPM(82),//电极箔化成量（平米）
	// YFGSDJGD(83),//一分公司多晶硅（吨）
	// EFGSDJGD(84),//二分公司多晶硅（吨）
	// FDLWD_1(85),//发电量（万度）
	// SWDLWD(86),//上网电量（万度）
	// JQKLF(87),//加气块（立方）
	// YGDWQWS(88),//转供电（万千瓦时）
	// QZDLC(89),//    其中_动力厂
	// QZGDJ(90),//    其中_供电局
	// SJGSF(91),//水井供水（方）
	// YQL(92),//蒸汽量
	// CPCLD(93),//成品产量（吨）
	// QZHJCPCLD(94),//    其中_合金产品产量（吨）
	// YCLD(95),//总产量（吨）
	// QZDYLBCLD(96),//    其中_电子铝箔产量（吨）
	// FDYBCLD(97),//非电子箔产量（吨）
	// XBCL(98),//型材产量(吨)
	// JKDL1kv10kvCLkm(99),//架空电缆（1KV、10KV）产量（km）
	// GXLJXCLD(100),//钢芯铝绞线产量（吨）
	// BDXCLkm(101),//布电线产量（km）
	// HCBCLWPM(102),//化成箔产量（万平米）
	// QZYCBWPM(103),//其中_自产箔（万平米）
	// JGBWPM(104),//加工箔（万平米）
	// XL(105),//销量
	// DJGD(106),//多晶硅（吨）
	// GPWP(107),//硅片（万片）
	// NBQmw_1(108),//逆变器（MW）
	// SWDLWD_1(109),//上网电量（万度）
	// svgmvar_1(110),//SVG（Mvar）
	// YYXMYRLmw(111),//自有项目转让量（MW）
	// JEKXL(112),//将二矿销量
	// DYK(113),//    大中块
	// SBK(114),//    三八块
	// QT(115),//    其他
	// MM(116),//    末煤
	// NLTKXL(117),//南露天矿销量
	// DYK_1(118),//    大中块
	// SBK_1(119),//    三八块
	// QT_1(120),//    其他
	// MM_1(121),//    末煤
	// epcXMHQSL(122),//EPC项目获取数量
	// JQKXL(123),//加气块销量
	// GCLCPJLGYCCPL(124),//高纯铝产品精铝杆一次成品率（%）
	// HJCPLGBYCYHCPL(125),//合金产品铝杆棒一次综合成品率（%）
	// LBCPYCCPLHYY(126),//铝箔产品一次成品率（含铸造）%
	// DJBCPYHFDLHYCB(127),//电极箔产品综合符单率（含自产箔）%
	// LBSBCBL(128),//铝箔失败成本率（%）
	// DJBSBCBL(129),//电极箔失败成本率（%）
	// GCLYPCLD(130),//高纯铝制品产量（吨）
	// QZ4nJYSCPCLD(131),//其中_4N及以上产品产量（吨）
	// _4n6JLKYCCPL(132),//4N6精铝块一次成品率（%）
	// JLGYCCPL(133),//精铝杆一次成品率（%）
	// _4n6JLK13XYSHYppm(134),//4N6精铝块13项元素和值（ppm）
	// SBCBL1(135),//失败成本率1（%）
	// JLYDDH(136),//精铝液电单耗（kwh/吨）
	// LGBYCYHCPL(137),//铝杆棒一次综合成品率（%）
	// QZ5154HJGYCCPL(138),//其中_5154合金杆一次成品率（%）
	// HJGYCCPL(139),//4043&8030&6201合金杆一次成品率（%）
	// GCLGCPYCCPL(140),//高纯铝杆产品一次成品率（%）
	// LBCPYCCPL(141),//铝棒产品一次成品率（%）
	// LDJGPYB99_90YSDJ13XYSFHLEJYS(142),//铝电解高品质槽99.90%以上等级13项元素符合率（二级以上）（%）
	// WBKSL(143),//外部客诉率（%）
	// QZGNKSL(144),//    其中_国内客诉率（%）
	// GWKSL(145),//国外客诉率（%）
	// YLYDDHkwh(146),//原铝液电单耗（kwh/吨）
	// YHCPL(147),//综合成品率（%）
	// WBKSL_1(148),//外部客诉率（%）
	// QZGNKSL_1(149),//其中_国内客诉率（%）
	// GWKSL_1(150),//    国外客诉率（%）
	// YCBYHFDL(151),//自产箔综合符单率（%）
	// WTJGHCBFDL(152),//委托加工化成箔符单率（%）
	// WBKSL_2(153),//外部客诉率（%）
	// QZGNKSL_2(154),//    其中_国内客诉率（%）
	// QZGWKSL(155),//    其中_国外客诉率（%）
	// JBCPL(156),//基材成品率（%）
	// FMPTCPL(157),//粉末喷涂成品率（%）
	// GRCPCPL(158),//隔热产品成品率（%）
	// SBCBL(159),//失败成本率（%）
	// JKDL1kv10kvHGL(160),//架空电缆（1KV、10KV）合格率（%）
	// GXLJXHGL(161),//钢芯铝绞线合格率（%）
	// BDXHGL(162),//布电线合格率（%）
	// NBQCBY(163),//逆变器成本（元/W）
	// YJCBY(164),//组件成本（元/W）
	// RJFDLWD(165),//人均发电量（万度/人）
	// DWGDCBY(166),//单位供电成本（元/度）
	// BMDHg(167),//标煤单耗（g/度）
	// CYDL(168),//厂用电率（%）
	// JYYHXSSh(169),//机组运行小时数（h）
	// XWDLWD(170),//下网电量（万度）
	// WGDDWCBY(171),//外购电单位成本（元/度）
	// YYXLXYLWD(172),//自有线路下站量（万吨）
	// YYXLFYLWD(173),//自有线路发运量（万吨）
	// QZXYWSRWY(174),//其中_新业务收入（万元）
	// JYYHXSWCL(175),//机组运行小时完成率（%）
	// MLR(176),//毛利润
	// YYDYRLmw(177),//运营电站容量（MW）
	// KFXMBASLmw(178),//开发项目备案数量（MW）
	// ZCZE179(179),//资产总额
	// GDZC180(180),//固定资产
	// JZCQMS181(181),//净资产（期末数）
	// JZCQCS182(182),//净资产（期初数）
	// JLR183(183),//净利润
	// FZZEQMS184(184),//负债总额期末数
	// FZL185(185),//负债率
	// XSSR_ZZYSR(186),// 销售收入_制造业收入
	// QZ_GNSR(187),// 其中_国内收入
	// QZ_GJSR(188),// 其中_国际收入
	// XSSR_GCXMSR(189),// 销售收入_工程项目收入
	// QZ_GNSR1(190),// 其中_国内收入
	// QZ_GJSR1(191),// 其中_国际收入
	// XSSR_WLMYSR(192),// 销售收入_物流贸易收入
	// QZ_GNSR2(193),// 其中_国内收入
	// QZ_GJSR2(194),// 其中_国际收入
	// QZ_ZZY_BKCYLYS(195),// 其中_制造业（包括产业链延伸）
	// GCYW(196),// 工程业务
	// WLMY(197),// 物流贸易
	// XMZJ(198),// 项目资金
	// QT1(199),// 其他
	// GNYSZK(200),// 国内应收账款
	// QZ_ZZY1(201),// 其中_制造业
	// QZ_GC_XSYW1(202),// 其中_工程、修试业务
	// QZ_WLMY1(203),// 其中_物流贸易
	// GJYSZK(204),// 国际应收账款
	// QZ_ZZY2(205),// 其中_制造业
	// QZ_GC_XSYW2(206),// 其中_工程、修试业务
	// QZ_WLMY2(207),// 其中_物流贸易
	// QZ_ZZY(208),// 其中_制造业
	// QZ_GC_XSYW(209),// 其中_工程、修试业务
	// QZ_WLMY(210),// 其中_物流贸易
	// ZZY_AYWF(211),// 制造业(按业务分)
	// GC_XSYW(212),// 工程、修试业务
	// WLMY1(213),// 物流贸易
	// XJ_AFSF(214),// 现金(按方式分)
	// PJ(215),// 票据
	// QT2(216),// 其他
	// ZZYRS(217),// 制造业人数
	// GC_XSYWRS(218),// 工程、修试业务人数
	// WLMYRS(219),// 物流贸易人数
	// ZJRG(220),// 直接人工
	// GLFY_CWKJ(221),// 管理费用(财务口径)
	// QZ_GDFY(222),// 其中_固定费用
	// QZ_BDFY(223),// 其中_变动费用
	// XSFY_CWKJ(224),// 销售费用(财务口径)
	// QZ_GDFY1(225),// 其中_固定费用
	// QZ_BDFY1(226),// 其中_变动费用
	// CWFY_CWKJ(227),// 财务费用(财务口径)
	// ZZY_YWKJ(228),// 制造业(业务口径)
	// GC_XSYW1(229),// 工程、修试业务
	// WLMY2(230),// 物流贸易
	// ZZYSXFYL231(231),// 制造业三项费用率
	// GC_XSYWSXFYL232(232),// 工程、修试业务三项费用率
	// WLMYSXFYL233(233);// 物流贸易三项费用率

	LRZE1(1), // 利润总额
	QZ_TCNY2(2), // 其中_天池能源
	QZ_XJNY3(3), // 其中_新疆能源
	QZ_YYSLR4(4), // 其中_运营商利润
	QZ_YEYLR_WY_5(5), // 其中_幼儿园利润（万元）
	XSSR6(6), // 销售收入
	XSSR_ZZYSR7(7), // 销售收入_制造业收入
	QZ_DJGSR8(8), // 其中_多晶硅收入
	QZ_JQKSR9(9), // 其中_加气块收入
	QZ_GNSR10(10), // 其中_国内收入
	QZ_GJSR11(11), // 其中_国际收入
	XSSR_GCXMSR12(12), // 销售收入_工程项目收入
	QZ_GNSR13(13), // 其中_国内收入
	QZ_GJSR14(14), // 其中_国际收入
	YYSSR15(15), // 运营商收入
	XSSR_WLMYSR16(16), // 销售收入_物流贸易收入
	QZ_GNSR17(17), // 其中_国内收入
	QZ_GJSR18(18), // 其中_国际收入
	MTXSSR19(19), // 煤炭销售收入
	FWLSR20(20), // 服务类收入
	QZ_HJCPWXSR21(21), // 其中_合金产品外销收入
	QZ_WBCS22(22), // 其中_外部创收
	QZ_CYCS23(23), // 其中_餐饮创收
	FCYCS24(24), // 非餐饮创收
	QZ_WXSR25(25), // 其中_外销收入
	QZ_YEYSR26(26), // 其中_幼儿园收入
	QZ_MYSR27(27), // 其中_贸易收入
	XSLRL_28(28), // 销售利润率(%)
	JYXJXJL29(29), // 经营性净现金流
	QZ_TCNY30(30), // 其中_天池能源
	QZ_XJNY31(31), // 其中_新疆能源
	YSZK32(32), // 应收账款
	YQK33(33), // 逾期款
	BL34(34), // 保理
	CH35(35), // 存货
	QZ_JYWZ36(36), // 其中_积压物资
	QZ_KCSP37(37), // 其中_库存商品
	QZ_ZJXMCH38(38), // 其中_自建项目存货
	QZ_BPBJ39(39), // 其中_备品备件
	QZ_DCMT40(40), // 其中_电厂煤炭
	QZ_DCRY41(41), // 其中_电厂燃油
	NBQCCP42(42), // 逆变器产成品
	NBQBCP43(43), // 逆变器半成品
	NBQYCL44(44), // 逆变器原材料
	NBQZZP45(45), // 逆变器在制品
	GCYWCH46(46), // 工程业务存货
	CHKHZ47(47), // 存货考核值
	HTQYE48(48), // 合同签约额
	GJQY_WMY_49(49), // 国际签约(万美元）
	QZ_DJQY50(50), // 其中_单机签约
	QZ_CTQY_WMY_51(51), // 其中_成套签约(万美元)
	GNQY52(52), // 国内签约
	QZ_DJQY53(53), // 其中_单机签约
	QZ_CTQY_WY_54(54), // 其中_成套签约(万元)
	XKXHQY55(55), // 现款现货签约
	CKXSE56(56), // 出口销售额
	ZJHL57(57), // 资金回笼
	QZ_TCNY58(58), // 其中_天池能源
	QZ_XJNY59(59), // 其中_新疆能源
	BHSCZ60(60), // 不含税产值
	RS61(61), // 人数
	RJLR62(62), // 人均利润
	RJSR63(63), // 人均收入
	SXFY64(64), // 三项费用
	SXFYL_65(65), // 三项费用率(%)
	JZCSYL_66(66), // 净资产收益率(%)
	CL67(67), // 产量
	BYQ_WKVA_68(68), // 变压器(万KVA)
	XLYTL_D_69(69), // 线缆用铜量(吨)
	XLYLL_D_70(70), // 线缆用铝量(吨)
	CL_DJG_D_71(71), // 产量_多晶硅(吨)
	CL_GP_WP_72(72), // 产量_硅片（万片）
	FDL_WD_73(73), // 发电量（万度）
	CL_NBQ_MW_74(74), // 产量_逆变器（MW）
	MTCL_WD_75(75), // 煤炭产量（万吨）
	QZ_JEKCL76(76), // 其中_将二矿产量
	QZ_NLTKCL77(77), // 其中_南露天矿产量
	LB_D_78(78), // 铝箔（吨）
	DCZJ_MW_79(79), // 电池组件(MW)
	SVG_Mvar_80(80), // SVG（Mvar）
	DJB_PM_81(81), // 电极箔（平米）
	DJBHCL_PM_82(82), // 电极箔化成量（平米）
	YFGSDJG_D_83(83), // 一分公司多晶硅（吨）
	EFGSDJG_D_84(84), // 二分公司多晶硅（吨）
	FDL_WD_85(85), // 发电量（万度）
	SWDL_WD_86(86), // 上网电量（万度）
	JQK_LF_87(87), // 加气块（立方）
	ZGD_WQWS_88(88), // 转供电（万千瓦时）
	QZ_DLC89(89), // 其中_动力厂
	QZ_GDJ90(90), // 其中_供电局
	SJGS_F_91(91), // 水井供水（方）
	ZQL92(92), // 蒸汽量
	CPCL_D_93(93), // 成品产量（吨）
	QZ_HJCPCL_D_94(94), // 其中_合金产品产量（吨）
	ZCL_D_95(95), // 总产量（吨）
	QZ_DZLBCL_D_96(96), // 其中_电子铝箔产量（吨）
	FDZBCL_D_97(97), // 非电子箔产量（吨）
	XCCL_D_98(98), // 型材产量(吨)
	JKDL_1KV_10KV_CL_km_99(99), // 架空电缆（1KV、10KV）产量（km）
	GXLJXCL_D_100(100), // 钢芯铝绞线产量（吨）
	BDXCL_km_101(101), // 布电线产量（km）
	HCBCL_WPM_102(102), // 化成箔产量（万平米）
	QZ_ZCB_WPM_103(103), // 其中_自产箔（万平米）
	JGB_WPM_104(104), // 加工箔（万平米）
	XL105(105), // 销量
	XL_DJG_D_106(106), // 销量_多晶硅（吨）
	XL_GP_WP_107(107), // 销量_硅片（万片）
	XL_NBQ_MW_108(108), // 销量_逆变器（MW）
	SWDL_WD_109(109), // 上网电量（万度）
	SVG_Mvar_110(110), // SVG（Mvar）
	ZYXMZRL_MW_111(111), // 自有项目转让量（MW）
	JEKXL112(112), // 将二矿销量
	DZK113(113), // 大中块
	SBK114(114), // 三八块
	QT115(115), // 其他
	EKMM116(116), // 二矿末煤
	NLTKXL117(117), // 南露天矿销量
	DZK118(118), // 大中块
	SBK119(119), // 三八块
	QT120(120), // 其他
	NKMM121(121), // 南矿末煤
	EPCXMHQSL122(122), // EPC项目获取数量
	JQKXL123(123), // 加气块销量
	GCLCPJLGYCCPL_124(124), // 高纯铝产品精铝杆一次成品率（%）
	HJCPLGBYCZHCPL_125(125), // 合金产品铝杆棒一次综合成品率（%）
	LBCPYCCPL_HZZ_126(126), // 铝箔产品一次成品率（含铸造）%
	DJBCPZHFDL_HZCB_127(127), // 电极箔产品综合符单率（含自产箔）%
	LBSBCBL_128(128), // 铝箔失败成本率（%）
	DJBSBCBL_129(129), // 电极箔失败成本率（%）
	GCLZPCL_D_130(130), // 高纯铝制品产量（吨）
	QZ_4NJYSCPCL_D_131(131), // 其中_4N及以上产品产量（吨）
	_4N6JLKYCCPL_132(132), // 4N6精铝块一次成品率（%）
	JLGYCCPL_133(133), // 精铝杆一次成品率（%）
	_4N6JLK13XYSHZ_ppm_134(134), // 4N6精铝块13项元素和值（ppm）
	SBCBL1_135(135), // 失败成本率1（%）
	JLYDDH_kwh_D_136(136), // 精铝液电单耗（kwh/吨）
	LGBYCZHCPL_137(137), // 铝杆棒一次综合成品率（%）
	QZ_5154HJGYCCPL_138(138), // 其中_5154合金杆一次成品率（%）
	_4043_8030_6201HJGYCCPL_139(139), // 4043&8030&6201合金杆一次成品率（%）
	GCLGCPYCCPL_140(140), // 高纯铝杆产品一次成品率（%）
	LBCPYCCPL_141(141), // 铝棒产品一次成品率（%）
	LDJGPZC99_90_YSDJ13XYSFHL_EJYS_142(142), // 铝电解高品质槽99.90%以上等级13项元素符合率（二级以上）（%）
	WBKSL_143(143), // 外部客诉率（%）
	YLYDDH_kwh_D_146(146), // 原铝液电单耗（kwh/吨）
	ZHCPL_147(147), // 综合成品率（%）
	WBKSL_148(148), // 外部客诉率（%）
	ZCBZHFDL_151(151), // 自产箔综合符单率（%）
	WTJGHCBFDL_152(152), // 委托加工化成箔符单率（%）
	WBKSL_153(153), // 外部客诉率（%）
	JCCPL_156(156), // 基材成品率（%）
	FMPTCPL_157(157), // 粉末喷涂成品率（%）
	GRCPCPL_158(158), // 隔热产品成品率（%）
	SBCBL_159(159), // 失败成本率（%）
	JKDL_1KV_10KV_HGL_160(160), // 架空电缆（1KV、10KV）合格率（%）
	GXLJXHGL_161(161), // 钢芯铝绞线合格率（%）
	BDXHGL_162(162), // 布电线合格率（%）
	NBQCB_Y_W_163(163), // 逆变器成本（元/W）
	ZJCB_Y_W_164(164), // 组件成本（元/W）
	RJFDL_WD_R_165(165), // 人均发电量（万度/人）
	DWGDCB_Y_D_166(166), // 单位供电成本（元/度）
	BMDH_g_D_167(167), // 标煤单耗（g/度）
	CYDL_168(168), // 厂用电率（%）
	JZLYXSS_h_169(169), // 机组利用小时数（h）
	XWDL_WD_170(170), // 下网电量（万度）
	WGDDWCB_Y_D_171(171), // 外购电单位成本（元/度）
	ZYXLXZL_WD_172(172), // 自有线路下站量（万吨）
	ZYXLFYL_WD_173(173), // 自有线路发运量（万吨）
	QZ_XYWSR_WY_174(174), // 其中_新业务收入（万元）
	MLR176(176), // 毛利润
	YYDZRL_MW_177(177), // 运营电站容量（MW）
	KFXMBASL_MW_178(178), // 开发项目备案数量（MW）
	ZCZE179(179), // 资产总额
	GDZC180(180), // 固定资产
	JZC_QMS_181(181), // 净资产（期末数）
	JZC_QCS_182(182), // 净资产（期初数）
	JLR183(183), // 净利润
	FZZEQMS184(184), // 负债总额期末数
	FZL185(185), // 负债率
	QZ_ZZY_BKCYLYS_195(195), // 其中_制造业（包括产业链延伸）
	GCYW196(196), // 工程业务
	WLMY197(197), // 物流贸易
	XMZJ198(198), // 项目资金
	QT199(199), // 其他
	GNYSZK200(200), // 国内应收账款
	QZ_ZZY201(201), // 其中_制造业
	QZ_GC_XSYW202(202), // 其中_工程、修试业务
	QZ_WLMY203(203), // 其中_物流贸易
	GJYSZK204(204), // 国际应收账款
	QZ_ZZY205(205), // 其中_制造业
	QZ_GC_XSYW206(206), // 其中_工程、修试业务
	QZ_WLMY207(207), // 其中_物流贸易
	QZ_ZZY208(208), // 其中_制造业
	QZ_GC_XSYW209(209), // 其中_工程、修试业务
	QZ_WLMY210(210), // 其中_物流贸易
	ZZY_AYWF_211(211), // 制造业(按业务分)
	GC_XSYW212(212), // 工程、修试业务
	WLMY213(213), // 物流贸易
	XJ_AFSF_214(214), // 现金(按方式分)
	PJ215(215), // 票据
	QT216(216), // 其他
	ZZYRS217(217), // 制造业人数
	GC_XSYWRS218(218), // 工程、修试业务人数
	WLMYRS219(219), // 物流贸易人数
	ZJRG220(220), // 直接人工
	GLFY_CWKJ_221(221), // 管理费用(财务口径)
	QZ_GDFY222(222), // 其中_固定费用
	QZ_BDFY223(223), // 其中_变动费用
	XSFY_CWKJ_224(224), // 销售费用(财务口径)
	QZ_GDFY225(225), // 其中_固定费用
	QZ_BDFY226(226), // 其中_变动费用
	CWFY_CWKJ_227(227), // 财务费用(财务口径)
	ZZY_YWKJ_228(228), // 制造业(业务口径)
	GC_XSYW229(229), // 工程、修试业务
	WLMY230(230), // 物流贸易
	ZZYSXFYL231(231), // 制造业三项费用率
	GC_XSYWSXFYL232(232), // 工程、修试业务三项费用率
	WLMYSXFYL233(233), // 物流贸易三项费用率
	QZ_FYYSLR234(234), // 其中_非运营商利润
	XSSR_ZYDZSR235(235), // 销售收入_自营电站收入
	CL_SVG_Mvar_236(236), // 产量_SVG_Mvar）
	CL_ZYXMFDL_WD_237(237), // 产量_自营项目发电量_万度_
	XL_SVG_Mvar_238(238), // 销量_SVG_Mvar_
	XL_ZYXMSWDL_WD_239(239), // 销量_自营项目上网电量_万度_
	QZ_DJGLR240(240), // 其中_多晶硅利润
	QZ_JQKLR241(241), // 其中_加气块利润
	QZ_JCXSLR242(242), // 其中_检测修试利润
	YYSLR243(243), // 运营商利润
	MTYWLR244(244), // 煤炭业务利润
	DJGGNSR245(245), // 多晶硅国内收入
	DJGGJSR246(246), // 多晶硅国际收入
	JCFWYWJCXSGNSR247(247), // 集成服务业务检测修试国内收入
	JCFWYWJCXSGJSR248(248), // 集成服务业务检测修试国际收入
	QZ_YYSGNSR249(249), // 其中_运营商国内收入
	QZ_YYSGJSR250(250), // 其中_运营商国际收入
	QZ_MTYWGNSR251(251), // 其中_煤炭业务国内收入
	QZ_MTYWGJSR252(252), // 其中_煤炭业务国际收入
	XSSR_QTSR253(253), // 销售收入_其他收入
	QZ_QTGNSR254(254), // 其中_其他国内收入
	QZ_QTGJSR255(255), // 其中_其他国际收入
	YSZK_ZZYYS256(256), // 应收账款_制造业应收
	QZ_ZZYGNYS257(257), // 其中_制造业国内应收
	DJGGNYS258(258), // 多晶硅国内应收
	JQKGNYS259(259), // 加气块国内应收
	QZ_ZZYGJYS260(260), // 其中_制造业国际应收
	DJGGJYS261(261), // 多晶硅国际应收
	YSZK_JCFWYW_HGCHJCXS_YS262(262), // 应收账款_集成服务业务_含工程和检测修试_应收
	QZ_JCFWGNYS263(263), // 其中_集成服务国内应收
	JCFWGNJCXS264(264), // 集成服务国内检测修试
	QZ_JCFWGJSR265(265), // 其中_集成服务国际收入
	JCFWGJJCXS266(266), // 集成服务国际检测修试
	YSZK_WLMYYS267(267), // 应收账款_物流贸易应收
	QZ_WLMYGNYS268(268), // 其中_物流贸易国内应收
	QZ_WLMYGJYS269(269), // 其中_物流贸易国际应收
	YSZK_YYSYS270(270), // 应收账款_运营商应收
	QZ_YYSGNYS271(271), // 其中_运营商国内应收
	QZ_YYSGJYS272(272), // 其中_运营商国际应收
	YSZK_MTYWYS273(273), // 应收账款_煤炭业务应收
	QZ_MTYWGNYS274(274), // 其中_煤炭业务国内应收
	QZ_MTYWGJYS275(275), // 其中_煤炭业务国际应收
	YSZK_QT276(276), // 应收账款_其他
	QZ_QTGNYS277(277), // 其中_其他国内应收
	QZ_QTGJYS278(278), // 其中_其他国际应收
	ZZYCH279(279), // 制造业存货
	DJGCH280(280), // 多晶硅存货
	JQKCH281(281), // 加气块存货
	JCFWYW_HGCHJCXS_CH282(282), // 集成服务业务_含工程和检测修试_存货
	QZ_ZYXMCH283(283), // 其中_自有项目存货
	WLMYCH284(284), // 物流贸易存货
	YYSCH285(285), // 运营商存货
	MTYWCH286(286), // 煤炭业务存货
	QZ_TCNYCH287(287), // 其中_天池能源存货
	QZ_XJNYCH288(288), // 其中_新疆能源存货
	QTCH289(289), // 其他存货
	HTQY_ZZYQY290(290), // 合同签约_制造业签约
	QZ_GNQY291(291), // 其中_国内签约
	DJGGNQY292(292), // 多晶硅国内签约
	JQKGNQY293(293), // 加气块国内签约
	SDSCQY294(294), // 属地市场签约
	QZ_GJQY295(295), // 其中_国际签约
	DJGGJQY296(296), // 多晶硅国际签约
	ZYCK297(297), // 自营出口
	DLCK298(298), // 代理出口
	JCFWYW_HGCHJCXS_QY299(299), // 集成服务业务_含工程和检测修试_签约
	QZ_JCFWYWGNQY300(300), // 其中_集成服务业务国内签约
	JCFWYWGNJCXSQY301(301), // 集成服务业务国内检测修试签约
	QZ_JCFWYWGJQY302(302), // 其中_集成服务业务国际签约
	JCFWYWGJJCXSQY303(303), // 集成服务业务国际检测修试签约
	HTQY_QT304(304), // 合同签约_其他
	QZ_GNQY305(305), // 其中_国内签约
	QZ_GJQY306(306), // 其中_国际签约
	CYLYSQY307(307), // 产业链延伸签约
	AYWLXF_ZZYZJHL308(308), // 按业务类型分_制造业资金回笼
	DJGZJHL309(309), // 多晶硅资金回笼
	JQKZJHL310(310), // 加气块资金回笼
	JCFWYW_HGCHJCXS_ZJHL311(311), // 集成服务业务_含工程和检测修试_资金回笼
	JCXSZJHL312(312), // 检测修试资金回笼
	WLMYZJHL313(313), // 物流贸易资金回笼
	YYSZJHL314(314), // 运营商资金回笼
	MTYWZJHL315(315), // 煤炭业务资金回笼
	QTYWZJHL316(316), // 其他业务资金回笼
	BYQCZ317(317), // 变压器产值
	XLCZ318(318), // 线缆产值
	SBDCYLYSCPCZ319(319), // 输变电产业链延伸产品产值
	DJGCZ320(320), // 多晶硅产值
	NBQCZ321(321), // 逆变器产值
	SVGCZ322(322), // SVG产值
	GPCZ323(323), // 硅片产值
	JQKCZ324(324), // 加气块产值
	DZLBCZ325(325), // 电子铝箔产值
	DJBHCLCZ326(326), // 电极箔化成量产值
	GCLCPCZ327(327), // 高纯铝产品产值
	HJCPCZ328(328), // 合金产品产值
	ZQL_WLF_329(329), // 蒸汽量_万立方_
	GCLCPXL_D_330(330), // 高纯铝产品销量_吨_
	HJCPXL_D_331(331), // 合金产品销量_吨_
	QTXL332(332), // 其他销量
	YYSRS333(333), // 运营商人数
	MTYWRS334(334), // 煤炭业务人数
	QTRS335(335), // 其他人数

	YSZK_DAILY_REPORT(-1);// 应收账款日报

	private Integer value;

	GSZB(Integer value) {
		this.value = value;
	}

	public Integer value() {
		return value;
	}

	public static GSZB valueOf(int Id) {
		GSZB[] types = GSZB.values();
		for (int i = types.length - 1; i >= 0; --i) {
			if (types[i].value() == Id) {
				return types[i];
			}
		}
		return null;
	}
}
