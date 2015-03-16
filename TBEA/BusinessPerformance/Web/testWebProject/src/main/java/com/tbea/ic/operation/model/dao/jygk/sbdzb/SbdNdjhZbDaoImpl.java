package com.tbea.ic.operation.model.dao.jygk.sbdzb;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;

@Repository
public class SbdNdjhZbDaoImpl implements SbdNdjhZbDao{
	private static Map<CompanyType, Double> ysZbs = new HashMap<CompanyType, Double>(); 
	private static Map<CompanyType, Double> chZbs = new HashMap<CompanyType, Double>(); 
	static {
		ysZbs.put(CompanyType.SBGS, 0.25);
		ysZbs.put(CompanyType.HBGS, 0.22);
		ysZbs.put(CompanyType.XBC, 0.20);
		ysZbs.put(CompanyType.LLGS, 0.21);
		ysZbs.put(CompanyType.XLC, 0.18);
		ysZbs.put(CompanyType.DLGS, 0.19);
	}
	
	static {
		chZbs.put(CompanyType.SBGS, 0.12);
		chZbs.put(CompanyType.HBGS, 0.125);
		chZbs.put(CompanyType.XBC, 0.13);
		chZbs.put(CompanyType.LLGS, 0.105);
		chZbs.put(CompanyType.XLC, 0.10);
		chZbs.put(CompanyType.DLGS, 0.10);
	}
	
	public Double getYszb(Company comp){
		return ysZbs.get(comp.getType());
	}
	
	public Double getChzb(Company comp){
		return chZbs.get(comp.getType());
	}
}
