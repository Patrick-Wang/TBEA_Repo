package com.tbea.ic.operation.common.jygk.zzy.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;

public class JygkZzyPercentFormatterHandler extends JygkZzyAbstractFormatterHandler {

	public JygkZzyPercentFormatterHandler(String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
	}
	
	public JygkZzyPercentFormatterHandler(String[] rows) {
		this(rows, null);
	}
	
	public JygkZzyPercentFormatterHandler() {
		this(null, null);
	}


	@Override
	protected void onHandle(JygkZzyExcelTemplate template, HSSFCell cell, String val) {
		cell.setCellValue(String.format("%.2f", Double.valueOf(val) * 100) + "%");
		cell.setCellStyle(template.getCellStylePercent());
	}

}
