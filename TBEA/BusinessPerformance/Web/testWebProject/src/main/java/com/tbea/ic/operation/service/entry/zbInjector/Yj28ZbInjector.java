package com.tbea.ic.operation.service.entry.zbInjector;

import java.util.Calendar;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.shzt.SHZTDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.YJ28ZB;

class Yj28ZbInjector extends ZbInjector {

	
	YJ28ZBDao yj28zbDao;

	public Yj28ZbInjector(ZBXXDao zbxxDao, DWXXDao dwxxDao, SHZTDao shztDao,
			YJ28ZBDao yj28zbDao) {
		super(zbxxDao, dwxxDao, shztDao);
		this.yj28zbDao = yj28zbDao;
	}

	@Override
	public void inject(Integer zbId, double val, Calendar cal, Company comp) {
		boolean newEntity = false;
		YJ28ZB zb28 = yj28zbDao.getZb(zbId, Util.toDate(cal), comp);
		if (null == zb28) {
			newEntity = true;
			zb28 = new YJ28ZB();
			zb28.setZbxx(zbxxDao.getById(zbId));
			zb28.setDwxx(dwxxDao.getById(comp.getId()));
			
		}
		zb28.setYj28shzt(shztDao.getById(2));
		zb28.setYj28xgsj(new java.sql.Date(new java.util.Date()
				.getTime()));
		zb28.setNf(cal.get(Calendar.YEAR));
		zb28.setYf(cal.get(Calendar.MONTH) + 1);
		zb28.setYj28z(val);
		if (newEntity){
			yj28zbDao.create(zb28);
		} else{
			yj28zbDao.merge(zb28);
		}	
	}

}
