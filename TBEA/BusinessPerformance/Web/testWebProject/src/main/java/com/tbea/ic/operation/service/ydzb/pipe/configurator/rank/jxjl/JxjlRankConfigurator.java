package com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.jxjl;

import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.jhlr.JhlrRankConfigurator;

public class JxjlRankConfigurator  implements IPipeConfigurator{

	IPipeConfigurator jhlrConfig;
	
	public JxjlRankConfigurator(JhlrRankConfigurator jhlrConfig) {
		this.jhlrConfig = jhlrConfig;
	}

	@Override
	public void onConfiguring(IPipe pipe) {
		jhlrConfig.onConfiguring(pipe);
	}

	@Override
	public int getColumnCount() {
		return jhlrConfig.getColumnCount();
	}
}
