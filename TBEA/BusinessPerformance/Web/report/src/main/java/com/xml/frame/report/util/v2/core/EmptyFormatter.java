package com.xml.frame.report.util.v2.core;

import java.util.List;

public class EmptyFormatter extends AbstractFormatter {


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
