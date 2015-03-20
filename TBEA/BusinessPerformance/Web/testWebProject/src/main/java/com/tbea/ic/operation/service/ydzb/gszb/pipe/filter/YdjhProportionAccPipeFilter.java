package com.tbea.ic.operation.service.ydzb.gszb.pipe.filter;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;

//占比指标
public class YdjhProportionAccPipeFilter extends NdjhProportionAccPipeFilter {

	private Integer month;
	
	public YdjhProportionAccPipeFilter(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc, int col, Date dateStart, Date dateEnd) {
		super(sbdzbDao, sjAcc, col, dateStart, dateEnd);
	}
	
	@Override
	protected Double onCompute(Integer curZb, List<Double> depValues,
			Company comp) {
		if (isSBDJYCompany(comp))
		{
			if (xssrRow != null && null != depValues.get(xssrRow)){
				if (curZb == GSZB.YSZK.getValue()){
					return Util.valueOf(depValues.get(xssrRow)) / month * 12 * sbdzbDao.getYszb(year, comp);
				} else if (curZb == GSZB.CH.getValue()){
					return Util.valueOf(depValues.get(xssrRow)) / month * 12 * sbdzbDao.getChzb(year, comp);
				}
			}
		}
	
		return null;
	}
	
	protected void computeCacheValue(List<Integer> zbs, List<Company> companies){
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateEnd);
		month = cal.get(Calendar.MONTH) + 1;
		super.computeCacheValue(zbs, companies);
	}
	private boolean isSBDJYCompany(Company comp)
	{	
		return comp.getId() == 1 || comp.getId() == 2 || comp.getId() == 3 || comp.getId() == 4 || comp.getId() == 5 || comp.getId() == 6;
	}
}
