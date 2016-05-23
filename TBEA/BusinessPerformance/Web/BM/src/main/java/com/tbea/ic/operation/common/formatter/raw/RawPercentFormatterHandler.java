package com.tbea.ic.operation.common.formatter.raw;


public class RawPercentFormatterHandler extends RawAbstractFormatterHandler {

	Integer reservedCount = 1;
	
	public RawPercentFormatterHandler(Integer reservedCount, String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
		this.reservedCount = reservedCount;
	}
	
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
		String ret = String.format("%." + reservedCount + "f", Double.valueOf(val) * 100) + "%";
		return ret;
	}

}
