package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.seasons.third;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.IndicatorBasedPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator.AbstractSbdPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.indicatorbased.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.indicatorbased.CopyPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.indicatorbased.RatioPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.indicatorbased.WclPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.indicatorbased.YdjhProportionAccPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.indicatorbased.ZzlPipeFilter;

public class JDZBMYConfigurator extends AbstractSbdPipeConfigurator {

	IAccumulator sjAcc;
	IAccumulator yjhAcc;
	IAccumulator njhAcc;

	public JDZBMYConfigurator(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc) {
		super(sbdzbDao);
		this.sjAcc = sjAcc;
		this.yjhAcc = yjhAcc;
		this.njhAcc = njhAcc;
	}

	@Override
	public void onConfiguring(IPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		List<Company> nonSbdCompanies = new ArrayList<Company>();
		List<Company> sbdCompanies = new ArrayList<Company>();
		seperate(allCompanies, sbdCompanies, nonSbdCompanies);
	
		DateHelper dh = new DateHelper(pipe.getDate());

		WclPipeFilter wclFilter = new WclPipeFilter();
		ZzlPipeFilter tbzzFilter = new ZzlPipeFilter();
		CopyPipeFilter copyFilter = new CopyPipeFilter();
		List<Integer> gsztzbs = pipe.getZbIds();
		
		// 全年计划
		pipe.add(new AccPipeFilter(njhAcc, 0)
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()));
		
		// 下季度全年计划
		pipe.add(new AccPipeFilter(njhAcc, 25, dh.getXjdLastMonth())
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()));


		// 当月计划
		if (sbdCompanies.isEmpty()) {
			pipe.add(new AccPipeFilter(yjhAcc, 3)
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs()));

		} else {
			pipe.add(new AccPipeFilter(yjhAcc, 3)
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs())
					.exclude(GSZB.YSZK)
					.exclude(GSZB.CH))
							
				.add(new YdjhProportionAccPipeFilter(sbdzbDao, sjAcc, 3, dh.getFirstMonth(), dh.getCur())
					.includeCompanies(sbdCompanies)
					.include(GSZB.YSZK)
					.include(GSZB.CH));

			if (!nonSbdCompanies.isEmpty()) {
				pipe.add(new AccPipeFilter(yjhAcc, 3)
						.includeCompanies(nonSbdCompanies)
						.include(GSZB.YSZK)
						.include(GSZB.CH));
			}
		}

		// 当月实际
		pipe.add(new AccPipeFilter(sjAcc, 4)
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()))

				// 计划完成率
				.add(wclFilter.add(5, 4, 3))

				// 去年同期
				.add(new AccPipeFilter(sjAcc, 6, dh.getQntq())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs()))

				// 同比增幅
				.add(tbzzFilter.add(7, 4, 6))

				// 季度计划
				.add(new AccPipeFilter(yjhAcc, 1, dh.getJdStart(), dh.getJdEnd(dh.getCur()))
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs()))
				.add(copyFilter
						.add(getTimePointNumberZbs(), 3, 1))

				// 下季度计划
				.add(new AccPipeFilter(yjhAcc, 2, dh.getXjdFirstMonth(), dh
						.getXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs()))
				.add(new AccPipeFilter(yjhAcc, 2, dh.getXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(getTimePointNumberZbs()))

				// 季度累计
				.add(new AccPipeFilter(sjAcc, 8, dh.getJdStart(), dh.getCur())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs())
						.excludeZbs(getZhHiddenZbs()))
				.add(copyFilter
						.add(getTimePointNumberZbs(), 4, 8))


				// 季度计划完成率
				.add(wclFilter.add(9, 8, 1))

				// 季度去年同期
				.add(new AccPipeFilter(sjAcc, 10, dh.getQntqJdStart(), dh
						.getQntq())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs())
						.excludeZbs(getZhHiddenZbs()))
				.add(new AccPipeFilter(sjAcc, 10, dh.getQntq())
						.includeCompanies(allCompanies)
						.includeZbs(getTimePointNumberZbs()))

				// 同比增幅
				.add(tbzzFilter
						.add(11, 8, 10))

				// 年度累计
				.add(new AccPipeFilter(sjAcc, 12, dh.getFirstMonth(), dh
						.getCur())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs())
						.excludeZbs(getZhHiddenZbs()))
				.add(copyFilter
						.add(getTimePointNumberZbs(), 4, 12))

				// 累计计划完成率
				.add(wclFilter.add(13, 12, 0))

				// 去年同期
				.add(new AccPipeFilter(sjAcc, 14, dh.getQnfirstMonth(), dh
						.getQntq())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs())
						.excludeZbs(getZhHiddenZbs()))
				.add(copyFilter
						.add(getTimePointNumberZbs(), 10, 14))

				// 同比增幅
				.add(tbzzFilter.add(15, 12, 14))

				// 下季度首月预计
				.add(new AccPipeFilter(sjAcc, 16, dh.getXjdFirstMonth())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs()))

				// 下季度次月预计
				.add(new AccPipeFilter(sjAcc, 17, dh.getXjdSecondMonth())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs()))

				// 下季度末月预计
				.add(new AccPipeFilter(sjAcc, 18, dh.getXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs()))

				// 下季度预计合计
				.add(new AccPipeFilter(sjAcc, 19, dh.getXjdFirstMonth(), dh
						.getXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs()))
				.add(new AccPipeFilter(sjAcc, 19, dh.getXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(getTimePointNumberZbs()))


				// 下季度预计完成率
				.add(wclFilter.add(20, 19, 2))

				// 下季度年度累计
				.add(new AccPipeFilter(sjAcc, 21, dh.getXjdDnFirstMonth(), dh
						.getXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs())
						.excludeZbs(getZhHiddenZbs()))
				.add(copyFilter
						.add(getTimePointNumberZbs(), 19, 21))

				// 下季度年度累计完成率
				.add(wclFilter
						.add(22, 21, 25))

				// 下季度去年同期年度累计
				.add(new AccPipeFilter(sjAcc, 23, dh.getQntqXjdDnFirstMonth(),
						dh.getQntqXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs())
						.excludeZbs(getZhHiddenZbs()))
				.add(new AccPipeFilter(sjAcc, 23, dh.getQntqXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(getTimePointNumberZbs()))

				// 下季度年度累计同比增幅
				.add(tbzzFilter
						.add(24, 21, 23))

				// 添加特殊指标过滤器
				.add(new RatioPipeFilter().exclude(5)// 计划完成率
						.exclude(7)// 同比增幅
						.exclude(9)// 季度计划完成率
						.exclude(11)// 同比增幅
						.exclude(13)// 累计计划完成率
						.exclude(15)// 同比增幅
						.exclude(20)// 下季度预计完成率
						.exclude(22)// 下季度年度累计完成率
						.exclude(24))// 下季度年度累计同比增幅
				.add(tbzzFilter)
				.add(wclFilter);
	}

	@Override
	public int getColumnCount() {
		return 26;
	}

}
