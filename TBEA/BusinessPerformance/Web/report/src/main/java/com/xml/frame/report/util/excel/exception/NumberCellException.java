package com.xml.frame.report.util.excel.exception;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class NumberCellException extends ValidationException{

	public static NumberCellException raiseException(XSSFCell cell){
		String message = "数值错误:[" + (cell.getRowIndex() + 1) + "行" + (cell.getColumnIndex() + 1) + "列] [类型码  : " + cell.getCellType() + "]";
		return new NumberCellException(message);
	}


	public NumberCellException(String message) {
		super(message);
	}

}
