package com.tbea.ic.operation.service.ydzb.pipe.configurator.standard;

import java.util.List;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.service.util.pipe.core.filter.IPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.RatioPipeFilter;


//单个公司的全年计划和当月计划 的 销售利润率 为从数据库实际获取的值 
class SC2016SingleCompany extends SC2016 {

	public SC2016SingleCompany(SbdNdjhZbDao sbdzbDao, IAccumulator sjAcc,
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
	protected void dyjh() {
		// 当月计划
		super.dyjh();
		((AccPipeFilter)(list.last())).include(GSZB.XSLRL_28);
	}
	
	@Override
	protected List<IPipeFilter> onFinished() {
		super.onFinished();
		RatioPipeFilter filter = (RatioPipeFilter)list.lastIndexOf(RatioPipeFilter.class);
		filter.exclude(GSZB.XSLRL_28.value(), ColumnType.qnjh.ordinal())
				.exclude(GSZB.XSLRL_28.value(), ColumnType.dyjh.ordinal());
		return list.toList();
	}
}
