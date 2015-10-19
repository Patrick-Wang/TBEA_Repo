package com.tbea.ic.operation.model.dao.market.signContract;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.model.entity.MktSignContract;
import com.tbea.ic.operation.service.market.pipe.MarketUnit;

public interface MktSignContractDao {

	void update(MktSignContract mktObject);

	List<MktSignContract> getData(String companyName);

	MktSignContract getById(String contractNo);

	void remove(String key);

	List<MktSignContract> getData(
			Date start, Date end, List<MarketUnit> list);

	List<MktSignContract> getIndustryData(Date start, Date end, List<MarketUnit> mu,
			List<MarketUnit> mus);

	List<MktSignContract> getCompanyData(Date start, Date end, List<MarketUnit> muSb,
			List<MarketUnit> mus);

	List<MarketUnit> getIndustries(List<MarketUnit> muSb);

	List<MarketUnit> getCompanies(List<MarketUnit> muSb);

	List<MarketUnit> getAreas(List<MarketUnit> muSb);

	List<MktSignContract> getAreaData(Date start, Date end, List<MarketUnit> muSb,
			List<MarketUnit> mus);
}
