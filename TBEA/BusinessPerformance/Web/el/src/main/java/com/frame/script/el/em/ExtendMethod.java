package com.frame.script.el.em;

import java.util.List;

import javax.el.ELContext;


public interface ExtendMethod{
	int paramCount();
	Object invoke(Object stub, List<Object> args);
	boolean check(String name);
	String getName();
}
