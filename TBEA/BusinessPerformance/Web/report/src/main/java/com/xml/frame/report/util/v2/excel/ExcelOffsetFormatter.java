package com.xml.frame.report.util.v2.excel;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.v2.core.AbstractFormatter;
import com.xml.frame.report.util.v2.core.FormatterMatcher;
import com.xml.frame.report.util.v2.core.Offset;

public class ExcelOffsetFormatter extends AbstractFormatter {

	protected ExcelHelper template;
	protected Offset offset;
	
	public ExcelOffsetFormatter(FormatterMatcher matcher,
			ExcelHelper template, Offset offset) {
		super(matcher);
		this.template = template;
		this.offset = offset;
	}
	
	public ExcelOffsetFormatter(ExcelHelper template, Offset offset) {
		this.template = template;
		this.offset = offset;
	}
	
	protected HSSFCell getCell(int row, int col){
		row += offset.getRow();
		col += offset.getCol();
		HSSFSheet sheet = template.getSheet();
		HSSFRow hssfRow = sheet.getRow(row);
		if (null == hssfRow){
			hssfRow = sheet.createRow(row);
		}
		
		HSSFCell cell = hssfRow.getCell(col);
		if (null == cell){
			cell = hssfRow.createCell(col);
		}
		return cell;
	}

	@Override
	protected String onHandle(List<List<String>> table, int row, int col,
			String val) {
		HSSFCell cell = this.getCell(row, col);
		cell.setCellValue(val);
		cell.setCellStyle(template.getCellStyleDefault());
		return val;
	}
	
}
