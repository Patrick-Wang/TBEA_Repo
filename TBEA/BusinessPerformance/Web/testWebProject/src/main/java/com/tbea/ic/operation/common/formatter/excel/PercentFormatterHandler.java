package com.tbea.ic.operation.common.formatter.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.tbea.ic.operation.common.excel.ExcelTemplate;

public class PercentFormatterHandler extends AbstractFormatterHandler {

	Integer reservedCount = 1;

	public PercentFormatterHandler(Integer reservedCount, String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
		this.reservedCount = reservedCount;
	}
	
	public PercentFormatterHandler(String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
	}

	public PercentFormatterHandler(String[] rows) {
		this(rows, null);
	}

	public PercentFormatterHandler() {
		this(null, null);
	}

	@Override
	protected String onHandle(ExcelTemplate template, HSSFCell cell, String val) {
		String ret = String.format("%." + reservedCount + "f", Double.valueOf(val) * 100) + "%";
		cell.setCellValue(ret);
		cell.setCellStyle(template.getCellStyleDefault());
		return ret;
	}

}
