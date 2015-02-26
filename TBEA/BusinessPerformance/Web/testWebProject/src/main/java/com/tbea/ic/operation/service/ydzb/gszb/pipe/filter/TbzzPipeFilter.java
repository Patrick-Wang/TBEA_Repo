package com.tbea.ic.operation.service.ydzb.gszb.pipe.filter;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;

public class TbzzPipeFilter implements IPipeFilter {
	List<Integer[]> tbzzs = new ArrayList<Integer[]>();

	public TbzzPipeFilter add(int dest, int sj, int tq) {
		tbzzs.add(new Integer[] { dest, sj, tq });
		return this;
	}

	@Override
	public void filter(int row, GszbPipe pipe) {
		updateZb(row, pipe.getZb(row));
	}

	private void updateZb(int row, Double[] zbRow) {
		Integer[] tbzz = null;
		for (int i = 0, len = tbzzs.size(); i < len; ++i) {
			tbzz = tbzzs.get(i);
			if (zbRow[tbzz[1]] < 0 || zbRow[tbzz[2]] < 0 || Math.abs(zbRow[tbzz[1]]) < 0.0000001
					|| Math.abs(zbRow[tbzz[2]]) < 0.0000001) {
				zbRow[tbzz[0]] = null;
			} else {
				zbRow[tbzz[0]] = zbRow[tbzz[1]] / zbRow[tbzz[2]] - 1;
			}
		}
	}
}
