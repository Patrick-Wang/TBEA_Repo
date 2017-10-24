package com.xml.frame.report.util;

import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LoggerProxy {
	Logger logger;
	
	public LoggerProxy(Logger logger) {
		super();
		this.logger = logger;
	}

	public LoggerProxy() {
		super();
	}
	
	public LoggerProxy getLogger(String name){
		 logger = LoggerFactory.getLogger(name);
		 return this;
	}
	
	public LoggerProxy debug(String text){
		if (null != logger){
			logger.debug(text);
		}
		
		return this;
	}
	
	public LoggerProxy trace(String text){
		if (null != logger){
			logger.trace(text);
		}
		return this;
	}
	
	public LoggerProxy info(String text){
		if (null != logger){
			logger.info(text);
		}
		return this;
	}

	public LoggerProxy info(List list){
		if (null != logger){
			logger.info(JSONArray.fromObject(list).toString());
		}
		return this;
	}

	public LoggerProxy info(List list, int max){
		if (null != logger){
			if (list.size() > max){
				logger.info(list.size() + " -> " + JSONArray.fromObject(list.subList(0, max)).toString() + "...");
			}else{
				logger.info(JSONArray.fromObject(list).toString());
			}
		}
		return this;
	}
}
