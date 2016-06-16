package com.tbea.ic.operation.common.formatter.v2.data;

import java.util.List;

import com.tbea.ic.operation.common.formatter.v2.core.AbstractFormatter;
import com.tbea.ic.operation.common.formatter.v2.core.FormatterMatcher;

public class TextFormatter extends AbstractFormatter {

	public TextFormatter(FormatterMatcher matcher) {
		super(matcher);
	}

	public TextFormatter() {
	}

	@Override
	protected String onHandle(List<List<String>> table, int row, int col, String val) {
		if (!isInvalid(val)){
			String ret = val;
			if (val.contains(" ")){
				ret = " " + val.trim();
			}
			return ret;
		}
		return null;
	}

}
