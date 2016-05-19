package com.tbea.ic.operation.common.formatter.excel;
import org.apache.poi.hssf.usermodel.HSSFCell;

import com.tbea.ic.operation.common.excel.ExcelTemplate;

public class HeaderFormatterHandler extends TextFormatterHandler {

	public HeaderFormatterHandler(String[] rows, Integer[] cols) {
		super(rows, cols);
	}
	
	public HeaderFormatterHandler(String[] rows) {
		this(rows, null);
	}
	
	public HeaderFormatterHandler() {
		this(null, null);
	}


	@Override
	protected String onHandle(ExcelTemplate template, HSSFCell cell, String val) {
		String ret = super.onHandle(template, cell, val);
		cell.setCellStyle(template.getCellStyleHeader());
		return ret;
	}

}
