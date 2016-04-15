package com.tbea.ic.operation.common.formatter.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.tbea.ic.operation.common.excel.ExcelTemplate;

public interface FormatterHandler {
	FormatterHandler next(FormatterHandler handler);
	String handle(String zbName, Integer col, ExcelTemplate template, HSSFCell cell, String val);
}
