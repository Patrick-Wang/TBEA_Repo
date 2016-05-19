package com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.jxjl;

import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.jhlr.JhlrDataConfigurator;

public class JxjlDataConfigurator  implements IPipeConfigurator{

	IPipeConfigurator jhConfig;
	public JxjlDataConfigurator(JhlrDataConfigurator JhlrDataConfigurator) {
		jhConfig = JhlrDataConfigurator;
	}
	
	@Override
	public void onConfiguring(IPipe pipe) {
		jhConfig.onConfiguring(pipe);
	}

	@Override
	public int getColumnCount() {
		return jhConfig.getColumnCount();
	}
}
