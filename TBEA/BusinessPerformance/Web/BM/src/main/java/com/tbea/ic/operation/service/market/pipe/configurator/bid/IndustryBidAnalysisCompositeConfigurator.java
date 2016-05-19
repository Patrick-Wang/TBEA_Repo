package com.tbea.ic.operation.service.market.pipe.configurator.bid;

import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.market.Indicator;
import com.tbea.ic.operation.service.market.pipe.filter.composite.CompositeRatioPipeFilter;
import com.tbea.ic.operation.service.util.pipe.configurator.composite.AbstractCompositeConfigurator;
import com.tbea.ic.operation.service.util.pipe.core.IPipe;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;
import com.tbea.ic.operation.service.util.pipe.filter.composite.AccPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.RatioPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.WclPipeFilter;
import com.tbea.ic.operation.service.util.pipe.filter.composite.ZzlPipeFilter;



//配置表结构, 横线部分为不需要计算值
//			当月情况	年度累计		去年同期累计		同比增幅
//投标数量	cp1											--
//投标数量	cp2											--
//投标数量	cp3											--
//投标数量												--
//投标金额												
//中标金额												--
//中标率		--							--


public class IndustryBidAnalysisCompositeConfigurator extends AbstractCompositeConfigurator{

	public IndustryBidAnalysisCompositeConfigurator(
			Map<Company, List<Company>> computeMap) {
		super(computeMap);
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	protected void onConfiguring(
			IPipe pipe,
			IAccumulator acc,
			List<Integer> zbs,
			int startRow,
			int step,
			List<Company> subComps,
			WclPipeFilter wclFilter,
			ZzlPipeFilter tbzzFilter) {
		pipe.addFilter(new AccPipeFilter(acc, 0, startRow, step)
			.includeCompanies(subComps)
			.exclude(Indicator.ZBL.ordinal()))
		.addFilter(new AccPipeFilter(acc, 1, startRow, step)
			.includeCompanies(subComps)
			.exclude(Indicator.ZBL.ordinal()))
		.addFilter(new AccPipeFilter(acc, 2, startRow, step)
			.includeCompanies(subComps))
		.addFilter(new CompositeRatioPipeFilter(startRow, step)
			.exclude(0)
			.exclude(3))
		.addFilter(new RatioPipeFilter(startRow, step)
			.exclude(3)
			.add(Indicator.TBZB.ordinal(), Indicator.TBJE.ordinal()))
		.addFilter(new ZzlPipeFilter()
			.add(3, 1, 2)
			.exclude(Indicator.TBSL.ordinal())
			.exclude(Indicator.TBJE.ordinal())
			.exclude(Indicator.ZBJE.ordinal())
			.exclude(Indicator.ZBL.ordinal())
			.exclude(Indicator.TBZB.ordinal())
			.includeRow(startRow, step));
	}


}
