package com.tbea.ic.operation.service.ydzb.gszb.pipe;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.IPipeFilter;

public class IndicatorBasedPipe implements IPipe{
	
	protected List<IPipeFilter> filters = new ArrayList<IPipeFilter>();
	protected List<Double[]> data = new ArrayList<Double[]>();
	protected List<Integer> zbIds;
	protected List<Company> companies;
	protected Date date;

	public IndicatorBasedPipe(List<Integer> zbIds, List<Company> companies,
			Date date, IPipeConfigurator pipeConfig) {
		this.companies = companies;
		this.zbIds = zbIds;
		this.date = date;
		if (null != pipeConfig) {
			pipeConfig.onConfiguring(this);
			int size = pipeConfig.getColumnCount();
			for (int i = 0; i < zbIds.size(); ++i) {
				data.add(new Double[size]);
			}
		}
	}

	public IndicatorBasedPipe(List<Integer> zbIds, Company comp, Date date, IPipeConfigurator pipeConfig) {
		this(zbIds, addComp(new ArrayList<Company>(), comp), date, pipeConfig);
	}
	
	public IndicatorBasedPipe(Integer zb, Company comp, Date date, IPipeConfigurator pipeConfig) {
		this(addzb(new ArrayList<Integer>(), zb), addComp(new ArrayList<Company>(), comp), date, pipeConfig);
	}
	
	public IndicatorBasedPipe(Integer zb, List<Company> companies, Date date,
			IPipeConfigurator pipeConfig) {
		this(addzb(new ArrayList<Integer>(), zb), companies, date, pipeConfig);
	}
	
	protected static List<Integer> addzb(List<Integer> zbIds, Integer zb){
		zbIds.add(zb);
		return zbIds;
	}
	
	protected static List<Company> addComp(List<Company> comps, Company comp){
		comps.add(comp);
		return comps;
	}

	@Override
	public Integer getRowId(int row){
		return zbIds.get(row);
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public List<Integer> getZbIds() {
		return zbIds;
	}

	@Override
	public List<Company> getCompanies() {
		return companies;
	}
	
	@Override
	public IPipe add(IPipeFilter filter) {
		if (filters.contains(filter)){
			filters.remove(filter);
		}
		filters.add(filter);
		return this;
	}

	@Override
	public Double[] getData(int row) {
		return data.get(row);
	}
	
	@Override
	public int getColumnCount(){
		if (this.data.isEmpty()){
			return 0;
		}
		return this.data.get(0).length;
	}
	
	@Override
	public int getRowCount(){
		return this.data.size();
	}

	@Override
	public List<Double[]> getData() {
		for (int i = 0, len = data.size(); i < len; ++i) {
			for (int j = 0, size = filters.size(); j < size; ++j) {
				try {
					filters.get(j).filter(i, this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
}
