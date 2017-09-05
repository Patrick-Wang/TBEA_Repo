package com.xml.frame.report.interpreter;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.frame.script.el.ELParser;
import com.frame.script.util.TypeUtil;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.util.xml.Loop;
import com.xml.frame.report.util.xml.XmlUtil;
import com.xml.frame.report.util.xml.XmlWalker;

import net.sf.json.JSONArray;
/***********************************
 * tagName : list
 * text	: 已逗号分隔的数组 
 * 		int eg. 1,2,3,4,${num1}
 * 		string eg. abc, ${str}, d423
 * 		date eg. 2016-1-1, ${cal.date}
 * 		double eg. 2.4, ${floatVal}
 * 		object eg. ${obj1}, ${obj2}
 * attributes :
 * 		id : list 对象标识
 * 		type : [int, string, double, date, object]
 * 		clear : [true, false]		
 * 		{
 * 			json : 二维 JSONArray EL Object
 * 			col	: 数字，指定装载 JSONArray 第几列
 * 		}
 *		{
 *			sql : sql 返回结果对象
 *			value : 指定装载sql第几列
 *			{
 *				order : 排序被参照的list
 *				by : 指定sql中参照列
 *			}
 *		}
 *
 **************** sub nodes*************
 * 	tagName : item
 * 	attributes :
 * 		concat : 指定要连接的 list id
 * 		insert : 元素插入位置
 * 		index : 指定替换元素索引
 * ************************************/
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
				if (sqlRet.get(j).length > by && sqlRet.get(j).length > index){
					if (order.get(i).equals(sqlRet.get(j)[by])){
						objs.set(objs.size() - 1, sqlRet.get(j)[index]);
						break;
					}
				}
			}
		}
	}

	private void injectFromSql(List<Object> objs, List<Object[]> sqlRet, int index) {
		for (int i = 0; i < sqlRet.size(); ++i){
			if (sqlRet.get(i).length > index){
				objs.add(sqlRet.get(i)[index]);
			}
		}
	}
	
	private void parseSql(AbstractXmlComponent component, Element e, List<Object> objs) throws Exception{
		List sqlRet = (List) component.getVar(e.getAttribute("sql"));
		if (null != sqlRet){
			if (!sqlRet.isEmpty()){
				if (sqlRet.get(0).getClass().isArray()){
					int index = XmlUtil.getIntAttr(e, "value", elp, 0);
					List order = (List) component.getVar(e.getAttribute("order"));
					if (null != order){
						int by = XmlUtil.getIntAttr(e, "by", elp, 0);
						injectFromSql(objs, sqlRet, index, order, by);
					}else{
						injectFromSql(objs, sqlRet, index);
					}
				}	
			}else{
				List order = (List) component.getVar(e.getAttribute("order"));
				if (null != order){
					int by = XmlUtil.getIntAttr(e, "by", elp, 0);
					injectFromSql(objs, sqlRet, 0, order, by);
				}
			}
		}
	}
	
	ELParser elp;
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		boolean bRet= Schema.isList(e);
		if (bRet) {
			elp = new ELParser(component);
			List<Object> objs = (List<Object>) component.getVar(XmlUtil.getAttr(e, "id"));
			if (null == objs || "true".equals(e.getAttribute("clear"))){
				objs = new ArrayList<Object>();
			}
			
			
			parseJson(component, e, objs);
			
			parseSql(component, e, objs);
			
			parseItems(component, e, objs);
			
			component.put(e, objs);
		}
		return bRet;
	}

	private void parseJson(AbstractXmlComponent component, Element e,
			List<Object> objs) {
		if (e.hasAttribute("json") && e.hasAttribute("col")){
			Object jarr;
			try {
				jarr = XmlUtil.parseELText(e.getAttribute("json"), elp);
				Integer col = XmlUtil.getIntAttr(e, "col", elp, null);
				if (jarr instanceof JSONArray && col != null){
					JSONArray jrow = ((JSONArray)jarr).getJSONArray(col);
					for (int i = 0; i < jrow.size(); ++i){
						objs.add(jrow.get(i));
					}
				}else{
					System.out.println(e.getAttribute("id") + " parseJson failed");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}

	
	private void parseItems(AbstractXmlComponent component, Element e, List<Object> objs) throws Exception {
		//NodeList children = e.getChildNodes();
		int type = TypeUtil.typeof(e);
		if (XmlUtil.hasText(e)){
			String text = XmlUtil.getText(e);
			if (TypeUtil.STRING == type){
				objs.addAll(XmlUtil.toStringList(text, elp));
			} else if (TypeUtil.INT == type){
				objs.addAll(XmlUtil.toIntList(text, elp));
			} else if (TypeUtil.DOUBLE == type){
				objs.addAll(XmlUtil.toDoubleList(text, elp));
			} else if (TypeUtil.OBJECT == type){
				objs.addAll(XmlUtil.toObjectList(text, elp));
			} else if (TypeUtil.SQLDATE == type){
				objs.addAll(XmlUtil.toDateList(text, elp));
			}   
		}
		
		XmlWalker.eachChildren(e, elp, new Loop(){
			@Override
			public void on(Element elem) throws Exception {
				if (elem.getTagName().equals("item")) {
					int repeat = XmlUtil.getIntAttr(elem, "repeat", elp, 1);
					int insert = XmlUtil.getIntAttr(elem, "insert", elp, objs.size());
					int index = XmlUtil.getIntAttr(elem, "index", elp, -1);
					if (concat(component, elem, objs, repeat, insert)) {
						return;
					}
					
					if (increase(component, elem, objs, repeat, insert)){
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

	protected boolean increase(AbstractXmlComponent component, Element elem,
			List<Object> objs, int repeat, int insert) throws Exception {
		Integer from = XmlUtil.getIntAttr(elem, "from", elp,null);
		Integer to = XmlUtil.getIntAttr(elem, "to", elp,null);
		if (null != from && null != to){
			if (to >= from){
				String print = elem.getAttribute("print");
				List list = new ArrayList<Object>(to - from + 1);
				if (print.isEmpty()){
					for(int i = from; i <= to; ++i){
						list.add(i);
					}
				}else{
					for(int i = from; i <= to; ++i){
						list.add(print.replace(":i", "" + i));
					}
				}
				repeatAddList(objs, list, repeat, insert);
			}
			return true;
		}
		return false;
	}

	private void replaceAdd(Element item, List<Object> objs, int repeat, int index,
			int type) throws Exception {
		objs.remove(index);
		insertAdd(item, objs, repeat, index, type);
	}
	
	private void insertAdd(Element item, List<Object> objs, int repeat, int insert,
			int type) throws Exception {
		String val = "";
		if (XmlUtil.hasText(item)){
			val = XmlUtil.getText(item);
		}
		if (type == TypeUtil.INT) {
			if (val.isEmpty()) {
				repeatAdd(objs, null, repeat, insert);
			} else {
				repeatAdd(objs, XmlUtil.getInt(val, elp, null), repeat, insert);
			}
		} else if (type == TypeUtil.DOUBLE) {
			if (val.isEmpty()) {
				repeatAdd(objs, null, repeat, insert);
			} else {
				repeatAdd(objs, XmlUtil.getDouble(val, elp, null), repeat, insert);
			}
		} else if (type == TypeUtil.STRING) {
			repeatAdd(objs, XmlUtil.getString(val, elp), repeat, insert);
		} else if (type == TypeUtil.OBJECT) {
			repeatAdd(objs, XmlUtil.parseELText(val, elp), repeat, insert);
		} else if (type == TypeUtil.SQLDATE) {
			repeatAdd(objs, XmlUtil.getDate(val, elp), repeat, insert);
		}
	}
	
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
		String concat = XmlUtil.getAttr(item, "concat");
		if (null != concat){
			List list = (List) component.getVar(concat);
			if (null != list) {
				repeatAddList(objs, list, repeat, insert);
				return true;
			}else{
				System.out.println("list concat " + concat + " object cannot be found");
			}
		}
		
		return false;
	}
}