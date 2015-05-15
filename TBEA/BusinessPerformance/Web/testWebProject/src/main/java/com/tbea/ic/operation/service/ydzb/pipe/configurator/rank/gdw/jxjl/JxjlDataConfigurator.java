package com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.gdw.jxjl;

import com.tbea.ic.operation.service.ydzb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.gdw.jhlr.JhlrDataConfigurator;

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
