package com.tbea.ic.operation.service.ydzb.gszb.pipe.filter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;

public class AccSbdPipeFilter extends AccPipeFilter {

	private Integer month;
	private SbdNdjhZbDao sbdzbDao;

	
	private void addToCacheValues(List<Integer> zbs, List<Double> tmpValues, Company comp){
		Double valueTmp;
		Double valueCache;
		for (int i = 0, len = zbs.size(); i < len; ++i){
			valueTmp = tmpValues.get(i);
			valueCache = cacheValues.get(i);
			if (null != valueTmp){
				if (zbs.get(i) == GSZB.YSZK.getValue()){
					valueCache = Util.valueOf(valueCache) + 
							Util.valueOf(valueTmp) / month * 12 * sbdzbDao.getYszb(comp);
				} else if (zbs.get(i) == GSZB.CH.getValue()){
					valueCache = Util.valueOf(valueCache) + 
							Util.valueOf(valueTmp) / month * 12 * sbdzbDao.getChzb(comp);
				} else {
					valueCache = Util.valueOf(valueCache) + Util.valueOf(valueTmp);
				}
				cacheValues.set(i, valueCache);
			}
		}
	}
	

	
	protected void computeCacheValue(List<Integer> zbs, List<Company> companies){
		if (null == month) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateStart);
			month = cal.get(Calendar.MONTH) + 1;
		}
		
		if (null == cacheValues){
			cacheValues = new ArrayList<Double>();
			for (int i = 0; i < zbs.size(); ++i){
				cacheValues.add(null);
			}
		}
		
		List<Double> tmpValues;
		List<Company> tmpComp = new ArrayList<Company>();
		for (Company comp : companies){
			tmpComp.clear();
			tmpComp.add(comp);
			tmpValues = accumulator.compute(col, dateStart, dateEnd, zbs, tmpComp);
			addToCacheValues(zbs, tmpValues, comp);
		}
	}
	

	public AccSbdPipeFilter(SbdNdjhZbDao sbdzbDao, IAccumulator accumulator, int col) {
		super(accumulator, col);
		this.sbdzbDao = sbdzbDao;
	}
}
