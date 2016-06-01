package com.tbea.ic.operation.common.formatter.v2.data;

import java.util.List;

import com.tbea.ic.operation.common.formatter.v2.core.AbstractFormatterHandler;
import com.tbea.ic.operation.common.formatter.v2.core.FormatterMatcher;

public class TextFormatter extends AbstractFormatterHandler {

	public TextFormatter(FormatterMatcher matcher) {
		super(matcher);
	}

	public TextFormatter() {
	}

	@Override
	protected String onHandle(List<List<String>> table, int row, int col, String val) {
		if (!isNull(val)){
			String ret = val;
			if (val.contains(" ")){
				ret = " " + val.trim();
			}
			return ret;
		}
		return null;
	}

}
