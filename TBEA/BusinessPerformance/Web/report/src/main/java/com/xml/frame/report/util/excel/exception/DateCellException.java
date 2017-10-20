package com.xml.frame.report.util.excel.exception;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class DateCellException extends ValidationException{

	public static DateCellException raiseException(XSSFCell cell){
		String message = "日期错误:[";
		if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			short df = cell.getCellStyle().getDataFormat();
			message += (cell.getRowIndex() + 1) + "行" + (cell.getColumnIndex() + 1) + "列] [格式码 : " + df + "] [值 : " + cell.getNumericCellValue();
		} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
			short df = cell.getCellStyle().getDataFormat();
			message += (cell.getRowIndex() + 1) + "行" + (cell.getColumnIndex() + 1) + "列] [格式码 : " + df + "] [值 : " + cell.getStringCellValue();
		} else {
			message += (cell.getRowIndex() + 1) + "行" + (cell.getColumnIndex() + 1) + "列] [类型码 : " + cell.getCellType();
		} 
		return new DateCellException(message + "]");
	}


	public DateCellException(String message) {
		super(message);
	}

}
