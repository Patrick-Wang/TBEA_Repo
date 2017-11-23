package com.xml.frame.report.util.v2.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.util.tools.Pair;
import com.xml.frame.report.util.EasyList;

public class FormatterServer {
	
	public static class HandlerBuilder{
		FormatterHandler current;
		FormatterServer serv;
		
		public HandlerBuilder(FormatterServer serv){
			this.serv = serv;
		}
		
		public HandlerBuilder add(FormatterHandler h){
			if (this.current != null){
				this.serv.add(current);
			}
			this.current = h;
			return this;
		}
		
		public HandlerBuilder to(int group){
			this.serv.add(current, group);
			current = null;
			return this;
		}
		
		public FormatterServer server(){
			if (this.current != null){
				this.serv.add(current);
			}
			this.current = null;
			return serv;
		}
	}
	
	
	public final static int GROP_DATA = 0;
	public final static int GROP_EXCEL = 1;
	public final static int GROP_EXCELMERGE = 2;
	public final static int GROP_WORD = 3;
	
	Map<Integer, Pair<FormatterHandler, FormatterHandler>> groupHandler = 
			new HashMap<Integer, Pair<FormatterHandler, FormatterHandler>>();
	String nullAs = "--";
	
	public FormatterServer add(FormatterHandler handler, int group){
		if (groupHandler.containsKey(group)){
			groupHandler.get(group).getSecond().next(handler);
			groupHandler.get(group).setSecond(handler);
		}else{
			groupHandler.put(group, new Pair<FormatterHandler, FormatterHandler>(handler, handler));
		}
		return this;
	}
	
	public HandlerBuilder handlerBuilder(){
		return new HandlerBuilder(this);
	}
	
	public FormatterServer add(FormatterHandler handler){
		return add(handler, GROP_DATA);
	}
	
	
	public FormatterServer acceptNullAs(String nullAs){
		this.nullAs = nullAs;
		return this;
	}
	
	public List<List<String>> format(List<List<String>> table){
		String result = null;
		Object cell = null;
		List<String> row;
		for (int i = 0, r = table.size(); i < r; ++i){
			row = table.get(i);
			for (int j = 0, c = row.size(); j < c; ++j){
				for (Entry<Integer, Pair<FormatterHandler, FormatterHandler>> entry : groupHandler.entrySet()){
					cell = row.get(j);
					if (!(cell instanceof String)) {
						cell = "" + cell;
					}
					result = entry.getValue().getFirst().handle(table, i, j, (String) cell);
					if (null == result){
						result = nullAs;
					}
					if (cell != result){
						row.set(j, result);
					}
				}
			}
		}
		return table;
	}
	
	public List<List<String>> formatArray(List table){
		List<List<String>> tableCopy = new ArrayList<List<String>>(table.size());
		for (int i = 0, len = table.size(); i < len; ++i){
			if (table.get(i).getClass().isArray()){
				tableCopy.add(new EasyList((Object[]) table.get(i)).toList());
			}else{
				tableCopy.add((List<String>) table.get(i));
			}
		}
		return format(tableCopy);
	}
	
	public List<String> formatRow(List<String> list){
		List<List<String>> table = new ArrayList<List<String>>(1);
		table.add(list);
		format(table);
		return list;		
	}

	List table;
	public void setTable(List<List<String>> value) {
		this.table = value;
	}

	public List<List<String>> getResult(){
		if (!table.isEmpty()){
			if (table.get(0).getClass().isArray()){
				return this.formatArray(table);
			}
		}
		return this.format(table);
	}



}
