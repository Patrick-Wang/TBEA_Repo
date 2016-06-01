package com.tbea.ic.operation.common.formatter.v2.core;

import java.util.List;


public abstract class AbstractFormatterHandler implements FormatterHandler {

	FormatterHandler mNextHandler;
	FormatterMatcher matcher;

	public AbstractFormatterHandler(FormatterMatcher matcher) {
		super();
		this.matcher = matcher;
	}
	
	public AbstractFormatterHandler() {
	}


	protected boolean isNull(String val){
		return val == null || "null".equals(val);
	}
	

	@Override
	public FormatterHandler next(FormatterHandler handler) {
		mNextHandler = handler;
		return mNextHandler;
	}

	protected String callNext(List<List<String>> table, int row, int col, String val){
		if (null != mNextHandler){
			return this.mNextHandler.handle(table, row, col, val);
		}
		return val;
	}
	
	abstract protected String onHandle(List<List<String>> table, int row, int col, String val);
	
	@Override
	public String handle(List<List<String>> table, int row, int col, String val) {
		String ret = null;
		val = isNull(val) ? null : val;
		if (null == matcher || matcher.match(table, row, col)) {
			ret =  onHandle(table, row, col, val);
		} else {
			ret = callNext(table, row, col, val);
		}
		return ret;
	}

}
