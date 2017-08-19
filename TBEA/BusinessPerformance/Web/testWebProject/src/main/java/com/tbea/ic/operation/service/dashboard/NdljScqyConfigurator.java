package com.tbea.ic.operation.service.dashboard;

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
import com.xml.frame.report.util.EasyList;


public class NdljScqyConfigurator implements IPipeConfigurator {

	protected IAccumulator sjAcc;
	protected IAccumulator yjhAcc;
	protected IAccumulator njhAcc;
	protected SbdNdjhZbDao sbdzbDao;
	protected enum ColumnType{
		dysj,// 当月实际
		ndlj,// 年度累计
		end
	} ;
	

	@Override
	public int getColumnCount() {
		return ColumnType.end.ordinal();
	}
	
	@Override
	public void onConfiguring(IPipe pipe) {
		onStart(pipe);
		dysj();// 当月实际
		ndlj();// 年度累计
		pipe.addFilter(list.toList());
	}

	
	public NdljScqyConfigurator(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc,
			IAccumulator yjhAcc, IAccumulator njhAcc) {
		this.sbdzbDao = sbdzbDao;
		this.sjAcc = sjAcc;
		this.njhAcc = njhAcc;
	}

	List<Company> allCompanies;
	DateHelper dh;
	CopyPipeFilter copyFilter;
	IPipe pipe;

	protected EasyList<IPipeFilter> list = new EasyList<IPipeFilter>();

	 void onStart(IPipe pipe) {
		list.clear();
		this.pipe = pipe;
		allCompanies = pipe.getCompanies();
		dh = new DateHelper(pipe.getDate());
		copyFilter = new CopyPipeFilter();
	}

	void dysj() {
		// 当月实际
		list.add(new AccPipeFilter(sjAcc, ColumnType.dysj.ordinal())
				.includeCompanies(allCompanies)
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


	
}
