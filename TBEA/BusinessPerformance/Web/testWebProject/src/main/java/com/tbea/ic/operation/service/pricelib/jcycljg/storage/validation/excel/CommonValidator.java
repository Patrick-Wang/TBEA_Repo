package com.tbea.ic.operation.service.pricelib.jcycljg.storage.validation.excel;

import org.apache.poi.xssf.usermodel.XSSFCell;

import com.tbea.ic.operation.service.pricelib.jcycljg.storage.validation.ValidationException;

public class CommonValidator extends ValidatorTemplate{


	public CommonValidator(int startRow, int columnCount) {
		super(startRow, columnCount);
	}

	@Override
	Object checkCell(int row, int col, XSSFCell cell) throws ValidationException  {
		Object objRet = null;
		if (null == cell){
			if (col == 0){
				throw new ValidationException((row + 1) + "行 " + (col + 1) + "列: 日期不能为空");
			}
		}else{
			try {
				if (col > 0){
					objRet = parseNumber(cell);
				}else{
					objRet = parseDate(cell);
				}
			}catch(Exception e){
				throw new ValidationException((row + 1) + "行 " + (col + 1) + "列: " +  e.getMessage());
			}
		}
		return objRet;
		
	}
}
