package com.tbea.ic.operation.service.entry.zbInjector;

import java.util.Calendar;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.shzt.SHZTDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.YJ28ZB;
import com.tbea.ic.operation.common.ZBStatus;

class Yj28ZbInjector extends ZbInjector {

	
	YJ28ZBDao yj28zbDao;

	public Yj28ZbInjector(ZBXXDao zbxxDao, DWXXDao dwxxDao, SHZTDao shztDao,
			YJ28ZBDao yj28zbDao) {
		super(zbxxDao, dwxxDao, shztDao);
		this.yj28zbDao = yj28zbDao;
	}

	@Override
	public void inject(Integer zbId, double val, Calendar cal, Company comp, ZBStatus status, Calendar time) {
		boolean newEntity = false;
		YJ28ZB zb = yj28zbDao.getZb(zbId, Util.toDate(cal), comp);
		if (null == zb) {
			newEntity = true;
			zb = new YJ28ZB();
			zb.setZbxx(zbxxDao.getById(zbId));
			zb.setDwxx(dwxxDao.getById(comp.getId()));
		}

		zb.setYj28shzt(shztDao.getById(status.ordinal()));
		if (time != null){
			zb.setYj28xgsj(new java.sql.Timestamp(time.getTimeInMillis()));
		}
		zb.setNf(cal.get(Calendar.YEAR));
		zb.setYf(cal.get(Calendar.MONTH) + 1);
		zb.setYj28z(val);
		if (newEntity){
			yj28zbDao.create(zb);
		} else{
			yj28zbDao.merge(zb);
		}	
	}

	@Override
	public void remove(Integer zbId, Calendar cal, Company comp) {
		YJ28ZB zb = yj28zbDao.getZb(zbId, Util.toDate(cal), comp);
		if (null != zb){
			yj28zbDao.delete(zb);
		}
	}
}
