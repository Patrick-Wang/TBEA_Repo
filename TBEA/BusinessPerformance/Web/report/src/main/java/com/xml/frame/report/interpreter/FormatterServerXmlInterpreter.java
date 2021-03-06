package com.xml.frame.report.interpreter;

import java.util.List;

import org.w3c.dom.Element;

import com.frame.script.el.ELParser;
import com.util.tools.xml.Loop;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.util.v2.core.FormatterHandler;
import com.xml.frame.report.util.v2.core.FormatterServer;
import com.xml.frame.report.util.xml.XmlElWalker;
import com.xml.frame.report.util.xml.XmlUtil;

public class FormatterServerXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {

		if (!Schema.isFormatterServer(e)) {
			return false;
		}
		//ReportLogger.trace().debug(component.getConfig().getTagName() + " : " + XmlUtil.toStringFromDoc(e));
		FormatterServer serv = new FormatterServer();
		
		XmlElWalker.eachChildren(e, new ELParser(component), new Loop(){
			
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