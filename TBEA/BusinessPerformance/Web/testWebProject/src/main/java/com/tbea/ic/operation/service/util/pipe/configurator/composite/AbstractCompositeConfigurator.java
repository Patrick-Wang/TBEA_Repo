package com.tbea.ic.operation.service.util.pipe.configurator.composite;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.acc.composite.CompositeAccDataSource;
import com.tbea.ic.operation.service.util.pipe.acc.composite.CompositeAccumulator;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.util.pipe.filter.composite.WclPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.ZzlPipeFilter;

public abstract class AbstractCompositeConfigurator implements
		IPipeConfigurator {

	
	private Map<Company, List<Company>> computeMap;
	
	private CompositeAccDataSource cads = new CompositeAccDataSource();
	private IAccumulator acc = new CompositeAccumulator(cads);
	public AbstractCompositeConfigurator(Map<Company, List<Company>> computeMap) {
		this.computeMap = computeMap;
	}

	@Override
	public void onConfiguring(IPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		List<Integer> allIndicators = pipe.getIndicators();
		
		WclPipeFilter wclFilter = new WclPipeFilter();
		ZzlPipeFilter tbzzFilter = new ZzlPipeFilter();
		
		this.cads.clear();

		//配置数据源
		for (int i = 0, len = allIndicators.size(); i < len; ++i){
			for (int j = 0, size = allCompanies.size(); j < size; ++j){
				this.cads.add(allCompanies.get(j), allIndicators.get(i), pipe.getRow(i * size + j));
			}
		}

		for (Company comp : computeMap.keySet()){
			wclFilter.includeRow(allCompanies.indexOf(comp), allCompanies.size());
			tbzzFilter.includeRow(allCompanies.indexOf(comp), allCompanies.size());
			onConfiguring(pipe, acc, allIndicators, allCompanies.indexOf(comp), allCompanies.size(), computeMap.get(comp), wclFilter, tbzzFilter);
		}
	}


	protected abstract void onConfiguring(IPipe pipe, IAccumulator acc, List<Integer> zbs, int startRow, int step,
			List<Company> subComps, WclPipeFilter wclFilter, ZzlPipeFilter tbzzFilter);
}
