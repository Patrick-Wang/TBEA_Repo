package com.tbea.ic.operation.service.report;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;

public class AccByComps implements IAccumulator {

	IAccumulator stub;
	
	public AccByComps(IAccumulator stub){
		this.stub = stub;
	}
	
	@Override
	public List<Double> compute(int col, Date start, Date end,
			List<Integer> zbs, List<Company> companies) {
		List<Double> result = new ArrayList<Double>();
		List<Company> compsTmp = new ArrayList<Company>();
		compsTmp.add(null);
		for (int i = 0; i < companies.size(); ++i){
			if (companies.get(i) != null){
				compsTmp.set(0, companies.get(i));
				result.addAll(stub.compute(col, start, end, zbs, compsTmp));
			}else{
				result.addAll(Util.resize(new ArrayList<Double>(), zbs.size()));
			}
		}
		return result;
	}

}
