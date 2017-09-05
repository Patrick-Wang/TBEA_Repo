package com.xml.frame.report.interpreter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import com.frame.script.el.ELParser;
import com.xml.frame.report.ReportLogger;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.util.xml.XmlUtil;

public class LogXmlInterpreter implements XmlInterpreter {

	ELParser elp;

	@Override
	public boolean accept(AbstractXmlComponent component, Element e)
			throws Exception {
		if (!Schema.isLog(e)) {
			return false;
		}
		elp = new ELParser(component);
		Logger logger = null;
		if (e.hasAttribute("ref")) {
			logger = LoggerFactory.getLogger(e.getAttribute("ref"));
		} else {
			logger = ReportLogger.logger();
		}
		if (null != logger) {
			if ("info".equals(e.getAttribute("level"))) {
				logger.info(XmlUtil.getString(XmlUtil.getText(e), elp));
			} else if ("error".equals(e.getAttribute("level"))) {
				logger.error(XmlUtil.getString(XmlUtil.getText(e), elp));
			} else if ("trace".equals(e.getAttribute("level"))) {
				logger.trace(XmlUtil.getString(XmlUtil.getText(e), elp));
			} else {
				logger.debug(XmlUtil.getString(XmlUtil.getText(e), elp));
			}
		}
		return true;
	}
}