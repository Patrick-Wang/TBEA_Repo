package com.tbea.ic.operation.service.entry.zbInjector;

import java.util.Calendar;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.shzt.SHZTDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.YJ20ZB;
import com.util.tools.DateUtil;

class Yj20ZbInjector extends ZbInjector {

	YJ20ZBDao yj20zbDao;

	public Yj20ZbInjector(ZBXXDao zbxxDao, DWXXDao dwxxDao, SHZTDao shztDao,
			YJ20ZBDao yj20zbDao, ZBInjectListener listener) {
		super(zbxxDao, dwxxDao, shztDao, listener);
		this.yj20zbDao = yj20zbDao;
	}

	@Override
	public void inject(Integer zbId, Double val, Calendar cal, Company comp, ZBStatus status, Calendar time) {
		boolean newEntity = false;
		YJ20ZB zb = yj20zbDao.getZb(zbId,
				DateUtil.toDate(cal), comp);
		if (null == zb) {
			newEntity = true;
			zb = new YJ20ZB();
			zb.setZbxx(zbxxDao.getById(zbId));
			zb.setDwxx(dwxxDao.getById(comp.getId()));
			
		}
		
		if (null != status){
			zb.setYj20shzt(shztDao.getById(status.ordinal()));	
		}
		
		
		if (null != time){
			zb.setYj20xgsj(new java.sql.Timestamp(time.getTimeInMillis()));
		}
		
		zb.setNf(cal.get(Calendar.YEAR));
		zb.setYf(cal.get(Calendar.MONTH) + 1);
		if (val != null){
			zb.setYj20z(val);
		}
		if (newEntity) {
			yj20zbDao.create(zb);
		} else {
			yj20zbDao.merge(zb);
		}
		listener.onInjected(zbId, val, cal, comp, status, time);
	}

	@Override
	public Double remove(Integer zbId, Calendar cal, Company comp) {
		Double ret = null;
		YJ20ZB zb = yj20zbDao.getZb(zbId, DateUtil.toDate(cal), comp);
		if (null != zb){
			ret = zb.getYj20z();
			yj20zbDao.delete(zb);
		}
		return ret;
	}

}
