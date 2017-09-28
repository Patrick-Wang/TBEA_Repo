package com.tbea.ic.operation.service.ydzb.pipe.filter.composite;

import java.util.Comparator;

import com.util.tools.MathUtil;

public class DoubleArrayComparator implements Comparator<Double[]>{

	private int index;
	boolean asc = true;
	public int getIndex() {
		return index;
	}
	
	public DoubleArrayComparator desc(){
		asc = false;
		return this;
	}

	public DoubleArrayComparator setIndex(int index) {
		this.index = index;
		return this;
	}

	@Override
	public int compare(Double[] o1, Double[] o2) {
//		Double val = 0d;
//		if (asc){
//			val = Util.valueOf(o1[index]) - Util.valueOf(o2[index]);
//		}else{
//			val = Util.valueOf(o2[index]) - Util.valueOf(o1[index]);
//		}
		Double val = MathUtil.minus(o1[index], o2[index]);
		
		if (!asc){
			val = MathUtil.minus(o2[index], o1[index]);
		}
			
		if (val == null){
			if (o1[index] == o2[index]){
				return 0;
			}
			
			if (o1[index] == null){
				return -1;
			}
			if (o2[index] == null){
				return 1;
			}
		}
			
			
		Integer ret = 0;
		if (MathUtil.isPositive(val)){
			ret = 1;
		}
		else if (MathUtil.isNegative(val)){
			ret = -1;
		}
		return ret; 
	}
}
