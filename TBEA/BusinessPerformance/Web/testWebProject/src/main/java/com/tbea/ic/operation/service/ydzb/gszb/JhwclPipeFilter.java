package com.tbea.ic.operation.service.ydzb.gszb;

import java.util.List;

public class JhwclPipeFilter implements IPipeFilter {
	List<Double> cacheValues;
	int col;
	int jhCol;
	int sjCol;

	public JhwclPipeFilter(int col, int jhCol, int sjCol) {
		this.col = col;
		this.jhCol = jhCol;
		this.sjCol = sjCol;
	}

	@Override
	public void filter(int row, GszbPipe pipe) {
		updateZb(row, pipe.getZb(row));
	}

	private void updateZb(int row, Double[] zbRow) {
		if (zbRow[jhCol] < 0 || zbRow[sjCol] < 0 || Math.abs(zbRow[sjCol]) < 0.0000001 || Math.abs(zbRow[sjCol]) < 0.0000001){
			zbRow[col] = null;
		}else{
			zbRow[col] = zbRow[sjCol] / zbRow[jhCol];
		}
	}
}
