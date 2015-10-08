package com.tbea.ic.operation.service.market.pipe.configurator;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.market.bidInfo.MktBidInfoDao;
import com.tbea.ic.operation.model.dao.market.signContract.MktSignContractDao;
import com.tbea.ic.operation.service.market.pipe.acc.BidAccumulator;
import com.tbea.ic.operation.service.market.pipe.acc.SignAccumulator;
import com.tbea.ic.operation.service.market.pipe.configurator.bid.IndustryBidAnalysisCompositeConfigurator;
import com.tbea.ic.operation.service.market.pipe.configurator.bid.IndustryBidAnalysisConfigurator;
import com.tbea.ic.operation.service.util.pipe.core.acc.AccCombiner;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;

public class ConfiguratorFactory {

	private MktBidInfoDao bidDao;
	private MktSignContractDao signDao;


	public ConfiguratorFactory(MktBidInfoDao bidDao, MktSignContractDao signDao) {
		super();
		this.bidDao = bidDao;
		this.signDao = signDao;
	}


	public IPipeConfigurator getIndustryBidAnalysisConfigurator() {
//		AccCombiner combiner = new AccCombiner();
//		combiner.join(new BidAccumulator(bidDao)).join(new SignAccumulator(signDao));
		return new IndustryBidAnalysisConfigurator(new BidAccumulator(bidDao));
	}

	public IPipeConfigurator getIndustryBidAnalysisCompositeConfigurator() {
		return new IndustryBidAnalysisCompositeConfigurator();
	}
}