package com.tbea.ic.operation.service.ydzb.pipe.acc;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;

public class YjhzbAccumulator implements IAccumulator {

	YDJHZBDao ydjhzbDao;
	
	public YjhzbAccumulator(YDJHZBDao ydjhzbDao) {
		this.ydjhzbDao = ydjhzbDao;
	}

	@Override
	public List<Double> compute(int col, Date start, Date end, List<Integer> zbs,
			List<Company> companies) {
		return ydjhzbDao.getDyjhz(start, end, zbs, companies);
	}

}
