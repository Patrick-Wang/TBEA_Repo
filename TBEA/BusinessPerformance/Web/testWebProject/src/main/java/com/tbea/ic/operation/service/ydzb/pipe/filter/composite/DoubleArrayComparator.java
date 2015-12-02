package com.tbea.ic.operation.service.ydzb.pipe.filter.composite;

import java.util.Comparator;

import com.tbea.ic.operation.common.Util;

public class DoubleArrayComparator implements Comparator<Double[]>{

	private int index;
	
	public int getIndex() {
		return index;
	}

	public DoubleArrayComparator setIndex(int index) {
		this.index = index;
		return this;
	}

	@Override
	public int compare(Double[] o1, Double[] o2) {
		Double val = Util.valueOf(o1[index]) - Util.valueOf(o2[index]);
		Integer ret = 0;
		if (Util.isPositive(val)){
			ret = 1;
		}
		else if (Util.isNegative(val)){
			ret = -1;
		}
		return ret; 
	}
}
