package com.tbea.ic.operation.common.jyzbexcel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;

public abstract class AbstractFormatterHandler implements FormatterHandler {

	FormatterHandler mNextHandler;
	List<String> mRows;
	List<Integer> mCols;
	
	public AbstractFormatterHandler(List<String> rows, List<Integer> cols) {
		super();
		this.mRows = rows;
		this.mCols = cols;
	}
	
	protected static List<Integer> toList(Integer[] rows){
		List<Integer> ret = new ArrayList<Integer>();
		for (int i = 0; i < rows.length; ++i){
			ret.add(rows[i]);
		}
		return ret;
	}
	
	protected static List<String> toList(String[] rows){
		List<String> ret = new ArrayList<String>();
		for (int i = 0; i < rows.length; ++i){
			ret.add(rows[i]);
		}
		return ret;
	}

	@Override
	public FormatterHandler next(FormatterHandler handler) {
		mNextHandler = handler;
		return mNextHandler;
	}

	protected void callNext(String zbName, Integer col, JyzbExcelTemplate template, HSSFCell cell, String val){
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
	
	abstract protected void onHandle(JyzbExcelTemplate template, HSSFCell cell, String val);
	
	@Override
	public void handle(String zbName, Integer col, JyzbExcelTemplate template, HSSFCell cell, String val) {
		if (match(zbName, col)){
			if (val != null){
				onHandle(template, cell, val);
			}else{
				cell.setCellValue("--");
				cell.setCellStyle(template.getCellStyleNull());
			}
		} else{
			callNext(zbName, col, template, cell, val);
		}
	}

	
}
