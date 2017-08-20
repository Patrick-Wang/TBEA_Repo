package com.xml.frame.report.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.docx4j.XmlUtils;
import org.docx4j.wml.ContentAccessor;
import org.jvnet.jaxb2_commons.ppp.Child;

public class DocxQuery {

	List<Object> elems;

	DocxQuery(List<Object> elems) {
		super();
		this.elems = elems;
	}

	private static List query(Object obj, Class<?> cls) {
		obj = unpackJAXB(obj);
		List<Object> ret = new ArrayList<Object>();
		if (obj instanceof ContentAccessor) {
			ContentAccessor ca = (ContentAccessor) obj;
			for (Object child : ca.getContent()) {
				child = unpackJAXB(child);
				if (child.getClass().equals(cls)) {
					ret.add(child);
				}
			}
		}
		return ret;
	}

	public static interface OnEach {
		boolean on(int i, Object elem) throws Exception;
	}

	public DocxQuery each(OnEach each) throws Exception {
		for (int i = 0; i < this.elems.size(); ++i) {
			if (!each.on(i, elems.get(i))) {
				break;
			}
		}
		return this;
	}

	public static DocxQuery find(Object obj, Class<?> cls) {
		List<Object> result = new ArrayList<Object>();
		obj = unpackJAXB(obj);
		if (obj.getClass().equals(cls)) {
			result.add(obj);
		} else if (obj instanceof ContentAccessor) {
			List children = ((ContentAccessor) obj).getContent();
			for (Object child : children) {
				result.addAll(DocxQuery.find(child, cls).val());
			}
		}
		return new DocxQuery(result);
	}

	public static Object getParent(Child c, Class<?> cls) {
		if (null != c.getParent()) {
			if (c.getParent().getClass().equals(cls)) {
				return c.getParent();
			}
			
			if (c.getParent() instanceof Child) {
				return getParent((Child)c.getParent(), cls);
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
	public static DocxQuery q(Object obj, Class<?> cls) {
		return new DocxQuery(query(obj, cls));
	}

	public static DocxQuery q(Object obj) {
		if (obj instanceof List) {
			return new DocxQuery((List<Object>) obj);
		}
		List<Object> es = new ArrayList<Object>();
		es.add(obj);
		return new DocxQuery(es);
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
				for (int i = index + 1; i < children.size(); ++i) {
					Object child = unpackJAXB(children.get(i));
					if (child.getClass().equals(cls)) {
						return child;
					}
				}
			}
		}
		return null;
	}

	public static Object unpackJAXB(Object obj) {
		if (obj instanceof JAXBElement) {
			return ((JAXBElement) obj).getValue();
		}
		return obj;
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

	static Object preSibling(Child c) {
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

	public boolean isEmpty() {
		return this.elems.isEmpty();
	}

	public int size() {
		return this.elems.size();
	}

	public List val() {
		return this.elems;
	}

	public Object val(int i) {
		return this.elems.get(i);
	}

	public DocxQuery pos() {
		List<Object> es = new ArrayList<Object>();
		for (Object e : elems) {
			e = unpackJAXB(e);
			if (e instanceof ContentAccessor) {
				es.add(findPosition((Child) e, e.getClass()));
			}
		}
		return new DocxQuery(es);
	}

	public DocxQuery clone() {
		List<Object> es = new ArrayList<Object>();
		for (Object e : elems) {
			e = unpackJAXB(e);
			if (e instanceof Child) {
				es.add(XmlUtils.deepCopy(e));
			}
		}
		return new DocxQuery(es);
	}

	public DocxQuery first() {
		return eq(0);
	}

	public DocxQuery eq(int i) {
		List<Object> es = new ArrayList<Object>();
		if (elems.size() > i) {
			es.add(elems.get(i));
		}
		return new DocxQuery(es);
	}

	public DocxQuery append(Object element) {
		for (Object e : elems) {
			e = unpackJAXB(e);
			if (e instanceof ContentAccessor) {
				((ContentAccessor) e).getContent().add(element);
				element = unpackJAXB(element);
				if (element instanceof Child) {
					((Child) element).setParent(e);
				}
			}
		}
		return this;
	}

	public DocxQuery q(Class cls) {
		List<Object> es = new ArrayList<Object>();
		for (Object e : elems) {
			es.addAll(query(e, cls));
		}
		return new DocxQuery(es);
	}

	public DocxQuery find(Class cls) {
		List<Object> es = new ArrayList<Object>();
		for (Object e : elems) {
			es.addAll(DocxQuery.find(e, cls).val());
		}
		return new DocxQuery(es);
	}

	public List remove() {
		for (Object e : elems) {
			e = unpackJAXB(e);
			if (e instanceof Child) {
				removeFromDocument((Child) e);
			}
		}
		return elems;
	}

	public DocxQuery parent(Class<?> cls) {
		List<Object> ps = new ArrayList<Object>();
		for (Object e : elems) {
			e = unpackJAXB(e);
			if (e instanceof Child) {
				Object p = getParent((Child) e, cls);
				if (p != null) {
					ps.add(p);
				}
			}
		}
		return new DocxQuery(ps);
	}

	public DocxQuery parent() {
		List<Object> ps = new ArrayList<Object>();
		for (Object e : elems) {
			e = unpackJAXB(e);
			if (e instanceof Child) {
				Object p = ((Child) e).getParent();
				if (p != null) {
					ps.add(p);
				}
			}
		}
		return new DocxQuery(ps);
	}

	public DocxQuery next() {
		List<Object> ps = new ArrayList<Object>();
		for (Object e : elems) {
			e = unpackJAXB(e);
			if (e instanceof Child) {
				Object p = nextSibling((Child) e, e.getClass());
				if (p != null) {
					ps.add(p);
				}
			}
		}
		return new DocxQuery(ps);
	}

	public DocxQuery anyNext() {
		List<Object> ps = new ArrayList<Object>();
		for (Object e : elems) {
			e = unpackJAXB(e);
			if (e instanceof Child) {
				Object p = nextSibling((Child) e);
				if (p != null) {
					ps.add(p);
				}
			}
		}
		return new DocxQuery(ps);
	}

	public DocxQuery pre() {
		List<Object> ps = new ArrayList<Object>();
		for (Object e : elems) {
			e = unpackJAXB(e);
			if (e instanceof Child) {
				Object p = preSibling((Child) e, e.getClass());
				if (p != null) {
					ps.add(p);
				}
			}
		}
		return new DocxQuery(ps);
	}

	public DocxQuery up() {
		List<Object> ps = new ArrayList<Object>();
		for (Object e : elems) {
			e = unpackJAXB(e);
			if (e instanceof Child) {
				Object p = ((Child) e).getParent();
				if (p == null) {
					continue;
				}
				p = preSibling((Child) p);
				if (null == p) {
					continue;
				}
				int index = findPosition((Child) e, e.getClass());
				List objs = query(p, e.getClass());
				if (objs.size() > index) {
					ps.add(objs.get(index));
				}

			}
		}
		return new DocxQuery(ps);
	}

	public DocxQuery down() {
		List<Object> ps = new ArrayList<Object>();
		for (Object e : elems) {
			e = unpackJAXB(e);
			if (e instanceof Child) {
				Object p = ((Child) e).getParent();
				if (p == null) {
					continue;
				}
				p = nextSibling((Child) p);
				if (null == p) {
					continue;
				}
				int index = findPosition((Child) e, e.getClass());
				List objs = query(p, e.getClass());
				if (objs.size() > index) {
					ps.add(objs.get(index));
				}

			}
		}
		return new DocxQuery(ps);
	}
}
