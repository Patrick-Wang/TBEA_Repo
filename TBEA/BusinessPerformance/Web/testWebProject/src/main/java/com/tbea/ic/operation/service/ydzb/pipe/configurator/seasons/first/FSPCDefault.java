package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.first;

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

public class FSPCDefault extends FirstSeasonPredictionConfigurator {

	public FSPCDefault(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc,
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
		list.add(new AccPipeFilter(njhAcc, 0)
					.includeCompanies(allCompanies)
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
				.add(ConfiguratorUtil.getTimePointNumberZbs(), 2, 1));
		
	}
	
	@Override
	protected void dyjh() {
		// 当月计划
		if (sbdCompanies.isEmpty()) {
			list.add(new AccPipeFilter(yjhAcc, 2)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators())
					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
					.excludeZbs(ConfiguratorUtil.getRatioZbs()));
		} else {
			list.add(new AccPipeFilter(yjhAcc, 2)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators())
					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
					.excludeZbs(ConfiguratorUtil.getRatioZbs())
					.exclude(GSZB.YSZK32)
					.exclude(GSZB.CH35))
						
				.add(new YdjhProportionAccPipeFilter(sbdzbDao, sjAcc, 2, dh.getFirstMonth(), dh.getCur())
					.includeCompanies(sbdCompanies)
					.include(GSZB.YSZK32)
					.include(GSZB.CH35));

			if (!nonSbdCompanies.isEmpty()) {
				list.add(new AccPipeFilter(yjhAcc, 2)
						.includeCompanies(nonSbdCompanies)
						.include(GSZB.YSZK32)
						.include(GSZB.CH35));
			}
		}
		
	}

	@Override
	protected void dysj() {
		// 当月实际
		list.add(new AccPipeFilter(sjAcc, 3)
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs()));
		
	}

	@Override
	protected void jhwcl() {
		// 计划完成率
		list.add(wclFilter.add(4, 3, 2));
		
	}

	@Override
	protected void qntq() {
		// 去年同期
		list.add(new AccPipeFilter(sjAcc, 5, dh.getQntq())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs()));		
	}

	@Override
	protected void tbzf() {
		// 同比增幅
		list.add(tbzzFilter.add(6, 3, 5));	
	}

	@Override
	protected void cyyj() {
		//次月预计		
		list.add(new AccPipeFilter(sjAcc, 7, dh.getSecondMonthinSeason())
			.includeCompanies(allCompanies)
			.includeZbs(pipe.getIndicators())
			.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
			.excludeZbs(ConfiguratorUtil.getRatioZbs()));
	}

	@Override
	protected void myyj() {
		//末月预计
		list.add(new AccPipeFilter(sjAcc, 8, dh.getLastMonthinSeason())
			.includeCompanies(allCompanies)
			.includeZbs(pipe.getIndicators())
			.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
			.excludeZbs(ConfiguratorUtil.getRatioZbs()));		
	}

	@Override
	protected void jdyjhj() {
		// 季度预计合计
		list.add(new AccPipeFilter(sjAcc, 9, dh.getCur(), dh.getLastMonthinSeason())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs())
				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs()))
		.add(copyFilter
				.add(ConfiguratorUtil.getTimePointNumberZbs(), 8, 9));	
	}

	@Override
	protected void jdyjwcl() {
		// 季度预计完成率
		list.add(wclFilter.add(10, 9, 1));		
	}

	@Override
	protected void jdqntq() {
		// 季度去年同期
		list.add(new AccPipeFilter(sjAcc, 11, dh.getQntqJdStart(), dh.getQntqJdEnd())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs())
				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
				.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
		.add(new AccPipeFilter(sjAcc, 11, dh.getQntqJdStart())
				.includeCompanies(allCompanies)
				.includeZbs(ConfiguratorUtil.getTimePointNumberZbs()));		
	}

	@Override
	protected void jdtbzf() {
		// 同比增幅
		list.add(tbzzFilter.add(12, 9, 11));
	}

	@Override
	protected void ndlj() {
		// 年度累计
		list.add(new AccPipeFilter(sjAcc, 13, dh.getFirstMonth(), dh.getCur())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs())
				.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
				.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
		.add(copyFilter
				.add(ConfiguratorUtil.getTimePointNumberZbs(), 3, 13));
	}

	@Override
	protected void ndljjhwcl() {
		// 累计计划完成率
		list.add(wclFilter.add(14, 13, 0));
	}

	@Override
	protected void ndqntq() {
		// 去年同期
		list.add(new AccPipeFilter(sjAcc, 15, dh.getQnfirstMonth(), dh.getQntq())
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
						.excludeZbs(ConfiguratorUtil.getRatioZbs())
						.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
						.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
				.add(copyFilter
						.add(ConfiguratorUtil.getTimePointNumberZbs(), 5, 15));

	}

	@Override
	protected void ndtbzf() {
		// 同比增幅
		list.add(tbzzFilter.add(16, 13, 15));
	}

	@Override
	protected List<IPipeFilter> onFinished() {
		// 添加特殊指标过滤器
		return list.add(new RatioPipeFilter().excludeCol(4)// 计划完成率
				.excludeCol(6)// 同比增幅
				.excludeCol(10)// 季度计划完成率
				.excludeCol(12)// 同比增幅
				.excludeCol(14)// 累计计划完成率
				.excludeCol(16))// 同比增幅
		.add(tbzzFilter)
		.add(wclFilter)
		.toList();
	}

}
