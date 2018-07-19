package com.tbea.ic.operation.controller.servlet.report;

import java.util.List;

import com.frame.script.util.TypeUtil;
import com.util.tools.MathUtil;

public class ReportMath {
	
	public ReportMath() {
		super();
	}

	public Double sum(List<Double> vals){
		Double ret = null;
		for (int i = 0; i < vals.size(); ++i){
			if (vals.get(i) != null){
				if (ret == null){
					ret = vals.get(i);
				}else{
					ret += vals.get(i);
				}
			}
		}
		return ret;
	}
	
	public Double div(Double sub, Double base){
		return MathUtil.division(sub, base);
	}

	public Integer toInt(Object obj){
	    if (null == obj){
	        return null;
        }
        if (TypeUtil.isInt(obj.getClass())){
	        return (Integer)obj;
        }
        if (TypeUtil.isDouble(obj.getClass())){
            return ((Double)obj).intValue();
        }
        if (TypeUtil.isString(obj.getClass())){
            return Integer.valueOf((String)obj);
        }
        if (TypeUtil.isLong(obj.getClass())){
            return ((Long)obj).intValue();
        }
        return null;
    }
}
