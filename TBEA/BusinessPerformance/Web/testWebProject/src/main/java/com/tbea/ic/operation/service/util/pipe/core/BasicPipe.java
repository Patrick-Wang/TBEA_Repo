package com.tbea.ic.operation.service.util.pipe.core;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;

public class BasicPipe extends BasePipe{
	
	//*****table data format (only for one company)****
	// ************************************************
	// zb1 -- -- -- -- -- -- -- -- -- -- -- -- -- --
	// zb2 -- -- -- -- -- -- -- -- -- -- -- -- -- --
	// zb3 -- -- -- -- -- -- -- -- -- -- -- -- -- --
	// zb4 -- -- -- -- -- -- -- -- -- -- -- -- -- --
	// zb5 -- -- -- -- -- -- -- -- -- -- -- -- -- --
	// zb6 -- -- -- -- -- -- -- -- -- -- -- -- -- --
	// zb7 -- -- -- -- -- -- -- -- -- -- -- -- -- --
	// ......
	// ************************************************

	public BasicPipe(List<Integer> zbIds, List<Company> companies,
			Date date, IPipeConfigurator pipeConfig) {
		super(zbIds, companies, date);
		if (null != pipeConfig) {
			pipeConfig.onConfiguring(this);
			int size = pipeConfig.getColumnCount();
			for (int i = 0; i < zbIds.size(); ++i) {
				data.add(new Double[size]);
			}
		}
	}

	public BasicPipe(List<Integer> zbIds, Company comp, Date date, IPipeConfigurator pipeConfig) {
		this(zbIds, addComp(new ArrayList<Company>(), comp), date, pipeConfig);
	}
	
	public BasicPipe(Integer zb, Company comp, Date date, IPipeConfigurator pipeConfig) {
		this(addIndicator(new ArrayList<Integer>(), zb), addComp(new ArrayList<Company>(), comp), date, pipeConfig);
	}
	
	public BasicPipe(Integer zb, List<Company> companies, Date date,
			IPipeConfigurator pipeConfig) {
		this(addIndicator(new ArrayList<Integer>(), zb), companies, date, pipeConfig);
	}


	@Override
	public Integer getIndicator(int row){
		return indicators.get(row);
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
