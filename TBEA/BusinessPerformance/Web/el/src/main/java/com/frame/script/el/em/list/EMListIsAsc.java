package com.frame.script.el.em.list;

import java.util.List;

import com.frame.script.el.em.NamedEM;
import com.util.tools.MathUtil;


public class EMListIsAsc  extends NamedEM{

	public EMListIsAsc() {
		super("isAsc");
	}

	@Override
	public int paramCount() {
		return 0;
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (stub != null && stub instanceof List) {
			List list = (List) stub;
			Double preVal = MathUtil.o2d(list.get(0));
			Double nextVal = null;
			for (int j = 1; j < list.size(); ++j) {
				nextVal = MathUtil.o2d(list.get(j));
				if (preVal == null) {
					preVal = nextVal;
				}else {
					if (nextVal != null) {
						if (preVal > nextVal) {
							return false;
						}
					}
				}
			}
			return true;
		}
		return null;
	}

}
