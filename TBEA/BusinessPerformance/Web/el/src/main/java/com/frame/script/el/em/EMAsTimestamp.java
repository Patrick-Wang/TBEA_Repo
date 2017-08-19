package com.frame.script.el.em;

import java.sql.Timestamp;
import java.util.List;

import com.frame.script.util.TypeUtil;


public class EMAsTimestamp extends NamedEM{

	public EMAsTimestamp() {
		super("asTimestamp");
	}
	
	@Override
	public int paramCount() {
		return 0;
	}


	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (TypeUtil.instanceOf(stub, Long.class)){
			return  new Timestamp((Long)stub);
		}
		return stub;
	}
}
