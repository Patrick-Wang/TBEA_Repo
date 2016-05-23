package com.tbea.ic.operation.service.util.pipe.configurator;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;

public class ConfiguratorUtil {

	static List<Integer> invisiableZbs = new ArrayList<Integer>();
	static List<Integer> timePointNumberZbs = new ArrayList<Integer>();
	static List<Integer> ratioZbs = new ArrayList<Integer>();
	static List<Integer> zhHiddenZbs = new ArrayList<Integer>();

	static {
		timePointNumberZbs.add(GSZB.YSZK32.value());
		timePointNumberZbs.add(GSZB.CH35.value());
		timePointNumberZbs.add(GSZB.RS61.value());
		timePointNumberZbs.add(GSZB.QZ_ZJXMCH38.value());
		timePointNumberZbs.add(GSZB.YQK33.value());
		timePointNumberZbs.add(GSZB.QZ_KCSP37.value());
		timePointNumberZbs.add(GSZB.BL34.value());
		timePointNumberZbs.add(GSZB.QZ_JYWZ36.value());
		timePointNumberZbs.add(GSZB.GNYSZK200.value());
		timePointNumberZbs.add(GSZB.QZ_ZZY201.value());
		timePointNumberZbs.add(GSZB.QZ_GC_XSYW202.value());
		timePointNumberZbs.add(GSZB.QZ_WLMY203.value());
		timePointNumberZbs.add(GSZB.GJYSZK204.value());
		timePointNumberZbs.add(GSZB.QZ_ZZY205.value());
		timePointNumberZbs.add(GSZB.QZ_GC_XSYW206.value());
		timePointNumberZbs.add(GSZB.QZ_WLMY207.value());
		timePointNumberZbs.add(GSZB.QZ_ZZY208.value());
		timePointNumberZbs.add(GSZB.QZ_GC_XSYW209.value());
		timePointNumberZbs.add(GSZB.QZ_WLMY210.value());
		timePointNumberZbs.add(GSZB.YSZK_ZZYYS256.value());
		timePointNumberZbs.add(GSZB.QZ_ZZYGNYS257.value());
		timePointNumberZbs.add(GSZB.DJGGNYS258.value());
		timePointNumberZbs.add(GSZB.JQKGNYS259.value());
		timePointNumberZbs.add(GSZB.QZ_ZZYGJYS260.value());
		timePointNumberZbs.add(GSZB.DJGGJYS261.value());
		timePointNumberZbs.add(GSZB.YSZK_JCFWYW_HGCHJCXS_YS262.value());
		timePointNumberZbs.add(GSZB.QZ_JCFWGNYS263.value());
		timePointNumberZbs.add(GSZB.JCFWGNJCXS264.value());
		timePointNumberZbs.add(GSZB.QZ_JCFWGJSR265.value());
		timePointNumberZbs.add(GSZB.JCFWGJJCXS266.value());
		timePointNumberZbs.add(GSZB.YSZK_WLMYYS267.value());
		timePointNumberZbs.add(GSZB.QZ_WLMYGNYS268.value());
		timePointNumberZbs.add(GSZB.QZ_WLMYGJYS269.value());
		timePointNumberZbs.add(GSZB.YSZK_YYSYS270.value());
		timePointNumberZbs.add(GSZB.QZ_YYSGNYS271.value());
		timePointNumberZbs.add(GSZB.QZ_YYSGJYS272.value());
		timePointNumberZbs.add(GSZB.YSZK_MTYWYS273.value());
		timePointNumberZbs.add(GSZB.QZ_MTYWGNYS274.value());
		timePointNumberZbs.add(GSZB.QZ_MTYWGJYS275.value());
		timePointNumberZbs.add(GSZB.YSZK_QT276.value());
		timePointNumberZbs.add(GSZB.QZ_QTGNYS277.value());
		timePointNumberZbs.add(GSZB.QZ_QTGJYS278.value());
		timePointNumberZbs.add(GSZB.ZZYCH279.value());
		timePointNumberZbs.add(GSZB.DJGCH280.value());
		timePointNumberZbs.add(GSZB.JQKCH281.value());
		timePointNumberZbs.add(GSZB.JCFWYW_HGCHJCXS_CH282.value());
		timePointNumberZbs.add(GSZB.QZ_ZYXMCH283.value());
		timePointNumberZbs.add(GSZB.WLMYCH284.value());
		timePointNumberZbs.add(GSZB.YYSCH285.value());
		timePointNumberZbs.add(GSZB.MTYWCH286.value());
		timePointNumberZbs.add(GSZB.QZ_TCNYCH287.value());
		timePointNumberZbs.add(GSZB.QZ_XJNYCH288.value());
		timePointNumberZbs.add(GSZB.QTCH289.value());
		timePointNumberZbs.add(GSZB.ZZYRS217.value());
		timePointNumberZbs.add(GSZB.GC_XSYWRS218.value());
		timePointNumberZbs.add(GSZB.WLMYRS219.value());
		timePointNumberZbs.add(GSZB.YYSRS333.value());
		timePointNumberZbs.add(GSZB.MTYWRS334.value());
		timePointNumberZbs.add(GSZB.QTRS335.value());
		
	}

	static {
		ratioZbs.add(GSZB.RJLR62.value());
		ratioZbs.add(GSZB.RJSR63.value());
		ratioZbs.add(GSZB.SXFYL_65.value());
		ratioZbs.add(GSZB.XSLRL_28.value());
	}

	static {
		invisiableZbs.add(GSZB.CL67.value());		
	}
	
	static {
		zhHiddenZbs.add(166);
		zhHiddenZbs.add(167);
		zhHiddenZbs.add(168);
		zhHiddenZbs.add(171);
		zhHiddenZbs.add(137);
		zhHiddenZbs.add(138);
		zhHiddenZbs.add(139);
		zhHiddenZbs.add(140);
		zhHiddenZbs.add(141);
		zhHiddenZbs.add(142);
		zhHiddenZbs.add(135);
		zhHiddenZbs.add(143);
		zhHiddenZbs.add(146);
		zhHiddenZbs.add(132);
		zhHiddenZbs.add(133);
		zhHiddenZbs.add(134);
		zhHiddenZbs.add(136);
		zhHiddenZbs.add(147);
		zhHiddenZbs.add(156);
		zhHiddenZbs.add(157);
		zhHiddenZbs.add(158);
		zhHiddenZbs.add(159);
		zhHiddenZbs.add(151);
		zhHiddenZbs.add(152);
		zhHiddenZbs.add(160);
		zhHiddenZbs.add(161);
		zhHiddenZbs.add(162);
		zhHiddenZbs.add(124);
		zhHiddenZbs.add(125);
		zhHiddenZbs.add(126);
		zhHiddenZbs.add(127);
		zhHiddenZbs.add(128);
		zhHiddenZbs.add(129);
	}
	
	/**
	 * @return the zhHiddenZbs
	 */
	public static List<Integer> getZhHiddenZbs() {
		return zhHiddenZbs;
	}

	public static List<Integer> getInvisiableZbs(){
		return invisiableZbs;
	}
	
	public static List<Integer> getTimePointNumberZbs(){
		return timePointNumberZbs;
	}
	
	public static List<Integer> getRatioZbs(){
		return ratioZbs;
	}
	

	
	public static void seperate(SbdNdjhZbDao sbdzbDao, List<Company> companies, List<Company> sbdComps, List<Company> nonSbdComps){
		for (Company comp : companies) {
			if (sbdzbDao.contains(comp)) {
				sbdComps.add(comp);
			} else{
				nonSbdComps.add(comp);
			}
		}
	}
}
