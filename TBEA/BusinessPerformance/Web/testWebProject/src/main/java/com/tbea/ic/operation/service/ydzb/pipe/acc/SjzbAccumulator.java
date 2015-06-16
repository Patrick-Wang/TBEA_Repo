package com.tbea.ic.operation.service.ydzb.pipe.acc;

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
import com.tbea.ic.operation.model.entity.jygk.SJZB;
import com.tbea.ic.operation.model.entity.jygk.YDZBZT;
import com.tbea.ic.operation.model.entity.jygk.YJ20ZB;
import com.tbea.ic.operation.model.entity.jygk.YJ28ZB;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;

public class SjzbAccumulator implements IAccumulator{
	
	SJZBDao sjzbDao;

	YJ20ZBDao yj20zbDao;

	YJ28ZBDao yj28zbDao;

	YDZBZTDao ydzbztDao;

	public SjzbAccumulator(SJZBDao sjzbDao, YJ20ZBDao yj20zbDao,
			YJ28ZBDao yj28zbDao, YDZBZTDao ydzbztDao) {
		this.sjzbDao = sjzbDao;
		this.yj20zbDao = yj20zbDao;
		this.yj28zbDao = yj28zbDao;
		this.ydzbztDao = ydzbztDao;
	}

	public List<Double> compute(int col, Date start, Date end,
			List<Integer> zbs, List<Company> companies) {
		List<Double> ret = new ArrayList<Double>();
		Map<Integer, Integer> zbMap = new HashMap<Integer, Integer>();
		for (int i = 0, len = zbs.size(); i < len; ++i) {
			zbMap.put(zbs.get(i), i);
			ret.add(null);
		}

		List<YDZBZT> ydzbzts = ydzbztDao.getYdzbzt(companies, start, end);
		List<YDZBZT> yd20zbzts = new ArrayList<YDZBZT>();
		List<YDZBZT> yd28zbzts = new ArrayList<YDZBZT>();
		List<YDZBZT> sjzbzts = new ArrayList<YDZBZT>();
		for (YDZBZT ydzbzt : ydzbzts) {
			switch (ydzbzt.getZt()) {
			case 1:
				yd20zbzts.add(ydzbzt);
				break;
			case 2:
				yd28zbzts.add(ydzbzt);
				break;
			case 3:
				sjzbzts.add(ydzbzt);
				break;
			}
		}
		Integer row;
		if (!yd20zbzts.isEmpty()) {
			List<YJ20ZB> yj20zbs = yj20zbDao.getYj20zbs(yd20zbzts, zbs);
			for (int i = 0, len = yj20zbs.size(); i < len; ++i) {
				YJ20ZB yj20Zb = yj20zbs.get(i);
				if (null !=  yj20Zb.getYj20z()){
					row = zbMap.get(yj20Zb.getZbxx().getId());
					ret.set(row, Util.valueOf(ret.get(row)) + yj20Zb.getYj20z());
				}				
			}
		}

		if (!yd28zbzts.isEmpty()) {
			List<YJ28ZB> yj28zbs = yj28zbDao.getYj28zbs(yd28zbzts, zbs);
			for (int i = 0, len = yj28zbs.size(); i < len; ++i) {
				YJ28ZB yj28Zb = yj28zbs.get(i);
				if (null !=  yj28Zb.getYj28z()){
					row = zbMap.get(yj28Zb.getZbxx().getId());
					ret.set(row, Util.valueOf(ret.get(row)) + yj28Zb.getYj28z());
				}			
			}
		}

		if (!sjzbzts.isEmpty()) {
			List<SJZB> sjzbs = sjzbDao.getSjzbs(sjzbzts, zbs);
			for (int i = 0, len = sjzbs.size(); i < len; ++i) {
				SJZB sjZb = sjzbs.get(i);
				if (null !=  sjZb.getSjz()){
					row = zbMap.get(sjZb.getZbxx().getId());
					ret.set(row, Util.valueOf(ret.get(row)) + sjZb.getSjz());
				}
			}
		}

		return ret;
	}



}
