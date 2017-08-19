package com.frame.script.el.em;

import java.util.List;

import com.frame.script.util.TypeUtil;


public class EM2Int extends NamedEM{

	public EM2Int() {
		super("toInt");
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (TypeUtil.instanceOf(stub, Double.class)){
			return ((Double)stub).intValue();
		}
		if (TypeUtil.instanceOf(stub, String.class)){
			return Integer.valueOf((String) stub);
		}
		return stub;
	}



	@Override
	public int paramCount() {
		return 0;
	}
}
