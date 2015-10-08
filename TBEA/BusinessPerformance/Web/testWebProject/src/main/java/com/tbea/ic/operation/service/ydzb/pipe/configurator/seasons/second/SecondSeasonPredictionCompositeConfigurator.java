package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.second;

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

public class SecondSeasonPredictionCompositeConfigurator extends
AbstractCompositeConfigurator {


	public SecondSeasonPredictionCompositeConfigurator(
			Map<Company, List<Company>> computeMap) {
		super(computeMap);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColumnCount() {
		return 20;
	}

	@Override
	protected void onConfiguring(IPipe pipe, IAccumulator acc, List<Integer> zbs,
			int startRow, int step, List<Company> subComps, WclPipeFilter wclFilter, ZzlPipeFilter tbzzFilter) {

		// 全年计划
		pipe.addFilter(new AccPipeFilter(acc, 0, zbs, startRow, step)
					.includeCompanies(subComps))
				// 当月计划
				.addFilter(new AccPipeFilter(acc, 2, zbs, startRow, step)
						.includeCompanies(subComps))
				// 季度计划
				.addFilter(new AccPipeFilter(acc, 1, zbs, startRow, step)
						.includeCompanies(subComps))

				// 当月实际
				.addFilter(new AccPipeFilter(acc, 3, zbs, startRow, step)
						.includeCompanies(subComps))

				// 计划完成率
				.addFilter(wclFilter.add(4, 3, 2))

				// 去年同期
				.addFilter(new AccPipeFilter(acc, 5, zbs, startRow, step)
						.includeCompanies(subComps))

				// 同比增幅
				.addFilter(tbzzFilter.add(6, 3, 5))

				// 季度累计
				.addFilter(new AccPipeFilter(acc, 7, zbs, startRow, step)
						.includeCompanies(subComps))

				// 季度计划完成率
				.addFilter(wclFilter.add(8, 7, 1))

				// 季度去年同期
				.addFilter(new AccPipeFilter(acc, 9, zbs, startRow, step)
						.includeCompanies(subComps))

				// 同比增幅
				.addFilter(tbzzFilter.add(10, 7, 9))

				// 末月预计
				.addFilter(new AccPipeFilter(acc, 11, zbs, startRow, step)
						.includeCompanies(subComps))

				// 季度预计合计
				.addFilter(new AccPipeFilter(acc, 12, zbs, startRow, step)
						.includeCompanies(subComps))

				// 季度预计完成率
				.addFilter(wclFilter.add(13, 12, 1))

				// 季度去年同期
				.addFilter(new AccPipeFilter(acc, 14, zbs, startRow, step)
						.includeCompanies(subComps))

				// 同比增幅
				.addFilter(tbzzFilter.add(15, 12, 14))

				// 年度累计
				.addFilter(new AccPipeFilter(acc, 16, zbs, startRow, step)
						.includeCompanies(subComps))

				// 累计计划完成率
				.addFilter(wclFilter.add(17, 16, 0))

				// 去年同期
				.addFilter(new AccPipeFilter(acc, 18, zbs, startRow, step)
						.includeCompanies(subComps))

				// 同比增幅
				.addFilter(tbzzFilter.add(19, 16, 18))

				.addFilter(tbzzFilter)
				.addFilter(wclFilter);
		
	}

}
