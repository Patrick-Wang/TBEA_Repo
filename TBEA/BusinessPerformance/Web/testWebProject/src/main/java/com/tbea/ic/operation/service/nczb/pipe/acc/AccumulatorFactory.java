package com.tbea.ic.operation.service.nczb.pipe.acc;

import com.tbea.ic.operation.model.dao.nc.NCZBDao;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;

public class AccumulatorFactory {
	private IAccumulator sjAcc;
	public AccumulatorFactory(NCZBDao sjzbDao){
		sjAcc = new SjzbAccumulator(sjzbDao);
	}
	
	/**
	 * @return the sjAcc
	 */
	public IAccumulator getSjAcc() {
		return sjAcc;
	}

	
}
