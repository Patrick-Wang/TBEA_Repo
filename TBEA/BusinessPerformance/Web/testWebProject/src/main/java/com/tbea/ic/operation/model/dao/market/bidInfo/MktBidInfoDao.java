package com.tbea.ic.operation.model.dao.market.bidInfo;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.model.entity.MktBidInfo;

public interface MktBidInfoDao{

	void update(MktBidInfo mktBidInfo);

	List<MktBidInfo> getData(String companyName);

	MktBidInfo getById(String bidNo);

	List<MktBidInfo> getUndecidedBidInfo(Date dStart,
			Date dEnd);

	List<MktBidInfo> getData(String companyName, Integer year);
}
