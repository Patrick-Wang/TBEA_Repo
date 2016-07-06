package com.tbea.ic.operation.reportframe.interpreter;

import org.w3c.dom.Element;


public interface XmlInterpreter {
	boolean accept(AbstractXmlComponent component, Element e);
}
