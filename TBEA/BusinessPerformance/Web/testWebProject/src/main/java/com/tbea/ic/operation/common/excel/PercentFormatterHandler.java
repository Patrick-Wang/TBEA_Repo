package com.tbea.ic.operation.common.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;

public class PercentFormatterHandler extends AbstractFormatterHandler {

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
	protected void onHandle(ExcelTemplate template, HSSFCell cell, String val) {
		cell.setCellValue(String.format("%.1f", Double.valueOf(val) * 100)
				+ "%");
		cell.setCellStyle(template.getCellStylePercent());
	}

}
