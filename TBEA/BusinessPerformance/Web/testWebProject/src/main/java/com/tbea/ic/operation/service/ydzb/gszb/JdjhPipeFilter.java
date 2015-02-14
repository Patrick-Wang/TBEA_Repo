package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;

public class JdjhPipeFilter implements IPipeFilter {

	YDJHZBDao ydjhzbDao;
	List<Double> cacheValues;
	int col;
	int dyjhCol;
	public JdjhPipeFilter(YDJHZBDao ydjhDao, int col, int dyjhCol, CompanyManager companyManager) {
		this.ydjhzbDao = ydjhDao;
		this.col = col;
		this.dyjhCol = dyjhCol;

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
//			cacheValues = ydjhzbDao.getDyjhz(pipe.getDate(), pipe.getDate(), zbsTmp,
//					filterSbdCompany(pipe.getCompanies()));
			Date end = pipe.getDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(end);
			Date start = Date.valueOf(cal.get(Calendar.YEAR) + "-1-1");
//			List<Double> sbdRet = accumulator.compute(start, end, getSbdCompany(pipe.getCompanies()), zbsTmp);
			
			for (int i = 0; i < zbsTmp.size(); ++i){
//				cacheValues.set(i, cacheValues.get(i) + sbdRet.get(i));
			}

			fillZbs(indexList);
		}
	}

	private Double[] getRow(GszbPipe pipe, int row, int zbId) {
		return pipe.getZb(row);
	}

	@Override
	public void filter(int row, GszbPipe pipe) {
		int zbId = pipe.getZbId(row);
		if (GSZB.YSZK.getValue() == zbId || GSZB.CH.getValue() == zbId) {
			updateCacheValues(pipe);

			Double[] zbRow = getRow(pipe, row, zbId);
			updateZb(row, zbId, zbRow);
		}
	}

	private void updateZb(int row, int zbId, Double[] zbRow) {
		if (GSZB.YSZK.getValue() == zbId || GSZB.CH.getValue() == zbId) {
			zbRow[col] = cacheValues.get(row);
		} 
	}

}
