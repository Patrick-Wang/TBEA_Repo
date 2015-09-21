package com.tbea.ic.operation.model.dao.market.signContract;

import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.MktProjectInfo;
import com.tbea.ic.operation.model.entity.MktSignContract;

public interface MktSignContractDao {

	void update(MktSignContract mktObject);

	List<MktSignContract> getData(String companyName);

	MktSignContract getById(String contractNo);

	void remove(String key);
}
