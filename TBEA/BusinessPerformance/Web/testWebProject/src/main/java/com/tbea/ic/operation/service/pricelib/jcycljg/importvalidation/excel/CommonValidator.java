package com.tbea.ic.operation.service.pricelib.jcycljg.importvalidation.excel;

import org.apache.poi.xssf.usermodel.XSSFCell;

import com.tbea.ic.operation.service.pricelib.jcycljg.importvalidation.ValidationException;

public class CommonValidator extends ValidatorTemplate{


	public CommonValidator(int startRow, int columnCount) {
		super(startRow, columnCount);
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
}
