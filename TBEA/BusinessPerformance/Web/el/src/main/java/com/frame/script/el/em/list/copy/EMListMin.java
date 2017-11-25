package com.frame.script.el.em.list.copy;

import java.util.List;

import com.frame.script.el.em.NamedEM;
import com.util.tools.MathUtil;


public class EMListMin  extends NamedEM{

	public EMListMin() {
		super("min");
	}

	@Override
	public int paramCount() {
		return 0;
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (stub != null && stub instanceof List) {
			List list = (List) stub;
			Double minVal = MathUtil.o2d(list.get(0));
			Double tmpVal = null;
			for (int j = 1; j < list.size(); ++j) {
				tmpVal = MathUtil.o2d(list.get(j));
				if (tmpVal != null) {
					if (minVal == null || minVal > tmpVal) {
						minVal = tmpVal;
					}
				}
			}
			return minVal;
		}
		return null;
	}

}
