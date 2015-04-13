package com.tbea.ic.operation.service.entry.zbInjector;

import java.util.Calendar;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.shzt.SHZTDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.YDJHZB;

class YdjhZbInjector extends ZbInjector {

	YDJHZBDao ydjhzbDao;

	public YdjhZbInjector(ZBXXDao zbxxDao, DWXXDao dwxxDao, SHZTDao shztDao,
			YDJHZBDao ydjhzbDao) {
		super(zbxxDao, dwxxDao, shztDao);
		this.ydjhzbDao = ydjhzbDao;
	}

	@Override
	public void inject(Integer zbId, double val, Calendar cal, Company comp) {
		boolean newEntity = false;
		YDJHZB ydjhzb = ydjhzbDao.getZb(zbId, Util.toDate(cal), comp);
		if (null == ydjhzb){
			newEntity = true;
			ydjhzb = new YDJHZB();
			ydjhzb.setZbxx(zbxxDao.getById(zbId));
			ydjhzb.setDwxx(dwxxDao.getById(comp.getId()));
		}
		ydjhzb.setYdjhshzt(shztDao.getById(2));
		ydjhzb.setYdjhxgsj(new java.sql.Date(new java.util.Date().getTime()));
		ydjhzb.setNf(cal.get(Calendar.YEAR));
		ydjhzb.setYf(cal.get(Calendar.MONTH) + 1);
		ydjhzb.setYdjhz(val);
		if (newEntity){
			ydjhzbDao.create(ydjhzb);
		}else{
			ydjhzbDao.merge(ydjhzb);
		}
	}


}
