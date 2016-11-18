package com.tbea.ic.operation.service.report;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;

public class MixedAcc{

	IAccumulator stub;
	
	public MixedAcc(IAccumulator stub){
		this.stub = stub;
	}

	public Double sumAll(Date start, Date end,
			List<Integer> zbs, List<Company> companies) {
		List<Double> result = stub.compute(0, start, end, zbs, companies);
		if (result.size() == 1){
			return result.get(0);
		}else{
			return MathUtil.sum(result);
		}
	}
	
	public List<Double> sumCompanies(Date start, Date end,
			List<Integer> zbs, List<Company> companies) {
		return stub.compute(0, start, end, zbs, companies);
	}
	
	public List<Double> sumZbs(Date start, Date end,
			List<Integer> zbs, List<Company> companies) {
		List<Double> result = new ArrayList<Double>(companies.size());
		List<Company> comps = new ArrayList<Company>(1);
		comps.add(null);
		for(Company comp : companies){
			comps.set(0, comp);
			result.add(sumAll(start, end, zbs, comps));
		}
		return result;
	}
}
