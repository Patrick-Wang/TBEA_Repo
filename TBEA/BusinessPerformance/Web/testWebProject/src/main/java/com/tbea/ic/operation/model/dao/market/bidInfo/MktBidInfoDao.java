package com.tbea.ic.operation.model.dao.market.bidInfo;

import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.MktBidInfo;

public interface MktBidInfoDao{

	void update(MktBidInfo mktBidInfo);

	List<MktBidInfo> getData(String companyName);

	MktBidInfo getById(String bidNo);
}
