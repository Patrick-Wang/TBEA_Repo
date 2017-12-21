package com.frame.script.el.em;

import com.frame.script.el.ElContext;

import java.util.List;



public class EMStore extends NamedEM{

	public EMStore() {
		super("store");
	}
	
	@Override
	public int paramCount() {
		return 1;
	}


	@Override
	public Object invoke(Object stub, List<Object> args) {
		String key = (String) args.get(0);
		ElContext elContext = (ElContext) args.get(1);
		elContext.storeObject(key, stub);
		return stub;
	}
}
