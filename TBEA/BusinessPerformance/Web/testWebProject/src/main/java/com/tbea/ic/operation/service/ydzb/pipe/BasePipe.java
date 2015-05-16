package com.tbea.ic.operation.service.ydzb.pipe;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.ydzb.pipe.filter.IPipeFilter;

public abstract class BasePipe implements IPipe{
	
	protected List<Double[]> data = new ArrayList<Double[]>();
	protected List<IPipeFilter> filters = new ArrayList<IPipeFilter>();
	protected List<Integer> indicators;
	protected List<Company> companies;
	protected Date date;

	public BasePipe(List<Integer> indicators, List<Company> companies,
			Date date) {
		this.companies = companies;
		this.indicators = indicators;
		this.date = date;
	}

	public BasePipe(List<Integer> indicators, Company comp, Date date) {
		this(indicators, addComp(new ArrayList<Company>(), comp), date);
	}
	
	public BasePipe(Integer indicator, Company comp, Date date) {
		this(addzb(new ArrayList<Integer>(), indicator), addComp(new ArrayList<Company>(), comp), date);
	}
	
	public BasePipe(Integer indicator, List<Company> companies, Date date) {
		this(addzb(new ArrayList<Integer>(), indicator), companies, date);
	}
	
	protected static List<Integer> addzb(List<Integer> indicators, Integer indicator){
		indicators.add(indicator);
		return indicators;
	}
	
	protected static List<Company> addComp(List<Company> comps, Company comp){
		comps.add(comp);
		return comps;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public List<Integer> getIndicators() {
		return indicators;
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
	public Double[] getRow(int row) {
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
}
