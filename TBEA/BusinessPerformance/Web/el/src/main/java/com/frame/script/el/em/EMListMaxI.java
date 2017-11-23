package com.frame.script.el.em;

import java.util.ArrayList;
import java.util.List;

import com.util.tools.MathUtil;


public class EMListMaxI  extends NamedEM{

	public EMListMaxI() {
		super("maxI");
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
			List<Integer> maxI = new ArrayList<Integer>();
			for (int j = 1; j < list.size(); ++j) {
				tmpVal = MathUtil.o2d(list.get(j));
				if (tmpVal != null) {
					if (mxVal == null || mxVal < tmpVal) {
						mxVal = tmpVal;
					}
				}
			}
			
			for (int j = 0; j < list.size(); ++j) {
				tmpVal = MathUtil.o2d(list.get(j));
				tmpVal = MathUtil.minus(mxVal, tmpVal);
				if (tmpVal != null) {
					if (MathUtil.isZero(tmpVal)) {
						maxI.add(j);
						mxVal = tmpVal;
					}
				}
			}
			
			return maxI;
		}
		return null;
	}

}
