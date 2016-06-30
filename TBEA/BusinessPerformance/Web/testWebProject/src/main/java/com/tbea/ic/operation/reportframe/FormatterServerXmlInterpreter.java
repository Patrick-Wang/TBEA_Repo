package com.tbea.ic.operation.reportframe;

import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.common.formatter.v2.core.FormatterHandler;
import com.tbea.ic.operation.common.formatter.v2.core.FormatterServer;

public class FormatterServerXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {

		if (!"formatterServer".equals(e.getTagName())) {
			return false;
		}

		FormatterServer serv = new FormatterServer();
		NodeList list = e.getChildNodes();
		for (int i = 0; i < list.getLength(); ++i) {
			if (list.item(i) instanceof Element) {
				Element elem = (Element) list.item(i);
				if ("formatter".equals(elem.getTagName())) {
					List<FormatterHandler> fmts = (List) component.getVar(elem
							.getAttribute("ref"));
					if (null != fmts) {
						for (FormatterHandler handler : fmts) {
							serv.add(handler, i);
						}
					}
				}
			}
		}

		if (e.hasAttribute("acceptNullAs")) {
			serv.acceptNullAs(e.getAttribute("acceptNullAs"));
		}
		ELParser el = new ELParser(component);
		el.parser(e.getAttribute("table"));
		List<ELExpression> elexprs = el.parser(e.getAttribute("table"));

		try {
			serv.setTable((List<List<String>>) elexprs.get(0).value());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String id = e.getAttribute("id");
		component.local(id, serv);
		return true;
	}
}