package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.third;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.core.BasicPipe;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.CopyPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.RatioPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.WclPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.ZzlPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.AbstractSbdPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.YdjhProportionAccPipeFilter;

public class ThirdSeasonPredictionConfigurator extends AbstractSbdPipeConfigurator {

	IAccumulator sjAcc;
	IAccumulator yjhAcc;
	IAccumulator njhAcc;

	public ThirdSeasonPredictionConfigurator(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc) {
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
		List<Integer> gsztzbs = pipe.getIndicators();
		
		// 全年计划
		pipe.addFilter(new AccPipeFilter(njhAcc, 0)
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()));
		
		// 下季度全年计划
		pipe.addFilter(new AccPipeFilter(njhAcc, 25, dh.getXjdLastMonth())
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()));


		Calendar cal = Calendar.getInstance();
		cal.setTime(dh.getCur());
		// 当月计划
		if (sbdCompanies.isEmpty() || cal.get(Calendar.YEAR) >= 2016) {
			pipe.addFilter(new AccPipeFilter(yjhAcc, 3)
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs()));

		} else {
			pipe.addFilter(new AccPipeFilter(yjhAcc, 3)
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs())
					.exclude(GSZB.YSZK32)
					.exclude(GSZB.CH35))
							
				.addFilter(new YdjhProportionAccPipeFilter(sbdzbDao, sjAcc, 3, dh.getFirstMonth(), dh.getCur())
					.includeCompanies(sbdCompanies)
					.include(GSZB.YSZK32)
					.include(GSZB.CH35));

			if (!nonSbdCompanies.isEmpty()) {
				pipe.addFilter(new AccPipeFilter(yjhAcc, 3)
						.includeCompanies(nonSbdCompanies)
						.include(GSZB.YSZK32)
						.include(GSZB.CH35));
			}
		}

		// 当月实际
		pipe.addFilter(new AccPipeFilter(sjAcc, 4)
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()))

				// 计划完成率
				.addFilter(wclFilter.add(5, 4, 3))

				// 去年同期
				.addFilter(new AccPipeFilter(sjAcc, 6, dh.getQntq())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs()))

				// 同比增幅
				.addFilter(tbzzFilter.add(7, 4, 6))

				// 季度计划
				.addFilter(new AccPipeFilter(yjhAcc, 1, dh.getJdStart(), dh.getJdEnd(dh.getCur()))
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs()))
				.addFilter(copyFilter
						.add(getTimePointNumberZbs(), 3, 1))

				// 下季度计划
				.addFilter(new AccPipeFilter(yjhAcc, 2, dh.getXjdFirstMonth(), dh
						.getXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs()))
				.addFilter(new AccPipeFilter(yjhAcc, 2, dh.getXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(getTimePointNumberZbs()))

				// 季度累计
				.addFilter(new AccPipeFilter(sjAcc, 8, dh.getJdStart(), dh.getCur())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs())
						.excludeZbs(getZhHiddenZbs()))
				.addFilter(copyFilter
						.add(getTimePointNumberZbs(), 4, 8))


				// 季度计划完成率
				.addFilter(wclFilter.add(9, 8, 1))

				// 季度去年同期
				.addFilter(new AccPipeFilter(sjAcc, 10, dh.getQntqJdStart(), dh
						.getQntq())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs())
						.excludeZbs(getZhHiddenZbs()))
				.addFilter(new AccPipeFilter(sjAcc, 10, dh.getQntq())
						.includeCompanies(allCompanies)
						.includeZbs(getTimePointNumberZbs()))

				// 同比增幅
				.addFilter(tbzzFilter
						.add(11, 8, 10))

				// 年度累计
				.addFilter(new AccPipeFilter(sjAcc, 12, dh.getFirstMonth(), dh
						.getCur())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs())
						.excludeZbs(getZhHiddenZbs()))
				.addFilter(copyFilter
						.add(getTimePointNumberZbs(), 4, 12))

				// 累计计划完成率
				.addFilter(wclFilter.add(13, 12, 0))

				// 去年同期
				.addFilter(new AccPipeFilter(sjAcc, 14, dh.getQnfirstMonth(), dh
						.getQntq())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs())
						.excludeZbs(getZhHiddenZbs()))
				.addFilter(copyFilter
						.add(getTimePointNumberZbs(), 10, 14))

				// 同比增幅
				.addFilter(tbzzFilter.add(15, 12, 14))

				// 下季度首月预计
				.addFilter(new AccPipeFilter(sjAcc, 16, dh.getXjdFirstMonth())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs()))

				// 下季度次月预计
				.addFilter(new AccPipeFilter(sjAcc, 17, dh.getXjdSecondMonth())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs()))

				// 下季度末月预计
				.addFilter(new AccPipeFilter(sjAcc, 18, dh.getXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs()))

				// 下季度预计合计
				.addFilter(new AccPipeFilter(sjAcc, 19, dh.getXjdFirstMonth(), dh
						.getXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs()))
				.addFilter(new AccPipeFilter(sjAcc, 19, dh.getXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(getTimePointNumberZbs()))


				// 下季度预计完成率
				.addFilter(wclFilter.add(20, 19, 2))

				// 下季度年度累计
				.addFilter(new AccPipeFilter(sjAcc, 21, dh.getXjdDnFirstMonth(), dh
						.getXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs())
						.excludeZbs(getZhHiddenZbs()))
				.addFilter(copyFilter
						.add(getTimePointNumberZbs(), 19, 21))

				// 下季度年度累计完成率
				.addFilter(wclFilter
						.add(22, 21, 25))

				// 下季度去年同期年度累计
				.addFilter(new AccPipeFilter(sjAcc, 23, dh.getQntqXjdDnFirstMonth(),
						dh.getQntqXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs())
						.excludeZbs(getZhHiddenZbs()))
				.addFilter(new AccPipeFilter(sjAcc, 23, dh.getQntqXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(getTimePointNumberZbs()))

				// 下季度年度累计同比增幅
				.addFilter(tbzzFilter
						.add(24, 21, 23))

				// 添加特殊指标过滤器
				.addFilter(new RatioPipeFilter().exclude(5)// 计划完成率
						.exclude(7)// 同比增幅
						.exclude(9)// 季度计划完成率
						.exclude(11)// 同比增幅
						.exclude(13)// 累计计划完成率
						.exclude(15)// 同比增幅
						.exclude(20)// 下季度预计完成率
						.exclude(22)// 下季度年度累计完成率
						.exclude(24))// 下季度年度累计同比增幅
				.addFilter(tbzzFilter)
				.addFilter(wclFilter);
	}

	@Override
	public int getColumnCount() {
		return 26;
	}

}
