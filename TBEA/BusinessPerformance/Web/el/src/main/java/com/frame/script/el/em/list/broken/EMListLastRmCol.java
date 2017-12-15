package com.frame.script.el.em.list.broken;

import java.util.List;

import com.frame.script.el.em.NamedEM;
import com.util.tools.ListUtil;


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
				if (!list.isEmpty()) {
					for (int i = 0, len = list.size(); i < len; ++i) {
                        list.set(i, ListUtil.lastRmAt(list.get(i), lastIndex));
					}
				}
				return list;
			}
		}
		return null;
	}

}
