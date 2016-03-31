package com.tbea.ic.operation.service.pricelib.jcycljg.excelimport.validation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

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
		
		if (columnCount != sheet.getRow(startRow).getLastCellNum()){
			throw new ValidationException("数据列数不匹配: 数据列数应为" + columnCount + "列，输入数据为" + sheet.getRow(startRow).getLastCellNum() + "列");
		}
		
		List<Object[]> result = new ArrayList<Object[]>();
		for (int i = startRow; i <= sheet.getLastRowNum(); ++i) {
			Object[] objs = new Object[columnCount];
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < columnCount; ++j){
				objs[j] = checkCell(i, j, row.getCell(j));
			}
			result.add(objs);
		}
		return result;
	}


	abstract Object checkCell(int row, int col, XSSFCell cell) throws ValidationException;
}
