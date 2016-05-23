package com.tbea.ic.operation.common.formatter.raw;

import java.math.BigDecimal;

import com.tbea.ic.operation.common.MathUtil;

public class RawNumberFormatterHandler extends RawAbstractFormatterHandler {

	Integer reservedCount = 1;
	boolean trimZero = false;
	
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
	
	public RawNumberFormatterHandler trimZero(boolean trimZero){
		this.trimZero = trimZero;
		return this;
	}

	@Override
	protected String onHandle(String val) {
		BigDecimal b = new BigDecimal(Double.valueOf(val));
		String ret = b.setScale(this.reservedCount, BigDecimal.ROUND_HALF_UP).toPlainString();
		if (trimZero){
			ret = MathUtil.trimZero(ret);
		}
		return ret;
	}

}
