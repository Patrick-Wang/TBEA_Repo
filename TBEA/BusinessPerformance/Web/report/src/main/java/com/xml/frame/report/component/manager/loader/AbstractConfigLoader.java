package com.xml.frame.report.component.manager.loader;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.util.tools.DateUtil;
import com.util.tools.xml.Loop;
import com.util.tools.xml.XmlWalker;

public abstract class AbstractConfigLoader implements ConfigLoader{

	Map<String, Long> componentsModifyTime = new HashMap<String, Long>();

	List<ConfigLoadedListener> listeners = new ArrayList<ConfigLoadedListener>();
	DocumentBuilder builder = null;
	String resPath;
	public AbstractConfigLoader(String resPath) {
		super();
		this.resPath = resPath;
	}


	private DocumentBuilder getBuilder() throws ParserConfigurationException{
		if (builder == null){
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			builder = factory.newDocumentBuilder();
		}
		return builder;
	}
	
	protected void scan(File dir) {
		notifyOnEnterFolder(dir.getAbsolutePath());
		File[] fs = dir.listFiles();
		for (int i = 0; i < fs.length; i++) {
			if (fs[i].isFile()) {
				loadComponent(fs[i]);
			}else if(fs[i].isDirectory()){
				scan(fs[i]);
			}
		}
		notifyOnExitFolder(dir.getAbsolutePath());
	}
	
	private boolean validateComponentFile(File file){
		if (!file.getName().endsWith(".xml")){
			return false;
		}
		
		if (!componentsModifyTime.containsKey(file.getAbsolutePath())	|| 
			componentsModifyTime.get(file.getAbsolutePath()).longValue() != file.lastModified()){
			componentsModifyTime.put(file.getAbsolutePath(), file.lastModified());
			return true;
		}
		
		return false;
	}
	
	private String pathFromComponent(String path){
		int index = path.indexOf("components");
		return path.substring(index + "components".length());
	}
	
	protected void loadComponent(File file) {
		if (validateComponentFile(file)){
			try {
				
				notifyOnEnterFile(file.getAbsolutePath());
				System.out.println(DateUtil.second(new Timestamp(Calendar.getInstance().getTimeInMillis())) + " : load config file " + pathFromComponent(file.getAbsolutePath()));
				String path = file.getAbsolutePath();
				DocumentBuilder builder = getBuilder();
				Document doc = builder.parse(file);
				NodeList nl = doc.getElementsByTagName("components");

				XmlWalker.eachChildren(nl.item(0), new Loop(){

					@Override
					public void on(Element e) throws Exception {
						if (e.getTagName().equals("service")) {
							notifyOnService(e.getAttribute("id"), e, path);
						} else if (e.getTagName().equals("controller")) {
							notifyOnController(e.getAttribute("id"), e, path);
						}
					}
					
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
			notifyOnExitFile(file.getAbsolutePath());
		}
	}
	
	public void reload(){
		componentsModifyTime.clear();
		scan(new File(resPath));
	}
		
	public ConfigLoader addListener(ConfigLoadedListener listener){
		listeners.add(listener);
		return this;
	}
	
	protected void notifyOnService(String id, Element e, String filePath){
		for (ConfigLoadedListener lsn : listeners){
			lsn.onService(id, e, filePath);
		}
	}
	
	protected void notifyOnController(String id, Element e, String filePath){
		for (ConfigLoadedListener lsn : listeners){
			lsn.onController(id, e, filePath);
		}
	}
	
	protected void notifyOnEnterFolder(String filePath){
		for (ConfigLoadedListener lsn : listeners){
			lsn.onEnterFolder(filePath);
		}
	}
	
	protected void notifyOnEnterFile(String filePath){
		for (ConfigLoadedListener lsn : listeners){
			lsn.onEnterFile(filePath);
		}
	}
	
	protected void notifyOnExitFolder(String filePath){
		for (ConfigLoadedListener lsn : listeners){
			lsn.onExitFolder(filePath);
		}
	}
	
	protected void notifyOnExitFile(String filePath){
		for (ConfigLoadedListener lsn : listeners){
			lsn.onExitFile(filePath);
		}
	}
	

	protected void notifyOnDeleteFile(String filePath){
		componentsModifyTime.remove(filePath);
		for (ConfigLoadedListener lsn : listeners){
			lsn.onDeleteFile(filePath);
		}
	}
	
	protected void notifyOnDeleteFolder(String folderPath){
		for (ConfigLoadedListener lsn : listeners){
			lsn.onDeleteFolder(folderPath);
		}
	}
}
