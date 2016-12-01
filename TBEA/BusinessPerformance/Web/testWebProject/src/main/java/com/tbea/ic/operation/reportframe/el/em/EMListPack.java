package com.tbea.ic.operation.reportframe.el.em;

import java.util.ArrayList;
import java.util.List;


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
		List list = new ArrayList();
		list.add(stub);
		return list;
	}

}
