package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import com.tbea.ic.operation.service.ydzb.gszb.pipe.IPipe;


public interface IPipeConfigurator {
	public void onConfiguring(IPipe pipe);
	public int getColumnCount();
}
