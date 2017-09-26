package com.util.tools.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XmlWalker {

	public static Element nextElement(Element e) throws Exception {
		Node node = e.getNextSibling();
		while (null != node && (!(node instanceof Element))) {
			node = node.getNextSibling();
		}
		return (Element) node;
	}

	public static Element element(NodeList list, int index) {
		try {
			return each(list, new Each() {
				int count;

				@Override
				public boolean on(Element elem) {
					if (count++ == index) {
						return true;
					}
					return false;
				}
			});
		} catch (Exception e) {
			return null;
		}
	}

	public static Element child(Node elem, String tagName) {
		try {
			return eachChildren(elem, new Each() {
				@Override
				public boolean on(Element elem) {
					if (elem.getTagName().equals(tagName)) {
						return true;
					}
					return false;
				}
			});
		} catch (Exception e) {
			return null;
		}
	}

	public static void eachChildren(Node elem, Loop onLoop) throws Exception {
		try {
			Node node = elem.getFirstChild();
			while (node != null) {
				if (node instanceof Element) {
					onLoop.on((Element) node);
				}
				node = node.getNextSibling();
			}
		} catch (java.lang.NullPointerException e) {
			e.printStackTrace();
		}
	}

	public static Element eachChildren(Node elem, Each onEach) throws Exception {
		try {
			Node node = elem.getFirstChild();
			while (node != null) {
				if (node instanceof Element) {
					if (onEach.on((Element) node)) {
						return (Element) node;
					}
				}
				node = node.getNextSibling();
			}
		} catch (java.lang.NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void each(NodeList list, Loop onLoop) throws Exception {
		try {
			Node node = null;
			for (int i = 0, len = list.getLength(); i < len; ++i) {
				node = list.item(i);
				if (node instanceof Element) {
					onLoop.on((Element) node);
				}
			}
		} catch (java.lang.NullPointerException e) {
			e.printStackTrace();
		}
	}

	public static Element each(NodeList list, Each onEach) throws Exception {
		Node node = null;
		for (int i = 0, len = list.getLength(); i < len; ++i) {
			node = list.item(i);
			if (node instanceof Element) {
				if (onEach.on((Element) node)) {
					return (Element) node;
				}
			}
		}
		return null;
	}
}
