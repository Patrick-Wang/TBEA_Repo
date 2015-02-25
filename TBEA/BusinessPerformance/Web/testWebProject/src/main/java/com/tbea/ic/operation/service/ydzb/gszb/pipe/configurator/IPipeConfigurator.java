package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;

public interface IPipeConfigurator {
	public void onConfiguring(GszbPipe pipe);
	public int columnCount();
}
