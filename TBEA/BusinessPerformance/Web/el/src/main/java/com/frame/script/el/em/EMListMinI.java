package com.frame.script.el.em;

import java.util.ArrayList;
import java.util.List;

import com.util.tools.MathUtil;


public class EMListMinI  extends NamedEM{

	public EMListMinI() {
		super("minI");
	}

	@Override
	public int paramCount() {
		return 0;
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (stub != null && stub instanceof List) {
			List list = (List) stub;
			List<Integer> minI = new ArrayList<Integer>();
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
			

			for (int j = 0; j < list.size(); ++j) {
				tmpVal = MathUtil.o2d(list.get(j));
				tmpVal = MathUtil.minus(minVal, tmpVal);
				if (tmpVal != null) {
					if (MathUtil.isZero(tmpVal)) {
						minI.add(j);
					}
				}
			}
			return minI;
		}
		return null;
	}

}
