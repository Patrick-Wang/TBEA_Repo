package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.second;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.ydzb.pipe.IPipe;
import com.tbea.ic.operation.service.ydzb.pipe.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.AbstractSbdPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.filter.indicatorbased.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.indicatorbased.CopyPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.indicatorbased.RatioPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.indicatorbased.WclPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.indicatorbased.YdjhProportionAccPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.indicatorbased.ZzlPipeFilter;

public class SecondSeasonPredictionConfigurator extends AbstractSbdPipeConfigurator {

	IAccumulator sjAcc;
	IAccumulator yjhAcc;
	IAccumulator njhAcc;

	public SecondSeasonPredictionConfigurator(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc) {
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

		// 全年计划
		pipe.add(new AccPipeFilter(njhAcc, 0)
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()));

		// 当月计划
		if (sbdCompanies.isEmpty()) {
			pipe.add(new AccPipeFilter(yjhAcc, 2)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getZbIds())
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs()));
		} else {
			pipe.add(new AccPipeFilter(yjhAcc, 2)
							.includeCompanies(allCompanies)
							.includeZbs(pipe.getZbIds())
							.excludeZbs(getInvisiableZbs())
							.excludeZbs(getRatioZbs())
							.exclude(GSZB.YSZK)
							.exclude(GSZB.CH))
							
				.add(new YdjhProportionAccPipeFilter(sbdzbDao, sjAcc, 2, dh.getFirstMonth(), dh.getCur())
							.includeCompanies(sbdCompanies)
							.include(GSZB.YSZK)
							.include(GSZB.CH));

			if (!nonSbdCompanies.isEmpty()) {
				pipe.add(new AccPipeFilter(yjhAcc, 2)
						.includeCompanies(nonSbdCompanies)
						.include(GSZB.YSZK)
						.include(GSZB.CH));
			}
		}
		// 季度计划
		pipe.add(new AccPipeFilter(yjhAcc, 1, dh.getJdStart(), DateHelper.getJdEnd(dh.getCur()))
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs()))
		.add(copyFilter
				.add(getTimePointNumberZbs(), 2, 1))


		// 当月实际
		.add(new AccPipeFilter(sjAcc, 3)
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()))


		// 计划完成率
		.add(wclFilter.add(4, 3, 2))

		// 去年同期
		.add(new AccPipeFilter(sjAcc, 5, dh.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()))

		// 同比增幅
		.add(tbzzFilter.add(6, 3, 5))
		
		
		// 季度累计
		.add(new AccPipeFilter(sjAcc, 7, dh.getJdStart(), dh.getCur())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs())
				.excludeZbs(getZhHiddenZbs()))
		.add(copyFilter
				.add(getTimePointNumberZbs(), 3, 7))

		// 季度计划完成率
		.add(wclFilter.add(8, 7, 1))

		// 季度去年同期
		.add(new AccPipeFilter(sjAcc, 9, dh.getQntqJdStart(), dh
				.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs())
				.excludeZbs(getZhHiddenZbs()))
		.add(new AccPipeFilter(sjAcc, 9, dh.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(getTimePointNumberZbs()))

		// 同比增幅
		.add(tbzzFilter.add(10, 7, 9))
		
		
		//末月预计
		.add(new AccPipeFilter(sjAcc, 11, dh.getLastMonthinSeason())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()))
		
		// 季度预计合计
		.add(new AccPipeFilter(sjAcc, 12, dh.getCur(), dh.getLastMonthinSeason())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs()))
		.add(copyFilter
				.add(getTimePointNumberZbs(), 11, 12))

		// 季度预计完成率
		.add(wclFilter
				.add(13, 12, 1))

		// 季度去年同期
		.add(new AccPipeFilter(sjAcc, 14, dh.getQntqJdStart(), dh
				.getQntqJdEnd())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs()))
		.add(new AccPipeFilter(sjAcc, 14, dh.getQntqJdStart())
				.includeCompanies(allCompanies)
				.includeZbs(getTimePointNumberZbs()))


		// 同比增幅
		.add(tbzzFilter.add(15, 12, 14))

		// 年度累计
		.add(new AccPipeFilter(sjAcc, 16, dh.getFirstMonth(), dh
				.getCur())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs())
				.excludeZbs(getZhHiddenZbs()))
		.add(copyFilter
				.add(getTimePointNumberZbs(), 3, 16))

		// 累计计划完成率
		.add(wclFilter.add(17, 16, 0))

		// 去年同期
		.add(new AccPipeFilter(sjAcc, 18, dh.getQnfirstMonth(), dh
				.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getZbIds())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs())
				.excludeZbs(getZhHiddenZbs()))
		.add(copyFilter
				.add(getTimePointNumberZbs(), 5, 18))


		// 同比增幅
		.add(tbzzFilter
				.add(19, 16, 18))

		// 添加特殊指标过滤器
		.add(new RatioPipeFilter().exclude(4)// 当月计划完成率
				.exclude(6)// 当月同比增幅
				.exclude(8)// 季度累计完成率
				.exclude(10)// 季度累计同比增幅
				.exclude(13)// 季度预计累计计划完成率
				.exclude(15)// 季度预计同比增幅
				.exclude(17)// 年度累计计划完成率
				.exclude(19))// 年度同比增幅
		.add(tbzzFilter)
		.add(wclFilter);
	}

	@Override
	public int getColumnCount() {
		return 20;
	}

}