package com.tbea.ic.operation.common.jyzbexcel;

import org.apache.poi.hssf.usermodel.HSSFCell;

public interface FormatterHandler {
	FormatterHandler next(FormatterHandler handler);
	void handle(String zbName, Integer col, JyzbExcelTemplate template, HSSFCell cell, String val);
}
