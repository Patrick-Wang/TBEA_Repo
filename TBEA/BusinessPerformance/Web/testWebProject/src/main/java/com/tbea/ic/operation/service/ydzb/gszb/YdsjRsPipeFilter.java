package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;

public class YdsjRsPipeFilter implements IPipeFilter {

	List<Double> cacheValues;
	int col;
	SJZBAccumulator accumulator;
	Date date;
	public YdsjRsPipeFilter(int col, SJZBAccumulator accumulator, Date date) {
		this.col = col;
		this.accumulator = accumulator;
		this.date = date;
	}
	
	private void filterZbs(List<Integer> zbs, List<Integer> zbsTmp, List<Integer> indexList){
		int zbId = 0;
		for (int i = 0, len = zbs.size(); i < len; ++i) {
			zbId = zbs.get(i);
			if (GSZB.RS.getValue() == zbId) {
				zbsTmp.add(zbId);
			} else {
				indexList.add(i);
			}
		}
	}
	

	
	
	private void updateCacheValues(GszbPipe pipe) {
		if (null == cacheValues) {
			List<Integer> zbsTmp = new ArrayList<Integer>();
			List<Integer> indexList = new ArrayList<Integer>();
			filterZbs(pipe.getZbIds(), zbsTmp, indexList);
			cacheValues = accumulator.compute(date, date, pipe.getCompanies(), zbsTmp);
		}
	}

	private Double[] getRow(GszbPipe pipe, int row, int zbId) {
		return pipe.getZb(row);
	}

	@Override
	public void filter(int row, GszbPipe pipe) {
		int zbId = pipe.getZbId(row);
		if (GSZB.RS.getValue() == zbId) {
			updateCacheValues(pipe);
			Double[] zbRow = getRow(pipe, row, zbId);
			updateZb(row, zbId, zbRow);
		}
	}

	private void updateZb(int row, int zbId, Double[] zbRow) {
		zbRow[col] = cacheValues.get(0);
	}

}
