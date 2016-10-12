package com.tbea.ic.operation.reportframe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportLogger {
	 static Logger logger;
	 public static Logger logger(){
		 if (logger == null){
			 logger = LoggerFactory.getLogger("REPORT-FRAME");
		 }
		 return logger;
	 }
}
