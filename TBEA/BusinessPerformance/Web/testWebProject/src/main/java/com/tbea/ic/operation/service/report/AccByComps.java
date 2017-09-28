package com.tbea.ic.operation.service.report;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;
import com.util.tools.ListUtil;

public class AccByComps implements IAccumulator {

	IAccumulator stub;
	
	public AccByComps(IAccumulator stub){
		this.stub = stub;
	}
	
	private boolean isSpecialCompany(CompanyType tp) {
		return tp == CompanyType.DJBGS || tp == CompanyType.WYDXDLC
				|| tp == CompanyType.WLGS || tp == CompanyType.ZHGS_MYGS
				|| tp == CompanyType.JWZHGS;
	}
	
	private boolean isDeprecated(CompanyType tp){
		return tp == CompanyType.RDGS || tp == CompanyType.DLBZGS;
	}
	
	//zbs.size == 1
	@Override
	public List<Double> compute(int col, Date start, Date end,
			List<Integer> zbs, List<Company> companies) {
		List<Double> result = new ArrayList<Double>();
		List<Company> compsTmp = new ArrayList<Company>();
		compsTmp.add(null);
		List<Integer> newZbs = new ArrayList<Integer>();
		for (int i = 0; i < companies.size(); ++i){
			if (companies.get(i) != null){
				compsTmp.set(0, companies.get(i));
				if (isSpecialCompany(companies.get(i).getType()) && 
					zbs.contains(336)){
					if (newZbs.isEmpty()){
						for (Integer zb : zbs){
							if (zb.equals(336)){
								newZbs.add(GSZB.XSSR6.value());
							}else{
								newZbs.add(zb);
							}
						}
					}
					result.addAll(stub.compute(col, start, end, newZbs, compsTmp));
				}else{
					int size = result.size();
					int index  = zbs.indexOf(336);
					result.addAll(stub.compute(col, start, end, zbs, compsTmp));
					if(isDeprecated(companies.get(i).getType()) && index >= 0){
						result.set(size + index, null);
					}
				}
			}else{
				result.addAll(ListUtil.resize(new ArrayList<Double>(), zbs.size()));
			}
		}
		return result;
	}

}
