package com.frame.script.el.em.list.broken;

import java.util.List;

import com.frame.script.el.em.NamedEM;


public class EMListConcat  extends NamedEM{

	public EMListConcat() {
		super("concat");
	}

	@Override
	public int paramCount() {
		return 1;
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (stub != null && stub instanceof List) {
			List list = (List) stub;
			if (args.get(0) instanceof List)
			list.addAll((List)args.get(0));
			return stub;
		}
		return null;
	}

}
