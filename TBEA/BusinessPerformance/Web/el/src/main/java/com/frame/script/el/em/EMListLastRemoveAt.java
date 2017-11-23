package com.frame.script.el.em;

import java.util.List;


public class EMListLastRemoveAt  extends NamedEM{

	
	
	public EMListLastRemoveAt() {
		super("lastRm");
	}

	@Override
	public int paramCount() {
		return 1;
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (stub != null) {
			Integer lastIndex = (Integer) args.get(0);
			if (stub instanceof List) {
				List list = (List) stub;
				if (list.size() > lastIndex){
					int index = list.size() - lastIndex - 1;
					list.remove(index);
				}
				return list;
			}
		}
		return null;
	}

}
