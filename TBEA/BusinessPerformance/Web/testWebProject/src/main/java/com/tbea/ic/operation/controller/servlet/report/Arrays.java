package com.tbea.ic.operation.controller.servlet.report;

import java.util.List;

import net.sf.json.JSONArray;

public class Arrays {
	
	public void copyList2Table(List<Object> fromlist, List<Object[]> table,  int col){
		for (int i = 0; i < table.get(col).length; ++i){
			table.get(col)[i] = fromlist.get(i);
		}
	}
	
	public void copyTableCols(List<Object[]> fromTable, int col1, List<Object[]> toTable, int col2){
		toTable.set(col2, fromTable.get(col1));
	}
	
	public void copyList2JsonTable(List<Object> fromlist, JSONArray table,  int col){
		for (int i = 0; i < table.size(); ++i){
			table.getJSONArray(i).set(col, fromlist.get(i));
		}
	}
}
