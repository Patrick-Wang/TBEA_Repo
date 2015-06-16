package com.tbea.ic.operation.service.ydzb.pipe.acc;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;

public class NjhzbAccumulator implements IAccumulator {

	NDJHZBDao ndjhzbDao;
	
	public NjhzbAccumulator(NDJHZBDao ndjhzbDao) {
		this.ndjhzbDao = ndjhzbDao;
	}

	@Override
	public List<Double> compute(int col, Date start, Date end, List<Integer> zbs,
			List<Company> companies) {
		return ndjhzbDao.getQnjhz(end, zbs, companies);
	}

}
