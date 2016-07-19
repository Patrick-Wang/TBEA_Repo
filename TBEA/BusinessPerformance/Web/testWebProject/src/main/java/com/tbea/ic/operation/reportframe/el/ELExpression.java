package com.tbea.ic.operation.reportframe.el;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tbea.ic.operation.common.EasyCalendar.Days;
import com.tbea.ic.operation.common.EasyCalendar.Months;
import com.tbea.ic.operation.reportframe.component.controller.ControllerRequest;
import com.tbea.ic.operation.reportframe.component.controller.ControllerSession;
import com.tbea.ic.operation.reportframe.el.ELParser.ObjectLoader;

public class ELExpression{
	static final Pattern namePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*(\\.[a-zA-Z][a-zA-Z0-9]*(\\[[0-9]+\\])*)*");  
	static final Pattern indexsPattern = Pattern.compile("\\[\\S*\\]"); 
	static final Pattern indexPattern = Pattern.compile("[0-9]+");
	int start;
	int end;
	String express;
	ObjectLoader loader;
	static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
	public ELExpression(int start, int end, String express,
			ObjectLoader loader) {
		super();
		this.start = start;
		this.end = end;
		this.express = express;
		this.loader = loader;
	}
	
	public int start(){
		return start;
	}
	
	public int end(){
		return end;
	}
	
	private Method getMethod(Object obj, String name) {
		try {
			return obj.getClass().getMethod(name);
		} catch (NoSuchMethodException | SecurityException e) {
		}
		return null;
	}
	
	private Object Invoke(Object obj, Method md) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return md.invoke(obj);
	}
	
	private Object getProperty(Object obj, String propName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Object propValue = null;
		if (obj instanceof ControllerRequest){
			ControllerRequest request = (ControllerRequest) obj;
 			propValue = request.getParameter(propName);
		}else if (obj instanceof ControllerSession){
			ControllerSession session = (ControllerSession) obj;
 			propValue = session.getAttribute(propName);
		}else if (obj instanceof JSONObject){
			JSONObject jsonObj = (JSONObject) obj;
 			propValue = jsonObj.get(propName);
		}else if ("packAsList".equals(propName)){
			List list = new ArrayList();
			list.add(obj);
 			propValue = list;
		}else {
			Method md = getMethod(obj, propName);
			if (null == md){
				md = getMethod(obj, "get" + propName.substring(0, 1).toUpperCase() + propName.substring(1));
			}
			
			if (null != md){
				propValue = Invoke(obj, md);
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
		Matcher matcher = namePattern.matcher(express);
		String expressTmp = express;
		while (matcher.find()){
			Object obj = parseObject(matcher.group());
			if (isNumber(obj)){
				expressTmp = expressTmp.substring(0, matcher.start()) + obj + expressTmp.substring(matcher.end());
				matcher = namePattern.matcher(expressTmp);
			}else if (isString(obj)){
				expressTmp = expressTmp.substring(0, matcher.start()) + "'" + obj + "'" + expressTmp.substring(matcher.end());
				matcher = namePattern.matcher(expressTmp);
			}else{
				return obj;
			}
		}
		try {
			return jse.eval(expressTmp);
		} catch (ScriptException e) {
			e.printStackTrace();
		}	
		return null;
	}

	List<Integer> getIndexs(String indexs){
		List<Integer> indxs = new ArrayList<Integer>();
		Matcher mc = indexPattern.matcher(indexs);
		while (mc.find()){
			indxs.add(Integer.valueOf(mc.group()));
		}
		return indxs;
	}
	
	private Object parseObject(String exp) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String[] exps = exp.split("\\.");
		Object obj = null;		
		Matcher mc = indexsPattern.matcher(exps[0]);
		if (mc.find()){
			obj = loader.onGetObject(exps[0].substring(0, mc.start()));
			obj = getProperty(obj, getIndexs(mc.group()));
		}else{
			obj = loader.onGetObject(exps[0]);
		}
		
		for (int i = 1; i < exps.length; ++i){
			if (obj == null){
				System.out.println("EL : " + exp);
				System.out.println(exps[i - 1] + " is null object");
			}
			mc = indexsPattern.matcher(exps[i]);
			if (mc.find()){
				obj = getProperty(obj, exps[i].substring(0, mc.start()));
				obj = getProperty(obj, getIndexs(mc.group()));
			}else{
				obj = getProperty(obj, exps[i]);
			}
		}
		
		
		if (obj == null){
			System.out.println("EL : " + exp);
			System.out.println(exps[exps.length - 1] + " is null object");
		}
		
		return obj;
	}

	private Object getProperty(Object obj, List<Integer> indexs) {
		Object propValue = null; 
		for (int i = 0; i < indexs.size(); ++i){
			if (obj instanceof List){
	 			propValue = ((List) obj).get(indexs.get(i));
			}else if (obj.getClass().isArray()){
	 			propValue = ((Object[])obj)[indexs.get(i)];
			}else if (obj instanceof JSONArray){
				propValue = ((JSONArray) obj).get(indexs.get(i));
			}else if (obj instanceof Days){
				propValue = ((Days) obj).getDay(indexs.get(i));
			}else if (obj instanceof Months){
				propValue = ((Months) obj).getMonth(indexs.get(i));
			}
			obj = propValue;
		}
		return propValue;
	}
}