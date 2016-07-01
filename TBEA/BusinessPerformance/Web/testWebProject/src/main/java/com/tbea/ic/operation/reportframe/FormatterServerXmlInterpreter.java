package com.tbea.ic.operation.reportframe;

import java.util.List;

import org.w3c.dom.Element;

import com.tbea.ic.operation.common.formatter.v2.core.FormatterHandler;
import com.tbea.ic.operation.common.formatter.v2.core.FormatterServer;
import com.tbea.ic.operation.reportframe.XmlUtil.OnLoop;

public class FormatterServerXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {

		if (!"formatterServer".equals(e.getTagName())) {
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
			serv.acceptNullAs(e.getAttribute("acceptNullAs"));
		}
		
		ELParser el = new ELParser(component);
		el.parser(e.getAttribute("table"));
		List<ELExpression> elexprs = el.parser(e.getAttribute("table"));

		try {
			serv.setTable((List<List<String>>) elexprs.get(0).value());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String id = e.getAttribute("id");
		component.local(id, serv);
		return true;
	}
}