package com.tbea.ic.operation.service.report;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;

public class AdvanceAccByGroup{

	IAccumulator stub;
	
	public AdvanceAccByGroup(IAccumulator stub){
		this.stub = stub;
	}
	
	public List<Double> computeByCompanies(int col, Date start, Date end,
			List<List<Integer>> zbs, List<Company> companies) {
		List<Double> result = new ArrayList<Double>();
		List<Company> compsTmp = new ArrayList<Company>();
		compsTmp.add(null);
		for (int i = 0; i < companies.size(); ++i){
			if (companies.get(i) != null){
				compsTmp.set(0, companies.get(i));
				result.addAll(stub.compute(col, start, end, zbs.get(i), compsTmp));
			}else{
				result.addAll(Util.resize(new ArrayList<Double>(), zbs.get(i).size()));
			}
		}
		return result;
	}
	
	public List<Double> computeByZbs(int col, Date start, Date end,
			List<Integer> zbs, List<List<Company>> companies) {
		List<Double> result = new ArrayList<Double>();
		for (int i = 0; i < zbs.size(); ++i){
			result.addAll(stub.compute(col, start, end, zbs, companies.get(i)));
		}
		return result;
	}

}
