package com.tbea.ic.operation.model.dao.jygk.sbdzb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;

@Repository
public class SbdNdjhZbDaoImpl implements SbdNdjhZbDao{
	private final static int baseYear = 2014;
	private static Map<CompanyType, List<Double>> ysZbs = new HashMap<CompanyType, List<Double>>(); 
	private static Map<CompanyType, List<Double>> chZbs = new HashMap<CompanyType, List<Double>>(); 
	static {
		List<Double> yearsZb = new ArrayList<Double>();
		yearsZb.add(0.19);
		yearsZb.add(0.25);
		ysZbs.put(CompanyType.SBGS, yearsZb);
		
		yearsZb = new ArrayList<Double>();
		yearsZb.add(0.18);
		yearsZb.add(0.22);
		ysZbs.put(CompanyType.HBGS, yearsZb);
		
		yearsZb = new ArrayList<Double>();
		yearsZb.add(0.17);
		yearsZb.add(0.20);
		ysZbs.put(CompanyType.XBC, yearsZb);
		
		yearsZb = new ArrayList<Double>();
		yearsZb.add(0.18);
		yearsZb.add(0.21);
		ysZbs.put(CompanyType.LLGS, yearsZb);
		
		yearsZb = new ArrayList<Double>();
		yearsZb.add(0.15);
		yearsZb.add(0.18);
		ysZbs.put(CompanyType.XLC, yearsZb);
		
		yearsZb = new ArrayList<Double>();
		yearsZb.add(0.16);
		yearsZb.add(0.19);
		ysZbs.put(CompanyType.DLGS, yearsZb);
	}
	
	static {
		List<Double> yearsZb = new ArrayList<Double>();
		yearsZb.add(0.12);
		yearsZb.add(0.12);
		chZbs.put(CompanyType.SBGS, yearsZb);
		
		yearsZb = new ArrayList<Double>();
		yearsZb.add(0.135);
		yearsZb.add(0.125);
		chZbs.put(CompanyType.HBGS, yearsZb);
		
		yearsZb = new ArrayList<Double>();
		yearsZb.add(0.13);
		yearsZb.add(0.13);
		chZbs.put(CompanyType.XBC, yearsZb);
		
		yearsZb = new ArrayList<Double>();
		yearsZb.add(0.12);
		yearsZb.add(0.105);
		chZbs.put(CompanyType.LLGS, yearsZb);
		
		yearsZb = new ArrayList<Double>();
		yearsZb.add(0.11);
		yearsZb.add(0.1);
		chZbs.put(CompanyType.XLC, yearsZb);
		
		yearsZb = new ArrayList<Double>();
		yearsZb.add(0.13);
		yearsZb.add(0.1);
		chZbs.put(CompanyType.DLGS, yearsZb);
	}
	
	public Double getYszb(int year, Company comp){
		if (ysZbs.containsKey(comp.getType())){
			ysZbs.get(comp.getType()).get(year - baseYear);
		}
		return null;
	}
	
	public Double getChzb(int year, Company comp){
		if (chZbs.containsKey(comp.getType())){
			chZbs.get(comp.getType()).get(year - baseYear);
		}
		return null;
	}
}
