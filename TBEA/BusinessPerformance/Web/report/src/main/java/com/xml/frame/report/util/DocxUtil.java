package com.xml.frame.report.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.docx4j.XmlUtils;
import org.docx4j.wml.CTFFData;
import org.docx4j.wml.CTFFTextInput;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.FldChar;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Tr;
import org.jvnet.jaxb2_commons.ppp.Child;

public class DocxUtil {
	
	public static Object clone(Child c) {
		return XmlUtils.deepCopy(c);
	}
	
	public static Object getParent(Child c, Class<?> cls) {
		if (null != c.getParent()) {
			if (c.getParent().getClass().equals(cls)) {
				return c.getParent();
			}
			
			if (c.getParent() instanceof Child) {
				return DocxUtil.getParent((Child)c.getParent(), cls);
			}			
		}
		return null;
	}
	
	public static Object getFirstChild(ContentAccessor c, Class<?> cls) {
		List<Object> children = c.getContent();
		for (Object child : children) {
			child = unpackJAXB(child);
			if (child.getClass().equals(cls)) {
				return child;
			}
		}
		return null;
	}
	
	public static int rowCount(Tbl tbl, Tr trStart) {
		List<Object> children = tbl.getContent();
		int count = -1;
		for (Object child : children) {
			child = unpackJAXB(child);
			if (child == trStart) {
				count = 0;
			}
			if (child instanceof Tr && count >= 0) {
				++count;
			}
		}
		return count;
	}
	
	public static int columnCount(Tr tr, Tc tcStart) {
		List<Object> children = tr.getContent();
		int count = -1;
		for (Object child : children) {
			child = unpackJAXB(child);
			if (child == tcStart) {
				count = 0;
			}
			if (child instanceof Tc && count >= 0) {
				++count;
			}
		}
		return count;
	}
	
	public static Tc upTc(Tc tc) {
		Tr tr = (Tr) DocxUtil.getParent(tc, Tr.class);
		int index = DocxUtil.findPosition(tc, Tc.class);
		Tr trUp = (Tr) DocxUtil.preSibling(tr, Tr.class);
		Tc tcUp = (Tc) unpackJAXB(tc(trUp, index));
		return tcUp;
	}
	
	public static Tc preTc(Tc tc) {
		return (Tc) DocxUtil.preSibling(tc, Tc.class);
	}
	
	public static Tr tr(Tbl tbl, int index) {
		List<Object> children = tbl.getContent();
		int i = 0;
		for (Object child : children) {
			child = unpackJAXB(child);
			if (child instanceof Tr) {
				if (i == index) {
					return (Tr) child;
				}
				++i;
			}
		}
		return null;
	}
	
	public static Object unpackJAXB(Object obj) {
		if (obj instanceof JAXBElement) {
			return ((JAXBElement)obj).getValue();
		}
		return obj;
	}
	
	public static Tc tc(Tr tr, int index) {
		List<Object> children = tr.getContent();
		int i = 0;
		for (Object child : children) {
			child = unpackJAXB(child);
			if (child instanceof Tc) {
				if (i == index) {
					return (Tc) child;
				}
				++i;
			}
		}
		return null;
	}
	

	
	public static void removeFromDocument(Child c) {
		if (c.getParent() instanceof ContentAccessor) {
			ContentAccessor ca = (ContentAccessor) c.getParent();
			List<Object> children = ca.getContent();
			for (int i = 0; i < children.size(); ++i) {
				Object child = unpackJAXB(children.get(i));
				if (child == c) {
					children.remove(i);
					break;
				}
			}
		}
	}

	public static int findPosition(Child c, Class cls) {
		int index = -1;
		if (c.getParent() instanceof ContentAccessor) {
			ContentAccessor ca = (ContentAccessor) c.getParent();
			List<Object> children = ca.getContent();
			for (int i = 0, j = 0; i < children.size(); ++i) {
				Object child = unpackJAXB(children.get(i));
				if (child.getClass().equals(cls)) {
					if (child == c) {
						index = j;
						break;
					}
					++j;
				}
			}
		}
		return index;
	}
	
	public static int findPosition(Child c) {
		int index = -1;
		if (c.getParent() instanceof ContentAccessor) {
			ContentAccessor ca = (ContentAccessor) c.getParent();
			List<Object> children = ca.getContent();
			for (int i = 0; i < children.size(); ++i) {
				Object child = unpackJAXB(children.get(i));
				if (child == c) {
					index = i;
					break;
				}
			}
		}
		return index;
	}
	
	public static Object nextSibling(Child c) {
		if (c.getParent() instanceof ContentAccessor) {
			ContentAccessor ca = (ContentAccessor) c.getParent();
			int index = findPosition(c);
			List<Object> children = ca.getContent();
			if (index >= 0 && index < children.size() - 1) {
				return unpackJAXB(children.get(index + 1));
			}
		}
		return null;
	}
	
	public static Object nextSibling(Child c, Class<?> cls) {
		if (c.getParent() instanceof ContentAccessor) {
			ContentAccessor ca = (ContentAccessor) c.getParent();
			int index = findPosition(c);
			List<Object> children = ca.getContent();
			if (index >= 0) {
				for (int i = index + 1; i <  children.size(); ++i) {
					Object child = unpackJAXB(children.get(i));
					if (child.getClass().equals(cls)) {
						return child;
					}
				}
			}
		}
		return null;
	}
	
	public static Object preSibling(Child c, Class cls) {
		if (c.getParent() instanceof ContentAccessor) {
			ContentAccessor ca = (ContentAccessor) c.getParent();
			int index = findPosition(c);
			List<Object> children = ca.getContent();
			
			if (index >= 1) {
				for (int i = index - 1; i >= 0; --i) {
					Object child = unpackJAXB(children.get(i));
					if (child.getClass().equals(cls)) {
						return child;
					}
				}
			}
		}
		return null;
	}
	
	public static Object preSibling(Child c) {
		if (c.getParent() instanceof ContentAccessor) {
			ContentAccessor ca = (ContentAccessor) c.getParent();
			int index = findPosition(c);
			List<Object> children = ca.getContent();
			if (index >= 1) {
				return unpackJAXB(children.get(index - 1));
			}
		}
		return null;
	}
	
	public static String getDefaultText(FldChar fldChar) {
		String val = "";
		CTFFData fdData = fldChar.getFfData();
		List<JAXBElement<?>> jaxbes = fdData.getNameOrEnabledOrCalcOnExit();
		for (JAXBElement<?> jaxb : jaxbes) {
			if (jaxb.getValue() instanceof CTFFTextInput) {
				CTFFTextInput input = (CTFFTextInput) jaxb.getValue();
				val = input.getDefault().getVal();
				break;
			}
		}
		return val;
	}

	public static List<Object> getElementsByType(Object obj, Class<?> target) {
		List<Object> result = new ArrayList<Object>();
		obj = unpackJAXB(obj);
		
		if (obj.getClass().equals(target)) {
			result.add(obj);
		} else if (obj instanceof ContentAccessor) {
			List children = ((ContentAccessor) obj).getContent();
			for (Object child : children) {
				result.addAll(getElementsByType(child, target));
			}
		}
		return result;
	}

}
