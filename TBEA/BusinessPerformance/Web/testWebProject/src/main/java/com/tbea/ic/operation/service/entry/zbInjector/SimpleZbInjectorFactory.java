package com.tbea.ic.operation.service.entry.zbInjector;

import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.shzt.SHZTDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;

public class SimpleZbInjectorFactory {
	public static ZbInjector createInjector(ZBXXDao zbxxDao, DWXXDao dwxxDao, SHZTDao shztDao,
			NDJHZBDao zbDao){
		return new NdjhZbInjector(zbxxDao, dwxxDao, shztDao, zbDao);
	}
	
	public static ZbInjector createInjector(ZBXXDao zbxxDao, DWXXDao dwxxDao, SHZTDao shztDao,
			YDJHZBDao zbDao){
		return new YdjhZbInjector(zbxxDao, dwxxDao, shztDao, zbDao);
	}
	
	public static ZbInjector createInjector(ZBXXDao zbxxDao, DWXXDao dwxxDao, SHZTDao shztDao,
			YJ28ZBDao zbDao){
		return new Yj28ZbInjector(zbxxDao, dwxxDao, shztDao, zbDao);
	}
	
	public static ZbInjector createInjector(ZBXXDao zbxxDao, DWXXDao dwxxDao, SHZTDao shztDao,
			YJ20ZBDao zbDao){
		return new Yj20ZbInjector(zbxxDao, dwxxDao, shztDao, zbDao);
	}
	
	public static ZbInjector createInjector(ZBXXDao zbxxDao, DWXXDao dwxxDao, SHZTDao shztDao,
			SJZBDao zbDao){
		return new SjZbInjector(zbxxDao, dwxxDao, shztDao, zbDao);
	}
}
