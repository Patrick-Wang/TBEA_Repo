package com.tbea.ic.operation.service.market.pipe.configurator;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.market.bidInfo.MktBidInfoDao;
import com.tbea.ic.operation.model.dao.market.signContract.MktSignContractDao;
import com.tbea.ic.operation.model.entity.MktBidInfo;
import com.tbea.ic.operation.model.entity.MktSignContract;
import com.tbea.ic.operation.service.market.pipe.MarketUnit;
import com.tbea.ic.operation.service.market.pipe.acc.BidAccumulator;
import com.tbea.ic.operation.service.market.pipe.acc.SignAccumulator;
import com.tbea.ic.operation.service.market.pipe.configurator.bid.CompanyBidAnalysisCompositeConfigurator;
import com.tbea.ic.operation.service.market.pipe.configurator.bid.CompanyBidAnalysisConfigurator;
import com.tbea.ic.operation.service.market.pipe.configurator.bid.IndustryBidAnalysisCompositeConfigurator;
import com.tbea.ic.operation.service.market.pipe.configurator.bid.IndustryBidAnalysisConfigurator;
import com.tbea.ic.operation.service.market.pipe.configurator.sign.IndustrySignAnalysisCompositeConfigurator;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;

public class ConfiguratorFactory {

	private MktBidInfoDao bidDao;
	private MktSignContractDao signDao;


	public ConfiguratorFactory(MktBidInfoDao bidDao, MktSignContractDao signDao) {
		super();
		this.bidDao = bidDao;
		this.signDao = signDao;
	}


	
	
	public IPipeConfigurator getIndustrySignAnalysisConfigurator(MarketUnit mu) {
		return new IndustryBidAnalysisConfigurator(new SignAccumulator(new SignAccumulator.DataPicker() {
			
			@Override
			public List<MktSignContract> getData(Date start, Date end, List<MarketUnit> mus) {
				return signDao.getIndustryData(start, end, mu, mus);
			}
		}));
	}
	
	public IPipeConfigurator getIndustrySignAnalysisCompositeConfigurator(Map<Company, List<Company>> computeMap) {
		return new IndustrySignAnalysisCompositeConfigurator(computeMap);
	}
	
	public IPipeConfigurator getCompanySignAnalysisConfigurator(MarketUnit mu) {
		return new IndustryBidAnalysisConfigurator(new SignAccumulator(new SignAccumulator.DataPicker() {
			
			@Override
			public List<MktSignContract> getData(Date start, Date end, List<MarketUnit> mus) {
				return signDao.getCompanyData(start, end, mu, mus);
			}
		}));
	}
	
	public IPipeConfigurator getCompanySignAnalysisCompositeConfigurator(Map<Company, List<Company>> computeMap) {
		return new IndustrySignAnalysisCompositeConfigurator(computeMap);
	}
	
	public IPipeConfigurator getIndustryBidAnalysisConfigurator(MarketUnit mu) {
//		AccCombiner combiner = new AccCombiner();
//		combiner.join(new BidAccumulator(bidDao)).join(new SignAccumulator(signDao));
		return new IndustryBidAnalysisConfigurator(new BidAccumulator(new BidAccumulator.DataPicker() {
			
			@Override
			public List<MktBidInfo> getData(Date start, Date end, List<MarketUnit> mus) {
				return bidDao.getIndustryData(start, end, mu, mus);
			}
		}));
	}
	
	public IPipeConfigurator getIndustryBidAnalysisCompositeConfigurator(Map<Company, List<Company>> computeMap) {
		return new IndustryBidAnalysisCompositeConfigurator(computeMap);
	}
	
	public IPipeConfigurator getCompanyBidAnalysisConfigurator(MarketUnit mu) {
		return new CompanyBidAnalysisConfigurator(new BidAccumulator(new BidAccumulator.DataPicker() {
			
			@Override
			public List<MktBidInfo> getData(Date start, Date end, List<MarketUnit> mus) {
				return bidDao.getCompanyData(start, end, mu, mus);
			}
		}));
	}

	public IPipeConfigurator getCompanyBidAnalysisCompositeConfigurator(Map<Company, List<Company>> computeMap) {
		return new CompanyBidAnalysisCompositeConfigurator(computeMap);
	}
}