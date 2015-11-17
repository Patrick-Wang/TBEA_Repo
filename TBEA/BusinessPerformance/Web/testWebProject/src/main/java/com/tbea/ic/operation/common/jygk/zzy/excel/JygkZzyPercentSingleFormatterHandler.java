package com.tbea.ic.operation.common.jygk.zzy.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;

public class JygkZzyPercentSingleFormatterHandler extends JygkZzyAbstractFormatterHandler {

	public JygkZzyPercentSingleFormatterHandler(String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
	}
	
	public JygkZzyPercentSingleFormatterHandler(String[] rows) {
		this(rows, null);
	}
	
	public JygkZzyPercentSingleFormatterHandler() {
		this(null, null);
	}


	@Override
	protected void onHandle(JygkZzyExcelTemplate template, HSSFCell cell, String val) {
		cell.setCellValue(String.format("%.1f", Double.valueOf(val)) + "%");
		cell.setCellStyle(template.getCellStylePercent());
	}

}
