package com.tbea.ic.operation.service.ydzb.pipe.configurator.ydhb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.configurator.ConfiguratorUtil;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.CopyPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.RatioPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.WclPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.ZzlPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.YdjhProportionAccPipeFilter;

//月度环比
public class YdhbConfigurator implements IPipeConfigurator {

	IAccumulator sjAcc;
	IAccumulator yjhAcc;
	IAccumulator njhAcc;
	SbdNdjhZbDao sbdzbDao;
	
	public YdhbConfigurator(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc) {
		this.sbdzbDao = sbdzbDao;
		this.sjAcc = sjAcc;
		this.yjhAcc = yjhAcc;
		this.njhAcc = njhAcc;
	}

	@Override
	public void onConfiguring(IPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		List<Company> nonSbdCompanies = new ArrayList<Company>();
		List<Company> sbdCompanies = new ArrayList<Company>();
		ConfiguratorUtil.seperate(sbdzbDao, allCompanies, sbdCompanies, nonSbdCompanies);

		DateHelper dh = new DateHelper(pipe.getDate());

		WclPipeFilter wclFilter = new WclPipeFilter();
		ZzlPipeFilter zzlFilter = new ZzlPipeFilter();
		CopyPipeFilter copyFilter = new CopyPipeFilter();
		List<Integer> gsztzbs = pipe.getIndicators();
		
		// 全年计划
		pipe.addFilter(new AccPipeFilter(njhAcc, 0)
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs()));

		Calendar cal = Calendar.getInstance();
		cal.setTime(dh.getCur());
		// 当月计划
		if (sbdCompanies.isEmpty() || cal.get(Calendar.YEAR) >= 2016) {
			pipe.addFilter(new AccPipeFilter(yjhAcc, 1)
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
						.excludeZbs(ConfiguratorUtil.getRatioZbs()));
		} else {
			pipe.addFilter(new AccPipeFilter(yjhAcc, 1).includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
						.excludeZbs(ConfiguratorUtil.getRatioZbs())
						.exclude(GSZB.YSZK32)
						.exclude(GSZB.CH35))
				.addFilter(new YdjhProportionAccPipeFilter(sbdzbDao, sjAcc, 1, dh.getFirstMonth(), dh.getCur())
						.includeCompanies(sbdCompanies)
						.include(GSZB.YSZK32)
						.include(GSZB.CH35));

			if (!nonSbdCompanies.isEmpty()) {
				pipe.addFilter(new AccPipeFilter(yjhAcc, 1)
					.includeCompanies(nonSbdCompanies)
					.include(GSZB.YSZK32)
					.include(GSZB.CH35));
			}
		}

		// 当月实际
		pipe.addFilter(new AccPipeFilter(sjAcc, 2)
				.includeCompanies(allCompanies)
				.includeZbs(gsztzbs)
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs()))

			// 计划完成率
			.addFilter(wclFilter.add(3, 2, 1))

			// 去年同期
			.addFilter(new AccPipeFilter(sjAcc, 16, dh.getQntq())
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
					.excludeZbs(ConfiguratorUtil.getRatioZbs()))

			// 上月实际
			.addFilter(new AccPipeFilter(sjAcc, 4, dh.getPreMonth())
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
					.excludeZbs(ConfiguratorUtil.getRatioZbs()))
			
			//环比增幅
			.addFilter(zzlFilter.add(5, 2, 4))
			
			//同比增幅
			.addFilter(zzlFilter.add(6, 2, 16))

			// 季度计划
			.addFilter(new AccPipeFilter(yjhAcc, 7, dh.getJdStart(), DateHelper.getJdEnd(dh.getCur()))
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
					.excludeZbs(ConfiguratorUtil.getRatioZbs())
					.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
			.addFilter(new AccPipeFilter(yjhAcc, 7, DateHelper.getJdEnd(dh.getCur()))
							.includeCompanies(allCompanies)
							.includeZbs(ConfiguratorUtil.getTimePointNumberZbs()))

			// 季度累计
			.addFilter(new AccPipeFilter(sjAcc, 8, dh.getJdStart(), dh.getCur())
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
					.excludeZbs(ConfiguratorUtil.getRatioZbs())
					.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
			.addFilter(copyFilter
					.add(ConfiguratorUtil.getTimePointNumberZbs(), 2, 8))


			// 季度计划完成率
			.addFilter(wclFilter.add(9, 8, 7))

			// 季度去年同期
			.addFilter(new AccPipeFilter(sjAcc, 10, dh.getQntqJdStart(), dh.getQntq())
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
					.excludeZbs(ConfiguratorUtil.getRatioZbs())
					.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
			.addFilter(new AccPipeFilter(sjAcc, 10, dh.getQntq())
					.includeCompanies(allCompanies)
					.includeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
					
			// 同比增幅
			.addFilter(zzlFilter.add(11, 8, 10))

			// 年度累计
			.addFilter(new AccPipeFilter(sjAcc, 12, dh.getFirstMonth(), dh.getCur())
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
					.excludeZbs(ConfiguratorUtil.getRatioZbs())
					.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
			.addFilter(copyFilter
					.add(ConfiguratorUtil.getTimePointNumberZbs(), 2, 12))

			// 累计计划完成率
			.addFilter(wclFilter.add(13, 12, 0))

			// 去年同期
			.addFilter(new AccPipeFilter(sjAcc, 14, dh.getQnfirstMonth(), dh
					.getQntq())
					.includeCompanies(allCompanies)
					.includeZbs(gsztzbs)
					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
					.excludeZbs(ConfiguratorUtil.getRatioZbs())
					.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
			.addFilter(copyFilter
					.add(ConfiguratorUtil.getTimePointNumberZbs(), 10, 14))

			// 同比增幅
			.addFilter(zzlFilter.add(15, 12, 14))

			// 添加特殊指标过滤器
			.addFilter(new RatioPipeFilter()
					.excludeCol(3)// 计划完成率
					.excludeCol(5)// 环比增幅
					.excludeCol(6)// 同比增幅
					.excludeCol(9)// 季度计划完成率
					.excludeCol(11)// 同比增幅
					.excludeCol(13)// 累计计划完成率
					.excludeCol(15))// 同比增幅
			.addFilter(zzlFilter)
			.addFilter(wclFilter);
	}

	@Override
	public int getColumnCount() {
		return 17;
	}

}
