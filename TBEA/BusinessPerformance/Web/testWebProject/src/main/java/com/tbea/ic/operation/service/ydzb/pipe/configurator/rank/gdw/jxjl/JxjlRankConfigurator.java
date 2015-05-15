package com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.gdw.jxjl;

import com.tbea.ic.operation.service.ydzb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.gdw.jhlr.JhlrRankConfigurator;

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
