package com.tbea.ic.operation.reportframe.interpreter;

import java.util.List;

import org.w3c.dom.Element;

import com.tbea.ic.operation.common.formatter.v2.core.FormatterHandler;
import com.tbea.ic.operation.common.formatter.v2.core.FormatterServer;
import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil.OnLoop;

public class FormatterServerXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {

		if (!Schema.isFormatterServer(e)) {
			return false;
		}

		FormatterServer serv = new FormatterServer();
		
		XmlUtil.each(e.getChildNodes(), new OnLoop(){
			
			private int group = 0;
			
			@Override
			public void on(Element elem) {
				if ("formatter".equals(elem.getTagName())) {
					List<FormatterHandler> fmts = (List<FormatterHandler>) component.getVar(elem.getAttribute("ref"));
					if (null != fmts) {
						for (FormatterHandler handler : fmts) {
							serv.add(handler, group);
						}
						++group;
					}
				}
			}
		});
		
		if (e.hasAttribute("acceptNullAs")) {
			serv.acceptNullAs(XmlUtil.getAttr(e, "acceptNullAs"));
		}
		
		serv.setTable((List<List<String>>) XmlUtil.getObjectAttr(e, "table", new ELParser(component)));
		
		component.put(e, serv);
		return true;
	}
}