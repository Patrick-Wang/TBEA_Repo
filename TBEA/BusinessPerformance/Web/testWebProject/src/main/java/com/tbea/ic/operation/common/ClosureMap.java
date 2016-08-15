package com.tbea.ic.operation.common;

import java.util.ArrayList;
import java.util.List;


public abstract class ClosureMap extends PropMap {

	List<Object> args = new ArrayList<Object>();
	
	@Override
	public Object getProperty(Object key) throws Exception {
		args.add(key);
		if (validate(args)){
			Object obj = onGetProp(args);
			args.clear();
			return obj;
		}
		return this;
	}

	abstract protected boolean validate(List<Object> args) throws Exception;

	abstract protected Object onGetProp(List<Object> args) throws Exception;
}
