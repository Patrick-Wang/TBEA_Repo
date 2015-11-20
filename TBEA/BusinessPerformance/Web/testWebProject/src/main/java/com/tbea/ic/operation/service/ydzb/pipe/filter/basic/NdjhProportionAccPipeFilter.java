package com.tbea.ic.operation.service.ydzb.pipe.filter.basic;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
//占比指标
public class NdjhProportionAccPipeFilter extends AbstractDividedCompanyAccPipeFilter {

	protected Integer year;
	protected Integer xssrRow;
	protected SbdNdjhZbDao sbdzbDao;
	
	public NdjhProportionAccPipeFilter(SbdNdjhZbDao sbdzbDao, IAccumulator ndjhAcc, int col, Date date) {
		this(sbdzbDao, ndjhAcc, col, date, date);
	}
	
	public NdjhProportionAccPipeFilter(SbdNdjhZbDao sbdzbDao, IAccumulator ndjhAcc, int col, Date dateS, Date dateE) {
		super(ndjhAcc, col, dateS, dateE);
		this.sbdzbDao = sbdzbDao;
		addDependentZbs(GSZB.XSSR6.getValue());
	}

	public AbstractDividedCompanyAccPipeFilter addDependentZbs(Integer zb){
		super.addDependentZbs(zb);
		if (zb == GSZB.XSSR6.getValue()){
			xssrRow = dependentZbs.indexOf(zb);
		}
		return this;
	}
	
	@Override
	protected Double onCompute(Integer curZb, List<Double> depValues, Company comp) {
		if (xssrRow != null && null != depValues.get(xssrRow)){
			if (curZb == GSZB.YSZK32.getValue()){
				return Util.valueOf(depValues.get(xssrRow)) * sbdzbDao.getYszb(year, comp);
			} else if (curZb == GSZB.CH35.getValue()){
				return Util.valueOf(depValues.get(xssrRow)) * sbdzbDao.getChzb(year, comp);
			}
		}
		return null;
	}
	
	protected void computeCacheValue(List<Integer> zbs, List<Company> companies){
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateEnd);
		year = cal.get(Calendar.YEAR);
		super.computeCacheValue(zbs, companies);
	}
}
