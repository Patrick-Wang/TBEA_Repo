package com.tbea.ic.operation.service.ydzb.gszb;

import java.util.List;

public class TbzzPipeFilter implements IPipeFilter {
	List<Double> cacheValues;
	int col;
	int qntqCol;
	int sjCol;

	public TbzzPipeFilter(int col, int qntqCol, int sjCol) {
		this.col = col;
		this.qntqCol = qntqCol;
		this.sjCol = sjCol;
	}

	@Override
	public void filter(int row, GszbPipe pipe) {
		updateZb(row, pipe.getZb(row));
	}

	private void updateZb(int row, Double[] zbRow) {
		if (zbRow[qntqCol] < 0 || zbRow[sjCol] < 0 || Math.abs(zbRow[sjCol]) < 0.0000001 || Math.abs(zbRow[qntqCol]) < 0.0000001){
			zbRow[col] = null;
		}else{
			zbRow[col] = zbRow[sjCol] / zbRow[qntqCol] - 1;
		}
	}
}
