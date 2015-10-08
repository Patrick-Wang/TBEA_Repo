package com.tbea.ic.operation.service.market.pipe.acc;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.market.bidInfo.MktBidInfoDao;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;

public class BidAccumulator implements IAccumulator {

	@Autowired
	private MktBidInfoDao bidInfoDao;
	
	@Override
	public List<Double> compute(int col, Date start, Date end,
			List<Integer> zbs, List<Company> companies) {
		return null;
	}

	

}
