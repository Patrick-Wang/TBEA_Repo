package com.tbea.ic.operation.common.excel;
import org.apache.poi.hssf.usermodel.HSSFCell;

public class HeaderFormatterHandler extends AbstractFormatterHandler {

	public HeaderFormatterHandler(String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
	}
	
	public HeaderFormatterHandler(String[] rows) {
		this(rows, null);
	}
	
	public HeaderFormatterHandler() {
		this(null, null);
	}


	@Override
	protected void onHandle(ExcelTemplate template, HSSFCell cell, String val) {
		if (val.contains(" ")){
			cell.setCellValue(" " + val.trim());
		}else{
			cell.setCellValue(val);
		}
		cell.setCellStyle(template.getCellStyleHeader());
	}

}
