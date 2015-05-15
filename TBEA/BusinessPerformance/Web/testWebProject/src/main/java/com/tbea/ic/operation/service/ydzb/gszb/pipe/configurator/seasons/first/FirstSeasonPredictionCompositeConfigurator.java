package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.seasons.first;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.acc.composite.CompositeAccDataSource;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.AbstractCompositeConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.companybased.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.companybased.WclPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.companybased.ZzlPipeFilter;

public class FirstSeasonPredictionCompositeConfigurator extends
AbstractCompositeConfigurator {

	


	public FirstSeasonPredictionCompositeConfigurator(IAccumulator acc,
			CompositeAccDataSource cads,
			Map<CompanyType, List<Company>> computeMap) {
		super(acc, cads, computeMap);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColumnCount() {
		return 17;
	}

	@Override
	protected void onConfiguring(IPipe pipe, IAccumulator acc, Integer zb,
			CompanyType type, List<Company> subComps, WclPipeFilter wclFilter, ZzlPipeFilter tbzzFilter) {
		

		// 全年计划
			pipe.add(new AccPipeFilter(acc, 0, zb, type)
						.includeCompanies(subComps))
		// 当月计划
				.add(new AccPipeFilter(acc, 2, zb, type)
					.includeCompanies(subComps)
						)
				// 季度计划
				.add(new AccPipeFilter(acc, 1, zb, type)
					.includeCompanies(subComps)
						)

				// 当月实际
				.add(new AccPipeFilter(acc, 3, zb, type)
					.includeCompanies(subComps)
						)

				// 计划完成率
				.add(wclFilter.add(4, 3, 2))

				// 去年同期
				.add(new AccPipeFilter(acc, 5, zb, type)
					.includeCompanies(subComps)
						)

				// 同比增幅
				.add(tbzzFilter.add(6, 3, 5))

				// 次月预计
				.add(new AccPipeFilter(acc, 7, zb, type)
					.includeCompanies(subComps)
						)

				// 末月预计
				.add(new AccPipeFilter(acc, 8, zb, type)
					.includeCompanies(subComps)
						)

				// 季度预计合计
				.add(new AccPipeFilter(acc, 9, zb, type)
					.includeCompanies(subComps)
						)

				// 季度预计完成率
				.add(wclFilter.add(10, 9, 1))

				// 季度去年同期
				.add(new AccPipeFilter(acc, 11, zb, type)
					.includeCompanies(subComps)
						)

				// 同比增幅
				.add(tbzzFilter.add(12, 9, 11))

				// 年度累计
				.add(new AccPipeFilter(acc, 13, zb, type)
					.includeCompanies(subComps)
						)

				// 累计计划完成率
				.add(wclFilter.add(14, 13, 0))

				// 去年同期
				.add(new AccPipeFilter(acc, 15, zb, type)
					.includeCompanies(subComps)	)

				// 同比增幅
				.add(tbzzFilter.add(16, 13, 15))
				.add(tbzzFilter).add(wclFilter);
		
	}

}
