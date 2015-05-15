package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.seasons.second;

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

public class SecondSeasonPredictionCompositeConfigurator extends
AbstractCompositeConfigurator {


	public SecondSeasonPredictionCompositeConfigurator(IAccumulator acc,
			CompositeAccDataSource cads,
			Map<CompanyType, List<Company>> computeMap) {
		super(acc, cads, computeMap);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColumnCount() {
		return 20;
	}

	@Override
	protected void onConfiguring(IPipe pipe, IAccumulator acc, Integer zb,
			CompanyType type, List<Company> subComps, WclPipeFilter wclFilter, ZzlPipeFilter tbzzFilter) {

		// 全年计划
		pipe.add(new AccPipeFilter(acc, 0, zb, type)
					.includeCompanies(subComps))
				// 当月计划
				.add(new AccPipeFilter(acc, 2, zb, type)
						.includeCompanies(subComps))
				// 季度计划
				.add(new AccPipeFilter(acc, 1, zb, type)
						.includeCompanies(subComps))

				// 当月实际
				.add(new AccPipeFilter(acc, 3, zb, type)
						.includeCompanies(subComps))

				// 计划完成率
				.add(wclFilter.add(4, 3, 2))

				// 去年同期
				.add(new AccPipeFilter(acc, 5, zb, type)
						.includeCompanies(subComps))

				// 同比增幅
				.add(tbzzFilter.add(6, 3, 5))

				// 季度累计
				.add(new AccPipeFilter(acc, 7, zb, type)
						.includeCompanies(subComps))

				// 季度计划完成率
				.add(wclFilter.add(8, 7, 1))

				// 季度去年同期
				.add(new AccPipeFilter(acc, 9, zb, type)
						.includeCompanies(subComps))

				// 同比增幅
				.add(tbzzFilter.add(10, 7, 9))

				// 末月预计
				.add(new AccPipeFilter(acc, 11, zb, type)
						.includeCompanies(subComps))

				// 季度预计合计
				.add(new AccPipeFilter(acc, 12, zb, type)
						.includeCompanies(subComps))

				// 季度预计完成率
				.add(wclFilter.add(13, 12, 1))

				// 季度去年同期
				.add(new AccPipeFilter(acc, 14, zb, type)
						.includeCompanies(subComps))

				// 同比增幅
				.add(tbzzFilter.add(15, 12, 14))

				// 年度累计
				.add(new AccPipeFilter(acc, 16, zb, type)
						.includeCompanies(subComps))

				// 累计计划完成率
				.add(wclFilter.add(17, 16, 0))

				// 去年同期
				.add(new AccPipeFilter(acc, 18, zb, type)
						.includeCompanies(subComps))

				// 同比增幅
				.add(tbzzFilter.add(19, 16, 18))

				.add(tbzzFilter)
				.add(wclFilter);
		
	}

}
