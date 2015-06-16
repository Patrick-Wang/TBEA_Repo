package com.tbea.ic.operation.service.nczb;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.service.nczb.pipe.configurator.ConfiguratorFactory;

public interface NCZBService {

	List<String[]> getGSZB(Date d);

	ConfiguratorFactory getConfigFactory();

}
