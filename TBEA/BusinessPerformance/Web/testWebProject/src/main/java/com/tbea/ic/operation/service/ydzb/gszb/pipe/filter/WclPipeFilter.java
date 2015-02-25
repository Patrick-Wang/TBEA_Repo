package com.tbea.ic.operation.service.ydzb.gszb.pipe.filter;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;


public class WclPipeFilter implements IPipeFilter {
	List<Integer[]> wcls = new ArrayList<Integer[]>();

	public WclPipeFilter add(int dest, int sj, int jh) {
		wcls.add(new Integer[] { dest, sj, jh });
		return this;
	}

	@Override
	public void filter(int row, GszbPipe pipe) {
		updateZb(row, pipe.getZb(row));
	}

	private void updateZb(int row, Double[] zbRow) {
		Integer[] wcl = null;
		for (int i = 0, len = wcls.size(); i < len; ++i) {
			wcl = wcls.get(i);
			if (zbRow[wcl[1]] < 0 || zbRow[wcl[2]] < 0 || Math.abs(zbRow[wcl[1]]) < 0.0000001
					|| Math.abs(zbRow[wcl[2]]) < 0.0000001) {
				zbRow[wcl[0]] = null;
			} else {
				zbRow[wcl[0]] = zbRow[wcl[1]] / zbRow[wcl[2]];
			}
		}
	}
}
