package com.xml.frame.report.util.v2.excel;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.v2.core.AbstractFormatter;
import com.xml.frame.report.util.v2.core.FormatterMatcher;

public class ExcelRandomFormatter extends AbstractFormatter {

	protected ExcelHelper template;
	protected Map<Integer, Integer> colMap;
	protected Map<Integer, Integer> rowMap;
	public ExcelRandomFormatter(FormatterMatcher matcher,
			ExcelHelper template, Map<Integer, Integer> rowMap, Map<Integer, Integer> colMap) {
		super(matcher);
		this.template = template;
		this.colMap = colMap;
		this.rowMap = rowMap;
	}
	
	public ExcelRandomFormatter(ExcelHelper template, Map<Integer, Integer> rowMap, Map<Integer, Integer> colMap) {
		super(null);
		this.template = template;
		this.colMap = colMap;
		this.rowMap = rowMap;
	}
	
	protected HSSFCell getCell(int row, int col){
		if (null != colMap){
			if (colMap.containsKey(col)){
				col = colMap.get(col);
			}
		}
		
		if (null != rowMap){
			if (rowMap.containsKey(row)){
				row = rowMap.get(row);
			}
		}
		
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
