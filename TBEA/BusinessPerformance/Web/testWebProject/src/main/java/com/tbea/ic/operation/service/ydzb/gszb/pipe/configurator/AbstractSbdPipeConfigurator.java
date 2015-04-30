package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;

public abstract class AbstractSbdPipeConfigurator implements IPipeConfigurator {

	protected SbdNdjhZbDao sbdzbDao;
	
	static List<Integer> invisiableZbs = new ArrayList<Integer>();
	static List<Integer> timePointNumberZbs = new ArrayList<Integer>();
	static List<Integer> ratioZbs = new ArrayList<Integer>();

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
	
	protected CompanyManager companyManager;

	public AbstractSbdPipeConfigurator(SbdNdjhZbDao sbdzbDao) {
		this.sbdzbDao = sbdzbDao;
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
	
	protected void seperate(List<Company> companies, List<Company> sbdComps, List<Company> nonSbdComps){
		for (Company comp : companies) {
			if (sbdzbDao.contains(comp)) {
				sbdComps.add(comp);
			} else{
				nonSbdComps.add(comp);
			}
		}
	}
}
