package com.tbea.ic.operation.reportframe.interpreter.excel;

import java.sql.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;

import com.tbea.ic.operation.service.pricelib.jcycljg.storage.validation.ValidationException;

public class ExcelUtil{

	public static Date parseDate(XSSFCell cell) throws ValidationException{
		if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			try{
				java.util.Date date = cell.getDateCellValue();
				return new Date(date.getTime());
			}catch(Exception e){
				e.printStackTrace();
				short df = cell.getCellStyle().getDataFormat();
				throw new ValidationException("日期解析失败，数据格式编码" + df);
			}
		}
		throw new ValidationException("日期解析失败，类型编码 " + cell.getCellType());
	}
	
	public static Double parseNumber(XSSFCell cell) throws ValidationException{
		switch(cell.getCellType()){
		case XSSFCell.CELL_TYPE_BLANK:
			return null;
		case XSSFCell.CELL_TYPE_NUMERIC:
		case XSSFCell.CELL_TYPE_FORMULA:
			return cell.getNumericCellValue();
		}
		throw new ValidationException("数值类型解析失败 ，类型编码  " + cell.getCellType());
	}
	
	public static String parseString(XSSFCell cell) throws ValidationException{
		switch(cell.getCellType()){
		case XSSFCell.CELL_TYPE_BLANK:
			return null;
		case XSSFCell.CELL_TYPE_NUMERIC:
		case XSSFCell.CELL_TYPE_FORMULA:
			return "" + cell.getNumericCellValue();
		case XSSFCell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		}
		throw new ValidationException("数值类型解析失败 ，类型编码  " + cell.getCellType());
	}
}
