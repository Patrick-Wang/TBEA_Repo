package com.tbea.ic.operation.reportframe.interpreter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

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
				case TypeUtil.OBJECT:
					if (paramObj != null){
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
				case TypeUtil.OBJECT:
					params.add(XmlUtil.getELValue(text, elp));
					break;
				}
				checkUnmatchedMethod(mdList, tp, index, params.get(params.size() - 1));
				
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
	
	private void invokeVoid(Method md, Object obj, List<Object> params){
		try {
			switch(params.size()){
			case 0:
				md.invoke(obj);
				break;
			case 1:
				md.invoke(obj, params.get(0));
				break;
			case 2:
				md.invoke(obj, params.get(0), params.get(1));
				break;
			case 3:
				md.invoke(obj, params.get(0), params.get(1), params.get(2));
				break;
			case 4:
				md.invoke(obj, params.get(0), params.get(1), params.get(2), params.get(3));
				break;
			case 5:
				md.invoke(obj, params.get(0), params.get(1), params.get(2), params.get(3), params.get(4));
				break;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private Object invoke(Method md, Object obj, List<Object> params){
		Object result = null;
		try {
			switch(params.size()){
			case 0:
				result = md.invoke(obj);
				break;
			case 1:
				result = md.invoke(obj, params.get(0));
				break;
			case 2:
				result = md.invoke(obj, params.get(0), params.get(1));
				break;
			case 3:
				result = md.invoke(obj, params.get(0), params.get(1), params.get(2));
				break;
			case 4:
				result = md.invoke(obj, params.get(0), params.get(1), params.get(2), params.get(3));
				break;
			case 5:
				result = md.invoke(obj, params.get(0), params.get(1), params.get(2), params.get(3), params.get(4));
				break;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isCall(e)){
			return false;
		}

		elp = new ELParser(component);
		
		Object obj = XmlUtil.getObjectAttr(e, "object", elp);
		String methodName = (String) XmlUtil.getObjectAttr(e, "method", elp);
		List<Object> params = new ArrayList<Object>();
		Method md = parseParams(e, obj, methodName, params);
		if (md != null){
			md.setAccessible(true);
			if (e.hasAttribute("id")){
				Object result = invoke(md, obj, params);
				String id = e.getAttribute("id");
				if ("true".equals(e.getAttribute("export"))){
					component.global(id, result);
				}else{
					component.local(id, result);
				}
			}else{
				invokeVoid(md, obj, params);
			}
		}
		return true;
	}
}