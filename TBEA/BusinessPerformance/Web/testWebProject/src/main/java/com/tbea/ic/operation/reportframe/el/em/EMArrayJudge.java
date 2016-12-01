package com.tbea.ic.operation.reportframe.el.em;

import java.util.List;


public class EMArrayJudge  extends NamedEM{

	
	
	public EMArrayJudge() {
		super("isArray");
	}

	@Override
	public int paramCount() {
		return 0;
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		return null != stub && stub.getClass().isArray();
	}

}
