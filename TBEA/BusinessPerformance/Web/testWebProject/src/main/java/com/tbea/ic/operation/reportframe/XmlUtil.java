package com.tbea.ic.operation.reportframe;

import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtil {
	
	public static interface OnEach{
		boolean on(Element elem);
	}
	
	public static interface OnLoop{
		void on(Element elem);
	}
	
	public static String elementText(NodeList list, int index) {
		Element e = element(list, index);
		if (null != e){
			return e.getFirstChild().getTextContent();
		}
		return "";
	}
	
	public static Element element(NodeList list, int index) {
		Element[] eles = new Element[]{null};
		each(list, new OnEach(){
			
			int count;
			
			@Override
			public boolean on(Element elem) {
				if (count++ == index){
					eles[0] = elem;
					return true;
				}
				return false;
			}
			
		});
		return eles[0];
	}
	
	public static Integer getInt(String text, ELParser elParser,
			Integer defaultVal) {
		String attrVal = text;
		if (null != attrVal && !attrVal.isEmpty()) {
			List<ELExpression> elexps = elParser.parser(attrVal);
			if (elexps.isEmpty()) {
				return Integer.valueOf(attrVal);
			} else {
				try {
					return (Integer) (elexps.get(0).value());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return defaultVal;
	}
	
	public static Integer getIntAttr(Element elem, String attr, ELParser elParser,
			Integer defaultVal) {
		return getInt(elem.getAttribute(attr), elParser, defaultVal);
	}
	
	public static String getAttr(Element e, String attr){
		if (e.hasAttribute(attr)){
			return e.getAttribute(attr);
		}
		return null;
	}
	
	public static void each(NodeList list, OnLoop onLoop){
		Node node = null;
		for (int i = 0, len = list.getLength(); i < len; ++i){
			node = list.item(i);
			if (node instanceof Element){
				onLoop.on((Element) node);
			}
		}
	}
	
	public static void each(NodeList list, OnEach onEach){
		Node node = null;
		for (int i = 0, len = list.getLength(); i < len; ++i){
			node = list.item(i);
			if (node instanceof Element){
				if (onEach.on((Element) node)){
					break;
				}
			}
		}
	}
}
