package com.xml.frame.report.interpreter;

import org.w3c.dom.Element;

import com.frame.script.el.ELExpression;
import com.frame.script.el.ELParser;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.util.xml.XmlUtil;


public class ScriptXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		if (Schema.isScript(e)){
			String value = XmlUtil.getText(e);
			String retEl = (String) XmlUtil.parseELText(value, new ELParser(component));
			ELExpression.getJse().eval(retEl);
			return true;
		}
		return false;
	}
}