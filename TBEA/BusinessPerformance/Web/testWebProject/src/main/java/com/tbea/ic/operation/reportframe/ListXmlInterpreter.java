package com.tbea.ic.operation.reportframe;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.common.MathUtil;

public class ListXmlInterpreter implements XmlInterpreter {

	private int getRepeat(AbstractXmlComponent component, Element item) {
		String repeat = item.getAttribute("repeat");
		if (null == repeat || repeat.isEmpty()) {
			return 1;
		}
		ELParser elp = new ELParser(component);
		List<ELExpression> elexps = elp.parser(repeat);
		if (elexps.isEmpty()) {
			return Integer.valueOf(repeat);
		} else {
			try {
				return (Integer) (elexps.get(0).value());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	private void repestAdd(List<Object> objs, Object obj, int repeat, int insert) {
		for (int j = 0; j < repeat; ++j) {
			objs.add(insert, obj);
		}
	}

	private void repestAddList(List<Object> objs, List list, int repeat,
			int insert) {
		for (int j = 0; j < repeat; ++j) {
			objs.addAll(insert, list);
		}
	}

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {

		if (!"list".equals(e.getTagName())) {
			return false;
		}
		String id = e.getAttribute("id");
		List<Object> objs = new ArrayList<Object>();
		NodeList children = e.getChildNodes();
		int type = TypeUtil.typeof(e);
		for (int i = 0; i < children.getLength(); ++i) {
			if (children.item(i) instanceof Element) {
				Element item = (Element) children.item(i);
				if (item.getTagName().equals("item")) {
					int repeat = getRepeat(component, item);
					int insert = getInsert(component, item, objs);
					if (concat(component, item, objs, repeat, insert)) {
						continue;
					}

					if (isNull(component, item, objs, repeat, insert)) {
						continue;
					}

					// if (sum(component, item, objs, repeat, insert, type)){
					// continue;
					// }

					value(item, objs, repeat, insert, type);
				}
			}
		}
		component.local(id, objs);
		return false;
	}

	private void value(Element item, List<Object> objs, int repeat, int insert,
			int type) {
		String val = item.getFirstChild().getTextContent();
		if (type == TypeUtil.INT) {
			if (val.isEmpty()) {
				repestAdd(objs, null, repeat, insert);
			} else {
				repestAdd(objs, Integer.valueOf(val), repeat, insert);
			}
		} else if (type == TypeUtil.DOUBLE) {
			if (val.isEmpty()) {
				repestAdd(objs, null, repeat, insert);
			} else {
				repestAdd(objs, Double.valueOf(val), repeat, insert);
			}
		} else if (type == TypeUtil.STRING) {
			if (val.isEmpty()) {
				repestAdd(objs, "", repeat, insert);
			} else {
				repestAdd(objs, val, repeat, insert);
			}
		}
	}

	private boolean sum(AbstractXmlComponent component, Element item,
			List<Object> objs, int repeat, int insert, int type) {

		if (type == TypeUtil.STRING) {
			return false;
		}

		String sumFrom = item.getAttribute("sumFrom");
		String sumTo = item.getAttribute("sumTo");
		Integer from = null;
		Integer to = null;
		if (null != sumFrom && !sumFrom.isEmpty() && null != sumTo
				&& !sumTo.isEmpty()) {
			ELParser elp = new ELParser(component);
			List<ELExpression> elexps = elp.parser(sumFrom);
			if (elexps.isEmpty()) {
				from = Integer.valueOf(sumFrom);
			} else {
				try {
					from = (Integer) (elexps.get(0).value());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			elexps = elp.parser(sumTo);
			if (elexps.isEmpty()) {
				to = Integer.valueOf(sumTo);
			} else {
				try {
					to = (Integer) (elexps.get(0).value());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (null != from && null != to) {
			if (type == TypeUtil.INT) {
				Integer ret = null;
				for (int i = from; i <= to; ++i) {
					ret = MathUtil.sum(ret, (Integer) objs.get(i));
				}
				repestAdd(objs, ret, repeat, insert);
			} else {
				Double ret = null;
				for (int i = from; i <= to; ++i) {
					ret = MathUtil.sum(ret, (Double) objs.get(i));
				}
				repestAdd(objs, ret, repeat, insert);
			}
			return true;
		}
		return false;
	}

	private boolean isNull(AbstractXmlComponent component, Element item,
			List<Object> objs, int repeat, int insert) {
		if ("true".equals(item.getAttribute("isNull"))) {
			repestAdd(objs, null, repeat, insert);
			return true;
		}
		return false;
	}

	private boolean concat(AbstractXmlComponent component, Element item,
			List<Object> objs, int repeat, int insert) {
		List list = (List) component.getVar(item.getAttribute("concat"));
		if (null != list) {
			repestAddList(objs, list, repeat, insert);
			return true;
		}
		return false;
	}

	private int getInsert(AbstractXmlComponent component, Element item,
			List<Object> objs) {
		String insert = item.getAttribute("insert");
		if (null == insert || insert.isEmpty()) {
			return objs.size();
		}

		ELParser elp = new ELParser(component);
		List<ELExpression> elexps = elp.parser(insert);
		if (elexps.isEmpty()) {
			return Integer.valueOf(insert);
		} else {
			try {
				return (Integer) (elexps.get(0).value());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objs.size();
	}

}