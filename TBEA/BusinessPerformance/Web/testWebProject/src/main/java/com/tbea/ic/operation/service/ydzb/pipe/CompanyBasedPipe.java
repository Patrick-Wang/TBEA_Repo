package com.tbea.ic.operation.service.ydzb.pipe;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.IPipeConfigurator;

public class CompanyBasedPipe extends IndicatorBasedPipe {

	private List<IPipeConfigurator> pipeConfigs = new ArrayList<IPipeConfigurator>();
	private IPipeConfigurator dwPipeConfig;
	private List<List<Company>> realCompsList = new ArrayList<List<Company>>();
	private List<Integer> dependZbs = new ArrayList<Integer>();
	
	public CompanyBasedPipe(Integer zb, Date date, IPipeConfigurator dwPipeConfig) {
		super(zb, new ArrayList<Company>(), date, null);
		this.dwPipeConfig = dwPipeConfig;
		this.data.clear();
	}

	public CompanyBasedPipe add(Integer dependZb) {
		if (!dependZbs.contains(dependZb)) {
			dependZbs.add(dependZb);
		}
		return this;
	}
	
	public CompanyBasedPipe add(Company comp, IPipeConfigurator dwPipeConfig) {
		if (!this.companies.contains(comp)) {
			this.companies.add(comp);
			this.pipeConfigs.add(dwPipeConfig);
			List<Company> realComps = new ArrayList<Company>();
			realComps.add(comp);
			this.realCompsList.add(realComps);
		}
		return this;
	}

	public CompanyBasedPipe add(Company comp, IPipeConfigurator dwPipeConfig, List<Company> realComps) {
		if (!this.companies.contains(comp)) {
			this.companies.add(comp);
			this.pipeConfigs.add(dwPipeConfig);
			this.realCompsList.add(realComps);
		}
		return this;
	}
	
	@Override
	public Integer getRowId(int row){
		return this.companies.get(row).getType().ordinal();
	}

	@Override
	public List<Double[]> getData() {
		this.data.clear();
		List<Integer> tmpZbs = new ArrayList<Integer>();
		tmpZbs.addAll(this.dependZbs);
		tmpZbs.add(zbIds.get(0));
		int sizeTmp = tmpZbs.size();
		for (int i = 0, len = this.companies.size(); i < len; ++i) {
			if (this.pipeConfigs.get(i) != null) {
				IndicatorBasedPipe pipe = new IndicatorBasedPipe(tmpZbs,
						this.realCompsList.get(i), date,
						this.pipeConfigs.get(i));
				this.data.add(pipe.getData().get(sizeTmp - 1));
			} else {
				this.data.add(new Double[this.dwPipeConfig.getColumnCount()]);
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
