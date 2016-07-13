package com.tbea.ic.operation.reportframe.interpreter;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;


public interface XmlInterpreter {
	boolean accept(AbstractXmlComponent component, Element e) throws Exception;
}
