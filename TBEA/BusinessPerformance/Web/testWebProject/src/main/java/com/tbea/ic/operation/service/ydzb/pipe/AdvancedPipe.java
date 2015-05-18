package com.tbea.ic.operation.service.ydzb.pipe;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.IPipeConfigurator;

public class AdvancedPipe extends BasePipe{

	
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
	
	
	private List<IPipeConfigurator> companyPipeConfigs = new ArrayList<IPipeConfigurator>();
	private IPipeConfigurator advancedPipeConfig;
	private List<List<Company>> basicCompanies = new ArrayList<List<Company>>();
	private List<Integer> dependIndicators = new ArrayList<Integer>();
	
	public AdvancedPipe(Integer indicator, Date date, IPipeConfigurator advancedPipeConfig) {
		super(indicator, new ArrayList<Company>(), date);
		this.advancedPipeConfig = advancedPipeConfig;
	}
	
	public AdvancedPipe(List<Integer> indicators, Date date, IPipeConfigurator advancedPipeConfig) {
		super(indicators, new ArrayList<Company>(), date);
		this.advancedPipeConfig = advancedPipeConfig;
	}

	public AdvancedPipe addDependentIndictor(Integer dependIndicator) {
		if (!dependIndicators.contains(dependIndicator)) {
			dependIndicators.add(dependIndicator);
		}
		return this;
	}
	
	public AdvancedPipe addCompany(Company comp, IPipeConfigurator dwPipeConfig) {
		if (!this.companies.contains(comp)) {
			this.companies.add(comp);
			this.companyPipeConfigs.add(dwPipeConfig);
			List<Company> realComps = new ArrayList<Company>();
			realComps.add(comp);
			this.basicCompanies.add(realComps);
		}
		return this;
	}

	public AdvancedPipe addCompany(Company comp, IPipeConfigurator dwPipeConfig, List<Company> realComps) {
		if (!this.companies.contains(comp)) {
			this.companies.add(comp);
			this.companyPipeConfigs.add(dwPipeConfig);
			this.basicCompanies.add(realComps);
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
		tmpIndicators.addAll(dependIndicators);
		tmpIndicators.addAll(indicators);
		int dependIndicatorsSize = dependIndicators.size();
		int tmpIndicatorSize = tmpIndicators.size();
		for (int i = 0, len = this.companies.size(); i < len; ++i) {
			if (this.companyPipeConfigs.get(i) != null) {
				BasicPipe pipe = new BasicPipe(tmpIndicators,
						this.basicCompanies.get(i), date,
						this.companyPipeConfigs.get(i));
				addList(i, len, pipe.getData().subList(dependIndicatorsSize, tmpIndicatorSize));
			} else {
				addList(i, len, create(indicators.size(), this.advancedPipeConfig.getColumnCount()));
			}
		}

		this.advancedPipeConfig.onConfiguring(this);
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

	@Override
	public Double[] getRow(int indicator, Company comp) {
		return getRow(this.companies.indexOf(comp) + this.indicators.indexOf(indicator) * this.companies.size());
	}
}
