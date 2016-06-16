package com.tbea.ic.operation.common.formatter.v2.core;

import java.util.List;


public abstract class AbstractFilter implements FormatterHandler {

	FormatterHandler mNextHandler;

	protected boolean isInvalid(String val){
		return val == null || "null".equals(val) || val.isEmpty();
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
	
	abstract protected void onFilter(List<List<String>> table, int row, int col, String val);
	
	@Override
	public String handle(List<List<String>> table, int row, int col, String val) {
		onFilter(table, row, col, val);
		return callNext(table, row, col, val);
	}

}
