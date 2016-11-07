package com.tbea.ic.operation.controller.servlet.report;

import java.util.List;

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
}
