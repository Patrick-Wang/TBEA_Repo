package com.tbea.ic.operation.reportframe.component;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.controller.servlet.convertor.Convertor;

public class ComponentLoader {

	public static interface ComponentLoadedListener {
		void onEnterFolder(String filePath);
		void onExitFolder(String filePath);
		void onEnterFile(String filePath);
		void onExitFile(String filePath);
		void onService(String id, Element e, String filePath);
		void onController(String id, Element e, String filePath);
	}

	private static String resPath;
	static {
		try {
			resPath = new URI(Convertor.class.getClassLoader().getResource("")
					.getPath()).getPath().substring(1)
					+ "META-INF/components/";
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	Timer timer = new Timer(true);
	List<ComponentLoadedListener> listeners = new ArrayList<ComponentLoadedListener>();
	DocumentBuilder builder = null;
	Map<String, Long> componentsTime = new HashMap<String, Long>();

	public ComponentLoader addListener(ComponentLoadedListener listener){
		listeners.add(listener);
		return this;
	}
	
	public void notifyOnService(String id, Element e, String filePath){
		for (ComponentLoadedListener lsn : listeners){
			lsn.onService(id, e, filePath);
		}
	}
	
	public void notifyOnController(String id, Element e, String filePath){
		for (ComponentLoadedListener lsn : listeners){
			lsn.onController(id, e, filePath);
		}
	}
	
	public void notifyOnEnterFolder(String filePath){
		for (ComponentLoadedListener lsn : listeners){
			lsn.onEnterFolder(filePath);
		}
	}
	
	public void notifyOnEnterFile(String filePath){
		for (ComponentLoadedListener lsn : listeners){
			lsn.onEnterFile(filePath);
		}
	}
	
	public void notifyOnExitFolder(String filePath){
		for (ComponentLoadedListener lsn : listeners){
			lsn.onExitFolder(filePath);
		}
	}
	
	public void notifyOnExitFile(String filePath){
		for (ComponentLoadedListener lsn : listeners){
			lsn.onExitFile(filePath);
		}
	}
	

	private void scan(File dir) {
		notifyOnEnterFolder(dir.getAbsolutePath());
		File[] fs = dir.listFiles();
		for (int i = 0; i < fs.length; i++) {
			if (fs[i].isFile() && fs[i].getName().endsWith(".xml")) {
				if (!componentsTime.containsKey(fs[i].getAbsolutePath())
						|| componentsTime.get(fs[i].getAbsolutePath()).longValue() != fs[i]
								.lastModified()) {
					componentsTime.put(fs[i].getAbsolutePath(), fs[i].lastModified());
					loadComponent(fs[i]);
				}
			}else if(fs[i].isDirectory()){
				scan(fs[i]);
			}
		}
		notifyOnExitFolder(dir.getAbsolutePath());
	}

	public void load() {
		timer.schedule(new TimerTask() {
			public void run() {
				synchronized(timer){
					scan(new File(resPath));
				}
			}
		}, 0, 30 * 1000);
	}
	
	public void pause(){
		timer.cancel();
		synchronized(timer){
			
		}
	}
	
	public void reload(){
		componentsTime.clear();
		scan(new File(resPath));
	}
	
	
	
	private DocumentBuilder getBuilder() throws ParserConfigurationException{
		if (builder == null){
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			builder = factory.newDocumentBuilder();
		}
		return builder;
	}
	
	
	private void loadComponent(File file) {
		try {
			notifyOnEnterFile(file.getAbsolutePath());
			System.out.println(Util.formatToSecond(new Timestamp(Calendar.getInstance().getTimeInMillis())) + " : load config file " + file.getName());
			String path = file.getAbsolutePath();
			DocumentBuilder builder = getBuilder();
			Document doc = builder.parse(file);
			NodeList nl = doc.getElementsByTagName("components");
			Element e = (Element) nl.item(0);
			nl = e.getChildNodes();
			for (int i = 0; i < nl.getLength(); i++) {
				if (nl.item(i) instanceof Element){
					e = (Element) nl.item(i);
					if (e.getTagName().equals("service")) {
						notifyOnService(e.getAttribute("id"), e, path);
					} else if (e.getTagName().equals("controller")) {
						notifyOnController(e.getAttribute("id"), e, path);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		notifyOnExitFile(file.getAbsolutePath());
	}
}
