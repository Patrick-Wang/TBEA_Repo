package com.frame.script.el.em.list;

import java.util.ArrayList;
import java.util.List;

import com.frame.script.el.em.NamedEM;


public class EMListPack  extends NamedEM{

	
	
	public EMListPack() {
		super("packAsList");
	}

	@Override
	public int paramCount() {
		return 0;
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		List list = new ArrayList(1);
		list.add(stub);
		return list;
	}

}
