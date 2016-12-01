package com.tbea.ic.operation.reportframe.el.em;

import java.util.List;

import com.tbea.ic.operation.reportframe.util.TypeUtil;


public class EMListJudge extends NamedEM{

	public EMListJudge() {
		super("isList");
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		return TypeUtil.instanceOf(stub, List.class);
	}



	@Override
	public int paramCount() {
		return 0;
	}
}
