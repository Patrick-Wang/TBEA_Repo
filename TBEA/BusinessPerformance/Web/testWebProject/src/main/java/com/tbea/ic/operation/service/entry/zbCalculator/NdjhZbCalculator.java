package com.tbea.ic.operation.service.entry.zbCalculator;

import java.util.Calendar;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.RequestHandler;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.entry.zbInjector.ZbInjector;
import com.tbea.ic.operation.common.ZBStatus;

public class NdjhZbCalculator extends GeneralZbCalculator {

	SbdNdjhZbDao sbdNdjhzbDao;
	Company sbdCy;
	Double xssr;

	public NdjhZbCalculator(CompanyManager compMgr, ZbInjector injector,
			SbdNdjhZbDao sbdNdjhzbDao, RequestHandler<Request> handler) {
		super(injector, handler);
		this.sbdNdjhzbDao = sbdNdjhzbDao;
		sbdCy = compMgr.getBMDBOrganization().getCompany(CompanyType.SBDCYJT);
	}

	@Override
	protected void onHandling(Integer zbId, Double val, Calendar cal,
			Company comp, ZBStatus status) {
		boolean isHandled = false;
		if (GSZB.XSSR6.value().equals(zbId)){
			xssr = val;
		}else if (sbdCy.contains(comp)) {
			if (GSZB.YSZK32.value().equals(zbId) && null != xssr) {
				Double yszb = sbdNdjhzbDao
						.getYszb(cal.get(Calendar.YEAR), comp);
				if (null != yszb) {
					injector.inject(zbId, xssr * yszb, cal, comp, status);
					isHandled = true;
				}
			} else if (GSZB.CH35.value().equals(zbId) && null != xssr) {
				Double chzb = sbdNdjhzbDao 
						.getChzb(cal.get(Calendar.YEAR), comp);
				if (null != chzb) {
					injector.inject(zbId, xssr * chzb, cal, comp, status);
					isHandled = true;
				}
			} 
		} 
		
		if (!isHandled){
			super.onHandling(zbId, val, cal, comp, status);
		}
	}
}
