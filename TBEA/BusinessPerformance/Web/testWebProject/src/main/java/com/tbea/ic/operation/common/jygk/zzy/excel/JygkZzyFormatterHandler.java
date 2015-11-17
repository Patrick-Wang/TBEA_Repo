package com.tbea.ic.operation.common.jygk.zzy.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;

public interface JygkZzyFormatterHandler {
	JygkZzyFormatterHandler next(JygkZzyFormatterHandler handler);
	void handle(String zbName, Integer col, JygkZzyExcelTemplate template, HSSFCell cell, String val);
}
