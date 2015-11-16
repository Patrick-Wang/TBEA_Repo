package com.tbea.ic.operation.common.jygk.zzy.excel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;

public abstract class JygkZzyAbstractFormatterHandler implements JygkZzyFormatterHandler {

	JygkZzyFormatterHandler mNextHandler;
	List<String> mRows;
	List<Integer> mCols;
	
	public JygkZzyAbstractFormatterHandler(List<String> rows, List<Integer> cols) {
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
	public JygkZzyFormatterHandler next(JygkZzyFormatterHandler handler) {
		mNextHandler = handler;
		return mNextHandler;
	}

	protected void callNext(String zbName, Integer col, JygkZzyExcelTemplate template, HSSFCell cell, String val){
		if (null != mNextHandler){
			this.mNextHandler.handle(zbName, col, template, cell, val);
		}
	}
	
	protected boolean match(String zbName, Integer col){
		if (null == mRows || mRows.contains(zbName)){
			if (null == mCols || mCols.contains(col)){
				return true;
			}
		}
		return false;
	}
	
	abstract protected void onHandle(JygkZzyExcelTemplate template, HSSFCell cell, String val);
	
	@Override
	public void handle(String zbName, Integer col, JygkZzyExcelTemplate template,
			HSSFCell cell, String val) {
		if (val != null) {
			if (match(zbName, col)) {
				onHandle(template, cell, val);
			} else {
				callNext(zbName, col, template, cell, val);
			}
		} else {
			cell.setCellValue("--");
			cell.setCellStyle(template.getCellStyleNull());
		}
	}

	
}
