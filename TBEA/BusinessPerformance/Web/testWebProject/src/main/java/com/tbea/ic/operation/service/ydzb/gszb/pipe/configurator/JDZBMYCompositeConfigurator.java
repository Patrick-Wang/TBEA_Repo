package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.SpecialPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.WclPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.ZzlPipeFilter;

public class JDZBMYCompositeConfigurator implements IPipeConfigurator {

	private IAccumulator acc;

	public JDZBMYCompositeConfigurator(IAccumulator acc) {
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
		pipe.add(
				new AccPipeFilter(acc, 0).includeCompanies(allCompanies)
						.includeZbs(allZbs))

		// 季度计划
				.add(new AccPipeFilter(acc, 1).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 下季度计划
				.add(new AccPipeFilter(acc, 2).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 当月计划
				.add(new AccPipeFilter(acc, 3).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 当月实际
				.add(new AccPipeFilter(acc, 4).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 计划完成率
				.add(wclFilter.add(5, 4, 3))

				// 去年同期
				.add(new AccPipeFilter(acc, 6).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 同比增幅
				.add(tbzzFilter.add(7, 4, 6))

				// 季度累计
				.add(new AccPipeFilter(acc, 8).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 季度计划完成率
				.add(wclFilter.add(9, 8, 1))

				// 季度去年同期
				.add(new AccPipeFilter(acc, 10).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 同比增幅
				.add(tbzzFilter.add(11, 8, 10))

				// 年度累计
				.add(new AccPipeFilter(acc, 12).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 累计计划完成率
				.add(wclFilter.add(13, 12, 0))

				// 去年同期
				.add(new AccPipeFilter(acc, 14).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 同比增幅
				.add(tbzzFilter.add(15, 12, 14))

				// 下季度首月预计
				.add(new AccPipeFilter(acc, 16).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 下季度次月预计
				.add(new AccPipeFilter(acc, 17).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 下季度末月预计
				.add(new AccPipeFilter(acc, 18).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 下季度预计合计
				.add(new AccPipeFilter(acc, 19).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 下季度预计完成率
				.add(wclFilter.add(20, 19, 2))

				// 下季度年度累计
				.add(new AccPipeFilter(acc, 21).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 下季度年度累计完成率
				.add(wclFilter.add(22, 21, 25))

				// 下季度去年同期年度累计
				.add(new AccPipeFilter(acc, 23).includeCompanies(allCompanies)
						.includeZbs(allZbs))

				// 下季度年度累计同比增幅
				.add(tbzzFilter.add(24, 21, 23))

				// 添加特殊指标过滤器
				.add(new SpecialPipeFilter().exclude(5)// 计划完成率
						.exclude(7)// 同比增幅
						.exclude(9)// 季度计划完成率
						.exclude(11)// 同比增幅
						.exclude(13)// 累计计划完成率
						.exclude(15)// 同比增幅
						.exclude(20)// 下季度预计完成率
						.exclude(22)// 下季度年度累计完成率
						.exclude(24))// 下季度年度累计同比增幅
				.add(tbzzFilter).add(wclFilter);

	}

	@Override
	public int getColumnCount() {
		return 26;
	}

}
