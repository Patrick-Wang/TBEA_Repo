package com.tbea.ic.operation.common.formatter.v2.data;

import java.util.List;

import com.tbea.ic.operation.common.formatter.v2.core.FormatterMatcher;

public class PercentSingleFormatter extends NumberFormatter {

	public PercentSingleFormatter(FormatterMatcher matcher,
			Integer reservedCount) {
		super(matcher, reservedCount);
	}

	@Override
	protected String onHandle(List<List<String>> table, int row, int col, String val) {
		String ret = super.onHandle(table, row, col, val);
		if (!isNull(ret)){
			return ret + "%";
		}
		return null;
	}

}
