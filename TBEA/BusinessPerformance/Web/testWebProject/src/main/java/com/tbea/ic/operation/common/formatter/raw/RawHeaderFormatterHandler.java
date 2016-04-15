package com.tbea.ic.operation.common.formatter.raw;

public class RawHeaderFormatterHandler extends RawAbstractFormatterHandler {

	public RawHeaderFormatterHandler(String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
	}
	
	public RawHeaderFormatterHandler(String[] rows) {
		this(rows, null);
	}
	
	public RawHeaderFormatterHandler() {
		this(null, null);
	}


	@Override
	protected String onHandle(String val) {
		String ret = val;
		if (val.contains(" ")){
			ret = " " + val.trim();
		}
		return val;
	}

}
