package com.tbea.ic.operation.service.nczb.pipe.configurator;

import com.tbea.ic.operation.service.nczb.pipe.acc.AccumulatorFactory;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;


public class ConfiguratorFactory {
	IPipeConfigurator financialPipeConfigurator;

	public ConfiguratorFactory(AccumulatorFactory accFac) {
		financialPipeConfigurator = new FinancialPipeConfigurator(
				accFac.getSjAcc());
	}

	/**
	 * @return the rjlrDataConfigurator
	 */
	public IPipeConfigurator createFinancialPipeConfigurator(IAccumulator sjAcc) {
		return new FinancialPipeConfigurator(sjAcc);
	}
	
	/**
	 * @return the rjlrDataConfigurator
	 */
	public IPipeConfigurator getFinancialPipeConfigurator() {
		return financialPipeConfigurator;
	}
}
