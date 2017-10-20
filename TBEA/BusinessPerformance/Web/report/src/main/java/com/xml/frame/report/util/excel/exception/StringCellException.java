package com.xml.frame.report.util.excel.exception;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class StringCellException extends ValidationException{

	public static StringCellException raiseException(XSSFCell cell){
		String message = "字符串错误:[" + (cell.getRowIndex() + 1) + "行" + (cell.getColumnIndex() + 1) + "列] [类型码 : " + cell.getCellType() + "]";
		return new StringCellException(message);
	}


	public StringCellException(String message) {
		super(message);
	}

}
