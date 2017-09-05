package com.xml.frame.report.util.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.frame.script.el.ELExpression;
import com.frame.script.el.ELParser;
import com.frame.script.util.StringUtil;
import com.frame.script.util.TypeUtil;

public class XmlUtil {
	
	public static List<Integer> toIntList(String text, ELParser elp) throws Exception{
		if(null == text){
			return null;
		}
		text = StringUtil.shrink(text);
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
	
	
	public static Set<Integer> toIntSet(String text, ELParser elp) throws Exception{
		if(null == text){
			return null;
		}
		text = StringUtil.shrink(text);
		Set<Integer> list = new HashSet<Integer>();
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
		text = StringUtil.shrink(text);
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

		text = StringUtil.shrink(text);
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
		List<ELExpression> elexps = elp.parser(el);
		if (elexps.isEmpty()) {
			return el;
		} else if (elexps.size() == 1){
			ELExpression exp = elexps.get(0);
			
			if (exp.end() == el.length()){
				return exp.value();
			}else{
				el = StringUtil.trim(el);
				if (exp.end() == el.length()){
					return exp.value();
				}
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
					val = Double.valueOf(((String)val).replaceAll("\\s+", ""));
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
					val = Integer.valueOf(((String)val).replaceAll("\\s+", ""));
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

	public static String toStringFromDoc(Element elem) {
		String result = null;

		StringWriter strWtr = new StringWriter();
		StreamResult strResult = new StreamResult(strWtr);
		TransformerFactory tfac = TransformerFactory.newInstance();
		try {
			javax.xml.transform.Transformer t = tfac.newTransformer();
			t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty(OutputKeys.METHOD, "xml"); // xml, html,
			// text
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			t.transform(new DOMSource(elem), strResult);
		} catch (Exception e) {
			System.err.println("XML.toString(Document): " + e);
		}
		result = strResult.getWriter().toString();
		try {
			strWtr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	

	public static Boolean getBoolean(String attribute, ELParser elParser) throws Exception {
		Boolean bRet = false;
		Object ret = parseELText(attribute, elParser);
		if (null != ret){
			if (TypeUtil.isBoolean(ret.getClass())){
				bRet = (Boolean) ret;
			}else if (TypeUtil.isString(ret.getClass())){
				bRet = Boolean.valueOf(((String)ret).replaceAll("\\s+", ""));
			}
		}
		return bRet;
	}

	public static List<Object> toObjectList(String text,
			ELParser elp) throws Exception {
		if(null == text){
			return null;
		}
		text = StringUtil.shrink(text);
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
				return Date.valueOf(((String)val).replaceAll("\\s+", ""));
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
		text = StringUtil.shrink(text);
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

	public static boolean hasText(Element e) {
		if (e.getFirstChild() != null){
			return e.getFirstChild().getNodeType() == Node.TEXT_NODE;
		}
		return false;
	}

	public static String getText(Element e) {
		if (null != e) {
			String text = "";
			Node node = e.getFirstChild();
			while (node != null){
				if (node.getNodeType() == Node.TEXT_NODE){
					text += node.getNodeValue();
				}
				node = node.getNextSibling();
			}
			return text;
		}
		return null;
	}

	public static void copyAttr(Element from, Element to) {
		NamedNodeMap nnm = from.getAttributes();
		for (int i = 0; i < nnm.getLength(); ++i){
			String name = nnm.item(i).getNodeName();
			if (!to.hasAttribute(name)){
				String value = nnm.item(i).getNodeValue();
				to.setAttribute(name, value);
			}
		}
	}
}
