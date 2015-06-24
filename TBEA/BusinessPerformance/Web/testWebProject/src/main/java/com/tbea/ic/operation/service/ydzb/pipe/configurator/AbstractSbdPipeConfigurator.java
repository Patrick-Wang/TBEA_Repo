package com.tbea.ic.operation.service.ydzb.pipe.configurator;

import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.configurator.AbstractPipeConfigurator;

public abstract class AbstractSbdPipeConfigurator extends AbstractPipeConfigurator {

	protected SbdNdjhZbDao sbdzbDao;
	
	protected CompanyManager companyManager;

	public AbstractSbdPipeConfigurator(SbdNdjhZbDao sbdzbDao) {
		this.sbdzbDao = sbdzbDao;
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
