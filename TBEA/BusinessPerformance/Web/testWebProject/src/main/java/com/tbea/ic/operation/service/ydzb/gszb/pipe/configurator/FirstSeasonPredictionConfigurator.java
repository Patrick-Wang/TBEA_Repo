package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.AccSbdPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.CopyPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.SpecialPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.ZzlPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.WclPipeFilter;

public class FirstSeasonPredictionConfigurator implements IPipeConfigurator {

	StandardConfigurator standardConfigurator;

	public FirstSeasonPredictionConfigurator(StandardConfigurator standardConfigurator) {
		this.standardConfigurator = standardConfigurator;
	}

	@Override
	public void onConfiguring(GszbPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		List<Company> nonSbdCompanies = standardConfigurator.getNonSbdCompany(allCompanies);
		List<Company> sbdCompanies = standardConfigurator.getSbdCompany(allCompanies);

		DateHelper dh = new DateHelper(pipe.getDate());

		WclPipeFilter wclFilter = new WclPipeFilter();
		ZzlPipeFilter tbzzFilter = new ZzlPipeFilter();
		CopyPipeFilter copyFilter = new CopyPipeFilter();

		IAccumulator sjAcc = standardConfigurator.getSjAcc();
		IAccumulator yjhAcc = standardConfigurator.getYjhAcc();
		IAccumulator njhAcc = standardConfigurator.getNjhAcc();
		List<Integer> specialZbs = standardConfigurator.getSpecialZbs();
		// 全年计划
		pipe.add(new AccPipeFilter(njhAcc, 0).includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs).include(GSZB.RS)
				.include(GSZB.YSZK).include(GSZB.CH));
		
		

		// 当月计划
		if (sbdCompanies.isEmpty()) {
			pipe.add(new AccPipeFilter(yjhAcc, 2)
					.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
					.excludeZbs(specialZbs).include(GSZB.RS).include(GSZB.YSZK)
					.include(GSZB.CH));
		} else {
			pipe.add(
					new AccPipeFilter(yjhAcc, 2).includeCompanies(allCompanies)
							.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs)
							.include(GSZB.RS)).add(
					new AccSbdPipeFilter(yjhAcc, 2)
							.includeCompanies(sbdCompanies).include(GSZB.YSZK)
							.include(GSZB.CH));

			if (!nonSbdCompanies.isEmpty()) {
				pipe.add(new AccPipeFilter(yjhAcc, 2)
						.includeCompanies(nonSbdCompanies).include(GSZB.YSZK)
						.include(GSZB.CH));
			}
		}
		// 季度计划
		pipe.add(new AccPipeFilter(yjhAcc, 1, dh.getJdStart(), dh.getCur())
				.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
				.excludeZbs(specialZbs))
		.add(copyFilter.add(GSZB.CH, 2, 1).add(GSZB.YSZK, 2, 1)
				.add(GSZB.RS, 2, 1))

		// 当月实际
		.add(
		new AccPipeFilter(sjAcc, 3).includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs)
				.include(GSZB.YSZK).include(GSZB.CH).include(GSZB.RS))

		// 计划完成率
		.add(wclFilter.add(4, 3, 2))

		// 去年同期
		.add(new AccPipeFilter(sjAcc, 5, dh.getQntq())
				.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
				.excludeZbs(specialZbs).include(GSZB.YSZK)
				.include(GSZB.CH).include(GSZB.RS))

		// 同比增幅
		.add(tbzzFilter.add(6, 3, 5))
		
		//次月预计		
		.add(new AccPipeFilter(sjAcc, 7, dh.getSecondMonthinSeason())
		.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
		.excludeZbs(specialZbs).include(GSZB.YSZK)
		.include(GSZB.CH).include(GSZB.RS))
		
		//末月预计
		.add(new AccPipeFilter(sjAcc, 8, dh.getLastMonthinSeason())
		.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
		.excludeZbs(specialZbs).include(GSZB.YSZK)
		.include(GSZB.CH).include(GSZB.RS))
		
		// 季度预计合计
		.add(new AccPipeFilter(sjAcc, 9, dh.getCur(), dh.getLastMonthinSeason())
				.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
				.excludeZbs(specialZbs))
		.add(copyFilter.add(GSZB.CH, 8, 9).add(GSZB.YSZK, 8, 9)
				.add(GSZB.RS, 8, 9))

		// 季度预计完成率
		.add(wclFilter.add(10, 9, 1))

		// 季度去年同期
		.add(new AccPipeFilter(sjAcc, 11, dh.getQntqJdStart(), dh
				.getQntqJdEnd()).includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs))
		.add(new AccPipeFilter(sjAcc, 11, dh.getQntqJdStart())
				.includeCompanies(allCompanies).include(GSZB.YSZK)
				.include(GSZB.CH).include(GSZB.RS))

		// 同比增幅
		.add(tbzzFilter.add(12, 9, 11))

		// 年度累计
		.add(new AccPipeFilter(sjAcc, 13, dh.getFirstMonth(), dh
				.getCur()).includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs))
		.add(copyFilter.add(GSZB.CH, 3, 13).add(GSZB.YSZK, 3, 13)
				.add(GSZB.RS, 3, 13))

		// 累计计划完成率
		.add(wclFilter.add(14, 13, 0))

		// 去年同期
		.add(new AccPipeFilter(sjAcc, 15, dh.getQnfirstMonth(), dh
				.getQntq()).includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs))
		.add(copyFilter.add(GSZB.CH, 5, 15).add(GSZB.YSZK, 5, 15)
				.add(GSZB.RS, 5, 15))

		// 同比增幅
		.add(tbzzFilter.add(16, 13, 15))

		// 添加特殊指标过滤器
		.add(new SpecialPipeFilter().exclude(4)// 计划完成率
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
