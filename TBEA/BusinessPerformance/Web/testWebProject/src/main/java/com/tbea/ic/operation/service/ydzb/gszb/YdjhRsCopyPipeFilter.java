package com.tbea.ic.operation.service.ydzb.gszb;

import java.util.ArrayList;
import java.util.List;


import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;

public class YdjhRsCopyPipeFilter implements IPipeFilter {

	YDJHZBDao ydjhzbDao;
	List<Double> cacheValues;
	int col;
	int ref;
	public YdjhRsCopyPipeFilter(int col, int ref) {
		this.col = col;
		this.ref = ref;
	}
	

	private Double[] getRow(GszbPipe pipe, int row, int zbId) {
		return pipe.getZb(row);
	}

	@Override
	public void filter(int row, GszbPipe pipe) {
		int zbId = pipe.getZbId(row);
		if (GSZB.RS.getValue() == zbId) {
			Double[] zbRow = getRow(pipe, row, zbId);
			updateZb(row, zbId, zbRow);
		}
	}

	private void updateZb(int row, int zbId, Double[] zbRow) {
		zbRow[col] = zbRow[ref];
	}

}
