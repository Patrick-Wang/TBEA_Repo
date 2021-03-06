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
import com.tbea.ic.operation.service.market.pipe.configurator.mixed.AreaAnalysisCompositeConfigurator;
import com.tbea.ic.operation.service.market.pipe.configurator.mixed.AreaAnalysisConfigurator;
import com.tbea.ic.operation.service.market.pipe.configurator.mixed.IndustryMixedAnalysisCompositeConfigurator;
import com.tbea.ic.operation.service.market.pipe.configurator.mixed.IndustryMixedAnalysisConfigurator;
import com.tbea.ic.operation.service.market.pipe.configurator.sign.IndustrySignAnalysisCompositeConfigurator;
import com.tbea.ic.operation.service.market.pipe.configurator.sign.IndustrySignAnalysisConfigurator;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.util.pipe.filter.acc.AccCombiner;

public class ConfiguratorFactory {

	private MktBidInfoDao bidDao;
	private MktSignContractDao signDao;


	public ConfiguratorFactory(MktBidInfoDao bidDao, MktSignContractDao signDao) {
		super();
		this.bidDao = bidDao;
		this.signDao = signDao;
	}


	
	
	public IPipeConfigurator getIndustrySignAnalysisConfigurator(List<MarketUnit> mu) {
		return new IndustrySignAnalysisConfigurator(new SignAccumulator(new SignAccumulator.DataPicker() {
			
			@Override
			public List<MktSignContract> getData(Date start, Date end, List<MarketUnit> mus) {
				return signDao.getIndustryData(start, end, mu, mus);
			}
		}));
	}
	
	public IPipeConfigurator getIndustrySignAnalysisCompositeConfigurator(Map<Company, List<Company>> computeMap) {
		return new IndustrySignAnalysisCompositeConfigurator(computeMap);
	}
	
	public IPipeConfigurator getCompanySignAnalysisConfigurator(List<MarketUnit> muSb) {
		return new IndustrySignAnalysisConfigurator(new SignAccumulator(new SignAccumulator.DataPicker() {
			
			@Override
			public List<MktSignContract> getData(Date start, Date end, List<MarketUnit> mus) {
				return signDao.getCompanyData(start, end, muSb, mus);
			}
		}));
	}
	
	public IPipeConfigurator getCompanySignAnalysisCompositeConfigurator(Map<Company, List<Company>> computeMap) {
		return new IndustrySignAnalysisCompositeConfigurator(computeMap);
	}
	
	public IPipeConfigurator getIndustryBidAnalysisConfigurator(List<MarketUnit> mu) {
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
	
	public IPipeConfigurator getCompanyBidAnalysisConfigurator(List<MarketUnit> muSb) {
		return new CompanyBidAnalysisConfigurator(new BidAccumulator(new BidAccumulator.DataPicker() {
			
			@Override
			public List<MktBidInfo> getData(Date start, Date end, List<MarketUnit> mus) {
				return bidDao.getCompanyData(start, end, muSb, mus);
			}
		}));
	}

	public IPipeConfigurator getCompanyBidAnalysisCompositeConfigurator(Map<Company, List<Company>> computeMap) {
		return new CompanyBidAnalysisCompositeConfigurator(computeMap);
	}
	
	public IPipeConfigurator getAreaAnalysisConfigurator(List<MarketUnit> muSb, Date startDate) {
		AccCombiner combiner = new AccCombiner();
		combiner.join(new BidAccumulator(new BidAccumulator.DataPicker() {
			
			@Override
			public List<MktBidInfo> getData(Date start, Date end, List<MarketUnit> mus) {
				return bidDao.getAreaData(start, end, muSb, mus);
			}
		})).join(new SignAccumulator(new SignAccumulator.DataPicker() {
			
			@Override
			public List<MktSignContract> getData(Date start, Date end, List<MarketUnit> mus) {
				return signDao.getAreaData(start, end, muSb, mus);
			}
		}));
		return new AreaAnalysisConfigurator(combiner, startDate);
	}
	
	public IPipeConfigurator getAreaAnalysisCompositeConfigurator(Map<Company, List<Company>> computeMap) {
		return new AreaAnalysisCompositeConfigurator(computeMap);
	}
	
	public IPipeConfigurator getIndustryMixedAnalysisConfigurator(List<MarketUnit> mu, Date startDate) {
		AccCombiner combiner = new AccCombiner();
		combiner.join(new BidAccumulator(new BidAccumulator.DataPicker() {
			
			@Override
			public List<MktBidInfo> getData(Date start, Date end, List<MarketUnit> mus) {
				return bidDao.getIndustryData(start, end, mu, mus);
			}
		})).join(new SignAccumulator(new SignAccumulator.DataPicker() {
			
			@Override
			public List<MktSignContract> getData(Date start, Date end, List<MarketUnit> mus) {
				return signDao.getIndustryData(start, end, mu, mus);
			}
		}));
		return new IndustryMixedAnalysisConfigurator(combiner, startDate);
	}
	
	public IPipeConfigurator getIndustryMixedAnalysisCompositeConfigurator(Map<Company, List<Company>> computeMap) {
		return new IndustryMixedAnalysisCompositeConfigurator(computeMap);
	}
}