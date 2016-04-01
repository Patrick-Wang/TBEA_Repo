package com.tbea.ic.operation.service.pricelib.jcycljg.importvalidation.excel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.tbea.ic.operation.service.pricelib.jcycljg.importvalidation.ValidationException;

public abstract class ValidatorTemplate implements FormatValidator{
	
	int columnCount;
	int startRow;
	
	public ValidatorTemplate(int startRow, int columnCount) {
		this.columnCount = columnCount;
		this.startRow = startRow;
	}

	
	public Date parseDate(XSSFCell cell) throws ValidationException{
		if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			short df = cell.getCellStyle().getDataFormat();
			if (14 == df) {
				java.util.Date date = cell.getDateCellValue();
				return new Date(date.getTime());
			}
		}
		throw new ValidationException("日期解析失败");
	}
	
	public double parseNumber(XSSFCell cell) throws ValidationException{
		if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			return cell.getNumericCellValue();
		}
		throw new ValidationException("数值类型解析失败 ");
	}

	public List<Object[]> validate(XSSFSheet sheet) throws ValidationException{
		
		if (sheet.getLastRowNum() < startRow){
			throw new ValidationException("表格中没有可导入数据");
		}
		
		List<Object[]> result = new ArrayList<Object[]>();
		for (int i = sheet.getFirstRowNum() + startRow; i <= sheet.getLastRowNum(); ++i) {
			Object[] objs = new Object[columnCount];
			XSSFRow row = sheet.getRow(i);			
			int start = row.getFirstCellNum();//返回索引
			int end = row.getLastCellNum();//相当于列总数
			if ((end - start) != columnCount){
				throw new ValidationException("第" + (i + 1) + "行数据列数不匹配");
			}
			for (int j = start; j < end; ++j){
				objs[j] = checkCell(i, j, row.getCell(j));
			}
			result.add(objs);
		}
		return result;
	}


	abstract Object checkCell(int row, int col, XSSFCell cell) throws ValidationException;
}
