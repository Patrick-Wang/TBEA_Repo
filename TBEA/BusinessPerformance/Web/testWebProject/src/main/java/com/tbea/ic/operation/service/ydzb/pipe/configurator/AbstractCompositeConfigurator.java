package com.tbea.ic.operation.service.ydzb.pipe.configurator;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.acc.composite.CompositeAccDataSource;
import com.tbea.ic.operation.service.ydzb.pipe.filter.advanced.WclPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.advanced.ZzlPipeFilter;

public abstract class AbstractCompositeConfigurator implements
		IPipeConfigurator {

	
	private Map<Company, List<Company>> computeMap;
	private IAccumulator acc;
	CompositeAccDataSource cads;
	public AbstractCompositeConfigurator(IAccumulator acc, CompositeAccDataSource cads, Map<Company, List<Company>> computeMap) {
		this.acc = acc;
		this.cads = cads;
		this.computeMap = computeMap;
	}

	@Override
	public void onConfiguring(IPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		List<Integer> allZbs = pipe.getIndicators();
		
		WclPipeFilter wclFilter = new WclPipeFilter();
		ZzlPipeFilter tbzzFilter = new ZzlPipeFilter();
		
		this.cads.clear();
		//配置数据源
		for (int i = 0, len = allZbs.size(); i < len; ++i){
			for (int j = 0, size = allCompanies.size(); j < size; ++j){
				this.cads.add(allCompanies.get(j), allZbs.get(i), pipe.getRow(i * size + j));
			}
		}
		
		for (Company type : computeMap.keySet()){
			wclFilter.include(type);
			tbzzFilter.include(type);
			onConfiguring(pipe, acc, allZbs, type, computeMap.get(type), wclFilter, tbzzFilter);
		}
	}


	protected abstract void onConfiguring(IPipe pipe, IAccumulator acc, List<Integer> zbs, Company type,
			List<Company> subComps, WclPipeFilter wclFilter, ZzlPipeFilter tbzzFilter);
}
