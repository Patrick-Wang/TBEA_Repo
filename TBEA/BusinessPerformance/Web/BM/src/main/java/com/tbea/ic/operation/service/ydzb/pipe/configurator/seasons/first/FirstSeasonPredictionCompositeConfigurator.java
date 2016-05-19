package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.first;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.util.pipe.acc.composite.CompositeAccDataSource;
import com.tbea.ic.operation.service.util.pipe.configurator.composite.AbstractCompositeConfigurator;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.composite.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.WclPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.ZzlPipeFilter;

public class FirstSeasonPredictionCompositeConfigurator extends
AbstractCompositeConfigurator {

	


	public FirstSeasonPredictionCompositeConfigurator(
			Map<Company, List<Company>> computeMap) {
		super(computeMap);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColumnCount() {
		return 17;
	}

	@Override
	protected void onConfiguring(IPipe pipe, IAccumulator acc, List<Integer> zbs,
			int startRow, int step, List<Company> subComps, WclPipeFilter wclFilter, ZzlPipeFilter tbzzFilter) {

		    // 全年计划
			pipe.addFilter(new AccPipeFilter(acc, 0, startRow, step)
						.includeCompanies(subComps))
				// 当月计划
				.addFilter(new AccPipeFilter(acc, 2, startRow, step)
					.includeCompanies(subComps)
						)
				// 季度计划
				.addFilter(new AccPipeFilter(acc, 1, startRow, step)
					.includeCompanies(subComps)
						)

				// 当月实际
				.addFilter(new AccPipeFilter(acc, 3, startRow, step)
					.includeCompanies(subComps)
						)

				// 计划完成率
				.addFilter(wclFilter.add(4, 3, 2))

				// 去年同期
				.addFilter(new AccPipeFilter(acc, 5, startRow, step)
					.includeCompanies(subComps)
						)

				// 同比增幅
				.addFilter(tbzzFilter.add(6, 3, 5))

				// 次月预计
				.addFilter(new AccPipeFilter(acc, 7, startRow, step)
					.includeCompanies(subComps)
						)

				// 末月预计
				.addFilter(new AccPipeFilter(acc, 8, startRow, step)
					.includeCompanies(subComps)
						)

				// 季度预计合计
				.addFilter(new AccPipeFilter(acc, 9, startRow, step)
					.includeCompanies(subComps)
						)

				// 季度预计完成率
				.addFilter(wclFilter.add(10, 9, 1))

				// 季度去年同期
				.addFilter(new AccPipeFilter(acc, 11, startRow, step)
					.includeCompanies(subComps)
						)

				// 同比增幅
				.addFilter(tbzzFilter.add(12, 9, 11))

				// 年度累计
				.addFilter(new AccPipeFilter(acc, 13, startRow, step)
					.includeCompanies(subComps)
						)

				// 累计计划完成率
				.addFilter(wclFilter.add(14, 13, 0))

				// 去年同期
				.addFilter(new AccPipeFilter(acc, 15, startRow, step)
					.includeCompanies(subComps)	)

				// 同比增幅
				.addFilter(tbzzFilter.add(16, 13, 15))
				.addFilter(tbzzFilter).addFilter(wclFilter);
		
	}

}
