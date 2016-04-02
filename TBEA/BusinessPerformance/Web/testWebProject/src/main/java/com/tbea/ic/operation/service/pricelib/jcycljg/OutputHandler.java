package com.tbea.ic.operation.service.pricelib.jcycljg;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.service.pricelib.jcycljg.storage.DataStringify;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.StorageAssemble;


public class OutputHandler {
	private UtilQuery query;
	@SuppressWarnings("rawtypes")
	private DataStringify ds;
	public OutputHandler(JcycljgType jcycljgType, UtilQuery query, StorageAssemble sa) {
		super();
		this.query = query;
		this.ds = sa.getStringify(jcycljgType);
	}
	
	@SuppressWarnings("unchecked")
	public List<List<String>> handle(Date start, Date end){
		return ds.stringify(query.query(start, end));
	}
};
