package com.tbea.ic.operation.service.util.pipe.filter.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;

public class SumPipeFilter implements IPipeFilter {
	List<Integer[]> sums = new ArrayList<Integer[]>();
	Set<Integer> excludeZbs = new HashSet<Integer>();
	
	public SumPipeFilter exclude(GSZB zb){
		excludeZbs.add(zb.getValue());
		return this;
	}
	
	public SumPipeFilter exclude(Integer zb){
		excludeZbs.add(zb);
		return this;
	}
	
	public SumPipeFilter add(int dest, int ref1, int ref2) {
		sums.add(new Integer[] { dest, ref1, ref2 });
		return this;
	}

	@Override
	public void filter(int row, IPipe pipe) {
		if (!excludeZbs.contains(pipe.getIndicator(row))){
			updateZb(pipe.getRow(row));
		}
	}

	private void updateZb(Double[] zbRow) {
		Integer[] sum = null;
		for (int i = 0, len = sums.size(); i < len; ++i) {
			sum = sums.get(i);
			if (zbRow[sum[1]] != null && zbRow[sum[2]] != null){
				zbRow[sum[0]] = Util.valueOf(zbRow[sum[1]]) + Util.valueOf(zbRow[sum[2]]);
			}
		}
	}
}

