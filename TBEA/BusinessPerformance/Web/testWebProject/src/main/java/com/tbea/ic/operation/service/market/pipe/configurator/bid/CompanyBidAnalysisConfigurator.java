package com.tbea.ic.operation.service.market.pipe.configurator.bid;

import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.market.Indicator;
import com.tbea.ic.operation.service.market.pipe.filter.RatioPipeFilter;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.core.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.ZzlPipeFilter;


//配置表结构, 横线部分为不需要计算值
//				当月情况		年度累计
//投标数量											
//投标金额												
//中标金额											
//中标率			--								


public class CompanyBidAnalysisConfigurator implements IPipeConfigurator{

	public CompanyBidAnalysisConfigurator(IAccumulator acc) {
		super();
		this.acc = acc;
	}

	IAccumulator acc;
	
	@Override
	public void onConfiguring(IPipe pipe) {
		List<Company> comps = pipe.getCompanies();
		DateHelper dh = new DateHelper(pipe.getDate());
		pipe.addFilter(new AccPipeFilter(acc, 0, dh.getCur())
			.include(Indicator.TBSL.ordinal())
			.include(Indicator.TBJE.ordinal())
			.include(Indicator.ZBJE.ordinal())
			.includeCompanies(comps))
		.addFilter(new AccPipeFilter(acc, 1, dh.getFirstMonth(), dh.getCur())
			.include(Indicator.TBSL.ordinal())
			.include(Indicator.TBJE.ordinal())
			.include(Indicator.ZBJE.ordinal())
			.includeCompanies(comps))
//		.addFilter(new AccPipeFilter(acc, 1, dh.getFirstMonth(), dh.getCur())
//			.include(Indicator.ZBL.ordinal())
//			.includeCompanies(comps))
		.addFilter(new RatioPipeFilter()
			.exclude(0));
	}

	@Override
	public int getColumnCount() {
		return 2;
	}


}
