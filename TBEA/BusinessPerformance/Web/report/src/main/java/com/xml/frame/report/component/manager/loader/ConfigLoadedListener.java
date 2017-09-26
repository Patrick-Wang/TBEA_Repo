package com.xml.frame.report.component.manager.loader;

import org.w3c.dom.Element;

public interface ConfigLoadedListener {
	void onEnterFolder(String filePath);
	void onExitFolder(String filePath);
	void onEnterFile(String filePath);
	void onExitFile(String filePath);
	void onDeleteFile(String filePath);
	void onDeleteFolder(String folderPath);
	void onService(String id, Element e, String filePath);
	void onController(String id, Element e, String filePath);
}
