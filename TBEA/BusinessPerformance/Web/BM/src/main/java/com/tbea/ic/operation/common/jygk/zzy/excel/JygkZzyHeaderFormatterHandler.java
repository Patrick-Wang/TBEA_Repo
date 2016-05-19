package com.tbea.ic.operation.common.jygk.zzy.excel;
import org.apache.poi.hssf.usermodel.HSSFCell;

public class JygkZzyHeaderFormatterHandler extends JygkZzyAbstractFormatterHandler {

	public JygkZzyHeaderFormatterHandler(String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
	}
	
	public JygkZzyHeaderFormatterHandler(String[] rows) {
		this(rows, null);
	}
	
	public JygkZzyHeaderFormatterHandler() {
		this(null, null);
	}


	@Override
	protected void onHandle(JygkZzyExcelTemplate template, HSSFCell cell, String val) {
		if (val.contains(" ")){
			cell.setCellValue(" " + val.trim());
		}else{
			cell.setCellValue(val);
		}
		cell.setCellStyle(template.getCellStyleHeader());
	}

}
