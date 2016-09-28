package com.tbea.ic.operation.service.entry.zbInjector;

import java.util.Calendar;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.shzt.SHZTDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.NDJHZB;
import com.tbea.ic.operation.model.entity.jygk.YDJHZB;
import com.tbea.ic.operation.common.ZBStatus;

class YdjhZbInjector extends ZbInjector {

	YDJHZBDao ydjhzbDao;

	public YdjhZbInjector(ZBXXDao zbxxDao, DWXXDao dwxxDao, SHZTDao shztDao,
			YDJHZBDao ydjhzbDao, ZBInjectListener listener) {
		super(zbxxDao, dwxxDao, shztDao, listener);
		this.ydjhzbDao = ydjhzbDao;
	}

	@Override
	public void inject(Integer zbId, double val, Calendar cal, Company comp, ZBStatus status
			, Calendar time) {
		boolean newEntity = false;
		YDJHZB zb = ydjhzbDao.getZb(zbId, Util.toDate(cal), comp);
		if (null == zb){
			newEntity = true;
			zb = new YDJHZB();
			zb.setZbxx(zbxxDao.getById(zbId));
			zb.setDwxx(dwxxDao.getById(comp.getId()));
		}
		
		if (null != status){
			zb.setYdjhshzt(shztDao.getById(status.ordinal()));	
		}
		
		if (null != time){
			zb.setYdjhxgsj(new java.sql.Timestamp(time.getTimeInMillis()));
		}
		
		zb.setNf(cal.get(Calendar.YEAR));
		zb.setYf(cal.get(Calendar.MONTH) + 1);
		zb.setYdjhz(val);
		if (newEntity){
			ydjhzbDao.create(zb);
		}else{
			ydjhzbDao.merge(zb);
		}
		listener.onInjected(zbId, val, cal, comp, status, time);
	}

	@Override
	public void remove(Integer zbId, Calendar cal, Company comp) {
		YDJHZB zb = ydjhzbDao.getZb(zbId, Util.toDate(cal), comp);
		if (null != zb){
			ydjhzbDao.delete(zb);
		}
	}


}
