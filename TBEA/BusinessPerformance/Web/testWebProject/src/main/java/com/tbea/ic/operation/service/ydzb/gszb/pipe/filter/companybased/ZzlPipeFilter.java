package com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.companybased;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.IPipeFilter;

public class ZzlPipeFilter implements IPipeFilter {
	List<Integer[]> zzls = new ArrayList<Integer[]>();
	Set<Integer> includeComps = new HashSet<Integer>();
	
	public ZzlPipeFilter include(CompanyType comp){
		includeComps.add(comp.ordinal());
		return this;
	}
	
	public ZzlPipeFilter add(int dest, int sj, int tq) {
		zzls.add(new Integer[] { dest, sj, tq });
		return this;
	}

	@Override
	public void filter(int row, IPipe pipe) {
		if (includeComps.contains(pipe.getRowId(row))){
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
