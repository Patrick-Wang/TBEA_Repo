package com.tbea.ic.operation.service.util.pipe.filter.composite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;


public class RatioIndicatorPipeFilter extends AbstractPipeFilter {

	Map<Integer, Integer> ratioParam = new HashMap<Integer, Integer>();
	Set<Integer> excludeCols = new HashSet<Integer>();
	
	public RatioIndicatorPipeFilter exclude(Integer col) {
		excludeCols.add(col);
		return this;
	}
	
	public RatioIndicatorPipeFilter(Integer baseCompany, Integer step) {
		this.includeRow(baseCompany, step);
	}

	
	
	public RatioIndicatorPipeFilter add(int indicator, int refIndicator) {
		ratioParam.put(indicator, refIndicator);
		return this;
	}

	@Override
	public void filter(int row, IPipe pipe) {
		int indicator = pipe.getIndicator(row);
		if (ratioParam.containsKey(indicator)){
			int refIndicatorIndex = pipe.getIndicators().indexOf(ratioParam.get(indicator));
			int indicatorIndex = pipe.getIndicators().indexOf(indicator);
			int refRow = row - (indicatorIndex - refIndicatorIndex) * pipe.getCompanies().size();
			int baseRow = this.rowInner2Outer(refIndicatorIndex, 0);
			updateZb(pipe.getRow(row), pipe.getRow(refRow), pipe.getRow(baseRow));
		}
	}

	private void updateZb(Double[] zbRow, Double[] refRow, Double[] baseRow) {
		for (int i = 0, len = zbRow.length; i < len; ++i) {
			if (!excludeCols.contains(i)){
				if (null == baseRow[i] || 
						null == refRow[i] || 
						Util.isNegative(baseRow[i]) || 
						Util.isNegative(refRow[i]) || 
						Util.isZero(baseRow[i]) || 
						Util.isZero(refRow[i])) {
						zbRow[i] = null;
					} else {
						zbRow[i] = refRow[i] / baseRow[i];
					}
			}
					
		}
	}
}
