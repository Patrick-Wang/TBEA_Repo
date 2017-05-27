package com.tbea.ic.operation.reportframe.el;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.ClosureMap;
import com.tbea.ic.operation.reportframe.el.em.EM2Int;
import com.tbea.ic.operation.reportframe.el.em.EMArrayJudge;
import com.tbea.ic.operation.reportframe.el.em.EMAsJson;
import com.tbea.ic.operation.reportframe.el.em.EMAsTimestamp;
import com.tbea.ic.operation.reportframe.el.em.EMDistinct;
import com.tbea.ic.operation.reportframe.el.em.EMJsonString2Json;
import com.tbea.ic.operation.reportframe.el.em.EMListJudge;
import com.tbea.ic.operation.reportframe.el.em.EMListPack;
import com.tbea.ic.operation.reportframe.el.em.EMTest;
import com.tbea.ic.operation.reportframe.el.em.EMTranspose;
import com.tbea.ic.operation.reportframe.el.em.ExtendMethod;
import com.tbea.ic.operation.reportframe.util.TypeUtil;


public class PackingMap2 extends ClosureMap {

	Object packageObj;
	int nextSize = 0;
	Method md;
	ExtendMethod emd;
	
	static List<ExtendMethod> extendMethods = new ArrayList<ExtendMethod>();
	static{
		extendMethods.add(new EMArrayJudge());
		extendMethods.add(new EMListJudge());
		extendMethods.add(new EMAsJson());
		extendMethods.add(new EMDistinct());
		extendMethods.add(new EMTranspose());
		extendMethods.add(new EMTest());
		extendMethods.add(new EMListPack());
		extendMethods.add(new EMAsTimestamp());
		extendMethods.add(new EMJsonString2Json());
		extendMethods.add(new EM2Int());
	}
	
	public Object unpack(){
		return packageObj;
	}
	
	public PackingMap2(Object packageObj) {
		super();
		this.packageObj = packageObj;
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
		for (ExtendMethod em : extendMethods){
			if (em.check(paramName)){
				emd = em;
				nextSize = emd.paramCount() + 1;
				return true;
			}
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
		return this.emd.invoke(packageObj, args);
	}
}
