package com.tbea.ic.operation.service.ydzb.gszb.pipe;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.IPipeFilter;

public class GszbPipe {
	List<IPipeFilter> filters = new ArrayList<IPipeFilter>();
	List<Double[]> data = new ArrayList<Double[]>();
	List<Integer> zbIds;
	List<Company> companies;
	Date date;
	public GszbPipe(List<Integer> zbIds, List<Company> companies, Date date, IPipeConfigurator pipeConfig) {
		this.companies = companies;
		this.zbIds = zbIds;
		this.date = date;
		pipeConfig.onConfiguring(this);
		int size = pipeConfig.getColumnCount();
		for (int i = 0; i < zbIds.size(); ++i){
			data.add(new Double[size]);
		}
	}
	
	public GszbPipe(List<Integer> zbIds, Company comp, Date date, IPipeConfigurator pipeConfig) {
		this(zbIds, new ArrayList<Company>(), date, pipeConfig);
		this.companies.add(comp);
	}
	
	public GszbPipe(Integer zb, Company comp, Date date, IPipeConfigurator pipeConfig) {
		this(new ArrayList<Integer>(), new ArrayList<Company>(), date, pipeConfig);
		this.companies.add(comp);
		this.zbIds.add(zb);
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
		if (filters.contains(filter)){
			filters.remove(filter);
		}
		filters.add(filter);
		return this;
	}

	public Double[] getZb(int row) {
		return data.get(row);
	}

	public List<Double[]> getGszb() {
		for (int i = 0, len = data.size(); i < len; ++i) {
			for (int j = 0, size = filters.size(); j < size; ++j) {
				try {
					filters.get(j).filter(i, this);
				} catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
		return data;
	}
}
