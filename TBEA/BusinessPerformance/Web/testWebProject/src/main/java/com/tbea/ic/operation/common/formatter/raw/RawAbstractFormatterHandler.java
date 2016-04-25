package com.tbea.ic.operation.common.formatter.raw;

import java.util.ArrayList;
import java.util.List;

public abstract class RawAbstractFormatterHandler implements RawFormatterHandler {

	RawFormatterHandler mNextHandler;
	List<String> mRows;
	List<Integer> mCols;
	
	public RawAbstractFormatterHandler(List<String> rows, List<Integer> cols) {
		super();
		this.mRows = rows;
		this.mCols = cols;
	}
	
	protected static List<Integer> toList(Integer[] rows){
		if (null == rows){
			return null;
		}
		List<Integer> ret = new ArrayList<Integer>();
		for (int i = 0; i < rows.length; ++i){
			ret.add(rows[i]);
		}
		return ret;
	}
	
	protected static List<String> toList(String[] rows){
		if (null == rows){
			return null;
		}
		List<String> ret = new ArrayList<String>();
		for (int i = 0; i < rows.length; ++i){
			ret.add(rows[i]);
		}
		return ret;
	}

	@Override
	public RawFormatterHandler next(RawFormatterHandler handler) {
		mNextHandler = handler;
		return mNextHandler;
	}

	protected String callNext(String zbName, Integer col, String val){
		if (null != mNextHandler){
			return this.mNextHandler.handle(zbName, col, val);
		}
		return val;
	}
	
	protected boolean match(String zbName, Integer col){
		if (null == mRows || mRows.contains(zbName)){
			if (null == mCols || mCols.contains(col)){
				return true;
			}
		}
		return false;
	}
	
	abstract protected String onHandle(String val);
	
	@Override
	public String handle(String zbName, Integer col, String val) {
		if (val != null) {
			if (match(zbName, col)) {
				return onHandle(val);
			} else {
				return callNext(zbName, col, val);
			}
		} else {
			return "--";
		}
	}

	
}
