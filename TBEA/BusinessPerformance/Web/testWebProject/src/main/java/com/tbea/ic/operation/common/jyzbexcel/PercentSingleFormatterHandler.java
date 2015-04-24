package com.tbea.ic.operation.common.jyzbexcel;

import org.apache.poi.hssf.usermodel.HSSFCell;

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
	protected void onHandle(JyzbExcelTemplate template, HSSFCell cell, String val) {
		cell.setCellValue(String.format("%.1f", Double.valueOf(val) / 100.0) + "%");
		cell.setCellStyle(template.getCellStylePercent());
	}

}
