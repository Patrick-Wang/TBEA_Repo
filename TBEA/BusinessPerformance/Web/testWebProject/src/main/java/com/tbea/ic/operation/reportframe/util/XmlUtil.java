package com.tbea.ic.operation.reportframe.util;

import java.util.ArrayList;
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
	
	public static Object getELValue(String el, ELParser elp) throws Exception{
		List<ELExpression> elexps = elp.parser(el);
		if (!elexps.isEmpty()) {
			return elexps.get(0).value();
		}
		return null;
	}
	
	public static String getString(String text, ELParser elParser) throws Exception {
		Object val = getELValue(text, elParser);
		if (val == null){
			val = text;
		}else{
			val = "" + val;
		}
		return (String) val;
	}
	
	public static Double getDouble(String text, ELParser elParser,
			Double defaultVal) throws Exception {
		Object val = getELValue(text, elParser);
		if (val == null){
			try{
				val = Double.valueOf(text);
			}catch(NumberFormatException e){
				val = defaultVal;
			}
		}else{
			if (val instanceof Integer){
				val = ((Integer) val).doubleValue();
			}
		}
		return (Double) val;
	}
	
	public static Integer getInt(String text, ELParser elParser,
			Integer defaultVal) throws Exception {
		Object val = getELValue(text, elParser);
		if (val == null){
			try{
				val = Integer.valueOf(text);
			}catch(NumberFormatException e){
				val = defaultVal;
			}
		}else{
			if (val instanceof Double){
				val = ((Double) val).intValue();
			}
		}
		return (Integer) val;
	}
	
	public static Object getObjectAttr(Element elem, String attr, ELParser elParser) throws Exception {
		Object obj = getELValue(elem.getAttribute(attr), elParser);
		if (obj == null){
			obj = getAttr(elem, attr);
		}
		return obj;
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
}
