package com.tbea.ic.operation.service.ydzb.pipe.configurator.standard;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.EasyList;
import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.configurator.ConfiguratorUtil;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.CopyPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.RatioPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.WclPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.ZzlPipeFilter;
import com.tbea.ic.operation.service.ydzb.pipe.filter.basic.YdjhProportionAccPipeFilter;

class SCDefault extends StandardConfigurator {

	public SCDefault(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc,
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
		list.add(new AccPipeFilter(njhAcc, ColumnType.qnjh.ordinal())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs()));
	}

	@Override
	protected void dyjh() {
		// 当月计划
		if (sbdCompanies.isEmpty()) {
			list.add(new AccPipeFilter(yjhAcc, ColumnType.dyjh.ordinal())
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators())
					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
					.excludeZbs(ConfiguratorUtil.getRatioZbs()));
		} else {
			list.add(
					new AccPipeFilter(yjhAcc, ColumnType.dyjh.ordinal())
							.includeCompanies(allCompanies)
							.includeZbs(pipe.getIndicators())
							.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
							.excludeZbs(ConfiguratorUtil.getRatioZbs())
							.exclude(GSZB.YSZK32)
							.exclude(GSZB.CH35))

			.add(new YdjhProportionAccPipeFilter(sbdzbDao, sjAcc,
					ColumnType.dyjh.ordinal(), dh.getFirstMonth(), dh.getCur())
					.includeCompanies(sbdCompanies).include(GSZB.YSZK32)
					.include(GSZB.CH35));

			if (!nonSbdCompanies.isEmpty()) {
				list.add(new AccPipeFilter(yjhAcc, ColumnType.dyjh.ordinal())
						.includeCompanies(nonSbdCompanies).include(GSZB.YSZK32)
						.include(GSZB.CH35));
			}
		}
	}

	@Override
	protected void dysj() {
		// 当月实际
		list.add(new AccPipeFilter(sjAcc, ColumnType.dysj.ordinal())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs()));

	}

	@Override
	protected void jhwcl() {
		list.add(wclFilter.add(ColumnType.jhwcl.ordinal(),
				ColumnType.dysj.ordinal(), ColumnType.dyjh.ordinal()));
	}

	@Override
	protected void qntq() {
		// 去年同期
		list.add(new AccPipeFilter(sjAcc, ColumnType.qntq.ordinal(), dh
				.getQntq()).includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs()));
	}

	@Override
	protected void tbzf() {
		// 同比增幅
		list.add(tbzzFilter.add(ColumnType.tbzf.ordinal(),
				ColumnType.dysj.ordinal(), ColumnType.qntq.ordinal()));
	}

	@Override
	protected void jdjh() {
		// 季度计划
		list.add(
				new AccPipeFilter(yjhAcc, ColumnType.jdjh.ordinal(), dh
						.getJdStart(), DateHelper.getJdEnd(dh.getCur()))
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
						.excludeZbs(ConfiguratorUtil.getRatioZbs())
						.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())).add(
				copyFilter.add(ConfiguratorUtil.getTimePointNumberZbs(),
						ColumnType.dyjh.ordinal(), ColumnType.jdjh.ordinal()));
	}

	@Override
	protected void jdlj() {
		// 季度累计
		list.add(
				new AccPipeFilter(sjAcc, ColumnType.jdlj.ordinal(), dh
						.getJdStart(), dh.getCur())
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
						.excludeZbs(ConfiguratorUtil.getRatioZbs())
						.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
						.excludeZbs(ConfiguratorUtil.getZhHiddenZbs())).add(
				copyFilter.add(ConfiguratorUtil.getTimePointNumberZbs(),
						ColumnType.dysj.ordinal(), ColumnType.jdlj.ordinal()));
	}

	@Override
	protected void jdjhwcl() {
		// 季度计划完成率
		list.add(wclFilter.add(ColumnType.jdjhwcl.ordinal(),
				ColumnType.jdlj.ordinal(), ColumnType.jdjh.ordinal()));
	}

	@Override
	protected void jdqntq() {
		// 季度去年同期
		list.add(
				new AccPipeFilter(sjAcc, ColumnType.jdqntq.ordinal(), dh
						.getQntqJdStart(), dh.getQntq())
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
						.excludeZbs(ConfiguratorUtil.getRatioZbs())
						.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
						.excludeZbs(ConfiguratorUtil.getZhHiddenZbs())).add(
				copyFilter.add(ConfiguratorUtil.getTimePointNumberZbs(),
						ColumnType.qntq.ordinal(), ColumnType.jdqntq.ordinal()));
	}

	@Override
	protected void jdtbzf() {
		// 同比增幅
		list.add(tbzzFilter.add(ColumnType.jdtbzf.ordinal(),
				ColumnType.jdlj.ordinal(), ColumnType.jdqntq.ordinal()));
	}

	@Override
	protected void ndlj() {
		// 年度累计
		list.add(
				new AccPipeFilter(sjAcc, ColumnType.ndlj.ordinal(), dh
						.getFirstMonth(), dh.getCur())
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
						.excludeZbs(ConfiguratorUtil.getRatioZbs())
						.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
						.excludeZbs(ConfiguratorUtil.getZhHiddenZbs())).add(
				copyFilter.add(ConfiguratorUtil.getTimePointNumberZbs(),
						ColumnType.dysj.ordinal(), ColumnType.ndlj.ordinal()));
	}

	@Override
	protected void ljjhwcl() {
		list.add(wclFilter.add(ColumnType.ljjhwcl.ordinal(),
				ColumnType.ndlj.ordinal(), ColumnType.qnjh.ordinal()));
	}

	@Override
	protected void ndqntq() {
		// 去年同期
		list.add(
				new AccPipeFilter(sjAcc, ColumnType.ndqntq.ordinal(), dh
						.getQnfirstMonth(), dh.getQntq())
						.includeCompanies(allCompanies)
						.includeZbs(pipe.getIndicators())
						.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
						.excludeZbs(ConfiguratorUtil.getRatioZbs())
						.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
						.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
				.add(copyFilter.add(ConfiguratorUtil.getTimePointNumberZbs(),
						ColumnType.jdqntq.ordinal(), ColumnType.ndqntq.ordinal()));
	}

	@Override
	protected void ndtbzf() {
		// 同比增幅
		list.add(tbzzFilter.add(ColumnType.ndtbzf.ordinal(),
				ColumnType.ndlj.ordinal(), ColumnType.ndqntq.ordinal()));
	}

	@Override
	protected List<IPipeFilter> onFinished() {
		// 添加特殊指标过滤器
		return list
				.add(new RatioPipeFilter()
						.excludeCol(ColumnType.jhwcl.ordinal())// 计划完成率
						.excludeCol(ColumnType.tbzf.ordinal())// 同比增幅
						.excludeCol(ColumnType.jdjhwcl.ordinal())// 季度计划完成率
						.excludeCol(ColumnType.jdtbzf.ordinal())// 同比增幅
						.excludeCol(ColumnType.ljjhwcl.ordinal())// 累计计划完成率
						.excludeCol(ColumnType.ndtbzf.ordinal()))// 同比增幅
				.add(tbzzFilter).add(wclFilter).toList();
	}

}
