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
		timePointNumberZbs.add(GSZB.YSZK32.getValue());
		timePointNumberZbs.add(GSZB.CH35.getValue());
		timePointNumberZbs.add(GSZB.RS61.getValue());
		timePointNumberZbs.add(GSZB.QZ_ZJXMCH38.getValue());
		timePointNumberZbs.add(GSZB.YQK33.getValue());
		timePointNumberZbs.add(GSZB.QZ_KCSP37.getValue());
		timePointNumberZbs.add(GSZB.BL34.getValue());
		timePointNumberZbs.add(GSZB.QZ_JYWZ36.getValue());
		timePointNumberZbs.add(GSZB.GNYSZK200.getValue());
		timePointNumberZbs.add(GSZB.QZ_ZZY201.getValue());
		timePointNumberZbs.add(GSZB.QZ_GC_XSYW202.getValue());
		timePointNumberZbs.add(GSZB.QZ_WLMY203.getValue());
		timePointNumberZbs.add(GSZB.GJYSZK204.getValue());
		timePointNumberZbs.add(GSZB.QZ_ZZY205.getValue());
		timePointNumberZbs.add(GSZB.QZ_GC_XSYW206.getValue());
		timePointNumberZbs.add(GSZB.QZ_WLMY207.getValue());
		timePointNumberZbs.add(GSZB.QZ_ZZY208.getValue());
		timePointNumberZbs.add(GSZB.QZ_GC_XSYW209.getValue());
		timePointNumberZbs.add(GSZB.QZ_WLMY210.getValue());
	}

	static {
		ratioZbs.add(GSZB.RJLR62.getValue());
		ratioZbs.add(GSZB.RJSR63.getValue());
		ratioZbs.add(GSZB.SXFYL_65.getValue());
		ratioZbs.add(GSZB.XSLRL_28.getValue());
	}

	static {
		invisiableZbs.add(GSZB.CL67.getValue());		
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
