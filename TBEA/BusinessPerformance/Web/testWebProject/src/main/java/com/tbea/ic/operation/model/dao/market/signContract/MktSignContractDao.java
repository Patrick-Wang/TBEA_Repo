package com.tbea.ic.operation.model.dao.market.signContract;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.MktSignContract;
import com.tbea.ic.operation.service.market.pipe.MarketUnit;

public interface MktSignContractDao {

	void update(MktSignContract mktObject);

	List<MktSignContract> getData(String companyName);

	MktSignContract getById(String contractNo);

	void remove(String key);

	List<MktSignContract> getData(
			Date start, Date end, List<MarketUnit> list);
}
