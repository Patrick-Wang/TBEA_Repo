package com.tbea.ic.operation.service.market.pipe.configurator.bid;

import java.util.List;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.market.Indicator;
import com.tbea.ic.operation.service.market.pipe.AbstractMarketConfigurator;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.filter.basic.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.basic.ZzlPipeFilter;


//配置表结构, 横线部分为不需要计算值
//				当月情况		年度累计		去年同期累计		同比增幅
//投标数量												--
//投标金额												--
//中标金额												--
//中标率			--									--
//签约额			--										

public class IndustryBidAnalysisConfigurator extends AbstractMarketConfigurator{

	@Override
	public void onConfiguring(IPipe pipe) {
		List<Company> comps = pipe.getCompanies();
		DateHelper dh = new DateHelper(pipe.getDate());
		pipe.addFilter(new AccPipeFilter(null, 0, dh.getCur())
			.include(Indicator.TBSL.ordinal())
			.include(Indicator.TBJE.ordinal())
			.include(Indicator.ZBJE.ordinal())
			.includeCompanies(comps))
		.addFilter(new AccPipeFilter(null, 1, dh.getFirstMonth(), dh.getCur())
			.include(Indicator.TBSL.ordinal())
			.include(Indicator.TBJE.ordinal())
			.include(Indicator.ZBJE.ordinal())
			.include(Indicator.QYJE.ordinal())
			.includeCompanies(comps))
		.addFilter(new AccPipeFilter(null, 1, dh.getFirstMonth(), dh.getCur())
			.include(Indicator.ZBL.ordinal())
			.includeCompanies(comps))
		.addFilter(new AccPipeFilter(null, 2, dh.getQnfirstMonth(), dh.getQntq())
			.include(Indicator.TBSL.ordinal())
			.include(Indicator.TBJE.ordinal())
			.include(Indicator.ZBJE.ordinal())
			.include(Indicator.QYJE.ordinal())
			.includeCompanies(comps))
		.addFilter(new AccPipeFilter(null, 2, dh.getQnfirstMonth(), dh.getQntq())
			.include(Indicator.ZBL.ordinal())
			.includeCompanies(comps))
		.addFilter(new ZzlPipeFilter()
			.add(3, 2, 1)
			.exclude(Indicator.TBSL.ordinal())
			.exclude(Indicator.TBJE.ordinal())
			.exclude(Indicator.ZBJE.ordinal())
			.exclude(Indicator.ZBL.ordinal()));
	}

	@Override
	public int getColumnCount() {
		return 4;
	}


}
