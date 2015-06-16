package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.first;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.pipe.acc.composite.CompositeAccDataSource;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.AbstractCompositeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.filter.advanced.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.advanced.WclPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.advanced.ZzlPipeFilter;

public class FirstSeasonPredictionCompositeConfigurator extends
AbstractCompositeConfigurator {

	


	public FirstSeasonPredictionCompositeConfigurator(IAccumulator acc,
			CompositeAccDataSource cads,
			Map<Company, List<Company>> computeMap) {
		super(acc, cads, computeMap);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColumnCount() {
		return 17;
	}

	@Override
	protected void onConfiguring(IPipe pipe, IAccumulator acc, List<Integer> zbs,
			Company type, List<Company> subComps, WclPipeFilter wclFilter, ZzlPipeFilter tbzzFilter) {

		    // 全年计划
			pipe.addFilter(new AccPipeFilter(acc, 0, zbs, type)
						.includeCompanies(subComps))
				// 当月计划
				.addFilter(new AccPipeFilter(acc, 2, zbs, type)
					.includeCompanies(subComps)
						)
				// 季度计划
				.addFilter(new AccPipeFilter(acc, 1, zbs, type)
					.includeCompanies(subComps)
						)

				// 当月实际
				.addFilter(new AccPipeFilter(acc, 3, zbs, type)
					.includeCompanies(subComps)
						)

				// 计划完成率
				.addFilter(wclFilter.add(4, 3, 2))

				// 去年同期
				.addFilter(new AccPipeFilter(acc, 5, zbs, type)
					.includeCompanies(subComps)
						)

				// 同比增幅
				.addFilter(tbzzFilter.add(6, 3, 5))

				// 次月预计
				.addFilter(new AccPipeFilter(acc, 7, zbs, type)
					.includeCompanies(subComps)
						)

				// 末月预计
				.addFilter(new AccPipeFilter(acc, 8, zbs, type)
					.includeCompanies(subComps)
						)

				// 季度预计合计
				.addFilter(new AccPipeFilter(acc, 9, zbs, type)
					.includeCompanies(subComps)
						)

				// 季度预计完成率
				.addFilter(wclFilter.add(10, 9, 1))

				// 季度去年同期
				.addFilter(new AccPipeFilter(acc, 11, zbs, type)
					.includeCompanies(subComps)
						)

				// 同比增幅
				.addFilter(tbzzFilter.add(12, 9, 11))

				// 年度累计
				.addFilter(new AccPipeFilter(acc, 13, zbs, type)
					.includeCompanies(subComps)
						)

				// 累计计划完成率
				.addFilter(wclFilter.add(14, 13, 0))

				// 去年同期
				.addFilter(new AccPipeFilter(acc, 15, zbs, type)
					.includeCompanies(subComps)	)

				// 同比增幅
				.addFilter(tbzzFilter.add(16, 13, 15))
				.addFilter(tbzzFilter).addFilter(wclFilter);
		
	}

}
