package com.tbea.ic.operation.reportframe.el.em;

import java.util.List;


public interface ExtendMethod{


	int paramCount();
	Object invoke(Object stub, List<Object> args);
	boolean check(String name);
	
}
