package com.tbea.ic.operation.service.util.nc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NCLoggerFactory {
	static Logger logger = LoggerFactory.getLogger("LOG-NC");
	public static Logger logger(){
		return logger;
	}
	
}
