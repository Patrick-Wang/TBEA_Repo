package com.tbea.ic.operation.common.formatter.v2.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public class ExcelRect implements Rectangle {

	HSSFSheet sheet;
	public ExcelRect(HSSFSheet sheet){
		this.sheet = sheet;
	}
	
	@Override
	public int width() {
		return -1;
	}

	@Override
	public int height() {
		return -1;
	}

	@Override
	public String value(int row, int col) {
		return sheet.getRow(row).getCell(col).getStringCellValue();
	}

	@Override
	public int row() {
		return 0;
	}

	@Override
	public int col() {
		return 0;
	}
}
