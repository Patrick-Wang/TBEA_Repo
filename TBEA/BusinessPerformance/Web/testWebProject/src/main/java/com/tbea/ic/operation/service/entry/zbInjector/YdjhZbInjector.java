package com.tbea.ic.operation.service.entry.zbInjector;

import java.util.Calendar;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.shzt.SHZTDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.YDJHZB;
import com.tbea.ic.operation.common.ZBStatus;

class YdjhZbInjector extends ZbInjector {

	YDJHZBDao ydjhzbDao;

	public YdjhZbInjector(ZBXXDao zbxxDao, DWXXDao dwxxDao, SHZTDao shztDao,
			YDJHZBDao ydjhzbDao) {
		super(zbxxDao, dwxxDao, shztDao);
		this.ydjhzbDao = ydjhzbDao;
	}

	@Override
	public void inject(Integer zbId, double val, Calendar cal, Company comp, ZBStatus status) {
		boolean newEntity = false;
		YDJHZB zb = ydjhzbDao.getZb(zbId, Util.toDate(cal), comp);
		if (null == zb){
			newEntity = true;
			zb = new YDJHZB();
			zb.setZbxx(zbxxDao.getById(zbId));
			zb.setDwxx(dwxxDao.getById(comp.getId()));
		}
		
		zb.setYdjhshzt(shztDao.getById(status.ordinal()));
		zb.setYdjhxgsj(new java.sql.Date(new java.util.Date().getTime()));
		zb.setNf(cal.get(Calendar.YEAR));
		zb.setYf(cal.get(Calendar.MONTH) + 1);
		zb.setYdjhz(val);
		if (newEntity){
			ydjhzbDao.create(zb);
		}else{
			ydjhzbDao.merge(zb);
		}
	}


}
