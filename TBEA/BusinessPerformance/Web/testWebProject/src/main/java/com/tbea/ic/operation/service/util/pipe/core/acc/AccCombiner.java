package com.tbea.ic.operation.service.util.pipe.core.acc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;

public class AccCombiner implements IAccumulator {

	List<IAccumulator> accList = new ArrayList<IAccumulator>();

	public AccCombiner join(IAccumulator acc) {
		accList.add(acc);
		return this;
	}

	private List<Double> combine(List<List<Double>> resultList, List<Integer> zbs){
		List<Double> combinedResult = new ArrayList<Double>();
		for (int i = 0; i < zbs.size(); ++i){
			combinedResult.add(null);
			for (int j = resultList.size() - 1; j >= 0; --j){
				if (null != resultList.get(j).get(i)){
					combinedResult.set(i, resultList.get(j).get(i));
				}
			}
		}
		return combinedResult;
	}
	
	@Override
	public List<Double> compute(int col, Date start, Date end,
			List<Integer> zbs, List<Company> companies) {
		List<List<Double>> result = new ArrayList<List<Double>>();
		for (int i = 0; i < accList.size(); ++i) {
			result.add(accList.get(i).compute(col, start, end, zbs, companies));
		}
		
		return combine(result, zbs);
	}

}
