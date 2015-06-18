package com.tbea.ic.operation.service.util.pipe.core;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;

public interface IPipe {

	public Date getDate();

	public Integer getRowId(int row);
	
	public List<Integer> getIndicators();

	public List<Company> getCompanies();
	
	public IPipe addFilter(IPipeFilter filter) ;

	public List<Double[]> getData();
	
	public int getColumnCount();
	
	public int getRowCount();
	
	public Double[] getRow(int indicator, Company comp);
	
	public Double[] getRow(int row);
}