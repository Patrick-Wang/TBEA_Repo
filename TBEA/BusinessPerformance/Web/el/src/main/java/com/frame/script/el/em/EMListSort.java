package com.frame.script.el.em;

import java.util.Comparator;
import java.util.List;

import com.frame.script.el.em.NamedEM;
import com.util.tools.MathUtil;


public class EMListSort  extends NamedEM{

	boolean asc = false;
	boolean isTable = false;
	
	public EMListSort(boolean asc, boolean isTable) {
		super("");
		this.asc = asc;
		this.isTable = isTable;
		if (this.isTable) {
			if (this.asc) {
				this.name="tasc";
			}else {
				this.name="tdesc";
			}
		}else {
			if (this.asc) {
				this.name="asc";
			}else {
				this.name="desc";
			}
		}
	}

	@Override
	public int paramCount() {
		return isTable ? 1 : 0;
	}

	private int sortCompare(Double d1, Double d2) {
		Double val = null;
		
		if (!asc){
			val = MathUtil.minus(d2, d1);
		}else {
			val = MathUtil.minus(d1, d2);
		}
			
		if (val == null){
			if (d1 == d2){
				return 0;
			}
			
			if (d1 == null){
				return -1;
			}
			if (d2 == null){
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (stub != null) {
			
			if (stub instanceof List) {
				List list = (List) stub;
				if (this.isTable) {
					Integer index = (Integer) args.get(0);
					if ((List)list.get(0) instanceof List) {
						if (((List)list.get(0)).size() > index) {
							list.sort(new Comparator() {
								@Override
								public int compare(Object o1, Object o2) {
									Double d1 = MathUtil.o2d(((List) o1).get(index));
									Double d2 = MathUtil.o2d(((List) o2).get(index));
									return sortCompare(d1, d2);
								}
							});
						}
					}else {
						list.sort(new Comparator() {
							@Override
							public int compare(Object o1, Object o2) {
								Double d1 = MathUtil.o2d(o1);
								Double d2 = MathUtil.o2d(o2);
								return sortCompare(d1, d2);
							}
						});
					}
				}
				return list;
			}
		}
		return null;
	}

}
