package com.tbea.ic.operation.common.formatter.v2.data;

import java.util.List;

import com.tbea.ic.operation.common.formatter.v2.core.FormatterMatcher;


public class PercentFormatter extends NumberFormatter {


	public PercentFormatter(FormatterMatcher matcher,
			Integer reservedCount) {
		super(matcher, reservedCount);
	}

	@Override
	protected String onHandle(List<List<String>> table, int row, int col, String val) {
		if (!isInvalid(val)){
			String ret = String.format("%." + reservedCount + "f", Double.valueOf(val) * 100);
			return trimZero(ret) + "%";
		}
		return null;
	}

}
