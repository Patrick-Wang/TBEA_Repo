package com.tbea.ic.operation.common.formatter.v2.excel;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.formatter.v2.core.FormatterMatcher;
import com.tbea.ic.operation.common.formatter.v2.core.Offset;

public class ExcelTextFormatter extends ExcelOffsetFormatter {

	public ExcelTextFormatter(FormatterMatcher matcher,
			ExcelTemplate template, Offset offset) {
		super(matcher, template, offset);
	}

	@Override
	protected String onHandle(List<List<String>> table, int row, int col, String val) {
		HSSFCell cell = this.getCell(row, col);;
		cell.setCellValue(val);
		cell.setCellStyle(template.getCellStyleText());
		return val;
	}

}
