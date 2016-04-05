package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.second;

import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.configurator.ConfiguratorUtil;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;

class SSPC2016 extends SSPCDefault {

	public SSPC2016(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc,
			IAccumulator yjhAcc, IAccumulator njhAcc) {
		super(sbdzbDao, sjAcc, yjhAcc, njhAcc);
	}

	@Override
	protected void dyjh() {
		// 当月计划
		list.add(new AccPipeFilter(yjhAcc, 2)
					.includeCompanies(allCompanies)
					.includeZbs(pipe.getIndicators())
					.excludeZbs(ConfiguratorUtil.getInvisiableZbs())
					.excludeZbs(ConfiguratorUtil.getRatioZbs()));
	}
}
