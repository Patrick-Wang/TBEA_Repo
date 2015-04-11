package com.tbea.ic.operation.service.ydzb.gszb.pipe.configurator;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.ydzb.gszb.acc.IAccumulator;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.AccPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.YdjhProportionAccPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.CopyPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.SpecialPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.ZzlPipeFilter;
import com.tbea.ic.operation.service.ydzb.gszb.pipe.filter.WclPipeFilter;

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
	public void onConfiguring(GszbPipe pipe) {
		List<Company> allCompanies = pipe.getCompanies();
		List<Company> nonSbdCompanies = new ArrayList<Company>();
		List<Company> sbdCompanies = new ArrayList<Company>();
		seperate(allCompanies, sbdCompanies, nonSbdCompanies);
		List<Integer> specialZbs = getSpecialZbs();

		DateHelper dh = new DateHelper(pipe.getDate());

		WclPipeFilter wclFilter = new WclPipeFilter();
		ZzlPipeFilter zzlFilter = new ZzlPipeFilter();
		CopyPipeFilter copyFilter = new CopyPipeFilter();
		List<Integer> gsztzbs = pipe.getZbIds();
		
		// 全年计划
				pipe.add(new AccPipeFilter(njhAcc, 0)
						.includeCompanies(allCompanies)
						.includeZbs(gsztzbs)
						.excludeZbs(specialZbs)
						.include(GSZB.RS)
						.include(GSZB.YSZK)
						.include(GSZB.CH)
						.include(GSZB.QZZJXMCH));

				// 当月计划
				if (sbdCompanies.isEmpty()) {
					pipe.add(new AccPipeFilter(yjhAcc, 1)
								.includeCompanies(allCompanies)
								.includeZbs(gsztzbs)
								.excludeZbs(specialZbs)
								.include(GSZB.RS)
								.include(GSZB.YSZK)
								.include(GSZB.CH)
								.include(GSZB.QZZJXMCH));
				} else {
					pipe.add(new AccPipeFilter(yjhAcc, 1).includeCompanies(allCompanies)
								.includeZbs(gsztzbs)
								.excludeZbs(specialZbs)
								.include(GSZB.RS)
								.include(GSZB.QZZJXMCH))
						.add(new YdjhProportionAccPipeFilter(sbdzbDao, sjAcc, 1, dh.getFirstMonth(), dh.getCur())
								.includeCompanies(sbdCompanies)
								.include(GSZB.YSZK)
								.include(GSZB.CH));

					if (!nonSbdCompanies.isEmpty()) {
						pipe.add(new AccPipeFilter(yjhAcc, 1)
							.includeCompanies(nonSbdCompanies)
							.include(GSZB.YSZK)
							.include(GSZB.CH));
					}
				}

						// 当月实际
					pipe.add(
						new AccPipeFilter(sjAcc, 2)
							.includeCompanies(allCompanies)
							.includeZbs(gsztzbs)
							.excludeZbs(specialZbs)
							.include(GSZB.YSZK)
							.include(GSZB.CH)
							.include(GSZB.QZZJXMCH)
							.include(GSZB.RS))

						// 计划完成率
						.add(wclFilter.add(3, 2, 1))

						// 去年同期
						.add(new AccPipeFilter(sjAcc, 16, dh.getQntq())
								.includeCompanies(allCompanies).includeZbs(gsztzbs)
								.excludeZbs(specialZbs)
								.include(GSZB.YSZK)
								.include(GSZB.CH)
								.include(GSZB.QZZJXMCH)
								.include(GSZB.RS))

						// 上月实际
						.add(new AccPipeFilter(sjAcc, 4, dh.getPreMonth())
								.includeCompanies(allCompanies)
								.includeZbs(gsztzbs)
								.excludeZbs(specialZbs)
								.include(GSZB.YSZK)
								.include(GSZB.CH)
								.include(GSZB.QZZJXMCH)
								.include(GSZB.RS))
						
						//环比增幅
						.add(zzlFilter.add(5, 2, 4))
						
						//同比增幅
						.add(zzlFilter.add(6, 2, 16))

						// 季度计划
						.add(new AccPipeFilter(yjhAcc, 7, dh.getJdStart(), dh.getCur())
								.includeCompanies(allCompanies)
								.includeZbs(gsztzbs)
								.excludeZbs(specialZbs))
						.add(copyFilter
								.add(GSZB.CH, 1, 7)
								.add(GSZB.QZZJXMCH, 1, 7)
								.add(GSZB.YSZK, 1, 7)
								.add(GSZB.RS, 1, 7))

						// 季度累计
						.add(new AccPipeFilter(sjAcc, 8, dh.getJdStart(), dh.getCur())
								.includeCompanies(allCompanies)
								.includeZbs(gsztzbs)
								.excludeZbs(specialZbs))
						.add(copyFilter
								.add(GSZB.CH, 2, 8)
								.add(GSZB.QZZJXMCH, 2, 8)
								.add(GSZB.YSZK, 2, 8)
								.add(GSZB.RS, 2, 8))

						// 季度计划完成率
						.add(wclFilter.add(9, 8, 7))

						// 季度去年同期
						.add(new AccPipeFilter(sjAcc, 10, dh.getQntqJdStart(), dh.getQntq())
								.includeCompanies(allCompanies)
								.includeZbs(gsztzbs)
								.excludeZbs(specialZbs))
						.add(new AccPipeFilter(sjAcc, 10, dh.getQntq())
								.includeCompanies(allCompanies)
								.include(GSZB.YSZK)
								.include(GSZB.CH)
								.include(GSZB.QZZJXMCH)
								.include(GSZB.RS))

						// 同比增幅
						.add(zzlFilter.add(11, 8, 10))

						// 年度累计
						.add(new AccPipeFilter(sjAcc, 12, dh.getFirstMonth(), dh.getCur())
								.includeCompanies(allCompanies)
								.includeZbs(gsztzbs)
								.excludeZbs(specialZbs))
						.add(copyFilter
								.add(GSZB.CH, 2, 12)
								.add(GSZB.QZZJXMCH, 2, 12)
								.add(GSZB.YSZK, 2, 12)
								.add(GSZB.RS, 2, 12))

						// 累计计划完成率
						.add(wclFilter.add(13, 12, 0))

						// 去年同期
						.add(new AccPipeFilter(sjAcc, 14, dh.getQnfirstMonth(), dh
								.getQntq())
								.includeCompanies(allCompanies)
								.includeZbs(gsztzbs)
								.excludeZbs(specialZbs))
						.add(copyFilter
								.add(GSZB.CH, 10, 14)
								.add(GSZB.QZZJXMCH, 10, 14)
								.add(GSZB.YSZK, 10, 14)
								.add(GSZB.RS, 10, 14))

						// 同比增幅
						.add(zzlFilter.add(15, 12, 14))

						// 添加特殊指标过滤器
						.add(new SpecialPipeFilter()
								.exclude(3)// 计划完成率
								.exclude(5)// 环比增幅
								.exclude(6)// 同比增幅
								.exclude(9)// 季度计划完成率
								.exclude(11)// 同比增幅
								.exclude(13)// 累计计划完成率
								.exclude(15))// 同比增幅
						.add(zzlFilter)
						.add(wclFilter);
	}

	@Override
	public int getColumnCount() {
		return 17;
	}

}
