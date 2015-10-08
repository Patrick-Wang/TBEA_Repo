package com.tbea.ic.operation.service.market.pipe.acc;

import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.market.pipe.MarketUnit;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;

public abstract class AbstractAccumulator implements IAccumulator{

	protected List<MarketUnit> getMarketUnits(List<Company> companies){
		List<? extends Company> comps = companies;
		return(List<MarketUnit>) comps;
	}


}
