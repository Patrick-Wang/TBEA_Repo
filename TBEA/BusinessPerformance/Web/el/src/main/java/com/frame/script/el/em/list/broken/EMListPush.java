package com.frame.script.el.em.list.broken;

import java.util.List;

import com.frame.script.el.em.NamedEM;


public class EMListPush  extends NamedEM{

	public EMListPush() {
		super("push");
	}

	@Override
	public int paramCount() {
		return 1;
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (stub != null && stub instanceof List) {
			List list = (List) stub;
			list.add(args.get(0));
			return stub;
		}
		return null;
	}

}
