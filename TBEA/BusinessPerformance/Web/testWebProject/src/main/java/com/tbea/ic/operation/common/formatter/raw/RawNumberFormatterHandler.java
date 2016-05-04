package com.tbea.ic.operation.common.formatter.raw;

import java.math.BigDecimal;

public class RawNumberFormatterHandler extends RawAbstractFormatterHandler {

	Integer reservedCount;
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
			int index = ret.indexOf('.');
			if (index > 0){
				int len = ret.length() - 1;
				while (len > index){
					if (ret.charAt(len) != '0'){
						break;
					}
					--len;
				}
				
				if (len == index){
					ret = ret.substring(0, index);
				}else if (len < ret.length() - 1){
					ret = ret.substring(0, len + 1);
				}
			}
		}
		return ret;
	}

}
