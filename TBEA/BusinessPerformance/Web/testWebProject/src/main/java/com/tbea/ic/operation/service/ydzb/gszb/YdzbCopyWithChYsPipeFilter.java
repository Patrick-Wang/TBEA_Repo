package com.tbea.ic.operation.service.ydzb.gszb;

import java.util.List;


import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;

public class YdzbCopyWithChYsPipeFilter implements IPipeFilter {

	YDJHZBDao ydjhzbDao;
	List<Double> cacheValues;
	int col;
	int dyjhCol;
	public YdzbCopyWithChYsPipeFilter(int col, int dyjhCol) {
		this.col = col;
		this.dyjhCol = dyjhCol;
	}


	private Double[] getRow(GszbPipe pipe, int row, int zbId) {
		return pipe.getZb(row);
	}

	@Override
	public void filter(int row, GszbPipe pipe) {
		int zbId = pipe.getZbId(row);
		if (GSZB.YSZK.getValue() == zbId || GSZB.CH.getValue() == zbId) {
			Double[] zbRow = getRow(pipe, row, zbId);
			updateZb(row, zbId, zbRow);
		}
	}

	private void updateZb(int row, int zbId, Double[] zbRow) {
		zbRow[col] = zbRow[dyjhCol];
	}

}
