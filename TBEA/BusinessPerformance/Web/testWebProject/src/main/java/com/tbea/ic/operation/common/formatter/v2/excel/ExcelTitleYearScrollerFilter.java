package com.tbea.ic.operation.common.formatter.v2.excel;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.util.CellRangeAddress;

import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.formatter.v2.core.Offset;

public class ExcelTitleYearScrollerFilter extends ExcelAbstractOffsetFilter {

	Date date;
	public ExcelTitleYearScrollerFilter(ExcelTemplate template, Offset offset, Date d) {
		super(null, template, offset);
		this.date = d;
	}

	@Override
	protected void onFilter(List<List<String>> table, int row, int col,
			String val) {
		if (null != this.date){
			Calendar endMonth = Calendar.getInstance();
			endMonth.setTime(date);
			
			Calendar startMonth = Calendar.getInstance();
			startMonth.setTime(date);
			startMonth.add(Calendar.YEAR, -1);
	
			Calendar yearEndMonth = Calendar.getInstance();
			yearEndMonth.setTime(date);
			yearEndMonth.add(Calendar.YEAR, -1);
			yearEndMonth.set(Calendar.MONTH, 11);
			
			int i = 0;
			int last = 0;
			while (!startMonth.after(endMonth)){
				HSSFCell cell = this.getCell(0, i);
				cell.setCellStyle(template.getCellStyleCenter());
				if (startMonth.after(yearEndMonth)){
					cell.setCellValue("本年度");
				}else{
					cell.setCellValue("上年度");
					++last;
				}
				
				cell =  this.getCell(1, i);
				cell.setCellValue((startMonth.get(Calendar.MONTH) + 1) + "月");
				cell.setCellStyle(template.getCellStyleCenter());
				startMonth.add(Calendar.MONTH, 1);
				++i;
			}
			this.date = null;
			template.getSheet().addMergedRegion(new CellRangeAddress(
					this.offset.getRow(), 
					this.offset.getRow(), 
					offset.getCol(), 
					offset.getCol() + last - 1));
			template.getSheet().addMergedRegion(new CellRangeAddress(
					this.offset.getRow(), 
					this.offset.getRow(), 
					offset.getCol() + last, 
					offset.getCol() + i - 1));
		}
	}
}
