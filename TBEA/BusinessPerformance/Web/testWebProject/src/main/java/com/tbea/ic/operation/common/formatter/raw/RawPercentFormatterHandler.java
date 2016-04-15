package com.tbea.ic.operation.common.formatter.raw;

import org.apache.poi.hssf.usermodel.HSSFCell;

public class RawPercentFormatterHandler extends RawAbstractFormatterHandler {

	public RawPercentFormatterHandler(String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
	}

	public RawPercentFormatterHandler(String[] rows) {
		this(rows, null);
	}

	public RawPercentFormatterHandler() {
		this(null, null);
	}

	@Override
	protected String onHandle(String val) {
		String ret = String.format("%.1f", Double.valueOf(val) * 100) + "%";
		return ret;
	}

}
