package com.frame.script.el.em;

import java.util.List;

import com.util.tools.MathUtil;


public class EMListMax  extends NamedEM{

	public EMListMax() {
		super("max");
	}

	@Override
	public int paramCount() {
		return 0;
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (stub != null && stub instanceof List) {
			List list = (List) stub;
			Double mxVal = MathUtil.o2d(list.get(0));
			Double tmpVal = null;
			for (int j = 1; j < list.size(); ++j) {
				tmpVal = MathUtil.o2d(list.get(j));
				if (tmpVal != null) {
					if (mxVal == null || mxVal < tmpVal) {
						mxVal = tmpVal;
					}
				}
			}
			return mxVal;
		}
		return null;
	}

}
