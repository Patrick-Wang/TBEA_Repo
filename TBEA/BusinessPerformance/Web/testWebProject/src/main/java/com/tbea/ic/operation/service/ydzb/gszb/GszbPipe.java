package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public class GszbPipe {
	List<IPipeFilter> filters = new ArrayList<IPipeFilter>();
	List<Double[]> data = new ArrayList<Double[]>();
	List<Integer> zbIds;
	List<Company> companies;
	Date date;
	public GszbPipe(List<Integer> zbIds, int colSize, List<Company> companies, Date date) {
		this.companies = companies;
		this.date = date;
		this.zbIds = zbIds;
		for (int i = 0; i < zbIds.size(); ++i){
			data.add(new Double[colSize]);
		}
	}
	
	public Integer getZbId(int row){
		return zbIds.get(row);
	}

	public Date getDate() {
		return date;
	}

	public List<Integer> getZbIds() {
		return zbIds;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public GszbPipe add(IPipeFilter filter) {
		filters.add(filter);
		return this;
	}

	public Double[] getZb(int row) {
		return data.get(row);
	}

	public List<Double[]> getGszb() {
		for (int i = 0, len = filters.size(); i < len; ++i) {
			for (int j = 0, size = filters.size(); j < size; ++j) {
				filters.get(j).filter(i, this);
			}
		}
		return data;
	}
}
