package com.frame.script.el.em;

import java.util.List;


public class EMListLastRmCol  extends NamedEM{


	public EMListLastRmCol() {
		super("lastRmCol");
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
				if (!list.isEmpty() && ((List)list.get(0)).size() > lastIndex) {
					int index = ((List)list.get(0)).size() - lastIndex - 1;
					for (int i = 0, len = list.size(); i < len; ++i) {
						((List)list.get(i)).remove(index);
					}
				}
				return list;
			}
		}
		return null;
	}

}
