package com.frame.script.el.em;

import java.util.List;


public class EMListResize  extends NamedEM{

	
	public EMListResize() {
		super("resize");
	}

	@Override
	public int paramCount() {
		return 1;
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (stub != null) {
			Integer size = (Integer) args.get(0);
			if (stub instanceof List) {
				List list = (List) stub;
				if (list.size() < size){
					for (int i = list.size(); i < size; ++i){
						list.add(null);
					}
				}
				return list;
			}
		}
		return null;
	}

}
