package com.xml.frame.report.util.raw;

public class RawTextFormatterHandler extends RawAbstractFormatterHandler {

	public RawTextFormatterHandler(String[] rows, Integer[] cols) {
		super(toList(rows), toList(cols));
	}
	
	public RawTextFormatterHandler(String[] rows) {
		this(rows, null);
	}
	
	public RawTextFormatterHandler() {
		this(null, null);
	}


	@Override
	protected String onHandle(String val) {
		String ret = val;
		if (val.contains(" ")){
			ret = " " + val.trim();
		}
		return ret;
	}

}
