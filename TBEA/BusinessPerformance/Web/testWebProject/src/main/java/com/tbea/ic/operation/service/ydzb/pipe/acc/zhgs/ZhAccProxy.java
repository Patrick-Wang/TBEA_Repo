package com.tbea.ic.operation.service.ydzb.pipe.acc.zhgs;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;

public class ZhAccProxy implements IAccumulator{
	
	IAccumulator stub;

	public ZhAccProxy(IAccumulator stub) {
		super();
		this.stub = stub;
	}

	public List<Double> compute(int col, Date start, Date end,
			List<Integer> zbs, List<Company> companies) {
		return this.stub.compute(col, start, end, transform(zbs, companies), companies);
	}

	private boolean containsSpecialCompany(List<Company> companies){
		return false;
	}
	
	public List<Integer> transform(List<Integer> zbs, List<Company> companies){
		if (containsSpecialCompany(companies)){
			List<Integer> newZbs = new ArrayList<Integer>();
			newZbs.addAll(zbs);
			 replaceZb(newZbs);
			 return newZbs;
		}
		return zbs;
	}

	private void replaceZb(List<Integer> newZbs) {
		
	}


}
