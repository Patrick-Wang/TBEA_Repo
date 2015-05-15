package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.acc.composite.CompositeAccDataSource;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.companybased.WclPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.companybased.ZzlPipeFilter;

public abstract class AbstractCompositeConfigurator implements
		IPipeConfigurator {

	
	private Map<CompanyType, List<Company>> computeMap;
	private IAccumulator acc;
	CompositeAccDataSource cads;
	public AbstractCompositeConfigurator(IAccumulator acc, CompositeAccDataSource cads, Map<CompanyType, List<Company>> computeMap) {
		this.acc = acc;
		this.cads = cads;
		this.computeMap = computeMap;
	}

	@Override
	public void onConfiguring(IPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		Integer zb = pipe.getZbIds().get(0);
		
		WclPipeFilter wclFilter = new WclPipeFilter();
		ZzlPipeFilter tbzzFilter = new ZzlPipeFilter();
		
		this.cads.clear();
		//配置数据源
		for (int i = 0; i < pipe.getRowCount(); ++i){
			this.cads.add(allCompanies.get(i), zb, pipe.getData(i));
		}

		for (CompanyType type : computeMap.keySet()){
			wclFilter.include(type);
			tbzzFilter.include(type);
			onConfiguring(pipe, acc, zb, type, computeMap.get(type), wclFilter, tbzzFilter);
		}
	}


	protected abstract void onConfiguring(IPipe pipe, IAccumulator acc, Integer zb, CompanyType type,
			List<Company> subComps, WclPipeFilter wclFilter, ZzlPipeFilter tbzzFilter);
}
