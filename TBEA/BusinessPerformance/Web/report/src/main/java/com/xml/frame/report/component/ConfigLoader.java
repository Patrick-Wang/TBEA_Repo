package com.xml.frame.report.component;

import org.w3c.dom.Element;

interface ConfigLoadedListener {
	void onEnterFolder(String filePath);
	void onExitFolder(String filePath);
	void onEnterFile(String filePath);
	void onExitFile(String filePath);
	void onDeleteFile(String filePath);
	void onDeleteFolder(String folderPath);
	void onService(String id, Element e, String filePath);
	void onController(String id, Element e, String filePath);
}

interface ConfigLoader {

	public ConfigLoader addListener(ConfigLoadedListener listener);
	
	public void load();
	
	public void pause();
	
	public void reload();
}
