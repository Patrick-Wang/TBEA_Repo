package com.tbea.ic.operation.service.ydzb.gszb.acc;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;

public class YdzbAccumulator implements IAccumulator {

	YDJHZBDao ydjhzbDao;
	
	public YdzbAccumulator(YDJHZBDao ydjhzbDao) {
		this.ydjhzbDao = ydjhzbDao;
	}

	@Override
	public List<Double> compute(Date start, Date end, List<Integer> zbs,
			List<Company> companies) {
		return ydjhzbDao.getDyjhz(start, end, zbs, companies);
	}

}
