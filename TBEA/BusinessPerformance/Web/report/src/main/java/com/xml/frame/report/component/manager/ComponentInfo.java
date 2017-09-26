package com.xml.frame.report.component.manager;

import org.w3c.dom.Element;

public class ComponentInfo{
	Element e;
	String path;
	public Element getE() {
		return e;
	}
	public void setE(Element e) {
		this.e = e;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public ComponentInfo(Element e, String path) {
		super();
		this.e = e;
		this.path = path;
	}
}