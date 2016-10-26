package com.tbea.ic.operation.reportframe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportLogger {
	 static Logger logger;
	 static Logger trace;
	 public static Logger logger(){
		 if (logger == null){
			 logger = LoggerFactory.getLogger("REPORT-FRAME");
		 }
		 return logger;
	 }
	 
	 public static Logger trace(){
		 if (trace == null){
			 trace = LoggerFactory.getLogger("REPORT-TRACE");
		 }
		 return trace;
	 }
}
