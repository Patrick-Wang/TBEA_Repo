package com.tbea.ic.operation.reportframe.interpreter;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.XmlUtil;


public class IFXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {

		if (Schema.isIf(e)){
			//ReportLogger.trace().debug(component.getConfig().getTagName() + " : " + XmlUtil.toStringFromDoc(e));
			String boolVal = e.getAttribute("test");
			if (boolVal.isEmpty() && XmlUtil.hasText(e)){
				boolVal = XmlUtil.getText(e);
			}
			if (XmlUtil.getBoolean(boolVal, new ELParser(component))){
				NamedNodeMap nnm = component.getConfig().getAttributes();
				for (int i = 0; i < nnm.getLength(); ++i){
					String name = nnm.item(i).getNodeName();
					if (!e.hasAttribute(name)){
						String value = nnm.item(i).getNodeValue();
						e.setAttribute(name, value);
					}
				}
				component.clone(e).run(component.globalContext());
			}
			return true;
		}
		return false;
	}
}