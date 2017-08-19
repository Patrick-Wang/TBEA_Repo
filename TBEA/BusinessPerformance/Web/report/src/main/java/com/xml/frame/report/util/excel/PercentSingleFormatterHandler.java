package com.xml.frame.report.util.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.xml.frame.report.util.ExcelHelper;

public class PercentSingleFormatterHandler extends AbstractFormatterHandler {

	
	public PercentSingleFormatterHandler(String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
	}

	public PercentSingleFormatterHandler(String[] rows) {
		this(rows, null);
	}

	public PercentSingleFormatterHandler() {
		this(null, null);
	}

	@Override
	protected String onHandle(ExcelHelper template, HSSFCell cell, String val) {
		String ret = String.format("%.1f", Double.valueOf(val)) + "%";
		cell.setCellValue(ret);
		cell.setCellStyle(template.getCellStyleDefault());
		return ret;
	}

}
