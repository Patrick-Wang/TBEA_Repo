package com.tbea.ic.operation.service.util.pipe.filter.composite;

import java.util.ArrayList;
import java.util.List;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;


public class WclPipeFilter extends AbstractPipeFilter {
	List<Integer[]> wcls = new ArrayList<Integer[]>();
	
	public WclPipeFilter add(int dest, int sj, int jh) {
		wcls.add(new Integer[] { dest, sj, jh });
		return this;
	}

	@Override
	public void filter(int row, IPipe pipe) {
		if (contains(row)){
			updateZb(pipe.getRow(row));
		}
	}

	private void updateZb(Double[] zbRow) {
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
