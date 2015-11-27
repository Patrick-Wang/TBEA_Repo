package com.tbea.ic.operation.service.util.pipe.filter.composite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;

public class SumPipeFilter extends AbstractPipeFilter  {
	Map<Integer, Integer[]> sums = new HashMap<Integer, Integer[]>();
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
		sums.put(dest, new Integer[] { ref1, ref2 });
		return this;
	}
	
	public void filter(int row, IPipe pipe) {
		if (contains(row) && !excludeZbs.contains(pipe.getIndicator(row))){
			updateZb(pipe.getRow(row));
		}
	}

	private void updateZb(Double[] zbRow) {
		Integer[] sum = null;
		for (Integer dest : sums.keySet()){
			sum = sums.get(dest);
			if (zbRow[sum[1]] != null && zbRow[sum[0]] != null){
				zbRow[dest] = Util.valueOf(zbRow[sum[0]]) + Util.valueOf(zbRow[sum[1]]);
			}
		}
	}
}

