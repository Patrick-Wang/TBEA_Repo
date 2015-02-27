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

public class JDZBMYConfigurator implements IPipeConfigurator {

	StandardConfigurator standardConfigurator;

	public JDZBMYConfigurator(StandardConfigurator standardConfigurator) {
		this.standardConfigurator = standardConfigurator;
	}

	@Override
	public void onConfiguring(GszbPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		List<Company> nonSbdCompanies = standardConfigurator.getNonSbdCompany(allCompanies);
		List<Company> sbdCompanies = standardConfigurator.getSbdCompany(allCompanies);
		IAccumulator sjAcc = standardConfigurator.getSjAcc();
		IAccumulator yjhAcc = standardConfigurator.getYjhAcc();
		IAccumulator njhAcc = standardConfigurator.getNjhAcc();
		List<Integer> specialZbs = standardConfigurator.getSpecialZbs();

		DateHelper dh = new DateHelper(pipe.getDate());

		WclPipeFilter wclFilter = new WclPipeFilter();
		ZzlPipeFilter tbzzFilter = new ZzlPipeFilter();
		CopyPipeFilter copyFilter = new CopyPipeFilter();
		List<Integer> gsztzbs = pipe.getZbIds();
		
		// 全年计划
		pipe.add(new AccPipeFilter(njhAcc, 0).includeCompanies(allCompanies)
				.includeZbs(gsztzbs).excludeZbs(specialZbs).include(GSZB.RS)
				.include(GSZB.YSZK).include(GSZB.CH));
		// 下季度全年计划
		pipe.add(new AccPipeFilter(njhAcc, 25, dh.getXjdLastMonth())
				.includeCompanies(allCompanies).includeZbs(gsztzbs)
				.excludeZbs(specialZbs).include(GSZB.RS).include(GSZB.YSZK)
				.include(GSZB.CH));

		// 当月计划
		if (sbdCompanies.isEmpty()) {
			pipe.add(new AccPipeFilter(yjhAcc, 3)
					.includeCompanies(allCompanies).includeZbs(gsztzbs)
					.excludeZbs(specialZbs).include(GSZB.RS).include(GSZB.YSZK)
					.include(GSZB.CH));
		} else {
			pipe.add(
					new AccPipeFilter(yjhAcc, 3).includeCompanies(allCompanies)
							.includeZbs(gsztzbs).excludeZbs(specialZbs)
							.include(GSZB.RS)).add(
					new AccSbdPipeFilter(yjhAcc, 3)
							.includeCompanies(sbdCompanies).include(GSZB.YSZK)
							.include(GSZB.CH));

			if (!nonSbdCompanies.isEmpty()) {
				pipe.add(new AccPipeFilter(yjhAcc, 3)
						.includeCompanies(nonSbdCompanies).include(GSZB.YSZK)
						.include(GSZB.CH));
			}
		}

		// 当月实际
		pipe.add(
				new AccPipeFilter(sjAcc, 4).includeCompanies(allCompanies)
						.includeZbs(gsztzbs).excludeZbs(specialZbs)
						.include(GSZB.YSZK).include(GSZB.CH).include(GSZB.RS))

				// 计划完成率
				.add(wclFilter.add(5, 4, 3))

				// 去年同期
				.add(new AccPipeFilter(sjAcc, 6, dh.getQntq())
						.includeCompanies(allCompanies).includeZbs(gsztzbs)
						.excludeZbs(specialZbs).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 同比增幅
				.add(tbzzFilter.add(7, 4, 6))

				// 季度计划
				.add(new AccPipeFilter(yjhAcc, 1, dh.getJdStart(), dh.getCur())
						.includeCompanies(allCompanies).includeZbs(gsztzbs)
						.excludeZbs(specialZbs))
				.add(copyFilter.add(GSZB.CH, 3, 1).add(GSZB.YSZK, 3, 1)
						.add(GSZB.RS, 3, 1))

				// 下季度计划
				.add(new AccPipeFilter(yjhAcc, 2, dh.getXjdFirstMonth(), dh
						.getXjdLastMonth()).includeCompanies(allCompanies)
						.includeZbs(gsztzbs).excludeZbs(specialZbs))
				.add(new AccPipeFilter(yjhAcc, 2, dh.getXjdLastMonth())
						.includeCompanies(allCompanies).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 季度累计
				.add(new AccPipeFilter(sjAcc, 8, dh.getJdStart(), dh.getCur())
						.includeCompanies(allCompanies).includeZbs(gsztzbs)
						.excludeZbs(specialZbs))
				.add(copyFilter.add(GSZB.CH, 4, 8).add(GSZB.YSZK, 4, 8)
						.add(GSZB.RS, 4, 8))

				// 季度计划完成率
				.add(wclFilter.add(9, 8, 1))

				// 季度去年同期
				.add(new AccPipeFilter(sjAcc, 10, dh.getQntqJdStart(), dh
						.getQntq()).includeCompanies(allCompanies)
						.includeZbs(gsztzbs).excludeZbs(specialZbs))
				.add(new AccPipeFilter(sjAcc, 10, dh.getQntq())
						.includeCompanies(allCompanies).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 同比增幅
				.add(tbzzFilter.add(11, 8, 10))

				// 年度累计
				.add(new AccPipeFilter(sjAcc, 12, dh.getFirstMonth(), dh
						.getCur()).includeCompanies(allCompanies)
						.includeZbs(gsztzbs).excludeZbs(specialZbs))
				.add(copyFilter.add(GSZB.CH, 4, 12).add(GSZB.YSZK, 4, 12)
						.add(GSZB.RS, 4, 12))

				// 累计计划完成率
				.add(wclFilter.add(13, 12, 0))

				// 去年同期
				.add(new AccPipeFilter(sjAcc, 14, dh.getQnfirstMonth(), dh
						.getQntq()).includeCompanies(allCompanies)
						.includeZbs(gsztzbs).excludeZbs(specialZbs))
				.add(copyFilter.add(GSZB.CH, 10, 14).add(GSZB.YSZK, 10, 14)
						.add(GSZB.RS, 10, 14))

				// 同比增幅
				.add(tbzzFilter.add(15, 12, 14))

				// 下季度首月预计
				.add(new AccPipeFilter(sjAcc, 16, dh.getXjdFirstMonth())
						.includeCompanies(allCompanies).includeZbs(gsztzbs)
						.excludeZbs(specialZbs).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 下季度次月预计
				.add(new AccPipeFilter(sjAcc, 17, dh.getXjdSecondMonth())
						.includeCompanies(allCompanies).includeZbs(gsztzbs)
						.excludeZbs(specialZbs).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 下季度末月预计
				.add(new AccPipeFilter(sjAcc, 18, dh.getXjdLastMonth())
						.includeCompanies(allCompanies).includeZbs(gsztzbs)
						.excludeZbs(specialZbs).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 下季度预计合计
				.add(new AccPipeFilter(sjAcc, 19, dh.getXjdFirstMonth(), dh
						.getXjdLastMonth()).includeCompanies(allCompanies)
						.includeZbs(gsztzbs).excludeZbs(specialZbs))
				.add(new AccPipeFilter(sjAcc, 19, dh.getXjdLastMonth())
						.includeCompanies(allCompanies).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

				// 下季度预计完成率
				.add(wclFilter.add(20, 19, 2))

				// 下季度年度累计
				.add(new AccPipeFilter(sjAcc, 21, dh.getXjdDnFirstMonth(), dh
						.getXjdLastMonth()).includeCompanies(allCompanies)
						.includeZbs(gsztzbs).excludeZbs(specialZbs))
				.add(copyFilter.add(GSZB.CH, 19, 21).add(GSZB.YSZK, 19, 21)
						.add(GSZB.RS, 19, 21))

				// 下季度年度累计完成率
				.add(wclFilter.add(22, 21, 25))

				// 下季度去年同期年度累计
				.add(new AccPipeFilter(sjAcc, 23, dh.getQntqXjdDnFirstMonth(),
						dh.getQntqXjdLastMonth())
						.includeCompanies(allCompanies).includeZbs(gsztzbs)
						.excludeZbs(specialZbs))
				.add(new AccPipeFilter(sjAcc, 23, dh.getQntqXjdLastMonth())
						.includeCompanies(allCompanies).include(GSZB.YSZK)
						.include(GSZB.CH).include(GSZB.RS))

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
