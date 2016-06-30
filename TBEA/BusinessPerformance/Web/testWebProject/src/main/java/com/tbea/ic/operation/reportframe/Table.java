package com.tbea.ic.operation.reportframe;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.DataNode;
import com.tbea.ic.operation.common.Util;

public class Table {
	DataNode header;
	String name;
	List<List<Object>> values;
	List<Integer> ids;
	
	public DataNode getHeader() {
		return header;
	}
	
	public void setHeader(DataNode header) {
		this.header = header;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<List<String>> getMatrix() {
		List<List<String>> matrix = new ArrayList<List<String>>();
		for (int i = 0; i < ids.size(); ++i){
			List<String> tmpList = new ArrayList<String>();
			tmpList.add("" + ids.get(i));
			for (int j = 0; j < values.size(); ++j){
				tmpList.add(values.get(j).get(i) + "");
			}
			matrix.add(tmpList);
		}
		return matrix;
	}
	
	public void setValues(List<List<Object>> values) {
		this.values = values;
	}
	
	public List<Integer> getIds() {
		return ids;
	}
	
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public List<List<Object>> getValues() {
		return values;
	}
	
	
}
