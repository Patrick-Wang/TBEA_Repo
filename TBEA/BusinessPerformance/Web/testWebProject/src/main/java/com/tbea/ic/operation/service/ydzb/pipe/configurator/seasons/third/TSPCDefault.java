package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.third;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.configurator.ConfiguratorUtil;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.CopyPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.RatioPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.WclPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.ZzlPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.YdjhProportionAccPipeFilter;
import com.xml.frame.report.util.EasyList;

class TSPCDefault extends ThirdSeasonPredictionConfigurator {

	public TSPCDefault(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc,
			IAccumulator yjhAcc, IAccumulator njhAcc) {
		super(sbdzbDao, sjAcc, yjhAcc, njhAcc);

	}

	List<Company> allCompanies;
	List<Company> nonSbdCompanies = new ArrayList<Company>();
	List<Company> sbdCompanies = new ArrayList<Company>();
	DateHelper dh;
	WclPipeFilter wclFilter;
	ZzlPipeFilter tbzzFilter;
	CopyPipeFilter copyFilter;
	IPipe pipe;

	protected EasyList<IPipeFilter> list = new EasyList<IPipeFilter>();

	@Override
	protected void onStart(IPipe pipe) {
		list.clear();
		this.pipe = pipe;
		allCompanies = pipe.getCompanies();
		ConfiguratorUtil.seperate(sbdzbDao, allCompanies, sbdCompanies, nonSbdCompanies);
		dh = new DateHelper(pipe.getDate());
		wclFilter = new WclPipeFilter();
		tbzzFilter = new ZzlPipeFilter();
		copyFilter = new CopyPipeFilter();	
	}

	@Override
	protected void qnjh() {
		// 全年计划
		list.add(
				new AccPipeFilter(njhAcc, 0).includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
						.excludeZbs(ConfiguratorUtil.getRatioZbs()));
	}

	@Override
	protected void jdjh() {
		// 季度计划
		list.add(new AccPipeFilter(yjhAcc, 1, dh.getJdStart(), DateHelper.getJdEnd(dh.getCur()))
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs())
				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
		.add(copyFilter
				.add(ConfiguratorUtil.getTimePointNumberZbs(), 3, 1));

	}

	@Override
	protected void xjdjh() {
		// 下季度计划
		list.add(new AccPipeFilter(yjhAcc, 2, dh.getXjdFirstMonth(), dh
				.getXjdLastMonth())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs())
				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
		.add(new AccPipeFilter(yjhAcc, 2, dh.getXjdLastMonth())
				.includeCompanies(allCompanies)
				.includeZbs(ConfiguratorUtil.getTimePointNumberZbs()));

	}

	@Override
	protected void dyjh() {
		// 当月计划
		if (sbdCompanies.isEmpty()) {
			list.add(new AccPipeFilter(yjhAcc, 3)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators())
					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
					.excludeZbs(ConfiguratorUtil.getRatioZbs()));

		} else {
			list.add(new AccPipeFilter(yjhAcc, 3)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators())
					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
					.excludeZbs(ConfiguratorUtil.getRatioZbs())
					.exclude(GSZB.YSZK32)
					.exclude(GSZB.CH35))
							
				.add(new YdjhProportionAccPipeFilter(sbdzbDao, sjAcc, 3, dh.getFirstMonth(), dh.getCur())
					.includeCompanies(sbdCompanies)
					.include(GSZB.YSZK32)
					.include(GSZB.CH35));

			if (!nonSbdCompanies.isEmpty()) {
				list.add(new AccPipeFilter(yjhAcc, 3)
						.includeCompanies(nonSbdCompanies)
						.include(GSZB.YSZK32)
						.include(GSZB.CH35));
			}
		}
	}

	@Override
	protected void dysj() {
		// 当月实际
		list.add(new AccPipeFilter(sjAcc, 4)
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
						.excludeZbs(ConfiguratorUtil.getRatioZbs()));
	}

	@Override
	protected void jhwcl() {
		// 计划完成率
		list.add(wclFilter.add(5, 4, 3));

	}

	@Override
	protected void qntq() {
		// 去年同期
		list.add(new AccPipeFilter(sjAcc, 6, dh.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs()));
		
	}

	@Override
	protected void tbzf() {
		// 同比增幅
		list.add(tbzzFilter.add(7, 4, 6));
	}

	@Override
	protected void jdlj() {
		// 季度累计
		list.add(new AccPipeFilter(sjAcc, 8, dh.getJdStart(), dh.getCur())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs())
				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
				.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
		.add(copyFilter
				.add(ConfiguratorUtil.getTimePointNumberZbs(), 4, 8));


		
	}

	@Override
	protected void jdjhwcl() {
		// 季度计划完成率
		list.add(wclFilter.add(9, 8, 1));
	}

	@Override
	protected void jdqntq() {
		// 季度去年同期
		list.add(new AccPipeFilter(sjAcc, 10, dh.getQntqJdStart(), dh
				.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs())
				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
				.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
		.add(new AccPipeFilter(sjAcc, 10, dh.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(ConfiguratorUtil.getTimePointNumberZbs()));		
	}

	@Override
	protected void jdtbzf() {
		// 同比增幅
		list.add(tbzzFilter
						.add(11, 8, 10));
	}

	@Override
	protected void ndlj() {
		// 年度累计
		list.add(new AccPipeFilter(sjAcc, 12, dh.getFirstMonth(), dh
				.getCur())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs())
				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
				.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
		.add(copyFilter
				.add(ConfiguratorUtil.getTimePointNumberZbs(), 4, 12));

		
	}

	@Override
	protected void ndljjhwcl() {
		// 累计计划完成率
		list.add(wclFilter.add(13, 12, 0));

				
	}

	@Override
	protected void ndljqntq() {
		// 去年同期
		list.add(new AccPipeFilter(sjAcc, 14, dh.getQnfirstMonth(), dh
				.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs())
				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
				.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
		.add(copyFilter
				.add(ConfiguratorUtil.getTimePointNumberZbs(), 10, 14));

		
	}

	@Override
	protected void ndljtbzf() {
		// 同比增幅
		list.add(tbzzFilter.add(15, 12, 14));

				
	}

	@Override
	protected void xjdsyyj() {
		// 下季度首月预计
		list.add(new AccPipeFilter(sjAcc, 16, dh.getXjdFirstMonth())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs()));

	
	}

	@Override
	protected void xjdcyyj() {
		// 下季度次月预计
		list.add(new AccPipeFilter(sjAcc, 17, dh.getXjdSecondMonth())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs()));
	}

	@Override
	protected void xjdmyyj() {
		// 下季度末月预计
		list.add(new AccPipeFilter(sjAcc, 18, dh.getXjdLastMonth())
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
						.excludeZbs(ConfiguratorUtil.getRatioZbs()));

				
	}

	@Override
	protected void xjdyjhj() {
		// 下季度预计合计
		list.add(new AccPipeFilter(sjAcc, 19, dh.getXjdFirstMonth(), dh
				.getXjdLastMonth())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs())
				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
		.add(new AccPipeFilter(sjAcc, 19, dh.getXjdLastMonth())
				.includeCompanies(allCompanies)
				.includeZbs(ConfiguratorUtil.getTimePointNumberZbs()));


		
	}

	@Override
	protected void xjdyjwcl() {
		// 下季度预计完成率
		list.add(wclFilter.add(20, 19, 2));

			
	}

	@Override
	protected void xjdndlj() {
		// 下季度年度累计
		list.add(new AccPipeFilter(sjAcc, 21, dh.getXjdDnFirstMonth(), dh
				.getXjdLastMonth())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs())
				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
				.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
		.add(copyFilter
				.add(ConfiguratorUtil.getTimePointNumberZbs(), 19, 21));

		
	}

	@Override
	protected void xjdndljwcl() {
		// 下季度年度累计完成率
		list.add(wclFilter
						.add(22, 21, 25));

				
	}

	@Override
	protected void xjdqntqndlj() {
		// 下季度去年同期年度累计
		list.add(new AccPipeFilter(sjAcc, 23, dh.getQntqXjdDnFirstMonth(),
				dh.getQntqXjdLastMonth())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs())
				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
				.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
		.add(new AccPipeFilter(sjAcc, 23, dh.getQntqXjdLastMonth())
				.includeCompanies(allCompanies)
				.includeZbs(ConfiguratorUtil.getTimePointNumberZbs()));	
	}

	@Override
	protected void xjdndljtbzf() {
		// 下季度年度累计同比增幅
		list.add(tbzzFilter
				.add(24, 21, 23));
	}

	@Override
	protected List<IPipeFilter> onFinished() {
		 // 添加特殊指标过滤器
		return	list.add(new RatioPipeFilter().excludeCol(5)// 计划完成率
						.excludeCol(7)// 同比增幅
						.excludeCol(9)// 季度计划完成率
						.excludeCol(11)// 同比增幅
						.excludeCol(13)// 累计计划完成率
						.excludeCol(15)// 同比增幅
						.excludeCol(20)// 下季度预计完成率
						.excludeCol(22)// 下季度年度累计完成率
						.excludeCol(24))// 下季度年度累计同比增幅
				.add(tbzzFilter)
				.add(wclFilter)
				.toList();
	}

	@Override
	protected void xjdqnjh() {
		// 下季度全年计划
		list.add(new AccPipeFilter(njhAcc, 25, dh.getXjdLastMonth())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs()));	
	}



}
