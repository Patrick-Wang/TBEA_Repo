package com.xml.frame.report.util.v2.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.v2.core.AbstractFilter;
import com.xml.frame.report.util.v2.core.FormatterMatcher;
import com.xml.frame.report.util.v2.core.Offset;

public abstract class ExcelAbstractOffsetFilter extends AbstractFilter {

	protected ExcelHelper template;
	protected Offset offset;
	
	public ExcelAbstractOffsetFilter(FormatterMatcher matcher,
			ExcelHelper template, Offset offset) {
		this.template = template;
		this.offset = offset;
	}
	
	public ExcelAbstractOffsetFilter(ExcelHelper template, Offset offset) {
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
}
