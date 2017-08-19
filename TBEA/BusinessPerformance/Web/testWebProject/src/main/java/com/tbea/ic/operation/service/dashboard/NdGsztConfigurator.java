package com.tbea.ic.operation.service.dashboard;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.configurator.ConfiguratorUtil;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.CopyPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.RatioPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.WclPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.ZzlPipeFilter;
import com.xml.frame.report.util.EasyList;


public class NdGsztConfigurator implements IPipeConfigurator {

	protected IAccumulator sjAcc;
	protected IAccumulator yjhAcc;
	protected IAccumulator njhAcc;
	protected SbdNdjhZbDao sbdzbDao;
	protected enum ColumnType{
		qnjh,// 全年计划
		dysj,// 当月实际
		qntq,// 去年同期
		ndlj,// 年度累计
		ljjhwcl,// 累计计划完成率
		ndqntq,// 去年同期
		ndtbzf,// 同比增幅
		end
	} ;
	

	@Override
	public int getColumnCount() {
		return ColumnType.end.ordinal();
	}
	
	@Override
	public void onConfiguring(IPipe pipe) {
		onStart(pipe);
		qnjh();// 全年计划
		dysj();// 当月实际
		qntq();// 去年同期
		ndlj();// 年度累计
		ljjhwcl();// 累计计划完成率
		ndqntq();// 去年同期
		ndtbzf();// 同比增幅
		pipe.addFilter(onFinished());
	}

	
	public NdGsztConfigurator(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc,
			IAccumulator yjhAcc, IAccumulator njhAcc) {
		this.sbdzbDao = sbdzbDao;
		this.sjAcc = sjAcc;
		this.njhAcc = njhAcc;
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

	 void onStart(IPipe pipe) {
		list.clear();
		this.pipe = pipe;
		allCompanies = pipe.getCompanies();
		ConfiguratorUtil.seperate(sbdzbDao, allCompanies, sbdCompanies, nonSbdCompanies);
		dh = new DateHelper(pipe.getDate());
		wclFilter = new WclPipeFilter();
		tbzzFilter = new ZzlPipeFilter();
		copyFilter = new CopyPipeFilter();
	}

	void qnjh() {
		// 全年计划
		list.add(new AccPipeFilter(njhAcc, ColumnType.qnjh.ordinal())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs()));
	}

	void dysj() {
		// 当月实际
		list.add(new AccPipeFilter(sjAcc, ColumnType.dysj.ordinal())
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs()));

	}


	void qntq() {
		// 去年同期
		list.add(new AccPipeFilter(sjAcc, ColumnType.qntq.ordinal(), dh
				.getQntq()).includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs()));
	}




	void ndlj() {
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

	void ljjhwcl() {
		list.add(wclFilter.add(ColumnType.ljjhwcl.ordinal(),
				ColumnType.ndlj.ordinal(), ColumnType.qnjh.ordinal()));
	}

	void ndqntq() {
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
						ColumnType.qntq.ordinal(), ColumnType.ndqntq.ordinal()));
	}

	void ndtbzf() {
		// 同比增幅
		list.add(tbzzFilter.add(ColumnType.ndtbzf.ordinal(),
				ColumnType.ndlj.ordinal(), ColumnType.ndqntq.ordinal()));
	}

	List<IPipeFilter> onFinished() {
		// 添加特殊指标过滤器
		return list
				.add(new RatioPipeFilter()
						.excludeCol(ColumnType.ljjhwcl.ordinal())// 累计计划完成率
						.excludeCol(ColumnType.ndtbzf.ordinal()))// 同比增幅
				.add(tbzzFilter).add(wclFilter).toList();
	}
}
