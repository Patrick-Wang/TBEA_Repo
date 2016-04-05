package com.tbea.ic.operation.service.yszkrb.pipe.configurator;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.configurator.composite.AbstractCompositeConfigurator;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.composite.SumPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.WclPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.ZzlPipeFilter;

public class YSZKRBCompositeConfigurator extends
AbstractCompositeConfigurator {

	public YSZKRBCompositeConfigurator(
			Map<Company, List<Company>> computeMap) {
		super(computeMap);
	}

	@Override
	public int getColumnCount() {
		return 13;
	}

	@Override
	protected void onConfiguring(IPipe pipe, IAccumulator acc, List<Integer> zbs,
			int startRow, int step, List<Company> subComps, WclPipeFilter wclFilter, ZzlPipeFilter tbzzFilter) {

		SumPipeFilter sumFilter = new SumPipeFilter();
		sumFilter.includeRow(startRow, step);
		// 集团下达月度资金回笼指标
		pipe.addFilter(new AccPipeFilter(acc, 0, startRow, step)
					.includeCompanies(subComps))

		// 各单位自行制定的回款计划
			.addFilter(new AccPipeFilter(acc, 1, startRow, step)
					.includeCompanies(subComps))

		//今日回款
			.addFilter(new AccPipeFilter(acc, 2, startRow, step)
					.includeCompanies(subComps))
		//月累计（截止今日）月度从1号累计到月末31号汇总总额（即：今日回款中1号-31号回款累计）
			.addFilter(new AccPipeFilter(acc, 3, startRow, step)
					.includeCompanies(subComps))
					
		// 资金回笼指标完成    月度累计回款/集团下达的计划
			.addFilter(wclFilter.add(4, 3, 0))
			
		// 回款计划完成率 等于：月度累计回款/各单位自行制定的回款计划.
			.addFilter(wclFilter.add(5, 3, 1))
			
			
		//已回款中可降应收的回款金额
			.addFilter(new AccPipeFilter(acc, 6, startRow, step)
					.includeCompanies(subComps))

		//确保办出
			.addFilter(new AccPipeFilter(acc, 7, startRow, step)
					.includeCompanies(subComps))

		//争取办出
			.addFilter(new AccPipeFilter(acc, 8, startRow, step)
					.includeCompanies(subComps))
		
		//两者合计   等于：确保办出+争取办出
			.addFilter(sumFilter.add(9, 8, 7))
		
		//全月确保 等于：确保办出+月累计回款
			.addFilter(sumFilter.add(10, 7, 3))

		//预计全月计划完成率  等于：全月确保/各单位自行定的计划
			.addFilter(wclFilter.add(11, 10, 1))
			
		//截止月底应收账款账面余额
			.addFilter(new AccPipeFilter(acc, 12, startRow, step)
					.includeCompanies(subComps))			
		
			.addFilter(sumFilter)
			.addFilter(wclFilter);		
	}

}
