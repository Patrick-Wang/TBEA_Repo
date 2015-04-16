package com.tbea.ic.operation.service.entry.zbCalculator;

import java.util.Calendar;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.entry.zbInjector.ZbInjector;
import com.tbea.ic.operation.common.ZBStatus;

public class NdjhZbCalculator extends AbstractZbCalculator {

	SbdNdjhZbDao sbdNdjhzbDao;

	public NdjhZbCalculator(ZbInjector injector, SbdNdjhZbDao sbdNdjhzbDao) {
		super(injector);
		this.sbdNdjhzbDao = sbdNdjhzbDao;
	}

	@Override
	protected void onHandling(Integer zbId, Double val, Calendar cal,
			Company comp, ZBStatus status) {

		if (GSZB.YSZK.getValue() == zbId && null != xssr) {
			Double chzb = sbdNdjhzbDao.getYszb(cal.get(Calendar.YEAR), comp);
			if (null != chzb) {
				injector.inject(zbId, xssr * chzb, cal, comp, status);
			}
		} else if (GSZB.CH.getValue() == zbId && null != xssr) {
			Double yszb = sbdNdjhzbDao.getYszb(cal.get(Calendar.YEAR), comp);
			if (null != yszb) {
				injector.inject(zbId, xssr * yszb, cal, comp, status);
			}
		} else {
			if (null != val){
				injector.inject(zbId, val, cal, comp, status);
			}
		}
	}

}
