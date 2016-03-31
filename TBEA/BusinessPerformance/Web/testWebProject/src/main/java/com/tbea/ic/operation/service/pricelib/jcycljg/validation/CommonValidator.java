package com.tbea.ic.operation.service.pricelib.jcycljg.validation;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class CommonValidator extends ValidatorTemplate{

	public CommonValidator(int columnCount) {
		super(columnCount);
	}

	@Override
	Object checkCell(int row, int col, XSSFCell cell) throws ValidationException  {
		try {
			if (col > 0){
				return parseNumber(cell);
			}else{
				return parseDate(cell);
			}
		}catch(Exception e){
			throw new ValidationException(row + "行 " + col + "列: " +  e.getMessage());
		}
	}

	@Override
	int checkStartRow(XSSFSheet sheet) throws ValidationException {
		if (sheet.getLastRowNum() < 2){
			throw new ValidationException("表格中没有可导入数据");
		}
		return 2;
	}

	
	
}
