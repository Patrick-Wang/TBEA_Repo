package com.frame.script.el.em;

import java.util.List;


public class EMAsEmpty extends NamedEM{

	
	
	public EMAsEmpty() {
		super("asEmpty");
	}
	
	@Override
	public int paramCount() {
		return 0;
	}


	@Override
	public Object invoke(Object stub, List<Object> args) {
		return  "";
	}
}
