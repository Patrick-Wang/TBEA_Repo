package com.tbea.ic.operation.service.ydzb.pipe.configurator;

import com.tbea.ic.operation.service.ydzb.pipe.IPipe;


public interface IPipeConfigurator {
	public void onConfiguring(IPipe pipe);
	public int getColumnCount();
}
