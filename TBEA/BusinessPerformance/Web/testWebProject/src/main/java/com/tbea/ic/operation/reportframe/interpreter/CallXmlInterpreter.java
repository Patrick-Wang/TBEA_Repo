package com.tbea.ic.operation.reportframe.interpreter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.TypeUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil.OnLoop;


public class CallXmlInterpreter implements XmlInterpreter {
	
	private boolean kindOfEqual(Class<?> c1, Class c2){
		if (null == c1){
			return false;
		}
		System.out.println(c1.getName());
		if (c1.getName().equals(c2.getName())){
			return true;
		}
		return kindOfEqual(c1.getSuperclass(), c2);
	}
	
	public boolean kindOf(Class<?> c1, Class c2){
		if (c1 != null){
			if (!kindOfEqual(c1, c2)){
				Class<?>[] cls = c1.getInterfaces();
				for (Class<?> cl : cls){
					if (kindOfEqual(cl, c2)){
						return true;
					}
				}
			}else{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {
		
		if (!Schema.isCall(e)){
			return false;
		}

		ELParser elp = new ELParser(component);
		
		Object obj = XmlUtil.getObjectAttr(e, "object", elp);
		String method = (String) XmlUtil.getObjectAttr(e, "method", elp);
		List<Object> params = new ArrayList<Object>();
		Method[] mds = obj.getClass().getMethods();
		List<Method> mdList = new ArrayList<Method>();
		for (Method m : mds){
			if (method.equals(m.getName())){
				mdList.add(m);
			}
		}
		XmlUtil.each(e.getChildNodes(), new OnLoop(){

			int count = 0;
			
			void compareList(int tp, Object object){
				for (int i = mdList.size() - 1; i >= 0; --i){
					if (mdList.get(i).getParameterCount() > count){
						System.out.println(mdList.get(i).getParameterTypes()[count].getName());
						switch (tp){
						case TypeUtil.DOUBLE:
							if (!mdList.get(i).getParameterTypes()[count].getName().equals(Double.class.getName())){
								mdList.remove(i);
							}
							break;
						case TypeUtil.INT:
							if (!mdList.get(i).getParameterTypes()[count].getName().equals(Integer.class.getName())){
								mdList.remove(i);
							}
							break;
						case TypeUtil.STRING:
							if (!mdList.get(i).getParameterTypes()[count].getName().equals(String.class.getName())){
								mdList.remove(i);
							}
							break;
						case TypeUtil.OBJECT:
							if (object != null){
								System.out.println(object.getClass().getName());
								if (!kindOf(object.getClass(), mdList.get(i).getParameterTypes()[count])){
									mdList.remove(i);
								};
							}
							break;
						}
					}
				}
			}
			
			@Override
			public void on(Element elem) {
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
				compareList(tp, params.get(params.size() - 1));
				count++;
			}
			
		});
		if (e.hasAttribute("id")){
		Object result = null;
		try {
			
			Method md = mdList.get(0);
			switch(params.size()){
			case 0:
				result = md.invoke(obj, params.get(0));
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
		
		
		
		String id = e.getAttribute("id");
		component.local(id, result);
		}else{
			try {
				
				Method md = mdList.get(0);
				switch(params.size()){
				case 0:
					md.invoke(obj, params.get(0));
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
		return true;
	}
}