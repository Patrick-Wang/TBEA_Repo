package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;

public class DysjPipeFilter implements IPipeFilter {
	List<Double> cacheValues;
	int col;
	Integer lrzeRow;
	Integer xssrRow;
	Integer rsRow;
	Integer sxfyRow;
	SJZBAccumulator accumulator;
	Date date;
	public DysjPipeFilter(int col, SJZBAccumulator accumulator, Date date) {
		this.col = col;
		this.date = date;
		this.accumulator = accumulator;
	}
	
	private void filterZbs(List<Integer> zbs, List<Integer> zbsTmp, List<Integer> indexList){
		int zbId = 0;
		for (int i = 0, len = zbs.size(); i < len; ++i) {
			zbId = zbs.get(i);
			if (GSZB.RJLR.getValue() == zbId
					|| GSZB.RJSR.getValue() == zbId
					|| GSZB.SXFYL.getValue() == zbId) {
				indexList.add(i);
			} else {
				zbsTmp.add(zbId);
			}
		}
	}
	
	private void fillZbs(List<Integer> indexList){
		for (int i = 0, len = indexList.size(); i < len; ++i) {
			cacheValues.add(indexList.get(i), null);
		}
	}

	private void updateCacheValues(GszbPipe pipe) {
		if (null == cacheValues) {
			List<Integer> zbsTmp = new ArrayList<Integer>();
			List<Integer> indexList = new ArrayList<Integer>();
			filterZbs(pipe.getZbIds(), zbsTmp, indexList);
			cacheValues = accumulator.compute(this.date, this.date, pipe.getCompanies(), pipe.getZbIds());
			fillZbs(indexList);
		}
	}

	private Double[] getRow(GszbPipe pipe, int row, int zbId) {
		if (GSZB.LRZE.getValue() == zbId) {
			lrzeRow = row;
		} else if (GSZB.XSSR.getValue() == zbId) {
			xssrRow = row;
		} else if (GSZB.RS.getValue() == zbId) {
			rsRow = row;
		} else if (GSZB.SXFY.getValue() == zbId) {
			sxfyRow = row;
		}
		return pipe.getZb(row);
	}

	@Override
	public void filter(int row, GszbPipe pipe) {
		updateCacheValues(pipe);
		int zbId = pipe.getZbId(row);
		Double[] zbRow = getRow(pipe, row, zbId);
		updateZb(row, zbId, zbRow);
	}

	private void updateZb(int row, int zbId, Double[] zbRow) {
		if (GSZB.RJLR.getValue() == zbId) {
			zbRow[col] = cacheValues.get(lrzeRow) / cacheValues.get(rsRow);
		} else if (GSZB.RJSR.getValue() == zbId) {
			zbRow[col] = cacheValues.get(xssrRow) / cacheValues.get(rsRow);
		} else if (GSZB.SXFYL.getValue() == zbId) {
			zbRow[col] = cacheValues.get(sxfyRow) / cacheValues.get(rsRow);
		} else{
			zbRow[col] = cacheValues.get(row);
		}
	}
}
