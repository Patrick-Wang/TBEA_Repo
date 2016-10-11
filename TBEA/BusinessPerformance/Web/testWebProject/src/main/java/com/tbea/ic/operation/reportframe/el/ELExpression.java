package com.tbea.ic.operation.reportframe.el;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tbea.ic.operation.reportframe.el.ELParser.ObjectLoader;
import com.tbea.ic.operation.reportframe.util.StringUtil;
import com.tbea.ic.operation.reportframe.util.TypeUtil;

public class ELExpression{
	
	static final String namePtn = "[a-zA-Z][a-zA-Z0-9]*";
	static final String indexPtn = "\\[[^\\[]+\\]";
	
	static final Pattern expPattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*(\\[[^\\[]+\\])*(\\.[a-zA-Z][a-zA-Z0-9]*(\\[[^\\[]+\\])*)*");  
	static final Pattern indexesPattern = Pattern.compile("(\\[[^\\[]+\\])+"); 
	static final Pattern indexedObjectPattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*(\\[[^\\[]+\\])*"); 
	static final Set<String> jsKeyWords = new HashSet<String>();
	
	static{
		jsKeyWords.add("indexOf");
		jsKeyWords.add("true");
		jsKeyWords.add("false");
	}
	
	int start;
	int end;
	String expression;
	ObjectLoader loader;
	static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
	public ELExpression(int start, int end, String expression,
			ObjectLoader loader) {
		super();
		this.start = start;
		this.end = end;
		this.expression = expression;
		this.loader = loader;
	}
	
	public int start(){
		return start;
	}
	
	public int end(){
		return end;
	}
	
	private String makeGetMethodName(String propName){
		String methodName = null;
		if (!propName.isEmpty()){
			if (propName.length() == 1){
				methodName = "get" + propName.toUpperCase();
			}else{
				methodName = "get" + propName.substring(0, 1).toUpperCase() + propName.substring(1);
			}
		}
		return methodName;
	}
	
	private Method getMethod(Object obj, String name) {
		try {
			return obj.getClass().getMethod(name);
		} catch (NoSuchMethodException | SecurityException e) {
		}
		return null;
	}
	
	
	
	private Object getObjectProp(Object obj, String propName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method md = getMethod(obj, propName);
		Object result;
		if (null == md){
			String getMdName = makeGetMethodName(propName);
			md = getMethod(obj, getMdName);
			if (null == md){
				result = new PackingMap(obj).get(propName);
				if (null == result){
					result = new PackingMap(obj).get(getMdName);
				}
			}else{
				result = md.invoke(obj);
			}
		}else{
			result = md.invoke(obj);
		}
		return result;
	}
	
	private Object getPropByIndex(Object obj, List<Object> indexs) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (int i = 0; i < indexs.size() && null != obj; ++i){
			if (obj instanceof List){
				obj = ((List) obj).get((Integer) indexs.get(i));
			}else if (obj.getClass().isArray()){
				obj = ((Object[])obj)[(Integer)indexs.get(i)];
			}else if (obj instanceof JSONArray){
				obj = ((JSONArray) obj).get((Integer)indexs.get(i));
			}else if (obj instanceof JSONObject){
				JSONObject jsonObj = (JSONObject) obj;
	 			obj = jsonObj.get(indexs.get(i));
			}else if (obj instanceof Map){
				obj = ((Map) obj).get(indexs.get(i));
			}else if (null != indexs.get(i) && TypeUtil.isString(indexs.get(i).getClass())){
				obj = getObjectProp(obj, (String)indexs.get(i));
			}
		}
		return obj;
	}

	private Object getPropByName(Object obj, String propName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Object propValue = null;
		if (null != obj){
			if (obj instanceof JSONObject){
				JSONObject jsonObj = (JSONObject) obj;
	 			propValue = jsonObj.get(propName);
			}else if (obj instanceof Map){
				propValue = ((Map) obj).get(propName);
			}else if ("packAsList".equals(propName)){
				List list = new ArrayList();
				list.add(obj);
	 			propValue = list;
			}else { 
				propValue = getObjectProp(obj, propName);
			}
		}
		return propValue;
	}
	
	private boolean isNumber(Object ob){
		return ob instanceof Integer || ob instanceof Double;  
	}
	
	private boolean isString(Object ob){
		return ob instanceof String;  
	}

	public Object value() throws Exception{
		Matcher matcher = expPattern.matcher(expression);
		String expressTmp = expression;
		int offset = 0;
		boolean isObject = false;
		Object obj = null;
		while (matcher.find()){
			try{
				obj = parseObject(matcher.group());
				if (null == obj || isNumber(obj) || isString(obj) || TypeUtil.isBoolean(obj.getClass())){
					String objVal = expressTmp.substring(0, offset + matcher.start()) + obj;
					expressTmp = objVal + expressTmp.substring(offset + matcher.end());
					offset = objVal.length();
					matcher = expPattern.matcher(expressTmp.substring(offset));
				}else{
					isObject = true;
					break;
				}
			}catch(ELInitObjectNotExist e){
				if (!jsKeyWords.contains(e.getMessage())){
					e.printStackTrace();
				}
			}
		}

		if (!isObject && !expressTmp.equals(obj)){
			obj = jse.eval(expressTmp);
			if (null != obj &&
				!TypeUtil.isDouble(obj.getClass()) &&
				!TypeUtil.isInt(obj.getClass()) &&
				!TypeUtil.isBoolean(obj.getClass()) &&
				!TypeUtil.isString(obj.getClass())){
				return expressTmp;
			}
		}
		return obj;
	}

	List<Object> getIndexs(String indexs) throws Exception{
		List<Object> indxs = new ArrayList<Object>();
		int start = 0;
		int end = indexs.indexOf(']', start);
		String val;
		while (end >= 0){
			val = StringUtil.trim(indexs.substring(start + 1, end));
			start = end + 1;
			end = indexs.indexOf(']', start);
			Object index = TypeUtil.asInt(val);
			if (null != index){
				indxs.add(index);
				continue;
			}
			index = TypeUtil.asBoolean(val);
			if (null != index){
				indxs.add(index);
				continue;
			}
			index = TypeUtil.asString(val);
			if (null != index){
				indxs.add(index);
				continue;
			}
			
			if (null == index){
				ELExpression exp = new ELExpression(0, val.length(), val, this.loader);
				indxs.add(exp.value());
			}
		}
		return indxs;
	}
	
//	private int nextIndexedObject(String exp, int start){
//		int len = exp.length();
//		if (start >= len){
//			return -1;
//		}
//		
//		int iIndex = -1;
//		for (int i = start; i < len; ++i){
//			if (exp.charAt(i) == '.'){
//				return i;
//			}else if (exp.charAt(i) == '['){
//				iIndex = i;
//				break;
//			}
//		}
//		
//		if (iIndex == -1){
//			return len;
//		}else{
//			iIndex = exp.indexOf(']', iIndex);
//			while ((iIndex + 1) < len && exp.charAt(iIndex + 1) == '['){
//				iIndex = exp.indexOf(']', iIndex);
//			}
//			return iIndex + 1;
//		}
//	}
	
	private Object parseObject(String exp) throws 
			Exception {
		Matcher indexedObjMatcher = indexedObjectPattern.matcher(exp);
		Object obj = null;
//		System.out.println("EL : " + exp);
		while(indexedObjMatcher.find()){
			String objExp = indexedObjMatcher.group();
//			System.out.println("obj : " + objExp);
			Matcher indexesMatcher = indexesPattern.matcher(objExp);
			if (obj == null){
				if (indexesMatcher.find()){
					obj = loader.onGetObject(objExp.substring(0, indexesMatcher.start()));
					if (null == obj){
						throw new ELInitObjectNotExist(objExp.substring(0, indexesMatcher.start()));
					}
					obj = getPropByIndex(obj, getIndexs(indexesMatcher.group()));
				}else{
					obj = loader.onGetObject(objExp);
					if (null == obj){
						throw new ELInitObjectNotExist(objExp);
					}
				}
			}else{
				if (indexesMatcher.find()){
					obj = getPropByName(obj, objExp.substring(0, indexesMatcher.start()));
					obj = getPropByIndex(obj, getIndexs(indexesMatcher.group()));
				}else{
					obj = getPropByName(obj, objExp);
				}
			}
//			System.out.println(obj);
		}
		return obj;
	}
}