package com.tbea.ic.operation.common.formatter.raw;

import java.util.List;

public class RawFormatterClient {
	RawFormatterHandler handler;

	public RawFormatterClient(RawFormatterHandler handler) {
		super();
		this.handler = handler;
	}
	
	public List<List<String>> doHandle(List<List<String>> table){
		for (int i = 0; i < table.size(); ++i){
			for (int j = 0; j < table.get(i).size(); ++j){
				table.get(i).set(j, handler.handle(table.get(i).get(0), j, table.get(i).get(j)));
			}
		}
		return table;
	}
}
