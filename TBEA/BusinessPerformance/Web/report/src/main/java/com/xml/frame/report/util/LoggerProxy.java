package com.xml.frame.report.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
