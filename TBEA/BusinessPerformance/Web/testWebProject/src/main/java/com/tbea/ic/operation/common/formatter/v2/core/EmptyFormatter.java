package com.tbea.ic.operation.common.formatter.v2.core;

import java.util.List;

public class EmptyFormatter extends AbstractFormatterHandler {


	public EmptyFormatter(FormatterMatcher matcher) {
		super(matcher);
	}

	public EmptyFormatter() {
	}

	@Override
	protected String onHandle(List<List<String>> table, int row, int col, String val) {
		return val;
	}

}
