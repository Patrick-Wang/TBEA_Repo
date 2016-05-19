package com.tbea.ic.operation.model.dao.market.bidInfo;

import java.sql.Date;
import java.util.List;

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

	List<MktBidInfo> getIndustryData(Date start, Date end, List<MarketUnit> mu,
			List<MarketUnit> companies);

	List<MarketUnit> getIndustries(List<MarketUnit> muSb);

	List<MktBidInfo> getCompanyData(Date start, Date end, List<MarketUnit> muSb,
			List companies);

	List<MarketUnit> getCompanies(List<MarketUnit> muSb);

	List<MarketUnit> getAreas(List<MarketUnit> muSb);

	List<MktBidInfo> getAreaData(Date start, Date end, List<MarketUnit> muSb,
			List<MarketUnit> mus);
}
