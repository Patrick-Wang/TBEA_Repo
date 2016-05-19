package com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.ljlr;

import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.jhlr.JhlrRankConfigurator;

public class LjlrRankConfigurator  implements IPipeConfigurator{

	IPipeConfigurator jhlrConfig;
	
	public LjlrRankConfigurator(JhlrRankConfigurator jhlrConfig) {
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
