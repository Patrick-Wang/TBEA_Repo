package com.frame.script.el.em;

import java.util.List;


public class EMTest  extends NamedEM{

	public EMTest() {
		super("test");
	}


	@Override
	public int paramCount() {
		return 2;
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		System.out.println(args.get(0));
		System.out.println(args.get(1));
		return stub;
	}
}
