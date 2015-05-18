package com.tbea.ic.operation.service.ydzb.pipe.configurator.ztzb;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.ydzb.pipe.acc.composite.CompositeAccDataSource;
import com.tbea.ic.operation.service.ydzb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.pipe.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.AbstractCompositeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.filter.complex.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.complex.WclPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.complex.ZzlPipeFilter;

//总体指标
public class ZtzbCompositeConfigurator  extends
AbstractCompositeConfigurator {


	public ZtzbCompositeConfigurator(IAccumulator acc,
			CompositeAccDataSource cads,
			Map<Company, List<Company>> computeMap) {
		super(acc, cads, computeMap);
	}

	@Override
	public int getColumnCount() {
		return 15;
	}

	@Override
	protected void onConfiguring(IPipe pipe, IAccumulator acc, List<Integer> zbs,
			Company type, List<Company> subComps,
			WclPipeFilter wclFilter, ZzlPipeFilter tbzzFilter) {

		// 全年计划
		pipe.addFilter(new AccPipeFilter(acc, 0, zbs, type)
				.includeCompanies(subComps))
				
			// 当月计划
			.addFilter(new AccPipeFilter(acc, 1, zbs, type)
				.includeCompanies(subComps))
		
			
			// 当月实际
			.addFilter(new AccPipeFilter(acc, 2, zbs, type)
				.includeCompanies(subComps))
			
			// 计划完成率
			.addFilter(wclFilter
				.add(3, 2, 1))
			
			// 去年同期	
			.addFilter(new AccPipeFilter(acc, 4, zbs, type)
				.includeCompanies(subComps))
			
			// 同比增幅
			.addFilter(tbzzFilter
				.add(5, 2, 4))
				
			// 季度计划
			.addFilter(new AccPipeFilter(acc, 6, zbs, type)
				.includeCompanies(subComps))
				
			// 季度累计
			.addFilter(new AccPipeFilter(acc, 7, zbs, type)
				.includeCompanies(subComps))
				
			// 季度计划完成率
			.addFilter(wclFilter
				.add(8, 7, 6))
				
			// 季度去年同期
			.addFilter(new AccPipeFilter(acc, 9, zbs, type)
				.includeCompanies(subComps))
			
			 // 同比增幅
			.addFilter(tbzzFilter
				.add(10, 7, 9))
	
			// 年度累计
			.addFilter(new AccPipeFilter(acc, 11, zbs, type)
				.includeCompanies(subComps))

			
		
			// 累计计划完成率
			.addFilter(wclFilter
				.add(12, 11, 0))
			
			// 去年同期
			.addFilter(new AccPipeFilter(acc, 13, zbs, type)
				.includeCompanies(subComps))
	
			// 同比增幅
			.addFilter(tbzzFilter
				.add(14, 11, 13))


			.addFilter(tbzzFilter)
			.addFilter(wclFilter);
		
	}

}
