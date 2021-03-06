package com.xml.frame.report.util.v2.data;

import java.math.BigDecimal;
import java.util.List;

import com.util.tools.MathUtil;
import com.xml.frame.report.util.v2.core.AbstractFormatter;
import com.xml.frame.report.util.v2.core.FormatterMatcher;

public class NumberFormatter extends AbstractFormatter {

	Integer reservedCount = 1;
	boolean trimZero = false;
	boolean currency = false;
	public NumberFormatter(FormatterMatcher matcher,
			Integer reservedCount) {
		super(matcher);
		this.reservedCount = reservedCount;
	} 
	

	public NumberFormatter(Integer reservedCount) {
		this.reservedCount = reservedCount;
	}

	public NumberFormatter trimZero(boolean trimZero){
		this.trimZero = trimZero;
		return this;
	}

	protected String trimZero(String val){
		if (trimZero){
			val = MathUtil.trimZero(val);
		}
		return val;
	}
	
	protected String currency(String val){
		if (currency){
			val = MathUtil.currency(val);
		}
		return val;
	}
	
	@Override
	protected String onHandle(List<List<String>> table, int row, int col, String val) {
		if (!isInvalid(val)){
			BigDecimal b = new BigDecimal(Double.valueOf(val));
			String ret = b.setScale(this.reservedCount, BigDecimal.ROUND_HALF_UP).toPlainString();
			return currency(trimZero(ret));
		}
		return null;
	}


	public void setCurrency(boolean b) {
		currency = b;
	}

}
