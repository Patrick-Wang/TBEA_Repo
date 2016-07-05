package com.tbea.ic.operation.reportframe;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.ELParser.ObjectLoader;

public abstract class AbstractXmlComponent implements Component, ObjectLoader {
	
	protected Context local = new Context();
	protected Context global;
	protected Element config;
	protected ComponentManager mgr;
	
	public ComponentManager getCM(){
		return mgr;
	}
	
	@Override
	public Object onGetObject(String key){
		return getVar(key);
	}
	
	@Override
	public String getId() {
		return config.getAttribute("id");
	}
	
	public Element getConfig(){
		return config;
	}
	
	public void local(String key, Object value){
		local.put(key, value);
	}
	
	public void global(String key, Object value){
		global.put(key, value);
	}
	
	public AbstractXmlComponent(Element e, ComponentManager mgr){
		this.config = e;
		this.mgr = mgr;
	}
	
	public Object getVar(String key){
		if (local.contains(key)){
			return local.get(key);
		}else{
			return global.get(key);
		}
	}
	
	@Override
	public void run(Context context) {
		global = context;
		onRun();
	}

	protected abstract void onRun();

	public Context globalContext() {
		return global;
	}

}
