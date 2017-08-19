package com.xml.frame.report.util.v2.core;

import java.util.List;

import com.xml.frame.report.util.EasyList;

public class DefaultMatcher implements FormatterMatcher{

	public final static FormatterMatcher LEFT1_MATCHER = new DefaultMatcher(null, new Integer[]{0});
	public final static FormatterMatcher LEFT2_MATCHER = new DefaultMatcher(null, new Integer[]{0, 1});
	
	List<Integer> mRows;
	List<Integer> mCols;
	public DefaultMatcher(List<Integer> rows, List<Integer> cols) {
		this.mRows = rows;
		this.mCols = cols;
	}

	public DefaultMatcher(Integer[] rows, Integer[] cols) {
		this(new EasyList<Integer>(rows).toList(), new EasyList<Integer>(cols).toList());
	}
	
	public DefaultMatcher(Integer[] rows) {
		this(new EasyList<Integer>(rows).toList(), null);
	}
	
	public DefaultMatcher(){
		
	}
	
	@Override
	public boolean match(List<List<String>> table, int row, int col) {
		if (null == mRows || mRows.contains(row)){
			if (null == mCols || mCols.contains(col)){
				return true;
			}
		}
		return false;
	}
}
