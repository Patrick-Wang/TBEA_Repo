package com.tbea.ic.operation.common.formatter.raw;

import java.math.BigDecimal;

import org.apache.poi.hssf.usermodel.HSSFCell;

public class RawNumberFormatterHandler extends RawAbstractFormatterHandler {

	public enum NumberType {
		RESERVE_0, RESERVE_1, RESERVE_2, RESERVE_4
	}

	NumberType type;

	public RawNumberFormatterHandler(NumberType type, String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
		this.type = type;
	}

	public RawNumberFormatterHandler(NumberType type, String[] rows) {
		this(type, rows, null);
	}

	public RawNumberFormatterHandler(NumberType type) {
		this(type, null, null);
	}

	@Override
	protected String onHandle(String val) {
		BigDecimal b = new BigDecimal(Double.valueOf(val));
		String ret = val;
		switch (type) {
		case RESERVE_0:
			ret = b.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
			break;
		case RESERVE_1:
			ret = b.setScale(1, BigDecimal.ROUND_HALF_UP).toString();
			break;
		case RESERVE_2:
			ret = b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			break;
		case RESERVE_4:
			ret = b.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
			break;
		default:
			break;
		}
		return ret;
	}

}
