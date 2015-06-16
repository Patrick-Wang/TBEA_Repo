package com.tbea.ic.operation.service.ydzb.pipe.filter.advanced;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;

public class ZzlPipeFilter implements IPipeFilter {
	List<Integer[]> zzls = new ArrayList<Integer[]>();
	Set<Integer> includeComps = new HashSet<Integer>();
	
	public ZzlPipeFilter include(Company comp){
		includeComps.add(comp.getType().ordinal());
		return this;
	}
	
	public ZzlPipeFilter add(int dest, int sj, int tq) {
		zzls.add(new Integer[] { dest, sj, tq });
		return this;
	}

	@Override
	public void filter(int row, IPipe pipe) {
		if (includeComps.contains(pipe.getRowId(row))){
			updateZb(row, pipe.getRow(row));
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
