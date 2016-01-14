package com.tbea.ic.operation.service.entry.zbCalculator;

import java.util.Calendar;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.entry.zbInjector.ZbInjector;
import com.tbea.ic.operation.common.ZBStatus;

abstract class AbstractZbCalculator implements ZbCalculator{
	
	protected ZbInjector injector;
	protected Double xssr;
	protected Double rs;
	protected Double lrze;
	protected Double sxfy;
	
	public AbstractZbCalculator(ZbInjector injector) {
		this.injector = injector;
	}

	public ZbInjector getInjector(){
		return injector;
	}
	
	public void reset(){
		xssr = null;
		rs = null;
		lrze = null;
		sxfy = null;
	}
	
	abstract protected void onHandling(Integer zbId, Double val, Calendar cal, Company comp, ZBStatus status);
	
	public void compute(Integer zbId, Double val, Calendar cal, Company comp, ZBStatus status){
		if (GSZB.XSSR6.getValue() == zbId){
			xssr = val;
		}else if (GSZB.RS61.getValue() == zbId){
			rs = val;
		}else if (GSZB.LRZE1.getValue() == zbId){
			lrze = val;
		}else if (GSZB.SXFY64.getValue() == zbId){
			sxfy = val;
		}
		
		if (GSZB.RJSR63.getValue() == zbId){
			setRjsr(cal, comp, status);
		}else if (GSZB.RJLR62.getValue() == zbId){
			setRjlr(cal, comp, status);
		}else if (GSZB.SXFYL_65.getValue() == zbId){
			setSxfyl(cal, comp, status);
		}/*else if (GSZB.XSLRL_28.getValue() == zbId){
			setXslrl(cal, comp, status);
		}*/else{
			onHandling(zbId, val, cal, comp, status);
		}
	}
	
	protected void setRjsr(Calendar cal, Company comp, ZBStatus status){
		if (null != xssr  && null != rs && Util.isPositive(rs) && Util.isPositive(xssr)){
			injector.inject(GSZB.RJSR63.getValue(), xssr / rs, cal, comp, status);
		} else{
			injector.remove(GSZB.RJSR63.getValue(), cal, comp);
		}
	}
	
	protected void setRjlr(Calendar cal, Company comp, ZBStatus status){
		if (null != lrze  && null != rs && Util.isPositive(rs) && Util.isPositive(lrze)){
			injector.inject(GSZB.RJLR62.getValue(), lrze / rs, cal, comp, status);
		} else{
			injector.remove(GSZB.RJLR62.getValue(), cal, comp);
		}
	}
	
	protected void setSxfyl(Calendar cal, Company comp, ZBStatus status){
		if (null != sxfy  && null != xssr && Util.isPositive(xssr) && Util.isPositive(sxfy)){
			injector.inject(GSZB.SXFYL_65.getValue(), sxfy / xssr, cal, comp, status);
		} else{
			injector.remove(GSZB.SXFYL_65.getValue(), cal, comp);
		}
	}
	
	protected void setXslrl(Calendar cal, Company comp, ZBStatus status){
		if (null != lrze  && null != xssr && Util.isPositive(xssr) && Util.isPositive(lrze)){
			injector.inject(GSZB.XSLRL_28.getValue(), lrze / xssr, cal, comp, status);
		}else{
			injector.remove(GSZB.XSLRL_28.getValue(), cal, comp);
		}
	}
}
