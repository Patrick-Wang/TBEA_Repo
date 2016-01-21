package com.tbea.ic.operation.service.ydzb.pipe.configurator.seasons.third;

import java.util.List;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.RatioPipeFilter;

class TSPC2016SingleCompany extends TSPC2016 {

	public TSPC2016SingleCompany(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc,
			IAccumulator yjhAcc, IAccumulator njhAcc) {
		super(sbdzbDao, sjAcc, yjhAcc, njhAcc);
	}
	
	@Override
	protected void qnjh() {
		// 全年计划
		super.qnjh();
		((AccPipeFilter)(list.last())).include(GSZB.XSLRL_28);
	}
	
	@Override
	protected void xjdqnjh() {
		// 下季度全年计划
		super.xjdqnjh();
		((AccPipeFilter)(list.last())).include(GSZB.XSLRL_28);
	}

	@Override
	protected void dyjh() {
		// 当月计划
		super.dyjh();
		((AccPipeFilter)(list.last())).include(GSZB.XSLRL_28);
	}
	
	@Override
	protected List<IPipeFilter> onFinished() {
		super.onFinished();
		RatioPipeFilter filter = (RatioPipeFilter)list.lastIndexOf(RatioPipeFilter.class);
		filter.exclude(GSZB.XSLRL_28.getValue(), 0)
				.exclude(GSZB.XSLRL_28.getValue(), 3);
		return list.toList();
	}
}
