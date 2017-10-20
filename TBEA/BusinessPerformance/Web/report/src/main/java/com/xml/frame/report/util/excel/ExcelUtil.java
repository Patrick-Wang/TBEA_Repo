package com.xml.frame.report.util.excel;

import java.sql.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;

import com.frame.script.util.TypeUtil;
import com.xml.frame.report.util.excel.exception.DateCellException;
import com.xml.frame.report.util.excel.exception.NumberCellException;
import com.xml.frame.report.util.excel.exception.StringCellException;
import com.xml.frame.report.util.excel.exception.ValidationException;

public class ExcelUtil{

	public static Date parseDate(XSSFCell cell) throws ValidationException{
		if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			try{
				java.util.Date date = cell.getDateCellValue();
				return new Date(date.getTime());
			}catch(Exception e){
				e.printStackTrace();
				throw DateCellException.raiseException(cell);
				//throw new ValidationException(cell.getRowIndex() + "行" + cell.getColumnIndex() + "列 日期解析失败，数据格式编码 :" + df + " 值 :" + cell.getNumericCellValue());
			}
		} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
			try{
				String sDate = cell.getStringCellValue();
				return Date.valueOf(sDate.replace('/', '-'));
			}catch(Exception e){
				e.printStackTrace();
				throw DateCellException.raiseException(cell);
				//short df = cell.getCellStyle().getDataFormat();
				//throw new ValidationException(cell.getRowIndex() + "行" + cell.getColumnIndex() + "列 日期解析失败，数据格式编码 :" + df + " 值 :" + cell.getStringCellValue());
			}
		} else if (cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
			return null;
		} 
		throw DateCellException.raiseException(cell);
		//throw new ValidationException(cell.getRowIndex() + "行" + cell.getColumnIndex() + "列 日期解析失败，类型编码 " + cell.getCellType() + " 值 :" + cell.toString());
	}
	
	public static Double parseNumber(XSSFCell cell) throws ValidationException{
		switch(cell.getCellType()){
		case XSSFCell.CELL_TYPE_BLANK:
			return null;
		case XSSFCell.CELL_TYPE_NUMERIC:
		case XSSFCell.CELL_TYPE_FORMULA:
			return cell.getNumericCellValue();
		case XSSFCell.CELL_TYPE_STRING:
			return TypeUtil.toDoubleNull(cell.getStringCellValue());
		}
		throw NumberCellException.raiseException(cell);
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
		throw StringCellException.raiseException(cell);
		//new ValidationException(cell.getRowIndex() + "行" + cell.getColumnIndex() + "列 数值类型解析失败 ，类型编码  " + cell.getCellType() + " 值 :" + cell.toString());
	}
}
