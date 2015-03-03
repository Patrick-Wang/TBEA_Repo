package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.service.ydzb.gszb.acc.AccumulatorFactory;
import com.tbea.ic.operation.service.ydzb.gszb.acc.CompositeAccumulator;

public class ConfiguratorFactory {
	private IPipeConfigurator standardConfigurator;
	private IPipeConfigurator srqyConfigurator;
	private IPipeConfigurator firstSeasonPredictionConfigurator;
	private IPipeConfigurator secondSeasonPredictionConfigurator;
	private IPipeConfigurator jdzbmyConfigurator;
	private IPipeConfigurator ydhbConfigurator;
	
	public ConfiguratorFactory(AccumulatorFactory accFac, CompanyManager companyManager){
		standardConfigurator = new StandardConfigurator(accFac.getSjAcc(), accFac.getYjhAcc(), accFac.getNjhAcc(), companyManager);
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
		if (null == srqyConfigurator){
			srqyConfigurator = new SrqyConfigurator((StandardConfigurator)getStandardConfigurator());
		}
		return srqyConfigurator;
	}
	/**
	 * @return the firstSeasonPredictionConfigurator
	 */
	public IPipeConfigurator getFirstSeasonPredictionConfigurator() {
		if (null == firstSeasonPredictionConfigurator){
			firstSeasonPredictionConfigurator = new FirstSeasonPredictionConfigurator((StandardConfigurator)getStandardConfigurator());
		}
		return firstSeasonPredictionConfigurator;
	}
	/**
	 * @return the secondSeasonPredictionConfigurator
	 */
	public IPipeConfigurator getSecondSeasonPredictionConfigurator() {
		if (null == secondSeasonPredictionConfigurator){
			secondSeasonPredictionConfigurator = new SecondSeasonPredictionConfigurator((StandardConfigurator)getStandardConfigurator());
		}
		return secondSeasonPredictionConfigurator;
	}
	/**
	 * @return the jDZBMYConfigurator
	 */
	public IPipeConfigurator getJDZBMYConfigurator() {
		if (null == jdzbmyConfigurator){
			jdzbmyConfigurator = new JDZBMYConfigurator((StandardConfigurator)getStandardConfigurator());
		}
		return jdzbmyConfigurator;
	}
	
	public IPipeConfigurator getZtzbCompositeConfigurator(CompositeAccumulator acc) {
		return new ZtzbCompositeConfigurator(acc);
	}
	
	public IPipeConfigurator getJdzbmyCompositeConfigurator(CompositeAccumulator acc) {
		return new JDZBMYCompositeConfigurator(acc);
	}
	
	/**
	 * @return the ydhbConfigurator
	 */
	public IPipeConfigurator getYdhbConfigurator() {
		
		if (null == ydhbConfigurator){
			ydhbConfigurator = new YdhbConfigurator((StandardConfigurator)getStandardConfigurator());
		}
		return ydhbConfigurator;
	}
	
	public IPipeConfigurator getSecondSeasonPredictionCompositeConfigurator(CompositeAccumulator acc) {
		return new SecondSeasonPredictionCompositeConfigurator(acc);
	}
	
}
