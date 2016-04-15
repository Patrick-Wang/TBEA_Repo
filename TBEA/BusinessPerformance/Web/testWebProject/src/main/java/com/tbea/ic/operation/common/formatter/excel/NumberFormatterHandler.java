package com.tbea.ic.operation.common.formatter.excel;

import java.math.BigDecimal;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.tbea.ic.operation.common.excel.ExcelTemplate;

public class NumberFormatterHandler extends AbstractFormatterHandler {

	public enum NumberType {
		RESERVE_0, RESERVE_1, RESERVE_2, RESERVE_4
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
	protected String onHandle(ExcelTemplate template, HSSFCell cell, String val) {
		// System.out.println(val);
		BigDecimal b = new BigDecimal(Double.valueOf(val));
		String ret = val;
		// System.out.println(b);
		// System.out.println(cell);
		switch (type) {
		case RESERVE_0:
			ret = b.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
			cell.setCellValue(ret);
			cell.setCellStyle(template.getCellStyleNumber0());
			break;
		case RESERVE_1:
			ret = b.setScale(1, BigDecimal.ROUND_HALF_UP).toString();
			cell.setCellValue(ret);
			cell.setCellStyle(template.getCellStyleNumber1());
			break;
		case RESERVE_2:
			ret = b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			cell.setCellValue(ret);
			cell.setCellStyle(template.getCellStyleNumber2());
			break;
		case RESERVE_4:
			ret = b.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
			cell.setCellValue(ret);
			cell.setCellStyle(template.getCellStyleNumber4());
			break;
		default:
			break;
		}
		return ret;
	}

}
