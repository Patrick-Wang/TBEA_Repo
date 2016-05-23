package com.tbea.ic.operation.common.formatter.excel;

import java.math.BigDecimal;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.tbea.ic.operation.common.excel.ExcelTemplate;

public class NumberFormatterHandler extends AbstractFormatterHandler {

	Integer reservedCount = 1;

	public NumberFormatterHandler(Integer reservedCount, String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
		this.reservedCount = reservedCount;
	}

	public NumberFormatterHandler(Integer reservedCount, String[] rows) {
		this(reservedCount, rows, null);
	}

	public NumberFormatterHandler(Integer reservedCount) {
		this(reservedCount, null, null);
	}

	@Override
	protected String onHandle(ExcelTemplate template, HSSFCell cell, String val) {
		// System.out.println(val);
		BigDecimal b = new BigDecimal(Double.valueOf(val));
		String ret = b.setScale(this.reservedCount, BigDecimal.ROUND_HALF_UP).toPlainString();
		cell.setCellValue(ret);
		cell.setCellStyle(template.getCellStyleDefault());
	
		// System.out.println(b);
		// System.out.println(cell);
//		switch (type) {
//		case NumberType.RESERVE_0:
//			
//			break;
//		case RESERVE_1:
//			ret = b.setScale(1, BigDecimal.ROUND_HALF_UP).toString();
//			cell.setCellValue(ret);
//			cell.setCellStyle(template.getCellStyleNumber1());
//			break;
//		case RESERVE_2:
//			ret = b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
//			cell.setCellValue(ret);
//			cell.setCellStyle(template.getCellStyleNumber2());
//			break;
//		case RESERVE_4:
//			ret = b.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
//			cell.setCellValue(ret);
//			cell.setCellStyle(template.getCellStyleNumber4());
//			break;
//		default:
//			break;
//		}
		return ret;
	}

}
