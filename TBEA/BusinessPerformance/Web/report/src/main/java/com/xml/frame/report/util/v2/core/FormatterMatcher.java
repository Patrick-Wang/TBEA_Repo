package com.xml.frame.report.util.v2.core;

import java.util.List;

public interface FormatterMatcher {
	boolean match(List<List<String>> table, int row, int col);
}
