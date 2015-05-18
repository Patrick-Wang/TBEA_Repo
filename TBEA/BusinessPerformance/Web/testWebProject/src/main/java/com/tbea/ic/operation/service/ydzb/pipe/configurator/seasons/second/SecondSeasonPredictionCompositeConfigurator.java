package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.second;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.ydzb.pipe.acc.composite.CompositeAccDataSource;
import com.tbea.ic.operation.service.ydzb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.pipe.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.AbstractCompositeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.filter.complex.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.complex.WclPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.complex.ZzlPipeFilter;

public class SecondSeasonPredictionCompositeConfigurator extends
AbstractCompositeConfigurator {


	public SecondSeasonPredictionCompositeConfigurator(IAccumulator acc,
			CompositeAccDataSource cads,
			Map<Company, List<Company>> computeMap) {
		super(acc, cads, computeMap);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColumnCount() {
		return 20;
	}

	@Override
	protected void onConfiguring(IPipe pipe, IAccumulator acc, List<Integer> zbs,
			Company type, List<Company> subComps, WclPipeFilter wclFilter, ZzlPipeFilter tbzzFilter) {

		// 全年计划
		pipe.addFilter(new AccPipeFilter(acc, 0, zbs, type)
					.includeCompanies(subComps))
				// 当月计划
				.addFilter(new AccPipeFilter(acc, 2, zbs, type)
						.includeCompanies(subComps))
				// 季度计划
				.addFilter(new AccPipeFilter(acc, 1, zbs, type)
						.includeCompanies(subComps))

				// 当月实际
				.addFilter(new AccPipeFilter(acc, 3, zbs, type)
						.includeCompanies(subComps))

				// 计划完成率
				.addFilter(wclFilter.add(4, 3, 2))

				// 去年同期
				.addFilter(new AccPipeFilter(acc, 5, zbs, type)
						.includeCompanies(subComps))

				// 同比增幅
				.addFilter(tbzzFilter.add(6, 3, 5))

				// 季度累计
				.addFilter(new AccPipeFilter(acc, 7, zbs, type)
						.includeCompanies(subComps))

				// 季度计划完成率
				.addFilter(wclFilter.add(8, 7, 1))

				// 季度去年同期
				.addFilter(new AccPipeFilter(acc, 9, zbs, type)
						.includeCompanies(subComps))

				// 同比增幅
				.addFilter(tbzzFilter.add(10, 7, 9))

				// 末月预计
				.addFilter(new AccPipeFilter(acc, 11, zbs, type)
						.includeCompanies(subComps))

				// 季度预计合计
				.addFilter(new AccPipeFilter(acc, 12, zbs, type)
						.includeCompanies(subComps))

				// 季度预计完成率
				.addFilter(wclFilter.add(13, 12, 1))

				// 季度去年同期
				.addFilter(new AccPipeFilter(acc, 14, zbs, type)
						.includeCompanies(subComps))

				// 同比增幅
				.addFilter(tbzzFilter.add(15, 12, 14))

				// 年度累计
				.addFilter(new AccPipeFilter(acc, 16, zbs, type)
						.includeCompanies(subComps))

				// 累计计划完成率
				.addFilter(wclFilter.add(17, 16, 0))

				// 去年同期
				.addFilter(new AccPipeFilter(acc, 18, zbs, type)
						.includeCompanies(subComps))

				// 同比增幅
				.addFilter(tbzzFilter.add(19, 16, 18))

				.addFilter(tbzzFilter)
				.addFilter(wclFilter);
		
	}

}
