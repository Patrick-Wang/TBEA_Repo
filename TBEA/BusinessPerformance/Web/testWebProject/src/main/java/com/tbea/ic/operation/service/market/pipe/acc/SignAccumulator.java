package com.tbea.ic.operation.service.market.pipe.acc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.market.bidInfo.MktBidInfoDao;
import com.tbea.ic.operation.model.dao.market.signContract.MktSignContractDao;
import com.tbea.ic.operation.model.entity.MktBidInfo;
import com.tbea.ic.operation.model.entity.MktSignContract;
import com.tbea.ic.operation.service.market.Indicator;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;

public class SignAccumulator extends AbstractAccumulator {

	@Autowired
	private MktSignContractDao signDao;
	
	public SignAccumulator(MktSignContractDao signDao) {
		super();
		this.signDao = signDao;
	}

	private void computeValue(List<Double> vals, MktSignContract info, List<Integer> zbs){
		if (zbs.contains(Indicator.QYJE.ordinal())){
			int index = zbs.indexOf(Indicator.TBJE.ordinal());
			Double val = vals.get(index);
			vals.set(index, val + info.getProductPrice());
		}
	}

	@Override
	public List<Double> compute(int col, Date start, Date end,
			List<Integer> zbs, List<Company> companies) {
		List<MktSignContract> infos = signDao.getData(start, end, this.getMarketUnits(companies));
		List<Double> result = new ArrayList<Double>();
		for (int i = 0; i < zbs.size(); ++i){
			result.add(null);
		}

		if(zbs.contains(Indicator.HTSL.ordinal())){
			int index = zbs.indexOf(Indicator.HTSL.ordinal());
			result.set(index, (double) infos.size());
		}
		
		for (int i = 0; i > infos.size(); ++i){
			computeValue(result, infos.get(i), zbs);
		}
		return result;
	}

}
