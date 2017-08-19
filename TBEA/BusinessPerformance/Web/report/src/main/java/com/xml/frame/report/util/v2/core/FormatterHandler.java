package com.xml.frame.report.util.v2.core;

import java.util.List;

public interface FormatterHandler {
	FormatterHandler next(FormatterHandler handler);
	String handle(List<List<String>> table, int row, int col, String val);
}
