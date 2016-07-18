package com.tbea.ic.operation.reportframe.component;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.component.entity.Context;
import com.tbea.ic.operation.reportframe.el.ELParser.ObjectLoader;

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
	
	public AbstractXmlComponent(Element e, ComponentManager mgr){
		this.config = e;
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
