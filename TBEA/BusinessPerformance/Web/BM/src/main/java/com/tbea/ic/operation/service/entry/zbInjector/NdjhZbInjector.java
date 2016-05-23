package com.tbea.ic.operation.service.entry.zbInjector;

import java.util.Calendar;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.model.dao.jygk.shzt.SHZTDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.NDJHZB;
import com.tbea.ic.operation.common.ZBStatus;

class NdjhZbInjector extends ZbInjector {

	NDJHZBDao ndjhzbDao;
	
	public NdjhZbInjector(ZBXXDao zbxxDao, DWXXDao dwxxDao, SHZTDao shztDao,
			NDJHZBDao ndjhzbDao) {
		super(zbxxDao, dwxxDao, shztDao);
		this.ndjhzbDao = ndjhzbDao;
	}

	@Override
	public void inject(Integer zbId, double val, Calendar cal, Company comp, ZBStatus status) {
		boolean newEntity = false;
		NDJHZB zb = ndjhzbDao.getZb(zbId, Util.toDate(cal), comp);
		if (null == zb){
			newEntity = true;
			zb = new NDJHZB();
			zb.setZbxx(zbxxDao.getById(zbId));
			zb.setDwxx(dwxxDao.getById(comp.getId()));
		}
		zb.setNdjhshzt(shztDao.getById(status.ordinal()));	
		zb.setNdjhxgsj(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
		zb.setNf(cal.get(Calendar.YEAR));
		zb.setNdjhz(val);
		
		if (newEntity) {
			ndjhzbDao.create(zb);
		} else {
			ndjhzbDao.merge(zb);
		}
	}

	@Override
	public void remove(Integer zbId, Calendar cal, Company comp) {
		NDJHZB zb = ndjhzbDao.getZb(zbId, Util.toDate(cal), comp);
		if (null != zb){
			ndjhzbDao.delete(zb);
		}
	}

}
