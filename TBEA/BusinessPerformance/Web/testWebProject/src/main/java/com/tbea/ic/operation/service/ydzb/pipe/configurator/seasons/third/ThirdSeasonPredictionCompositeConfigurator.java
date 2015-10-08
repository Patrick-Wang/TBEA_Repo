package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.third;

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

public class ThirdSeasonPredictionCompositeConfigurator  extends
AbstractCompositeConfigurator{

	
	public ThirdSeasonPredictionCompositeConfigurator(
			Map<Company, List<Company>> computeMap) {
		super(computeMap);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColumnCount() {
		return 26;
	}

	@Override
	protected void onConfiguring(IPipe pipe, IAccumulator acc, List<Integer> zbs,
			int startRow, int step, List<Company> subComps, WclPipeFilter wclFilter, ZzlPipeFilter tbzzFilter) {

		// 全年计划
		pipe.addFilter(new AccPipeFilter(acc, 0, zbs, startRow, step)
					.includeCompanies(subComps))

				// 季度计划
			.addFilter(new AccPipeFilter(acc, 1, zbs, startRow, step)
				.includeCompanies(subComps))

			// 下季度计划
			.addFilter(new AccPipeFilter(acc, 2, zbs, startRow, step)
				.includeCompanies(subComps))

			// 当月计划
			.addFilter(new AccPipeFilter(acc, 3, zbs, startRow, step)
				.includeCompanies(subComps))

			// 当月实际
			.addFilter(new AccPipeFilter(acc, 4, zbs, startRow, step)
				.includeCompanies(subComps))

			// 计划完成率
			.addFilter(wclFilter.add(5, 4, 3))

			// 去年同期
			.addFilter(new AccPipeFilter(acc, 6, zbs, startRow, step)
				.includeCompanies(subComps))

			// 同比增幅
			.addFilter(tbzzFilter.add(7, 4, 6))

			// 季度累计
			.addFilter(new AccPipeFilter(acc, 8, zbs, startRow, step)
				.includeCompanies(subComps))

			// 季度计划完成率
			.addFilter(wclFilter.add(9, 8, 1))

			// 季度去年同期
			.addFilter(new AccPipeFilter(acc, 10, zbs, startRow, step)
				.includeCompanies(subComps))

			// 同比增幅
			.addFilter(tbzzFilter.add(11, 8, 10))

			// 年度累计
			.addFilter(new AccPipeFilter(acc, 12, zbs, startRow, step)
				.includeCompanies(subComps))

			// 累计计划完成率
			.addFilter(wclFilter.add(13, 12, 0))

			// 去年同期
			.addFilter(new AccPipeFilter(acc, 14, zbs, startRow, step)
				.includeCompanies(subComps))

			// 同比增幅
			.addFilter(tbzzFilter.add(15, 12, 14))

			// 下季度首月预计
			.addFilter(new AccPipeFilter(acc, 16, zbs, startRow, step)
				.includeCompanies(subComps)
				)

			// 下季度次月预计
			.addFilter(new AccPipeFilter(acc, 17, zbs, startRow, step)
				.includeCompanies(subComps))

			// 下季度末月预计
			.addFilter(new AccPipeFilter(acc, 18, zbs, startRow, step)
				.includeCompanies(subComps))

			// 下季度预计合计
			.addFilter(new AccPipeFilter(acc, 19, zbs, startRow, step)
				.includeCompanies(subComps))

			// 下季度预计完成率
			.addFilter(wclFilter.add(20, 19, 2))

			// 下季度年度累计
			.addFilter(new AccPipeFilter(acc, 21, zbs, startRow, step)
				.includeCompanies(subComps))

			// 下季度年度累计完成率
			.addFilter(wclFilter.add(22, 21, 25))

			// 下季度去年同期年度累计
			.addFilter(new AccPipeFilter(acc, 23, zbs, startRow, step)
				.includeCompanies(subComps))

			// 下季度年度累计同比增幅
			.addFilter(tbzzFilter.add(24, 21, 23))
			.addFilter(tbzzFilter).addFilter(wclFilter);
	}

}
