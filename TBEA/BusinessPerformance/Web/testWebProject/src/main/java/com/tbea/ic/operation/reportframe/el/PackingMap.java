package com.tbea.ic.operation.reportframe.el;

import java.lang.reflect.Method;
import java.util.List;

import com.tbea.ic.operation.common.ClosureMap;
import com.tbea.ic.operation.reportframe.util.TypeUtil;


public class PackingMap extends ClosureMap {

	Object packageObj;
	int nextSize = 0;
	Method md;
	
	public Object unpack(){
		return packageObj;
	}
	
	public PackingMap(Object packageObj) {
		super();
		this.packageObj = packageObj;
	}
	
	@Override
	protected boolean validate(List<Object> args) {
		if (1 == args.size()){
			this.md = null;
			Method[] mds = packageObj.getClass().getMethods();
			for (Method md : mds){
				if (md.getName().equals((String)args.get(0))){
					this.md = md;
					break;
				}
			}
			if (this.md != null){
				nextSize = md.getParameterCount() + 1;
			}else{
				return true;
			}
		}
		return args.size() == nextSize;
	}

	@Override
	protected Object onGetProp(List<Object> args) throws Exception {
		if (null == this.md){
			return null;
		}
		nextSize = 0;
		args.remove(0);
		Object ret = this;
		if (TypeUtil.typeOf(md.getReturnType(), void.class)){
			this.md.invoke(packageObj, args.toArray());
		}else{
			ret = this.md.invoke(packageObj, args.toArray());
		}
		return ret;
	}
	
}
