package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.ydzb.gszb.acc.AccumulatorFactory;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.acc.composite.CompositeAccDataSource;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.rank.LrzbDataConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.rank.LrzbRankConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.seasons.first.FirstSeasonPredictionCompositeConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.seasons.first.FirstSeasonPredictionConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.seasons.second.SecondSeasonPredictionCompositeConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.seasons.second.SecondSeasonPredictionConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.seasons.third.JDZBMYCompositeConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.seasons.third.JDZBMYConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.srqy.SrqyConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.ydhb.YdhbConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.ztzb.ZtzbCompositeConfigurator;

public class ConfiguratorFactory {
	IPipeConfigurator standardConfigurator;
	IPipeConfigurator srqyConfigurator;
	IPipeConfigurator firstSeasonPredictionConfigurator;
	IPipeConfigurator secondSeasonPredictionConfigurator;
	IPipeConfigurator jdzbmyConfigurator;
	IPipeConfigurator ydhbConfigurator;
	LrzbDataConfigurator lrzeDataConfigurator;
	LrzbRankConfigurator lrzeRankConfigurator = new LrzbRankConfigurator();
	/**
	 * @return the lrzbConfigurator
	 */
	public IPipeConfigurator getLrzbDataConfigurator() {
		return lrzeDataConfigurator;
	}
	
	public IPipeConfigurator getLrzbRankConfigurator() {
		return lrzeRankConfigurator;
	}
	
	public ConfiguratorFactory(SbdNdjhZbDao sbdzbDao, AccumulatorFactory accFac, CompanyManager companyManager){
		standardConfigurator = new StandardConfigurator(sbdzbDao, accFac.getSjAcc(), accFac.getYjhAcc(), accFac.getNjhAcc());
		firstSeasonPredictionConfigurator = new FirstSeasonPredictionConfigurator(sbdzbDao, accFac.getSjAcc(), accFac.getYjhAcc(), accFac.getNjhAcc());
		secondSeasonPredictionConfigurator = new SecondSeasonPredictionConfigurator(sbdzbDao, accFac.getSjAcc(), accFac.getYjhAcc(), accFac.getNjhAcc());
		jdzbmyConfigurator = new JDZBMYConfigurator(sbdzbDao, accFac.getSjAcc(), accFac.getYjhAcc(), accFac.getNjhAcc());
		ydhbConfigurator = new YdhbConfigurator(sbdzbDao, accFac.getSjAcc(), accFac.getYjhAcc(), accFac.getNjhAcc());
		srqyConfigurator = new SrqyConfigurator(accFac.getSjAcc(), accFac.getYjhAcc(), accFac.getNjhAcc(), companyManager);
	}
	/**
	 * @return the standardConfigurator
	 */
	public IPipeConfigurator getStandardConfigurator() {
		return standardConfigurator;
	}
	/**
	 * @return the srqyConfigurator
	 */
	public IPipeConfigurator getSrqyConfigurator() {
		return srqyConfigurator;
	}
	/**
	 * @return the firstSeasonPredictionConfigurator
	 */
	public IPipeConfigurator getFirstSeasonPredictionConfigurator() {
		return firstSeasonPredictionConfigurator;
	}
	/**
	 * @return the secondSeasonPredictionConfigurator
	 */
	public IPipeConfigurator getSecondSeasonPredictionConfigurator() {
		return secondSeasonPredictionConfigurator;
	}
	/**
	 * @return the jDZBMYConfigurator
	 */
	public IPipeConfigurator getJDZBMYConfigurator() {
		return jdzbmyConfigurator;
	}
	
	public IPipeConfigurator getZtzbCompositeConfigurator(IAccumulator acc, CompositeAccDataSource cads, Map<CompanyType, List<Company>> computeMap) {
		return new ZtzbCompositeConfigurator(acc, cads, computeMap);
	}
	
	public IPipeConfigurator getJdzbmyCompositeConfigurator(IAccumulator acc, CompositeAccDataSource cads, Map<CompanyType, List<Company>> computeMap) {
		return new JDZBMYCompositeConfigurator(acc, cads, computeMap);
	}
	
	/**
	 * @return the ydhbConfigurator
	 */
	public IPipeConfigurator getYdhbConfigurator() {
		return ydhbConfigurator;
	}
	
	public IPipeConfigurator getSecondSeasonPredictionCompositeConfigurator(IAccumulator acc, CompositeAccDataSource cads, Map<CompanyType, List<Company>> computeMap) {
		return new SecondSeasonPredictionCompositeConfigurator(acc, cads, computeMap);
	}
	
	public IPipeConfigurator getFirstSeasonPredictionCompositeConfigurator(IAccumulator acc, CompositeAccDataSource cads, Map<CompanyType, List<Company>> computeMap) {
		return new FirstSeasonPredictionCompositeConfigurator(acc, cads, computeMap);
	}
	
}
