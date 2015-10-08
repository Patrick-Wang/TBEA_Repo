package com.tbea.ic.operation.model.dao.market.bidInfo;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.MktBidInfo;
import com.tbea.ic.operation.service.market.pipe.MarketUnit;

public interface MktBidInfoDao{

	void update(MktBidInfo mktBidInfo);

	List<MktBidInfo> getData(String companyName);

	MktBidInfo getById(String bidNo);

	List<MktBidInfo> getUndecidedBidInfo(Date dStart,
			Date dEnd);

	List<MktBidInfo> getData(String companyName, Integer year);

	void remove(String key);

	List<MktBidInfo> getData(Date start, Date end, List<MarketUnit> list);

	List<MarketUnit> getIndustries();
}
