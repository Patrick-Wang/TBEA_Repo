package com.tbea.ic.operation.service.ydzb.pipe.filter.basic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;

public abstract class AbstractDividedCompanyAccPipeFilter extends AccPipeFilter {

	protected List<Integer> dependentZbs = new ArrayList<Integer>();
	
	public AbstractDividedCompanyAccPipeFilter(IAccumulator accumulator,
			int col, Date dateStart, Date dateEnd) {
		super(accumulator, col, dateStart, dateEnd);
	}

	public AbstractDividedCompanyAccPipeFilter addDependentZbs(Integer zb){
		if (!dependentZbs.contains(zb)){
			dependentZbs.add(zb);
		}
		return this;
	}

	abstract protected Double onCompute(Integer curZb, List<Double> depValues, Company comp);
	
	private void addToCacheValues(List<Integer> zbs, List<Double> depValues, Company comp){
		Double valueCache;
		for (int i = 0, len = zbs.size(); i < len; ++i){
			valueCache = onCompute(zbs.get(i), depValues, comp);
			if (null != valueCache){
				cacheValues.set(i, Util.valueOf(cacheValues.get(i)) + valueCache);
			}
		}
	}

	protected void computeCacheValue(List<Integer> zbs, List<Company> companies){
		if (null == cacheValues){
			cacheValues = new ArrayList<Double>();
			for (int i = 0; i < zbs.size(); ++i){
				cacheValues.add(null);
			}
		}

		List<Company> tmpComp = new ArrayList<Company>();
		for (Company independentComp : companies){
			tmpComp.clear();
			tmpComp.add(independentComp);
			addToCacheValues(zbs, accumulator.compute(col, dateStart, dateEnd, dependentZbs, tmpComp), independentComp);
		}
	}
}
