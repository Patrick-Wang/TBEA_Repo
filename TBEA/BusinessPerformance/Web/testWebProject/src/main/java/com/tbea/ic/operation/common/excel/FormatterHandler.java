package com.tbea.ic.operation.common.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;

public interface FormatterHandler {
	FormatterHandler next(FormatterHandler handler);
	void handle(String zbName, Integer col, ExcelTemplate template, HSSFCell cell, String val);
}
