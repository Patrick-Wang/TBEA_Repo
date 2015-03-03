package com.tbea.ic.operation.service.ydzb.gszb.pipe.filter;

import java.util.Calendar;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;

public class AccSbdPipeFilter extends AccPipeFilter {

	private Integer month;
	private final static double yszb = 0.8;

	protected void updateZb(int row, int zbId, Double[] zbRow) {
		if (cacheValues.get(row) != null) {
			if (null == month) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateStart);
				month = cal.get(Calendar.MONTH) + 1;
			}
			zbRow[col] = Util.valueOf(zbRow[col]) + cacheValues.get(row)
					/ month * 12 * yszb;
		}
	}

	public AccSbdPipeFilter(IAccumulator accumulator, int col) {
		super(accumulator, col);
	}
}
