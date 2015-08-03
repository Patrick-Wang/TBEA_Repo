package com.tbea.ic.operation.common.jyzbexcel;
import java.util.List;
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
	protected void onHandle(JyzbExcelTemplate template, HSSFCell cell, String val) {
		if (val.contains(" ")){
			cell.setCellValue(" " + val.trim());
		}else{
			cell.setCellValue(val);
		}
		cell.setCellStyle(template.getCellStyleHeader());
	}

}
