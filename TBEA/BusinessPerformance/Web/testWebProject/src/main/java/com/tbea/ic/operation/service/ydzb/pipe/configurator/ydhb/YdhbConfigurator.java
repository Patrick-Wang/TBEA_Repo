package com.tbea.ic.operation.service.ydzb.pipe.configurator.ydhb;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.core.BasicPipe;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.AbstractSbdPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.CopyPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.RatioPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.WclPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.YdjhProportionAccPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.ZzlPipeFilter;

//月度环比
public class YdhbConfigurator extends AbstractSbdPipeConfigurator {

	IAccumulator sjAcc;
	IAccumulator yjhAcc;
	IAccumulator njhAcc;
	
	
	public YdhbConfigurator(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc) {
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
		ZzlPipeFilter zzlFilter = new ZzlPipeFilter();
		CopyPipeFilter copyFilter = new CopyPipeFilter();
		List<Integer> gsztzbs = pipe.getIndicators();
		
		// 全年计划
		pipe.addFilter(new AccPipeFilter(njhAcc, 0)
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()));

		// 当月计划
		if (sbdCompanies.isEmpty()) {
			pipe.addFilter(new AccPipeFilter(yjhAcc, 1)
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(getInvisiableZbs())
						.excludeZbs(getRatioZbs()));
		} else {
			pipe.addFilter(new AccPipeFilter(yjhAcc, 1).includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
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
				.includeZbs(gsztzbs)
				.excludeZbs(getInvisiableZbs())
				.excludeZbs(getRatioZbs()))

			// 计划完成率
			.addFilter(wclFilter.add(3, 2, 1))

			// 去年同期
			.addFilter(new AccPipeFilter(sjAcc, 16, dh.getQntq())
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs()))

			// 上月实际
			.addFilter(new AccPipeFilter(sjAcc, 4, dh.getPreMonth())
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs()))
			
			//环比增幅
			.addFilter(zzlFilter.add(5, 2, 4))
			
			//同比增幅
			.addFilter(zzlFilter.add(6, 2, 16))

			// 季度计划
			.addFilter(new AccPipeFilter(yjhAcc, 7, dh.getJdStart(), DateHelper.getJdEnd(dh.getCur()))
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs())
					.excludeZbs(getTimePointNumberZbs()))
			.addFilter(copyFilter
					.add(getTimePointNumberZbs(), 1, 7))

			// 季度累计
			.addFilter(new AccPipeFilter(sjAcc, 8, dh.getJdStart(), dh.getCur())
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs())
					.excludeZbs(getTimePointNumberZbs()))
			.addFilter(copyFilter
					.add(getTimePointNumberZbs(), 2, 8))


			// 季度计划完成率
			.addFilter(wclFilter.add(9, 8, 7))

			// 季度去年同期
			.addFilter(new AccPipeFilter(sjAcc, 10, dh.getQntqJdStart(), dh.getQntq())
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs())
					.excludeZbs(getTimePointNumberZbs()))
			.addFilter(new AccPipeFilter(sjAcc, 10, dh.getQntq())
					.includeCompanies(allCompanies)
					.includeZbs(getTimePointNumberZbs()))
					
			// 同比增幅
			.addFilter(zzlFilter.add(11, 8, 10))

			// 年度累计
			.addFilter(new AccPipeFilter(sjAcc, 12, dh.getFirstMonth(), dh.getCur())
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs())
					.excludeZbs(getTimePointNumberZbs()))
			.addFilter(copyFilter
					.add(getTimePointNumberZbs(), 2, 12))

			// 累计计划完成率
			.addFilter(wclFilter.add(13, 12, 0))

			// 去年同期
			.addFilter(new AccPipeFilter(sjAcc, 14, dh.getQnfirstMonth(), dh
					.getQntq())
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(getInvisiableZbs())
					.excludeZbs(getRatioZbs())
					.excludeZbs(getTimePointNumberZbs()))
			.addFilter(copyFilter
					.add(getTimePointNumberZbs(), 10, 14))

			// 同比增幅
			.addFilter(zzlFilter.add(15, 12, 14))

			// 添加特殊指标过滤器
			.addFilter(new RatioPipeFilter()
					.exclude(3)// 计划完成率
					.exclude(5)// 环比增幅
					.exclude(6)// 同比增幅
					.exclude(9)// 季度计划完成率
					.exclude(11)// 同比增幅
					.exclude(13)// 累计计划完成率
					.exclude(15))// 同比增幅
			.addFilter(zzlFilter)
			.addFilter(wclFilter);
	}

	@Override
	public int getColumnCount() {
		return 17;
	}

}
