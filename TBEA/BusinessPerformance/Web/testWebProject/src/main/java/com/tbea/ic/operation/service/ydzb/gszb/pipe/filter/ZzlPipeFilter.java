package com.tbea.ic.operation.service.ydzb.gszb.pipe.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;

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
	public void filter(int row, GszbPipe pipe) {
		if (!excludeZbs.contains(pipe.getZbId(row))){
			updateZb(row, pipe.getZb(row));
		}
	}

	private void updateZb(int row, Double[] zbRow) {
		Integer[] zzl = null;
		for (int i = 0, len = zzls.size(); i < len; ++i) {
			zzl = zzls.get(i);
			if (null == zbRow[zzl[1]] || 
				null == zbRow[zzl[2]] || 
				zbRow[zzl[1]] < 0 || 
				zbRow[zzl[2]] < 0 || 
				Util.isZero(zbRow[zzl[1]]) ||
				Util.isZero(zbRow[zzl[2]])) {
				zbRow[zzl[0]] = null;
			} else {
				zbRow[zzl[0]] = zbRow[zzl[1]] / zbRow[zzl[2]] - 1;
			}
		}
	}
}
