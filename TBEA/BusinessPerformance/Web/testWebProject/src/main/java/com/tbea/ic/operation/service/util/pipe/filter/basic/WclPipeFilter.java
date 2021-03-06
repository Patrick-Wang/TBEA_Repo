package com.tbea.ic.operation.service.util.pipe.filter.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;
import com.util.tools.MathUtil;


public class WclPipeFilter implements IPipeFilter {
	List<Integer[]> wcls = new ArrayList<Integer[]>();
	Set<Integer> excludeZbs = new HashSet<Integer>();
	

	public WclPipeFilter exclude(Integer zb){
		excludeZbs.add(zb);
		return this;
	}
	
	public WclPipeFilter add(int dest, int sj, int jh) {
		wcls.add(new Integer[] { dest, sj, jh });
		return this;
	}

	@Override
	public void filter(int row, IPipe pipe) {
		if (!excludeZbs.contains(pipe.getIndicator(row))){
			updateZb(row, pipe.getRow(row));
		}
	}

	private void updateZb(int row, Double[] zbRow) {
		Integer[] wcl = null;
		for (int i = 0, len = wcls.size(); i < len; ++i) {
			wcl = wcls.get(i);
			if (null == zbRow[wcl[1]] || 
				null == zbRow[wcl[2]] || 
				MathUtil.isNegative(zbRow[wcl[1]]) || 
				MathUtil.isNegative(zbRow[wcl[2]]) || 
				MathUtil.isZero(zbRow[wcl[1]]) || 
				MathUtil.isZero(zbRow[wcl[2]])) {
				zbRow[wcl[0]] = null;
			} else {
				zbRow[wcl[0]] = zbRow[wcl[1]] / zbRow[wcl[2]];
			}
		}
	}
}
