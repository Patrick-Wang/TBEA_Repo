package com.xml.frame.report.util.xml;

import com.util.tools.xml.XmlWalker;
import org.docx4j.vml.root.Xml;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.frame.script.el.ELParser;
import com.util.tools.xml.Each;
import com.util.tools.xml.Loop;

public class XmlElWalker {

	static boolean skip(Element elem, ELParser elp) throws Exception {
		if (!elem.hasAttribute("skip") || null == elp) {
			return false;
		}
		return XmlUtil.getBoolean(elem.getAttribute("skip"), elp);
	}

	public static String elementText(NodeList list, ELParser elp, int index) {
		Element e = element(list, elp, index);
		if (null != e) {
			return XmlUtil.getText(e);
		}
		return "";
	}

	public static Element nextElement(Element e, ELParser elp) throws Exception {
		Node node = e.getNextSibling();
		while (null != node && (!(node instanceof Element) || skip((Element) node, elp))) {
			node = node.getNextSibling();
		}
		return (Element) node;
	}

	public static Element element(NodeList list, ELParser elp, int index) {
		try {
			return each(list, elp, new Each() {
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

	public static Element child(Node elem, ELParser elp, String tagName) {
		try {
			return eachChildren(elem, elp, new Each() {
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

	public static void eachChildren(Node node, ELParser elp, Loop onLoop) throws Exception {
//		try {
//			Node node = elem.getFirstChild();
//			while (node != null) {
//				if (node instanceof Element && !skip((Element) node, elp)) {
//					onLoop.on((Element) node);
//				}
//				node = node.getNextSibling();
//			}
//		} catch (java.lang.NullPointerException e) {
//			e.printStackTrace();
//		}
        XmlWalker.eachChildren(node, new Loop() {
            @Override
            public void on(Element elem) throws Exception {
                if(!skip(elem, elp)){
                    onLoop.on(elem);
                }
            }
        });
	}

	public static Element eachChildren(Node node, ELParser elp, Each onEach) throws Exception {
	    return XmlWalker.eachChildren(node, new Each() {
            @Override
            public boolean on(Element elem) throws Exception {
                if(!skip(elem, elp)){
                    return onEach.on(elem);
                }
                return true;
            }
        });
//		try {
//			Node node = elem.getFirstChild();
//			while (node != null) {
//				if (node instanceof Element && !skip((Element) node, elp)) {
//					if (onEach.on((Element) node)) {
//						return (Element) node;
//					}
//				}
//				node = node.getNextSibling();
//			}
//		} catch (java.lang.NullPointerException e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	public static void each(NodeList list, ELParser elp, Loop onLoop) throws Exception {
	    XmlWalker.each(list, new Loop() {
            @Override
            public void on(Element elem) throws Exception {
                if(!skip(elem, elp)){
                    onLoop.on(elem);
                }
            }
        });
//		try {
//			Node node = null;
//			for (int i = 0, len = list.getLength(); i < len; ++i) {
//				node = list.item(i);
//				if (node instanceof Element && !skip((Element) node, elp)) {
//					onLoop.on((Element) node);
//				}
//			}
//		} catch (java.lang.NullPointerException e) {
//			e.printStackTrace();
//		}
	}

	public static Element each(NodeList list, ELParser elp, Each onEach) throws Exception {
       return XmlWalker.each(list, new Each() {
            @Override
            public boolean on(Element elem) throws Exception {
                if(!skip(elem, elp)){
                    return onEach.on(elem);
                }
                return true;
            }
        });
//		Node node = null;
//		for (int i = 0, len = list.getLength(); i < len; ++i) {
//			node = list.item(i);
//			if (node instanceof Element && !skip((Element) node, elp)) {
//				if (onEach.on((Element) node)) {
//					return (Element) node;
//				}
//			}
//		}
//		return null;
	}
}
