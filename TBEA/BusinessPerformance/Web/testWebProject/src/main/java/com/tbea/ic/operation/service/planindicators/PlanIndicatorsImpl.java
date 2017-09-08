package com.tbea.ic.operation.service.planindicators;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.entity.jygk.NDJHZB;
import com.tbea.ic.operation.model.entity.jygk.YDJHZB;

@Service
@Transactional("transactionManager")
public class PlanIndicatorsImpl implements PlanIndicators {

	@Autowired
	NDJHZBDao ndjhzbDao;

	@Autowired
	YDJHZBDao ydjhzbDao;

	
	@Override
	public Double getYearPlan(Integer zbId, Company comp, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		NDJHZB njh = ndjhzbDao.getZb(zbId, new Date(cal.getTimeInMillis()), comp);
		if (njh == null) {
			return null;
		}
		return njh.getNdjhz();
	}

	@Override
	public Double getMonthPlan(Integer zbId, Company comp, int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		YDJHZB yjh = ydjhzbDao.getZb(zbId, new Date(cal.getTimeInMillis()), comp);
		if (yjh == null) {
			return null;
		}
		return yjh.getYdjhz();
	}

}
