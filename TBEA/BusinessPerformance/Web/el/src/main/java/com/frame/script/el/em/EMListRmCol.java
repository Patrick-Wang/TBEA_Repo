package com.frame.script.el.em;

import java.util.List;


public class EMListRmCol  extends NamedEM{

	public EMListRmCol() {
		super("rmCol");
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
				if (!list.isEmpty() && ((List)list.get(0)).size() > index) {
					for (int i = 0, len = list.size(); i < len; ++i) {
						((List)list.get(i)).remove(index.intValue());
					}
				}
				return list;
			}
		}
		return null;
	}

}
