package com.xml.frame.report.component;

import org.w3c.dom.Element;

import com.frame.script.el.ELParser;
import com.frame.script.el.ELParser.ObjectLoader;
import com.xml.frame.report.component.entity.Context;
import com.xml.frame.report.component.manager.ComponentManager;

public abstract class AbstractXmlComponent implements Component, ObjectLoader {
	
	protected Context local = new Context();
	protected Context global;
	protected Element config;
	protected AbstractXmlComponent preComponent;
	protected ComponentManager mgr;
	protected ELParser elp = new ELParser(this);
	
	
	public ComponentManager getCM(){
		return mgr;
	}
	
	@Override
	public Object onGetObject(String key){
		return getVar(key);
	}
	
	@Override
	public boolean hasObject(String key){
		return local.contains(key) || global.contains(key);
	}
	
	@Override
	public String getId() {
		return config.getAttribute("id");
	}
	
	public Element getConfig(){
		return config;
	}
	

	public String getConfigAttribute(String attr){
		if (config.hasAttribute(attr)){
			return config.getAttribute(attr);
		}else{
			if (preComponent != null){
				return preComponent.getConfigAttribute(attr);
			}
			return null;
		}
	}
	
	public void local(String key, Object value){
		if (!key.isEmpty()){
			local.put(key, value);
		}
		if (value == null){
			System.out.println("local object " + key + " is null");
		}
	}
	
	public void put(Element eVar, Object val){
		if (eVar.hasAttribute("id")){
			if ("true".equals(eVar.getAttribute("export"))){
				global(eVar.getAttribute("id"), val);
			}else{
				local(eVar.getAttribute("id"), val);
			}
		}
	}
	
	public void global(String key, Object value){

		if (!key.isEmpty()){
			global.put(key, value);
		}
		if (value == null){
			System.out.println("global object " + key + " is null");
		}
	}
	
	public AbstractXmlComponent(AbstractXmlComponent preComponent, Element e, ComponentManager mgr){
		this.config = e;
		this.preComponent = preComponent;
		this.mgr = mgr;
	}
	
	abstract public AbstractXmlComponent clone(Element e);
	
	public Object removeLocal(String key){
		return local.remove(key);
	}
	
	public Object getVar(String key){
		if (local.contains(key)){
			return local.get(key);
		}else{
			return global.get(key);
		}
	}
	
	@Override
	public void run(Context context) throws Exception {
		global = context;
		local.put("_this", this);
		onRun();
	}

	protected abstract void onRun() throws Exception;

	public Context globalContext() {
		return global;
	}

	public Context localContext() {
		return local;
	}
}
