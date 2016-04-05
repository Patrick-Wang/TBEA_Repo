package com.tbea.ic.operation.service.util.pipe.filter.composite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;

//计算的比例为引用指标的 当前公司 除以 合计公司，合计公司必须位于引用指标的所有公司最后一个
public class RatioPipeFilter extends AbstractPipeFilter {

	Map<Integer, Integer> ratioParam = new HashMap<Integer, Integer>();
	Set<Integer> excludeCols = new HashSet<Integer>();
	
	public RatioPipeFilter exclude(Integer col) {
		excludeCols.add(col);
		return this;
	}
	
	public RatioPipeFilter(Integer baseCompany, Integer step) {
		this.includeRow(baseCompany, step);
	}

	
	
	public RatioPipeFilter add(int indicator, int refIndicator) {
		ratioParam.put(indicator, refIndicator);
		return this;
	}

	@Override
	public void filter(int row, IPipe pipe) {
		int indicator = pipe.getIndicator(row);
		if (ratioParam.containsKey(indicator)){
			int refIndicatorIndex = pipe.getIndicators().indexOf(ratioParam.get(indicator));
			int indicatorIndex = pipe.getIndicators().indexOf(indicator);
			
			//当前公司
			int refRow = row - (indicatorIndex - refIndicatorIndex) * getStep(0);
			
			//指标对应最后一个公司为合计公司
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
