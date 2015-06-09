package com.tbea.ic.operation.common.jyzbexcel;

import java.math.BigDecimal;

import org.apache.poi.hssf.usermodel.HSSFCell;

public class NumberFormatterHandler extends AbstractFormatterHandler {

	public enum NumberType{
		RESERVE_0,
		RESERVE_1,
		RESERVE_2,
		RESERVE_4
	}

	NumberType type;

	public NumberFormatterHandler(NumberType type, String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
		this.type = type;
	}

	public NumberFormatterHandler(NumberType type, String[] rows) {
		this(type, rows, null);
	}

	public NumberFormatterHandler(NumberType type) {
		this(type, null, null);
	}

	@Override
	protected void onHandle(JyzbExcelTemplate template, HSSFCell cell, String val) {
		System.out.println(val);
		BigDecimal   b   =   new   BigDecimal(Double.valueOf(val));
		System.out.println(b);
		System.out.println(cell);
		switch(type){
		case RESERVE_0:
			cell.setCellValue(b.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			cell.setCellStyle(template.getCellStyleNumber0());
			break;
		case RESERVE_1:
			
			cell.setCellValue(b.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
			cell.setCellStyle(template.getCellStyleNumber1());
			break;
		case RESERVE_2:
			cell.setCellValue(b.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			cell.setCellStyle(template.getCellStyleNumber2());
			break;
		case RESERVE_4:
			cell.setCellValue(b.setScale(4, BigDecimal.ROUND_HALF_UP).toString());
			cell.setCellStyle(template.getCellStyleNumber4());
			break;
		default:
			break;
		}
	}

}
