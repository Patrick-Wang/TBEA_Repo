package com.xml.frame.report.util.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.xml.frame.report.util.ExcelHelper;

public interface FormatterHandler {
	FormatterHandler next(FormatterHandler handler);
	String handle(String zbName, Integer col, ExcelHelper template, HSSFCell cell, String val);
}
