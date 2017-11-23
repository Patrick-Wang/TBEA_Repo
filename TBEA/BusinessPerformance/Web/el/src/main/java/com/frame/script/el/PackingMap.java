package com.frame.script.el;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.frame.script.el.ELParser.ElContext;
import com.frame.script.el.em.EMRegistry;
import com.frame.script.el.em.ExtendMethod;
import com.frame.script.util.ClosureMap;
import com.frame.script.util.TypeUtil;


public class PackingMap extends ClosureMap {

	Object packageObj;
	int nextSize = 0;
	Method md;
	ExtendMethod emd;
	ElContext elContext;
	
	public Object unpack(){
		return packageObj;
	}
	
	public PackingMap(Object packageObj, ElContext elContext) {
		super();
		this.packageObj = packageObj;
		this.elContext = elContext;
	}

	private boolean findObjectMethod(String methodName){
		this.md = null;
		Method[] mds = packageObj.getClass().getMethods();
		for (Method md : mds){
			if (md.getName().equals(methodName)){
				this.md = md;
				nextSize = md.getParameterCount() + 1;
				return true;
			}
		}
		return false;
	}
	
	private boolean findExtendMethod(String paramName){
		ExtendMethod em = EMRegistry.find(paramName);
		if (null != em){
			emd = em;
			nextSize = emd.paramCount() + 1;
			return true;
		}
		return false;
	}
	
	private boolean isMethodNameArgs(List<Object> args){
		return 1 == args.size();
	}
	
	@Override
	protected boolean validate(List<Object> args) {
		if (isMethodNameArgs(args)){
			String mdName = (String)args.get(0);
			if (!findObjectMethod(mdName) && 
				!findExtendMethod(mdName)){
				return true;
			}
		}
		return args.size() == nextSize;
	}
	
	private boolean objectMethodIsFound(){
		return null != this.md;
	}
	
	private boolean extendMethodIsFound(){
		return null != this.emd;
	}
	
	private Object invokeObjectMethod(List<Object> args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Object ret = this;
		if (!args.isEmpty()){
			if (TypeUtil.typeOf(md.getReturnType(), void.class)){
				this.md.invoke(packageObj, args.toArray());
			}else{
				ret = this.md.invoke(packageObj, args.toArray());
			}
		}else{
			if (TypeUtil.typeOf(md.getReturnType(), void.class)){
				this.md.invoke(packageObj);
			}else{
				ret = this.md.invoke(packageObj);
			}
		}
		return ret;
	}
	
	@Override
	protected Object onGetProp(List<Object> args) throws Exception {
		nextSize = 0;
		args.remove(0);
		Object ret = null;
		if (objectMethodIsFound()){
			ret = invokeObjectMethod(args);
		}else if (extendMethodIsFound()){
			ret = invokeExtendMethod(args);
		}
		args.clear();
		return ret;
	}

	private Object invokeExtendMethod(List<Object> args) {
		args.add(elContext);
		return this.emd.invoke(packageObj, args);
	}
}
