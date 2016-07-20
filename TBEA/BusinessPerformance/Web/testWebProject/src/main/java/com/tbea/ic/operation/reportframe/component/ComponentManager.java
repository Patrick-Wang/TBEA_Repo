package com.tbea.ic.operation.reportframe.component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.component.ComponentLoader.ComponentLoadedListener;
import com.tbea.ic.operation.reportframe.component.controller.Controller;
import com.tbea.ic.operation.reportframe.component.entity.Context;
import com.tbea.ic.operation.reportframe.component.service.Service;



public class ComponentManager implements ComponentLoadedListener {
	static public class Config{
		Element e;
		String path;
		public Element getE() {
			return e;
		}
		public void setE(Element e) {
			this.e = e;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public Config(Element e, String path) {
			super();
			this.e = e;
			this.path = path;
		}
	}
	ComponentLoader loader;
	
	Map<String, Config> serviceMap = Collections
			.synchronizedMap(new HashMap<String, Config>());
	Map<String, Config> controllerMap = Collections
			.synchronizedMap(new HashMap<String, Config>());

	public ComponentManager() {
 		loader = new ComponentLoader(this);
		loader.load();
	}

	public Config getControllerConfig(String id){
		return controllerMap.get(id);
	}
	
	public Set<String> getController(){
		return controllerMap.keySet();
	}
	
	@Override
	public void onService(String id, Element e, String path) {
		if (null != id) {
			serviceMap.put(id, new Config(e, path));
		}
	}

	@Override
	public void onController(String id, Element e, String path) {
		if (null != id) {
			controllerMap.put(id,  new Config(e, path));
		}
	}

	public Controller getController(String id) {
		if (controllerMap.containsKey(id)){
			return new Controller(controllerMap.get(id).getE(), this);
		}
		return null;
	}
	
	public Controller getController(String id, Context local) {
		if (controllerMap.containsKey(id)){
			return new Controller(controllerMap.get(id).getE(), this, local);
		}
		return null;
	}
	
	private Service createService(Element e){
		return new Service(e, this);
	}
	
	public Service getService(String id, Context local){
		if (serviceMap.containsKey(id)){
			return new Service(serviceMap.get(id).getE(), this, local);
		}
		return null;
	}
	
	public Service getService(String id) {
		if (serviceMap.containsKey(id)){
			return createService(serviceMap.get(id).getE());
		}
		return null;
	}
}
