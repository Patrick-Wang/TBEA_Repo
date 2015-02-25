package com.tbea.ic.operation.service.ydzb.gszb.acc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.model.entity.jygk.SJZB;
import com.tbea.ic.operation.model.entity.jygk.YDZBZT;
import com.tbea.ic.operation.model.entity.jygk.YJ20ZB;
import com.tbea.ic.operation.model.entity.jygk.YJ28ZB;

public class SjzbAccumulator implements IAccumulator{
	public interface Algorithm {
		Double onCompute(Integer id, Integer compId, Date date, double acc,
				double value);
	}

	SJZBDao sjzbDao;

	YJ20ZBDao yj20zbDao;

	YJ28ZBDao yj28zbDao;

	YDZBZTDao ydzbztDao;

	Map<Integer, Integer> zbMap = new HashMap<Integer, Integer>();

	Algorithm algorithm = new Algorithm() {
		@Override
		public Double onCompute(Integer id, Integer compId, Date date,
				double acc, double value) {
			return acc + value;
		}

	};

	public SjzbAccumulator(SJZBDao sjzbDao, YJ20ZBDao yj20zbDao,
			YJ28ZBDao yj28zbDao, YDZBZTDao ydzbztDao) {
		super();
		this.sjzbDao = sjzbDao;
		this.yj20zbDao = yj20zbDao;
		this.yj28zbDao = yj28zbDao;
		this.ydzbztDao = ydzbztDao;
	}

	public List<Double> compute(int col, Date start, Date end,
			List<Integer> zbs, List<Company> companies) {
		List<Double> ret = new ArrayList<Double>();
		for (int i = 0, len = zbs.size(); i < len; ++i) {
			zbMap.put(zbs.get(i), i);
			ret.add(0.0);
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
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		Integer row;
		if (!yd20zbzts.isEmpty()) {
			List<YJ20ZB> yj20zbs = yj20zbDao.getYj20zbs(yd20zbzts, zbs);

			for (int i = 0, len = yj20zbs.size(); i < len; ++i) {
				YJ20ZB yj20Zb = yj20zbs.get(i);
				row = zbMap.get(yj20Zb.getZbxx().getId());
				ret.set(row, algorithm.onCompute(yj20Zb.getId(), yj20Zb
						.getDwxx().getId(), Date.valueOf(yj20Zb.getNf() + "-"
						+ yj20Zb.getYf() + "-1"), ret.get(row), yj20Zb
						.getYj20z()));
			}
		}

		if (!yd28zbzts.isEmpty()) {
			List<YJ28ZB> yj28zbs = yj28zbDao.getYj28zbs(yd28zbzts, zbs);
			for (int i = 0, len = yj28zbs.size(); i < len; ++i) {
				YJ28ZB yj28Zb = yj28zbs.get(i);
				row = zbMap.get(yj28Zb.getZbxx().getId());
				ret.set(row, algorithm.onCompute(yj28Zb.getId(), yj28Zb
						.getDwxx().getId(), Date.valueOf(yj28Zb.getNf() + "-"
						+ yj28Zb.getYf() + "-1"), ret.get(row), yj28Zb
						.getYj28z()));
			}
		}

		if (!sjzbzts.isEmpty()) {
			List<SJZB> sjzbs = sjzbDao.getSjzbs(sjzbzts, zbs);

			for (int i = 0, len = sjzbs.size(); i < len; ++i) {
				SJZB sjZb = sjzbs.get(i);
				row = zbMap.get(sjZb.getZbxx().getId());
				ret.set(row, algorithm.onCompute(sjZb.getId(), sjZb.getDwxx()
						.getId(), Date.valueOf(sjZb.getNf() + "-"
						+ sjZb.getYf() + "-1"), ret.get(row), sjZb.getSjz()));
			}
		}

		return ret;
	}

	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

}
