package com.tbea.ic.operation.reportframe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.ComponentLoader.ComponentLoadedListener;

public class ComponentManager implements ComponentLoadedListener {
	ComponentLoader loader;
	Map<String, Element> serviceMap = Collections
			.synchronizedMap(new HashMap<String, Element>());
	Map<String, Element> controllerMap = Collections
			.synchronizedMap(new HashMap<String, Element>());

	public ComponentManager() {
 		loader = new ComponentLoader(this);
		loader.load();
	}

	@Override
	public void onService(String id, Element config) {
		if (null != id) {
			serviceMap.put(id, config);
		}
	}

	@Override
	public void onController(String id, Element config) {
		if (null != id) {
			controllerMap.put(id, config);
		}
	}

	public Controller getController(String id) {
		if (controllerMap.containsKey(id)){
			return new Controller(controllerMap.get(id), this);
		}
		return null;
	}
	
	public Service getService(String id) {
		if (serviceMap.containsKey(id)){
			return new Service(serviceMap.get(id), this);
		}
		return null;
	}
}
