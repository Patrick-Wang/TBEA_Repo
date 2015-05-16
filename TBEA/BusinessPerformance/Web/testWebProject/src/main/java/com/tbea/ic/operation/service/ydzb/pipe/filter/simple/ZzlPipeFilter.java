package com.tbea.ic.operation.service.ydzb.pipe.filter.simple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.service.ydzb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.pipe.filter.IPipeFilter;

public class ZzlPipeFilter implements IPipeFilter {
	List<Integer[]> zzls = new ArrayList<Integer[]>();
	Set<Integer> excludeZbs = new HashSet<Integer>();
	
	public ZzlPipeFilter exclude(GSZB zb){
		excludeZbs.add(zb.getValue());
		return this;
	}
	
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
		if (!excludeZbs.contains(pipe.getRowId(row))){
			updateZb(row, pipe.getData(row));
		}
	}

	private void updateZb(int row, Double[] zbRow) {
		Integer[] zzl = null;
		for (int i = 0, len = zzls.size(); i < len; ++i) {
			zzl = zzls.get(i);
			if (null == zbRow[zzl[1]] || 
				null == zbRow[zzl[2]] || 
				Util.isNegative(zbRow[zzl[2]]) ||
				Util.isNegative(zbRow[zzl[1]]) ||
				Util.isZero(zbRow[zzl[1]]) ||
				Util.isZero(zbRow[zzl[2]])) {
				zbRow[zzl[0]] = null;
			} else {
				zbRow[zzl[0]] = zbRow[zzl[1]] / zbRow[zzl[2]] - 1;
			}
		}
	}
}
