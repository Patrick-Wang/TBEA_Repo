package com.tbea.ic.operation.service.nczb.pipe.acc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.model.dao.nc.NCZBDao;
import com.tbea.ic.operation.model.entity.jygk.SJZB;
import com.tbea.ic.operation.model.entity.jygk.YDZBZT;
import com.tbea.ic.operation.model.entity.jygk.YJ20ZB;
import com.tbea.ic.operation.model.entity.jygk.YJ28ZB;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;

public class SjzbAccumulator implements IAccumulator{
	
	NCZBDao nczbDao;

	public SjzbAccumulator(NCZBDao sjzbDao) {
		this.nczbDao = sjzbDao;
	}

	public List<Double> compute(int col, Date start, Date end,
			List<Integer> zbs, List<Company> companies) {
		return nczbDao.getSjzbs(start, end, zbs, companies);
	}



}
