package com.tbea.ic.operation.service.util.pipe.filter.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;
import com.util.tools.MathUtil;

public class ZzlPipeFilter implements IPipeFilter {
	List<Integer[]> zzls = new ArrayList<Integer[]>();
	Set<Integer> excludeZbs = new HashSet<Integer>();
	
	public ZzlPipeFilter exclude(Integer zb){
		excludeZbs.add(zb);
		return this;
	}
	
	public ZzlPipeFilter add(int dest, int sj, int tq) {
		zzls.add(new Integer[] { dest, sj, tq });
		return this;
	}

	@Override
	public void filter(int row, IPipe pipe) {
		if (!excludeZbs.contains(pipe.getIndicator(row))){
			updateZb(row, pipe.getRow(row));
		}
	}

	private void updateZb(int row, Double[] zbRow) {
		Integer[] zzl = null;
		for (int i = 0, len = zzls.size(); i < len; ++i) {
			zzl = zzls.get(i);
			if (null == zbRow[zzl[1]] || 
				null == zbRow[zzl[2]] || 
				MathUtil.isNegative(zbRow[zzl[2]]) ||
				MathUtil.isNegative(zbRow[zzl[1]]) ||
				MathUtil.isZero(zbRow[zzl[1]]) ||
				MathUtil.isZero(zbRow[zzl[2]])) {
				zbRow[zzl[0]] = null;
			} else {
				zbRow[zzl[0]] = zbRow[zzl[1]] / zbRow[zzl[2]] - 1;
			}
		}
	}
}
