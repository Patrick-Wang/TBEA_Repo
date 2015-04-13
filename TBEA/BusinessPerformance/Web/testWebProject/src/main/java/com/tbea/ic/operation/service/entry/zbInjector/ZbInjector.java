package com.tbea.ic.operation.service.entry.zbInjector;

import java.util.Calendar;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.shzt.SHZTDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;

public abstract class ZbInjector {
	protected ZBXXDao zbxxDao;
	protected DWXXDao dwxxDao;
	protected SHZTDao shztDao;


	public ZbInjector(ZBXXDao zbxxDao, DWXXDao dwxxDao,
			SHZTDao shztDao) {
		this.zbxxDao = zbxxDao;
		this.dwxxDao = dwxxDao;
		this.shztDao = shztDao;
	}
	
	abstract public void inject(Integer zbId, double val, Calendar cal, Company comp);	

}
