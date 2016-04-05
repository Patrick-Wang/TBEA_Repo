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
