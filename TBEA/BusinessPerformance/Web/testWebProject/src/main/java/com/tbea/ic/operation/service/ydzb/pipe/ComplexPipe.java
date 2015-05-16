package com.tbea.ic.operation.service.ydzb.pipe;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.IPipeConfigurator;

public class ComplexPipe extends BasePipe{

	
	//*****table data format (for multiple company)****
	// ************************************************
	// zb1 company1 -- -- -- -- -- -- -- -- -- -- -- --
	// zb1 company2 -- -- -- -- -- -- -- -- -- -- -- --
	// zb1 company3 -- -- -- -- -- -- -- -- -- -- -- --
	// zb1 companyN -- -- -- -- -- -- -- -- -- -- -- --
	// zb2 company1 -- -- -- -- -- -- -- -- -- -- -- --
	// zb2 company2 -- -- -- -- -- -- -- -- -- -- -- --
	// zb2 company3 -- -- -- -- -- -- -- -- -- -- -- --
	// zb2 companyN -- -- -- -- -- -- -- -- -- -- -- --
	// zb3 company1 -- -- -- -- -- -- -- -- -- -- -- --
	// zb3 company2 -- -- -- -- -- -- -- -- -- -- -- --
	// zb3 company3 -- -- -- -- -- -- -- -- -- -- -- --
	// zb3 companyN -- -- -- -- -- -- -- -- -- -- -- --
	// ......
	// ************************************************
	
	
	private List<IPipeConfigurator> pipeConfigs = new ArrayList<IPipeConfigurator>();
	private IPipeConfigurator dwPipeConfig;
	private List<List<Company>> realCompsList = new ArrayList<List<Company>>();
	private List<Integer> dependIndicators = new ArrayList<Integer>();
	
	public ComplexPipe(Integer indicator, Date date, IPipeConfigurator dwPipeConfig) {
		super(indicator, new ArrayList<Company>(), date);
		this.dwPipeConfig = dwPipeConfig;
	}
	
	public ComplexPipe(List<Integer> indicators, Date date, IPipeConfigurator dwPipeConfig) {
		super(indicators, new ArrayList<Company>(), date);
		this.dwPipeConfig = dwPipeConfig;
	}

	public ComplexPipe add(Integer dependIndicator) {
		if (!dependIndicators.contains(dependIndicator)) {
			dependIndicators.add(dependIndicator);
		}
		return this;
	}
	
	public ComplexPipe add(Company comp, IPipeConfigurator dwPipeConfig) {
		if (!this.companies.contains(comp)) {
			this.companies.add(comp);
			this.pipeConfigs.add(dwPipeConfig);
			List<Company> realComps = new ArrayList<Company>();
			realComps.add(comp);
			this.realCompsList.add(realComps);
		}
		return this;
	}

	public ComplexPipe add(Company comp, IPipeConfigurator dwPipeConfig, List<Company> realComps) {
		if (!this.companies.contains(comp)) {
			this.companies.add(comp);
			this.pipeConfigs.add(dwPipeConfig);
			this.realCompsList.add(realComps);
		}
		return this;
	}
	
	@Override
	public Integer getRowId(int row){
		return this.companies.get(row % this.companies.size()).getType().ordinal();
	}

	
	private List<Double[]> create(int size){
		List<Double[]> ret = new ArrayList<Double[]>();
		for (int i = 0; i < size; ++i){
			ret.add(null);
		}
		return ret;
	}
	
	private List<Double[]> create(int size, int width){
		List<Double[]> ret = new ArrayList<Double[]>();
		for (int i = 0; i < size; ++i){
			ret.add(new Double[width]);
		}
		return ret;
	}
	
	private void addList(int from, int step, List<Double[]> tmpData){
		for (int i = 0, len = tmpData.size(); i < len; ++i){
			this.data.set(from + i * step, tmpData.get(i));
		}
	}
	
	@Override
	public List<Double[]> getData() {
		this.data = create(this.companies.size() * indicators.size());
		List<Integer> tmpIndicators = new ArrayList<Integer>();
		tmpIndicators.addAll(this.dependIndicators);
		tmpIndicators.addAll(indicators);
		int indicatorsSize = indicators.size();
		int dependIndicatorsSize = dependIndicators.size();
		int tmpZbSize = indicatorsSize + dependIndicatorsSize;
		for (int i = 0, len = this.companies.size(); i < len; ++i) {
			if (this.pipeConfigs.get(i) != null) {
				SimplePipe pipe = new SimplePipe(tmpIndicators,
						this.realCompsList.get(i), date,
						this.pipeConfigs.get(i));
				addList(i, len, pipe.getData().subList(dependIndicatorsSize, tmpZbSize));
			} else {
				addList(i, len, create(indicators.size(), this.dwPipeConfig.getColumnCount()));
			}
		}

		this.dwPipeConfig.onConfiguring(this);
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
