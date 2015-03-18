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
	
	private static List<Integer> addzb(List<Integer> zbIds, Integer zb){
		zbIds.add(zb);
		return zbIds;
	}
	
	private static List<Company> addComp(List<Company> comps, Company comp){
		comps.add(comp);
		return comps;
	}
	
	public GszbPipe(List<Integer> zbIds, Company comp, Date date, IPipeConfigurator pipeConfig) {
		this(zbIds, addComp(new ArrayList<Company>(), comp), date, pipeConfig);
	}
	
	public GszbPipe(Integer zb, Company comp, Date date, IPipeConfigurator pipeConfig) {
		this(addzb(new ArrayList<Integer>(), zb), addComp(new ArrayList<Company>(), comp), date, pipeConfig);
	}
	
	public GszbPipe(Integer zb, List<Company> sbdhj, Date date,
			IPipeConfigurator pipeConfig) {
		this.companies = sbdhj;

		this.zbIds = new ArrayList<Integer>();
		this.zbIds.add(zb);
		this.date = date;
		int size = pipeConfig.getColumnCount();
		for (int i = 0; i < zbIds.size(); ++i){
			data.add(new Double[size]);
		}
		pipeConfig.onConfiguring(this);
	}

	public Integer getZbId(int row){
		return zbIds.get(row);
	}

	public Date getDate() {
		return date;
	}

	public Integer getRow(Integer zbId){
		return zbIds.indexOf(zbId);
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
