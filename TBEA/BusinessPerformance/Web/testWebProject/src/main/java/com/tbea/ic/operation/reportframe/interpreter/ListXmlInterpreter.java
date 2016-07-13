package com.tbea.ic.operation.reportframe.interpreter;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.TypeUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil.OnLoop;

public class ListXmlInterpreter implements XmlInterpreter {
	
	private void repeatAdd(List<Object> objs, Object obj, int repeat, int insert) {
		for (int j = 0; j < repeat; ++j) {
			objs.add(insert, obj);
		}
	}

	private void repeatAddList(List<Object> objs, List list, int repeat,
			int insert) {
		for (int j = 0; j < repeat; ++j) {
			objs.addAll(insert, list);
		}
	}
	
	private void injectFromSql(List<Object> objs, List<Object[]> sqlRet, int index,
			List order, int by) {
		for (int i = 0; i < order.size(); ++i){
			objs.add(null);
			for (int j = 0; j < sqlRet.size(); ++j){
				if (order.get(i).equals(sqlRet.get(j)[by])){
					objs.set(objs.size() - 1, sqlRet.get(j)[index]);
					break;
				}
			}
		}
	}

	private void injectFromSql(List<Object> objs, List<Object[]> sqlRet, int index) {
		for (int i = 0; i < sqlRet.size(); ++i){
			objs.add(sqlRet.get(i)[index]);
		}
	}
	
	private void parseSql(AbstractXmlComponent component, Element e, List<Object> objs) throws Exception{
		List sqlRet = (List) component.getVar(e.getAttribute("sql"));
		if (sqlRet != null && !sqlRet.isEmpty()){
			ELParser elParser = new ELParser(component);
			
			if (sqlRet.get(0).getClass().isArray()){
				int index = XmlUtil.getIntAttr(e, "value", elParser, 0);
				List order = (List) component.getVar(e.getAttribute("order"));
				if (null != order){
					int by = XmlUtil.getIntAttr(e, "by", elParser, 0);
					injectFromSql(objs, sqlRet, index, order, by);
				}else{
					injectFromSql(objs, sqlRet, index);
				}
			}else{
				
			}			
		}
	}
	

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		boolean bRet= Schema.isList(e);
		if (bRet) {
			List<Object> objs = new ArrayList<Object>();
			
			parseSql(component, e, objs);
			
			parseItems(component, e, objs);

			if ("true".equals(e.getAttribute("global"))){
				component.global(e.getAttribute("id"), objs);
			}else{
				component.local(e.getAttribute("id"), objs);
			}
		}
		return bRet;
	}

	public static List<Integer> parserArray(AbstractXmlComponent component, String arr) throws Exception {
		return XmlUtil.toIntList(arr, new ELParser(component));
	}
	
	private void parseItems(AbstractXmlComponent component, Element e, List<Object> objs) throws Exception {
		NodeList children = e.getChildNodes();
		int type = TypeUtil.typeof(e);
		ELParser elp = new ELParser(component);
		if (null != e.getFirstChild()){
			String text = e.getFirstChild().getTextContent();
			if (TypeUtil.STRING == type){
				objs.addAll(XmlUtil.toStringList(text, elp));
			} else if (TypeUtil.INT == type){
				objs.addAll(XmlUtil.toIntList(text, elp));
			} else if (TypeUtil.DOUBLE == type){
				objs.addAll(XmlUtil.toDoubleList(text, elp));
			}  
		}
		
		XmlUtil.each(children, new OnLoop(){
			@Override
			public void on(Element elem) throws Exception {
				if (elem.getTagName().equals("item")) {
					int repeat = XmlUtil.getIntAttr(elem, "repeat", elp, 1);
					int insert = XmlUtil.getIntAttr(elem, "insert", elp, objs.size());
					int index = XmlUtil.getIntAttr(elem, "index", elp, -1);
					if (concat(component, elem, objs, repeat, insert)) {
						return;
					}

					if (isNull(component, elem, objs, repeat, insert)) {
						return;
					}

					// if (sum(component, item, objs, repeat, insert, type)){
					// continue;
					// }
					if (index >= 0){
						replaceAdd(elem, objs, repeat, index, type);
					}else{
						insertAdd(elem, objs, repeat, insert, type);
					}
				}
			}
		});
	}

	private void replaceAdd(Element item, List<Object> objs, int repeat, int index,
			int type) {
		objs.remove(index);
		insertAdd(item, objs, repeat, index, type);
	}
	
	private void insertAdd(Element item, List<Object> objs, int repeat, int insert,
			int type) {
		String val = item.getFirstChild().getTextContent();
		if (type == TypeUtil.INT) {
			if (val.isEmpty()) {
				repeatAdd(objs, null, repeat, insert);
			} else {
				repeatAdd(objs, Integer.valueOf(val), repeat, insert);
			}
		} else if (type == TypeUtil.DOUBLE) {
			if (val.isEmpty()) {
				repeatAdd(objs, null, repeat, insert);
			} else {
				repeatAdd(objs, Double.valueOf(val), repeat, insert);
			}
		} else if (type == TypeUtil.STRING) {
			repeatAdd(objs, val, repeat, insert);
		}
	}

//	private boolean sum(AbstractXmlComponent component, Element item,
//			List<Object> objs, int repeat, int insert, int type) {
//
//		if (type == TypeUtil.STRING) {
//			return false;
//		}
//
//		String sumFrom = item.getAttribute("sumFrom");
//		String sumTo = item.getAttribute("sumTo");
//		Integer from = null;
//		Integer to = null;
//		if (null != sumFrom && !sumFrom.isEmpty() && null != sumTo
//				&& !sumTo.isEmpty()) {
//			ELParser elp = new ELParser(component);
//			List<ELExpression> elexps = elp.parser(sumFrom);
//			if (elexps.isEmpty()) {
//				from = Integer.valueOf(sumFrom);
//			} else {
//				try {
//					from = (Integer) (elexps.get(0).value());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//			elexps = elp.parser(sumTo);
//			if (elexps.isEmpty()) {
//				to = Integer.valueOf(sumTo);
//			} else {
//				try {
//					to = (Integer) (elexps.get(0).value());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		if (null != from && null != to) {
//			if (type == TypeUtil.INT) {
//				Integer ret = null;
//				for (int i = from; i <= to; ++i) {
//					ret = MathUtil.sum(ret, (Integer) objs.get(i));
//				}
//				repestAdd(objs, ret, repeat, insert);
//			} else {
//				Double ret = null;
//				for (int i = from; i <= to; ++i) {
//					ret = MathUtil.sum(ret, (Double) objs.get(i));
//				}
//				repestAdd(objs, ret, repeat, insert);
//			}
//			return true;
//		}
//		return false;
//	}

	private boolean isNull(AbstractXmlComponent component, Element item,
			List<Object> objs, int repeat, int insert) {
		if ("true".equals(item.getAttribute("isNull"))) {
			repeatAdd(objs, null, repeat, insert);
			return true;
		}
		return false;
	}

	private boolean concat(AbstractXmlComponent component, Element item,
			List<Object> objs, int repeat, int insert) {
		List list = (List) component.getVar(item.getAttribute("concat"));
		if (null != list) {
			repeatAddList(objs, list, repeat, insert);
			return true;
		}
		return false;
	}
}