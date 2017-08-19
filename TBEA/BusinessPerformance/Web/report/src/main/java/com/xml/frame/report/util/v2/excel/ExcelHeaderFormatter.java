package com.xml.frame.report.util.v2.excel;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.v2.core.FormatterMatcher;
import com.xml.frame.report.util.v2.core.Offset;

public class ExcelHeaderFormatter extends ExcelOffsetFormatter {

	public ExcelHeaderFormatter(FormatterMatcher matcher,
			ExcelHelper template, Offset offset) {
		super(matcher, template, offset);
	}

	@Override
	protected String onHandle(List<List<String>> table, int row, int col,
			String val) {
		HSSFCell cell = this.getCell(row, col);;
		cell.setCellValue(val);
		cell.setCellStyle(template.getCellStyleHeader());
		return val;
	}

}
