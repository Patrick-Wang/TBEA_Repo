package com.tbea.ic.operation.common.formatter.raw;

import java.util.List;

public class RawFormatterClient {
	RawFormatterHandler handler;

	public RawFormatterClient(RawFormatterHandler handler) {
		super();
		this.handler = handler;
	}
	
	String nullAs = "--";
	
	public RawFormatterClient acceptNullAs(String nullAs){
		this.nullAs = nullAs;
		return this;
	}
	
	public List<List<String>> format(List<List<String>> table){
		String val = null;
		for (int i = 0; i < table.size(); ++i){
			for (int j = 0; j < table.get(i).size(); ++j){
				val = handler.handle(table.get(i).get(0), j, table.get(i).get(j));
				if (val == null){
					val = nullAs;
				}
				table.get(i).set(j, val);
			}
		}
		return table;
	}
}
