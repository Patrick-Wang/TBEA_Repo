package com.tbea.ic.operation.service.entry;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.RequestHandler;
import com.tbea.ic.operation.service.entry.zbCalculator.Request;
import com.tbea.ic.operation.service.entry.zbCalculator.requesthandler.RatioRequestHandler;

public class CalculatedZbManager {
	final static Set<Integer> defaultZbs = new HashSet<Integer>();
	final static Set<Integer> jh2016Zbs = new HashSet<Integer>();//include 2016

	static {
		defaultZbs.add(GSZB.RJSR63.getValue());
		defaultZbs.add(GSZB.RJLR62.getValue());
		defaultZbs.add(GSZB.SXFYL_65.getValue());
		defaultZbs.add(GSZB.XSLRL_28.getValue());
		defaultZbs.add(GSZB.ZZYSXFYL231.getValue());
		defaultZbs.add(GSZB.GC_XSYWSXFYL232.getValue());
		defaultZbs.add(GSZB.WLMYSXFYL233.getValue());
	};

	static {
		jh2016Zbs.add(GSZB.RJSR63.getValue());
		jh2016Zbs.add(GSZB.RJLR62.getValue());
		jh2016Zbs.add(GSZB.SXFYL_65.getValue());
		jh2016Zbs.add(GSZB.ZZYSXFYL231.getValue());
		jh2016Zbs.add(GSZB.GC_XSYWSXFYL232.getValue());
		jh2016Zbs.add(GSZB.WLMYSXFYL233.getValue());
	};
	
	
	public static Set<Integer> getJHCalculatedZbs(Calendar cal){
		if (cal.get(Calendar.YEAR) >= 2016){
			return jh2016Zbs;
		}
		return defaultZbs;
	}
	
	public static Set<Integer> getSJCalculatedZbs(){
		return defaultZbs;
	}
	
	public static RequestHandler<Request> getJHHandler(Calendar cal){
		RequestHandler<Request> handler = new RatioRequestHandler(GSZB.RJLR62,GSZB.LRZE1, GSZB.RS61);
		handler.add(new RatioRequestHandler(GSZB.RJSR63,GSZB.XSSR6, GSZB.RS61))
			.add(new RatioRequestHandler(GSZB.SXFYL_65,GSZB.SXFY64, GSZB.XSSR6))
			.add(new RatioRequestHandler(GSZB.GC_XSYWSXFYL232,GSZB.GC_XSYW229, GSZB.XSSR_GCXMSR12))
			.add(new RatioRequestHandler(GSZB.WLMYSXFYL233,GSZB.WLMY230, GSZB.XSSR_WLMYSR16))
			.add(new RatioRequestHandler(GSZB.ZZYSXFYL231,GSZB.ZZY_YWKJ_228, GSZB.XSSR_ZZYSR7));
		if (cal.get(Calendar.YEAR) < 2016){
			handler.add(new RatioRequestHandler(GSZB.XSLRL_28,GSZB.LRZE1, GSZB.XSSR6));
		}
		return handler;
	}
	
	public static RequestHandler<Request> getSJHandler(){
		RequestHandler<Request> handler = new RatioRequestHandler(GSZB.RJLR62,GSZB.LRZE1, GSZB.RS61);
		handler.add(new RatioRequestHandler(GSZB.RJSR63,GSZB.XSSR6, GSZB.RS61))
			.add(new RatioRequestHandler(GSZB.SXFYL_65,GSZB.SXFY64, GSZB.XSSR6))
			.add(new RatioRequestHandler(GSZB.XSLRL_28,GSZB.LRZE1, GSZB.XSSR6))
			.add(new RatioRequestHandler(GSZB.GC_XSYWSXFYL232,GSZB.GC_XSYW229, GSZB.XSSR_GCXMSR12))
			.add(new RatioRequestHandler(GSZB.WLMYSXFYL233,GSZB.WLMY230, GSZB.XSSR_WLMYSR16))
			.add(new RatioRequestHandler(GSZB.ZZYSXFYL231,GSZB.ZZY_YWKJ_228, GSZB.XSSR_ZZYSR7));
		return handler;
	}
}
