package com.xml.frame.report.component.manager.loader;

public interface ConfigLoader {

	public ConfigLoader addListener(ConfigLoadedListener listener);
	
	public void load();
	
	public void pause();
	
	public void reload();
}
