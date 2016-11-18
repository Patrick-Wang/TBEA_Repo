package com.tbea.ic.operation.reportframe.interpreter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.ReportLogger;
import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.XmlUtil;


public class LogXmlInterpreter implements XmlInterpreter {	
	
	ELParser elp;
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		if (!Schema.isLog(e)){
			return false;
		}
		elp = new ELParser(component);
		Logger logger = null;
		if (e.hasAttribute("ref")){
			logger = LoggerFactory.getLogger(e.getAttribute("ref"));
		}else{
			logger = ReportLogger.logger();
		}
		if ("info".equals(e.hasAttribute("level"))){
			logger.info(XmlUtil.getString(XmlUtil.getText(e), elp));
		}else{
			logger.debug(XmlUtil.getString(XmlUtil.getText(e), elp));
		}
		return true;
	}
}