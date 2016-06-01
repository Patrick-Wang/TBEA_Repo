package com.tbea.ic.operation.common.formatter.v2.word;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.formatter.v2.core.AbstractFormatterHandler;
import com.tbea.ic.operation.common.formatter.v2.core.FormatterMatcher;
import com.tbea.ic.operation.common.formatter.v2.core.Offset;

public class WordOffsetFormatter extends AbstractFormatterHandler {

	protected ExcelTemplate template;
	protected Offset offset;
	
	public WordOffsetFormatter(FormatterMatcher matcher,
			ExcelTemplate template, Offset offset) {
		super(matcher);
		this.template = template;
		this.offset = offset;
	}
	
	public WordOffsetFormatter(ExcelTemplate template, Offset offset) {
		this.template = template;
		this.offset = offset;
	}
	
	protected HSSFCell getCell(int row, int col){
		return template.getSheet().getRow(offset.getRow() + row).getCell(offset.getCol() + col);
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
