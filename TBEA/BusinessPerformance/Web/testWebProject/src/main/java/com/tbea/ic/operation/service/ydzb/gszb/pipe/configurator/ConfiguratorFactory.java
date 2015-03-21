package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.ydzb.gszb.acc.AccumulatorFactory;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;

public class ConfiguratorFactory {
	private IPipeConfigurator standardConfigurator;
	private IPipeConfigurator srqyConfigurator;
	private IPipeConfigurator firstSeasonPredictionConfigurator;
	private IPipeConfigurator secondSeasonPredictionConfigurator;
	private IPipeConfigurator jdzbmyConfigurator;
	private IPipeConfigurator ydhbConfigurator;
	
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
	
	public IPipeConfigurator getZtzbCompositeConfigurator(IAccumulator acc) {
		return new ZtzbCompositeConfigurator(acc);
	}
	
	public IPipeConfigurator getJdzbmyCompositeConfigurator(IAccumulator acc) {
		return new JDZBMYCompositeConfigurator(acc);
	}
	
	/**
	 * @return the ydhbConfigurator
	 */
	public IPipeConfigurator getYdhbConfigurator() {
		return ydhbConfigurator;
	}
	
	public IPipeConfigurator getSecondSeasonPredictionCompositeConfigurator(IAccumulator acc) {
		return new SecondSeasonPredictionCompositeConfigurator(acc);
	}
	
	public IPipeConfigurator getFirstSeasonPredictionCompositeConfigurator(IAccumulator acc) {
		return new FirstSeasonPredictionCompositeConfigurator(acc);
	}
	
}
