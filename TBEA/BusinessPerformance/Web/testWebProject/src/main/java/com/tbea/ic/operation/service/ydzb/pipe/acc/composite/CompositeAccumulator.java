package com.tbea.ic.operation.service.ydzb.pipe.acc.composite;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;

public class CompositeAccumulator implements IAccumulator {

	private CompositeAccDataSource dataSource;
	
	public CompositeAccumulator(CompositeAccDataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private Double sum(Integer zb, List<Company> companies, int col){
		Double dRet = null;
		Double cellVal;
		Double[] srcRow;
		for (int i = 0, len = companies.size(); i < len; ++i){
			srcRow = this.dataSource.getRow(companies.get(i), zb);
			if (null != srcRow){
				cellVal = srcRow[col];
				if (null != cellVal){
					dRet = Util.valueOf(dRet) + cellVal;
				}
			}
		}
		return dRet;
	}

	@Override
	public List<Double> compute(int col, Date start, Date end, List<Integer> zbs,
			List<Company> companies) {
		List<Double> ret = new ArrayList<Double>();
		for (int i = 0, len = zbs.size(); i < len; ++i){
			ret.add(sum(zbs.get(i), companies, col));
		}
		return ret;
	}

}
