package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.seasons.third;

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

public class JDZBMYCompositeConfigurator  extends
AbstractCompositeConfigurator{

	
	public JDZBMYCompositeConfigurator(IAccumulator acc,
			CompositeAccDataSource cads,
			Map<CompanyType, List<Company>> computeMap) {
		super(acc, cads, computeMap);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColumnCount() {
		return 26;
	}

	@Override
	protected void onConfiguring(IPipe pipe, IAccumulator acc, Integer zb,
			CompanyType type, List<Company> subComps, WclPipeFilter wclFilter, ZzlPipeFilter tbzzFilter) {

		// 全年计划
		pipe.add(new AccPipeFilter(acc, 0, zb, type)
					.includeCompanies(subComps))

				// 季度计划
			.add(new AccPipeFilter(acc, 1, zb, type)
				.includeCompanies(subComps))

			// 下季度计划
			.add(new AccPipeFilter(acc, 2, zb, type)
				.includeCompanies(subComps))

			// 当月计划
			.add(new AccPipeFilter(acc, 3, zb, type)
				.includeCompanies(subComps))

			// 当月实际
			.add(new AccPipeFilter(acc, 4, zb, type)
				.includeCompanies(subComps))

			// 计划完成率
			.add(wclFilter.add(5, 4, 3))

			// 去年同期
			.add(new AccPipeFilter(acc, 6, zb, type)
				.includeCompanies(subComps))

			// 同比增幅
			.add(tbzzFilter.add(7, 4, 6))

			// 季度累计
			.add(new AccPipeFilter(acc, 8, zb, type)
				.includeCompanies(subComps))

			// 季度计划完成率
			.add(wclFilter.add(9, 8, 1))

			// 季度去年同期
			.add(new AccPipeFilter(acc, 10, zb, type)
				.includeCompanies(subComps))

			// 同比增幅
			.add(tbzzFilter.add(11, 8, 10))

			// 年度累计
			.add(new AccPipeFilter(acc, 12, zb, type)
				.includeCompanies(subComps))

			// 累计计划完成率
			.add(wclFilter.add(13, 12, 0))

			// 去年同期
			.add(new AccPipeFilter(acc, 14, zb, type)
				.includeCompanies(subComps))

			// 同比增幅
			.add(tbzzFilter.add(15, 12, 14))

			// 下季度首月预计
			.add(new AccPipeFilter(acc, 16, zb, type)
				.includeCompanies(subComps)
				)

			// 下季度次月预计
			.add(new AccPipeFilter(acc, 17, zb, type)
				.includeCompanies(subComps))

			// 下季度末月预计
			.add(new AccPipeFilter(acc, 18, zb, type)
				.includeCompanies(subComps))

			// 下季度预计合计
			.add(new AccPipeFilter(acc, 19, zb, type)
				.includeCompanies(subComps))

			// 下季度预计完成率
			.add(wclFilter.add(20, 19, 2))

			// 下季度年度累计
			.add(new AccPipeFilter(acc, 21, zb, type)
				.includeCompanies(subComps))

			// 下季度年度累计完成率
			.add(wclFilter.add(22, 21, 25))

			// 下季度去年同期年度累计
			.add(new AccPipeFilter(acc, 23, zb, type)
				.includeCompanies(subComps))

			// 下季度年度累计同比增幅
			.add(tbzzFilter.add(24, 21, 23))


			.add(tbzzFilter).add(wclFilter);
	}

}
