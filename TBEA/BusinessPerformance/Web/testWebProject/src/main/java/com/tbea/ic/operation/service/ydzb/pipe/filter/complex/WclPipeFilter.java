package com.tbea.ic.operation.service.ydzb.pipe.filter.complex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.ydzb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.pipe.filter.IPipeFilter;


public class WclPipeFilter implements IPipeFilter {
	List<Integer[]> wcls = new ArrayList<Integer[]>();
	Set<Integer> includeComps = new HashSet<Integer>();
	
	public WclPipeFilter include(Company comp){
		includeComps.add(comp.getType().ordinal());
		return this;
	}
	
	public WclPipeFilter add(int dest, int sj, int jh) {
		wcls.add(new Integer[] { dest, sj, jh });
		return this;
	}

	@Override
	public void filter(int row, IPipe pipe) {
		if (includeComps.contains(pipe.getRowId(row))){
			updateZb(row, pipe.getRow(row));
		}
	}

	private void updateZb(int row, Double[] zbRow) {
		Integer[] wcl = null;
		for (int i = 0, len = wcls.size(); i < len; ++i) {
			wcl = wcls.get(i);
			if (null == zbRow[wcl[1]] || 
				null == zbRow[wcl[2]] || 
				Util.isNegative(zbRow[wcl[1]]) || 
				Util.isNegative(zbRow[wcl[2]]) || 
				Util.isZero(zbRow[wcl[1]]) || 
				Util.isZero(zbRow[wcl[2]])) {
				zbRow[wcl[0]] = null;
			} else {
				zbRow[wcl[0]] = zbRow[wcl[1]] / zbRow[wcl[2]];
			}
		}
	}
}
