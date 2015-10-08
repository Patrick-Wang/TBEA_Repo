package com.tbea.ic.operation.service.market.pipe;

import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.market.pipe.MarketUnit;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;

public abstract class AbstractMarketConfigurator implements IPipeConfigurator{

	protected List<MarketUnit> getMarketUnits(IPipe pipe){
		List<? extends Company> comps = pipe.getCompanies();
		return(List<MarketUnit>) comps;
	}


}
