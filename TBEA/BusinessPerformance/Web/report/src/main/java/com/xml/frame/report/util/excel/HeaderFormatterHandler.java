package com.xml.frame.report.util.excel;
import org.apache.poi.hssf.usermodel.HSSFCell;

import com.xml.frame.report.util.ExcelHelper;

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
	protected String onHandle(ExcelHelper template, HSSFCell cell, String val) {
		String ret = super.onHandle(template, cell, val);
		cell.setCellStyle(template.getCellStyleHeader());
		return ret;
	}

}
