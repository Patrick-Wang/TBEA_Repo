package com.xml.frame.report.interpreter;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import com.frame.script.el.ELParser;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.util.XmlUtil;

public class LoopXmlInterpreter implements XmlInterpreter {

	ELParser elp;
	AbstractXmlComponent component;
	AbstractXmlComponent blockComponent;

	private AbstractXmlComponent getBlockComp(Element e) {
		if (null == blockComponent) {
			NamedNodeMap nnm = component.getConfig().getAttributes();
			for (int i = 0; i < nnm.getLength(); ++i) {
				String name = nnm.item(i).getNodeName();
				if (!e.hasAttribute(name)) {
					String value = nnm.item(i).getNodeValue();
					e.setAttribute(name, value);
				}
			}
			blockComponent = component.clone(e);
		}
		return blockComponent;
	}

	@Override
	public boolean accept(AbstractXmlComponent component, Element e)
			throws Exception {
		boolean bRet = Schema.isLoop(e);
		if (bRet) {
			this.component = component;
			this.blockComponent = null;
			elp = new ELParser(component);
			String test = null;
			if (e.hasAttribute("test")) {
				test = e.getAttribute("test");
			}

			if (!parseForLoop(e, test)) {
				if (test != null) {
					while (XmlUtil.getBoolean(test, elp)) {
						this.getBlockComp(e).run(component.globalContext());
					}
				}
			}
		}
		return bRet;
	}

	private boolean parseForLoop(Element e, String test) throws Exception {
		if (e.hasAttribute("from") && e.hasAttribute("to")) {
			Integer from = XmlUtil.getInt(e.getAttribute("from"), elp, null);
			Integer to = XmlUtil.getInt(e.getAttribute("to"), elp, null);
			String var = null;
			if (e.hasAttribute("var")) {
				var = e.getAttribute("var");
			}
			if (null != from && to != null) {
				if (test != null) {
					for (int i = from; i <= to && XmlUtil.getBoolean(test, elp); ++i) {
						if (var != null) {
							this.getBlockComp(e).local(var, i);
						}
						this.getBlockComp(e).run(component.globalContext());
					}
				} else {
					for (int i = from; i <= to; ++i) {
						if (var != null) {
							this.getBlockComp(e).local(var, i);
						}
						this.getBlockComp(e).run(component.globalContext());
					}
				}
				return true;
			}
		}
		return false;
	}
}