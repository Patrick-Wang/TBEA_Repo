package com.tbea.ic.operation.common.jyzbexcel;

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
	protected void onHandle(JyzbExcelTemplate template, HSSFCell cell, String val) {
		cell.setCellValue(Double.valueOf(val));
		cell.setCellStyle(template.getCellStylePercent());
	}

}
