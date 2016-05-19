package com.tbea.ic.operation.common.formatter.raw;

public class RawPercentSingleFormatterHandler extends RawAbstractFormatterHandler {

	public RawPercentSingleFormatterHandler(String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
	}

	public RawPercentSingleFormatterHandler(String[] rows) {
		this(rows, null);
	}

	public RawPercentSingleFormatterHandler() {
		this(null, null);
	}

	@Override
	protected String onHandle(String val) {
		return String.format("%.1f", Double.valueOf(val)) + "%";
	}

}
