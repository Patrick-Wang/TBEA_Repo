package com.tbea.ic.operation.reportframe;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.controller.servlet.convertor.Convertor;

public class ComponentLoader {

	public static interface ComponentLoadedListener {
		void onService(String id, Element e);

		void onController(String id, Element e);
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

	ComponentLoadedListener listener;
	Map<String, Long> componentsTime = new HashMap<String, Long>();

	
	public ComponentLoader(ComponentLoadedListener listener) {
		this.listener = listener;
	}


	private void testChange() {
		File dir = new File(resPath);
		File[] fs = dir.listFiles();
		for (int i = 0; i < fs.length; i++) {
			if (fs[i].isFile() && fs[i].getName().endsWith(".xml")) {
				if (!componentsTime.containsKey(fs[i].getName())
						|| componentsTime.get(fs[i].getName()).longValue() != fs[i]
								.lastModified()) {
					componentsTime.put(fs[i].getName(), fs[i].lastModified());
					loadComponent(fs[i]);
				}
			}
		}
	}

	public void load() {
		Timer timer = new Timer(true);
		timer.schedule(new TimerTask() {
			public void run() {
				testChange();
			}
		}, 0, 5 * 60 * 1000);
	}

	private void loadComponent(File file) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
 			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			NodeList nl = doc.getElementsByTagName("components");
			Element e = (Element) nl.item(0);
			nl = e.getChildNodes();
			for (int i = 0; i < nl.getLength(); i++) {
				if (nl.item(i) instanceof Element){
					e = (Element) nl.item(i);
					if (e.getTagName().equals("service")) {
						listener.onService(e.getAttribute("id"), e);
					} else if (e.getTagName().equals("controller")) {
						listener.onController(e.getAttribute("id"), e);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
