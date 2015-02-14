package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;

public class DyjhSbdPipeFilter implements IPipeFilter {

	YDJHZBDao ydjhzbDao;
	List<Double> cacheValues;
	int col;
	SJZBAccumulator accumulator;
	Company sbdComp;
	public DyjhSbdPipeFilter(YDJHZBDao ndjhzbDao, int col, SJZBAccumulator accumulator, CompanyManager companyManager) {
		this.ydjhzbDao = ndjhzbDao;
		this.col = col;
		this.accumulator = accumulator;
		sbdComp = companyManager.getBMDBOrganization().getCompany(CompanyType.SBDCYJT);
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
	
	private void fillZbs(List<Integer> indexList, List<Double> vals){
		for (int i = 0, len = indexList.size(); i < len; ++i) {
			cacheValues.add(indexList.get(i), vals.get(i));
		}
	}
	
	
	private List<Company> filterCompany(List<Company> companies){
		List<Company> retComps = new ArrayList<Company>();
		for (Company comp : companies){
			if (!comp.getSubCompanys().isEmpty() && comp.getType() != CompanyType.SBDCYJT){
				retComps.addAll(comp.getSubCompanys());
			}
		}
		return retComps;
	}
	
	private void updateCacheValues(GszbPipe pipe) {
		if (null == cacheValues) {
			List<Integer> zbsTmp = new ArrayList<Integer>();
			List<Integer> indexList = new ArrayList<Integer>();
			filterZbs(pipe.getZbIds(), zbsTmp, indexList);
			cacheValues = ydjhzbDao.getDyjhz(pipe.getDate(), zbsTmp,
					filterCompany(pipe.getCompanies()));
			List<Integer> zbs = new ArrayList<Integer>();
			zbs.add(GSZB.YSZK.getValue());
			zbs.add(GSZB.CH.getValue());
			Date end = pipe.getDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(end);;
			Date start = Date.valueOf(cal.get(Calendar.YEAR) + "-1-1");
			
			//List<Double> ret = accumulator.compute(start, end, pipe, zbs);
			//fillZbs(indexList, ret);
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
		if (GSZB.YSZK.getValue() == zbId) {
			zbRow[col] = Util.valueOf(zbRow[col]) + cacheValues.get(row);
		} else if (GSZB.CH.getValue() == zbId) {
			//zbRow[col] = cacheValues.get(xssrRow) / cacheValues.get(rsRow);
		} 
	}

}
