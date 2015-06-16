package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.first;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.CopyPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.RatioPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.WclPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.ZzlPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.AbstractSbdPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.YdjhProportionAccPipeFilter;

public class FirstSeasonPredictionConfigurator extends AbstractSbdPipeConfigurator{

	IAccumulator sjAcc;
	IAccumulator yjhAcc;
	IAccumulator njhAcc;

	public FirstSeasonPredictionConfigurator(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc) {
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
		pipe.addFilter(new AccPipeFilter(njhAcc, 0)
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()));
		
		

		// 当月计划
		if (sbdCompanies.isEmpty()) {
			pipe.addFilter(new AccPipeFilter(yjhAcc, 2)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators())
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs()));
		} else {
			pipe.addFilter(new AccPipeFilter(yjhAcc, 2)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators())
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs())
					.exclude(GSZB.YSZK)
					.exclude(GSZB.CH))
						
				.addFilter(new YdjhProportionAccPipeFilter(sbdzbDao, sjAcc, 2, dh.getFirstMonth(), dh.getCur())
					.includeCompanies(sbdCompanies)
					.include(GSZB.YSZK)
					.include(GSZB.CH));

			if (!nonSbdCompanies.isEmpty()) {
				pipe.addFilter(new AccPipeFilter(yjhAcc, 2)
						.includeCompanies(nonSbdCompanies)
						.include(GSZB.YSZK)
						.include(GSZB.CH));
			}
		}
		// 季度计划
		pipe.addFilter(new AccPipeFilter(yjhAcc, 1, dh.getJdStart(), DateHelper.getJdEnd(dh.getCur()))
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs()))
			.addFilter(copyFilter
				.add(getTimePointNumberZbs(), 2, 1))

		// 当月实际
		.addFilter(new AccPipeFilter(sjAcc, 3)
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()))

		// 计划完成率
		.addFilter(wclFilter.add(4, 3, 2))

		// 去年同期
		.addFilter(new AccPipeFilter(sjAcc, 5, dh.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()))

		// 同比增幅
		.addFilter(tbzzFilter.add(6, 3, 5))
		
		//次月预计		
		.addFilter(new AccPipeFilter(sjAcc, 7, dh.getSecondMonthinSeason())
			.includeCompanies(allCompanies)
			.includeZbs(pipe.getIndicators())
			.excludeZbs(getInvisiableZbs())
			.excludeZbs(getRatioZbs()))

		//末月预计
		.addFilter(new AccPipeFilter(sjAcc, 8, dh.getLastMonthinSeason())
			.includeCompanies(allCompanies)
			.includeZbs(pipe.getIndicators())
			.excludeZbs(getInvisiableZbs())
			.excludeZbs(getRatioZbs()))
		
		// 季度预计合计
		.addFilter(new AccPipeFilter(sjAcc, 9, dh.getCur(), dh.getLastMonthinSeason())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs()))
		.addFilter(copyFilter
				.add(getTimePointNumberZbs(), 8, 9))

		// 季度预计完成率
		.addFilter(wclFilter.add(10, 9, 1))

		// 季度去年同期
		.addFilter(new AccPipeFilter(sjAcc, 11, dh.getQntqJdStart(), dh.getQntqJdEnd())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs())
				.excludeZbs(getZhHiddenZbs()))
		.addFilter(new AccPipeFilter(sjAcc, 11, dh.getQntqJdStart())
				.includeCompanies(allCompanies)
				.includeZbs(getTimePointNumberZbs()))

		// 同比增幅
		.addFilter(tbzzFilter.add(12, 9, 11))

		// 年度累计
		.addFilter(new AccPipeFilter(sjAcc, 13, dh.getFirstMonth(), dh.getCur())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs())
				.excludeZbs(getZhHiddenZbs()))
		.addFilter(copyFilter
				.add(getTimePointNumberZbs(), 3, 13))

		// 累计计划完成率
		.addFilter(wclFilter.add(14, 13, 0))

		// 去年同期
		.addFilter(new AccPipeFilter(sjAcc, 15, dh.getQnfirstMonth(), dh.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs())
				.excludeZbs(getZhHiddenZbs()))
		.addFilter(copyFilter
				.add(getTimePointNumberZbs(), 5, 15))

		// 同比增幅
		.addFilter(tbzzFilter.add(16, 13, 15))

		// 添加特殊指标过滤器
		.addFilter(new RatioPipeFilter().exclude(4)// 计划完成率
				.exclude(6)// 同比增幅
				.exclude(10)// 季度计划完成率
				.exclude(12)// 同比增幅
				.exclude(14)// 累计计划完成率
				.exclude(16))// 同比增幅
		.addFilter(tbzzFilter)
		.addFilter(wclFilter);
	}

	@Override
	public int getColumnCount() {
		return 17;
	}

}
