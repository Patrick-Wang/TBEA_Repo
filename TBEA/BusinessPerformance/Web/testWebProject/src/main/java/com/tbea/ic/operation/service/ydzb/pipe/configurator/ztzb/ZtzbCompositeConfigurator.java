package com.tbea.ic.operation.service.ydzb.pipe.configurator.ztzb;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.acc.composite.CompositeAccDataSource;
import com.tbea.ic.operation.service.util.pipe.configurator.composite.AbstractCompositeConfigurator;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.composite.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.WclPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.ZzlPipeFilter;

//总体指标
public class ZtzbCompositeConfigurator  extends
AbstractCompositeConfigurator {


	public ZtzbCompositeConfigurator(
			Map<Company, List<Company>> computeMap) {
		super(computeMap);
	}

	@Override
	public int getColumnCount() {
		return 15;
	}

	@Override
	protected void onConfiguring(IPipe pipe, IAccumulator acc, List<Integer> zbs,
			int startRow, int step, List<Company> subComps,
			WclPipeFilter wclFilter, ZzlPipeFilter tbzzFilter) {

		// 全年计划
		pipe.addFilter(new AccPipeFilter(acc, 0, zbs, startRow, step)
				.includeCompanies(subComps))
				
			// 当月计划
			.addFilter(new AccPipeFilter(acc, 1, zbs, startRow, step)
				.includeCompanies(subComps))
		
			
			// 当月实际
			.addFilter(new AccPipeFilter(acc, 2, zbs, startRow, step)
				.includeCompanies(subComps))
			
			// 计划完成率
			.addFilter(wclFilter
				.add(3, 2, 1))
			
			// 去年同期	
			.addFilter(new AccPipeFilter(acc, 4, zbs, startRow, step)
				.includeCompanies(subComps))
			
			// 同比增幅
			.addFilter(tbzzFilter
				.add(5, 2, 4))
				
			// 季度计划
			.addFilter(new AccPipeFilter(acc, 6, zbs, startRow, step)
				.includeCompanies(subComps))
				
			// 季度累计
			.addFilter(new AccPipeFilter(acc, 7, zbs, startRow, step)
				.includeCompanies(subComps))
				
			// 季度计划完成率
			.addFilter(wclFilter
				.add(8, 7, 6))
				
			// 季度去年同期
			.addFilter(new AccPipeFilter(acc, 9, zbs, startRow, step)
				.includeCompanies(subComps))
			
			 // 同比增幅
			.addFilter(tbzzFilter
				.add(10, 7, 9))
	
			// 年度累计
			.addFilter(new AccPipeFilter(acc, 11, zbs, startRow, step)
				.includeCompanies(subComps))

			
		
			// 累计计划完成率
			.addFilter(wclFilter
				.add(12, 11, 0))
			
			// 去年同期
			.addFilter(new AccPipeFilter(acc, 13, zbs, startRow, step)
				.includeCompanies(subComps))
	
			// 同比增幅
			.addFilter(tbzzFilter
				.add(14, 11, 13))


			.addFilter(tbzzFilter)
			.addFilter(wclFilter);
		
	}

}
