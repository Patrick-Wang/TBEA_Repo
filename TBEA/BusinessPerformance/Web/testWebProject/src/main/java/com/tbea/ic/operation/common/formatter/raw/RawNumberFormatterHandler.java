package com.tbea.ic.operation.common.formatter.raw;

import java.math.BigDecimal;

public class RawNumberFormatterHandler extends RawAbstractFormatterHandler {

	Integer reservedCount;

	public RawNumberFormatterHandler(Integer reservedCount, String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
		this.reservedCount = reservedCount;
	}

	public RawNumberFormatterHandler(Integer reservedCount, String[] rows) {
		this(reservedCount, rows, null);
	}

	public RawNumberFormatterHandler(Integer reservedCount) {
		this(reservedCount, null, null);
	}

	@Override
	protected String onHandle(String val) {
		BigDecimal b = new BigDecimal(Double.valueOf(val));
		String ret = b.setScale(this.reservedCount, BigDecimal.ROUND_HALF_UP).toString();
//		String ret = val;
//		switch (type) {
//		case RESERVE_0:
//			
//			break;
//		case RESERVE_1:
//			ret = b.setScale(1, BigDecimal.ROUND_HALF_UP).toString();
//			break;
//		case RESERVE_2:
//			ret = b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
//			break;
//		case RESERVE_4:
//			ret = b.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
//			break;
//		default:
//			break;
//		}
		return ret;
	}

}
