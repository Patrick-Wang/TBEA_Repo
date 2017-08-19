package com.xml.frame.report.interpreter;

import org.w3c.dom.Element;

import com.xml.frame.report.component.AbstractXmlComponent;


public interface XmlInterpreter {
	boolean accept(AbstractXmlComponent component, Element e) throws Exception;
}
