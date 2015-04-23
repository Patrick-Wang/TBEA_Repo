package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.RatioPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.WclPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.ZzlPipeFilter;

public class FirstSeasonPredictionCompositeConfigurator implements
		IPipeConfigurator {

	private IAccumulator acc;

	public FirstSeasonPredictionCompositeConfigurator(IAccumulator acc) {
		super();
		this.acc = acc;
	}

	@Override
	public void onConfiguring(GszbPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();

		WclPipeFilter wclFilter = new WclPipeFilter();
		ZzlPipeFilter tbzzFilter = new ZzlPipeFilter();
		List<Integer> allZbs = pipe.getZbIds();

		// 全年计划
			pipe.add(new AccPipeFilter(acc, 0)
						.includeCompanies(allCompanies)
						.includeZbs(allZbs))
		// 当月计划
				.add(new AccPipeFilter(acc, 2).includeCompanies(allCompanies)
						.includeZbs(allZbs))
				// 季度计划
				.add(new AccPipeFilter(acc, 1).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 当月实际
				.add(new AccPipeFilter(acc, 3).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 计划完成率
				.add(wclFilter.add(4, 3, 2))

				// 去年同期
				.add(new AccPipeFilter(acc, 5).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 同比增幅
				.add(tbzzFilter.add(6, 3, 5))

				// 次月预计
				.add(new AccPipeFilter(acc, 7).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 末月预计
				.add(new AccPipeFilter(acc, 8).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 季度预计合计
				.add(new AccPipeFilter(acc, 9).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 季度预计完成率
				.add(wclFilter.add(10, 9, 1))

				// 季度去年同期
				.add(new AccPipeFilter(acc, 11).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 同比增幅
				.add(tbzzFilter.add(12, 9, 11))

				// 年度累计
				.add(new AccPipeFilter(acc, 13).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 累计计划完成率
				.add(wclFilter.add(14, 13, 0))

				// 去年同期
				.add(new AccPipeFilter(acc, 15).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 同比增幅
				.add(tbzzFilter.add(16, 13, 15))

				// 添加特殊指标过滤器
				.add(new RatioPipeFilter().exclude(4)// 计划完成率
						.exclude(6)// 同比增幅
						.exclude(10)// 季度计划完成率
						.exclude(12)// 同比增幅
						.exclude(14)// 累计计划完成率
						.exclude(16))// 同比增幅
				.add(tbzzFilter).add(wclFilter);
	}

	@Override
	public int getColumnCount() {
		return 17;
	}

}
