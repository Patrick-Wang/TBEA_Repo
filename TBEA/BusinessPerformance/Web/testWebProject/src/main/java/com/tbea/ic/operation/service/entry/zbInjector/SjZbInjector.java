package com.tbea.ic.operation.service.entry.zbInjector;

import java.util.Calendar;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.shzt.SHZTDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.SJZB;
import com.util.tools.DateUtil;

class SjZbInjector extends ZbInjector {
	
	SJZBDao sjzbDao;

	public SjZbInjector(ZBXXDao zbxxDao, DWXXDao dwxxDao, SHZTDao shztDao,
			SJZBDao sjzbDao, ZBInjectListener listener) {
		super(zbxxDao, dwxxDao, shztDao, listener);
		this.sjzbDao = sjzbDao;
	}

	@Override
	public void inject(Integer zbId, Double val, Calendar cal, Company comp, 
			ZBStatus status, Calendar time) {
		boolean newEntity = false;;
		SJZB zb = sjzbDao.getZb(zbId, DateUtil.toDate(cal), comp);
		if (null == zb){
			newEntity = true;
			zb = new SJZB();
			zb.setZbxx(zbxxDao.getById(zbId));
			zb.setDwxx(dwxxDao.getById(comp.getId()));
			
		}
		if (null != status){
			zb.setSjshzt(shztDao.getById(status.ordinal()));	
		}
			
		if (null != time){
			zb.setSjxgsj(new java.sql.Timestamp(time.getTimeInMillis()));
		}
		zb.setNf(cal.get(Calendar.YEAR));
		zb.setYf(cal.get(Calendar.MONTH) + 1);
		
		if (val != null){
			zb.setSjz(val);
		}

		if (newEntity) {
			sjzbDao.create(zb);
		} else {
			sjzbDao.merge(zb);
		}
		listener.onInjected(zbId, val, cal, comp, status, time);
	}
	
	@Override
	public Double remove(Integer zbId, Calendar cal, Company comp) {
		Double ret = null;
		SJZB zb = sjzbDao.getZb(zbId, DateUtil.toDate(cal), comp);
		if (null != zb){
			ret = zb.getSjz();
			sjzbDao.delete(zb);
		}
		return ret;
	}
}
