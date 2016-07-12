package com.tbea.ic.operation.reportframe.el;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.tbea.ic.operation.reportframe.component.controller.ControllerRequest;
import com.tbea.ic.operation.reportframe.component.controller.ControllerSession;
import com.tbea.ic.operation.reportframe.el.ELParser.ObjectLoader;

public class ELExpression{
	static final Pattern namePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*(\\.[a-zA-Z][a-zA-Z0-9]*)*");   
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
		}else{
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

	public Object value() throws Exception{
		Matcher matcher = namePattern.matcher(express);
		String expressTmp = express;
		while (matcher.find()){
			Object obj = parseObject(matcher.group());
			if (isNumber(obj)){
				expressTmp = expressTmp.substring(0, matcher.start()) + obj + expressTmp.substring(matcher.end());
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

	private Object parseObject(String exp) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String[] exps = exp.split("\\.");
		Object obj = loader.onGetObject(exps[0]);
		for (int i = 1; i < exps.length; ++i){
			if (obj == null){
				System.out.println("EL : " + exp);
				System.out.println(exps[i - 1] + " is null object");
			}
			obj = getProperty(obj, exps[i]);
		}
		
		if (obj == null){
			System.out.println("EL : " + exp);
			System.out.println(exps[exps.length - 1] + " is null object");
		}
		
		return obj;
	}
}