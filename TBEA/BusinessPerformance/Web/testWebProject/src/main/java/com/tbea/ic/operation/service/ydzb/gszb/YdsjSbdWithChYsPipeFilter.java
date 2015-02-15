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

public class YdsjSbdWithChYsPipeFilter implements IPipeFilter {


	List<Double> cacheValues;
	int col;
	SJZBAccumulator accumulator;
	Company sbdComp;
	Date startDate;
	Date endDate;

	public YdsjSbdWithChYsPipeFilter( int col,
			SJZBAccumulator accumulator, CompanyManager companyManager,
			Date startDate, Date endDate) {

		this.col = col;
		this.accumulator = accumulator;
		this.startDate = startDate;
		this.endDate = endDate;
		sbdComp = companyManager.getBMDBOrganization().getCompany(
				CompanyType.SBDCYJT);
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
	
	
	private List<Company> filterSbdCompany(List<Company> companies){
		List<Company> retComps = new ArrayList<Company>();
		for (Company comp : companies){
			if (!sbdComp.contains(comp)){
				retComps.add(comp);
			}
		}
		return retComps;
	}
	
	private List<Company> getSbdCompany(List<Company> companies){
		List<Company> retComps = new ArrayList<Company>();
		for (Company comp : companies){
			if (sbdComp.contains(comp)){
				retComps.add(comp);
			}
		}
		return retComps;
	}
	
	private void updateCacheValues(GszbPipe pipe) {
		if (null == cacheValues) {
			List<Integer> zbsTmp = new ArrayList<Integer>();
			List<Integer> indexList = new ArrayList<Integer>();
			filterZbs(pipe.getZbIds(), zbsTmp, indexList);

			cacheValues = accumulator.compute(this.startDate, this.endDate, getSbdCompany(pipe.getCompanies()), zbsTmp);
			
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
		zbRow[col] = Util.valueOf(zbRow[col]) + cacheValues.get(row);
	}

}
