package com.tbea.ic.operation.service.util.pipe.core.configurator;

import com.tbea.ic.operation.service.util.pipe.core.IPipe;


public interface IPipeConfigurator {
	public void onConfiguring(IPipe pipe);
	public int getColumnCount();
}
