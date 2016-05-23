package com.tbea.ic.operation.service.market.pipe.configurator.sign;

import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.market.Indicator;
import com.tbea.ic.operation.service.market.pipe.filter.RatioPipeFilter;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.ZzlPipeFilter;



//配置表结构, 横线部分为不需要计算值
//当月情况		当月	年度累计		去年同期累计		同比增幅
//合同数量									-
//签约金额												

public class IndustrySignAnalysisConfigurator implements IPipeConfigurator{

	public IndustrySignAnalysisConfigurator(IAccumulator acc) {
		super();
		this.acc = acc;
	}

	IAccumulator acc;
	
	@Override
	public void onConfiguring(IPipe pipe) {
		List<Company> comps = pipe.getCompanies();
		DateHelper dh = new DateHelper(pipe.getDate());
		pipe.addFilter(new AccPipeFilter(acc, 0, dh.getCur())
			.include(Indicator.HTSL.ordinal())
			.include(Indicator.QYJE.ordinal())
			.includeCompanies(comps))
		.addFilter(new AccPipeFilter(acc, 1, dh.getFirstMonth(), dh.getCur())
			.include(Indicator.HTSL.ordinal())
			.include(Indicator.QYJE.ordinal())
			.includeCompanies(comps))
		.addFilter(new AccPipeFilter(acc, 2, dh.getQnfirstMonth(), dh.getQntq())
			.include(Indicator.HTSL.ordinal())
			.include(Indicator.QYJE.ordinal())
			.includeCompanies(comps))
		.addFilter(new ZzlPipeFilter()
			.add(3, 1, 2)
			.exclude(Indicator.HTSL.ordinal()));
	}

	@Override
	public int getColumnCount() {
		return 4;
	}


}
