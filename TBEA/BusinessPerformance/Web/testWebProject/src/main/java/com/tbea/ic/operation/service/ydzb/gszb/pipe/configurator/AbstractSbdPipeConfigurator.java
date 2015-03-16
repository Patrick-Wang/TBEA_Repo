package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;

public abstract class AbstractSbdPipeConfigurator implements IPipeConfigurator {

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

	public AbstractSbdPipeConfigurator(CompanyManager companyManager) {
		this.companyManager = companyManager;
	}
	
	protected List<Integer> getSpecialZbs(){
		return specialZbs;
	}
	
	protected List<Company> getNonSbdCompany(List<Company> companies) {
		Organization org = companyManager.getBMDBOrganization();
		Company sbd = org.getCompany(CompanyType.SBDCYJT);
		List<Company> retComps = new ArrayList<Company>();
		for (Company comp : companies) {
			if (!sbd.contains(comp)) {
				retComps.add(comp);
			}
		}
		return retComps;
	}

	protected List<Company> getSbdCompany(List<Company> companies) {
		Organization org = companyManager.getBMDBOrganization();
		Company sbd = org.getCompany(CompanyType.SBDCYJT);
		List<Company> retComps = new ArrayList<Company>();
		for (Company comp : companies) {
			if (sbd.contains(comp)) {
				retComps.add(comp);
			}
		}
		return retComps;
	}

}
