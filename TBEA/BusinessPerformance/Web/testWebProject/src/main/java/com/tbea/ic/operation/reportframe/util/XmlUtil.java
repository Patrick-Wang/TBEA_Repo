package com.tbea.ic.operation.reportframe.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.reportframe.el.ELExpression;
import com.tbea.ic.operation.reportframe.el.ELParser;

public class XmlUtil {
	
	public static interface OnEach{
		boolean on(Element elem) throws Exception;
	}
	
	public static interface OnLoop{
		void on(Element elem) throws Exception;
	}
	
	public static String elementText(NodeList list, int index) {
		Element e = element(list, index);
		if (null != e){
			return e.getFirstChild().getTextContent();
		}
		return "";
	}
	
	public static Element element(NodeList list, int index) {
		try {
			return each(list, new OnEach(){
				int count;
				@Override
				public boolean on(Element elem) {
					if (count++ == index){
						return true;
					}
					return false;
				}
			});
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Element element(NodeList list, String tagName) {
		try {
			return each(list, new OnEach(){
				@Override
				public boolean on(Element elem) {
					if (elem.getTagName().equals(tagName)){
						return true;
					}
					return false;
				}
			});
		} catch (Exception e) {
			return null;
		}
	}
	
	public static List<Integer> toIntList(String text, ELParser elp) throws Exception{
		if(null == text){
			return null;
		}
		text = text.replaceAll("\\s", "");
		List<Integer> list = new ArrayList<Integer>();
		if (!text.isEmpty()){
			String[] items = text.split(",");
			Integer val = null;
			for (String item : items){
				val = getInt(item, elp, null);
				if (null != val){
					list.add(val);
				}
			}
		}
		return list;
	}
	
	public static List<Double> toDoubleList(String text, ELParser elp) throws Exception{
		if(null == text){
			return null;
		}
		text = text.replaceAll("\\s", "");
		List<Double> list = new ArrayList<Double>();
		if (!text.isEmpty()){
			String[] items = text.split(",");
			Double val = null;
			for (String item : items){
				val = getDouble(item, elp, null);
				if (null != val){
					list.add(val);
				}
			}
		}
		return list;
	}
	
	public static List<String> toStringList(String text, ELParser elp) throws Exception{
		if(null == text){
			return null;
		}

		text = text.replaceAll("\\s", "");
		List<String> list = new ArrayList<String>();
		if (!text.isEmpty()){
			String[] items = text.split(",");
			for (String item : items){
				if (!item.isEmpty()){
					list.add(getString(item, elp));
				}
			}
		}
		return list;
	}
	
	public static Object parseELText(String el, ELParser elp) throws Exception{
		el = el.trim();
		List<ELExpression> elexps = elp.parser(el);
		if (elexps.isEmpty()) {
			return el;
		} else if (elexps.size() == 1){
			ELExpression exp = elexps.get(0);
			if (exp.end() == el.length()){
				return exp.value();
			}else{
				return el.substring(0, exp.start()) + exp.value() + el.substring(exp.end());
			}
		} else{
			Object objTmp = null;
			String strRet = el;
			ELExpression exp = null;
			for (int i = elexps.size() - 1; i >= 0; --i){
				exp = elexps.get(i);
				objTmp = exp.value();
				if (!TypeUtil.isDouble(objTmp.getClass()) &&
					!TypeUtil.isInt(objTmp.getClass()) &&
					!TypeUtil.isString(objTmp.getClass())){
					strRet = null;
					break;
				}else{
					strRet = strRet.substring(0, exp.start()) + objTmp + strRet.substring(exp.end());
				}
			}
			return strRet;
		}
	}
	
	public static String getString(String text, ELParser elParser) throws Exception {
		Object val = parseELText(text, elParser);
		if (val == null){
			val = text;
		}else{
			val = "" + val;
		}
		return (String) val;
	}
	
	public static Double getDouble(String text, ELParser elParser,
			Double defaultVal) throws Exception {
		Object val = parseELText(text, elParser);
		if (val != null){
			if (TypeUtil.isInt(val.getClass())){
				val = ((Integer) val).doubleValue();
			}else if (TypeUtil.isString(val.getClass())){
				try{
					val = Double.valueOf(text);
				}catch(NumberFormatException e){
					val = defaultVal;
				}
			}else if (!TypeUtil.isDouble(val.getClass())){
				val = defaultVal;
			}
		}else{
			val = defaultVal;
		}
		return (Double) val;
	}
	
	public static Integer getInt(String text, ELParser elParser,
			Integer defaultVal) throws Exception {
		Object val = parseELText(text, elParser);
		if (val != null){
			if (TypeUtil.isDouble(val.getClass())){
				val = ((Double) val).intValue();
			}else if (TypeUtil.isString(val.getClass())){
				try{
					val = Integer.valueOf(text);
				}catch(NumberFormatException e){
					val = defaultVal;
				}
			}else if (!TypeUtil.isInt(val.getClass())){
				val = defaultVal;
			}
		}else{
			val = defaultVal;
		}
		return (Integer) val;
	}
	
	public static Object getObjectAttr(Element elem, String attr, ELParser elParser) throws Exception {
		if (elem.hasAttribute(attr)){
			return parseELText(elem.getAttribute(attr), elParser);
		}
		return null;
	}
	
	public static Integer getIntAttr(Element elem, String attr, ELParser elParser,
			Integer defaultVal) throws Exception {
		return getInt(elem.getAttribute(attr), elParser, defaultVal);
	}
	
	public static String getAttr(Element e, String attr){
		if (e.hasAttribute(attr)){
			return e.getAttribute(attr);
		}
		return null;
	}
	
	public static void each(NodeList list, OnLoop onLoop) throws Exception{
		Node node = null;
		for (int i = 0, len = list.getLength(); i < len; ++i){
			node = list.item(i);
			if (node instanceof Element){
				onLoop.on((Element) node);
			}
		}
	}
	
	public static Element each(NodeList list, OnEach onEach) throws Exception{
		Node node = null;
		for (int i = 0, len = list.getLength(); i < len; ++i){
			node = list.item(i);
			if (node instanceof Element){
				if (onEach.on((Element) node)){
					return (Element)node;
				}
			}
		}
		return null;
	}

	public static Boolean getBoolean(String attribute, ELParser elParser) throws Exception {
		Boolean bRet = false;
		Object ret = parseELText(attribute, elParser);
		if (null != ret){
			if (TypeUtil.isBoolean(ret.getClass())){
				bRet = (Boolean) ret;
			}else if (TypeUtil.isString(ret.getClass())){
				bRet = Boolean.valueOf((String)ret);
			}
		}
		return bRet;
	}

	public static List<Object> toObjectList(String text,
			ELParser elp) throws Exception {
		if(null == text){
			return null;
		}
		text = text.replaceAll("\\s", "");
		List<Object> list = new ArrayList<Object>();
		if (!text.isEmpty()){
			String[] items = text.split(",");
			Object val = null;
			for (String item : items){
				val = parseELText(item, elp);
				if (null != val){
					list.add(val);
				}
			}
		}
		return list;
	}

	public static Date getDate(String text,
			ELParser elp) throws Exception{
		Object val = parseELText(text, elp);
		if (null != val){
			if (TypeUtil.isString(val.getClass())){
				return Date.valueOf((String)val);
			}else if(TypeUtil.isDate(val.getClass())){
				return (Date) val;
			}else{
				throw new Exception(val.toString() + "is not a type of Date");
			}
		}
		return null;
	}
	
	public static List<Date> toDateList(String text,
			ELParser elp) throws Exception {
		if(null == text){
			return null;
		}
		text = text.replaceAll("\\s", "");
		List<Date> list = new ArrayList<Date>();
		if (!text.isEmpty()){
			String[] items = text.split(",");
			Date val = null;
			for (String item : items){
				val = getDate(item, elp);
				if (null != val){
					list.add(val);
				}
			}
		}
		return list;
	}
}
