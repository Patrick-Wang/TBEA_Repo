package com.xml.frame.report.util.v2.core;

import java.util.ArrayList;
import java.util.List;

import com.xml.frame.report.util.EasyList;

public class IndicatorMatcher implements FormatterMatcher{

	public final static FormatterMatcher LEFT1_MATCHER = new IndicatorMatcher(null, new Integer[]{0});
	public final static FormatterMatcher LEFT2_MATCHER = new IndicatorMatcher(null, new Integer[]{0, 1});

	List<String> mRows;
	List<Integer> mCols;
	public IndicatorMatcher(List<String> rows, List<Integer> cols) {
		this.mRows = rows;
		this.mCols = cols;
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
	
	public IndicatorMatcher(String[] rows, Integer[] cols) {
		this(new EasyList<String>(rows).toList(), new EasyList<Integer>(cols).toList());
	}
	
	public IndicatorMatcher(String[] rows) {
		this(new EasyList<String>(rows).toList(), null);
	}
	
	public IndicatorMatcher(){
		
	}
	
	@Override
	public boolean match(List<List<String>> table, int row, int col) {
		if (null == mRows || mRows.contains(table.get(row).get(0))){
			if (null == mCols || mCols.contains(col)){
				return true;
			}
		}
		return false;
	}
}
