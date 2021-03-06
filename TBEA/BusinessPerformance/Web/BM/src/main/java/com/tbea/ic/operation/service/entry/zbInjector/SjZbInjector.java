package com.tbea.ic.operation.service.entry.zbInjector;

import java.util.Calendar;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.shzt.SHZTDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.SJZB;

class SjZbInjector extends ZbInjector {
	
	SJZBDao sjzbDao;

	public SjZbInjector(ZBXXDao zbxxDao, DWXXDao dwxxDao, SHZTDao shztDao,
			SJZBDao sjzbDao) {
		super(zbxxDao, dwxxDao, shztDao);
		this.sjzbDao = sjzbDao;
	}

	@Override
	public void inject(Integer zbId, double val, Calendar cal, Company comp, ZBStatus status) {
		boolean newEntity = false;;
		SJZB zb = sjzbDao.getZb(zbId, Util.toDate(cal), comp);
		if (null == zb){
			newEntity = true;
			zb = new SJZB();
			zb.setZbxx(zbxxDao.getById(zbId));
			zb.setDwxx(dwxxDao.getById(comp.getId()));
			
		}
		zb.setSjshzt(shztDao.getById(status.ordinal()));		
		zb.setSjxgsj(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
		zb.setNf(cal.get(Calendar.YEAR));
		zb.setYf(cal.get(Calendar.MONTH) + 1);
		zb.setSjz(val);
		if (newEntity) {
			sjzbDao.create(zb);
		} else {
			sjzbDao.merge(zb);
		}
	}
	
	@Override
	public void remove(Integer zbId, Calendar cal, Company comp) {
		SJZB zb = sjzbDao.getZb(zbId, Util.toDate(cal), comp);
		if (null != zb){
			sjzbDao.delete(zb);
		}
	}
}
