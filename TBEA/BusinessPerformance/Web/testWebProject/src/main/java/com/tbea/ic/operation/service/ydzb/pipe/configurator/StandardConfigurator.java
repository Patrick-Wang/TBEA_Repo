package com.tbea.ic.operation.service.ydzb.pipe.configurator;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.CopyPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.RatioPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.WclPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.YdjhProportionAccPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.ZzlPipeFilter;

public class StandardConfigurator extends AbstractSbdPipeConfigurator {

	IAccumulator sjAcc;
	IAccumulator yjhAcc;
	IAccumulator njhAcc;
	
	
	public StandardConfigurator(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc) {
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
			pipe.addFilter(new AccPipeFilter(yjhAcc, 1)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators())
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs()));

		} else {
			pipe.addFilter(new AccPipeFilter(yjhAcc, 1)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators())
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs())
					.exclude(GSZB.YSZK)
					.exclude(GSZB.CH))
					
				.addFilter(new YdjhProportionAccPipeFilter(sbdzbDao, sjAcc, 1, dh.getFirstMonth(), dh.getCur())
					.includeCompanies(sbdCompanies)
					.include(GSZB.YSZK)
					.include(GSZB.CH));

			if (!nonSbdCompanies.isEmpty()) {
				pipe.addFilter(new AccPipeFilter(yjhAcc, 1)
						.includeCompanies(nonSbdCompanies)
						.include(GSZB.YSZK)
						.include(GSZB.CH));
			}
		}

				// 当月实际
			pipe.addFilter(new AccPipeFilter(sjAcc, 2)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators())
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs()))

				// 计划完成率
				.addFilter(wclFilter.add(3, 2, 1))

				// 去年同期
				.addFilter(new AccPipeFilter(sjAcc, 4, dh.getQntq())
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs()))

				// 同比增幅
				.addFilter(tbzzFilter.add(5, 2, 4))

				// 季度计划
				.addFilter(new AccPipeFilter(yjhAcc, 6, dh.getJdStart(), DateHelper.getJdEnd(dh.getCur()))
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs()))
				.addFilter(copyFilter
						.add(getTimePointNumberZbs(), 1, 6))


				// 季度累计
				.addFilter(new AccPipeFilter(sjAcc, 7, dh.getJdStart(), dh.getCur())
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs())
						.excludeZbs(getZhHiddenZbs()))
				.addFilter(copyFilter
						.add(getTimePointNumberZbs(), 2, 7))

				// 季度计划完成率
				.addFilter(wclFilter.add(8, 7, 6))

				// 季度去年同期
				.addFilter(new AccPipeFilter(sjAcc, 9, dh.getQntqJdStart(), dh.getQntq())
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs())
						.excludeZbs(getZhHiddenZbs()))
				.addFilter(copyFilter
						.add(getTimePointNumberZbs(), 4, 9))


				// 同比增幅
				.addFilter(tbzzFilter.add(10, 7, 9))

				// 年度累计
				.addFilter(new AccPipeFilter(sjAcc, 11, dh.getFirstMonth(), dh.getCur())
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs())
						.excludeZbs(getZhHiddenZbs()))
				.addFilter(copyFilter
						.add(getTimePointNumberZbs(), 2, 11))

				// 累计计划完成率
				.addFilter(wclFilter.add(12, 11, 0))

				// 去年同期
				.addFilter(new AccPipeFilter(sjAcc, 13, dh.getQnfirstMonth(), dh.getQntq())
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs())
						.excludeZbs(getTimePointNumberZbs())
						.excludeZbs(getZhHiddenZbs()))
				.addFilter(copyFilter
						.add(getTimePointNumberZbs(), 9, 13))


				// 同比增幅
				.addFilter(tbzzFilter.add(14, 11, 13))

				// 添加特殊指标过滤器
				.addFilter(new RatioPipeFilter()
						.exclude(3)// 计划完成率
						.exclude(5)// 同比增幅
						.exclude(8)// 季度计划完成率
						.exclude(10)// 同比增幅
						.exclude(12)// 累计计划完成率
						.exclude(14))// 同比增幅
				.addFilter(tbzzFilter)
				.addFilter(wclFilter);
	}

	@Override
	public int getColumnCount() {
		return 15;
	}
}
