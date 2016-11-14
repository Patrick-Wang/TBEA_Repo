package com.tbea.ic.operation.reportframe.el;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tbea.ic.operation.reportframe.el.ELParser.ObjectLoader;
import com.tbea.ic.operation.reportframe.el.querier.ExpressionQuerier;
import com.tbea.ic.operation.reportframe.el.querier.IndexQuerier;
import com.tbea.ic.operation.reportframe.el.querier.ObjectQuerier;
import com.tbea.ic.operation.reportframe.el.querier.Querier;
import com.tbea.ic.operation.reportframe.util.StringUtil;
import com.tbea.ic.operation.reportframe.util.TypeUtil;

public class ELExpression{
	
	static final Set<String> jsKeyWords = new HashSet<String>();
	static final Pattern varPattern = Pattern.compile("var\\s+"); 
	
	static{
		jsKeyWords.add("indexOf");
		jsKeyWords.add("true");
		jsKeyWords.add("false");
		jsKeyWords.add("null");
		jsKeyWords.add("undefined");
		jsKeyWords.add("var");
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
	
	public String exp(){
		return expression;
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
		return ob instanceof Integer || 
				ob instanceof Long || 
				ob instanceof Double || 
				ob instanceof Float;
	}
	
	private boolean isString(Object ob){
		return ob instanceof String;  
	}

	//user.take[a.b[]] + user.walk
	public Object value() throws Exception{
		
		String expressTmp = expression;
		int offset = 0;
		boolean isObject = false;
		Object obj = null;
		Querier querier = new ExpressionQuerier(expression);
		while (querier.hasNext()){
			try{
				obj = parseObject(querier.next());
				if (null == obj || isNumber(obj) || isString(obj) || TypeUtil.isBoolean(obj.getClass())){
					String objVal = expressTmp.substring(0, querier.start()) + obj;
					expressTmp = objVal + expressTmp.substring(querier.end());
					offset = objVal.length();
					querier.reset(expressTmp, offset);
				}else{
					isObject = true;
					break;
				}
			}catch(ELInitObjectNotExist e){
				if (!jsKeyWords.contains(e.getMessage())){
					if (!Pattern.compile("var\\s+" + e.getMessage()).matcher(expression).find()){
						e.printStackTrace();
					}					
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
				
				if (TypeUtil.isLong(obj.getClass())){
					return ((Long)obj).intValue();
				}
				
				return expressTmp;
			}
		}
		return obj;
	}

	List<Object> getIndexs(String indexs) throws Exception{
		List<Object> indxs = new ArrayList<Object>();
		Querier querier = new IndexQuerier(indexs);
		while(querier.hasNext()){
			String val = StringUtil.trim(querier.next());
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
			
			ELExpression exp = new ELExpression(0, val.length(), val, this.loader);
			indxs.add(exp.value());
		}
		return indxs;
	}
	
	private Object loadStartObject(String objExp)
			throws  Exception {
		Object ret = null;
		int index = objExp.indexOf('[');
		String objName = objExp;
		if (index > 0){
			objName = objExp.substring(0, index);
			if (!loader.hasObject(objName)){
				throw new ELInitObjectNotExist(objName);
			}
			ret = loader.onGetObject(objName);
			ret = getPropByIndex(ret, getIndexs(objExp.substring(index)));
		}else{
			if (!loader.hasObject(objName)){
				throw new ELInitObjectNotExist(objName);
			}
			ret = loader.onGetObject(objName);
		}
		return ret;
	}
	
	private Object parseObject(String exp) throws 
			Exception {
		Querier querier = new ObjectQuerier(exp);
		Object obj = null;
		
		if (querier.hasNext()){
			 obj = loadStartObject(querier.next());
		}
		
		String objExp = null;
		int index = -1;
		while(querier.hasNext()){
			objExp = querier.next();
			index = objExp.indexOf('[');
			if (index > 0){
				obj = getPropByName(obj,  StringUtil.trim(objExp.substring(0, index)));
				obj = getPropByIndex(obj, getIndexs(objExp.substring(index)));
			}else{
				obj = getPropByName(obj, objExp);
			}
		}
		return obj;
	}
}