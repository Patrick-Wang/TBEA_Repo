package com.tbea.ic.operation.common.formatter.excel;
import org.apache.poi.hssf.usermodel.HSSFCell;

import com.tbea.ic.operation.common.excel.ExcelTemplate;

public class TextFormatterHandler extends AbstractFormatterHandler {

	public TextFormatterHandler(String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
	}
	
	public TextFormatterHandler(String[] rows) {
		this(rows, null);
	}
	
	public TextFormatterHandler() {
		this(null, null);
	}

	@Override
	protected String onHandle(ExcelTemplate template, HSSFCell cell, String val) {
		String ret = val;
		if (val.contains(" ")){
			ret = " " + val.trim();
			cell.setCellValue(ret);
		}else{
			cell.setCellValue(val);
		}
		cell.setCellStyle(template.getCellStyleText());
		return ret;
	}

}
