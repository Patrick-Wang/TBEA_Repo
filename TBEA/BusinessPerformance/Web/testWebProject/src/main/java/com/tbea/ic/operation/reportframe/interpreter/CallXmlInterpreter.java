package com.tbea.ic.operation.reportframe.interpreter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.ReportLogger;
import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.TypeUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil.OnLoop;


public class CallXmlInterpreter implements XmlInterpreter {	
	
	ELParser elp;
	
	private void checkUnmatchedMethod(List<Method> mdList, int index, int tp, Object paramObj){
		for (int i = mdList.size() - 1; i >= 0; --i){
			if (mdList.get(i).getParameterCount() > index){
				//System.out.println(mdList.get(i).getParameterTypes()[index].getName());
				switch (tp){
				case TypeUtil.DOUBLE:
					if (!TypeUtil.isDouble(mdList.get(i).getParameterTypes()[index])){
						mdList.remove(i);
					}
					break;
				case TypeUtil.INT:
					if (!TypeUtil.isInt(mdList.get(i).getParameterTypes()[index])){
						mdList.remove(i);
					}
					break;
				case TypeUtil.STRING:
					if (!TypeUtil.isString(mdList.get(i).getParameterTypes()[index])){
						mdList.remove(i);
					}
					break;
				case TypeUtil.SQLDATE:
				case TypeUtil.OBJECT:
					if (paramObj != null){
						//System.out.println(paramObj.getClass());
						if (!TypeUtil.instanceOf(paramObj, mdList.get(i).getParameterTypes()[index])){
							mdList.remove(i);
						};
					}
					break;
				}
			}
		}
	}
	
	private Method parseParams(Element e, Object obj, String method, List<Object> params) throws Exception{
		Method[] mds = obj.getClass().getMethods();
		List<Method> mdList = new ArrayList<Method>();
		for (Method m : mds){
			if (method.equals(m.getName())){
				mdList.add(m);
			}
		}
		
		XmlUtil.each(e.getChildNodes(), new OnLoop(){
			int index = 0;
			
			@Override
			public void on(Element elem) throws Exception {
				String text = elem.getFirstChild().getTextContent();
				int tp = TypeUtil.typeof(elem);
				switch (tp){
				case TypeUtil.DOUBLE:
					params.add(XmlUtil.getDouble(text, elp, null));
					break;
				case TypeUtil.INT:
					params.add(XmlUtil.getInt(text, elp, null));
					break;
				case TypeUtil.STRING:
					params.add(XmlUtil.getString(text, elp));
					break;
				case TypeUtil.SQLDATE:
					params.add(XmlUtil.parseELText(text, elp));
					break;
				case TypeUtil.OBJECT:
					params.add(XmlUtil.parseELText(text, elp));
					break;
				}
				checkUnmatchedMethod(mdList, index, tp, params.get(params.size() - 1));
				
				index++;
			}
		});
		
		if (!mdList.isEmpty()){
			for (Method md : mdList){
				if (md.getParameterCount() == params.size()){
					return md;
				}
			}
		}
		return null;
	}
	
	private void invokeVoid(Method md, Object obj, List<Object> params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		if (params.isEmpty()){
			md.invoke(obj);
		}else{
			md.invoke(obj, params.toArray());
		}
	}
	
	private Object invoke(Method md, Object obj, List<Object> params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		if (params.isEmpty()){
			return md.invoke(obj);
		}else{
			return md.invoke(obj, params.toArray());
		}
	}
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isCall(e)){
			return false;
		}
		//ReportLogger.trace().debug(component.getConfig().getTagName() + " : " + XmlUtil.toStringFromDoc(e));
		elp = new ELParser(component);
		
		Object obj = XmlUtil.getObjectAttr(e, "object", elp);
		String methodName = (String) XmlUtil.getObjectAttr(e, "method", elp);
		List<Object> params = new ArrayList<Object>();
		Method md = parseParams(e, obj, methodName, params);
		if (md != null){
			md.setAccessible(true);
			if (e.hasAttribute("id")){
				Object result = invoke(md, obj, params);
				component.put(e, result);
			}else{
				invokeVoid(md, obj, params);
			}
		}
		return true;
	}
}