package com.tbea.ic.operation.common.formatter.excel;
import org.apache.poi.hssf.usermodel.HSSFCell;

import com.tbea.ic.operation.common.excel.ExcelTemplate;

public class HeaderCenterFormatterHandler extends AbstractFormatterHandler {

	public HeaderCenterFormatterHandler(String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
	}
	
	public HeaderCenterFormatterHandler(String[] rows) {
		this(rows, null);
	}
	
	public HeaderCenterFormatterHandler() {
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
		cell.setCellStyle(template.getCellStyleCenterHeader());
		return val;
	}

}
