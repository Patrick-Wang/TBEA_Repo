package com.tbea.ic.operation.service.ydzb.pipe.configurator.rank.rjlr;

import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.configurator.ConfiguratorUtil;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.RatioPipeFilter;

public class RjlrDataConfigurator implements IPipeConfigurator{

	IAccumulator sjAcc;
	IAccumulator yjhAcc;
	IAccumulator njhAcc;
	
	public RjlrDataConfigurator(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc, IAccumulator yjhAcc, IAccumulator njhAcc) {
		this.sjAcc = sjAcc;
		this.yjhAcc = yjhAcc;
		this.njhAcc = njhAcc;
	}
	
	@Override
	public void onConfiguring(IPipe pipe) {

			List<Company> allCompanies = pipe.getCompanies();
			DateHelper dh = new DateHelper(pipe.getDate());

			// 年度累计
			pipe.addFilter(new AccPipeFilter(sjAcc, 0, dh.getFirstMonth(), dh.getCur())
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators())
					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
					.excludeZbs(ConfiguratorUtil.getRatioZbs())
					.excludeZbs(ConfiguratorUtil.getTimePointNumberZbs())
					.excludeZbs(ConfiguratorUtil.getZhHiddenZbs()))
			.addFilter(new AccPipeFilter(sjAcc, 0)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators())
					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
					.excludeZbs(ConfiguratorUtil.getRatioZbs()))

			// 当月实际
			.addFilter(new AccPipeFilter(sjAcc, 2)
				.includeCompanies(allCompanies)
				.includeZbs(pipe.getIndicators())
				.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
				.excludeZbs(ConfiguratorUtil.getRatioZbs()))

			// 添加特殊指标过滤器
			.addFilter(new RatioPipeFilter());
	}

	@Override
	public int getColumnCount() {
		return 4;
	}
}
