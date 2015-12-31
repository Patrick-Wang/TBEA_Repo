package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.second;

import java.util.ArrayList;
import java.util.Calendar;
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
		pipe.addFilter(new AccPipeFilter(njhAcc, 0)
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()));

		Calendar cal = Calendar.getInstance();
		cal.setTime(dh.getCur());
		// 当月计划
		if (sbdCompanies.isEmpty() || cal.get(Calendar.YEAR) >= 2016) {
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
							.exclude(GSZB.YSZK32)
							.exclude(GSZB.CH35))
							
				.addFilter(new YdjhProportionAccPipeFilter(sbdzbDao, sjAcc, 2, dh.getFirstMonth(), dh.getCur())
							.includeCompanies(sbdCompanies)
							.include(GSZB.YSZK32)
							.include(GSZB.CH35));

			if (!nonSbdCompanies.isEmpty()) {
				pipe.addFilter(new AccPipeFilter(yjhAcc, 2)
						.includeCompanies(nonSbdCompanies)
						.include(GSZB.YSZK32)
						.include(GSZB.CH35));
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
		
		
		// 季度累计
		.addFilter(new AccPipeFilter(sjAcc, 7, dh.getJdStart(), dh.getCur())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs())
				.excludeZbs(getZhHiddenZbs()))
		.addFilter(copyFilter
				.add(getTimePointNumberZbs(), 3, 7))

		// 季度计划完成率
		.addFilter(wclFilter.add(8, 7, 1))

		// 季度去年同期
		.addFilter(new AccPipeFilter(sjAcc, 9, dh.getQntqJdStart(), dh
				.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs())
				.excludeZbs(getZhHiddenZbs()))
		.addFilter(new AccPipeFilter(sjAcc, 9, dh.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(getTimePointNumberZbs()))

		// 同比增幅
		.addFilter(tbzzFilter.add(10, 7, 9))
		
		
		//末月预计
		.addFilter(new AccPipeFilter(sjAcc, 11, dh.getLastMonthinSeason())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()))
		
		// 季度预计合计
		.addFilter(new AccPipeFilter(sjAcc, 12, dh.getJdStart(), dh.getLastMonthinSeason())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs()))
		.addFilter(copyFilter
				.add(getTimePointNumberZbs(), 11, 12))

		// 季度预计完成率
		.addFilter(wclFilter
				.add(13, 12, 1))

		// 季度去年同期
		.addFilter(new AccPipeFilter(sjAcc, 14, dh.getQntqJdStart(), dh
				.getQntqJdEnd())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs()))
		.addFilter(new AccPipeFilter(sjAcc, 14, dh.getQntqJdStart())
				.includeCompanies(allCompanies)
				.includeZbs(getTimePointNumberZbs()))


		// 同比增幅
		.addFilter(tbzzFilter.add(15, 12, 14))

		// 年度累计
		.addFilter(new AccPipeFilter(sjAcc, 16, dh.getFirstMonth(), dh
				.getCur())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs())
				.excludeZbs(getZhHiddenZbs()))
		.addFilter(copyFilter
				.add(getTimePointNumberZbs(), 3, 16))

		// 累计计划完成率
		.addFilter(wclFilter.add(17, 16, 0))

		// 去年同期
		.addFilter(new AccPipeFilter(sjAcc, 18, dh.getQnfirstMonth(), dh
				.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs())
				.excludeZbs(getTimePointNumberZbs())
				.excludeZbs(getZhHiddenZbs()))
		.addFilter(copyFilter
				.add(getTimePointNumberZbs(), 5, 18))


		// 同比增幅
		.addFilter(tbzzFilter
				.add(19, 16, 18))

		// 添加特殊指标过滤器
		.addFilter(new RatioPipeFilter().exclude(4)// 当月计划完成率
				.exclude(6)// 当月同比增幅
				.exclude(8)// 季度累计完成率
				.exclude(10)// 季度累计同比增幅
				.exclude(13)// 季度预计累计计划完成率
				.exclude(15)// 季度预计同比增幅
				.exclude(17)// 年度累计计划完成率
				.exclude(19))// 年度同比增幅
		.addFilter(tbzzFilter)
		.addFilter(wclFilter);
	}

	@Override
	public int getColumnCount() {
		return 20;
	}

}
