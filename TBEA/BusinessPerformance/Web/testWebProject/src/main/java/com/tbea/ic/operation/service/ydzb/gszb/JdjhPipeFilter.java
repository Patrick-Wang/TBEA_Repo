package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;

public class JdjhPipeFilter implements IPipeFilter {

	YDJHZBDao ydjhzbDao;
	List<Double> cacheValues;
	int col;
	Integer lrzeRow;
	Integer xssrRow;
	Integer rsRow;
	Integer sxfyRow;
	
	public JdjhPipeFilter(YDJHZBDao ydjhDao, int col, CompanyManager companyManager) {
		this.ydjhzbDao = ydjhDao;
		this.col = col;
	}

	
	private void filterZbs(List<Integer> zbs, List<Integer> zbsTmp, List<Integer> indexList){
		int zbId = 0;
		for (int i = 0, len = zbs.size(); i < len; ++i) {
			zbId = zbs.get(i);
			if (GSZB.YSZK.getValue() == zbId ||
				GSZB.CH.getValue() == zbId) {
				zbsTmp.add(zbId);
			} else {
				indexList.add(i);
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
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(pipe.getDate());
			
			int startMonth = (cal.get(Calendar.MONTH) + 1) - (cal.get(Calendar.MONTH) + 1) % 3 + 1;
			
			cacheValues = ydjhzbDao.getDyjhz(
					Date.valueOf(cal.get(Calendar.YEAR) + "-" + startMonth + "-1"), 
					pipe.getDate(), 
					zbsTmp,
					pipe.getCompanies());

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
		int zbId = pipe.getZbId(row);
		if (GSZB.YSZK.getValue() != zbId && GSZB.CH.getValue() != zbId) {
			updateCacheValues(pipe);
			Double[] zbRow = getRow(pipe, row, zbId);
			updateZb(row, zbId, zbRow);
		}
	}

	private void updateZb(int row, int zbId, Double[] zbRow) {	
		if (GSZB.RJLR.getValue() == zbId) {
			zbRow[col] = cacheValues.get(lrzeRow) / cacheValues.get(rsRow);
		} else if (GSZB.RJSR.getValue() == zbId) {
			zbRow[col] = cacheValues.get(xssrRow) / cacheValues.get(rsRow);
		} else if (GSZB.SXFYL.getValue() == zbId) {
			zbRow[col] = cacheValues.get(sxfyRow) / cacheValues.get(rsRow);
		} else {
			zbRow[col] = cacheValues.get(row);
		} 
	}

}
