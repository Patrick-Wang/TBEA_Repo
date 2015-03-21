package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;

public abstract class AbstractSbdPipeConfigurator implements IPipeConfigurator {

	protected SbdNdjhZbDao sbdzbDao;
	
	static List<Integer> specialZbs = new ArrayList<Integer>();
	static {
		specialZbs.add(GSZB.YSZK.getValue());
		specialZbs.add(GSZB.CH.getValue());
		specialZbs.add(GSZB.RJLR.getValue());
		specialZbs.add(GSZB.RJSR.getValue());
		specialZbs.add(GSZB.SXFYL.getValue());
		specialZbs.add(GSZB.RS.getValue());
		specialZbs.add(GSZB.CL.getValue());
		specialZbs.add(GSZB.XL.getValue());
	}
	
	protected CompanyManager companyManager;

	public AbstractSbdPipeConfigurator(SbdNdjhZbDao sbdzbDao) {
		this.sbdzbDao = sbdzbDao;
	}
	
	protected List<Integer> getSpecialZbs(){
		return specialZbs;
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
