package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.ArrayList;
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

	
	private Map<CompanyType, CompanyType[]> computeMap;
	private IAccumulator acc;
	CompositeAccDataSource cads;
	public AbstractCompositeConfigurator(IAccumulator acc, CompositeAccDataSource cads, Map<CompanyType, CompanyType[]> computeMap) {
		this.acc = acc;
		this.cads = cads;
		this.computeMap = computeMap;
	}


	private List<Company> toCompanies(CompanyType[] types, List<Company> companies){
		List<Company> ret = new ArrayList<Company>();
		for (int i = 0; i < companies.size(); ++i){
			for (int j = 0; j < types.length; ++j){
				if (types[j] == companies.get(i).getType()){
					ret.add(companies.get(i));
				}
			}
		}
		return ret;
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
			List<Company> subComps = toCompanies(computeMap.get(type), allCompanies);
			onConfiguring(pipe, acc, zb, type, subComps, wclFilter, tbzzFilter);
		}
	}


	protected abstract void onConfiguring(IPipe pipe, IAccumulator acc, Integer zb, CompanyType type,
			List<Company> subComps, WclPipeFilter wclFilter, ZzlPipeFilter tbzzFilter);
}
