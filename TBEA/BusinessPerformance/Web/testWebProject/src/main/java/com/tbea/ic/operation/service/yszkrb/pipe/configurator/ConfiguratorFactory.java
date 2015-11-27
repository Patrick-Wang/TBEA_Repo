package com.tbea.ic.operation.service.yszkrb.pipe.configurator;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.ysdaily.YSDAILYDao;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;

public class ConfiguratorFactory {
	IPipeConfigurator yszkrbConfigurator;

	
	public ConfiguratorFactory(YSDAILYDao ysdaily) {
		yszkrbConfigurator = new YSZKRBConfigurator(ysdaily);
	}


	public IPipeConfigurator getYszkrbConfigurator() {
		return yszkrbConfigurator;
	}



	public IPipeConfigurator getYszkrbCompositeConfigurator(
			Map<Company, List<Company>> computeMap) {
		return new YSZKRBCompositeConfigurator(computeMap);
	}
}
