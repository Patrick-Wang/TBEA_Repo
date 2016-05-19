package com.tbea.ic.operation.service.market.pipe.acc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.MktBidInfo;
import com.tbea.ic.operation.service.market.Indicator;
import com.tbea.ic.operation.service.market.pipe.MarketUnit;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;

public class BidAccumulator implements IAccumulator {

	
	public static interface DataPicker{
		List<MktBidInfo> getData(Date start,
			Date end, List<MarketUnit> mus);
	}

	private DataPicker dataPicker;


	public BidAccumulator(DataPicker dataPicker) {
		this.dataPicker = dataPicker;
	}

	private void computeValue(List<Double> vals, MktBidInfo bidInfo, List<Integer> zbs){
		if (zbs.contains(Indicator.TBJE.ordinal())){
			int index = zbs.indexOf(Indicator.TBJE.ordinal());
			Double val = vals.get(index);
			vals.set(index, Util.valueOf(val) + Util.toDouble(bidInfo.getBidPrice()));
		}
		
		if(zbs.contains(Indicator.ZBJE.ordinal())){
			int index = zbs.indexOf(Indicator.ZBJE.ordinal());
			Double val = vals.get(index);
			vals.set(index, Util.valueOf(val) + Util.toDouble(bidInfo.getSucessfulBidderPrice()));
		}
	}

	
	@Override
	public List<Double> compute(int col, Date start, Date end,
			List<Integer> zbs, List<Company> companies) {
		List<MktBidInfo> bidInfos = dataPicker.getData(start, end, (List)companies);
		List<Double> result = new ArrayList<Double>();
		for (int i = 0; i < zbs.size(); ++i){
			result.add(null);
		}

		if(zbs.contains(Indicator.TBSL.ordinal())){
			int index = zbs.indexOf(Indicator.TBSL.ordinal());
			result.set(index, (double) bidInfos.size());
		}
		
		for (int i = 0; i < bidInfos.size(); ++i){ 
			computeValue(result, bidInfos.get(i), zbs);
		}
		return result;
	}
}
