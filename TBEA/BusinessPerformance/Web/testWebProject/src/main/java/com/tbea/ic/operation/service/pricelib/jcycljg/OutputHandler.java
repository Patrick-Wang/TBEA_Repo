package com.tbea.ic.operation.service.pricelib.jcycljg;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.service.pricelib.jcycljg.storage.DataStorage;


public class OutputHandler {
	private UtilQuery query;
	@SuppressWarnings("rawtypes")
	private DataStorage storage;
	public OutputHandler(UtilQuery query, DataStorage<?> storage) {
		super();
		this.query = query;
		this.storage = storage;
	}
	
	@SuppressWarnings("unchecked")
	public List<List<String>> handle(Date start, Date end){
		return storage.stringify(query.query(start, end));
	}
};
