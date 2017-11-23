package com.frame.script.el.em;

import java.util.List;


public class EMListRemoveAt  extends NamedEM{

	
	
	public EMListRemoveAt() {
		super("rm");
	}

	@Override
	public int paramCount() {
		return 1;
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (stub != null) {
			Integer index = (Integer) args.get(0);
			if (stub instanceof List) {
				List list = (List) stub;
				if (list.size() > index){
					list.remove(index.intValue());
				}
				return list;
			}
		}
		return null;
	}

}
