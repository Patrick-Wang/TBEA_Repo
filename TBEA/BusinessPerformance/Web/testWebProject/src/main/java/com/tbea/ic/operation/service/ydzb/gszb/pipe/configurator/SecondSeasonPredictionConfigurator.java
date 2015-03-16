package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.AccSbdPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.CopyPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.SpecialPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.ZzlPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.WclPipeFilter;

public class SecondSeasonPredictionConfigurator extends AbstractSbdPipeConfigurator {

	IAccumulator sjAcc;
	IAccumulator yjhAcc;
	IAccumulator njhAcc;
	SbdNdjhZbDao sbdzbDao;

	public SecondSeasonPredictionConfigurator(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc, CompanyManager companyManager) {
		super(companyManager);
		this.sbdzbDao = sbdzbDao;
		this.sjAcc = sjAcc;
		this.yjhAcc = yjhAcc;
		this.njhAcc = njhAcc;
	}

	@Override
	public void onConfiguring(GszbPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		List<Company> nonSbdCompanies = getNonSbdCompany(allCompanies);
		List<Company> sbdCompanies = getSbdCompany(allCompanies);
		List<Integer> specialZbs = getSpecialZbs();
		
		DateHelper dh = new DateHelper(pipe.getDate());

		WclPipeFilter wclFilter = new WclPipeFilter();
		ZzlPipeFilter tbzzFilter = new ZzlPipeFilter();
		CopyPipeFilter copyFilter = new CopyPipeFilter();

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
					new AccSbdPipeFilter(sbdzbDao, sjAcc, 2, dh.getFirstMonth(), dh.getCur())
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
		
		
		// 季度累计
		.add(new AccPipeFilter(sjAcc, 7, dh.getJdStart(), dh.getCur())
				.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
				.excludeZbs(specialZbs))
		.add(copyFilter.add(GSZB.CH, 3, 7).add(GSZB.YSZK, 3, 7)
				.add(GSZB.RS, 3, 7))

		// 季度计划完成率
		.add(wclFilter.add(8, 7, 1))

		// 季度去年同期
		.add(new AccPipeFilter(sjAcc, 9, dh.getQntqJdStart(), dh
				.getQntq()).includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs))
		.add(new AccPipeFilter(sjAcc, 9, dh.getQntq())
				.includeCompanies(allCompanies).include(GSZB.YSZK)
				.include(GSZB.CH).include(GSZB.RS))

		// 同比增幅
		.add(tbzzFilter.add(10, 7, 9))
		
		
		//末月预计
		.add(new AccPipeFilter(sjAcc, 11, dh.getLastMonthinSeason())
		.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
		.excludeZbs(specialZbs).include(GSZB.YSZK)
		.include(GSZB.CH).include(GSZB.RS))
		
		// 季度预计合计
		.add(new AccPipeFilter(sjAcc, 12, dh.getCur(), dh.getLastMonthinSeason())
				.includeCompanies(allCompanies).includeZbs(pipe.getZbIds())
				.excludeZbs(specialZbs))
		.add(copyFilter.add(GSZB.CH, 11, 12).add(GSZB.YSZK, 11, 12)
				.add(GSZB.RS, 11, 12))

		// 季度预计完成率
		.add(wclFilter.add(13, 12, 1))

		// 季度去年同期
		.add(new AccPipeFilter(sjAcc, 14, dh.getQntqJdStart(), dh
				.getQntqJdEnd()).includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs))
		.add(new AccPipeFilter(sjAcc, 14, dh.getQntqJdStart())
				.includeCompanies(allCompanies).include(GSZB.YSZK)
				.include(GSZB.CH).include(GSZB.RS))

		// 同比增幅
		.add(tbzzFilter.add(15, 12, 14))

		// 年度累计
		.add(new AccPipeFilter(sjAcc, 16, dh.getFirstMonth(), dh
				.getCur()).includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs))
		.add(copyFilter.add(GSZB.CH, 3, 16).add(GSZB.YSZK, 3, 16)
				.add(GSZB.RS, 3, 16))

		// 累计计划完成率
		.add(wclFilter.add(17, 16, 0))

		// 去年同期
		.add(new AccPipeFilter(sjAcc, 18, dh.getQnfirstMonth(), dh
				.getQntq()).includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds()).excludeZbs(specialZbs))
		.add(copyFilter.add(GSZB.CH, 5, 18).add(GSZB.YSZK, 5, 18)
				.add(GSZB.RS, 5, 18))

		// 同比增幅
		.add(tbzzFilter.add(19, 16, 18))

		// 添加特殊指标过滤器
		.add(new SpecialPipeFilter().exclude(4)// 当月计划完成率
				.exclude(6)// 当月同比增幅
				.exclude(8)// 季度累计完成率
				.exclude(10)// 季度累计同比增幅
				.exclude(13)// 季度预计累计计划完成率
				.exclude(15)// 季度预计同比增幅
				.exclude(17)// 年度累计计划完成率
				.exclude(19))// 年度同比增幅
		.add(tbzzFilter).add(wclFilter);
	}

	@Override
	public int getColumnCount() {
		return 20;
	}

}
