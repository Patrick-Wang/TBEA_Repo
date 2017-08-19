package com.frame.script.maker.xml;

import java.util.ArrayList;
import java.util.List;

import com.frame.script.config.excel.ConfigTable;
import com.frame.script.maker.Maker;
import com.frame.script.maker.MakerException;
import com.frame.script.util.Util;

public abstract class XmlComponentMaker implements Maker {

	protected String template = "";
	protected String wrapperName = "";
	
	List<Integer> ids = new ArrayList<Integer>();
	List<String> vals = new ArrayList<String>();
	List<String> controllers = new ArrayList<String>();
	int currentId = 0;
	
	public XmlComponentMaker(String template, String wrapperName) {
		super();
		this.template = template;
		this.wrapperName = wrapperName;
	}
	
	protected void addAllData(ConfigTable src){
		ids.add(this.currentId++);
		vals.add(src.getTitle());
		controllers.add(Util.tableName2ComponentName(src.getTableName()));
	}
	
	
	protected String getId(String id) {
		if (wrapperName.isEmpty()) {
			return id;
		}
		return wrapperName + id.substring(0, 1).toUpperCase() + id.substring(1);
	}
	
	@Override
	public String make(ConfigTable src) throws MakerException{
		addAllData(src);
		return onMake(src);
	}

	@Override
	public String makeAll() throws MakerException{
		return onMakeAll(ids, vals, controllers);
	}

	protected abstract String onMakeAll(List<Integer> ids, List<String> vals, List<String> controllers) throws MakerException;
	
	protected abstract String onMake(ConfigTable src) throws MakerException;

}
