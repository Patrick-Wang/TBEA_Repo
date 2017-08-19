package com.xml.frame.report.util.excel;
import org.apache.poi.hssf.usermodel.HSSFCell;

import com.xml.frame.report.util.ExcelHelper;

public class HeaderCenterFormatterHandler extends TextFormatterHandler {

	public HeaderCenterFormatterHandler(String[] rows, Integer[] cols) {
		super(rows, cols);
	}
	
	public HeaderCenterFormatterHandler(String[] rows) {
		this(rows, null);
	}
	
	public HeaderCenterFormatterHandler() {
		this(null, null);
	}


	@Override
	protected String onHandle(ExcelHelper template, HSSFCell cell, String val) {
		String ret = super.onHandle(template, cell, val);
		cell.setCellStyle(template.getCellStyleCenterHeader());
		return ret;
	}

}
