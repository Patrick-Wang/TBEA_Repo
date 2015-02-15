package com.tbea.ic.operation.service.ydzb.gszb.acc;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;

public class NjhzbAccumulator implements IAccumulator {

	NDJHZBDao ndjhzbDao;
	
	public NjhzbAccumulator(NDJHZBDao ndjhzbDao) {
		this.ndjhzbDao = ndjhzbDao;
	}

	@Override
	public List<Double> compute(Date start, Date end, List<Integer> zbs,
			List<Company> companies) {
		return ndjhzbDao.getQnjhz(end, zbs, companies);
	}

}
