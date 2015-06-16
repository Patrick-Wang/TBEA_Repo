package com.tbea.ic.operation.service.util.pipe.configurator;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;

public abstract class AbstractPipeConfigurator implements IPipeConfigurator {

	static List<Integer> invisiableZbs = new ArrayList<Integer>();
	static List<Integer> timePointNumberZbs = new ArrayList<Integer>();
	static List<Integer> ratioZbs = new ArrayList<Integer>();
	static List<Integer> zhHiddenZbs = new ArrayList<Integer>();

	static {
		timePointNumberZbs.add(GSZB.YSZK.getValue());
		timePointNumberZbs.add(GSZB.CH.getValue());
		timePointNumberZbs.add(GSZB.RS.getValue());
		timePointNumberZbs.add(GSZB.QZZJXMCH.getValue());
		timePointNumberZbs.add(GSZB.QZYQK.getValue());
		timePointNumberZbs.add(GSZB.QZKCSP.getValue());
		timePointNumberZbs.add(GSZB.BL.getValue());
		timePointNumberZbs.add(GSZB.QZJYWY.getValue());
	}

	static {
		ratioZbs.add(GSZB.RJLR.getValue());
		ratioZbs.add(GSZB.RJSR.getValue());
		ratioZbs.add(GSZB.SXFYL.getValue());
		ratioZbs.add(GSZB.XSLRL.getValue());
	}

	static {
		invisiableZbs.add(GSZB.CL.getValue());		
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
	protected static List<Integer> getZhHiddenZbs() {
		return zhHiddenZbs;
	}

	protected List<Integer> getInvisiableZbs(){
		return invisiableZbs;
	}
	
	protected List<Integer> getTimePointNumberZbs(){
		return timePointNumberZbs;
	}
	
	protected List<Integer> getRatioZbs(){
		return ratioZbs;
	}
}
