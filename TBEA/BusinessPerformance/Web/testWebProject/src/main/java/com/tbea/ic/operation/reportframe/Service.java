package com.tbea.ic.operation.reportframe;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Service extends AbstractXmlComponent {

	XmlInterpreter[] interpreters = new XmlInterpreter[] {
			new SqlXmlInterpreter(), 
			new ListXmlInterpreter(), 
			new TableXmlInterpreter(),
			new ContextXmlInterpreter() };

	public Service(Element e) {
		super(e);
	}

	protected Transaction getTransactionManager() {
		return (Transaction) getVar(config.getAttribute("transaction"));
	}

	@Override
	protected void onRun() {
		Transaction tr = getTransactionManager();
		tr.run(new Runnable() {
			@Override
			public void run() {
				NodeList children = config.getChildNodes();

				for (int i = 0; i < children.getLength(); ++i) {
					if (children.item(i) instanceof Element){
					Element e = (Element) children.item(i);
						for (XmlInterpreter interpreter : interpreters) {
							if (interpreter.accept(Service.this, e)) {
								break;
							}
						}
					}
				}
			}

		});
	}
}
